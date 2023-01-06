package com.Netanel.glutenfreerestaurant.Activites.ui.food;

import androidx.fragment.app.Fragment;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.Netanel.glutenfreerestaurant.Category;
import com.Netanel.glutenfreerestaurant.Food;
import com.Netanel.glutenfreerestaurant.databinding.FragmentFoodListBinding;

import java.util.ArrayList;


public class FoodFragment extends Fragment {
    private FragmentFoodListBinding binding;
    private RecyclerView foodRV;
    private FoodViewModel foodViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        foodViewModel =
                new ViewModelProvider(this).get(FoodViewModel.class);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFoodListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        foodRV = binding.listFood;
        String categoryId = getArguments().getString("CategoryId");
        Log.d("Category Selected: ", ""+categoryId);
        foodViewModel.getFoodItems().observe(getViewLifecycleOwner(),observer);
        final RecyclerView recyclerView = binding.listFood;
        return root;
    }

    Observer<ArrayList<Food>> observer = new Observer<ArrayList<Food>>(){

        @Override
        public void onChanged(ArrayList<Food> foodItems) {
            mAdapter.updateFoodItems(foodItems);
        }
    };
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
