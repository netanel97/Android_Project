package com.Netanel.glutenfreerestaurant.Activites.ui.orders;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.Netanel.glutenfreerestaurant.Adapter.OrderRecyclerViewAdapter;
import com.Netanel.glutenfreerestaurant.Model.Order;
import com.Netanel.glutenfreerestaurant.Model.UserDB;
import com.Netanel.glutenfreerestaurant.MyUtils.Constants;
import com.Netanel.glutenfreerestaurant.R;
import com.Netanel.glutenfreerestaurant.databinding.FragmentOrdersBinding;

import java.util.ArrayList;

public class OrdersFragment extends Fragment {

    private FragmentOrdersBinding binding;
    private OrderRecyclerViewAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentOrdersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        RecyclerView orderRV = binding.orderList;
        requestLocationPermission();
        OrderViewModel orderViewModel = new OrderViewModel(UserDB.getInstance().getAllOrders());
        orderViewModel.getOrders().observe(getViewLifecycleOwner(),observer);
        mAdapter = new OrderRecyclerViewAdapter(getContext());
        mAdapter.updateOrderTrucking(UserDB.getInstance().getAllOrders());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        orderRV.setLayoutManager(linearLayoutManager);
        orderRV.setAdapter(mAdapter);
        mAdapter.setOrderClickListener(new OrderRecyclerViewAdapter.OrderClickListener() {
            @Override
            public void changeScreen(int position) {
                Bundle args = new Bundle();
                args.putParcelableArrayList(Constants.ALL_LOCATIONS,UserDB.getInstance().getAllOrders().get(position).getAllLocations());
                final NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_home);
                navController.navigate(R.id.nav_truckOrder,args);

            }
        });
        return root;

    }

    private void requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) getContext(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 101);

        }
    }

    /**
     * The observer will listen all the time to changes in OrderViewModel and when will be a change,
     * it will update the Adapter.
     */
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