package com.androidproject;

import com.google.firebase.firestore.Exclude;

import java.util.HashMap;
import java.util.Map;

// https://blog.naver.com/PostView.nhn?blogId=nife0719&logNo=221049879862&parentCategoryNo=&categoryNo=26&viewDate=&isShowPopularPosts=false&from=postView
// 여기 참고해서 만드는 중 !!

public class FirebaseDatabase {
    public String test1;


    public FirebaseDatabase(String test1) {
        this.test1 = test1;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("test1", test1);
        return result;
    }




}
