package com.example.whatsappchatappclone.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.whatsappchatappclone.R;
import com.example.whatsappchatappclone.Tools;
import com.example.whatsappchatappclone.adapter.AdapterChatWhatsapp;
import com.example.whatsappchatappclone.model.ChatMessage;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ChatActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initToolbar();
        initComponent();
    }
    RecyclerView recycler_view;
    AdapterChatWhatsapp adapter;
    private void initComponent() {
        recycler_view = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(layoutManager);
        recycler_view.setHasFixedSize(true);

        adapter = new AdapterChatWhatsapp(this);
        recycler_view.setAdapter(adapter);

        adapter.insertItem(new ChatMessage(adapter.getItemCount(),
                Tools.getFormattedTimeEvent(System.currentTimeMillis()),
                "Hai",true,
                adapter.getItemCount() % 5 == 0));
        adapter.insertItem(new ChatMessage(adapter.getItemCount(),
                Tools.getFormattedTimeEvent(System.currentTimeMillis()),"Hello",
                false,adapter.getItemCount() % 5 == 0));
        btn_send = findViewById(R.id.btn_send);
        et_content = findViewById(R.id.text_content);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendChat();

            }
        });

        if(et_content != null){
            et_content.addTextChangedListener(contentWatcher);
        }
//        et_content.addTextChangedListener(contentWatcher);
        (findViewById(R.id.lyt_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        hideKeyboard();
    }
    private void hideKeyboard(){
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager imm =(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

    private TextWatcher contentWatcher = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable etd) {
            if(etd.toString().trim().length() == 0){
//            if(etd.toString().length() == 0){
                btn_send.setImageResource(R.drawable.ic_mic);
            }else {
                btn_send.setImageResource(R.drawable.ic_send);
            }

        }
        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

        }


    };
    private  void sendChat (){
        final String msg =et_content.getText().toString();
        if(msg.isEmpty()) return;
        adapter.insertItem(new ChatMessage(adapter.getItemCount(),
                Tools.getFormattedTimeEvent(System.currentTimeMillis()) ,
                msg,true,
                adapter.getItemCount() % 5 == 0));
        et_content.setText("");
        recycler_view.scrollToPosition(adapter.getItemCount() - 1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.insertItem(new ChatMessage(adapter.getItemCount(),
                        Tools.getFormattedTimeEvent(System.currentTimeMillis()) ,
                        msg,false,
                        adapter.getItemCount() % 5 == 0));
                recycler_view.scrollToPosition(adapter.getItemCount() - 1);


            }
        },2000);
    }

    private ActionBar actionBar;
    private FloatingActionButton btn_send;
    private TextView et_content;
    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setTitle(null);
        Tools.setSystemBarColorInt(this, Color.parseColor("#054D44"));

    }




}