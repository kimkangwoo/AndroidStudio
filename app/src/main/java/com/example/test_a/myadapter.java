package com.example.test_a;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.zip.Inflater;

public class myadapter extends RecyclerView.Adapter<myadapter.MyViewHolder> {
    @NonNull
    @Override
    public myadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_categoical, parent, false);

        return new MyViewHolder(itemView);
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // 아이템 뷰 내부의 뷰들을 참조하는 변수들을 정의
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            // 뷰홀더 생성자에서 뷰들을 초기화
            textView = itemView.findViewById(R.id.textView);
        }

        public void bind(String data) {
            // 뷰홀더에 데이터를 바인딩
            textView.setText(data);
        }
    }
}
