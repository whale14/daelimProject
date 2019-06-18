package com.androidproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private SharedPreferences sp_titleList;
    private SharedPreferences.Editor editor_titleList;

    @Override
    public PackageManager getPackageManager() {
        return super.getPackageManager();
    }


    private TextView userName, userEmail;
    private ImageView userPhoto, logout;
    private FirebaseUser user;
    private FirebaseFirestore db;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar_main;

    private SharedPreferences sp_login, sp_idList;
    private SharedPreferences.Editor editor_login, editor_idList;

    //Navigation drawer
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @SuppressLint("CommitPrefEdits")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //로그아웃시 로그인상태를 초기화하기 위함
        sp_login = getSharedPreferences("loginInfo", MODE_PRIVATE);
        editor_login = sp_login.edit();

        sp_idList = getSharedPreferences("idList", MODE_PRIVATE);
        editor_idList = sp_idList.edit();

        sp_titleList = getSharedPreferences("titleList", MODE_PRIVATE);
        editor_titleList = sp_titleList.edit();

        //파이어베이스 인증 로그인정보
        user = FirebaseAuth.getInstance().getCurrentUser();

        //툴바(상단바)
        toolbar_main = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar_main);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black);

        viewPager = findViewById(R.id.pager);
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout = findViewById(R.id.tab);

        //Navigation drawer
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerLayout);
        navigationView=(NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        //드라워 메뉴 이벤트리스너 구현을 위한 뷰 정의
        View navHeaderView = navigationView.getHeaderView(0);

        userPhoto = navHeaderView.findViewById(R.id.image_user);
        userName = navHeaderView.findViewById(R.id.text_user_name);
        userEmail = navHeaderView.findViewById(R.id.text_user_email);
        userName.setText(user.getDisplayName());
        userEmail.setText(user.getEmail());

        userPhoto.setImageDrawable(getDrawable(R.drawable.ic_person));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.ic_home);
                    Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.ic_my_location);
                    Objects.requireNonNull(tabLayout.getTabAt(2)).setIcon(R.drawable.ic_person);

                    switch (i) {
                        case 0 :
                            Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.ic_home_black);
                            break;
                        case 1 :
                            Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.ic_my_location_black);
                            break;
                        case 2 :
                            Objects.requireNonNull(tabLayout.getTabAt(2)).setIcon(R.drawable.ic_person_black);
                            break;
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        tabLayout.setupWithViewPager(viewPager);

        //탭레이아웃 아이콘적용
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.ic_home_black);
            Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.ic_my_location);
            Objects.requireNonNull(tabLayout.getTabAt(2)).setIcon(R.drawable.ic_person);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
       // getMenuInflater().inflate(R.menu.menu_drawer,menu);//drawer 메뉴항목
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){//왼쪽 홈버튼 드로어 오픈
            drawerLayout.openDrawer(navigationView);

        }
        return super.onOptionsItemSelected(item);
    }

    //navigation 메뉴 항목을 눌렀을 때 처리
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.menu1:
                //Snackbar.make(toolbar_main,"1번",Snackbar.LENGTH_SHORT).show();
//                Intent intent = new Intent(this, MyListActivity.class);
//                startActivity(intent);
                startActivity(new Intent(getApplicationContext(), MyListActivity.class));
                break;
//            case R.id.menu2:
//                Snackbar.make(toolbar_main,"2번",Snackbar.LENGTH_SHORT).show();
//                break;
            case R.id.menu_logout:

                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("주의");
                builder.setMessage("로그아웃시 즐겨찾기 리스트가 삭제됩니다. 그래도 진행하시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //로그인정보 초기화
                        editor_login.clear();
                        editor_login.commit();
                        //id리스트 초기화
                        editor_idList.clear();
                        editor_idList.commit();

                        editor_titleList.clear();
                        editor_titleList.apply();

                        // 파이어베이스 인증 로그아웃
                        AuthUI.getInstance()
                                .signOut(getApplicationContext())
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                    }
                                });
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));

                        finish();
                    }
                });
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();

                break;
        }
        drawerLayout.closeDrawers();
        return false;
    }

    //뒤로가기 버튼을 눌렀을 때
    @Override
    public void onBackPressed() {//drawer가 열려 있을 때의 처리
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }
}
