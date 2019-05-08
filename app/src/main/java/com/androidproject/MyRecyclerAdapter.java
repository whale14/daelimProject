package com.androidproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androidproject.apidata.Example;
import com.androidproject.apidata.Result;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.support.constraint.Constraints.TAG;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private List<Result> mFeedList;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public MyRecyclerAdapter(List<Result> feedList) {
        mFeedList = feedList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_card, viewGroup, false);



        db.collection("test1")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    //@Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final Result item = mFeedList.get(i);

        viewHolder.title.setText(item.getTitle());

        viewHolder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //리사이클러뷰 more 클릭 이벤트 리스너
                Log.d("tada", "onClick: more_button" + item.getTitle());
                Intent intent = new Intent(view.getContext(),MoreInfomationActivity.class);
                intent.putExtra("title", item.getTitle());
                intent.putExtra("description", item.getDescription());
                intent.putExtra("start", item.getStart());
                intent.putExtra("end", item.getEnd());
                view.getContext().startActivity(intent);
            }
        });

        viewHolder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //리사이클러뷰 like 클릭 이벤트 리스너

                // Test용 값 저장
                Map<String, Object> test1 = new HashMap<>();
                test1.put("title", item.getTitle());
                test1.put("contry", item.getCountry());
                test1.put("start", item.getStart());
                test1.put("end", item.getEnd());

                // https://console.firebase.google.com/project/festivalproject-adc50/database/firestore/data~2Ftest1~2FO3IxRgkNiVBBFvmDJGpW
                // 여기서 데이터베이스에 데이터 들어가는거 실시간 확인
                
                db.collection("test1")
                        .add(test1)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });



            }
        });

    }
    public void setItems(List<Result> items) {
        mFeedList = items;
    }

    @Override
    public int getItemCount() {
        return mFeedList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, contents;
        Button more, like;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_text);
            more = itemView.findViewById(R.id.more_button);
            like = itemView.findViewById(R.id.like_button);
        }
    }
}
