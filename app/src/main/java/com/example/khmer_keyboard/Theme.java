package com.example.khmer_keyboard;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Theme extends Activity implements View.OnClickListener {
    ImageView back,white, defaultBoard, blue, purple, orange, red;
    SharedPreferences theme ;







    @Override
    protected void onCreate(Bundle savedInstanceState) {

        theme = getSharedPreferences("theme", Context.MODE_PRIVATE);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
        back = findViewById(R.id.back_key);
        white = findViewById(R.id.white_board);

        defaultBoard = findViewById(R.id.dark_board);
        blue = findViewById(R.id.blue_board);
        purple = findViewById(R.id.purple);
        orange = findViewById(R.id.orange);
        red = findViewById(R.id.red_board);
        back.setOnClickListener(this);
        Log.d("PIUKeyboard", "theme opened");
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor edit = theme.edit();
                edit.putString("theme_default", "true");
                edit.putString("theme_name", "red");
                edit.putString("theme_bg_color","#c85f5f" );
                edit.putString("theme_font_color","default" );
                edit.commit();
            }
        });
        orange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor edit = theme.edit();
                edit.putString("theme_default", "false");
                edit.putString("theme_name", "orange");
                edit.putString("theme_bg_color","#f2af61" );
                edit.putString("theme_font_color","#default" );
                edit.commit();


            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("PIUKeyboard", "orange is clikced");
                SharedPreferences.Editor edit = theme.edit();
                edit.putString("theme_default", "false");
                edit.putString("theme_name", "blue");
                edit.putString("theme_bg_color","#6a9fef" );
                edit.putString("theme_font_color","#default" );

                edit.commit();

            }
        });
        purple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("PIUKeyboard", "orange is clikced");
                SharedPreferences.Editor edit = theme.edit();
                edit.putString("theme_default", "false");
                edit.putString("theme_name", "purple");
                edit.putString("theme_bg_color","#9489d8" );
                edit.putString("theme_font_color","#default" );

                edit.commit();
            }
        });

        white.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("PIUKeyboard", "orange is clikced");
                SharedPreferences.Editor edit = theme.edit();
                edit.putString("theme_default", "false");
                edit.putString("theme_name", "white");
                edit.putString("theme_bg_color","#cecece" );
                edit.putString("theme_font_color","white_theme_font_color" );

                edit.commit();
            }
        });

        defaultBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("PIUKeyboard", "orange is clikced");
                SharedPreferences.Editor edit = theme.edit();
                edit.putString("theme_default", "false");
                edit.putString("theme_name", "default");
                edit.putString("theme_bg_color","#313131" );
                edit.putString("theme_font_color","#default" );
                edit.commit();
            }
        });

    }

    @Override
    public void onClick(View view) {
        finish();

}}