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
    private ArrayList<category_item> localDataSet = null;
    // 데이터 리스트 및 생성자 선언
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView precausion;
        private ImageView showGIF;
        public ViewHolder(@NonNull View view){
            super(view);

            name = (TextView) view.findViewById(R.id.name_tex);
            precausion = (TextView) view.findViewById(R.id.precaution);
            showGIF = (ImageView) view.findViewById(R.id.show_gif);
        }
        void onBind(category_item item){
            showGIF.setImageResource(item.getImage());
            name.setText(item.getname());
            precausion.setText(item.getMissionText());
        }

    }

    // viewholder 객체를 생성하여 리턴한다.
    public Myadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_item, parent, false);
        Myadapter.ViewHolder viewHolder = new Myadapter.ViewHolder(view);

        return viewHolder;
    }

    //ViewHolder안의 내용을 position에 해당되는 데이터로 교체
    public void onBindViewHolder(@NonNull Myadapter.ViewHolder holder, final int position){
        holder.onBind(localDataSet.get(position));
    }

    // 전체 데이터의 갯수를 리턴
    public int getItemCount(){
        return localDataSet.size();
    }

    public void setLocalDataSet(ArrayList<category_item> list){
        localDataSet = list;
        notifyDataSetChanged();
    }

}
