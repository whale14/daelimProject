package com.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.androidproject.apidata.Item;
import com.bumptech.glide.Glide;

public class MoreInformationActivity extends AppCompatActivity {

    TextView title, start, end;
    ImageView imageView;
    ViewFlipper vf;
    Intent i;

    long start_num,end_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_infomation);

         String images[]={"image1","image2"};

        i = getIntent();
        title = findViewById(R.id.title_more);
        start = findViewById(R.id.start_more);
        end = findViewById(R.id.end_more);
        imageView=findViewById(R.id.imageView);
        vf=findViewById(R.id.vf);

        start_num=i.getLongExtra("start",0);
        end_num=i.getLongExtra("end",0);

        title.setText(i.getStringExtra("title"));
        start.setText(String.valueOf(start_num));
        end.setText(String.valueOf(end_num));


        for (int j = 0; j <images.length-1 ; j++) {
            flipperImages(images[j]);
        }

        /*for(String image:images){
            flipperImages(image);
        }*/
    }

    public void flipperImages(String image){
        ImageView imageView = new ImageView(this);
        Glide.with(this).load(i.getStringExtra(image)).into(imageView);

        vf.addView(imageView);
        vf.setFlipInterval(4000);
        vf.setAutoStart(true);

        vf.setInAnimation(this,android.R.anim.slide_in_left);
        vf.setOutAnimation(this,android.R.anim.slide_out_right);
    }
}
