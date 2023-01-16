package com.Netanel.glutenfreerestaurant.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TimeZone;

public class Order implements Parcelable {
    private ArrayList<Food> allFoods;
    private long timeStamp;
    private boolean isActive;
    private ArrayList<LatLng> allLocations;


    public Order() {
        this.allFoods = new ArrayList<Food>();
        this.allLocations = new ArrayList<>();
        setLocations();
    }


    protected Order(Parcel in) {
        allFoods = in.createTypedArrayList(Food.CREATOR);
        timeStamp = in.readLong();
        isActive = in.readByte() != 0;
        allLocations = in.createTypedArrayList(LatLng.CREATOR);
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

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
    private void setLocations() {
        this.allLocations.add(new LatLng(32.146259, 34.837870));
        this.allLocations.add(new LatLng(32.145907, 34.838393));
        this.allLocations.add(new LatLng(32.143211, 34.837374));
        this.allLocations.add(new LatLng(32.141316, 34.836580));
        this.allLocations.add(new LatLng(32.136840, 34.834396));
        this.allLocations.add(new LatLng(32.129764, 34.829910));
        this.allLocations.add(new LatLng(32.122819, 34.824346));
        this.allLocations.add(new LatLng(32.118647, 34.818495));
        this.allLocations.add(new LatLng(32.114032, 34.816993));
    }


    public ArrayList<LatLng> getAllLocations() {
        return allLocations;
    }

    public long getTimeStamp() {
        return timeStamp;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(allFoods);
        parcel.writeLong(timeStamp);
        parcel.writeByte((byte) (isActive ? 1 : 0));
        parcel.writeTypedList(allLocations);
    }
}
