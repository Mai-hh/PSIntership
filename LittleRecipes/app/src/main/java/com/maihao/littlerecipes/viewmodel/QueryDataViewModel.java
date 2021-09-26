package com.maihao.littlerecipes.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class QueryDataViewModel extends ViewModel {

    public static final String TAG = "QueryDataViewModel";

    public MutableLiveData<String> title = new MutableLiveData<>();

    public MutableLiveData<String> content = new MutableLiveData<>();

    public MutableLiveData<Integer> imageId = new MutableLiveData<>();

    public MutableLiveData<String> ingredients = new MutableLiveData<>();

    public MutableLiveData<String> getTitle() {
        return title;
    }

    public MutableLiveData<String> getContent() {
        return content;
    }

    public MutableLiveData<Integer> getImageId() {
        return imageId;
    }

    public MutableLiveData<String> getIngredients() {
        return ingredients;
    }
}
