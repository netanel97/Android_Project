package com.Netanel.glutenfreerestaurant.Activites.ui.food;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Netanel.glutenfreerestaurant.Adapter.FoodRycyclerViewAdapter;
import com.Netanel.glutenfreerestaurant.Food;
import com.Netanel.glutenfreerestaurant.MyUtils.Constants;
import com.Netanel.glutenfreerestaurant.R;
import com.Netanel.glutenfreerestaurant.databinding.FragmentFoodListBinding;

import java.util.ArrayList;


public class FoodFragment extends Fragment {
    private FragmentFoodListBinding binding;
    private RecyclerView foodRV;
    private FoodViewModel foodViewModel;
    private FoodRycyclerViewAdapter mAdapter;

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        //foodViewModel = new ViewModelProvider(this).get(FoodViewModel.class);
//    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFoodListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        foodRV = binding.listFood;
        String categoryId = getArguments().getString(Constants.ARGS_CATEGORYID);
        foodViewModel = new FoodViewModel(categoryId);
        foodViewModel.getFoodItems().observe(getViewLifecycleOwner(),observer);
        mAdapter = new FoodRycyclerViewAdapter(getContext());
        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        foodRV.setLayoutManager(linearLayoutManager);
        foodRV.setAdapter(mAdapter);
        mAdapter.setItemFoodClickListener(new FoodRycyclerViewAdapter.ItemClickListener() {
            @Override
            public void changeScreenItem(Food food) {
                Bundle args = new Bundle();
                args.putString(Constants.ARG_FOOD_Description,food.getDescription());
                args.putString(Constants.ARG_FOOD_NAME,food.getName());
                args.putString(Constants.ARG_PRICE,food.getPrice());
                args.putString(Constants.ARG_FOOD_IMG,food.getImage());

                final NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_home);
                navController.navigate(R.id.nav_itemFood,args);//moving to..
            }
        });
        return root;
    }

    Observer<ArrayList<Food>> observer = new Observer<ArrayList<Food>>(){

        @Override
        public void onChanged(ArrayList<Food> foodItems) {
            mAdapter.updateFoods(foodItems);
        }
    };
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
