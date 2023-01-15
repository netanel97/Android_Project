package com.Netanel.glutenfreerestaurant.Model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TimeZone;

public class Order {
    private ArrayList<Food> allFoods;
    private double lat = 32.113558;
    private double lon = 34.817590;
    private long timeStamp;
    private boolean isActive;


    public Order() {
        allFoods = new ArrayList<Food>();
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isActive() {
        return isActive;
    }

    /**
    adding specific food to the list
     **/
    public void addFood(Food food){
        allFoods.add(food);
    }



    public ArrayList<Food> getAllFoods() {
        return allFoods;
    }

    public void setTimeStamp(long timeStamp) {
        this.isActive = true; //active order
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

    public String totalPrice(){
        int totalPrice = 0;
        for (int i = 0; i < allFoods.size(); i++) {
            totalPrice += Integer.parseInt(allFoods.get(i).getPrice());

        }
        return Integer.toString(totalPrice);
    }

    public LocalDateTime getTimestampAsLDT(){
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(this.timeStamp), TimeZone.getDefault().toZoneId());
    }
}
