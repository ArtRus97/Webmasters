package com.example.webmasters.ui.web_store;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.example.webmasters.R;
import com.example.webmasters.ui.WebStoreSingleton;

public class WebStoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_store);

        TextView desc = findViewById(R.id.textViewDesc);
        WebStoreSingleton singleton = WebStoreSingleton.Singleton();
        desc.setText(singleton.desc);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.webstore_menu, menu);
        return true;
    }
}