package com.prakruthi.viewmodal;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.prakruthi.Response.GetCartDetailsResponse;

public class DisplayViewmodal extends ViewModel {

    private MutableLiveData<GetCartDetailsResponse> successlist;
    @SuppressLint("StaticFieldLeak")
    Context context;

    public LiveData<GetCartDetailsResponse> getDisplayMenuDetails() {
        //if the list is null
        if (successlist == null) {
            successlist = new MutableLiveData<GetCartDetailsResponse>();
            //we will load it asynchronously from server in this method
//            HomePageMethod();
        }

        //finally we will return the list
        return successlist;
    }


}

