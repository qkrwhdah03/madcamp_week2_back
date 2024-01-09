package com.example.week2;

import android.os.Bundle;
import android.util.Log;

import com.example.week2.ui.dashboard.DashboardViewModel;
import com.example.week2.ui.home.HomeViewModel;
import com.example.week2.ui.notifications.NotificationsViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.week2.databinding.ActivityTrainerBinding;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class TrainerActivity extends AppCompatActivity {

    private ActivityTrainerBinding binding;
    private ProfileItem item;
    private NotificationsViewModel notificationsViewModel;
    private HomeViewModel homeViewModel;
    private ArrayList<ProfileItem> data;
    private ArrayList<ProfileItem> matched;
    private ArrayList<ProfileItem> request;

    public TrainerActivity(){
        data = new ArrayList<>();
        matched = new ArrayList<>();
        request = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTrainerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        // 로그인된 프로필 얻어오기
        item = ProfileItem.getItemFromJsonString(getIntent().getStringExtra("profile"));
        handleData(getIntent().getStringExtra("profiles"));

        // Notification Fragment로 전달
        notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);
        notificationsViewModel.setProfileItem(item);

        // HomeFragement로 도 전달
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.setProfileItem(item);
        homeViewModel.setProfileList(data);

        // Dashboard로 정보 전달
        DashboardViewModel dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        dashboardViewModel.setMatched(matched);
        dashboardViewModel.setRequest(request);

        // 위에 바 숨기기
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        // 네비게이션바 설정
        BottomNavigationView navView = findViewById(R.id.nav_view);
        //Passing each menu ID as a set of Ids because each
        //menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_trainer);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    private void handleData(String result){

        // data
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
        JsonArray jsonArray = jsonObject.get("profiles").getAsJsonArray();
        for(int i=0; i<jsonArray.size(); i++){
            data.add(ProfileItem.getItemFromJsonObject(jsonArray.get(i).getAsJsonObject()));
        }

        // matched
        jsonArray = jsonObject.get("matches").getAsJsonArray();
        for(int i=0; i<jsonArray.size(); i++){
            matched.add(ProfileItem.getItemFromJsonObject(jsonArray.get(i).getAsJsonObject()));
        }
        // request
        jsonArray = jsonObject.get("requests").getAsJsonArray();
        for(int i=0; i<jsonArray.size(); i++){
            request.add(ProfileItem.getItemFromJsonObject(jsonArray.get(i).getAsJsonObject()));
        }

    }
}