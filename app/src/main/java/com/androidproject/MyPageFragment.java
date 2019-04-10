package com.androidproject;


import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyPageFragment extends Fragment {

    TextView selectView;
    CalendarView calendar;

    TextView select_listView;

    String test1;



    public MyPageFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_my_page, container, false);

        selectView = rootView.findViewById(R.id.selectView);
        select_listView = rootView.findViewById(R.id.select_list_View);
        //CalendarView 인스턴스 만들기

        CalendarView calendar = rootView.findViewById(R.id.calendar);

        selectView.setText("");

        select_listView.setText("");

        //리스너 등록

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {



            @Override

            public void onSelectedDayChange(CalendarView view, int year, int month,

                                            int dayOfMonth) {

                // TODO Auto-generated method stub

                /*
                Toast.makeText(MyPageFragment.this, ""+year+"/"+(month+1)+"/"

                        +dayOfMonth, Toast.LENGTH_LONG).show(); */
                selectView.setText(""+year+"/"+(month+1)+"/" +dayOfMonth);
                select_listView.setText("test");
                // https://github.com/yoondowon/FirebaseConnection/blob/master/app/src/main/java/com/example/user/firebaseconnection/MainActivity.java
                // 여기까지함!

            }

        });


        return rootView;
    }


}
