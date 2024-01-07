package com.example.week2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.week2.databinding.ActivityApplicationBinding;
import com.example.week2.ui.ApplicationMemberFragment;
import com.example.week2.ui.ApplicationTrainerFragment;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class ApplicationActivity extends AppCompatActivity {

    private ActivityApplicationBinding binding;
    private int name_flag;
    private int phone_flag;
    private int birth_flag;
    private int gender_flag;
    private int user_flag;

    private ApplicationTrainerFragment trainerFragment;
    private ApplicationMemberFragment memberFragment;

    public ApplicationActivity(){
        name_flag = 0;
        phone_flag = 0;
        birth_flag = 0;
        gender_flag = 0;
        user_flag = 0;
        memberFragment = new ApplicationMemberFragment();
        trainerFragment = new ApplicationTrainerFragment();
    }

    private boolean check_filled(){
        return (name_flag & phone_flag & birth_flag & gender_flag & user_flag) == 1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityApplicationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 텍스트 입력이 없는 경우 경고 띄우기 + 제출 불가
        TextInputEditText name = binding.applicationNameEdittext;
        TextInputEditText phone = binding.applicationPhoneEdittext;
        TextInputEditText birth = binding.applicationBirthEdittext;
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().isEmpty()){
                    name_flag = 0;
                } else {
                    name_flag = 1;
                }
            }
        });

        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().isEmpty()){
                    phone_flag = 0;
                } else{
                    phone_flag = 1;
                }
            }
        });

        birth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().isEmpty()){
                    birth_flag = 0;
                } else{
                    birth_flag = 1;
                }
            }
        });


        // Check Box 설정
        CheckBox man = binding.applicationManCheckbox;
        CheckBox woman = binding.applicationWomanCheckbox;
        if(man != null && woman != null) {
            man.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        if (woman.isChecked()) {
                            woman.setChecked(false);
                        }
                    }
                    if (!man.isChecked() && !woman.isChecked()) gender_flag = 0;
                    else gender_flag = 1;
                }
            });

            woman.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        if (man.isChecked()) {
                            man.setChecked(false);
                        }
                        if (!man.isChecked() && !woman.isChecked()) gender_flag = 0;
                        else gender_flag = 1;
                    }
                }
            });
        }
            // 회원 구분 checkbox 설정 + 나머지 입력 폼 설정
        CheckBox trainer = binding.applicationTrainerCheckbox;
        CheckBox member = binding.applicationMemberCheckbox;
        if(trainer != null && member != null) {
            trainer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        if (member.isChecked()) {
                            member.setChecked(false);
                        }
                        // trainer application fragment를 띄움
                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.new_form_container, trainerFragment);
                        ft.commit();
                    }
                    if (!trainer.isChecked() && !member.isChecked()) user_flag = 0;
                    else user_flag = 1;
                }
            });
            member.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        if (trainer.isChecked()) {
                            trainer.setChecked(false);
                        }
                        // member application fragment를 띄움
                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.new_form_container, memberFragment);
                        ft.commit();
                    }
                    if (!trainer.isChecked() && !member.isChecked()) user_flag = 0;
                    else user_flag = 1;
                }
            });
        }

        // 제출 버튼 처리
        Button submit = binding.applicationButton;
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String json_string;
                if(check_filled()){
                    // 서버에 정보 보내주기
                    // 1. 정보 모아서 ProfileItem 만들기
                    String iname = name.getText().toString();
                    String iphone = phone.getText().toString();
                    String ibirth = birth.getText().toString();
                    String igender = man.isChecked()? "Male":"Female";
                    String iuser = trainer.isChecked()? "Trainer":"Member";
                    if(iuser.equals("Trainer")){ // Trainer
                        String ibelong = trainerFragment.getBelong();
                        String ihistory = trainerFragment.getBelong();
                        Bitmap iimage = trainerFragment.getImage();
                        ProfileItem item = ProfileItem.getTrainerItem(iname, iphone, ibirth, igender, iuser, ibelong, iimage, ihistory);
                        json_string = item.toJsonString();
                    }
                    else{ // Member 정보 처리
                        ArrayList<Integer> igoal = memberFragment.getGoal();
                        ArrayList<Integer> icheck = memberFragment.getCheck();
                        ProfileItem item = ProfileItem.getMemberItem(iname, iphone, ibirth, igender, iuser, igoal, icheck);
                        json_string = item.toJsonString();
                    }
                    // 3. 서버로 전송

                    // 4. 메인 액티비티로 반환
                    Intent intent = new Intent(ApplicationActivity.this, MainActivity.class);
                    Boolean result = iuser.equals("Trainer");
                    intent.putExtra("isTrainer",Boolean.toString(result)); // 트레이너인지 정보를 전달
                    setResult(RESULT_OK, intent);
                    finish();
                } else{
                    Toast.makeText(ApplicationActivity.this, "입력하지 않은 칸이 있습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}