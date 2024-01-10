package com.example.week2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.week2.ui.find.FindViewModel;
import com.example.week2.ui.profile.ProfileViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.week2.databinding.ActivityMemberBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class MemberActivity extends AppCompatActivity {

    private ActivityMemberBinding binding;
    private ProfileItem item;

    private FindViewModel findViewModel;
    private ProfileViewModel profileViewModel;

    private ArrayList<ProfileItem> data;
    private ArrayList<ProfileItem> matched;
    private ArrayList<ProfileItem> request;

    public MemberActivity(){
        data = new ArrayList<>();
        matched = new ArrayList<>();
        request = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMemberBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        // 위에 바 숨기기
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        // 로그인된 프로필 얻어오기
        item = ProfileItem.getItemFromJsonString(getIntent().getStringExtra("profile"));
        handleData(getIntent().getStringExtra("profiles"));


        // Floating Button
        FloatingActionButton reload = binding.memberAppReload;
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reloadRequest();
            }
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_find, R.id.navigation_calendar, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_member);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    private void handleData(String result){

        // Init
        data = new ArrayList<>();
        matched = new ArrayList<>();
        request = new ArrayList<>();

        // data
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
        JsonArray jsonArray = jsonObject.get("profiles").getAsJsonArray();
        Log.d("Procedure", "Member Data : "+jsonObject.get("profiles"));
        for(int i=0; i<jsonArray.size(); i++){
            data.add(ProfileItem.getItemFromJsonObject(jsonArray.get(i).getAsJsonObject()));
        }

        // matched
        jsonArray = jsonObject.get("matches").getAsJsonArray();
        Log.d("Procedure", "Member matches : "+jsonObject.get("matches"));
        for(int i=0; i<jsonArray.size(); i++){
            matched.add(ProfileItem.getItemFromJsonObject(jsonArray.get(i).getAsJsonObject()));
        }
        // request
        jsonArray = jsonObject.get("requests").getAsJsonArray();
        Log.d("Procedure", "Member requests : "+jsonObject.get("requests"));
        for(int i=0; i<jsonArray.size(); i++){
            request.add(ProfileItem.getItemFromJsonObject(jsonArray.get(i).getAsJsonObject()));
        }

        // Viewmodel을 통해서 데이터 전달
        findViewModel = new ViewModelProvider(this).get(FindViewModel.class);
        findViewModel.setItem(item);
        findViewModel.setData(data);
        findViewModel.setMatched(matched);
        findViewModel.setRequest(request);

        // Profile
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        profileViewModel.setItem(item);

    }

    private void reloadRequest(){
        HttpRequestor.GET("http://172.10.7.24:80/profiles", item.getKakaoid(), new HttpCallback() {
            @Override
            public void onSuccess(String results) {
                Log.d("Procedure", "Request Profiles Result : " + results);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        handleData(results);
                    }
                });
            }
            @Override
            public void onFailure(Exception e) {
                Log.d("Procedure", "Request Profiles Fail");
            }
        });
    }
}