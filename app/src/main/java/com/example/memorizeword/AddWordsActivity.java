package com.example.memorizeword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.memorizeword.databinding.ActivityAddWordsBinding;

public class AddWordsActivity extends AppCompatActivity {


    private ActivityAddWordsBinding binding;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_add_words);

        binding = ActivityAddWordsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        database = this.openOrCreateDatabase("Words", MODE_PRIVATE, null);

        Intent intent = getIntent();
        String info = intent.getStringExtra("info");

        if (info.matches("new")) {
            binding.addWordActivityEditTextKitapIsmi.setText("");
            binding.addWordActivityEditTextKitapOzeti.setText("");
            binding.addWordActivityEditTextKitapYazari.setText("");
            binding.button.setVisibility(View.VISIBLE);;


        } else {
            int artId = intent.getIntExtra("wordId",1);
            binding.button.setVisibility(View.INVISIBLE);

            try {

                Cursor cursor = database.rawQuery("SELECT * FROM words WHERE id = ?",new String[] {String.valueOf(artId)});

                int artNameIx = cursor.getColumnIndex("wordname");
                int painterNameIx = cursor.getColumnIndex("ozet");
                int yearIx = cursor.getColumnIndex("yazarname");


                while (cursor.moveToNext()) {

                    binding.addWordActivityEditTextKitapIsmi.setText(cursor.getString(artNameIx));
                    binding.addWordActivityEditTextKitapOzeti.setText(cursor.getString(painterNameIx));
                    binding.addWordActivityEditTextKitapYazari.setText(cursor.getString(yearIx));


                }

                cursor.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



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
}