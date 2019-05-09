package com.androidproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Objects;

public class IntroActivity extends AppCompatActivity {

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        sp = getSharedPreferences("loginInfo", MODE_PRIVATE);

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Objects.equals(sp.getString("uId", ""), "")) {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
        }, 2000);

    }
}
