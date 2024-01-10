package com.example.week2.ui.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.week2.R;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week2.ProfileItem;

import java.util.ArrayList;

public class SearchListAdapter extends RecyclerView.Adapter<SearchListViewHolder> {

    private LiveData<ArrayList<ProfileItem>> origin;
    private LiveData<ArrayList<ProfileItem>> matched;
    private ArrayList<ProfileItem> member;
    private LiveData<ProfileItem> cur;
    public SearchListAdapter(LiveData<ArrayList<ProfileItem>> origin, LiveData<ArrayList<ProfileItem>> matched, LiveData<ProfileItem> cur){
        this.origin = origin;
        this.matched = matched;
        this.cur = cur;
        this.member = new ArrayList<>();
        if(this.origin.getValue() != null) {
            for (ProfileItem item : this.origin.getValue()) {
                if (item.getUser().equals("Member") && !inMatched(item)) {
                    this.member.add(item);
                }
            }
        }
        this.matched.observeForever(profileItems -> {
            member.clear();
            if (profileItems != null) {
                for (ProfileItem item : profileItems) {
                    if ("Member".equals(item.getUser()) && !inMatched(item)) {
                        member.add(item);
                    }
                }
                notifyDataSetChanged(); // Notify adapter about the data change
            }
        });

        this.origin.observeForever(profileItems -> {
            member.clear();
            if (profileItems != null) {
                for (ProfileItem item : profileItems) {
                    if ("Member".equals(item.getUser()) && !inMatched(item)) {
                        member.add(item);
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
    public SearchListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listofmembers, parent, false);
        return new SearchListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchListViewHolder holder, int position) {
        ProfileItem item = member.get(position);
        holder.bind(item, cur.getValue());
    }

    @Override
    public int getItemCount() {
        return member.size();
    }
}
