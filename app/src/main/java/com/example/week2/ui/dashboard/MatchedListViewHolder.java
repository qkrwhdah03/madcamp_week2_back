package com.example.week2.ui.dashboard;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week2.ProfileItem;
import com.example.week2.R;

import java.util.ArrayList;
import java.util.List;

public class MatchedListViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    TextView phone;
    TextView goal;
    public MatchedListViewHolder(@NonNull View itemView){
        super(itemView);
        name = itemView.findViewById(R.id.matched_member_name);
        phone = itemView.findViewById(R.id.matched_member_phone);
        goal = itemView.findViewById(R.id.matched_member_goal);
    }
    public void bind(ProfileItem item){
        name.setText(item.getName() + "  (" + (item.getGender().equals("Female")? "여":"남")+ ")");
        phone.setText(item.getPhone());
        goal.setText(getGoal(item.getGoal()));
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
