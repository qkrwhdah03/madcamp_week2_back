package com.example.week2.ui.calendar;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week2.R;

public class AddworkFragment extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AddworkAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        AddworkAdapter adapter = new AddworkAdapter(); // YourAdapter는 RecyclerView에 사용될 어댑터입니다.
        recyclerView.setAdapter(adapter);
        */


        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_layout);

        recyclerView = findViewById(R.id.your_recyclerview_id);
        adapter = new AddworkAdapter(); // YourAdapter는 RecyclerView에 사용될 어댑터입니다.
        recyclerView.setAdapter(adapter);

        // 기타 RecyclerView 설정들...

    }

    public void onRelativeLayoutClick(View view) {
        // 클릭 이벤트 처리
        // RecyclerView에 새로운 아이템을 추가하는 코드를 작성
        // 아래는 예제 코드입니다. 필요에 따라 수정하세요.

        YourItem newItem = new YourItem(); // YourItem은 RecyclerView에 추가할 데이터 모델 클래스입니다.
        adapter.addItem(newItem); // addItem 메소드는 어댑터에 아이템을 추가하는 메소드로 수정이 필요합니다.

        // 데이터가 변경되었음을 어댑터에 알려서 업데이트합니다.
        adapter.notifyDataSetChanged();
    }
}