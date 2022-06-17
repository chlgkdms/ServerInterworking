package com.example.server;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.server.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private PostAPI postAPI;

    private Editable postId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        postAPI = ApiProvider.getRetrofit().create(PostAPI.class);
        postId = binding.etNumber.getText();

        binding.btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postAPI.fetchPost(postId).enqueue(new Callback<PostResponse>() {
                    @Override
                    public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                        if (response.isSuccessful()) {
                            binding.tvResult.setText(response.body().getContent());
                            binding.tvTitle.setText(response.body().getTitle());
                            binding.tvId.setText(response.body().getId());
                        } else if(response.code() == 404) {
                            Toast.makeText(getApplicationContext(),"user not found!",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<PostResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "error!",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}