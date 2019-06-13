package com.androidproject;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidproject.apidata.Item;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MyListViewAdapter  extends BaseAdapter {

    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<FireStoreModel> listViewItemList = new ArrayList<>() ;

    // ListViewAdapter의 생성자
    public MyListViewAdapter() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "list_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        ImageView myImg = (ImageView) convertView.findViewById(R.id.myImg) ;
        TextView myTitle = (TextView) convertView.findViewById(R.id.myTitle) ;
        TextView myDayStart = (TextView) convertView.findViewById(R.id.myDayStart) ;
        TextView myDayEnd = (TextView) convertView.findViewById(R.id.myDayEnd) ;

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        FireStoreModel listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        Glide.with(convertView).load(listViewItem.getImg1()).into(myImg);
        myTitle.setText(listViewItem.getTitle());
        myDayStart.setText(String.valueOf(listViewItem.getStart()) + " ~ ");
        myDayEnd.setText(String.valueOf(listViewItem.getEnd()));

        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴.
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    Item item;

    // 아이템 데이터 추가를 위한 함수.
    public void addItem(Drawable img, String title, String dayStart, String dayEnd) {
//        MyListView myListItem = new MyListView();

//        myListItem.setIcon(img); // 못가져옴..
//        myListItem.setTitle(title);
//        myListItem.setDayStart(dayStart); // 날짜도.. 왜..지..
//        myListItem.setDayEnd(dayEnd);
//
//
//        listViewItemList.add(myListItem);

    }

    public void setItems(List<FireStoreModel> items) {
        listViewItemList.clear();
        listViewItemList.addAll(items);
        notifyDataSetChanged();
    }

}
