
package com.androidproject.apidata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    private String addr1;
    private int areacode;
    private String cat1;
    private String cat2;
    private String cat3;
    private int contentid;
    private int contenttypeid;
    private long createdtime;
    private long eventenddate;
    private long eventstartdate;
    private String firstimage;
    private String firstimage2;
    private double mapx;
    private double mapy;
    private int mlevel;
    private long modifiedtime;
    private int readcount;
    private int sigungucode;
    private String tel;
    private String title;
    private String addr2;

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public int getAreacode() {
        return areacode;
    }

    public void setAreacode(int areacode) {
        this.areacode = areacode;
    }

    public String getCat1() {
        return cat1;
    }

    public void setCat1(String cat1) {
        this.cat1 = cat1;
    }

    public String getCat2() {
        return cat2;
    }

    public void setCat2(String cat2) {
        this.cat2 = cat2;
    }

    public String getCat3() {
        return cat3;
    }

    public void setCat3(String cat3) {
        this.cat3 = cat3;
    }

    public int getContentid() {
        return contentid;
    }

    public void setContentid(int contentid) {
        this.contentid = contentid;
    }

    public int getContenttypeid() {
        return contenttypeid;
    }

    public void setContenttypeid(int contenttypeid) {
        this.contenttypeid = contenttypeid;
    }

    public long getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(long createdtime) {
        this.createdtime = createdtime;
    }

    public long getEventenddate() {
        return eventenddate;
    }

    public void setEventenddate(long eventenddate) {
        this.eventenddate = eventenddate;
    }

    public long getEventstartdate() {
        return eventstartdate;
    }

    public void setEventstartdate(long eventstartdate) {
        this.eventstartdate = eventstartdate;
    }

    public void setEventstartdate(int eventstartdate) {
        this.eventstartdate = eventstartdate;
    }

    public String getFirstimage() {
        return firstimage;
    }

    public void setFirstimage(String firstimage) {
        this.firstimage = firstimage;
    }

    public String getFirstimage2() {
        return firstimage2;
    }

    public void setFirstimage2(String firstimage2) {
        this.firstimage2 = firstimage2;
    }

    public double getMapx() {
        return mapx;
    }

    public void setMapx(double mapx) {
        this.mapx = mapx;
    }

    public double getMapy() {
        return mapy;
    }

    public void setMapy(double mapy) {
        this.mapy = mapy;
    }

    public int getMlevel() {
        return mlevel;
    }

    public void setMlevel(int mlevel) {
        this.mlevel = mlevel;
    }

    public long getModifiedtime() {
        return modifiedtime;
    }

    public void setModifiedtime(long modifiedtime) {
        this.modifiedtime = modifiedtime;
    }

    public int getReadcount() {
        return readcount;
    }

    public void setReadcount(int readcount) {
        this.readcount = readcount;
    }

    public int getSigungucode() {
        return sigungucode;
    }

    public void setSigungucode(int sigungucode) {
        this.sigungucode = sigungucode;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    @Override
    public String toString() {
        return "Item{" +
                "addr1='" + addr1 + '\'' +
                ", areacode=" + areacode +
                ", cat1='" + cat1 + '\'' +
                ", cat2='" + cat2 + '\'' +
                ", cat3='" + cat3 + '\'' +
                ", contentid=" + contentid +
                ", contenttypeid=" + contenttypeid +
                ", createdtime=" + createdtime +
                ", eventenddate=" + eventenddate +
                ", eventstartdate=" + eventstartdate +
                ", firstimage='" + firstimage + '\'' +
                ", firstimage2='" + firstimage2 + '\'' +
                ", mapx=" + mapx +
                ", mapy=" + mapy +
                ", mlevel=" + mlevel +
                ", modifiedtime=" + modifiedtime +
                ", readcount=" + readcount +
                ", sigungucode=" + sigungucode +
                ", tel='" + tel + '\'' +
                ", title='" + title + '\'' +
                ", addr2='" + addr2 + '\'' +
                '}';
    }
}
