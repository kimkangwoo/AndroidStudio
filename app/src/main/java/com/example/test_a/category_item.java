package com.example.test_a;

import android.util.Log;

import androidx.annotation.NonNull;

public class category_item {
    private static final String Tag = "category_item";
    private String name;
    private String Image;
    private String MissionText;

    public void setcategory(@NonNull String image, @NonNull String name, String Text){
        this.name = name;
        this.Image = image;
        this.MissionText = Text;
        Log.d(Tag, name+": 카테고리 저장완료");
        }

    public String getname(){return name;}
    public String getImage(){return Image;}
    public String getMissionText(){return MissionText;}
}
