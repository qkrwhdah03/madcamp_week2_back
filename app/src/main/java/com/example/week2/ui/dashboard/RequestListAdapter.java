package com.example.week2.ui.dashboard;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;
import com.example.week2.R;
import com.example.week2.ProfileItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RequestListAdapter extends RecyclerView.Adapter<RequestListViewHolder> {
    private MutableLiveData<ArrayList<ProfileItem>> itemList;
    private LiveData<ProfileItem> cur;

    public RequestListAdapter(MutableLiveData<ArrayList<ProfileItem>> itemList, LiveData<ProfileItem> cur) {
        this.itemList = itemList;
        this.cur = cur;
        itemList.observeForever(profileItems -> {
            notifyDataSetChanged();
        });
    }

    @Override
    public RequestListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 새로운 뷰 홀더를 생성하고 반환
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_profile_request, parent, false);
        return new RequestListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestListViewHolder holder, int position) {
        // 뷰 홀더에 데이터를 바인딩
        ProfileItem item = itemList.getValue().get(position);
        holder.bind(item, cur, this);
    }

    @Override
    public int getItemCount() {
        // 데이터 세트의 크기 반환
        return itemList.getValue().size();
    }

    public void remove(ProfileItem target){
        ArrayList<ProfileItem> tmp = itemList.getValue();
        Iterator<ProfileItem> iterator = tmp.iterator();
        while (iterator.hasNext()) {
            ProfileItem item = iterator.next();
            if (item.getKakaoid().equals(target.getKakaoid())) {
                iterator.remove();
            }
        }
        this.itemList.setValue(tmp);
        notifyDataSetChanged();
    }
}
