package com.example.webmasters.ui.game_activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.webmasters.R;
import com.example.webmasters.ui.game_activity.engine.GameEngine;
import com.example.webmasters.ui.game_activity.enums.Direction;
import com.example.webmasters.ui.game_activity.enums.GameState;
import com.example.webmasters.ui.game_activity.views.SnakeView;
/******************************************************************************
NOTE: SNAKE IS ROTATED BY SWIPING TO THE DIRECTION OF WHERE YOU WANNA TURN TO.
 ******************************************************************************/
/******************************************
 Activity used for running the game.
******************************************/
public class gameActivity extends AppCompatActivity implements View.OnTouchListener {
    MediaPlayer musicPlayer;

    //Variables for game-view, game-engine, movement-speed interval
    private GameEngine gameEngine;
    private SnakeView snakeView;
    private final Handler handler = new Handler();
    private final long updateDelay = 200;


    //variables used for mouse-event
    private float prevX, prevY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Initializing the game and it's view
        gameEngine = new GameEngine();
        gameEngine.initGame();
        snakeView = (SnakeView) findViewById(R.id.snakeView);
        snakeView.setOnTouchListener(this);
        musicPlayer = MediaPlayer.create(this, R.raw.game_music);
        musicPlayer.start();

        startUpdateHandler();
    }

    //Runs the game
    private void startUpdateHandler(){
        handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    gameEngine.Update();
                    if( gameEngine.getCurrentGameState() == GameState.Running ) {
                        handler.postDelayed(this, updateDelay);
                    }
                    if(gameEngine.getCurrentGameState() == GameState.Lost){
                        OnGameLost();
                    }

                snakeView.setSnakeViewMap(gameEngine.getMap());
                snakeView.invalidate();
            }
        }, updateDelay);
    }

    //Called when the game ends
    private void OnGameLost(){
        Toast.makeText(this, "You lost.", Toast.LENGTH_SHORT).show();
        musicPlayer.stop();
        musicPlayer = MediaPlayer.create(this, R.raw.game_over_sound);
        musicPlayer.start();
    }


    @Override //Touch event handling user input
    public boolean onTouch(View v, MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                prevX = event.getX();
                prevY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                float newX = event.getX();
                float newY = event.getY();

                //Calculate where we swiped
                if(Math.abs( newX - prevX ) > Math.abs(newY - prevY)){
                    //LEFT - RIGHT direction
                    if( newX > prevX){
                        //RIGHT
                        gameEngine.UpdateDirection(Direction.East);
                    } else {
                        //LEFT
                        gameEngine.UpdateDirection(Direction.West);
                    }
                } else {
                    //UP - DOWN direction
                    if( newY > prevY){
                        //DOWN
                        gameEngine.UpdateDirection(Direction.South);
                    } else {
                        //UP
                        gameEngine.UpdateDirection(Direction.North);
                    }
                } break;
        } return true;
    }

    protected void onPause() {
        super.onPause();
        musicPlayer.stop();
    }
}
