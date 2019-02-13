package com.example.projet_rse;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserAccountFragment extends Fragment {

    private TextView Name;
    private Button Edit;

    // TODO : Ajouter les TextView + bouton par champ
    // TODO : Imagebutton Edit

    public static UserAccountFragment newInstance() {
        return (new UserAccountFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_useraccount, container, false);
        Name = (TextView) view.findViewById(R.id.tv_Name);
        Edit = (Button) view.findViewById(R.id.button_edit);
        Name.setText("totot");
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                final EditText edittext = new EditText(getActivity());
                alert.setMessage("Enter Your Message");
                alert.setTitle("Enter Your Title");

                alert.setView(edittext);
                edittext.setHint(Name.getText().toString());
                alert.setPositiveButton("Yes Option", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        String YouEditTextValue = edittext.getText().toString();
                    }
                });

                alert.setNegativeButton("No Option", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // what ever you want to do with No option.
                    }
                });

                alert.show();
            }
        });

        return view;
    }
}
