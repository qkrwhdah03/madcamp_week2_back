package com.example.week2.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.week2.R;
public class MatchedFragment extends Fragment {


    public MatchedFragment() {
        // Required empty public constructor
    }

    public static MatchedFragment newInstance() {
        MatchedFragment fragment = new MatchedFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_matched, container, false);
    }
}