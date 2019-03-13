package com.example.projet_rse;

public class UserAccount {

    private String Name;
//    private String FirstName;
    private String Address;
    private String Password;
    private String Email;
    private String SumAmount;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

//    public String getFirstName() {
//        return FirstName;
//    }
//
//    public void setFirstName(String firstName) {
//        FirstName = firstName;
//    }

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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSumAmount() {
        return SumAmount;
    }

    public UserAccount(String name, String address, String password, String email, String sumAmount) {
        Name = name;
        Address = address;
        Password = password;
        Email = email;
        SumAmount = sumAmount;
    }

    public int GetAddressLines() {
        int count = 0;

        for (int i = 0; i < Address.length(); i++) {
            if (Address.charAt(i) == '\n')
                count++;
        }

        return count + 1;
    }
}
