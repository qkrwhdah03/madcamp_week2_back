package com.example.week2.ui.find;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week2.HttpCallback;
import com.example.week2.HttpRequestor;
import com.example.week2.ProfileItem;
import com.example.week2.R;

public class FindRequestViewHolder extends RecyclerView.ViewHolder{

    private ImageView profile;
    private TextView name;
    private TextView belong;
    private TextView history;
    private Button confirm;
    private Button dismiss;

    public FindRequestViewHolder(@NonNull View itemView){
        super(itemView);
        profile = itemView.findViewById(R.id.request_trainer_profile_image);
        name = itemView.findViewById(R.id.request_trainer_name);
        belong = itemView.findViewById(R.id.request_trainer_belong);
        history = itemView.findViewById(R.id.request_trainer_history);
        confirm = itemView.findViewById(R.id.member_confirm);
        dismiss = itemView.findViewById(R.id.member_dismiss);
    }

    public void bind(ProfileItem item, ProfileItem cur, FindRequestAdapter adapter){


        Bitmap image = item.getImage();
        if(image != null) profile.setImageBitmap(item.getImage());
        else profile.setImageResource(R.drawable.basic_profile);

        name.setText(item.getName() + "  (" + (item.getGender().equals("Female")? "여":"남")+ ")");
        belong.setText(item.getBelong());
        history.setText(item.getHistory());


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://172.10.7.24:80/accept_match_request/"+ cur.getKakaoid() + "/" + item.getKakaoid();
                HttpRequestor.GET(url, "1", new HttpCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Log.d("Procedure", "Accept Match Request Success");
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                adapter.remove(item);
                            }
                        });
                    }
                    @Override
                    public void onFailure(Exception e) {
                        Log.d("Procedure", "Accept Match Request Failed");
                    }
                });
            }
        });

        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://172.10.7.24:80/accept_match_request/"+ cur.getKakaoid() + "/" + item.getKakaoid();
                HttpRequestor.GET(url, "0", new HttpCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Log.d("Procedure", "Dismiss Match Request Success");

                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                adapter.remove(item);
                            }
                        });
                    }
                    @Override
                    public void onFailure(Exception e) {
                        Log.d("Procedure", "Dismiss Match Request Failed");
                    }
                });
            }
        });
    }
}
