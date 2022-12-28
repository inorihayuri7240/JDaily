package com.example.jdaily;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import mObject.Itinerary;

public class ItineraryAdd extends AppCompatActivity {
    public static final int RESULT_ITINERARYADD = 3;
//    public static final int REQ_FROM_ITINERARYADD = 3;

    EditText edtTimeStart,edtTimeEnd,edtName,edtDescription,edtGuest,edtPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itinerary_add);

        setTxtDate();
        setEdtTxt();
        setBtnCreate();
        setBtnClose();

    }

    private void setEdtTxt(){
        edtTimeStart=findViewById(R.id.edtTimeStart);
        edtTimeStart.setInputType(InputType.TYPE_NULL); //EditText不顯示軟件鍵盤
        edtTimeEnd=findViewById(R.id.edtTimeEnd);
        edtTimeEnd.setInputType(InputType.TYPE_NULL); //EditText不顯示軟件鍵盤

        edtName=findViewById(R.id.edtNickName);
        edtDescription=findViewById(R.id.edtDescription);
        edtGuest=findViewById(R.id.edtGuest);
        edtPlace=findViewById(R.id.edtPlace);
    }

    private void setTxtDate(){
        String mainDate =getIntent().getStringExtra("mainDate"); //取得主頁日期
        ((TextView)findViewById(R.id.txtDate)).setText(mainDate);
    }

    private void setBtnClose(){
        ImageButton btnClose=findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View v) {
                ItineraryAdd.this.finish();
            }
        });
    }

    private void setBtnCreate(){
        Button btnCreate=findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(ItineraryAdd.this,MainActivity.class);
                Bundle bun=new Bundle();

                Date TimeStart = null,TimeEnd = null;
                SimpleDateFormat sdf = new SimpleDateFormat("HH : mm"); //定義時間字串的格式
                try {
                    TimeStart=sdf.parse(edtTimeStart.getText().toString()); //將字串轉成Date型
                    TimeEnd=sdf.parse(edtTimeEnd.getText().toString());
                } catch (ParseException e) {e.printStackTrace();}
                Itinerary newIti=new Itinerary(TimeStart,TimeEnd,edtName.getText().toString(),edtDescription.getText().toString(),edtGuest.getText().toString(),edtPlace.getText().toString());
                bun.putSerializable("newIti", newIti); //將(可序列化)物件資料放入Bundle
                intent.putExtras(bun);
                setResult(RESULT_ITINERARYADD, intent);
                ItineraryAdd.this.finish();
            }
        });
    }

    //日期選擇器
    public void datePicker(final View v){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                String dateTime = String.valueOf(year)+"-"+String.valueOf(month+1)+"-"+String.valueOf(day);
                ((EditText)v).setText(dateTime);
            }

        }, year, month, day).show();
    }

    //時間選擇器
    public void timePicker(final View v){
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        new TimePickerDialog(v.getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String dateTime=String.valueOf(hourOfDay+" : "+String.valueOf(minute));
                ((EditText)v).setText(dateTime);
            }
        }, hour, minute, true).show();
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data)
//    {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(resultCode == MainActivity.RESULT_MAINACTIVITY) //確認是否從CANCELED回傳
//        {
//            if(requestCode == REQ_FROM_ITINERARYADD) //確認所要執行的動作
//            {
//                String mainDate = data.getExtras().getString("mainDate"); //取得選擇的日期
//                ((TextView)findViewById(R.id.txtDate)).setText("mainDate");
//            }
//        }
//    }
}
