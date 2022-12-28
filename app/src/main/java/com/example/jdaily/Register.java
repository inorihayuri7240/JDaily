package com.example.jdaily;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import mObject.Member;

public class Register extends AppCompatActivity {
    Member thisMem;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        setSpiGender();
        setBtnClose();

        thisMem=new Member();
        ((EditText)findViewById(R.id.edtBirthday)).setInputType(InputType.TYPE_NULL); //EditText不顯示軟件鍵盤

    }

    private void getThisMember(){
        thisMem.setLoginId(((EditText)findViewById(R.id.edtLoginId)).getText().toString());
        thisMem.setPwd(((EditText)findViewById(R.id.edtPWD)).getText().toString());
        thisMem.setNickName(((EditText)findViewById(R.id.edtNickName)).getText().toString());
        try {
            thisMem.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(((EditText)findViewById(R.id.edtBirthday)).getText().toString()));
        } catch (ParseException e) { e.printStackTrace();}
        thisMem.setEmail(((EditText)findViewById(R.id.edtEmail)).getText().toString());
    }

    private void setSpiGender(){
        final Spinner spinner = (Spinner)findViewById(R.id.spiGender);
        final String[] options = {"不表態", "男", "女"};
        int con=(R.layout.txtgreen_spinner_dropdown_item);
        ArrayAdapter<String> optionsList = new ArrayAdapter<>(Register.this,
                R.layout.txtgreen_spinner_dropdown_item,
                options); //適配器
        spinner.setAdapter(optionsList);

        //設定選取選單後的動作
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { //選項有選取時的動作
                thisMem.setGender(options[position]); //存入選擇的性別
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { //沒有選取時的動作
            }
        });
    }

    private void setBtnClose(){
        ImageButton btnClose=findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View v) {
                Register.this.finish();
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
}
