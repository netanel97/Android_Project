package com.Netanel.glutenfreerestaurant.Model;

import java.util.ArrayList;

public class Order {
//    private String orderNumber;
    private ArrayList<Food> allFoods;
    private double lat = 33.4;
    private double lon = 32.4;
    private long timeStamp;
    // TODO: 1/13/2023 add status boolean true-->active false -->inactive


    public Order() {
        allFoods = new ArrayList<Food>();
    }

//    public String getOrderNumber() {
//        return orderNumber;
//    }
    /**
    adding specific food to the list
     **/
    public void addFood(Food food){
        allFoods.add(food);
    }

//    public void setOrderNumber(String orderNumber) {
//        this.orderNumber = orderNumber;
//    }

    public ArrayList<Food> getAllFoods() {
        return allFoods;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
    public long getTimeStamp() {
        return timeStamp;
    }

    public void setAllFoods(ArrayList<Food> allFoods) {
        this.allFoods = allFoods;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "Order{" +
                ", allFoods=" + allFoods +
                ", lat=" + lat +
                ", lon=" + lon +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
