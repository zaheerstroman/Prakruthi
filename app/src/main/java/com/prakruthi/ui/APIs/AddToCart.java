package com.prakruthi.ui.APIs;

import static com.google.firebase.messaging.Constants.TAG;

import android.util.Log;

import com.prakruthi.ui.Variables;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AddToCart {

    String Productid , quantity ,quantityafter , id;
    boolean edit = false;

    OnDataFetchedListner mListner;

    public AddToCart(String productid , String quantity , String quantityafter , String id , boolean edit , OnDataFetchedListner listner)
    {
        this.Productid = productid;
        this.edit = edit;
        this.quantity = quantity;
        this.quantityafter = quantityafter;
        this.id = id;
        mListner = listner;
    }

    public void fetchData()
    {
        if (edit)
        {
            Executor executor = Executors.newSingleThreadExecutor();
            executor.execute(new editCartItem());
        }
        else
        {
            Executor executor = Executors.newSingleThreadExecutor();
            executor.execute(new AddtoCartItem());
        }
    }


    private class AddtoCartItem implements Runnable
    {
        @Override
        public void run() {
            //Creating array for parameters
            String[] field = new String[4];
            field[0] = "user_id";
            field[1] = "token";
            field[2] = "product_id";
            field[3] = "quantity";
            //Creating array for data
            String[] data = new String[4];
            data[0] = String.valueOf(Variables.id);
            data[1] = Variables.token;
            data[2] = Productid;
            data[3] = quantity;

            PutData putData = new PutData(Variables.BaseUrl+"addToCart", "POST", field, data);

            if (putData.startPut()) {
                if (putData.onComplete()) {
                    String result = putData.getResult();
                    try {
                        JSONObject response = new JSONObject(result);

                        // Extract the "message" string
                        String message = response.getString("message");

                        // Use the "message" string as needed
                        handleResponseAdd(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    handleError("Failed to fetch data");
                }
            } else {
                handleError("Failed to connect to server");
            }
        }
    }

    private class editCartItem implements Runnable
    {
        @Override
        public void run() {
            //Creating array for parameters
            String[] field = new String[5];
            field[0] = "user_id";
            field[1] = "token";
            field[2] = "product_id";
            field[3] = "quantity";
            field[4] = "id";
            //Creating array for data
            String[] data = new String[5];
            data[0] = String.valueOf(Variables.id);
            data[1] = Variables.token;
            data[2] = Productid;
            data[3] = quantityafter;
            data[4] = id;
            Log.e(TAG, Arrays.toString(field));
            Log.e(TAG, Arrays.toString(data));

            PutData putData = new PutData(Variables.BaseUrl+"addToCart", "POST", field, data);

            if (putData.startPut()) {
                if (putData.onComplete()) {
                    String result = putData.getResult();
                    try {
                        JSONObject response = new JSONObject(result);

                        // Extract the "message" string
                        String message = response.getString("message");

                        // Use the "message" string as needed
                        handleResponseEdit(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    handleError("Failed to fetch data");
                }
            } else {
                handleError("Failed to connect to server");
            }
        }
    }
    private void handleError(String failed_to_fetch_data) {
        mListner.OnErrorFetched(failed_to_fetch_data);
    }

    private void handleResponseAdd(String result) {
        mListner.OnAddtoCartDataFetched(result);
    }

    private void handleResponseEdit(String result) {
        mListner.OnCarteditDataFetched(result);
    }

    public interface OnDataFetchedListner
    {
        void OnCarteditDataFetched(String Message);

        void OnAddtoCartDataFetched(String Message);

        void OnErrorFetched(String Error);
    }
}
