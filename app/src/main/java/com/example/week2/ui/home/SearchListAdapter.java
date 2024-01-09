package com.example.week2.ui.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.week2.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week2.ProfileItem;

import java.util.ArrayList;

public class SearchListAdapter extends RecyclerView.Adapter<SearchListViewHolder> {

    private ArrayList<ProfileItem> origin;
    private ArrayList<ProfileItem> member;
    private ProfileItem cur;
    public SearchListAdapter(ArrayList<ProfileItem> origin, ProfileItem cur){
        this.origin = origin;
        this.cur = cur;
        this.member = new ArrayList<>();
        if(origin != null) {
            for (ProfileItem item : origin) {
                if (item.getUser().equals("Member")) {
                    this.member.add(item);
                }
            }
        }
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
        holder.bind(item, cur);
    }

    @Override
    public int getItemCount() {
        return member.size();
    }
}
