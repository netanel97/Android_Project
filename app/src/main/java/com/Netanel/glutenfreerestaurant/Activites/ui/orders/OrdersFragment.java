package com.Netanel.glutenfreerestaurant.Activites.ui.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.Netanel.glutenfreerestaurant.Adapter.OrderRyclerViewAdapter;
import com.Netanel.glutenfreerestaurant.databinding.FragmentOrdersBinding;

public class OrdersFragment extends Fragment {

    private FragmentOrdersBinding binding;
    private OrderRyclerViewAdapter mAdapter;
    private RecyclerView orderRV;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentOrdersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        orderRV = binding.orderList;

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}