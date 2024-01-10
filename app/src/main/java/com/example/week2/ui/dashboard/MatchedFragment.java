package com.example.week2.ui.dashboard;

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

public class MatchedFragment extends Fragment {

    private LiveData<ArrayList<ProfileItem>> list;
    public MatchedFragment(LiveData<ArrayList<ProfileItem>> list) {
        // Required empty public constructor
        this.list = list;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_matched, container, false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView request_list = root.findViewById(R.id.match_view_container);
        MatchedListAdapter adapter = new MatchedListAdapter(list);
        request_list.setAdapter(adapter);
        request_list.setLayoutManager(layoutManager);

        return root;
    }
}