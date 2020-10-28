package com.example.webmasters.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.webmasters.ui.game_activity.gameActivity;
import com.example.webmasters.ui.graphic_design.GraphicDesignActivity;
import com.example.webmasters.R;
import com.example.webmasters.ui.web_store.WebStoreActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Fetch views from layout.
        Button buttonGraphicDesign = (Button) findViewById(R.id.buttonGraphicDesign);
        Button buttonWebStore = (Button) findViewById(R.id.buttonWebStore);
        Button buttonGame = (Button) findViewById(R.id.buttonGame);

        // Setup view callbacks.
        buttonGraphicDesign.setOnClickListener(this::onNavigationClick);
        buttonWebStore.setOnClickListener((this::onNavigationClick));
        buttonGame.setOnClickListener(this::onNavigationClick);
    }

    private void onNavigationClick(View view) {
        switch (view.getId()) {
            // Navigate to graphic design activity.
            case R.id.buttonGraphicDesign:
                Intent intentGraph = new Intent(this, GraphicDesignActivity.class);
                startActivity(intentGraph);
                break;
            case R.id.buttonWebStore:
                Intent intentStore = new Intent(this, WebStoreActivity.class);
                startActivity(intentStore);
                break;
            case R.id.buttonGame:
                Intent intentGame = new Intent(this, gameActivity.class);
                startActivity(intentGame);
                break;

            default:
                Log.e("MainActivity", "Invalid navigation!");
        }
    }
}