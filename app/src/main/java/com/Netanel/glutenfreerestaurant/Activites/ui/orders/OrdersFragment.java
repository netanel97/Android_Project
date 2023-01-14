package com.Netanel.glutenfreerestaurant.Activites.ui.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.Netanel.glutenfreerestaurant.Adapter.OrderRecyclerViewAdapter;
import com.Netanel.glutenfreerestaurant.Model.UserDB;
import com.Netanel.glutenfreerestaurant.databinding.FragmentOrdersBinding;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class OrdersFragment extends Fragment {

    private FragmentOrdersBinding binding;
    private OrderRecyclerViewAdapter mAdapter;
    private RecyclerView orderRV;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentOrdersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        orderRV = binding.orderList;
        mAdapter = new OrderRecyclerViewAdapter(getContext());
        checkTimeTrucking();
        mAdapter.updateOrderTrucking(UserDB.getInstance().getAllOrders());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        orderRV.setLayoutManager(linearLayoutManager);
        orderRV.setAdapter(mAdapter);
        return root;
    }

    private void checkTimeTrucking() {
        LocalDateTime time;
        for (int i = 0; i < UserDB.getInstance().getAllOrders().size(); i++) {
            time = LocalDateTime.ofInstant(Instant.ofEpochMilli(UserDB.getInstance().getAllOrders().get(i).getTimeStamp()), TimeZone.getDefault().toZoneId());
            UserDB.getInstance().getAllOrders().get(i).setActive(LocalDateTime.now().isBefore(time.plusMinutes(15)));

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}