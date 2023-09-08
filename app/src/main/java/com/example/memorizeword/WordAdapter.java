package com.example.memorizeword;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memorizeword.client.WordC;
import com.example.memorizeword.databinding.RecyclerViewBinding;

import java.util.ArrayList;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordHolder> {
    ArrayList<WordC> wordArrayList;

    public WordAdapter(ArrayList<WordC> wordArrayList) {
        this.wordArrayList = wordArrayList;
    }

    @NonNull
    @Override
    public WordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerViewBinding recyclerViewBinding = RecyclerViewBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new WordHolder(recyclerViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull WordHolder holder, @SuppressLint("RecyclerView") int position) {
    holder.binding.recyclerTextView.setText(wordArrayList.get(position).getWord());
    holder.binding.extraTextView.setText(wordArrayList.get(position).getExplanation());
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(holder.itemView.getContext(),AddWordsActivity.class);
            intent.putExtra("info","old");
            intent.putExtra("wordId",wordArrayList.get(position).getId());
            holder.itemView.getContext().startActivity(intent);

        }
    });


    }

    @Override
    public int getItemCount() {
        return wordArrayList.size();
    }

    public class WordHolder extends RecyclerView.ViewHolder{
        private RecyclerViewBinding binding;


        public WordHolder(RecyclerViewBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
