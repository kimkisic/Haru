package com.kisicsoft.harutest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class ItemActivity extends AppCompatActivity {

    ImageView img;
    TextView title;
    TextView star;
    TextView review;
    TextView distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        img = findViewById(R.id.main_image);

        Intent intent = getIntent();
    }
}
