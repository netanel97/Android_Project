package com.Netanel.glutenfreerestaurant.Activites.ui.cart;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Netanel.glutenfreerestaurant.Activites.HomeActivity;
import com.Netanel.glutenfreerestaurant.Activites.MainActivity;
import com.Netanel.glutenfreerestaurant.Activites.PaymentSplash;
import com.Netanel.glutenfreerestaurant.Activites.SplashActivity;
import com.Netanel.glutenfreerestaurant.Adapter.CartRecyclerViewAdapter;
import com.Netanel.glutenfreerestaurant.Model.Order;
import com.Netanel.glutenfreerestaurant.Model.UserDB;
import com.Netanel.glutenfreerestaurant.MyUtils.Constants;
import com.Netanel.glutenfreerestaurant.MyUtils.FireBaseOperations;
import com.Netanel.glutenfreerestaurant.MyUtils.MySignal;
import com.Netanel.glutenfreerestaurant.R;
import com.Netanel.glutenfreerestaurant.databinding.FragmentCartBinding;
import com.chinalwb.slidetoconfirmlib.ISlideListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


public class CartFragment extends Fragment {

    private FragmentCartBinding binding;
    private CartRecyclerViewAdapter mAdapter;
    private RecyclerView cartRV;


    private DatabaseReference reference = FireBaseOperations.getInstance().getDatabaseReference(Constants.USER_DB);

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        cartRV = binding.cartlist;
        mAdapter = new CartRecyclerViewAdapter(getContext());
        mAdapter.updateCart(UserDB.getInstance().getCurrentOrder().getAllFoods());
        checkEmptyCart();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        cartRV.setLayoutManager(linearLayoutManager);
        cartRV.setAdapter(mAdapter);

        /**
         * When a customer press on delete item it will be removed from the cart
         */
        mAdapter.setFoodClickListener((food, position) -> {
            UserDB.getInstance().getCurrentOrder().getAllFoods().remove(position);
            mAdapter.updateCart(UserDB.getInstance().getCurrentOrder().getAllFoods());
            checkEmptyCart();
        });
        if(UserDB.getInstance().getCurrentOrder().getAllFoods().size() > 0)
            binding.cartBTNCheckOut.setOnClickListener(view -> checkOut());

        return root;
    }

    /**
     * Adding the current order to the Arraylist<Order>
     */
    private void checkOut() {
        UserDB.getInstance().getCurrentOrder().setTimeStamp(System.currentTimeMillis());
        UserDB.getInstance().addOrder(UserDB.getInstance().getCurrentOrder());
        reference = reference.child(MainActivity.currentUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                reference.setValue(UserDB.getInstance());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        clearCheckOut();


    }

    private void clearCheckOut() {
        UserDB.getInstance().setCurrentOrder(new Order());
        mAdapter.updateCart(UserDB.getInstance().getCurrentOrder().getAllFoods());
        checkEmptyCart();
        changeActivity();
    }

    private void changeActivity() {
        Intent intent = new Intent(getContext(), PaymentSplash.class);
        startActivity(intent);
        getActivity().finish();

    }

    private void checkEmptyCart() {

        if (UserDB.getInstance().getCurrentOrder().getAllFoods().size() > 0) {
            binding.cartBTNCheckOut.setClickable(true);
            binding.cartLBLTotalPrice.setVisibility(View.VISIBLE);
            binding.cartLBLTotalPrice.setText("Total price:" + UserDB.getInstance().getCurrentOrder().totalPrice() + "$");
        } else {
            binding.cartLBLTotalPrice.setText(Constants.EMPTY_CART);

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}