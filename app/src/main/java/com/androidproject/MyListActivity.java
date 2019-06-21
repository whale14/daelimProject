package com.androidproject;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.Objects;

public class MyListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_custom_list);

        ListView listview ;
        final MyListViewAdapter adapter;

        // Adapter 생성
        adapter = new MyListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.mylist);
        listview.setAdapter(adapter);



        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        Query query = FirebaseFirestore.getInstance()
                .collection(user.getUid());

        // firestore에서 가져오는 부분
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Toast.makeText(getApplicationContext(), "오류 발생", Toast.LENGTH_LONG).show();
                    return;
                }

                // Convert query snapshot to a list of chats
                List<FireStoreModel> festivals = snapshot.toObjects(FireStoreModel.class);


                // Update UI
                // ...
                adapter.setItems(festivals);
            }
        });

//        // sample Test list
//        // 첫 번째 아이템 추가.
//        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.intro),
//                "첫번째", "첫번째 테스트") ;
//        // 두 번째 아이템 추가.
//        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.no_image),
//                "두번째", "두번째 테스트") ;
//        // 세 번째 아이템 추가.
//        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.sample),
//                "세번째", "세번째 테스트") ;

        // item은 firestore에서 가졍

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                FireStoreModel myListItem = (FireStoreModel) parent.getItemAtPosition(position) ;

                String myTitle = myListItem.getTitle() ;
                String myDayString = String.valueOf(myListItem.getStart());
                String myDayEnd = String.valueOf(myListItem.getEnd());
//                Drawable myImg = myListItem.getIcon() ;

                // Test용 Toast
                //Toast.makeText(getApplicationContext(), myTitle + " 클릭", Toast.LENGTH_LONG).show();
                // 클릭한 객체의 more page로 이동하도록 구현하기
            }
        }) ;



    }

}
