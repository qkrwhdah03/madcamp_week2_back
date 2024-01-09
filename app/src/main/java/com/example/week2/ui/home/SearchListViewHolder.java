package com.example.week2.ui.home;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week2.HttpCallback;
import com.example.week2.HttpRequestor;
import com.example.week2.R;
import com.example.week2.ProfileItem;

import java.util.ArrayList;
import java.util.List;

public class SearchListViewHolder extends RecyclerView.ViewHolder{

    private TextView name;
    private TextView goal;
    private TextView tags;
    private View container;
    public SearchListViewHolder(@NonNull View view){
        super(view);
        name = view.findViewById(R.id.member_name);
        goal = view.findViewById(R.id.member_goal);
        tags = view.findViewById(R.id.member_tag);
        container = view.findViewById(R.id.search_list_item);
    }

    public void showConfirmDialog(ProfileItem item, ProfileItem cur){
        AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
        builder.setTitle("회원 등록 요청");
        builder.setMessage(item.getName()+ "님에게 요청하시겠습니까?");

        builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    HttpRequestor.GET("http://172.10.7.24:80/send_match_request/" + cur.getKakaoid(), item.getKakaoid(), new HttpCallback() {
                        @Override
                        public void onSuccess(String result) {
                            Log.d("Procedure", "ConfirmDialog : Request for match done..");
                            if(result.equals("fail")){
                                Toast.makeText(container.getContext(), "이미 신청 완료했습니다.", Toast.LENGTH_SHORT).show();
                            }
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

    public String getGoal(ArrayList<Integer> goal_id){
        List<String> list = new ArrayList<>();
        list.add("체력 기르기");
        list.add("증량");
        list.add("감량");
        list.add("대회 준비");

        String ret = "";
        for(Integer item : goal_id){
            ret += ",  ";
            ret += list.get(item);
        }
        return ret.substring(1);
    }

    public String getTag(ArrayList<Integer> tag_id){
        Boolean trainer = false;
        Boolean  time = false;
        String ret = "";
        List<String> list = new ArrayList<>();
        list.add("남성");
        list.add("여성");
        list.add("PT 경험");
        list.add("1~2회");
        list.add("3~4회");
        list.add("5회 이상");

        for(Integer item : tag_id){
            if(item == 0){
                trainer = true;
                ret += list.get(0);
            }
            if(item == 1){
                if(trainer == false){
                    trainer = true;
                    ret += list.get(1);
                }
                else ret += (", " + list.get(1));
                if(trainer) ret += " 트레이너 선호해요!\n";

            }
            if(item == 2){
                ret += "PT 경험 있어요!\n";
            }
            if(item >= 3){
                if(!time){
                    time = true;
                    ret += "주 ";
                    ret += list.get(item);
                }
                else{
                    ret += ", ";
                    ret += list.get(item);
                }
                if(item == 5){
                    ret += "\n";
                }
            }
        }
        if(ret.length() == 0){
            ret = " ";
        }
        return ret.substring(0, ret.length()-1);
    }
    public void bind(ProfileItem item, ProfileItem cur){
        name.setText(item.getName() + "  (" + (item.getGender().equals("Female")? "남":"여")+ ")");
        goal.setText(getGoal(item.getGoal()));
        tags.setText(getTag(item.getCheck()));
        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmDialog(item, cur);
            }
        });
    }
}
