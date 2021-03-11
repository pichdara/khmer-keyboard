package com.example.khmer_keyboard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import android.widget.ImageView;



public class MainActivity extends Activity {

    ImageView image;
    private static int SPLASH_SCREEN = 1200;
    SharedPreferences theme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        theme = getApplicationContext().getSharedPreferences("theme", Context.MODE_PRIVATE);
//        SharedPreferences.Editor edit = theme.edit();
//        edit.putString("default_theme", "true");
//        edit.commit();

        //Animation


        //Hooks

        image = findViewById(R.id.banner);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }
}