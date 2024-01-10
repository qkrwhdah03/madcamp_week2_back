package com.example.week2.ui.find;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.week2.ProfileItem;
import com.example.week2.databinding.FragmentFindBinding;
import com.example.week2.databinding.FragmentHomeBinding;
import com.example.week2.ui.home.HomeViewModel;
import com.example.week2.R;

import java.util.ArrayList;

public class FindFragment extends Fragment {

    private FragmentFindBinding binding;
    private FindViewModel findViewModel;
    private LiveData<ProfileItem> item;
    private LiveData<ArrayList<ProfileItem>> data;
    private LiveData<ArrayList<ProfileItem>> matched;
    private MutableLiveData<ArrayList<ProfileItem>> request;
    private FindMatchedFragment findMatchedFragment;
    private FindRequestFragment findRequestFragment;
    private FindDataFragment findDataFragment;

    private boolean open_data;
    private boolean open_matched;
    private boolean open_request;

    public FindFragment(){
        open_matched = true;
        open_request = false;
        open_data = false;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentFindBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        findViewModel = new ViewModelProvider(requireActivity()).get(FindViewModel.class);
        item = findViewModel.getItem();
        data = findViewModel.getData();
        matched = findViewModel.getMatched();
        request = findViewModel.getRequest();

        findMatchedFragment = new FindMatchedFragment(matched);
        findRequestFragment = new FindRequestFragment(request, item);
        findDataFragment = new FindDataFragment(data, matched, item);

        View matched_layout = binding.findMatchedListLinearLayout;
        View request_layout = binding.findRequestListLinearLayout;
        View data_layout = binding.findDataListLinearLayout;

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.find_matched_list_container, findMatchedFragment)
                .addToBackStack(null)
                .commit();

        matched_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_matched = !open_matched;
                if(open_matched){
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.find_matched_list_container, findMatchedFragment)
                            .addToBackStack(null)
                            .commit();
                } else{
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    Fragment fragment = fragmentManager.findFragmentById(R.id.find_matched_list_container);
                    if (fragment != null) {
                        fragmentManager.beginTransaction().remove(fragment).commit();
                    }
                }
            }
        });

        request_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_request = !open_request;
                if(open_request){
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.find_request_list_container, findRequestFragment)
                            .addToBackStack(null)
                            .commit();
                } else{
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    Fragment fragment = fragmentManager.findFragmentById(R.id.find_request_list_container);
                    if (fragment != null) {
                        fragmentManager.beginTransaction().remove(fragment).commit();
                    }
                }
            }
        });

        data_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_data = !open_data;
                if(open_data){
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.find_data_list_container, findDataFragment)
                            .addToBackStack(null)
                            .commit();
                } else{
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    Fragment fragment = fragmentManager.findFragmentById(R.id.find_data_list_container);
                    if (fragment != null) {
                        fragmentManager.beginTransaction().remove(fragment).commit();
                    }
                }
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}