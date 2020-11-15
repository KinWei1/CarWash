package com.example.registration;

public class ShopInfo {

    String email;
    String shopName;
    String shopAddress;
    String phoneNumber;
    String password;
    String shopID;

    public ShopInfo(String email, String password, String phoneNumber, String shopName, String shopAddress) {
        this.email = email;
        this.shopName = shopName;
        this.shopAddress = shopAddress;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public String getShopName() {
        return shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getShopID() { return shopID; }
}
