package com.Netanel.glutenfreerestaurant.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Netanel.glutenfreerestaurant.Model.Food;
import com.Netanel.glutenfreerestaurant.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;

public class CartRycyclerViewAdapter {
    private Context context;
    private FoodClickListener listener;



    public interface FoodClickListener{
        void delete(Food food);
    }





    class CartViewHolder extends RecyclerView.ViewHolder {
        private ImageView cart_IMG_FoodItem;
        private MaterialTextView cart_LBL_FoodName;
        private MaterialTextView cart_LBL_price;
        private FloatingActionButton cart_BTN_deleteItem;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            findViews(itemView);


        }

        private void findViews(View itemView) {
            cart_IMG_FoodItem = itemView.findViewById(R.id.cart_IMG_FoodItem);
            cart_LBL_FoodName = itemView.findViewById(R.id.cart_LBL_FoodName);
            cart_LBL_price = itemView.findViewById(R.id.cart_LBL_price);
            cart_BTN_deleteItem = itemView.findViewById(R.id.cart_BTN_deleteItem);


        }
    }
}
