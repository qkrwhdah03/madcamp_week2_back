package com.example.week2.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.week2.ProfileItem;

public class NotificationsViewModel extends ViewModel {

    private final MutableLiveData<ProfileItem> item;

    public NotificationsViewModel(){
        item = new MutableLiveData<>();
    }
    public void setProfileItem(ProfileItem nitem){
        this.item.setValue(nitem);
    }
    public ProfileItem getProfileItem(){
        return item.getValue();
    }
}