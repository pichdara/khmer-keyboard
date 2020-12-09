package com.example.khmer_keyboard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Theme extends Activity implements View.OnClickListener {
    ImageView back,white, defaultBoard, blue, purple, orange, red;
    LinearLayout bigboard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
        back = findViewById(R.id.back_key);
        white = findViewById(R.id.white_board);
        defaultBoard = findViewById(R.id.dark_board);
//        blue = findViewById(R.id.blur_keyboard);
        purple = findViewById(R.id.purple);
        orange = findViewById(R.id.orange);
        red = findViewById(R.id.red_board);

        back.setOnClickListener(this);
//        white.setOnClickListener(this);
//        defaultBoard.setOnClickListener(this);
//        blue.setOnClickListener(this);
//        purple.setOnClickListener(this);
//        orange.setOnClickListener(this);
//        red.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        finish();
//     switch (view.getId()){
//         case R.id.white_board:
//            bigboard.setBackground(getResources().getDrawable(R.drawable.white_rounded_shape));
//            break;
//
//         default:
//             bigboard.setBackground(getResources().getDrawable(R.drawable.rounded_shape));
//            break;
//    }
}}