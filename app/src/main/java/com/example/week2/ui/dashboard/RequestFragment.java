package com.example.week2.ui.dashboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.week2.ProfileItem;
import com.example.week2.R;
import com.example.week2.ui.home.SearchListAdapter;

import java.util.ArrayList;

public class RequestFragment extends Fragment {

    private MutableLiveData<ArrayList<ProfileItem>> list;
    private LiveData<ProfileItem> cur;
    public RequestFragment(MutableLiveData<ArrayList<ProfileItem>> list, LiveData<ProfileItem> cur) {
        this.list = list;
        this.cur = cur;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_request, container, false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView request_list = root.findViewById(R.id.request_view_container);
        RequestListAdapter adapter = new RequestListAdapter(list, cur);
        request_list.setAdapter(adapter);
        request_list.setLayoutManager(layoutManager);

        return root;
    }
}