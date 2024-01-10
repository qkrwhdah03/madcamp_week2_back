package com.example.week2.ui.dashboard;

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
import com.example.week2.R;
import com.example.week2.databinding.FragmentDashboardBinding;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private DashboardViewModel dashboardViewModel;
    private MatchedFragment matchedFragment;
    private LiveData<ArrayList<ProfileItem>> matched_list;
    private RequestFragment requestFragment;
    private MutableLiveData<ArrayList<ProfileItem>> request_list;
    private LiveData<ProfileItem> item;

    private boolean matched_open;
    private boolean request_open;

    public DashboardFragment(){
        matched_open = true;
        request_open = false;
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // View model에서 데이터 얻어오기
        dashboardViewModel =  new ViewModelProvider(requireActivity()).get(DashboardViewModel.class);
        matched_list = dashboardViewModel.getMatched();
        request_list = dashboardViewModel.getRequest();
        item = dashboardViewModel.getItem();

        //끼워 넣을 Fragment 정의
        matchedFragment = new MatchedFragment(matched_list);
        requestFragment = new RequestFragment(request_list, item);

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.match_container, matchedFragment)
                .addToBackStack(null)
                .commit();

        View match_click = binding.matchLinearLayout;
        match_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matched_open = !matched_open;
                if(matched_open){
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.match_container, matchedFragment)
                            .addToBackStack(null)
                            .commit();
                } else{
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    Fragment fragment = fragmentManager.findFragmentById(R.id.match_container);
                    if (fragment != null) {
                        fragmentManager.beginTransaction().remove(fragment).commit();
                    }
                }
            }
        });

        View request_click = binding.requestLinearLayout;
        request_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               request_open = !request_open;
               if(request_open){
                   getActivity().getSupportFragmentManager()
                           .beginTransaction()
                           .replace(R.id.request_container, requestFragment)
                           .addToBackStack(null)
                           .commit();
               } else{
                   FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                   Fragment fragment = fragmentManager.findFragmentById(R.id.request_container);
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
