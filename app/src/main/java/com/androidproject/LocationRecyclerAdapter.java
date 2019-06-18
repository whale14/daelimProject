package com.androidproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static android.support.constraint.Constraints.TAG;

public class LocationRecyclerAdapter extends RecyclerView.Adapter<LocationRecyclerAdapter.ViewHolder> {

    private List<Item> mFeedList;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    Set<String> titles;

    public LocationRecyclerAdapter(List<Item> feedList) {
        mFeedList = feedList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_card_location, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final Item item = mFeedList.get(i);

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
                intent.putExtra("typeId", item.getContenttypeid());
                intent.putExtra("contentId", item.getContentid());

                view.getContext().startActivity(intent);
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
        TextView title;
        ImageView thumbnail;
        Button more;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_text_location);
            thumbnail = itemView.findViewById(R.id.img_recycler_location);
            more = itemView.findViewById(R.id.more_button_location);
        }
    }
}
