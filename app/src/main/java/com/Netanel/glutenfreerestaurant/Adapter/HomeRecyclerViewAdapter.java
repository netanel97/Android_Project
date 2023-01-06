package com.Netanel.glutenfreerestaurant.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Netanel.glutenfreerestaurant.Category;
import com.Netanel.glutenfreerestaurant.InterFace.ItemClickListener;
import com.Netanel.glutenfreerestaurant.R;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.HomeViewHolder> {
    private Context context;
    private ArrayList<Category> categories;

    public HomeRecyclerViewAdapter(Context context) {
        this.context = context;
        categories = new ArrayList<>();
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        HomeViewHolder homeViewHolder = new HomeViewHolder(view);
        return homeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Category item = getItem(position);
        holder.item_TXT_name.setText(item.getName());
        Glide.with(context)
                .load(item.getImage())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.item_IMG_card);
    }


    @Override
    public int getItemCount() {
        return categories == null ? 0 : categories.size();
    }

    private Category getItem(int position){
        return categories.get(position);
    }

    public void updateCategories(final ArrayList<Category> categories){

        this.categories = categories;
        notifyDataSetChanged();
    }

    class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView item_IMG_card;
        private TextView item_TXT_name;
        private ItemClickListener itemClickListener;
        public HomeViewHolder(View itemView) {
            super(itemView);
            item_IMG_card =itemView.findViewById(R.id.item_IMG_card);
            item_TXT_name =itemView.findViewById(R.id.item_TXT_name);
            itemView.setOnClickListener(this);

        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition(),false);
        }
    }
}


