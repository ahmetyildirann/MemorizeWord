package com.example.memorizeword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.memorizeword.client.RetrofitService;
import com.example.memorizeword.client.WordApi;
import com.example.memorizeword.client.WordC;
import com.example.memorizeword.databinding.ActivityAddWordClientBinding;


import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddWordActivityClient extends AppCompatActivity {
    
    private ActivityAddWordClientBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddWordClientBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        Intent intent = getIntent();
        String info = intent.getStringExtra("info");
        

        
    }



        RetrofitService retrofitService = new RetrofitService();
        WordApi wordApi = retrofitService.getRetrofit().create(WordApi.class);


           public void wordSave(View v){

            String word = binding.word.getText().toString();
            String explanation = binding.explanation.getText().toString();
            String meaning = binding.meaning.getText().toString();


            WordC wordC = new WordC();
            wordC.setWord(word);
            wordC.setExplanation(explanation);
            wordC.setMeaning(meaning);

            wordApi.save(wordC)
                    .enqueue(new Callback<WordC>() {
                        @Override
                        public void onResponse(Call<WordC> call, Response<WordC> response) {
                            Toast.makeText(AddWordActivityClient.this, "Word saved", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<WordC> call, Throwable t) {
                            Toast.makeText(AddWordActivityClient.this, "Error", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(AddWordActivityClient.class.getName()).log(Level.SEVERE, "Error Occures", t);
                        }
                    });

               Intent intent = new Intent(AddWordActivityClient.this, MainActivity.class);
               intent.addFlags (Intent. FLAG_ACTIVITY_CLEAR_TOP);
               startActivity(intent);


        }



}


/*
    public void wordSave(View v){

        String name = binding.addWordActivityEditTextKitapIsmi.getText().toString();
        String yazar = binding.addWordActivityEditTextKitapYazari.getText().toString();
        String ozet = binding.addWordActivityEditTextKitapOzeti.getText().toString();

        try {

            database = this.openOrCreateDatabase("Words", MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS words(id INTEGER PRIMARY KEY, wordname VARCHAR, yazarname VARCHAR, ozet VARCHAR) ");

            String sqlString = "INSERT INTO words (wordname, yazarname, ozet) VALUES(?, ?, ?)";
            SQLiteStatement sqlLiteStatement = database.compileStatement(sqlString) ;
            sqlLiteStatement.bindString(1,name);
            sqlLiteStatement.bindString(2,yazar);
            sqlLiteStatement.bindString(3,ozet);
            sqlLiteStatement.execute();

            Intent intent = new Intent(AddWordsActivity.this, MainActivity.class);
            intent.addFlags (Intent. FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);


        }catch (Exception e){
            e.printStackTrace();
        }


    }
*/