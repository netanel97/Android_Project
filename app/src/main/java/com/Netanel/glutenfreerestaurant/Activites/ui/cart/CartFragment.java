package com.Netanel.glutenfreerestaurant.Activites.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.Netanel.glutenfreerestaurant.Adapter.CartRycyclerViewAdapter;
import com.Netanel.glutenfreerestaurant.Adapter.FoodRycyclerViewAdapter;
import com.Netanel.glutenfreerestaurant.databinding.FragmentCartBinding;

public class CartFragment extends Fragment {

    private FragmentCartBinding binding;
    private CartRycyclerViewAdapter mAdapter;
    private RecyclerView cartRV;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}