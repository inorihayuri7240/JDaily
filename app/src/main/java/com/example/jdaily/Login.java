package com.example.jdaily;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jdaily.R;

public class Login extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        setBtnClose();
    }

    private void setBtnClose(){
        ImageButton btnClose=findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View v) {
                Login.this.finish();
            }
        });
    }
}
