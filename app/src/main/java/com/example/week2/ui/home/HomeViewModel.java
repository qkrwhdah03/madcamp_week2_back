package com.example.week2.ui.home;

import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.week2.ProfileItem;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<ProfileItem> item;
    private final MutableLiveData<ArrayList<ProfileItem>> data;

    public HomeViewModel() {
        item = new MutableLiveData<>();
        data = new MutableLiveData<>();
        data.setValue(new ArrayList<>());
    }
    public void setProfileItem(ProfileItem nitem){
        this.item.setValue(nitem);
    }
    public void setProfileList(ArrayList<ProfileItem> data){this.data.setValue(data);}
    public LiveData<ArrayList<ProfileItem>> getProfileList(){return data;}
    public LiveData<ProfileItem> getProfileItem(){
        return item;
    }

}