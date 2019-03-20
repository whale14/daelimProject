package com.androidproject;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private final List<CardItem> mFeedList;

    public MyRecyclerAdapter(List<CardItem> feedList) {
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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        CardItem item = mFeedList.get(i);
        viewHolder.title.setText(item.getTitle());
        viewHolder.contents.setText(item.getContents());

    }

    @Override
    public int getItemCount() {
        return mFeedList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, contents;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_text);
            contents = itemView.findViewById(R.id.contents_text);
        }
    }
}
