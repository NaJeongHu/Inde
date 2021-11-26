package com.inde.inde;

import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface RESTApi {

    @Headers(value = "Content-Type: application/json")

    @POST("/login")
    Call<ResponseBody> login(
            @Body HashMap<String, String> user);

//    @Multipart
//    @POST("/user")
//    Call<ResponseBody> register(
//            @FieldMap HashMap<user, Object> user,
//            @FieldMap HashMap<String, Object> email,
//            @Part MultipartBody.Part file);

    @Multipart
    @POST("/user")
    Call<ResponseBody> register(
            @PartMap() HashMap<String, user> user,
            @Part MultipartBody.Part file);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://15.164.98.8:8084/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
