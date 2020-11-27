package com.example.khmer_keyboard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Dashboard extends Activity implements View.OnClickListener{
    ImageView d1, d2, d3, d4, d5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        d1 = findViewById(R.id.m1);
        d2 = findViewById(R.id.m2);
        d3 = findViewById(R.id.m3);
        d4 = findViewById(R.id.m4);


        //applying clicklistener
        d1.setOnClickListener(this);
        d2.setOnClickListener(this);
        d3.setOnClickListener(this);
        d4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()){
            case R.id.m1 : i = new Intent(this, Howto.class);startActivity(i) ;break;
            case R.id.m2 : i = new Intent(this, Theme.class);startActivity(i);break;
            case R.id.m3 : i = new Intent(this, Dictionary.class);startActivity(i);break;
            case R.id.m4 : i = new Intent(this, AboutUs.class);startActivity(i);break;
            default:break;
        }
    }
}