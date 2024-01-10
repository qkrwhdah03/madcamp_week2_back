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

import java.util.ArrayList;

public class FindMatchedFragment extends Fragment {
    private LiveData<ArrayList<ProfileItem>> matched;
    public FindMatchedFragment(LiveData<ArrayList<ProfileItem>> matched) {
        this.matched = matched;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_find_matched, container, false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView request_list = root.findViewById(R.id.find_matched_view_container);
        FindMatchedAdapter adapter = new FindMatchedAdapter(matched);
        request_list.setAdapter(adapter);
        request_list.setLayoutManager(layoutManager);
        return root;
    }
}