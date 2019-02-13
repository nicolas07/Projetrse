package com.example.projet_rse;

public class PurchasingVoucher {

    private String ExpirationDate;
    private int Amount;

    public PurchasingVoucher(String expirationDate, int amount) {
        ExpirationDate = expirationDate;
        Amount = amount;
    }

    public String getExpirationDate() {
        return ExpirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        ExpirationDate = expirationDate;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }
}
