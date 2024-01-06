package com.example.week2;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.week2.databinding.ActivityMainBinding;
import com.example.week2.databinding.ActivityTrainerBinding;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private boolean IsMember;
    private boolean IsTrainer;
    ActivityResultLauncher<Intent> StartForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == RESULT_OK){
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
    );
    ActivityResultLauncher<Intent> AfterLoginForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == RESULT_OK){
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
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       //Log.d("KeyHash", getKeyHash());

        // 로그인 버튼 설정
        /*
        ImageButton kakao_login_button = (ImageButton) binding.kakaoLoginButton;
        kakao_login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
         */
        
        // Kakao 로그인 후, 가입 정보 없다 -> 가입
        //                가입 정보 있다 -> 회원, 트레이너 정보에 따라 Activity 이덩

        //Kakao 로그인 후 정보 얻어오기 + 서버에 존재성 여부 요청

    }


    /*
    public String getKeyHash(){
        try{
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            if(packageInfo == null) return null;
            for(Signature signature: packageInfo.signatures){
                try{
                    MessageDigest md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                    return android.util.Base64.encodeToString(md.digest(), Base64.NO_WRAP);
                }catch (NoSuchAlgorithmException e){
                    Log.w("getKeyHash", "Unable to get MessageDigest. signature="+signature, e);
                }
            }
        }catch(PackageManager.NameNotFoundException e){
            Log.w("getPackageInfo", "Unable to getPackageInfo");
        }
        return null;
        }
     */
}