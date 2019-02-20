package com.example.projet_rse;

import java.text.DecimalFormat;
import java.util.Date;

public class History {

    private java.util.Date Date;
    private String PackagesNumber;
    private String Amount;
    private String Address;

    public History(Date date, String packagesNumber, String address) {
        Date = date;
        PackagesNumber = packagesNumber;
        Address = address;
        Amount = CalculateAmount(packagesNumber);
    }

    private String CalculateAmount(String packagesNumber){
        int quantity = Integer.parseInt(packagesNumber);
        double unitAmount = 0.3;
        double sum = quantity* unitAmount;
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return String.valueOf(twoDForm.format(sum)+"€");
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date date) {
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


