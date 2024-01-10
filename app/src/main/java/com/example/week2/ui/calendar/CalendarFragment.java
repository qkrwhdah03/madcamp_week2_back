package com.example.week2.ui.calendar;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.week2.R;

import java.util.Calendar;

public class CalendarFragment extends Fragment {

    private static final int REQUEST_IMAGE_GALLERY = 1;

    private ImageView bobMorningImageView;
    private ImageView bobLunchImageView;
    private ImageView bobDinnerImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);



        // CalendarView 초기화
        View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);
        CalendarView calendarView = rootView.findViewById(R.id.calendarView);

        // 오늘 날짜로 설정
        Calendar calendar = Calendar.getInstance();
        calendarView.setDate(calendar.getTimeInMillis());



        bobMorningImageView = view.findViewById(R.id.bob_morning);
        bobLunchImageView = view.findViewById(R.id.bob_lunch);
        bobDinnerImageView = view.findViewById(R.id.bob_dinner);

        bobMorningImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery(REQUEST_IMAGE_GALLERY);
            }
        });

        bobLunchImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery(REQUEST_IMAGE_GALLERY);
            }
        });

        bobDinnerImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery(REQUEST_IMAGE_GALLERY);
            }
        });

        return view;
    }

    private void openGallery(int requestCode) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_GALLERY && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                Uri selectedImage = data.getData();

                // Glide를 사용하여 선택한 이미지를 ImageView에 설정
                if (bobMorningImageView != null) {
                    Glide.with(this)
                            .load(selectedImage)
                            .into(bobMorningImageView);
                }
            }
        }
    }
}