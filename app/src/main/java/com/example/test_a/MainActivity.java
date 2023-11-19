package com.example.test_a;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
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
                showalertdialog();
                Toast.makeText(MainActivity.this, "클릭 성공", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showalertdialog(){

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        Myadapter myadapter = new Myadapter();
        ArrayList<category_item> datalist = new ArrayList<>();

        if(recyclerView != null){
            // 데이터 리스트
            datalist.add(new category_item(R.drawable.sample, "이름", "사용 방법 표시"));
            datalist.add(new category_item(R.drawable.sample, "이름", "사용 방법 표시"));
            datalist.add(new category_item(R.drawable.sample, "이름", "사용 방법 표시"));

            myadapter.setLocalDataSet(datalist);
            recyclerView.setAdapter(myadapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        }else{
            Log.e("MainActivity", "RecyclerView is null");
        }



        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_categoical,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        // Positive 및 Negative 버튼 등 다른 설정 추가 가능


        // AlertDialog 객체 생성
        AlertDialog alertDialog = builder.create();

        // window 클릭 시 팝업 창 종료
        if(alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }

}