package com.androidproject;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.androidproject.apidata.KoreanJsonService;
import com.androidproject.apidetails.Example;
import com.androidproject.apidetails.Item;
import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoreInformationActivity extends AppCompatActivity {

    TextView title, date, tel, addr, homePage, overview;
    ImageView imageView;

    long start_num,end_num;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_infomation);

         String images[]={"image1","image2"};

        i = getIntent();
        title = findViewById(R.id.title_more);
        date = findViewById(R.id.date_more);
        tel = findViewById(R.id.tel_more);
        addr = findViewById(R.id.addr_more);
        homePage = findViewById(R.id.home_page_more);
        overview = findViewById(R.id.overview_more);
        imageView=findViewById(R.id.imageView);


        int typeId = i.getIntExtra("typeId",0);
        int contentId = i.getIntExtra("contentId",0);

        String typeString = Integer.toString(typeId);
        String contentString = Integer.toString(contentId);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.visitkorea.or.kr")
//                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        KoreanJsonService service = retrofit.create(KoreanJsonService.class);
        service.listDetails(typeString,
                contentString,
                "TourAPI3.0_Guide",
                "Y",
                "Y",
                "Y",
                "Y",
                "Y",
                "Y",
                "Y",
                "Y",
                "27eTyCleBTtFCRPd1ct0WRpGIs6oJ8ah%2BYcibDG4E8WcoGP6T%2BjYaAr9deXHQwxEwTuY4wUaIq3LN8OdaXslwQ%3D%3D",
                "AND",
                "json"
        ).enqueue(new Callback<com.androidproject.apidetails.Example>() {
            @Override
            public void onResponse(Call<com.androidproject.apidetails.Example> call, Response<com.androidproject.apidetails.Example> response) {
                Item details = response.body().getResponse().getBody().getItems().getItem();

                Glide.with(imageView).load(response.body().getResponse().getBody().getItems().getItem().getFirstimage()).into(imageView);
                title.setText(details.getTitle());
                title.setTextColor(Color.WHITE);

                long start = i.getLongExtra("start", 0);
                long end = i.getLongExtra("end", 0);

                date.setText("축제기간 : " + setDate(start, end));
                tel.setText("전화번호 : " + details.getTel());
                addr.setText("주소 : " + details.getAddr1());

                if (details.getHomepage()!= null) {
                    String[] hp = details.getHomepage().split("\"");
                    homePage.setText("홈페이지 : " + hp[1]);
                } else homePage.setText("홈페이지 : 홈페이지가 존재하지 않습니다.");

                String overviewString = details.getOverview().replace("<br>", " ");
                overview.setText(overviewString);
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.d("!", "onFailure: " + t.getLocalizedMessage());
            }
        });

        /*for(String image:images){
            flipperImages(image);
        }*/
    }

    private String setDate(long start, long end) {
        String newDate = "";
        String strDate = String.valueOf(start);
        String endDate = String.valueOf(end);

        int startYear = Integer.parseInt(strDate.substring(0, 4));
        int startMonth = Integer.parseInt(strDate.substring(4, 6));
        int startDay = Integer.parseInt(strDate.substring(6, 8));

        int endYear = Integer.parseInt(endDate.substring(0, 4));
        int endMonth = Integer.parseInt(endDate.substring(4, 6));
        int endDay = Integer.parseInt(endDate.substring(6, 8));

        strDate = startYear + "년 " + startMonth + "월 " + startDay + "일";
        endDate = endYear + "년 " + endMonth + "월 " + endDay + "일";

        newDate = strDate + " ~ " + endDate;

        return newDate;
    }

}
