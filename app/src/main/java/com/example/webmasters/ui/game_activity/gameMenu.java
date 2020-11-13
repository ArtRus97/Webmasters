package com.example.webmasters.ui.game_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.webmasters.R;
import com.example.webmasters.ui.graphic_design.GraphicDesignActivity;
import com.example.webmasters.ui.web_store.WebStoreActivity;


public class gameMenu extends AppCompatActivity {
    MediaPlayer musicPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);
        Button buttonGame = (Button) findViewById(R.id.playButton);
        Button buttonShop = (Button) findViewById(R.id.shopButton);
        Button buttonOptions = (Button) findViewById(R.id.optionsButton);
        buttonGame.setOnClickListener(this::onNavigationClick);
        buttonShop.setOnClickListener(this::onNavigationClick);
        buttonOptions.setOnClickListener(this::onNavigationClick);
        musicPlayer = MediaPlayer.create(this, R.raw.game_menu_music);
        musicPlayer.start();
    }

    private void onNavigationClick(View view) {
        switch (view.getId()) {
            // Navigate in the menu
            case R.id.playButton:
                musicPlayer.stop();
                Intent intentGraph = new Intent(this, gameActivity.class);
                startActivity(intentGraph);
                break;
            case R.id.shopButton:
                Intent intentGraph2 = new Intent(this, gameShop.class);
                startActivity(intentGraph2);
                break;
            case R.id.optionsButton:
                Intent intentGraph3 = new Intent(this, gameOptions.class);
                startActivity(intentGraph3);
                break;

            default:
                Log.e("MainActivity", "Invalid navigation!");
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        musicPlayer.stop();
    }
}