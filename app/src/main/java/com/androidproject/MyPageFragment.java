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
import android.widget.TextView;

import com.androidproject.utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnRangeSelectedListener;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.annotation.Nullable;

import static android.support.constraint.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyPageFragment extends Fragment {

    TextView selectView;
    MaterialCalendarView materialCalendarView;

    TextView select_listView;


    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private List<DocumentSnapshot> list;

    public MyPageFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_my_page, container, false);

        selectView = rootView.findViewById(R.id.selectView);
        select_listView = rootView.findViewById(R.id.select_list_View);

        //CalendarView 인스턴스 만들기
        materialCalendarView = rootView.findViewById(R.id.calendar);

        selectView.setText("");

        select_listView.setText("");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        list = new ArrayList<>();

        db.collection(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                final List<FireStoreModel> modelList = Objects.requireNonNull(task.getResult()).toObjects(FireStoreModel.class);

                List<CalendarDay> dateArray = new ArrayList();
                for (int i = 0; i < modelList.size(); i++) {
                    dateArray.add(Utils.badLongDateToCalendarDay(modelList.get(i).getStart()));
                }
                Log.d(TAG, "onComplete: " + dateArray.toString());
                try {
                    materialCalendarView.addDecorator(new EventDecorator(dateArray));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
//                materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
//                    @Override
//                    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
//                        if (modelList.contains(materialCalendarView.getSelectedDate()) ) {
//
//                        }
//                    }
//                });

            }
        });

        return rootView;
    }



}
