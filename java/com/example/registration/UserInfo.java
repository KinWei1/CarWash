package com.example.registration;

public class UserInfo {

    String email;
    String phoneNumber;
    String password;

    public UserInfo() {
    }

    public UserInfo(String email, String phoneNumber, String password) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }
}
