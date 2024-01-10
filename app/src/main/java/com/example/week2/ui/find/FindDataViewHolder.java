package com.example.week2.ui.find;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week2.HttpCallback;
import com.example.week2.HttpRequestor;
import com.example.week2.ProfileItem;
import com.example.week2.R;
public class FindDataViewHolder extends RecyclerView.ViewHolder{

    private View layout;
    private ImageView profile;
    private TextView name;
    private TextView belong;
    private TextView history;
    public FindDataViewHolder(@NonNull View itemView){
        super(itemView);
        layout = itemView.findViewById(R.id.find_data_list_item);
        profile = itemView.findViewById(R.id.trainer_profile_image);
        name = itemView.findViewById(R.id.trainer_name);
        belong = itemView.findViewById(R.id.trainer_belong);
        history = itemView.findViewById(R.id.trainer_history);
    }
    public void bind(ProfileItem item, ProfileItem cur){
        Bitmap image = item.getImage();
        if(image != null) profile.setImageBitmap(item.getImage());
        else profile.setImageResource(R.drawable.basic_profile);

        name.setText(item.getName() + "  (" + (item.getGender().equals("Female")? "여":"남")+ ")");
        belong.setText(item.getBelong());
        history.setText(item.getHistory());

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmDialog(item, cur);
            }
        });
    }

    public void showConfirmDialog(ProfileItem item, ProfileItem cur) {
        AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
        builder.setTitle("매칭 요청");
        builder.setMessage(item.getName() + "님에게 요청하시겠습니까?");

        builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    HttpRequestor.GET("http://172.10.7.24:80/send_match_request/" + cur.getKakaoid(), item.getKakaoid(), new HttpCallback() {
                        @Override
                        public void onSuccess(String result) {
                            Log.d("Procedure", "ConfirmDialog : Request for match done..");
                            ((Activity) layout.getContext()).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(layout.getContext(), "이미 신청 완료했습니다.", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void onFailure(Exception e) {
                            Log.d("Procedure", "ConfirmDialog : Request for match fail..");
                        }
                    });
                }
            }
        });

        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 다이얼 로그 닫기
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
