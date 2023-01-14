package com.Netanel.glutenfreerestaurant.Activites.ui.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.Netanel.glutenfreerestaurant.Adapter.OrderRecyclerViewAdapter;
import com.Netanel.glutenfreerestaurant.Model.Food;
import com.Netanel.glutenfreerestaurant.Model.Order;
import com.Netanel.glutenfreerestaurant.Model.UserDB;
import com.Netanel.glutenfreerestaurant.databinding.FragmentOrdersBinding;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TimeZone;

public class OrdersFragment extends Fragment {

    private FragmentOrdersBinding binding;
    private OrderRecyclerViewAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentOrdersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        RecyclerView orderRV = binding.orderList;
        OrderViewModel orderViewModel = new OrderViewModel(UserDB.getInstance().getAllOrders());

        orderViewModel.getOrders().observe(getViewLifecycleOwner(),observer);
        mAdapter = new OrderRecyclerViewAdapter(getContext());
        mAdapter.updateOrderTrucking(UserDB.getInstance().getAllOrders());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        orderRV.setLayoutManager(linearLayoutManager);
        orderRV.setAdapter(mAdapter);
        return root;

    }

    Observer<ArrayList<Order>> observer = new Observer<ArrayList<Order>>(){


        @Override
        public void onChanged(ArrayList<Order> orders) {
            mAdapter.updateOrderTrucking(orders);
        }};

        @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}