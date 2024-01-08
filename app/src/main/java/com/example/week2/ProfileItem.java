package com.example.week2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ProfileItem {
    private String kakaoid;
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
        return new ProfileItem(name, phone, birth, gender, user, "", null, "", goal, check);
    }

    public void setKakaoid(String kakaoid) {
        this.kakaoid = kakaoid;
    }

    static ProfileItem getItemFromJsonString(String json){
        JsonObject jsonObject;
        if(json.equals("")){ // 만약 프로필이 없다면..
            return null;
        }
        try {
            Gson gson = new Gson();
            jsonObject = gson.fromJson(json, JsonObject.class);
            //jsonObject = (JsonObject) JsonParser.parseString(json);
        } catch (Exception e){
            Log.d("Procedure", "Not a valid json String");
            return null;
        }
        return getItemFromJsonObject(jsonObject);
    }

    static ProfileItem getItemFromJsonObject(JsonObject obj){
        String okakaoid = obj.get("kakaoid").getAsString();
        String oname = obj.get("name").getAsString();
        String ophone = obj.get("phone").getAsString();
        String obirth = obj.get("birthdate").getAsString();
        String ogender = obj.get("gender").getAsString();
        String ouser = obj.get("user").getAsString();
        String obelong = obj.get("belong").getAsString();
        String ohistory = obj.get("history").getAsString();
        String o_string_image = obj.get("image").getAsString();
        Bitmap oimage;
        // oimage를 bitmap으로 변환
        if(o_string_image.length()==0){ oimage = null; }
        else{
            byte[] decodedByteArray = Base64.decode(o_string_image, Base64.DEFAULT);
            oimage = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
        }


        ArrayList<Integer> ocheck  = new ArrayList<>();
        JsonArray ja = obj.get("tag").getAsJsonArray();
        if(ja != null){
            for(int i=0; i<ja.size(); ++i){
                ocheck.add(ja.get(i).getAsInt());
            }
        }
        ArrayList<Integer> ogoal  = new ArrayList<>();
        JsonArray ja2 = obj.get("goal").getAsJsonArray();
        if(ja2 != null){
            for(int i=0; i<ja2.size(); ++i){
                ogoal.add(ja.get(i).getAsInt());
            }
        }
        ProfileItem item =new ProfileItem(oname, ophone, obirth, ogender, ouser, obelong, oimage, ohistory, ogoal, ocheck);
        item.setKakaoid(okakaoid);
        return item;
    }

    public String getKakaoid(){return kakaoid;}
    public String getName(){return name;}
    public String getPhone(){return phone;}
    public String getBirth(){return birth;}
    public String getGender(){return gender;}
    public String getUser(){return user;}
    public String getBelong(){return belong;}
    public String getHistory(){return history;}
    public ArrayList<Integer> getGoal(){return goal;}
    public ArrayList<Integer> getCheck(){return check;}

    public Bitmap getImage(){
        return image;
    }

    public String toJsonString(){ // 이미지는 포함하지 않는다.
        JsonObject item = new JsonObject();
        try {
            item.addProperty("kakaoid", kakaoid);
            item.addProperty("name", name);
            item.addProperty("phone", phone);
            item.addProperty("birthdate", birth);
            item.addProperty("gender", gender);
            item.addProperty("belong", belong);
            item.addProperty("history", history);
            item.addProperty("user", user);

            JsonArray goal_json = new JsonArray();
            if(goal != null) {
                for (int i = 0; i < goal.size(); ++i) {
                    goal_json.add(goal.get(i));
                }
            }
            item.add("goal", goal_json);

            JsonArray check_json = new JsonArray();
            if(check != null) {
                for (int i = 0; i < check.size(); ++i) {
                    check_json.add(check.get(i));
                }
            }
            item.add("tag", check_json);

            if(image != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                String encoded_image = Base64.encodeToString(byteArray, Base64.DEFAULT);
                item.addProperty("image", encoded_image);
            } else item.addProperty("image", "");

        } catch (Exception e){
            e.printStackTrace();
        }
        return item.toString();
    }
}
