package com.androidproject;

import java.util.Date;

public class FireStoreModel {
    private String title;
    private String img1;
    private String img2;
    private String addr;
    private String tel;
    private long start;
    private long end;

    public FireStoreModel() {
    }

    public FireStoreModel(String title, String img1, String img2, String addr, String tel, long start, long end) {
        this.title = title;
        this.img1 = img1;
        this.img2 = img2;
        this.addr = addr;
        this.tel = tel;
        this.start = start;
        this.end = end;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }
}
