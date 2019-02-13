package com.example.projet_rse;

public class UserAccount {

    private String Name;
    private String FirstName;
    private String Address;
    private String Password;
    private String Email;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public UserAccount(String name, String firstName, String address, String password, String email) {
        Name = name;
        FirstName = firstName;
        Address = address;
        Password = password;
        Email = email;
    }
}
