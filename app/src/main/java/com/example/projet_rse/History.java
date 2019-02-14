package com.example.projet_rse;

import java.text.DecimalFormat;

public class History {

    private static double UnitAmount = 0.3;

    private String Date;
    private String PackagesNumber;
    private String Amount;
    private String Address;

    public History(String date, String packagesNumber, String address) {
        Date = date;
        PackagesNumber = packagesNumber;
        Address = address;
        Amount = CalculateAmount(packagesNumber);
    }

    private String CalculateAmount(String packagesNumber){
        int quantity = Integer.parseInt(packagesNumber);
        double sum = quantity*UnitAmount;
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return String.valueOf(twoDForm.format(sum));
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getPackagesNumber() {
        return PackagesNumber;
    }

    public void setPackagesNumber(String packagesNumber) {
        PackagesNumber = packagesNumber;
    }

    public String getAmount() {
        return Amount;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int GetAddressLines()
    {
        int count = 0;

        for(int i=0; i < Address.length(); i++)
        {    if(Address.charAt(i) == '\n')
            count++;
        }

        return count+1;
    }

}


