package com.example.week2;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.Output;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.week2.databinding.ActivityMainBinding;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApi;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ProfileItem profile;
    private boolean IsMember;
    private boolean IsTrainer;

    ActivityResultLauncher<Intent> StartForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    String application_result = result.getData().getStringExtra("profile");
                    //Log.d("Procedure", "Main으로 전달된 application data :" + application_result);
                    ProfileItem item = ProfileItem.getItemFromJsonString(application_result);
                    //Log.d("Procedure", "Main에서 얻은 deta를 item으로 바꿈 : "+Boolean.toString(item==null));
                    IsTrainer = item.getUser().equals("Trainer");
                    if (IsTrainer) {
                        Intent trainer_intent = new Intent(this, TrainerActivity.class);
                        trainer_intent.putExtra("profile", application_result);
                        startActivity(trainer_intent);
                    } else {
                        Intent user_intent = new Intent(this, MemberActivity.class);
                        user_intent.putExtra("profile", application_result);
                        startActivity(user_intent);
                    }
                }
            }
    );

    public MainActivity() {
        /*
        IsMember IsTrainer
         true     true    --> 바로 trainer 앱으로 넘어감
         true     false   --> 바로 member 앱으로 넘어감
         false            --> 입력 폼 창으로 넘어감
         */
        //IsMember = false;
        //IsTrainer = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 위에 생성자로 tab 이동하려고 만들어놓음 (카카오로그인 생략)
        //afterLoginStartIntent();


        //Kakao 로그인 후 정보 얻어오기 + 서버에 존재성 여부 요청
        // 로그인 버튼 설정
        ImageButton kakao_login_button = (ImageButton) binding.kakaoLoginButton;
        Button logout_button = (Button) binding.kakoLogoutButton;
        Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                // oAuthToken != null 이면 로그인 성고
                if (oAuthToken != null) {
                    // 로그인 성공
                    kakao_login_button.setVisibility(View.GONE);
                    logout_button.setVisibility(View.VISIBLE);
                    // 해당 카카오 계정에 대한 프로필 정보를 요청해서 받아오고 IsMember, Istrainer 설정하기
                    UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
                        @Override
                        public Unit invoke(User user, Throwable throwable) {
                            if(user != null){
                                long userId = user.getId();
                                // 서버에 userId에 대응되는 profile 정보를 요청
                                requestProfileUsingId(userId);
                                //Toast.makeText(MainActivity.this, Long.toString(userId), Toast.LENGTH_LONG).show();
                            } else{
                                //Toast.makeText(MainActivity.this, "로그인 안됨", Toast.LENGTH_LONG).show();
                            }
                            return null;
                        }
                    });
                    // 받은 정보에 따라 설정하기
                    if(profile != null){
                        IsMember = true;
                        IsTrainer = profile.getUser().equals("Trainer");
                        Log.d("Procedure","Profile is not null");
                    } else{
                        IsMember = false;
                        Log.d("Procedure","Profile is null");
                    }
                    // Intent 실행
                    afterLoginStartIntent(profile);

                } else {
                    // 로그인 실패
                    Toast.makeText(MainActivity.this, "카카오톡 로그인이 실패했습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT);
                }
                return null;
            }
        };
        kakao_login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserApiClient.getInstance().isKakaoTalkLoginAvailable(MainActivity.this)) {
                    UserApiClient.getInstance().loginWithKakaoTalk(
                            MainActivity.this, callback);
                } else {
                    UserApiClient.getInstance().loginWithKakaoAccount(MainActivity.this, callback);
                }
            }
        });

        // 로그아웃버튼 설정
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserApiClient.getInstance().logout(new Function1<Throwable, Unit>() {
                    @Override
                    public Unit invoke(Throwable throwable) {
                        kakao_login_button.setVisibility(View.VISIBLE);
                        logout_button.setVisibility(View.GONE);
                        return null;
                    }
                });
            }
        });

       //Log.d("KeyHash", getKeyHash());

    }

    private void afterLoginStartIntent(ProfileItem item) {
        if (!IsMember) { // 멤버가 아니라면 프로필 생성 처리 후 이동
            Intent application_intent = new Intent(this, ApplicationActivity.class);
            StartForResult.launch(application_intent);
        } else {
            if (IsTrainer) {
                Intent trainer_intent = new Intent(this, TrainerActivity.class);
                trainer_intent.putExtra("profile", item.toJsonString());
                startActivity(trainer_intent);
            } else {
                Intent user_intent = new Intent(this, MemberActivity.class);
                user_intent.putExtra("profile", item.toJsonString());
                startActivity(user_intent);
            }
        }
    }

    private void requestProfileUsingId(long id){
        HttpRequestor.GET("http://172.10.7.24:80/check", Long.toString(id), new HttpCallback() {
            @Override
            public void onSuccess(String result) {
                ProfileItem item = ProfileItem.getItemFromJsonString(result);
                handleProfileResult(item);
            }
            @Override
            public void onFailure(Exception e) {
            }
        });
    }

    private void handleProfileResult(ProfileItem item){
        profile = item;
        return;
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