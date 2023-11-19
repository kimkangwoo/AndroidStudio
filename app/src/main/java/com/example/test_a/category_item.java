package com.example.test_a;

import android.util.Log;

import androidx.annotation.NonNull;

public class category_item {
    private static final String Tag = "category_item";
    private String name;
    private int Image;
    private String MissionText;

    public category_item(int image, String name, String Text){
        this.name = name;
        this.Image = image;
        this.MissionText = Text;
    }

    public String getname(){return name;}
    public int getImage(){return Image;}
    public String getMissionText(){return MissionText;}
}
