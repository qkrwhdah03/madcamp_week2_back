package com.example.week2.ui.find;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week2.ProfileItem;
import com.example.week2.R;

import java.util.ArrayList;

public class FindMatchedAdapter extends RecyclerView.Adapter<FindMatchedViewHolder> {
    private LiveData<ArrayList<ProfileItem>> matched;
    private LiveData<ProfileItem> cur;
    public FindMatchedAdapter(LiveData<ArrayList<ProfileItem>> matched){
        this.matched = matched;
        this.matched.observeForever(profileItems -> {
            notifyDataSetChanged(); // Notify adapter about the data change
        });
    }

    @NonNull
    @Override
    public FindMatchedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trainer_profile_matched, parent, false);
        return new FindMatchedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FindMatchedViewHolder holder, int position) {
        ProfileItem item = matched.getValue().get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return matched.getValue().size();
    }
}
