package com.androidproject;

import android.content.pm.PackageManager;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    public PackageManager getPackageManager() {
        return super.getPackageManager();
    }


    private FirebaseFirestore db;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar_main;

    //Navigation drawer
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.ic_home);
                    Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.ic_rate_review);
                    Objects.requireNonNull(tabLayout.getTabAt(2)).setIcon(R.drawable.ic_person);

                    switch (i) {
                        case 0 :
                            Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.ic_home_black);
                            break;
                        case 1 :
                            Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.ic_rate_review_black);
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.ic_home_black);
            Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.ic_rate_review);
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
                Snackbar.make(toolbar_main,"1번",Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.menu2:
                Snackbar.make(toolbar_main,"2번",Snackbar.LENGTH_SHORT).show();
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
