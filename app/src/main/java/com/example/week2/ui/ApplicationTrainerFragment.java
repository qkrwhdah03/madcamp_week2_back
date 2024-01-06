package com.example.week2.ui;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.week2.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ApplicationTrainerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ApplicationTrainerFragment extends Fragment {

    private EditText belong;
    private EditText history;
    private ImageView imageview;
    public ApplicationTrainerFragment() {
        // Required empty public constructor
    }

    public static ApplicationTrainerFragment newInstance() {
        ApplicationTrainerFragment fragment = new ApplicationTrainerFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_application_trainer, container, false);
        belong = view.findViewById(R.id.application_belong_edittext);
        history = view.findViewById(R.id.application_history_edittext);
        imageview = view.findViewById(R.id.trainer_profile_imageview);
        return view;
    }

    public String getBelong(){
        return belong.getText().toString();
    }

    public Bitmap getImage(){
        return null;
    }
    public String getHistory(){
        return history.getText().toString();
    }
}