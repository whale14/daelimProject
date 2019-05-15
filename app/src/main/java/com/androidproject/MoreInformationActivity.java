package com.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MoreInformationActivity extends AppCompatActivity {

    TextView title, description, start, end;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_infomation);

        Intent i = getIntent();
        title = findViewById(R.id.title_more);
        description = findViewById(R.id.description_more);
        start = findViewById(R.id.start_more);
        end = findViewById(R.id. end_more);

        title.setText(i.getStringExtra("title"));
        description.setText(i.getStringExtra("description"));
        start.setText(i.getStringExtra("start"));
        end.setText(i.getStringExtra("end"));
    }
}
