package com.example.englishapp.view;

import android.graphics.Bitmap;

public class Type {

    int id;

    public String getDescription() {
        return description;
    }

    String description;
    Bitmap bitmap;

    public String getName() {
        return name;
    }

    String name;

    public Type(int id, String description, Bitmap bitmap, String name) {
        this.id = id;
        this.description = description;
        this.bitmap = bitmap;
        this.name = name;
    }

    public int getId() {
        return id;
    }


    public Bitmap getBitmap() {
        return bitmap;
    }

}
