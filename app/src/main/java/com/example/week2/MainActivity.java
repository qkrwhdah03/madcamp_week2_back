package com.example.week2;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private boolean IsMember;
    private boolean IsTrainer;

    ActivityResultLauncher<Intent> StartForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == RESULT_OK){
                    Intent intent = result.getData();
                    Log.d("MainActivity", intent.getStringExtra("result"));
                }
            }
    );

    public MainActivity(){
        /*
        IsMember IsTrainer
         true     true    --> 바로 trainer 앱으로 넘어감
         true     false   --> 바로 member 앱으로 넘어감
         false            --> 입력 폼 창으로 넘어감
         */
        IsMember = true;
        IsTrainer = true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Kakao 로그인 후, 가입 정보 없다 -> 가입
        //                가입 정보 있다 -> 회원, 트레이너 정보에 따라 Activity 이덩

        //(1) Kakao 로그인 후 정보 얻어오기 + 서버에 존재성 여부 요청
        if(!IsMember){ // 멤버가 아니라면 프로필 생성 처리 후 이동
            Intent application_intent = new Intent(this, ApplicationActivity.class);
            StartForResult.launch(application_intent);
        }
        else {
            if (IsTrainer) {
                Intent trainer_intent = new Intent(this, TrainerActivity.class);
                startActivity(trainer_intent);
                finish();
            } else {
                Intent user_intent = new Intent(this, MemberActivity.class);
                startActivity(user_intent);
                finish();
            }
        }
    }
}