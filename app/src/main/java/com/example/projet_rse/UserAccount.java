package com.example.projet_rse;

public class UserAccount {

    private String Name;
    private String ForName;
    private String Address;
    private String Password;

    public UserAccount(String name, String forName, String address, String password) {
        Name = name;
        ForName = forName;
        Address = address;
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getForName() {
        return ForName;
    }

    public void setForName(String forName) {
        ForName = forName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
