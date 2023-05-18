package com.prakruthi.ui.APIs;

import com.prakruthi.ui.ui.home.products.HomeProductModel;
import com.prakruthi.ui.ui.productPage.ProductModel;

import java.util.List;

public interface OnProfileProductDataFetched {
    //GetHomeDetails

    //onProductListFetched
    void onProfileProductListFetched(List<HomeProductModel> homeProductModels);

    void OnDataFetched(ProductModel productModel);

    void OnDataFetchError(String message);

}
