package com.androidproject;


import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.androidproject.apidata.Example;
import com.androidproject.apidata.Item;
import com.androidproject.apidata.KoreanJsonService;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.util.FusedLocationSource;

import java.security.Permission;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewFragment extends Fragment {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private MapView mapView;
    private FusedLocationSource source;
    private RecyclerView recyclerViewLocation;
    private LocationRecyclerAdapter adapter;
    private KoreanJsonService service;
    String areaCode;

    public ReviewFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_review, container, false);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.visitkorea.or.kr")
//                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(KoreanJsonService.class);

        areaCode = "1";

        setList(areaCode);

        recyclerViewLocation = rootView.findViewById(R.id.recycler_view_location);
        Button btnSeul = rootView.findViewById(R.id.btn_seul_1);
        Button btnInch = rootView.findViewById(R.id.btn_ic_2);
        Button btnDeaj = rootView.findViewById(R.id.btn_dj_3);
        Button btnDeag = rootView.findViewById(R.id.btn_dg_4);
        Button btnGwan = rootView.findViewById(R.id.btn_gj_5);
        Button btnBusa = rootView.findViewById(R.id.btn_bs_6);
        Button btnUlsa = rootView.findViewById(R.id.btn_us_7);
        Button btnSejo = rootView.findViewById(R.id.btn_sj_8);
        Button btnGyea = rootView.findViewById(R.id.btn_gg_9);
        Button btnGang = rootView.findViewById(R.id.btn_gw_10);
        Button btnChbu = rootView.findViewById(R.id.btn_cb_11);
        Button btnChna = rootView.findViewById(R.id.btn_cn_12);
        Button btnGybu = rootView.findViewById(R.id.btn_gb_13);
        Button btnGyna = rootView.findViewById(R.id.btn_gn_14);
        Button btnJubu = rootView.findViewById(R.id.btn_jb_15);
        Button btnJuna = rootView.findViewById(R.id.btn_jn_16);
        Button btnJeju = rootView.findViewById(R.id.btn_jj_17);

        btnSeul.setOnClickListener(v);
        btnInch.setOnClickListener(v);
        btnDeaj.setOnClickListener(v);
        btnDeag.setOnClickListener(v);
        btnGwan.setOnClickListener(v);
        btnBusa.setOnClickListener(v);
        btnUlsa.setOnClickListener(v);
        btnSejo.setOnClickListener(v);
        btnGyea.setOnClickListener(v);
        btnGang.setOnClickListener(v);
        btnChbu.setOnClickListener(v);
        btnChna.setOnClickListener(v);
        btnGybu.setOnClickListener(v);
        btnGyna.setOnClickListener(v);
        btnJubu.setOnClickListener(v);
        btnJuna.setOnClickListener(v);
        btnJeju.setOnClickListener(v);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        recyclerViewLocation.setLayoutManager(manager);
        List<Item> feedList = new ArrayList<>();
        adapter = new LocationRecyclerAdapter(feedList);
        recyclerViewLocation.setAdapter(adapter);

        return rootView;
    }

    private void setList(String areaCode) {

        service.listLocations("15",
                "TourAPI3.0_Guide",
                "27eTyCleBTtFCRPd1ct0WRpGIs6oJ8ah%2BYcibDG4E8WcoGP6T%2BjYaAr9deXHQwxEwTuY4wUaIq3LN8OdaXslwQ%3D%3D",
                areaCode,
                "",
                "A02",
                "A0207",
                "",
                "Y",
                "AND",
                "A",
                "100",
                "1",
                "json").enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                List<Item> posts = response.body().getResponse().getBody().getItems().getItem();
                if (posts != null) {
                    adapter.setItems(posts);
                    adapter.notifyItemRangeInserted(0, posts.size());
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });

    }

    View.OnClickListener v = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_seul_1:
                    areaCode = "1";
                    setList(areaCode);
                    break;
                case R.id.btn_ic_2:
                    areaCode = "2";
                    setList(areaCode);
                    break;
                case R.id.btn_dj_3:
                    areaCode = "3";
                    setList(areaCode);
                    break;
                case R.id.btn_dg_4:
                    areaCode = "4";
                    setList(areaCode);
                    break;
                case R.id.btn_gj_5:
                    areaCode = "5";
                    setList(areaCode);
                    break;
                case R.id.btn_bs_6:
                    areaCode = "6";
                    setList(areaCode);
                    break;
                case R.id.btn_us_7:
                    areaCode = "7";
                    setList(areaCode);
                    break;
                case R.id.btn_sj_8:
                    areaCode = "8";
                    setList(areaCode);
                    break;
                case R.id.btn_gg_9:
                    areaCode = "9";
                    setList(areaCode);
                    break;
                case R.id.btn_gw_10:
                    areaCode = "10";
                    setList(areaCode);
                    break;
                case R.id.btn_cb_11:
                    areaCode = "11";
                    setList(areaCode);
                    break;
                case R.id.btn_cn_12:
                    areaCode = "12";
                    setList(areaCode);
                    break;
                case R.id.btn_gb_13:
                    areaCode = "13";
                    setList(areaCode);
                    break;
                case R.id.btn_gn_14:
                    areaCode = "14";
                    setList(areaCode);
                    break;
                case R.id.btn_jb_15:
                    areaCode = "15";
                    setList(areaCode);
                    break;
                case R.id.btn_jn_16:
                    areaCode = "16";
                    setList(areaCode);
                    break;
                case R.id.btn_jj_17:
                    areaCode = "17";
                    setList(areaCode);
                    break;
            }
        }
    };

}
