package com.Netanel.glutenfreerestaurant.Activites.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Netanel.glutenfreerestaurant.Activites.MainActivity;
import com.Netanel.glutenfreerestaurant.Adapter.CartRycyclerViewAdapter;
import com.Netanel.glutenfreerestaurant.Model.Order;
import com.Netanel.glutenfreerestaurant.MyUtils.Constants;
import com.Netanel.glutenfreerestaurant.MyUtils.FireBaseOperations;
import com.Netanel.glutenfreerestaurant.databinding.FragmentCartBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class CartFragment extends Fragment {

    private FragmentCartBinding binding;
    private CartRycyclerViewAdapter mAdapter;
    private RecyclerView cartRV;
    private DatabaseReference reference = FireBaseOperations.getInstance().getDatabaseReference(Constants.USER_DB);

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        cartRV = binding.cartlist;
        mAdapter = new CartRycyclerViewAdapter(getContext());
        mAdapter.updateCart(MainActivity.userDB.getCurrentOrder().getAllFoods());
        checkEmptyCart();
        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        cartRV.setLayoutManager(linearLayoutManager);
        cartRV.setAdapter(mAdapter);
        /**
         * When a customer press on delete item it will be removed from the cart
         */
        mAdapter.setFoodClickListener((food, position) -> {
            MainActivity.userDB.getCurrentOrder().getAllFoods().remove(position);
            mAdapter.updateCart(MainActivity.userDB.getCurrentOrder().getAllFoods());
            checkEmptyCart();
        });
        binding.cartBTNCheckOut.setOnClickListener(view ->checkOut());

        return root;
    }

    private void checkOut() {
        // TODO: 1/13/2023 to insure the lat + lon + orderNumber
        MainActivity.userDB.getCurrentOrder().setTimeStamp(15);
        MainActivity.userDB.addOrder(MainActivity.userDB.getCurrentOrder());
        reference = reference.child(MainActivity.currentUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                reference.setValue(MainActivity.userDB);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        clearCheckOut();


    }

    private void clearCheckOut() {
        MainActivity.userDB.setCurrentOrder(new Order());
        mAdapter.updateCart(MainActivity.userDB.getCurrentOrder().getAllFoods());
        checkEmptyCart();
    }

    private void checkEmptyCart() {
        if( MainActivity.userDB.getCurrentOrder().getAllFoods().size() > 0){
            binding.cartBTNCheckOut.setVisibility(View.VISIBLE);
            binding.cartLBLTotalPrice.setVisibility(View.VISIBLE);
            binding.cartLBLTotalPrice.setText("Total price:"+MainActivity.userDB.totalPrice() +"$");
        }
        else{
            binding.cartBTNCheckOut.setVisibility(View.INVISIBLE);
            binding.cartLBLTotalPrice.setText("The Cart Empty");


        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}