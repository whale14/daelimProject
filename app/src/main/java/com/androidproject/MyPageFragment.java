package com.androidproject;


import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnRangeSelectedListener;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private AlertDialog.Builder builder;

    public MyPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_my_page, container, false);

        selectView = rootView.findViewById(R.id.selectView);
        select_listView = rootView.findViewById(R.id.select_list_View);

        //CalendarView 인스턴스 만들기
        materialCalendarView = rootView.findViewById(R.id.calendar);

        selectView.setText("");

        select_listView.setText("");

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        list = new ArrayList<>();

        db.collection(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull final Task<QuerySnapshot> task) {
                final List<FireStoreModel> modelList = Objects.requireNonNull(task.getResult()).toObjects(FireStoreModel.class);

                final List<CalendarDay> dateArray = new ArrayList();
                for (int i = 0; i < modelList.size(); i++) {
                    dateArray.add(Utils.badLongDateToCalendarDay(modelList.get(i).getStart()));
                }
                Log.d(TAG, "onComplete: " + dateArray.toString());
                materialCalendarView.addDecorator(new EventDecorator(dateArray));
                materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
                    @Override
                    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                        Log.d(TAG, "onDateSelected date " + date);
                        Log.d(TAG, "onDateSelected array " + dateArray.toString());
                        builder = new AlertDialog.Builder(getContext());
                        if (dateArray.contains(date)) {
                            String year = String.valueOf(date.getYear());
                            String month = String.valueOf(date.getMonth() + 1);
                            String day = String.valueOf(date.getDay());

                            if (month.startsWith("1")) {
                                if (month.equals("1")) {
                                    month = "0" + month;
                                } else month = month;
                            } else {
                                month = "0" + month;
                            }

                            if (day.startsWith("1")) {
                                if (day.equals("1")) {
                                    day = "0" + day;
                                } else day = day;
                            } else if (day.startsWith("2")) {
                                if (day.equals("2")) {
                                    day = "0" + day;
                                } else day = day;
                            } else if (day.startsWith("3")) {
                                if (day.equals("3")) {
                                    day = "0" + day;
                                } else day = day;
                            } else {
                                day = "0" + day;
                            }
                            long dateLong = Long.parseLong(year + month + day);
                            Log.d(TAG, "onDateSelected: dateLong = " + dateLong);

                            Log.d(TAG, "onDateSelected: " + month);
                            Log.d(TAG, "onDateSelected: " + day);

//                            db.collection(user.getUid()).whereEqualTo("start", date)
//                                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                                @Override
//                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
//
//                                }
//                            });
                            builder.setTitle("지역축제");
//                            db.collection(user.getUid()).whereEqualTo("start", dateLong).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                                @Override
//                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                                    List<FireStoreModel> startList = Objects.requireNonNull(task.getResult()).toObjects(FireStoreModel.class);
//                                    Log.d(TAG, "onComplete: " + startList.toString());
//                                    Log.d(TAG, "onComplete: " + task.getResult().toString());
//                                    for (int i = 0; i < startList.size(); i++) {
//                                        builder.setMessage("시작 : " + startList.get(i).getStart());
//                                        builder.setMessage("종료 : " + startList.get(i).getEnd());
//                                        builder.setMessage("주소 : " + startList.get(i).getAddr());
//                                        builder.setMessage("전화번호 : " + startList.get(i).getTel());
//                                    }
//                                }
//                            });
                            db.collection(user.getUid()).whereEqualTo("start", dateLong).addSnapshotListener(new EventListener<QuerySnapshot>() {
                                @Override
                                public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                                    assert queryDocumentSnapshots != null;
                                    List<FireStoreModel> listSnapshot = queryDocumentSnapshots.toObjects(FireStoreModel.class);
                                    Log.d(TAG, "onEvent: " + queryDocumentSnapshots.getDocuments().size());
                                    StringBuilder stringBuilder = new StringBuilder();
                                    for (int i = 0; i < listSnapshot.size(); i++) {
                                        stringBuilder.append("\n" + listSnapshot.get(i).getTitle());
                                        stringBuilder.append("\n시작일 : " + listSnapshot.get(i).getStart());
                                        stringBuilder.append("\n종료일 : " + listSnapshot.get(i).getEnd());
                                        stringBuilder.append("\n주소 : " + listSnapshot.get(i).getAddr());
                                        stringBuilder.append("\n문의 : " + listSnapshot.get(i).getTel());
                                        stringBuilder.append("\n");
                                        builder.setMessage(stringBuilder);
                                    }
                                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    });
                                    builder.show();
                                }
                            });


                        }

                    }
                });

            }
        });

        return rootView;
    }


}
