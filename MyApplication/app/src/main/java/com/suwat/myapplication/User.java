package com.suwat.myapplication;

public class User {
    String Email;
    String Fname;
    String Lname;
    String ID;
    String Phone;
    String Password;
    public User() {
    }

    public User(String email, String fname, String lname, String ID, String phone, String password) {
        Email = email;
        Fname = fname;
        Lname = lname;
        this.ID = ID;
        Phone = phone;
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
