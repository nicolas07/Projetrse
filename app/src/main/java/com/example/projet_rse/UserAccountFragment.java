package com.example.projet_rse;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserAccountFragment extends Fragment implements View.OnClickListener {

    private TextView TextViewName;
    private TextView TextViewFirstName;
    private TextView TextViewAddress;
    private TextView TextViewEmail;
    private TextView TextViewPassword;

    private Button ButtonEditName;
    private Button ButtonEditFirstName;
    private Button ButtonEditAddress;
    private Button ButtonEditEmail;
    private Button ButtonEditPassword;

    private UserAccount userAccount;

    // TODO : Imagebutton Edit
    // TODO : Maj des TextView après dismiss des alertdialog

    public static UserAccountFragment newInstance() {
        return (new UserAccountFragment());
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        userAccount = new UserAccount("Nom","Prénom","14 rue Sarrette - 75014 Paris","sdfsgfdgdgdg","dffd@df.df");


        View view = inflater.inflate(R.layout.fragment_useraccount, container, false);
        TextViewName = (TextView) view.findViewById(R.id.tv_Name);
        TextViewFirstName = (TextView) view.findViewById(R.id.tv_FirstName);
        TextViewAddress = (TextView) view.findViewById(R.id.tv_Address);
        TextViewEmail = (TextView) view.findViewById(R.id.tv_EMail);
        TextViewPassword = (TextView) view.findViewById(R.id.tv_Password);

        TextViewName.setText(userAccount.getName());
        TextViewFirstName.setText(userAccount.getFirstName());
        TextViewAddress.setText(userAccount.getAddress());
        TextViewEmail.setText(userAccount.getEmail());
        TextViewPassword.setText(userAccount.getPassword());

        ButtonEditName = (Button) view.findViewById(R.id.bt_EditName);
        ButtonEditName.setOnClickListener(this);
        ButtonEditFirstName = (Button) view.findViewById(R.id.bt_EditFirstName);
        ButtonEditFirstName.setOnClickListener(this);
        ButtonEditAddress = (Button) view.findViewById(R.id.bt_EditAddress);
        ButtonEditAddress.setOnClickListener(this);
        ButtonEditEmail= (Button) view.findViewById(R.id.bt_EditEMail);
        ButtonEditEmail.setOnClickListener(this);
        ButtonEditPassword = (Button) view.findViewById(R.id.bt_EditPassword);
        ButtonEditPassword.setOnClickListener(this);

        UserAccountHelper userAccountHelper = new UserAccountHelper(getActivity());
        userAccountHelper.StoreUserAccount(userAccount);
        UserAccount acc = userAccountHelper.GetUserAccount();

        return view;
    }


    @Override
    public void onClick(View v) {

        int i = v.getId();

        String message = "";
        String title = "";
        String hint = "";

        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        final EditText SimpleEditText = new EditText(getActivity());
        alert.setView(SimpleEditText);
        switch (i){
            case R.id.bt_EditAddress:
                title = "Nouvelle Adresse : ";
                hint = userAccount.getAddress();
                alert.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    String input = SimpleEditText.getText().toString();
                    userAccount.setAddress(input);
                    }
                });
                break;
            case R.id.bt_EditEMail:
                title = "Nouvel Email :";
                hint = userAccount.getEmail();
                alert.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String input = SimpleEditText.getText().toString();
                        userAccount.setEmail(input);
                    }
                });
                break;
            case R.id.bt_EditFirstName:
                title = "Nouveau Prénom :";
                hint = userAccount.getFirstName();
                alert.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String input = SimpleEditText.getText().toString();
                        userAccount.setFirstName(input);
                    }
                });
                break;
            case R.id.bt_EditName:
                title = "Nouveau Nom :";
                hint = userAccount.getName();
                alert.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String input = SimpleEditText.getText().toString();
                        userAccount.setName(input);
                    }
                });
                break;
            case R.id.bt_EditPassword:
                title = "Nouveau Mot De Passe :";
                SimpleEditText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                hint = userAccount.getPassword();
                alert.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String input = SimpleEditText.getText().toString();
                        userAccount.setPassword(input);
                    }
                });
                break;
            default:
                break;
        }
        SimpleEditText.setHint(hint);
        alert.setTitle(title);

        alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // what ever you want to do with No option.
            }
        });

        alert.show();

        UserAccountHelper userAccountHelper = new UserAccountHelper(getActivity());
        userAccountHelper.StoreUserAccount(userAccount);
    }
}
