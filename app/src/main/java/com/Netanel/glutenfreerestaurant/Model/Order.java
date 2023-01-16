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


    public Order() {
        this.allFoods = new ArrayList<Food>();
        setLocations();

    }


    protected Order(Parcel in) {
        allFoods = in.createTypedArrayList(Food.CREATOR);
        timeStamp = in.readLong();
        isActive = in.readByte() != 0;
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
    private ArrayList<LatLng> setLocations() {
        ArrayList<LatLng> allLocations = new ArrayList<>();
        allLocations.add(new LatLng(32.146259, 34.837870));
        allLocations.add(new LatLng(32.145907, 34.838393));
        allLocations.add(new LatLng(32.143211, 34.837374));
        allLocations.add(new LatLng(32.141316, 34.836580));
        allLocations.add(new LatLng(32.136840, 34.834396));
        allLocations.add(new LatLng(32.129764, 34.829910));
        allLocations.add(new LatLng(32.122819, 34.824346));
        allLocations.add(new LatLng(32.118647, 34.818495));
        allLocations.add(new LatLng(32.114032, 34.816993));
        return allLocations;

    }


    public ArrayList<LatLng> getAllLocations() {
        return setLocations();
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

    public String getOrderTime(){
        LocalDateTime time = getTimestampAsLDT();
        StringBuffer sb = new StringBuffer();
        sb.append("Date of Order: ").append(time.getDayOfMonth()).append(".").append(time.getDayOfMonth()).append(".").append(time.getYear());
        return sb.toString();
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
    }
}
