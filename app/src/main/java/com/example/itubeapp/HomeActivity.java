package com.example.itubeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    Button play, addList, list;
    EditText url;
    ArrayList<String> playlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        play = findViewById(R.id.button4);
        addList = findViewById(R.id.button5);
        list = findViewById(R.id.button6);
        url = findViewById(R.id.editTextTextPersonName4);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!url.getText().toString().equals("")) {
                    Intent i = new Intent(HomeActivity.this, WebViewActivity.class);
                    i.putExtra("url", url.getText().toString());
                    i.putExtra("playlist", playlist);
                    startActivity(i);
                }
            }
        });

        addList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlText = url.getText().toString();
                if (!urlText.equals("")) {
                    playlist.add(urlText);
                }
            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(HomeActivity.this, PlaylistActivity.class);
                j.putStringArrayListExtra("playlist", playlist);
                startActivity(j);
            }
        });
    }
}