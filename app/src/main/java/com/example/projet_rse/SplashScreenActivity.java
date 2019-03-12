package com.example.projet_rse;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //Set Status bar color
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));

        TextView textView = findViewById(R.id.tv_Label);

        SpannableString spannableString = new SpannableString(getResources().getString(R.string.app_name));
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorLogo)),0,1,0);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorLogo)),6,7,0);

        textView.setText(spannableString);

        //en msecondes
        int SPLASH_DUREE_OUT = 5000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_DUREE_OUT);
    }
}
