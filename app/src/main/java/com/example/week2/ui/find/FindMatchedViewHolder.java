package com.example.week2.ui.find;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
    private Button phone_call;
    private Button message;
    public FindMatchedViewHolder(@NonNull View itemView){
        super(itemView);
        profile = itemView.findViewById(R.id.matched_trainer_profile_image);
        name = itemView.findViewById(R.id.matched_trainer_name);
        belong = itemView.findViewById(R.id.matched_trainer_belong);
        phone = itemView.findViewById(R.id.matched_trainer_phone);
        phone_call = itemView.findViewById(R.id.member_func1);
        message = itemView.findViewById(R.id.member_func2);
    }

    public void bind(ProfileItem item){
        Bitmap image = item.getImage();
        if(image != null) profile.setImageBitmap(item.getImage());
        else profile.setImageResource(R.drawable.basic_profile);
        name.setText(item.getName() + "  (" + (item.getGender().equals("Female")? "여":"남")+ ")");
        belong.setText(item.getBelong());
        phone.setText(item.getPhone());

        phone_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(ContextCompat.checkSelfPermission(itemView.getContext(), Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions((Activity) itemView.getContext(), new String[]{android.Manifest.permission.CALL_PHONE},1);
                    }
                    else startCall(item);
                }
                else startCall(item);
            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(itemView.getContext(), Manifest.permission.SEND_SMS)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) itemView.getContext(),
                            new String[]{Manifest.permission.SEND_SMS}, 2);
                } else {
                    sendMessage(item);
                }
            }
        });
    }

    void startCall(ProfileItem item) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        String phoneNumber = "tel:" + item.getPhone();
        intent.setData(Uri.parse(phoneNumber));
        itemView.getContext().startActivity(intent);
    }

    void sendMessage(ProfileItem item){
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("vnd.android-dir/mms-sms");
        sendIntent.putExtra("address", item.getPhone());
        sendIntent.putExtra("sms_body", "");
        itemView.getContext().startActivity(sendIntent);
    }

}
