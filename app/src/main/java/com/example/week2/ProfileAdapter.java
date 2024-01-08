package com.example.week2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week2.R;
import com.example.week2.models.Profile;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {

    private List<Profile> profiles;

    public ProfileAdapter(List<Profile> profiles) {
        this.profiles = profiles;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_item, parent, false);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        Profile profile = profiles.get(position);
        holder.bind(profile);
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView;
        private TextView phoneTextView;
        // 추가 필드들

        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            phoneTextView = itemView.findViewById(R.id.phoneTextView);
            // 추가 필드들 초기화
        }

        public void bind(Profile profile) {
            nameTextView.setText(profile.getName());
            phoneTextView.setText(profile.getPhone());
            // 추가 필드들 설정
        }
    }
}
