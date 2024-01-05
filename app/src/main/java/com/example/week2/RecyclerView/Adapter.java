package com.example.week2.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week2.R;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    // 데이터 리스트 및 필요한 변수들 선언

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listofworks, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // 각 아이템에 대한 데이터를 ViewHolder에 바인딩
    }

    @Override
    public int getItemCount() {
        // 데이터 리스트의 크기 반환
        return 0;
    }
}