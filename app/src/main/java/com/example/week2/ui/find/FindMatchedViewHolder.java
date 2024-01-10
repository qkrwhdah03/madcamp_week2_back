package com.example.week2.ui.find;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week2.HttpCallback;
import com.example.week2.HttpRequestor;
import com.example.week2.ProfileItem;
import com.example.week2.R;

public class FindMatchedViewHolder extends RecyclerView.ViewHolder{

    private ImageView profile;
    private TextView name;
    private TextView belong;
    private TextView phone;

    // 기능 버튼 추가 가능

    public FindMatchedViewHolder(@NonNull View itemView){
        super(itemView);
        profile = itemView.findViewById(R.id.matched_trainer_profile_image);
        name = itemView.findViewById(R.id.matched_trainer_name);
        belong = itemView.findViewById(R.id.matched_trainer_belong);
        phone = itemView.findViewById(R.id.matched_trainer_phone);
    }

    public void bind(ProfileItem item){
        Bitmap image = item.getImage();
        if(image != null) profile.setImageBitmap(item.getImage());
        else profile.setImageResource(R.drawable.basic_profile);
        name.setText(item.getName() + "  (" + (item.getGender().equals("Female")? "여":"남")+ ")");
        belong.setText(item.getBelong());
        phone.setText(item.getPhone());
    }

}
