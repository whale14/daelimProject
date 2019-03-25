package com.androidproject;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import com.google.firebase.firestore.FirebaseFirestore;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    public PackageManager getPackageManager() {
        return super.getPackageManager();
    }


    private FirebaseFirestore db;
    private ViewPager viewPager;
    private TabLayout tabLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                    Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.ic_location);
                    Objects.requireNonNull(tabLayout.getTabAt(2)).setIcon(R.drawable.ic_my_location);
                    Objects.requireNonNull(tabLayout.getTabAt(3)).setIcon(R.drawable.ic_person);

                    switch (i) {
                        case 0 :
                            Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.ic_home_black);
                            break;
                        case 1 :
                            Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.ic_location_black);
                            break;
                        case 2 :
                            Objects.requireNonNull(tabLayout.getTabAt(2)).setIcon(R.drawable.ic_my_location_black);
                            break;
                        case 3 :
                            Objects.requireNonNull(tabLayout.getTabAt(3)).setIcon(R.drawable.ic_person_black);
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
            Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.ic_location);
            Objects.requireNonNull(tabLayout.getTabAt(2)).setIcon(R.drawable.ic_my_location);
            Objects.requireNonNull(tabLayout.getTabAt(3)).setIcon(R.drawable.ic_person);
        }
    }

}
