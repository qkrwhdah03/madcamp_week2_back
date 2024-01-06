package com.example.week2.ui.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.week2.R;
import com.example.week2.databinding.FragmentCalendarBinding;
import com.example.week2.ui.calendar.CalendarViewModel;

import java.util.Calendar;

public class CalendarFragment extends Fragment {

    private FragmentCalendarBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CalendarViewModel calendarViewModel =
                new ViewModelProvider(this).get(CalendarViewModel.class);

        binding = FragmentCalendarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textHome;
        //CalendarViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);




        Button addButton = view.findViewById(R.id.image_9);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // AddworkFragment를 추가
                AddworkFragment addworkFragment = new AddworkFragment();
                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.fragment_container, addworkFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });




        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void showFoodOfToday(View view) {
        // View를 찾아옵니다.
        View foodOfTodayLayout = binding.getRoot().findViewById(R.id.foodoftoday);

        // View를 보이거나 숨기도록 설정합니다.
        if (foodOfTodayLayout.getVisibility() == View.VISIBLE) {
            foodOfTodayLayout.setVisibility(View.GONE);
        } else {
            foodOfTodayLayout.setVisibility(View.VISIBLE);
        }
    }

    public void showWorkOfToday(View view) {
        // View를 찾아옵니다.
        View ListOfTodayLayout = binding.getRoot().findViewById(R.id.listofworks);

        // View를 보이거나 숨기도록 설정합니다.
        if (ListOfTodayLayout.getVisibility() == View.VISIBLE) {
            ListOfTodayLayout.setVisibility(View.GONE);
        } else {
            ListOfTodayLayout.setVisibility(View.VISIBLE);
        }
    }

    /*
    public void showInbodyOfToday(View view) {
        // View를 찾아옵니다.
        View foodOfTodayLayout = binding.getRoot().findViewById(R.id.foodoftoday);

        // View를 보이거나 숨기도록 설정합니다.
        if (foodOfTodayLayout.getVisibility() == View.VISIBLE) {
            foodOfTodayLayout.setVisibility(View.GONE);
        } else {
            foodOfTodayLayout.setVisibility(View.VISIBLE);
        }
    }
    */


}