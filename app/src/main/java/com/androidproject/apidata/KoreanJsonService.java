package com.androidproject.apidata;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface KoreanJsonService {
    @GET("/openapi/service/rest/KorService/searchFestival?ServiceKey=27eTyCleBTtFCRPd1ct0WRpGIs6oJ8ah%2BYcibDG4E8WcoGP6T%2BjYaAr9deXHQwxEwTuY4wUaIq3LN8OdaXslwQ%3D%3D")
    Call<Example> listPosts(@Query("eventStartDate") String startDate,
                            @Query("eventEndDate") String endDate,
                            @Query("areaCode") String areaCode,
                            @Query("sigunguCode") String sigunguCode,
                            @Query("cat1") String cat1,
                            @Query("cat2") String cat2,
                            @Query("cat3") String cat3,
                            @Query("listYN") String listYN,
                            @Query("MobileOS") String mobileOS,
                            @Query("MobileApp") String mobileApp,
                            @Query("arrange") String arrange,
                            @Query("numOfRows") String numOfRows,
                            @Query("pageNo") String pageNo,
                            @Query("_type") String type);

    @GET("/openapi/service/rest/KorService/detailCommon")
    Call<com.androidproject.apidetails.Example> listDetails(@Query("contentTypeId") String typeId,
                                                            @Query("contentId") String contentId,
                                                            @Query("MobileApp") String mApp,
                                                            @Query("defaultYN") String defaultYN,
                                                            @Query("firstImageYN") String firstImageYN,
                                                            @Query("areacodeYN") String areaCode,
                                                            @Query("catcodeYN") String catCode,
                                                            @Query("addrinfoYN") String addrInfo,
                                                            @Query("mapinfoYN") String mapInfo,
                                                            @Query("overviewYN") String overView,
                                                            @Query("transGuideYN") String transGuideYN,
                                                            @Query(value = "ServiceKey", encoded = true) String serviceKey,
                                                            @Query("MobileOS") String mobileOS,
                                                            @Query("_type") String type);
}
