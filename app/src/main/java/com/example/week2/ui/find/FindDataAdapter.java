package com.example.week2.ui.find;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week2.ProfileItem;
import com.example.week2.R;
import com.example.week2.ui.dashboard.MatchedListViewHolder;
import com.example.week2.ui.home.SearchListViewHolder;

import java.util.ArrayList;

public class FindDataAdapter extends RecyclerView.Adapter<FindDataViewHolder>{
    private LiveData<ArrayList<ProfileItem>> origin;
    private ArrayList<ProfileItem> data;
    private LiveData<ProfileItem> cur;
    private LiveData<ArrayList<ProfileItem>> matched;
    public FindDataAdapter(LiveData<ArrayList<ProfileItem>> origin,LiveData<ArrayList<ProfileItem>> matched,  LiveData<ProfileItem> cur){
        this.origin = origin;
        this.matched = matched;
        this.cur = cur;
        this.data = new ArrayList<>();
        if(this.origin.getValue() != null) {
            for (ProfileItem item : this.origin.getValue()) {
                if (item.getUser().equals("Trainer") && !inMatched(item)) {
                    this.data.add(item);
                }
            }
        }
        this.matched.observeForever(profileItems -> {
            data.clear();
            if (profileItems != null) {
                for (ProfileItem item : profileItems) {
                    if ("Trainer".equals(item.getUser()) && !inMatched(item)) {
                        data.add(item);
                    }
                }
                notifyDataSetChanged(); // Notify adapter about the data change
            }
        });

        this.origin.observeForever(profileItems -> {
            data.clear();
            if (profileItems != null) {
                for (ProfileItem item : profileItems) {
                    if ("Trainer".equals(item.getUser()) && !inMatched(item)) {
                        data.add(item);
                    }
                }
                notifyDataSetChanged(); // Notify adapter about the data change
            }
        });
    }
    
    private boolean inMatched(ProfileItem item){
        ArrayList<ProfileItem> cur_matched = matched.getValue();
        for(ProfileItem matched_item : cur_matched){
            if(matched_item.getKakaoid().equals(item.getKakaoid())){
                return true;
            }
        }
        return false;
    }

    @NonNull
    @Override
    public FindDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listoftrainers, parent, false);
        return new FindDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FindDataViewHolder holder, int position) {
        ProfileItem item = data.get(position);
        holder.bind(item, cur.getValue());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
