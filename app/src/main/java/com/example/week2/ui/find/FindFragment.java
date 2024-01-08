package com.example.week2.ui.find;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week2.ProfileAdapter;
import com.example.week2.R;
import com.example.week2.databinding.FragmentHomeBinding;
import com.example.week2.ui.home.HomeViewModel;
import com.example.week2.models.Profile;
import com.example.week2.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private ProfileAdapter profileAdapter;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);



        recyclerView = root.findViewById(R.id.recyclerView); // RecyclerView 초기화
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        // Retrofit을 사용하여 프로필 데이터를 가져옴
        ProfileApi profileApi = RetrofitClient.getInstance().create(ProfileApi.class);
        Call<List<Profile>> call = profileApi.getProfiles();
        call.enqueue(new Callback<List<Profile>>() {
            @Override
            public void onResponse(Call<List<Profile>> call, Response<List<Profile>> response) {
                if (response.isSuccessful()) {
                    List<Profile> profiles = response.body();
                    profileAdapter = new ProfileAdapter(profiles);
                    recyclerView.setAdapter(profileAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Profile>> call, Throwable t) {
                // 오류 처리
            }
        });




        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}