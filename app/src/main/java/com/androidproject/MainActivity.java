package com.androidproject;

import android.content.pm.PackageManager;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    public PackageManager getPackageManager() {
        return super.getPackageManager();
    }


    private FirebaseFirestore db;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar_main;



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
        return super.onCreateOptionsMenu(menu);
    }
}
