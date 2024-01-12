package com.example.englishapp.view;

import static com.example.englishapp.view.LoginActivity.KEY_INT_VALUE;
import static com.example.englishapp.view.SearchActivity.PREF_NAME;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.englishapp.controller.Decorator;
import com.example.englishapp.NetUtil;
import com.example.englishapp.R;
import com.example.englishapp.databean.History;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PunchActivity extends AppCompatActivity {
    private List<History> list = new ArrayList<>();
    private MaterialCalendarView calendarView;
    private Button button;
    private TextView num;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_punch);
        calendarView = findViewById(R.id.calendar);
        button = findViewById(R.id.sign_in_btn);
        num = findViewById(R.id.punch_num);
        button.setOnClickListener(v -> {signIn();});
        getList();
    }

    private void getList() {
        SharedPreferences preferences = this.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        int id =  preferences.getInt(KEY_INT_VALUE, 0);

        calendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_NONE);


        list.clear();
        NetUtil.getInstance().getApi().getHistory(id).enqueue(new Callback<List<History>>() {
            @Override
            public void onResponse(Call<List<History>> call, Response<List<History>> response) {
                if(response.body() != null) {
                    list.addAll(response.body());
                    num.setText(list.size()+"");
                    setDecorator(list);
                }
            }
            @Override
            public void onFailure(Call<List<History>> call, Throwable t) {

            }
        });
    }

    private void signIn() {

        SharedPreferences preferences = this.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        int id =  preferences.getInt(KEY_INT_VALUE, 0);
        String date = CalendarDay.today().getYear() + "-" + CalendarDay.today().getMonth()+1 + "-" + CalendarDay.today().getDay();
        NetUtil.getInstance().getApi().checkIn(id, date).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.body() == 1) {
                    successful();
                } else if (response.body() == -1) {
                    failed();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }

    private void setDecorator (List<History> list) {
        List<CalendarDay> calendarDays = new ArrayList<>();
        for (int i=0; i < list.size(); i++) {
            String s = list.get(i).getDate();
            int syear = 0, sday = 0, smonth = 0;
            syear = Integer.parseInt(s.substring(0, 4));
            if (s.charAt(s.length() - 2) == '-') {//日期一位数 2022.11.1
                sday = Integer.parseInt(s.substring(s.length() - 1));
                if (s.charAt(s.length() - 4) == '-') {
                    smonth = Integer.parseInt(s.substring(s.length() - 3, s.length() - 2));
                } else if (s.charAt(s.length() - 5) == '-') {
                    smonth = Integer.parseInt(s.substring(s.length() - 4, s.length() - 2));
                }
            } else {
                sday = Integer.parseInt(s.substring(s.length() - 2, s.length()));
                if (s.charAt(s.length() - 5) == '-') {
                    smonth = Integer.parseInt(s.substring(s.length() - 4, s.length() - 3));
                } else if (s.charAt(s.length() - 6) == '-') {
                    smonth = Integer.parseInt(s.substring(s.length() - 5, s.length() - 3));
                }
            }

            CalendarDay date = CalendarDay.from(syear, smonth-1, sday);

            calendarDays.add(new CalendarDay(date.getYear(), date.getMonth(), date.getDay()));
        }
        Decorator decorator = new Decorator(Color.RED, calendarDays);
        calendarView.addDecorator(decorator);
        for (CalendarDay d : calendarDays) {
            if (d.equals(CalendarDay.today())) {
                button.setEnabled(false);
                button.setBackgroundColor(Color.GRAY);
            }
        }
    }

    private void successful() {
        Toast.makeText(this, "打卡成功！", Toast.LENGTH_SHORT).show();
        getList();
        button.setEnabled(false);
        button.setBackgroundColor(Color.GRAY);
    }

    private void failed () {
        Toast.makeText(this, "出错啦！", Toast.LENGTH_SHORT).show();
    }

}
