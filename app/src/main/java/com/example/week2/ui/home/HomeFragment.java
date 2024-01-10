package com.example.week2.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week2.ProfileItem;
import com.example.week2.databinding.FragmentHomeBinding;
import com.example.week2.R;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private LiveData<ProfileItem> cur;
    private LiveData<ArrayList<ProfileItem>> data;
    private LiveData<ArrayList<ProfileItem>> matched;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // 현재 프로필 가져올 view 모델
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        cur = homeViewModel.getProfileItem();
        data = homeViewModel.getProfileList();
        matched = homeViewModel.getProfileMatched();

        Log.d("procedure", "Size "+ Integer.toString(data.getValue().size()));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView list = root.findViewById(R.id.search_show_memeber_listview);
        SearchListAdapter adapter = new SearchListAdapter(data, matched, cur);
        list.setAdapter(adapter);
        list.setLayoutManager(layoutManager);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}