package com.Netanel.glutenfreerestaurant.Activites.ui.orders;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.Netanel.glutenfreerestaurant.Activites.MainActivity;
import com.Netanel.glutenfreerestaurant.Model.Order;
import com.Netanel.glutenfreerestaurant.Model.UserDB;
import com.Netanel.glutenfreerestaurant.MyUtils.Constants;
import com.Netanel.glutenfreerestaurant.MyUtils.FireBaseOperations;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TimeZone;

public class OrderViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Order>> mOrders;
    private DatabaseReference reference = FireBaseOperations.getInstance().getDatabaseReference(Constants.USER_DB);


    public OrderViewModel() {
        mOrders = new MutableLiveData<>();
    }
    public OrderViewModel(ArrayList<Order> allOrders){
        this();
        mOrders = new MutableLiveData<>();
        changeStatusOrder(allOrders);
        updateStatusOrderDB();


    }

    /**
     * This Function will check if the the order from allOrders is InActive,if yes it will update the FB
     */

    private void updateStatusOrderDB() {
        reference = reference.child(MainActivity.currentUser.getUid()).child(Constants.ALL_ORDERS);;
        for (int i = 0; i < UserDB.getInstance().getAllOrders().size() ; i++) {
            if(!UserDB.getInstance().getAllOrders().get(i).isActive()){
                String currentChild = String.valueOf(i);
                int currentOrder = i;
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        snapshot.getRef().child(currentChild).child(Constants.ACTIVE).setValue(UserDB.getInstance().getAllOrders().get(currentOrder).isActive());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }
    }

    /**
     * check for each order,the current time that was checked, and put Active/InActive value
     */
    private void changeStatusOrder(ArrayList<Order> allOrders) {
        LocalDateTime time;
        for (int i = 0; i < allOrders.size(); i++) {
            time = LocalDateTime.ofInstant(Instant.ofEpochMilli(UserDB.getInstance().getAllOrders().get(i).getTimeStamp()), TimeZone.getDefault().toZoneId());
            UserDB.getInstance().getAllOrders().get(i).setActive(LocalDateTime.now().isBefore(time.plusMinutes(Constants.DELIVERY_TIME)));
        }
        mOrders.setValue(allOrders);

    }
    public LiveData<ArrayList<Order>> getOrders(){
        return mOrders;
    }


}