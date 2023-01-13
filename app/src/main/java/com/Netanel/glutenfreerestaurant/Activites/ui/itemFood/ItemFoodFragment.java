package com.Netanel.glutenfreerestaurant.Activites.ui.itemFood;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.Netanel.glutenfreerestaurant.Activites.MainActivity;
import com.Netanel.glutenfreerestaurant.Model.Food;
import com.Netanel.glutenfreerestaurant.MyUtils.Constants;
import com.Netanel.glutenfreerestaurant.MyUtils.FireBaseOperations;
import com.Netanel.glutenfreerestaurant.MyUtils.MySignal;
import com.Netanel.glutenfreerestaurant.R;
import com.Netanel.glutenfreerestaurant.databinding.FragmentFoodItemBinding;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class ItemFoodFragment extends Fragment {
    private FragmentFoodItemBinding binding;
    private ShapeableImageView food_IMG_foodItem;
    private FloatingActionButton foodItem_BTN_cart;
    private MaterialTextView food_TXT_name;
    private MaterialTextView food_TXT_description;
    private MaterialTextView food_TXT_price;
    private Food food;
    private DatabaseReference reference = FireBaseOperations.getInstance().getDatabaseReference("UserDB");
    //currentUser

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFoodItemBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        food = getArguments().getParcelable(Constants.FOOD_DATA);
        return root;
    }

    /**
        This function will put details on the screen of the current food,
        After click on adding to the cart he can press on the toolbar and show it on Cart
     **/

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        putDetailOnScreen();

        foodItem_BTN_cart.setOnClickListener(view1 -> {

            MainActivity.userDB.getCurrentOrder().addFood(food);//adding the current food to the currentOrder
            MySignal.getInstance().toast("Item Added");

        });

    }
    private void findViews(View view) {
        food_IMG_foodItem = view.findViewById(R.id.food_IMG_foodItem);
        Glide.with(getContext())
                .load(food.getImage())
                .into(food_IMG_foodItem);
        food_TXT_name = view.findViewById(R.id.food_TXT_name);
        food_TXT_description = view.findViewById(R.id.food_TXT_description);
        food_TXT_price = view.findViewById(R.id.food_TXT_price);
        foodItem_BTN_cart = view.findViewById(R.id.foodItem_BTN_cart);
    }

    private void putDetailOnScreen() {
        food_TXT_name.setText(food.getName());
        food_TXT_description.setText(food.getDescription());
        food_TXT_price.setText("Total Price:" +food.getPrice() + "$");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
