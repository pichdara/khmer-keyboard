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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Theme extends Activity implements View.OnClickListener {
    ImageView back,white, defaultBoard, blue, purple, orange, red;
    SharedPreferences theme ;
    RadioButton bBlack, bRed, bBlue, bPurple, bWhite, bOrange;



    void setCheckedRadio (String theme_name){
        switch (theme_name){
            case "default":
                bBlue.setChecked(false);
                bOrange.setChecked(false);
                bBlack.setChecked(true);
                bPurple.setChecked(false);
                bRed.setChecked(false);
                bWhite.setChecked(false);
                break;
            case "blue":
                bBlue.setChecked(true);
                bOrange.setChecked(false);
                bBlack.setChecked(false);
                bPurple.setChecked(false);
                bRed.setChecked(false);
                bWhite.setChecked(false);
                break;
            case "red":
                bBlue.setChecked(false);
                bOrange.setChecked(false);
                bBlack.setChecked(false);
                bPurple.setChecked(false);
                bRed.setChecked(true);
                bWhite.setChecked(false);
                break;
            case "white":
                bBlue.setChecked(false);
                bOrange.setChecked(false);
                bBlack.setChecked(false);
                bPurple.setChecked(false);
                bRed.setChecked(false);
                bWhite.setChecked(true);
                break;
            case "purple":
                bBlue.setChecked(false);
                bOrange.setChecked(false);
                bBlack.setChecked(false);
                bPurple.setChecked(true);
                bRed.setChecked(false);
                bWhite.setChecked(false);
                break;
            case "orange":
                bBlue.setChecked(false);
                bOrange.setChecked(true);
                bBlack.setChecked(false);
                bPurple.setChecked(false);
                bRed.setChecked(false);
                bWhite.setChecked(false);
                break;


        }

    }

    void setSharedPreference (String theme_name){
        SharedPreferences.Editor edit = theme.edit();
        switch (theme_name){
            case "default":
                edit.putString("theme_default", "false");
                edit.putString("theme_name", "default");
                edit.putString("theme_bg_color","#313131" );
                edit.putString("theme_font_color","#default" );
                edit.commit();
                break;
            case "blue":
                edit.putString("theme_default", "false");
                edit.putString("theme_name", "blue");
                edit.putString("theme_bg_color","#6a9fef" );
                edit.putString("theme_font_color","#default" );
                edit.commit();
                break;
            case "red":

                edit.putString("theme_default", "false");
                edit.putString("theme_name", "red");
                edit.putString("theme_bg_color","#c85f5f" );
                edit.putString("theme_font_color","default" );
                edit.commit();
                break;
            case "white":
                edit.putString("theme_default", "false");
                edit.putString("theme_name", "white");
                edit.putString("theme_bg_color","#cecece" );
                edit.putString("theme_font_color","white_theme_font_color" );
                edit.commit();
                break;
            case "purple":
                edit.putString("theme_default", "false");
                edit.putString("theme_name", "purple");
                edit.putString("theme_bg_color","#9489d8" );
                edit.putString("theme_font_color","#default" );
                edit.commit();
                break;
            case "orange":
                edit.putString("theme_default", "false");
                edit.putString("theme_name", "orange");
                edit.putString("theme_bg_color","#f2af61" );
                edit.putString("theme_font_color","#default" );
                edit.commit();
                break;


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        theme = getSharedPreferences("theme", Context.MODE_PRIVATE);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
        back = findViewById(R.id.back_key);
        white = findViewById(R.id.white_board);
        bBlack = findViewById(R.id.radioButtonBlack);
        bBlue = findViewById(R.id.radioButtonBlue);
        bWhite = findViewById(R.id.radioButtonWhite);
        bPurple = findViewById(R.id.radioButtonPurple);
        bRed = findViewById(R.id.radioButtonRed);
        bOrange = findViewById(R.id.radioButtonOrange);
        defaultBoard = findViewById(R.id.dark_board);
        blue = findViewById(R.id.blue_board);
        purple = findViewById(R.id.purple);
        orange = findViewById(R.id.orange);
        red = findViewById(R.id.red_board);
        back.setOnClickListener(this);

        Log.d("PIUKeyboard", "theme opened");
        Log.d("PIUKeyboard", theme.getString("theme_name", ""));

        setCheckedRadio(theme.getString("theme_name", ""));



        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSharedPreference("red");
                setCheckedRadio(theme.getString("theme_name", ""));
            }
        });

        orange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSharedPreference("orange");
                setCheckedRadio(theme.getString("theme_name", ""));

            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSharedPreference("blue");
                setCheckedRadio(theme.getString("theme_name", ""));
            }
        });
        purple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSharedPreference("purple");
                setCheckedRadio(theme.getString("theme_name", ""));

            }
        });

        white.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSharedPreference("white");
                setCheckedRadio(theme.getString("theme_name", ""));

            }
        });

        defaultBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSharedPreference("default");
                setCheckedRadio(theme.getString("theme_name", ""));

            }
        });

        bRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSharedPreference("red");
                setCheckedRadio(theme.getString("theme_name", ""));

            }
        });
        bOrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSharedPreference("orange");
                setCheckedRadio(theme.getString("theme_name", ""));


            }
        });

        bBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSharedPreference("blue");
                setCheckedRadio(theme.getString("theme_name", ""));



            }
        });
        bPurple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSharedPreference("purple");
                setCheckedRadio(theme.getString("theme_name", ""));

            }
        });

        bWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSharedPreference("white");
                setCheckedRadio(theme.getString("theme_name", ""));

            }
        });

        bBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSharedPreference("default");

                setCheckedRadio(theme.getString("theme_name", ""));

            }
        });






    }

    @Override
    public void onClick(View view) {
        finish();

}}