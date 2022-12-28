package com.example.jdaily;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;

public class Calender extends AppCompatActivity {
    private String choiceDate;
    public static final int RESULT_CALENDER = 2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender);
        choiceDate= (DateFormat.format("yyyy MM dd", Calendar.getInstance().getTime())).toString(); //預設為當天日期

        setbtnConfirm();
        setCalendarView();
    }

    private void setbtnConfirm(){
        ImageButton btnConfirm=findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View v) {
                //執行Activity
                Intent intent=new Intent();
                intent.setClass(Calender.this,MainActivity.class);
                Bundle bun=new Bundle();
                bun.putString("choiceDate",choiceDate); //將String型別資料放入Bundle
                intent.putExtras(bun);
                setResult(RESULT_CALENDER, intent);
                Calender.this.finish();
            }
        });
    }

    private void setCalendarView(){
        CalendarView calView=findViewById(R.id.calView);
        calView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                choiceDate=year+" "+(month+1)+" "+dayOfMonth;
            }
        });
    }
}
