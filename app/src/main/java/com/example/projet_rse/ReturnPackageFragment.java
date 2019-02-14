package com.example.projet_rse;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ReturnPackageFragment extends Fragment {

    // TODO : Ajouter un date picker

    private EditText NumberPackages;
    private TextView Address;
    private Button ValidateReturn;

        public static ReturnPackageFragment newInstance() {
            return (new ReturnPackageFragment());
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.fragment_returnpackage, container, false);
            NumberPackages = (EditText) view.findViewById(R.id.et_packageNumber);
            SharedPreferences settings = this.getActivity().getSharedPreferences("PREFS", 0);
            NumberPackages.setText(settings.getString("value", ""));

            Address = (TextView) view.findViewById(R.id.tv_address);
            ValidateReturn = (Button) view.findViewById(R.id.bt_validateReturn);
            ValidateReturn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int n = Integer.parseInt(NumberPackages.getText().toString());
                    String message = null;
                    if(n > 0 && TextUtils.isEmpty(Address.getText().toString())){
                        message = "Retour validée";
                    } else {
                        message = "Erreur";
                    }
                    Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();


                }
            });
            return view;
        }
}
