package com.example.amanmehta.rentals;

/**
 * Created by amanmehta on 11/01/18.
 */

public class Rooms {
    private String address,rate,Preference,noOfRooms,water,electricityRate,extra,image1,image2,image3,image4;

    public Rooms(String address, String rate, String preference, String noOfRooms, String water, String electricityRate, String extra, String image1, String image2, String image3, String image4) {
        this.address = address;
        this.rate = rate;
        Preference = preference;
        this.noOfRooms = noOfRooms;
        this.water = water;
        this.electricityRate = electricityRate;
        this.extra = extra;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
    }

    public Rooms() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getPreference() {
        return Preference;
    }

    public void setPreference(String preference) {
        Preference = preference;
    }

    public String getNoOfRooms() {
        return noOfRooms;
    }

    public void setNoOfRooms(String noOfRooms) {
        this.noOfRooms = noOfRooms;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getElectricityRate() {
        return electricityRate;
    }

    public void setElectricityRate(String electricityRate) {
        this.electricityRate = electricityRate;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getImage4() {
        return image4;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }
}
