package com.suwat.myapplication;

public class Data {
    String FirstName;
    String LastName;
    String Identify;
    String PhoneNumber;
    String Password;

    public Data() {
    }

    public Data(String firstName, String lastName, String identify, String phoneNumber, String password) {
        FirstName = firstName;
        LastName = lastName;
        Identify = identify;
        PhoneNumber = phoneNumber;
        Password = password;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getIdentify() {
        return Identify;
    }

    public void setIdentify(String identify) {
        Identify = identify;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}