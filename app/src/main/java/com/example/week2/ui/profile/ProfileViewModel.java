package com.example.week2.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.week2.ProfileItem;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<ProfileItem> item;

    public ProfileViewModel(){
        item = new MutableLiveData<>();
    }

    public void setItem(ProfileItem item){
        this.item.setValue(item);
    }

    public LiveData<ProfileItem> getItem(){
        return item;
    }

}