package com.suwat.myapplication;

public class Car {
    String CarBrand;
    String CarID;

    public Car() {
    }

    public Car(String carBrand, String carID) {
        CarBrand = carBrand;
        CarID = carID;
    }

    public String getCarBrand() {
        return CarBrand;
    }

    public void setCarBrand(String carBrand) {
        CarBrand = carBrand;
    }

    public String getCarID() {
        return CarID;
    }

    public void setCarID(String carID) {
        CarID = carID;
    }
}
