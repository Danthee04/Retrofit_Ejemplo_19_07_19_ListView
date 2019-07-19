package com.example.retrofit_ejemplo_19_07_19.Interfaz;

import com.example.retrofit_ejemplo_19_07_19.Modelo.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<Posts>>getPosts();
}
