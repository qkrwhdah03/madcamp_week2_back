package com.example.week2.ui.dashboard;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.week2.R;
import com.example.week2.databinding.FragmentDashboardBinding;
import com.example.week2.ui.MatchedFragment;
import com.example.week2.ui.RequestFragment;

import java.time.DateTimeException;
import java.util.Calendar;
import java.util.Date;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private MatchedFragment matchedFragment;
    private RequestFragment requestFragment;

    private boolean matched_open;
    private boolean request_open;

    public DashboardFragment(){
        matched_open = true;
        request_open = false;
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //끼워 넣을 Fragment 정의
        matchedFragment = new MatchedFragment();
        requestFragment = new RequestFragment();

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