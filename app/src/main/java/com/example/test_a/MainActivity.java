package com.example.test_a;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
// wlstladmfh
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.cat_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
        alertDialog.show();
    }
//  private void showCustomDialog(){

//      AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);


//      builder.setView(view);
//      ((ImageView)view.findViewById(R.id.show_gif)).setImageDrawable(R.drawable.sample);
//      ((TextView)view.findViewById(R.id.name_tex)).setText(name);
//      ((TextView)view.findViewById(R.id.name_tex));

//      if(alertDialog.getWindow() != null){
//          alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
//      }

//      alertDialog.show();
//  }
}