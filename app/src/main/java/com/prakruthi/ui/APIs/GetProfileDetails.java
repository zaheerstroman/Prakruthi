package com.prakruthi.ui.APIs;

import com.prakruthi.ui.Variables;
import com.prakruthi.ui.ui.home.banners.HomeBannerModel;
import com.prakruthi.ui.ui.home.category.HomeCategoryModal;
import com.prakruthi.ui.ui.home.products.HomeProductModel;
import com.prakruthi.ui.ui.productPage.ProductModel;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class GetProfileDetails {

    //private GetProfileDetails.OnDataFetchedListener mListener;
    private GetProfileDetails.OnProfileProductDataFetched mListener;

    private static String productId;

    public GetProfileDetails(GetProfileDetails.OnProfileProductDataFetched listner, String productId) {
        this.mListener = listner;
        this.productId = productId;
    }

    public void fetchData() {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new GetProfileDetails.GetProfileProductData());

    }


    private class GetProfileProductData implements Runnable {

        @Override
        public void run() {
            //Creating array for parameters
            String[] field = new String[3];
            field[0] = "user_id";
            field[1] = "token";
            field[2] = "product_id";
            //Creating array for data
            String[] data = new String[3];
            data[0] = String.valueOf(Variables.id);
            data[1] = Variables.token;
            data[2] = productId;

//            PutData putData = new PutData(Variables.BaseUrl+"getDashboardDetails", "POST", field, data);
//            PutData putData = new PutData(Variables.BaseUrl+"getProductDetails", "POST", field, data);
            PutData putData = new PutData(Variables.BaseUrl + "getProductslist", "POST", field, data);

            if (putData.startPut()) {
                if (putData.onComplete()) {
                    String result = putData.getResult();
                    handleResponse(result);
                } else {
                    handleError("Failed to fetch data");
                }
            } else {
                handleError("Failed to connect to server");
            }
        }
    }

    private void handleResponse(String result) {

        if (result != null) {
            try {
                JSONObject jsonResponse = new JSONObject(result);
                JSONArray productList = jsonResponse.getJSONObject("data").getJSONArray("products_list");

                List<HomeProductModel> homeProductModels = new ArrayList<>();

                for (int i = 0; i < productList.length(); i++) {
                    JSONObject productlist = productList.getJSONObject(i);
                    int id = productlist.getInt("id");
                    String categoryId = productlist.getString("category_id");
                    String name = productlist.getString("name");
                    String attachment = productlist.getString("attachment");
                    String description = productlist.getString("description");
                    homeProductModels.add(new HomeProductModel(id, name, description, attachment));
                }

//                mListener.onProductListFetched(homeProductModels);
                mListener.onProfileProductListFetched(homeProductModels);
            } catch (JSONException e) {
                e.printStackTrace();
                handleError("Failed to parse data");
            }
        } else {
            handleError("Failed to fetch data");
        }
    }

    private void handleError(String error) {
        mListener.onDataFetchError(error);
    }

    public interface OnProfileProductDataFetched {
        //GetHomeDetails

        //onProductListFetched
        void onProfileProductListFetched(List<HomeProductModel> homeProductModels);

        void OnDataFetched(ProductModel productModel);

        void onDataFetchError(String message);

    }

}



