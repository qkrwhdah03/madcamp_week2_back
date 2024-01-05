package com.example.week2.ui.body;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BodyViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public BodyViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is body fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}