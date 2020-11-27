package com.example.khmer_keyboard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutUs extends Activity implements View.OnClickListener{
    TextView email, phone, facebook, website, location;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        phone = findViewById(R.id.phone_num);
        facebook = findViewById(R.id.facebook_contact);
        website = findViewById(R.id.website);
        location = findViewById(R.id.location);
        back = findViewById(R.id.back_key);

        phone.setOnClickListener(this);
        facebook.setOnClickListener(this);
        website.setOnClickListener(this);
        location.setOnClickListener(this);
        back.setOnClickListener(this);
;    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){

            case R.id.phone_num:
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "+855 12 123 456", null)));
                break;

            case R.id.facebook_contact:
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.google.com"));
                startActivity(intent);
                break;
            case R.id.website:
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.typeyourURL.com"));
                startActivity(intent);
                break;
            case R.id.location:
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://goo.gl/maps/c1K637WArhQyugkt8"));
                startActivity(intent);
                break;
            case R.id.back_key : finish();break;

            default: break;


        }

    }
}