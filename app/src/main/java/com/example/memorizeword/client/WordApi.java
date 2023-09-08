package com.example.memorizeword.client;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WordApi {

    @GET("words")
    Call<List<WordC>> getWords();

    @POST("words")
    Call<WordC> save(@Body WordC wordC);

}
