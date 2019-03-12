package com.example.projet_rse;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.example.projet_rse.MainActivity.FRAGMENT_HISTORY;
import static com.example.projet_rse.MainActivity.FRAGMENT_RETURNPACKAGE;
import static com.example.projet_rse.MainActivity.FRAGMENT_USERACCOUNT;

public class HomeFragment extends Fragment {

    public static HomeFragment newInstance() {
        return (new HomeFragment());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ((MainActivity) getActivity()).setActionBarTitle("Accueil");
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        LinearLayout LlReturnPackage = view.findViewById(R.id.ll_ReturnPackage);
        LinearLayout LlHistory = view.findViewById(R.id.ll_History);
        LinearLayout LlUserAccount = view.findViewById(R.id.ll_UserAccount);

        LlReturnPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).showFragment(FRAGMENT_RETURNPACKAGE);
            }
        });

        LlHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).showFragment(FRAGMENT_HISTORY);
            }
        });

        LlUserAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).showFragment(FRAGMENT_USERACCOUNT);
            }
        });

        TextView textView = view.findViewById(R.id.tv_Title);

        SpannableString spannableString = new SpannableString("Bienvenue sur l'application " + getResources().getString(R.string.app_name));
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorLogo)),28,29,0);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorLogo)),34,35,0);

        textView.setText(spannableString);

        return view;
    }
}
