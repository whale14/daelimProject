package com.androidproject;

import android.graphics.drawable.Drawable;

public class MyListView {

    private Drawable myImg ;
    private String myTitle ;
    private String myDay ;

    public void setIcon(Drawable img) {
        myImg = img ;
    }
    public void setTitle(String title) {
        myTitle = title ;
    }
    public void setDesc(String day) {
        myDay = day ;
    }

    public Drawable getIcon() {
        return this.myImg ;
    }
    public String getTitle() {
        return this.myTitle ;
    }
    public String getDesc() {
        return this.myDay ;
    }


}
