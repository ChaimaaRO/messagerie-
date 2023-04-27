package com.example.whatsappchatappclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.whatsappchatappclone.activities.ChatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Button myButton = (Button) findViewById(R.id.my_button);
    }

    public void open_chat(View view) {
        Intent i =new Intent(this, ChatActivity.class);
        startActivity(i);
    }
}