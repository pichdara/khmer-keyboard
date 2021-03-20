package com.example.khmer_keyboard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentProviderClient;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;

public class Howto extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageView gs, back;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howto);
        gs = findViewById(R.id.get_started);
        back = findViewById(R.id.back_key);
        gs.setOnClickListener(this);
        back.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.get_started : startActivityForResult(new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS), 0);break;
            case R.id.back_key : finish();break;
            default:break;
        }
    }
}