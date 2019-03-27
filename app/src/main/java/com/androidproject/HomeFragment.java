package com.androidproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        Toolbar mToolbar = rootview.findViewById(R.id.toolbar_home);
        SearchView searchView = rootview.findViewById(R.id.search_view);

        RecyclerView recyclerView = rootview.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        // Inflate the layout for this fragment
        List<CardItem> feedList = new ArrayList<>();
        for (int i = 0; i <10; i++) {
            feedList.add(new CardItem(i + "번째 제목", i + "번째 설명"));
        }
        MyRecyclerAdapter adapter = new MyRecyclerAdapter(feedList);
        recyclerView.setAdapter(adapter);
        return  rootview;
//        return inflater.inflate(R.layout.fragment_home, container, false);
    }

}
