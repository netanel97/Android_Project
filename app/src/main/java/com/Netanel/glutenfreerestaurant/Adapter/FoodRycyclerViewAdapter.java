package com.Netanel.glutenfreerestaurant.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Netanel.glutenfreerestaurant.Model.Food;
import com.Netanel.glutenfreerestaurant.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FoodRycyclerViewAdapter extends RecyclerView.Adapter<FoodRycyclerViewAdapter.FoodViewHolder> {
    private Context context;
    private ArrayList<Food> foods;
    private ItemClickListener listener;
    public FoodRycyclerViewAdapter(Context context){
        this.context = context;
        foods = new ArrayList<>();

    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false);
        FoodViewHolder foodViewHolder = new FoodViewHolder(view);
        return foodViewHolder;
    }

    public FoodRycyclerViewAdapter setItemFoodClickListener(ItemClickListener itemFoodClickListener){
        this.listener = itemFoodClickListener;
        return this;
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food item = getItem(position);
        item.setKey(String.valueOf(position));
        holder.item_TXT_listName.setText(item.getName());
        Glide.with(context)
                .load(item.getImage())
                .into(holder.item_IMG_listcard);
    }

    public void updateFoods(final ArrayList<Food> foods) {

        this.foods = foods;
        notifyDataSetChanged();
    }

    private Food getItem(int position) {
        return foods.get(position);
    }

    @Override
    public int getItemCount() {
        return foods == null ? 0 : foods.size();
    }


    public interface ItemClickListener{
        void changeScreenItem(Food food);
    }



    class FoodViewHolder extends RecyclerView.ViewHolder {
        private TextView item_TXT_listName;
        private ImageView item_IMG_listcard;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            item_IMG_listcard = itemView.findViewById(R.id.item_IMG_listcard);
            item_TXT_listName = itemView.findViewById(R.id.item_TXT_listName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    listener.changeScreenItem(foods.get(position));

                }
            });
        }
    }
}




