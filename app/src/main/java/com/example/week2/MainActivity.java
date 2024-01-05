package com.example.week2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private boolean IsMember;
    private boolean IsTrainer;

    public MainActivity(){
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

        }
        if(IsTrainer){
            Intent trainer_intent = new Intent(this, TrainerActivity.class);
            startActivity(trainer_intent);
            finish();
        }
        else{
            Intent user_intent = new Intent(this, MemberActivity.class);
            startActivity(user_intent);
            finish();
        }
    }
}