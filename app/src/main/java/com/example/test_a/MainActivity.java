package com.example.test_a;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.Data;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.cat_btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
                Toast.makeText(MainActivity.this, "클릭 성공", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showAlertDialog() {
        // 레이아웃 인플레이터를 사용하여 레이아웃 파일을 인플레이트
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_categoical, null);

        // RecyclerView 참조
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);

        // AlertDialog를 만들기 전에 recyclerView가 null인지 확인
        if (recyclerView != null) {
            // 데이터 리스트
            ArrayList<category_item> dataList = new ArrayList<>();
            dataList.add(new category_item(R.drawable.sample, "이름", "사용 방법 표시"));
            dataList.add(new category_item(R.drawable.sample, "이름", "사용 방법 표시"));
            dataList.add(new category_item(R.drawable.sample, "이름", "사용 방법 표시"));

            // RecyclerView에 어댑터 설정
            Myadapter myAdapter = new Myadapter();
            myAdapter.setLocalDataSet(dataList);

            // 화면에 하나의 아이템만 생성
            PagerSnapHelper snapHelper = new PagerSnapHelper();
            snapHelper.attachToRecyclerView(recyclerView);

            recyclerView.setAdapter(myAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        } else {
            // recyclerView가 null인 경우 에러 로그 출력
            Log.e("MainActivity", "RecyclerView is null");
            return; // recyclerView가 null이면 더 이상 진행하지 않음
        }

        // AlertDialog.Builder를 사용하여 AlertDialog 생성
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);

        // Positive 및 Negative 버튼 등 다른 설정 추가 가능

        // AlertDialog 객체 생성
        AlertDialog alertDialog = builder.create();

        // window 클릭 시 팝업 창 종료
        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        // AlertDialog를 보여줌
        alertDialog.show();
    }

}