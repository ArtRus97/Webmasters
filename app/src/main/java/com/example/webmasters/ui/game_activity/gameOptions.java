package com.example.webmasters.ui.game_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.webmasters.R;

public class gameOptions extends AppCompatActivity {
    MediaPlayer musicPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_options);
        musicPlayer = MediaPlayer.create(this, R.raw.game_menu_music);
        CheckBox chkbMusicOnOff = (CheckBox) findViewById(R.id.chkbMusicOnOff);
        chkbMusicOnOff.setOnCheckedChangeListener(this::OnCheckedChanged);
    }

    private void OnCheckedChanged(CompoundButton compoundButton, boolean b) {
        CheckBox checkBox = (CheckBox) findViewById(R.id.chkbMusicOnOff);
        if(checkBox.isChecked()){
            checkBox.setText("Enabled");
            musicPlayer.start();
        } else{
            checkBox.setText("Disabled");
            musicPlayer.stop();
        }
    }
}