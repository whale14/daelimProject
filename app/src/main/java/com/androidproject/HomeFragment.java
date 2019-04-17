package com.androidproject;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.androidproject.apidata.Example;
import com.androidproject.apidata.KoreanJsonService;
import com.androidproject.apidata.Result;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private static final String TAG = MainActivity.class.getSimpleName();

    public HomeFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        // API 데이터 받아오는 부분
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("Authorization", "Bearer " + "BfmZIhV1ms5cdBDBCzU8NxhYmU9UEn").build();
                return chain.proceed(request);
            }
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.predicthq.com")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        KoreanJsonService service = retrofit.create(KoreanJsonService.class);

        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        // Inflate the layout for this fragment
        List<Result> feedList = new ArrayList<>();
//        for (int i = 0; i <10; i++) {
//            feedList.add(new CardItem(i + "번째 제목", i + "번째 설명"));
//        }
        final MyRecyclerAdapter adapter = new MyRecyclerAdapter(feedList);
        recyclerView.setAdapter(adapter);

        service.listPosts("KR").enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                List<Result> posts = response.body().getResults();

                Log.d(TAG, "onResponse: " + posts);

                if (posts != null) {
                    adapter.setItems(posts);
                    adapter.notifyItemRangeInserted(0, posts.size());
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });

        return  rootView;
//        return inflater.inflate(R.layout.fragment_home, container, false);
    }



}
