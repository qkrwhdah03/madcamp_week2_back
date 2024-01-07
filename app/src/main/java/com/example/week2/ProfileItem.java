package com.example.week2;

import android.graphics.Bitmap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;

public class ProfileItem {
    private String name;
    private String phone;
    private String birth;
    private String gender;
    private String user;
    private String belong;
    private Bitmap image;
    private String history;
    private ArrayList<Integer> goal;
    private ArrayList<Integer> check;

    public ProfileItem(String name, String phone, String birth, String gender, String user, String belong, Bitmap image, String history,
                       ArrayList<Integer> goal, ArrayList<Integer> check){
        this.name = name;
        this.phone = phone;
        this.birth = birth;
        this.gender = gender;
        this.user = user;
        this.belong = belong;
        this.image = image;
        this.history = history;
        this.goal = goal;
        this.check = check;
    }

    static ProfileItem getTrainerItem(String name, String phone, String birth, String gender,
                                      String user, String belong, Bitmap image, String history){
        return new ProfileItem(name, phone, birth, gender, user, belong, image, history, new ArrayList<Integer>(), new ArrayList<Integer>());
    }
    static ProfileItem getMemberItem(String name, String phone, String birth, String gender,
                                     String user, ArrayList<Integer> goal, ArrayList<Integer> check){
        return new ProfileItem(name, phone, birth, gender, user, null, null, null, goal, check);
    }

    public String toJsonString(){
        JSONObject item = new JSONObject();
        try {
            item.put("name", name);
            item.put("phone", phone);
            item.put("birthdate", birth);
            item.put("gender", gender);
            item.put("belong", belong);
            item.put("history", history);
            item.put("user", user);

            JSONArray goal_json = new JSONArray();
            for(int i=0; i<goal.size(); ++i){
                goal_json.put(goal.get(i));
            }
            item.put("goal", goal_json);

            JSONArray check_json = new JSONArray();
            for(int i=0; i<check.size(); ++i){
                check_json.put(check.get(i));
            }
            item.put("tag", check_json);

            //item.put("image", );

        } catch (Exception e){
            e.printStackTrace();
        }
        return item.toString();
    }
}
