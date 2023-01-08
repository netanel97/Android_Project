package com.Netanel.glutenfreerestaurant.Activites.ui.itemFood;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.Netanel.glutenfreerestaurant.MyUtils.Constants;
import com.Netanel.glutenfreerestaurant.R;
import com.Netanel.glutenfreerestaurant.databinding.FragmentFoodItemBinding;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

public class ItemFoodFragment extends Fragment {
    private FragmentFoodItemBinding binding;
    private ShapeableImageView food_IMG_foodItem;
    private FloatingActionButton foodItem_BTN_cart;
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
        description = getArguments().getString(Constants.ARG_FOOD_Description);
        image = getArguments().getString(Constants.ARG_FOOD_IMG);
        name = getArguments().getString(Constants.ARG_FOOD_NAME);
        price = getArguments().getString(Constants.ARG_PRICE);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        putDetailOnScreen();

    }

    private void findViews(View view) {
        food_IMG_foodItem = view.findViewById(R.id.food_IMG_foodItem);
        Glide.with(getContext())
                .load(image)
                .into(food_IMG_foodItem);
        food_TXT_name = view.findViewById(R.id.food_TXT_name);
        food_TXT_description = view.findViewById(R.id.food_TXT_description);
        food_TXT_price = view.findViewById(R.id.food_TXT_price);
        foodItem_BTN_cart = view.findViewById(R.id.foodItem_BTN_cart);
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
