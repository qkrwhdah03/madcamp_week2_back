package com.example.week2.ui.find;

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

import java.util.ArrayList;

public class FindRequestFragment extends Fragment {

    private MutableLiveData<ArrayList<ProfileItem>> request;
    private LiveData<ProfileItem> cur;
    public FindRequestFragment(MutableLiveData<ArrayList<ProfileItem>> request, LiveData<ProfileItem> cur) {
        this.request = request;
        this.cur = cur;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_find_request, container, false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView request_list = root.findViewById(R.id.find_request_view_container);
        FindRequestAdapter adapter = new FindRequestAdapter(request, cur);
        request_list.setAdapter(adapter);
        request_list.setLayoutManager(layoutManager);

        return root;
    }
}