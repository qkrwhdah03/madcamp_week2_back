package com.example.week2.ui.dashboard;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week2.HttpCallback;
import com.example.week2.HttpRequestor;
import com.example.week2.ProfileItem;

import java.util.ArrayList;
import java.util.List;
import com.example.week2.R;

public class RequestListViewHolder extends RecyclerView.ViewHolder {

    private TextView name;
    private TextView goal;
    private TextView tags;
    private Button confirm;
    private Button dismiss;

    public RequestListViewHolder(@NonNull View itemView){
        super(itemView);
        name = itemView.findViewById(R.id.request_member_name);
        goal = itemView.findViewById(R.id.request_member_goal);
        tags = itemView.findViewById(R.id.request_member_tag);
        confirm = itemView.findViewById(R.id.trainer_confirm);
        dismiss = itemView.findViewById(R.id.trainer_dismiss);
    }
    public void bind(ProfileItem item, LiveData<ProfileItem> cur, RequestListAdapter adapter){
        name.setText(item.getName() + "  (" + (item.getGender().equals("Female")? "남":"여")+ ")");
        goal.setText(getGoal(item.getGoal()));
        tags.setText(getTag(item.getCheck()));

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cur.getValue()!=null) {
                    String url = "http://172.10.7.24:80/accept_match_request/" + cur.getValue().getKakaoid() + "/" + item.getKakaoid();
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
            }
        });

        dismiss.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(cur.getValue() != null) {
                    String url = "http://172.10.7.24:80/accept_match_request/" + cur.getValue().getKakaoid() + "/" + item.getKakaoid();
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
            }
        });
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
}
