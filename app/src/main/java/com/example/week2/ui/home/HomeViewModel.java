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
    private final MutableLiveData<ArrayList<ProfileItem>> matched;

    public HomeViewModel() {
        item = new MutableLiveData<>();
        data = new MutableLiveData<>();
        matched = new MutableLiveData<>();
        data.setValue(new ArrayList<>());
        matched.setValue(new ArrayList<>());
    }
    public void setProfileItem(ProfileItem nitem){
        this.item.setValue(nitem);
    }
    public void setProfileList(ArrayList<ProfileItem> data){this.data.setValue(data);}
    public void setProfileMatched(ArrayList<ProfileItem> matched){this.matched.setValue(matched);}
    public LiveData<ArrayList<ProfileItem>> getProfileList(){return data;}
    public LiveData<ArrayList<ProfileItem>> getProfileMatched(){return matched;}
    public LiveData<ProfileItem> getProfileItem(){
        return item;
    }

}