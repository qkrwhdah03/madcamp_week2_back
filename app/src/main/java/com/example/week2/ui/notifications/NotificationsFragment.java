package com.example.week2.ui.notifications;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.week2.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        // 서버에서 프로필 데이터 받아오고 그걸로 초기화 해주기

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // 이름
        EditText trainer_profile_name_edittext =  binding.trainerProfileNameTextedit;
        if(trainer_profile_name_edittext != null){
            trainer_profile_name_edittext.setText("서버에서 받은 이름");
            //trainer_profile_name_edittext.setEnabled(false);
            trainer_profile_name_edittext.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s) {
                    trainer_profile_name_edittext.clearFocus();
                    // 서버로 전달하기 - 이름
                }
            });
        }

        // 전화번호
        EditText trainer_profile_phone_edittext = binding.trainerProfilePhoneTextedit;
        if(trainer_profile_phone_edittext != null){
            trainer_profile_phone_edittext.setText("이게 입력");
            //trainer_profile_phone_edittext.setEnabled(false);
            trainer_profile_phone_edittext.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
                @Override
                public void afterTextChanged(Editable s) {
                    trainer_profile_phone_edittext.clearFocus();
                    // 서버로 전달하기 - 전화번호
                }
            });
        }

        // 생일
        EditText trainer_profile_birth_edittext=  binding.trainerProfileBirthEdittext;
        if(trainer_profile_birth_edittext != null){
            trainer_profile_birth_edittext.setText("서버에서 받은 생일");
            //trainer_profile_birth_edittext.setEnabled(false);
            trainer_profile_birth_edittext.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s) {
                    trainer_profile_birth_edittext.clearFocus();
                    // 서버로 전달 - 생일
                }
            });
        }

        // 소속
        EditText trainer_profile_belong_edittext = binding.trainerProfileBelongEdittext;
        if(trainer_profile_belong_edittext != null){
            trainer_profile_belong_edittext.setText("서버에서 받은 소속");
            // trainer_profile_belong_edittext.setEnabled(false);
            trainer_profile_belong_edittext.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s) {
                    trainer_profile_belong_edittext.clearFocus();
                    // 서버로 전달하기 - 소속
                }
            });
        }

        // 경력
        ListView trainer_profile_history_listview = binding.trainerProfileHistoryListview;
        if(trainer_profile_history_listview != null){
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}