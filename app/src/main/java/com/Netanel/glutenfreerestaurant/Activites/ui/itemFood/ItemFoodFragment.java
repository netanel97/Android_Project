package com.Netanel.glutenfreerestaurant.Activites.ui.itemFood;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.Netanel.glutenfreerestaurant.MyUtils.Constants;
import com.Netanel.glutenfreerestaurant.R;
import com.Netanel.glutenfreerestaurant.databinding.FragmentFoodItemBinding;
import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

public class ItemFoodFragment extends Fragment {
    private FragmentFoodItemBinding binding;
    private TextView foodItem_TXT_foodDetail;
    private ShapeableImageView food_IMG_foodItem;
    private MaterialTextView food_TXT_name;
    private MaterialTextView food_TXT_description;
    private MaterialTextView food_TXT_price;
    private String description;
    private String image;
    private String name;
    private String price;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFoodItemBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
//        //detailFood = getArguments().getString(Constants.ARG_ITEMFOOD);
//        detailFood = getArguments().getString("bdika");
        description = getArguments().getString(Constants.ARG_FOOD_Description);
        image = getArguments().getString(Constants.ARG_FOOD_IMG);
        name = getArguments().getString(Constants.ARG_FOOD_NAME);
        price = getArguments().getString(Constants.ARG_PRICE);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        food_IMG_foodItem = view.findViewById(R.id.food_IMG_foodItem);
        Glide.with(getContext())
                .load(image)
                .into(food_IMG_foodItem);
        food_TXT_name = view.findViewById(R.id.food_TXT_name);
        food_TXT_description = view.findViewById(R.id.food_TXT_description);
        food_TXT_price = view.findViewById(R.id.food_TXT_price);

        putDetailOnScreen();

    }

    private void putDetailOnScreen() {
        food_TXT_name.setText(this.name);
        food_TXT_description.setText(this.description);
        food_TXT_price.setText("Total Price:" +this.price + "$");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
