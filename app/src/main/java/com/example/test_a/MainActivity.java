package com.example.test_a;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.cat_btn);

        ArrayList<String> testDataSet = new ArrayList<>();
        for (int i = 0; i<20; i++) {
            testDataSet.add("TEST DATA" + i);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showalertdialog();
                Toast.makeText(MainActivity.this, "클릭 성공", Toast.LENGTH_SHORT).show();

            }
        });



    }

    private void showalertdialog(){
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.category_item,null);
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