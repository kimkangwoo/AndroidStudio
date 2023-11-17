package com.example.test_a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder> {
    private ArrayList<String> localDataSet;
    // 데이터 리스트 및 생성자 선언
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }

    // 생성자
    public Myadapter(ArrayList<String> dataSet){
        localDataSet = dataSet;
    }

    // viewholder 객체를 생성하여 리턴한다.
    public Myadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_item, parent, false);
        Myadapter.ViewHolder viewHolder = new Myadapter.ViewHolder(view);

        return viewHolder;
    }

    //ViewHolder안의 내용을 position에 해당되는 데이터로 교체
    public void onBindViewHolder(@NonNull Myadapter.ViewHolder holder, int position){

        String text = localDataSet.get(position);
    }

    // 전체 데이터의 갯수를 리턴
    public int getItemCount(){
        return localDataSet.size();
    }
}
