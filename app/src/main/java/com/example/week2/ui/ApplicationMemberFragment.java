package com.example.week2.ui;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.week2.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ApplicationMemberFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ApplicationMemberFragment extends Fragment {

    private ArrayList<CheckBox> goal;
    private ArrayList<CheckBox> check;
    public ApplicationMemberFragment() {
        goal = new ArrayList<>();
        check = new ArrayList<>();
    }
    public static ApplicationMemberFragment newInstance() {
        ApplicationMemberFragment fragment = new ApplicationMemberFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_application_member, container, false);

        goal.add(view.findViewById(R.id.application_goal1_checkbox));
        goal.add(view.findViewById(R.id.application_goal2_checkbox));
        goal.add(view.findViewById(R.id.application_goal3_checkbox));
        goal.add(view.findViewById(R.id.application_goal4_checkbox));
        goal.add(view.findViewById(R.id.application_goal5_checkbox));

        check.add(view.findViewById(R.id.application_check1_checkbox));
        check.add(view.findViewById(R.id.application_check2_checkbox));
        check.add(view.findViewById(R.id.application_check3_checkbox));
        check.add(view.findViewById(R.id.application_check4_checkbox));
        check.add(view.findViewById(R.id.application_check5_checkbox));
        check.add(view.findViewById(R.id.application_check6_checkbox));
        return view;
    }

    public ArrayList<Integer> getGoal(){
        ArrayList<Integer> ret = new ArrayList<>();
        for(int i=0; i<5; ++i){
            CheckBox item = goal.get(i);
            if(item.isChecked()){
                ret.add(i);
            }
        }
        return ret;
    }

    public ArrayList<Integer> getCheck(){
        ArrayList<Integer> ret = new ArrayList<>();
        for(int i=0; i<6; ++i){
            CheckBox item = check.get(i);
            if(item.isChecked()){
                ret.add(i);
            }
        }
        return ret;
    }

}