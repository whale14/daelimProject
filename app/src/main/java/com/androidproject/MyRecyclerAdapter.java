package com.androidproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidproject.apidata.Item;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static android.support.constraint.Constraints.TAG;
import static android.view.View.generateViewId;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private List<Item> mFeedList;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    Set<String> titles;

    public MyRecyclerAdapter(List<Item> feedList) {
        mFeedList = feedList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_card, viewGroup, false);

        sp = view.getContext().getSharedPreferences("titleList", Context.MODE_PRIVATE);
        editor = sp.edit();
        titles = sp.getStringSet("titleList", new HashSet<String>());
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final Item item = mFeedList.get(i);

        if (titles.contains(item.getTitle())) {
            viewHolder.like.setTextColor(Color.RED);
        } else {
            viewHolder.like.setTextColor(Color.DKGRAY);
        }

        viewHolder.title.setText(item.getTitle());
        if (item.getFirstimage() != null) {
            Glide.with(viewHolder.itemView).load(item.getFirstimage()).into(viewHolder.thumbnail);
            viewHolder.thumbnail.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

        viewHolder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //리사이클러뷰 more 클릭 이벤트 리스너
                Log.d("tada", "onClick: more_button" + item.getTitle());
                Intent intent = new Intent(view.getContext(), MoreInformationActivity.class);
                intent.putExtra("start",item.getEventstartdate());
                intent.putExtra("end",item.getEventenddate());
                intent.putExtra("typeId", item.getContenttypeid());
                intent.putExtra("contentId", item.getContentid());

                view.getContext().startActivity(intent);
            }
        });

        viewHolder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //리사이클러뷰 like 클릭 이벤트 리스너
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                titles = sp.getStringSet("titleList", new HashSet<String>());
                String contentIdString = Integer.toString(item.getContentid());
                String title = item.getTitle();
                if (titles.contains(title)) {
                    titles.remove(title);
                    Toast.makeText(view.getContext(), "즐겨찾기에서 삭제 되었습니다.", Toast.LENGTH_SHORT).show();
                    db.collection(user.getUid()).document(contentIdString)
                            .delete()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: ");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: " + e.getLocalizedMessage());
                                }
                            });
                } else {
                    titles.add(title);
                    Toast.makeText(view.getContext(), "즐겨찾기에 추가 되었습니다.", Toast.LENGTH_SHORT).show();
                    FireStoreModel fireStoreModel = new FireStoreModel(
                            item.getTitle(),
                            item.getFirstimage(),
                            item.getFirstimage2(),
                            item.getAddr1(),
                            item.getTel(),
                            item.getEventstartdate(),
                            item.getEventenddate());

                    db.collection(user.getUid()).document(contentIdString).set(fireStoreModel);
                }
                editor.putStringSet("titleList", titles);
                editor.apply();
                notifyDataSetChanged();
                Log.d(TAG, "onClick: " + titles.toString());


                // https://console.firebase.google.com/project/festivalproject-adc50/database/firestore/data~2Ftest1~2FO3IxRgkNiVBBFvmDJGpW
                // 여기서 데이터베이스에 데이터 들어가는거 실시간 확인
                // https://firebase.google.com/docs/firestore/query-data/get-data?hl=ko 파이어스토어 공식문서
            }
        });
    }

    public void setItems(List<Item> items) {
        mFeedList = items;
    }

    @Override
    public int getItemCount() {
        return mFeedList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, contents;
        ImageView thumbnail;
        Button more, like;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_text);
            thumbnail = itemView.findViewById(R.id.img_recycler);
            more = itemView.findViewById(R.id.more_button);
            like = itemView.findViewById(R.id.like_button);
        }
    }
}
