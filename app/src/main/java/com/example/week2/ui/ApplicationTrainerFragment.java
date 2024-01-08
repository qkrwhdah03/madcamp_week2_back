package com.example.week2.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.week2.R;
import com.example.week2.databinding.FragmentApplicationTrainerBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ApplicationTrainerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ApplicationTrainerFragment extends Fragment {

    private static final int PERMISSION_CODE = 100;
    private EditText belong;
    private EditText history;
    private ImageView imageview;

    public ApplicationTrainerFragment() {
        // Required empty public constructor

    }

    ActivityResultLauncher<Intent> StartForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // 카메라에서 이미지를 성공적으로 촬영했을 때
                    Bundle extras = result.getData().getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    imageview.setImageBitmap(imageBitmap);
                }
            }
    );

    public static ApplicationTrainerFragment newInstance() {
        ApplicationTrainerFragment fragment = new ApplicationTrainerFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_application_trainer, container, false);
        belong = view.findViewById(R.id.application_belong_edittext);
        history = view.findViewById(R.id.application_history_edittext);
        imageview = view.findViewById(R.id.trainer_profile_imageview);

        if(imageview != null) {
            imageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        StartForResult.launch(camera);
                    }else {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, PERMISSION_CODE);
                    }
                }
            });
        }
        return view;
    }

    public String getBelong(){

        String res = belong.getText().toString();
        if(res == null) res = "";
        return res;
    }

    public Bitmap getImage(){
        Drawable image = imageview.getDrawable();
        if(image instanceof BitmapDrawable){
            return ((BitmapDrawable)image).getBitmap();
        } else{
            return null;
        }
    }
    public String getHistory(){
        String res = history.getText().toString();
        if(res == null) res = "";
        return res;
    }
}