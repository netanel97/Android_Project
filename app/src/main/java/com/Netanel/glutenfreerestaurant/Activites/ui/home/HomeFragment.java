package com.Netanel.glutenfreerestaurant.Activites.ui.home;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Netanel.glutenfreerestaurant.Adapter.HomeRecyclerViewAdapter;
import com.Netanel.glutenfreerestaurant.Model.Category;
import com.Netanel.glutenfreerestaurant.MyUtils.Constants;
import com.Netanel.glutenfreerestaurant.R;
import com.Netanel.glutenfreerestaurant.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment{
    private HomeRecyclerViewAdapter mAdapter;
    private HomeViewModel homeViewModel;
    private RecyclerView searchResultsRV;
    private FragmentHomeBinding binding;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        searchResultsRV = binding.searchResultsRV;
        homeViewModel.getCategories().observe(getViewLifecycleOwner(),observer);
        mAdapter = new HomeRecyclerViewAdapter(getContext());
        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        searchResultsRV.setLayoutManager(linearLayoutManager);
        searchResultsRV.setAdapter(mAdapter);
        mAdapter.setCategoryItemClickListener(new HomeRecyclerViewAdapter.FoodItemClickListener() {
            @Override
            public void changeScreen(Category category) {
                String categoryId = category.getKey();
                Bundle args = new Bundle();
                args.putString(Constants.ARGS_CATEGORYID, categoryId);
                Log.d("Saving Cat Id", "changeScreen: " + categoryId);
                final NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_home);
                navController.navigate(R.id.nav_food_list,args);

            }
        });
        return root;
    }



    Observer<ArrayList<Category>> observer = new Observer<ArrayList<Category>>(){

        @Override
        public void onChanged(ArrayList<Category> categories) {
            mAdapter.updateCategories(categories);
        }
    };
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}