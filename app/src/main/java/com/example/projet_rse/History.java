package com.example.projet_rse;

public class History {
    private String Date;
    private String PackagesNumber;
    private PurchasingVoucher PurchasingVoucher;

    public History(String date, String packagesNumber, com.example.projet_rse.PurchasingVoucher purchasingVoucher) {
        Date = date;
        PackagesNumber = packagesNumber;
        PurchasingVoucher = purchasingVoucher;
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

    public com.example.projet_rse.PurchasingVoucher getPurchasingVoucher() {
        return PurchasingVoucher;
    }

    public void setPurchasingVoucher(com.example.projet_rse.PurchasingVoucher purchasingVoucher) {
        PurchasingVoucher = purchasingVoucher;
    }
}


