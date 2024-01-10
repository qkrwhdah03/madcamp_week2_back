package com.example.week2.ui.find;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.week2.ProfileItem;
import com.example.week2.R;
import com.example.week2.ui.dashboard.MatchedListAdapter;

import java.util.ArrayList;

public class FindDataFragment extends Fragment {

    private LiveData<ArrayList<ProfileItem>> data;
    private LiveData<ArrayList<ProfileItem>> matched;
    private LiveData<ProfileItem> cur;
    public FindDataFragment( LiveData<ArrayList<ProfileItem>> data, LiveData<ArrayList<ProfileItem>> matched, LiveData<ProfileItem> cur) {
        this.data = data;
        this.matched = matched;
        this.cur = cur;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_find_data, container, false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView request_list = root.findViewById(R.id.find_data_view_container);
        FindDataAdapter adapter = new FindDataAdapter(data, matched, cur);
        request_list.setAdapter(adapter);
        request_list.setLayoutManager(layoutManager);

        return root;
    }
}