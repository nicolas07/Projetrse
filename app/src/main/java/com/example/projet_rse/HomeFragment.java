package com.example.projet_rse;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        ((MainActivity) getActivity()).setActionBarTitle("Accueil");
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        String text = " - Pour retourner des colis, cliquez ici";
        SpannableString ss = new SpannableString(text);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                ((MainActivity)getActivity()).showFragment(FRAGMENT_RETURNPACKAGE);
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
        ss.setSpan(clickableSpan, text.indexOf(","), text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView textView = view.findViewById(R.id.tv_returnPackage);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setHighlightColor(Color.TRANSPARENT);

        String text2 = " - Pour consulter votre historique, cliquez ici";
        SpannableString ss2 = new SpannableString(text2);
        ClickableSpan clickableSpan2 = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                ((MainActivity)getActivity()).showFragment(FRAGMENT_HISTORY);
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
        ss2.setSpan(clickableSpan2, text2.indexOf(","), text2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView textView2 = view.findViewById(R.id.tv_History);
        textView2.setText(ss2);
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
        textView2.setHighlightColor(Color.TRANSPARENT);

        String text3 = " - Pour consulter votre profil, cliquez ici";
        SpannableString ss3 = new SpannableString(text3);
        ClickableSpan clickableSpan3 = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                ((MainActivity)getActivity()).showFragment(FRAGMENT_USERACCOUNT);
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
        ss3.setSpan(clickableSpan3, text3.indexOf(","), text3.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView textView3 = view.findViewById(R.id.tv_Profil);
        textView3.setText(ss3);
        textView3.setMovementMethod(LinkMovementMethod.getInstance());
        textView3.setHighlightColor(Color.TRANSPARENT);


        return view;
    }
}
