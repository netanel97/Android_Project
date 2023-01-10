package com.Netanel.glutenfreerestaurant.Model;

import java.util.ArrayList;

public class Order {
    private String orderNumber;
    private ArrayList<Food> allfoods;
    private double lat = 33.4;
    private double lon = 32.4;
    private long timeStamp;

    public Order() {
        allfoods = new ArrayList<Food>();
    }

    public String getOrderNumber() {
        return orderNumber;
    }
    /**
    adding specific food to the list
     **/
    public void addFood(Food food){
        allfoods.add(food);
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public ArrayList<Food> getAllfoods() {
        return allfoods;
    }

    public void setAllfoods(ArrayList<Food> allfoods) {
        this.allfoods = allfoods;
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
}
