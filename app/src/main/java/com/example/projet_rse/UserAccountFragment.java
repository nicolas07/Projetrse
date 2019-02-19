package com.example.projet_rse;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class UserAccountFragment extends Fragment implements View.OnClickListener {

    private TextView TextViewName;
    private TextView TextViewFirstName;
    private TextView TextViewAddress;
    private TextView TextViewEmail;
    private TextView TextViewPassword;

    private ImageButton ImageButtonEditName;
    private ImageButton ImageButtonEditFirstName;
    private ImageButton ImageButtonEditAddress;
    private ImageButton ImageButtonEditEmail;
    private ImageButton ImageButtonEditPassword;

    private UserAccount userAccount;
    private StorageHelper storageHelper;

    public static UserAccountFragment newInstance() {
        return (new UserAccountFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ((MainActivity) getActivity()).setActionBarTitle("Profil");

        storageHelper = new StorageHelper(getContext());

        userAccount = storageHelper.GetUserAccount();


        View view = inflater.inflate(R.layout.fragment_useraccount, container, false);
        TextViewName = (TextView) view.findViewById(R.id.tv_Name);
        TextViewFirstName = (TextView) view.findViewById(R.id.tv_FirstName);
        TextViewAddress = (TextView) view.findViewById(R.id.tv_Address);
        TextViewEmail = (TextView) view.findViewById(R.id.tv_EMail);
        TextViewPassword = (TextView) view.findViewById(R.id.tv_Password);

        TextViewName.setText(userAccount.getName());
        TextViewFirstName.setText(userAccount.getFirstName());
        TextViewAddress.setText(userAccount.getAddress());
        TextViewAddress.setLines(userAccount.GetAddressLines());
        TextViewEmail.setText(userAccount.getEmail());
        TextViewPassword.setText(userAccount.getPassword());

        ImageButtonEditName = (ImageButton) view.findViewById(R.id.ib_EditName);
        ImageButtonEditName.setOnClickListener(this);
        ImageButtonEditFirstName = (ImageButton) view.findViewById(R.id.ib_EditFirstName);
        ImageButtonEditFirstName.setOnClickListener(this);
        ImageButtonEditAddress = (ImageButton) view.findViewById(R.id.ib_EditAddress);
        ImageButtonEditAddress.setOnClickListener(this);
        ImageButtonEditEmail= (ImageButton) view.findViewById(R.id.ib_EditEMail);
        ImageButtonEditEmail.setOnClickListener(this);
        ImageButtonEditPassword = (ImageButton) view.findViewById(R.id.ib_EditPassword);
        ImageButtonEditPassword.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {

        int i = v.getId();

        String message = "";
        String title = "";
        String hint = "";

        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        View vEditText = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_edittext,null);

        final EditText SimpleEditText = (EditText) vEditText.findViewById(R.id.et_EditDialog);

        alert.setView(vEditText);
        switch (i){
            case R.id.ib_EditAddress:
                title = "Nouvelle Adresse : ";
                hint = userAccount.getAddress();
                alert.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    String input = SimpleEditText.getText().toString();
                    userAccount.setAddress(input);
                    }
                });
                break;
            case R.id.ib_EditEMail:
                title = "Nouvel Email :";
                hint = userAccount.getEmail();
                alert.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String input = SimpleEditText.getText().toString();
                        userAccount.setEmail(input);
                    }
                });
                break;
            case R.id.ib_EditFirstName:
                title = "Nouveau Prénom :";
                hint = userAccount.getFirstName();
                alert.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String input = SimpleEditText.getText().toString();
                        userAccount.setFirstName(input);
                    }
                });
                break;
            case R.id.ib_EditName:
                title = "Nouveau Nom :";
                hint = userAccount.getName();
                alert.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String input = SimpleEditText.getText().toString();
                        userAccount.setName(input);
                    }
                });
                break;
            case R.id.ib_EditPassword:
                title = "Nouveau Mot De Passe :";
                SimpleEditText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                SimpleEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
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
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(getResources().getColor(R.color.colorAccent));
        SpannableStringBuilder ssBuilder = new SpannableStringBuilder(title);
        ssBuilder.setSpan(
                foregroundColorSpan,
                0,
                title.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );
        alert.setTitle(ssBuilder);

        alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // what ever you want to do with No option.
            }
        });

        alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                storageHelper.StoreUserAccount(userAccount);

                TextViewName.setText(userAccount.getName());
                TextViewFirstName.setText(userAccount.getFirstName());
                TextViewAddress.setText(userAccount.getAddress());
                TextViewAddress.setLines(userAccount.GetAddressLines());
                TextViewEmail.setText(userAccount.getEmail());
                TextViewPassword.setText(userAccount.getPassword());
            }
        });
        AlertDialog dial = alert.create();
        dial.show();

    }
}
