package com.example.webmasters.ui.game_activity.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.webmasters.ui.game_activity.enums.TileType;
/****************************************
 Class used for creating game's graphics
 ****************************************/
public class SnakeView extends View{
    //Variables used for game's graphics
    private Paint mPaint = new Paint();
    private TileType snakeViewMap[][];

    //Initializing the view when called
    public SnakeView(Context context, AttributeSet attrs){
        super(context, attrs);
    }
    public void setSnakeViewMap( TileType[][] map) { this.snakeViewMap = map; }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(snakeViewMap != null){
            float tileSizeX = canvas.getWidth() / snakeViewMap.length;
            float tileSizeY = canvas.getHeight() / snakeViewMap[0].length;
            float circleSize = Math.min(tileSizeX, tileSizeY) / 2;
            //Painting the game map
            for(int x = 0; x < snakeViewMap.length; x++){
                for(int y = 0; y < snakeViewMap[x].length; y++){
                    switch (snakeViewMap[x][y]){
                        case Nothing:
                            mPaint.setColor(Color.GRAY);
                            break;
                        case Wall:
                        case SnakeTail:
                            mPaint.setColor(Color.GREEN);
                            break;
                        case SnakeHead:
                            mPaint.setColor(Color.YELLOW);
                            break;
                        case Apple:
                            mPaint.setColor(Color.RED);
                            break;
                    }
                    canvas.drawCircle(x * tileSizeX + tileSizeX / 2f + circleSize / 2, y * tileSizeY + tileSizeY / 2f + circleSize / 2, circleSize, mPaint);
                }
            }
        }
    }

}
