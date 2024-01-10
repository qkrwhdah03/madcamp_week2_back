package com.example.week2.ui.profile;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.week2.ProfileItem;
import com.example.week2.R;
import com.example.week2.databinding.FragmentProfileBinding;
import com.example.week2.ui.profile.ProfileViewModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private LiveData<ProfileItem> item;
    private ProfileViewModel profileViewModel;

    private ImageView profile;
    private TextView name;
    private TextView phone;
    private TextView birth;
    private TextView gender;
    private TextView goal;
    private TextView tags;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        profileViewModel = new ViewModelProvider(getActivity()).get(ProfileViewModel.class);
        item = profileViewModel.getItem();
        ProfileItem cur = item.getValue();

        // xml 파일 연결해서 값 설정해주기
        name = binding.memberProfileNameTextedit;
        phone = binding.memberProfilePhoneTextedit;
        birth = binding.memberProfileBirthEdittext;
        gender = binding.memberProfileGenderEdittext;
        goal = binding.memberProfileGoalEdittext;
        tags = binding.memberProfileTagEdittext;
        profile = binding.memberProfileImage;

        // 값 설정
        Bitmap image = cur.getImage();
        if(image != null) profile.setImageBitmap(image);
        else profile.setImageResource(R.drawable.basic_profile);

        name.setText(cur.getName());
        phone.setText(cur.getPhone());
        birth.setText(cur.getBirth());
        gender.setText(cur.getGender());
        goal.setText(getGoal(cur.getGoal()));
        tags.setText(getTag(cur.getCheck()));
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public String getGoal(ArrayList<Integer> goal_id){
        List<String> list = new ArrayList<>();
        list.add("체력 기르기");
        list.add("증량");
        list.add("감량");
        list.add("바디 프로필");
        list.add("대회 준비");

        String ret = "";
        for(Integer item : goal_id){
            ret += ",  ";
            ret += list.get(item);
        }
        return ret.substring(1);
    }

    public String getTag(ArrayList<Integer> tag_id){
        Boolean trainer = false;
        Boolean  time = false;
        Boolean first = true;
        String ret = "";
        List<String> list = new ArrayList<>();
        list.add("남성");
        list.add("여성");
        list.add("PT 경험");
        list.add("1~2회");
        list.add("3~4회");
        list.add("5회 이상");

        for(Integer item : tag_id){
            if(item == 0){
                trainer = true;
                ret += list.get(0);
            }
            if(item == 1){
                if(trainer == false){
                    trainer = true;
                    ret += list.get(1);
                }
                else ret += (", " + list.get(1));
            }
            if(first && trainer){
                first = false;
                ret += " 트레이너 선호해요!\n";
            }
            if(item == 2){
                ret += "PT 경험 있어요!\n";
            }
            if(item >= 3){
                if(!time){
                    time = true;
                    ret += "주 ";
                    ret += list.get(item);
                }
                else{
                    ret += ", ";
                    ret += list.get(item);
                }
            }
        }
        ret+="\n";
        if(ret.length() == 0){
            ret = " ";
        }
        return ret.substring(0, ret.length()-1);
    }
}