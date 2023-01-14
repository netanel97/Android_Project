package com.Netanel.glutenfreerestaurant.Activites.ui.orders;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.Netanel.glutenfreerestaurant.Model.Order;
import com.Netanel.glutenfreerestaurant.Model.UserDB;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TimeZone;

public class OrderViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Order>> mOrders;


    public OrderViewModel() {
        mOrders = new MutableLiveData<>();
    }
    public OrderViewModel(ArrayList<Order> allOrders){
        this();
        mOrders = new MutableLiveData<>();
        changeStatusOrder(allOrders);


    }

    /**
     * check for each order,the current time that was checked, and put Active/InActive value
     */
    private void changeStatusOrder(ArrayList<Order> allOrders) {
        LocalDateTime time;
        for (int i = 0; i < allOrders.size(); i++) {
            time = LocalDateTime.ofInstant(Instant.ofEpochMilli(UserDB.getInstance().getAllOrders().get(i).getTimeStamp()), TimeZone.getDefault().toZoneId());
            UserDB.getInstance().getAllOrders().get(i).setActive(LocalDateTime.now().isBefore(time.plusMinutes(2)));
        }
        mOrders.setValue(allOrders);

    }
    public LiveData<ArrayList<Order>> getOrders(){
        return mOrders;
    }


}