package com.inde.inde;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface RESTApi {

    @Headers(value = "Content-Type: application/json")

    @POST("/login")
    Call<ResponseBody> login(
            @Query("email") String email,
            @Query("password") String password);

    @Multipart
    @POST("/user")
    Call<ResponseBody> register(
            @Query("email") String email,
            @Query("password") String password,
            @Query("nickname") String nickname,
            @Query("name") String name,
            @Part MultipartBody.Part file);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://15.164.98.8:8084/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
