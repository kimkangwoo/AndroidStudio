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
import java.util.zip.Inflater;

public class myadapter extends RecyclerView.Adapter<myadapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgView_item;
        TextView txt_main;
        TextView txt_sub;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgView_item = (ImageView) itemView.findViewById(R.id.show_gif);
            txt_main = (TextView) itemView.findViewById(R.id.name_tex);
            txt_sub = (TextView) itemView.findViewById(R.id.precaution);
        }
    }

    private ArrayList<myadapter> mList = null;

    public myadapter(ArrayList<myadapter> mList) {
        this.mList = mList;
    }

    // 아이템 뷰를 위한 뷰홀더 객체를 생성하여 리턴
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.category_item, parent, false);
        myadapter.ViewHolder vh = new myadapter.ViewHolder(view);
        return vh;
    }

    // position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(@NonNull myadapter.ViewHolder holder, int position) {
        myadapter item = mList.get(position);

        holder.imgView_item.setImageResource(R.drawable.ic_launcher_background);   // 사진 없어서 기본 파일로 이미지 띄움
        holder.txt_main.setText(item.getname());
        holder.txt_sub.setText(item.getMissionText());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


}
