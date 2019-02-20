package com.example.projet_rse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class RandomDateGenerator {


        public Date Generate() {

            SimpleDateFormat dfDateTime  = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault());
            int year = randBetween(2018, 2018);// Here you can set Range of years you need
            int month = randBetween(0, 11);



            GregorianCalendar gc = new GregorianCalendar(year, month, 1);
            int day = randBetween(1, gc.getActualMaximum(gc.DAY_OF_MONTH));

            gc.set(year, month, day,0,0,0);

            return gc.getTime();
        }


        public int randBetween(int start, int end) {
            return start + (int)Math.round(Math.random() * (end - start));
        }
}
