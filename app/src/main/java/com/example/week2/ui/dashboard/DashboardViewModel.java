package com.example.week2.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.week2.ProfileItem;

import java.util.ArrayList;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<ArrayList<ProfileItem>> matched;
    private MutableLiveData<ArrayList<ProfileItem>> request;
    private MutableLiveData<ProfileItem> item;

    public DashboardViewModel() {
        matched = new MutableLiveData<>();
        request = new MutableLiveData<>();
        item = new MutableLiveData<>();
        matched.setValue(new ArrayList<ProfileItem>());
        request.setValue(new ArrayList<ProfileItem>());
    }

    public void setMatched(ArrayList<ProfileItem> list){
        matched.setValue(list);
    }
    public void setRequest(ArrayList<ProfileItem> list){
        request.setValue(list);
    }
    public void setItem(ProfileItem item){this.item.setValue(item);}
    public LiveData<ArrayList<ProfileItem>> getMatched(){
        return matched;
    }
    public MutableLiveData<ArrayList<ProfileItem>> getRequest(){
        return request;
    }
    public LiveData<ProfileItem> getItem(){return item;}
}