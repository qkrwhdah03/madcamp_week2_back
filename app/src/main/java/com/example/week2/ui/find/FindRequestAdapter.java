package com.example.week2.ui.find;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week2.ProfileItem;
import com.example.week2.R;

import java.util.ArrayList;
import java.util.Iterator;

public class FindRequestAdapter  extends RecyclerView.Adapter<FindRequestViewHolder>{
    private MutableLiveData<ArrayList<ProfileItem>> request;
    private LiveData<ProfileItem> cur;
    public FindRequestAdapter(MutableLiveData<ArrayList<ProfileItem>> request, LiveData<ProfileItem> cur){
        this.request = request;
        this.cur = cur;
        this.request.observeForever(profileItems -> {
            notifyDataSetChanged(); // Notify adapter about the data change
        });
    }

    @NonNull
    @Override
    public FindRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trainer_profile_request, parent, false);
        return new FindRequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FindRequestViewHolder holder, int position) {
        ProfileItem item = request.getValue().get(position);
        holder.bind(item, cur.getValue(), this);
    }

    @Override
    public int getItemCount() {
        return request.getValue().size();
    }

    public void remove(ProfileItem target){
        ArrayList<ProfileItem> tmp = request.getValue();
        Iterator<ProfileItem> iterator = tmp.iterator();
        while (iterator.hasNext()) {
            ProfileItem item = iterator.next();
            if (item.getKakaoid().equals(target.getKakaoid())) {
                iterator.remove();
            }
        }
        this.request.setValue(tmp);
        notifyDataSetChanged();
    }
}
