package com.example.khmer_keyboard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class Dictionary extends Activity implements View.OnClickListener {
    ImageView back;
    SearchView mySearchView;
    ListView myList;

    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        back = findViewById(R.id.back_key);
        mySearchView = (SearchView)findViewById(R.id.searchView);
        myList = (ListView)findViewById(R.id.myList);

        list = new ArrayList<String>();

        list.add("ក");
        list.add("កក");
        list.add("កករ");
        list.add("កកាត");
        list.add("កកាយ");
        list.add("កកិចកកុច");
        list.add("កកិត");
        list.add("កកិល");
        list.add("កកូរ");
        list.add("កកេបកកាប");
        list.add("កកេរ");
        list.add("កកេះ");

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        myList.setAdapter(adapter);

        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);

                return false;
            }
        });
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
