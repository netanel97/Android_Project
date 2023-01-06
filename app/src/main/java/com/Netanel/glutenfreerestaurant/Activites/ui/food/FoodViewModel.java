package com.Netanel.glutenfreerestaurant.Activites.ui.food;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.Netanel.glutenfreerestaurant.Category;
import com.Netanel.glutenfreerestaurant.Food;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class FoodViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<Food>> mFoods;
    private final String food = "Food";
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference(food);

    public FoodViewModel() {
        mFoods = new MutableLiveData<>();
    }

    public FoodViewModel(String categoryId) {
        this();

        ArrayList<Food> foods = new ArrayList<>();
        databaseReference.child(categoryId).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                foods.add(snapshot.getValue(Food.class));
                mFoods.setValue(foods);
                Log.d("Foods in pasta",foods.toString());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public LiveData<ArrayList<Food>> getFoodItems() {
        return mFoods;
    }



}
