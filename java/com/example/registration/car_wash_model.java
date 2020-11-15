package com.example.registration;

public class car_wash_model {

    private String CarWashName;
    private String PhoneNumber;
    private String ShopAddress;
    private String Description;
    private String Price;

    private car_wash_model() {}

    private car_wash_model(String CarWashName, String PhoneNumber, String ShopAddress, String Description,String Price) {
        this.CarWashName = CarWashName;
        this.PhoneNumber = PhoneNumber;
        this.ShopAddress = ShopAddress;
        this.Description = Description;
        this.Price = Price;
    }

    public String getCarWashName() {
        return CarWashName;
    }

    public void setCarWashName(String carWashName) {
        this.CarWashName = carWashName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.PhoneNumber = phoneNumber;
    }

    public String getShopAddress() {
        return ShopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.ShopAddress = shopAddress;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        this.Price = price;
    }



}
