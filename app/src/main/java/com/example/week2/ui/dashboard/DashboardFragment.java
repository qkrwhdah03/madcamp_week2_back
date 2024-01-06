package com.example.week2.ui.dashboard;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.week2.R;
import com.example.week2.databinding.FragmentDashboardBinding;

import java.time.DateTimeException;
import java.util.Calendar;
import java.util.Date;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;


    // 달력 관련 정보 저장
    private int year;
    private int month;
    private int day;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // 캘린더 클릭 설정
        CalendarView calendarView = (CalendarView) binding.trainerCalendar;
        TextView calendar_todo_text_view = (TextView) binding.calendarTodoText;
        if(calendarView != null && calendar_todo_text_view != null) {

            // 오늘 날짜로 초기화
            Calendar calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH) + 1;
            day = calendar.get(Calendar.DAY_OF_MONTH);
            calendar_todo_text_view.setText(year + " / " + month + " / " + day);
            // 오늘 날짜 todo list 얻기

            calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView view, int nyear, int nmonth, int nday) {
                    year = nyear;
                    month = nmonth + 1;
                    day = nday;
                    calendar_todo_text_view.setText(year + " / " + month + " / " + day);
                    // 서버로 부터..? 오늘 일정 정보 얻어와서 listview에 넣자.

                }
            });
        } else{
            Log.d("Error", "Null calender or text view");
        }

        // 추가 버튼 클릭 설정
        ImageButton add_todo_button = (ImageButton) binding.trainerTodoAddButton;
        if(add_todo_button != null) {
            add_todo_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // List View에 입력창 새로 만들기
                    calendar_todo_text_view.setText("Haha ");
                }
            });
        }
        return root;
    }

    private void updateListView(){
        ListView listView = binding.trainerTodoListview;
        if(listView != null){

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
