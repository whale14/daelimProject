package com.androidproject;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static android.support.constraint.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyPageFragment extends Fragment {

    TextView selectView;
    MaterialCalendarView materialCalendarView;

    TextView select_listView;


    private FirebaseFirestore db = FirebaseFirestore.getInstance();



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
        materialCalendarView = rootView.findViewById(R.id.calendar);

        try {
            materialCalendarView.addDecorator(new EventDecorator());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        selectView.setText("");

        select_listView.setText("");

        // Test용 값 저장
//        Map<String, Object> test = new HashMap<>();
//        test.put("ID", "test1");
//        test.put("text", "테스트할때마다 값 저장");

// Test용 값 저장한 것 불러오는 부분

// Add a new document with a generated ID
//        db.collection("test")
//                .add(test)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error adding document", e);
//                    }
//                });
//
//
//        db.collection("test")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    //@Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d(TAG, document.getId() + " => " + document.getData());
//                            }
//                        } else {
//                            Log.w(TAG, "Error getting documents.", task.getException());
//                        }
//                    }
//                });



        //리스너 등록

//        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//
//
//
//            @Override
//
//            public void onSelectedDayChange(CalendarView view, int year, int month,
//
//                                            int dayOfMonth) {
//
//                // TODO Auto-generated method stub
//
//                /*
//                Toast.makeText(MyPageFragment.this, ""+year+"/"+(month+1)+"/"
//
//                        +dayOfMonth, Toast.LENGTH_LONG).show(); */
//                selectView.setText(""+year+"/"+(month+1)+"/" +dayOfMonth);
//
//
//                db.collection("test").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (DocumentSnapshot document: task.getResult()) {
//                                Log.d(TAG, "DocumentSnapshot data: " + document.getData());
//                            }
//                            select_listView.setText("성공");
//
//                        } else {
//                            Log.d(TAG, "get failed with ", task.getException());
//                            select_listView.setText("실패");
//                        }
//                    }
//                });
//
//
//            }
//
//        });


        return rootView;
    }



}
