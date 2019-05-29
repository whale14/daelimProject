package com.androidproject;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MyListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_custom_list);

        ListView listview ;
        MyListViewAdapter adapter;

        // Adapter 생성
        adapter = new MyListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.mylist);
        listview.setAdapter(adapter);

        // sample Test list
        // 첫 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.intro),
                "첫번째", "첫번째 테스트") ;
        // 두 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.no_image),
                "두번째", "두번째 테스트") ;
        // 세 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.sample),
                "세번째", "세번째 테스트") ;

        // item은 firestore에서 가졍

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                MyListView myListItem = (MyListView) parent.getItemAtPosition(position) ;

                String myTitle = myListItem.getTitle() ;
                String myDay = myListItem.getDesc() ;
                Drawable myImg = myListItem.getIcon() ;

                Toast.makeText(getApplicationContext(), myTitle + "클릭", Toast.LENGTH_LONG).show();
                // 클릭한 객체의 more page로 이동하도록 구현하기
            }
        }) ;



    }

}
