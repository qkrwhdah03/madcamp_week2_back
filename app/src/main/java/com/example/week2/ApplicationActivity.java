package com.example.week2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ApplicationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);

        // Main Activity로 값 전달.. 제출버튼 클릭시
        /*
        Intent intent = new Intent(ApplicationActivity.this, MainActivity.class);
        intent.putExtra("result","resultData");
        setResult(RESULT_OK, intent);
        finish();
         */
    }
}