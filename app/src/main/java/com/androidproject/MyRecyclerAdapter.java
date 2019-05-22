package com.androidproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

@SuppressWarnings("ALL")
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private List<Item> mFeedList;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    Set<String> ids;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public MyRecyclerAdapter(List<Item> feedList) {
        mFeedList = feedList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_card, viewGroup, false);

        sp = view.getContext().getSharedPreferences("idList", Context.MODE_PRIVATE);
        editor = sp.edit();
        ids = sp.getStringSet("idList", new HashSet<String>());
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final Item item = mFeedList.get(i);

        if (ids.contains(Integer.toString(item.getContentid()))) {
            viewHolder.like.setTextColor(R.color.red);
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
                intent.putExtra("title", item.getTitle());
                view.getContext().startActivity(intent);
            }
        });

        viewHolder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //리사이클러뷰 like 클릭 이벤트 리스너
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                ids = sp.getStringSet("idList", new HashSet<String>());
                String contentIdString = Integer.toString(item.getContentid());
                if (ids.contains(contentIdString)) {
                    ids.remove(contentIdString);
                    Toast.makeText(view.getContext(), "즐겨찾기에서 삭제 되었습니다.", Toast.LENGTH_SHORT).show();
                    db.collection(user.getUid()).document(contentIdString)
                            .delete()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "DocumentSnapshot successfully deleted!");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error deleting document", e);
                                }
                            });

                } else {
                    ids.add(contentIdString);
                    Toast.makeText(view.getContext(), "즐겨찾기에 추가 되었습니다.", Toast.LENGTH_SHORT).show();
                    FireStoreModel fireStoreModel = new FireStoreModel(
                            item.getTitle(),
                            item.getFirstimage(),
                            item.getFirstimage2(),
                            item.getAddr1(),
                            item.getTel(),
                            item.getEventstartdate(),
                            item.getEventenddate());

                    int contentId = item.getContentid();
                    String contentIdToString = Integer.toString(contentId);
                    db.collection(user.getUid()).document(contentIdToString).set(fireStoreModel);
                    Log.d(TAG, "uId : " + user.getUid());
                    Log.d(TAG, "itemId : " + item.getContentid());
                }
                editor.putStringSet("idList", ids);
                editor.apply();
                notifyDataSetChanged();
                Log.d(TAG, "onClick: " + ids.toString());


                // https://console.firebase.google.com/project/festivalproject-adc50/database/firestore/data~2Ftest1~2FO3IxRgkNiVBBFvmDJGpW
                // 여기서 데이터베이스에 데이터 들어가는거 실시간 확인
                //https://firebase.google.com/docs/firestore/query-data/get-data?hl=ko 파이어스토어 공식문서
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
