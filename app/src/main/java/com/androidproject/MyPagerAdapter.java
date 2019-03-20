package com.androidproject;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;

import java.util.ArrayList;

public class MyPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mData;

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);

        mData = new ArrayList<>();

        mData.add(new HomeFragment());
        mData.add(new LocationFragment());
        mData.add(new PeripheryFragment());
        mData.add(new MyPageFragment());
    }

    @Override
    public Fragment getItem(int i) {
        return mData.get(i);
    }

    @Override
    public int getCount() {
        return mData.size();
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }
}
