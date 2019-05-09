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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static android.support.constraint.Constraints.TAG;

@SuppressWarnings("ALL")
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
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                CollectionReference users = db.collection(user.getUid());

                String date = item.getStart();
                Date parseDate = null;
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    parseDate = format.parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                DateFormat stringFormat = new SimpleDateFormat("yyyy-MM-dd");
                String startDate = stringFormat.format(parseDate);

                // Test용 값 저장
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("title", item.getTitle());
                userMap.put("country", item.getCountry());
                userMap.put("startDate", parseDate);
                userMap.put("endDate", item.getEnd());
                users.document(item.getId()).set(userMap);
                Log.d(TAG, "uId : " + user.getUid());
                Log.d(TAG, "itemId : " + item.getId());

                // https://console.firebase.google.com/project/festivalproject-adc50/database/firestore/data~2Ftest1~2FO3IxRgkNiVBBFvmDJGpW
                // 여기서 데이터베이스에 데이터 들어가는거 실시간 확인
                //https://firebase.google.com/docs/firestore/query-data/get-data?hl=ko 파이어스토어 공식문서
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
