package com.example.week2.ui.dashboard;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;
import com.example.week2.R;
import com.example.week2.ProfileItem;

import java.util.ArrayList;

public class MatchedListAdapter extends RecyclerView.Adapter<MatchedListViewHolder> {
    private LiveData<ArrayList<ProfileItem>> itemList;

    public MatchedListAdapter(LiveData<ArrayList<ProfileItem>> itemList) {
        this.itemList = itemList;
        this.itemList.observeForever(profileItems -> {
            notifyDataSetChanged();
        });
    }

    @Override
    public MatchedListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 새로운 뷰 홀더를 생성하고 반환
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_profile_matched, parent, false);
        return new MatchedListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchedListViewHolder holder, int position) {
        // 뷰 홀더에 데이터를 바인딩
        ProfileItem item = itemList.getValue().get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        // 데이터 세트의 크기 반환
        return itemList.getValue().size();
    }
}
