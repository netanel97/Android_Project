package com.Netanel.glutenfreerestaurant.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Netanel.glutenfreerestaurant.Model.Food;
import com.Netanel.glutenfreerestaurant.Model.UserDB;
import com.Netanel.glutenfreerestaurant.R;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class CartRecyclerViewAdapter extends RecyclerView.Adapter<CartRecyclerViewAdapter.CartViewHolder> {
    private Context context;
    private ArrayList<Food> foods;
    private FoodClickListener listener;
    public CartRecyclerViewAdapter(Context context){
        this.context = context;
        foods = new ArrayList<>();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        CartViewHolder cartViewHolder = new CartViewHolder(view);
        return cartViewHolder;
    }

    public CartRecyclerViewAdapter setFoodClickListener(CartRecyclerViewAdapter.FoodClickListener FoodClickListener){
        this.listener = FoodClickListener;
        return this;
    }
    public void updateCart(final ArrayList<Food> foods){
        this.foods = foods;
        notifyDataSetChanged();

    }

    @Override
    public void onBindViewHolder(@NonNull CartRecyclerViewAdapter.CartViewHolder holder, int position) {
        Food item = getItem(position);
        holder.cart_LBL_FoodName.setText(item.getName());
        holder.cart_LBL_price.setText(item.getPrice()+"$");
        Glide.with(context)
                .load(item.getImage())
                .into(holder.cart_IMG_FoodItem);
    }
    private Food getItem(int position) {
        return UserDB.getInstance().getCurrentOrder().getAllFoods().get(position);
    }

    @Override
    public int getItemCount() {
        return UserDB.getInstance().getCurrentOrder().getAllFoods() == null ? 0 : UserDB.getInstance().getCurrentOrder().getAllFoods().size();
    }


    public interface FoodClickListener{
        void removeItemFromCart(Food food,int position);
    }






    class CartViewHolder extends RecyclerView.ViewHolder {
        private ImageView cart_IMG_FoodItem;
        private MaterialTextView cart_LBL_FoodName;
        private MaterialTextView cart_LBL_price;
        private FloatingActionButton cart_BTN_deleteItem;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            findViews(itemView);
            cart_BTN_deleteItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    listener.removeItemFromCart(foods.get(position),position);
                }
            });
        }

        private void findViews(View itemView) {
            cart_IMG_FoodItem = itemView.findViewById(R.id.cart_IMG_FoodItem);
            cart_LBL_FoodName = itemView.findViewById(R.id.cart_LBL_FoodName);
            cart_LBL_price = itemView.findViewById(R.id.cart_LBL_price);
            cart_BTN_deleteItem = itemView.findViewById(R.id.cart_BTN_deleteItem);


        }
    }
}
