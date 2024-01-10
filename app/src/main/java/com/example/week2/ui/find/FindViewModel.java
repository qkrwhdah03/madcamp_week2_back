package com.example.week2.ui.find;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.week2.ProfileItem;

import java.util.ArrayList;

public class FindViewModel extends ViewModel {

    private MutableLiveData<ProfileItem> item;
    private MutableLiveData<ArrayList<ProfileItem>> matched;
    private MutableLiveData<ArrayList<ProfileItem>> data;
    private MutableLiveData<ArrayList<ProfileItem>> request;

    public FindViewModel(){
        item = new MutableLiveData<>();
        matched = new MutableLiveData<>();
        data = new MutableLiveData<>();
        request = new MutableLiveData<>();
        matched.setValue(new ArrayList<>());
        data.setValue(new ArrayList<>());
        request.setValue(new ArrayList<>());
    }

    public void setItem(ProfileItem item){
        this.item.setValue(item);
    }

    public void setData(ArrayList<ProfileItem> data){
        this.data.setValue(data);
    }
    public void setMatched(ArrayList<ProfileItem> matched){
        this.matched.setValue(matched);
    }

    public void setRequest(ArrayList<ProfileItem> request){
        this.request.setValue(request);
    }
    public LiveData<ProfileItem> getItem(){
        return item;
    }
    public LiveData<ArrayList<ProfileItem>> getData(){
        return data;
    }
    public LiveData<ArrayList<ProfileItem>> getMatched(){
        return matched;
    }
    public MutableLiveData<ArrayList<ProfileItem>> getRequest(){
        return request;
    }

}