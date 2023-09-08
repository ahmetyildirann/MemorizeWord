package com.example.memorizeword;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.memorizeword.client.RetrofitService;
import com.example.memorizeword.client.WordApi;
import com.example.memorizeword.client.WordC;
import com.example.memorizeword.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    WordAdapter wordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super. onCreate(savedInstanceState) ;

        binding = ActivityMainBinding. inflate (getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view) ;





        binding.saveForClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddWordActivityClient.class);
                intent.putExtra("info","new");
                startActivity(intent);
            }
        });

//        wordArrayList = new ArrayList<>();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

       getDataRetrofit();

        binding.swipeRefreshLayout.setOnRefreshListener(() -> {
            getDataRetrofit();
            binding.swipeRefreshLayout.setRefreshing(false);
        });


    }




    public void getDataRetrofit(){
        RetrofitService retrofitService = new RetrofitService();
        WordApi wordApi = retrofitService.getRetrofit().create(WordApi.class);
        wordApi.getWords()
                .enqueue(new Callback<List<WordC>>() {
                    @Override
                    public void onResponse(Call<List<WordC>> call, Response<List<WordC>> response) {
                        populateListView(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<WordC>> call, Throwable t) {

                    }
                });

    }

    private void populateListView(List<WordC> wordCList) {
        wordAdapter = new WordAdapter((ArrayList<WordC>) wordCList);
        binding.recyclerView.setAdapter(wordAdapter);

        wordAdapter.notifyDataSetChanged();

    }
        }