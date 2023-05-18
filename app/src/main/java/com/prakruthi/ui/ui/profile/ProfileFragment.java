package com.prakruthi.ui.ui.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prakruthi.R;
import com.prakruthi.databinding.FragmentProfileBinding;
import com.prakruthi.ui.APIs.GetHomeDetails;
import com.prakruthi.ui.APIs.GetProfileDetails;
import com.prakruthi.ui.ui.home.products.HomeProductAdaptor;
import com.prakruthi.ui.ui.home.products.HomeProductModel;
import com.prakruthi.ui.ui.productPage.ProductModel;

import java.util.List;

//public class ProfileFragment extends Fragment implements GetProfileDetails.OnDataFetchedListener{
//public class ProfileFragment extends Fragment implements GetProfileDetails.onProductListFetched{
//    public class ProfileFragment extends Fragment implements GetProfileDetails.OnProductDataFetched{
public class ProfileFragment extends Fragment implements GetProfileDetails.OnProfileProductDataFetched{



        private FragmentProfileBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater,container,false);
       return binding.getRoot();
//        GetProfileDetails();
    }

    

//    onProductListFetched
    //GetProfileDetails
    @Override
    public void onProfileProductListFetched(List<HomeProductModel> homeProductModels) {
        requireActivity().runOnUiThread(() -> {
            binding.ProfileHomeProductsRecycler.hideShimmerAdapter();
//          Linear Horizontal/Vertical Recyclerview:------
//            binding.HomeCategoryRecyclerview.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false));
//            GridView Recylerview:-------
            binding.ProfileHomeProductsRecycler.setLayoutManager(new GridLayoutManager(requireContext(),2));
            binding.ProfileHomeProductsRecycler.setAdapter(new HomeProductAdaptor(homeProductModels));
//            binding.dotsIndicator.attachTo(viewPager);
//            BannerFetched = true;
//            runnable.run();
        });

    }

    @Override
    public void OnDataFetched(ProductModel productModel) {

    }

    @Override
    public void onDataFetchError(String message) {

    }
}