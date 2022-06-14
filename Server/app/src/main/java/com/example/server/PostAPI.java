package com.example.server;

import android.text.Editable;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PostAPI {
    @GET("/post/{post-id}")
    Call<PostResponse> fetchPost(@Path("post-id") Editable postId);
}