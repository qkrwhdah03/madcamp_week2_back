package com.example.week2.ui.dashboard;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week2.ProfileItem;
import com.example.week2.R;

import java.util.ArrayList;
import java.util.List;

public class MatchedListViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    TextView phone;
    TextView goal;

    Button phone_call;
    Button message;
    public MatchedListViewHolder(@NonNull View itemView){
        super(itemView);
        name = itemView.findViewById(R.id.matched_member_name);
        phone = itemView.findViewById(R.id.matched_member_phone);
        goal = itemView.findViewById(R.id.matched_member_goal);
        phone_call = itemView.findViewById(R.id.trainer_func1);
        message = itemView.findViewById(R.id.trainer_func2);
    }
    public void bind(ProfileItem item){
        name.setText(item.getName() + "  (" + (item.getGender().equals("Female")? "여":"남")+ ")");
        phone.setText(item.getPhone());
        goal.setText(getGoal(item.getGoal()));
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

    public String getGoal(ArrayList<Integer> goal_id){
        List<String> list = new ArrayList<>();
        list.add("체력 기르기");
        list.add("증량");
        list.add("감량");
        list.add("바디 프로필");
        list.add("대회 준비");

        String ret = "";
        for(Integer item : goal_id){
            ret += ",  ";
            ret += list.get(item);
        }
        return ret.substring(1);
    }
}
