package com.example.week2.ui.calendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week2.R;

public class AddworkAdapter extends RecyclerView.Adapter<YourViewHolder> {

    // 데이터 리스트
    private List<YourItem> itemList;

    // 생성자 등 필요한 메소드 작성

    @NonNull
    @Override
    public YourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new YourViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull YourViewHolder holder, int position) {
        YourItem item = itemList.get(position);
        // 데이터 바인딩 등 필요한 작업 수행
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void addItem(YourItem newItem) {
    }
}

// YourViewHolder.java
public class YourViewHolder extends RecyclerView.ViewHolder {
    // ViewHolder에서 사용될 뷰들 선언

    public YourViewHolder(@NonNull View itemView) {
        super(itemView);
        // 뷰들 초기화 등 필요한 작업 수행
    }
}
