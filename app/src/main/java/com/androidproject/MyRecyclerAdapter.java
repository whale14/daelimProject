package com.androidproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidproject.apidata.Item;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.support.constraint.Constraints.TAG;

@SuppressWarnings("ALL")
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private List<Item> mFeedList;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    public MyRecyclerAdapter(List<Item> feedList) {
        mFeedList = feedList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_card, viewGroup, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final Item item = mFeedList.get(i);

        viewHolder.title.setText(item.getTitle());
        Glide.with(viewHolder.itemView).load(item.getFirstimage()).into(viewHolder.thumbnail);


        viewHolder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //리사이클러뷰 more 클릭 이벤트 리스너
                Log.d("tada", "onClick: more_button" + item.getTitle());
                Intent intent = new Intent(view.getContext(), MoreInformationActivity.class);
                intent.putExtra("title", item.getTitle());
//                intent.putExtra("description", item.getDescription());
//                intent.putExtra("start", item.getStart());
//                intent.putExtra("end", item.getEnd());
                view.getContext().startActivity(intent);
            }
        });

        viewHolder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //리사이클러뷰 like 클릭 이벤트 리스너
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                CollectionReference users = db.collection(user.getUid());

                //날짜 데이터 형식 변환
//                String dateStart = item.getStart();
//                Date parseStart = null;
//                DateFormat formatStart = new SimpleDateFormat("yyyy-MM-dd");
//                try {
//                    parseStart = formatStart.parse(dateStart);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                DateFormat startFormat = new SimpleDateFormat("yyyy-MM-dd");
//                String startDate = startFormat.format(parseStart);
//
//                String dateEnd = item.getEnd();
//                Date parseEnd = null;
//                DateFormat formatEnd = new SimpleDateFormat("yyyy-MM-dd");
//                try {
//                    parseEnd = formatEnd.parse(dateEnd);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                DateFormat endFormat = new SimpleDateFormat("yyyy-MM-dd");
//                String endDate = endFormat.format(parseEnd);

                // Test용 값 저장
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("title", item.getTitle());
                userMap.put("img1", item.getFirstimage());
                userMap.put("img2", item.getFirstimage2());
                userMap.put("start", item.getEventstartdate());
                userMap.put("end", item.getEventenddate());
                userMap.put("addr", item.getAddr1());
                userMap.put("tel", item.getTel());

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
//                users.document(contentIdToString).set(userMap);
                Log.d(TAG, "uId : " + user.getUid());
                Log.d(TAG, "itemId : " + item.getContentid());

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
