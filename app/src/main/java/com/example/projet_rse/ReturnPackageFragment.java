package com.example.projet_rse;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ReturnPackageFragment extends Fragment {

    private EditText EditTextReturnPackageNumber;
    private TextView TextViewReturnAddress;
    private TextView TextViewReturnDate;
    private Button ButtonEditReturnDate;
    private Button ButtonValidateReturn;

    private StorageHelper storageHelper;

        public static ReturnPackageFragment newInstance() {
            return (new ReturnPackageFragment());
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            storageHelper = new StorageHelper(getContext());

            View view = inflater.inflate(R.layout.fragment_returnpackage, container, false);
            EditTextReturnPackageNumber = (EditText) view.findViewById(R.id.et_ReturnPackageNumber);
            EditTextReturnPackageNumber.setText("0");
            UserAccount userAccount = storageHelper.GetUserAccount();

            TextViewReturnAddress = (TextView) view.findViewById(R.id.tv_ReturnAddress);
            TextViewReturnAddress.setText(userAccount.getAddress());
            TextViewReturnAddress.setLines(userAccount.GetAddressLines());
            ButtonValidateReturn = (Button) view.findViewById(R.id.bt_ValidateReturn);
            ButtonValidateReturn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int n = Integer.parseInt(EditTextReturnPackageNumber.getText().toString());

                    String message = null;
                    if(n > 0 && !TextUtils.isEmpty(TextViewReturnAddress.getText().toString())){
                        message = "Demande Retour validée";
                        storageHelper.StoreHistory(new History(TextViewReturnDate.getText().toString(),EditTextReturnPackageNumber.getText().toString(),TextViewReturnAddress.getText().toString()));
                    } else {
                        message = "Une erreur s'est produite";
                    }
                    Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();


                }
            });
            String date = "";
            TextViewReturnDate = (TextView) view.findViewById(R.id.tv_ReturnDate);
            Date todayDate = Calendar.getInstance().getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String todayString = formatter.format(todayDate);
            TextViewReturnDate.setText(todayString);
            ButtonEditReturnDate = (Button) view.findViewById(R.id.bt_EditReturnDate);
            ButtonEditReturnDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View toto = LayoutInflater.from(getActivity())
                            .inflate(R.layout.dialog_date,null);
                    final DatePicker datePicker = (DatePicker) toto.findViewById(R.id.dialog_date_date_picker);
                    android.support.v7.app.AlertDialog.Builder alert = new android.support.v7.app.AlertDialog.Builder(getActivity());
                    alert.setTitle("Datepicker");
                    alert.setView(toto);
                    alert.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            int year = datePicker.getYear();
                            int mon = datePicker.getMonth();
                            int day = datePicker.getDayOfMonth();
                            String date = String.valueOf(day) + "/" + String.valueOf(mon) + "/" + String.valueOf(year);
                            TextViewReturnDate.setText(date);
                        }
                    });
                alert.show();
                }
            });
            return view;
        }
}
