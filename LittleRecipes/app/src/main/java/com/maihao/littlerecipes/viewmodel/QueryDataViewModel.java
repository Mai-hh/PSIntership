package com.maihao.littlerecipes.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class QueryDataViewModel extends ViewModel {

    public static final String TAG = "QueryDataViewModel";

    public MutableLiveData<String> title = new MutableLiveData<>();

    public MutableLiveData<String> content = new MutableLiveData<>();

    public MutableLiveData<String> getTitle() {
        return title;
    }

    public MutableLiveData<String> getContent() {
        return content;
    }
}
