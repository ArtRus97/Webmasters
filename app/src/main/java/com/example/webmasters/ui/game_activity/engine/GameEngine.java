package com.example.webmasters.ui.game_activity.engine;

import androidx.constraintlayout.solver.widgets.analyzer.Direct;

import com.example.webmasters.ui.game_activity.classes.Coordinate;
import com.example.webmasters.ui.game_activity.enums.Direction;
import com.example.webmasters.ui.game_activity.enums.GameState;
import com.example.webmasters.ui.game_activity.enums.TileType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**********************************
 Class used for game's logic
 **********************************/
public class GameEngine {
    //Game view's size
    public static final int GameWidth = 28;
    public static final int GameHeight = 42;

    //Variables used for game view's objects
    private List<Coordinate> walls = new ArrayList<>();
    private List<Coordinate> snake = new ArrayList<>();
    private List<Coordinate> apples = new ArrayList<>();

    private Random random = new Random();
    private boolean increaseTail = false;

    //On start snake's direction is initialized heading East by default.
    private Direction currentDirection = Direction.East;
    private GameState currentGameState = GameState.Running;
    private Coordinate getSnakeHead(){
        return snake.get(0);
    }
    public GameEngine(){}

    //Initializes the game's objects
    public void initGame(){
        AddSnake();
        AddWalls();
        AddApples();
    }

    //Updates the direction of snake's movement
    public void UpdateDirection(Direction newDirection){
        if(currentDirection == Direction.East)
        {
            if(newDirection != Direction.West)
            {
                if( Math.abs(newDirection.ordinal() - currentDirection.ordinal()) % 2 == 1);
                currentDirection = newDirection;
            }
        }
        if(currentDirection == Direction.West)
        {
            if(newDirection != Direction.East)
            {
                if( Math.abs(newDirection.ordinal() - currentDirection.ordinal()) % 2 == 1);
                currentDirection = newDirection;
            }
        }
        if(currentDirection == Direction.North)
        {
            if(newDirection != Direction.South)
            {
                if( Math.abs(newDirection.ordinal() - currentDirection.ordinal()) % 2 == 1);
                currentDirection = newDirection;
            }
        }
        if(currentDirection == Direction.South)
        {
            if(newDirection != Direction.North)
            {
                if( Math.abs(newDirection.ordinal() - currentDirection.ordinal()) % 2 == 1);
                currentDirection = newDirection;
            }
        }
    }

    //Updating the game overall
    public void Update(){
        // Update the snake's direction
        switch(currentDirection){
            case North:
                UpdateSnake(0,-1);
                break;
            case East:
                UpdateSnake(1,0);
                break;
            case South:
                UpdateSnake(0,1);
                break;
            case West:
                UpdateSnake(-1,0);
                break;
        }
        //Check wall collision
        for(Coordinate w : walls){
            if(snake.get(0).equals(w)){
                currentGameState = GameState.Lost;
            }
        }
        //Check self collision
        for(int i = 1 ; i < snake.size() ; i++){
            if(getSnakeHead().equals(snake.get(i))){
                currentGameState = GameState.Lost;
                return;
            }
        }
        //Check if apples are eaten
        Coordinate appleToRemove = null;
        for(Coordinate apple : apples){
            if(getSnakeHead().equals(apple)){
                appleToRemove = apple;
                increaseTail = true;
            }
        }
        if(appleToRemove != null){
            apples.remove(appleToRemove);
            AddApples();
        }
    }
    //Return the map's content
    public TileType[][] getMap(){
        TileType[][] map = new TileType[GameWidth][GameHeight];
        for(int x = 0; x < GameWidth; x++){
            for(int y = 0; y < GameHeight; y++){
                map[x][y] = TileType.Nothing;
            }
        }
        for(Coordinate wall: walls){
            map[wall.getX()][wall.getY()] = TileType.Wall;
        }
        for (Coordinate s : snake){
            map[s.getX()][s.getY()] = TileType.SnakeTail;
        }
        for(Coordinate a : apples){
            map[a.getX()][a.getY()] = TileType.Apple;
        }

        map[snake.get(0).getX()][snake.get(0).getY()] = TileType.SnakeHead;
        return map;
    }

    //Function moving the snake towards it's direction
    private void UpdateSnake(int x, int y){
        int newX = snake.get(snake.size() - 1).getX();
        int newY = snake.get(snake.size() - 1).getY();

        for(int i = snake.size()-1; i > 0; i--){
            snake.get(i).setX( snake.get(i-1).getX() );
            snake.get(i).setY( snake.get(i-1).getY() );
        }
        if(increaseTail){
            snake.add(new Coordinate(newX, newY));
            increaseTail = false;
        }
        snake.get(0).setX( snake.get(0).getX() + x);
        snake.get(0).setY( snake.get(0).getY() + y);
    }

    //Creates the snake on start, length of 6 tiles by default
    private void AddSnake(){
        snake.clear();
        snake.add(new Coordinate(7,7));
        snake.add(new Coordinate(6,7));
        snake.add(new Coordinate(5,7));
        snake.add(new Coordinate(4,7));
        snake.add(new Coordinate(3,7));
        snake.add(new Coordinate(2,7));
    }

    //Creates the map boundaries
    private void AddWalls(){
        //Top and bottom walls
        for(int x = 0; x < GameWidth; x++){
            walls.add(new Coordinate(x, 0));
            walls.add(new Coordinate(x, GameHeight-1));
        }
        //Left and Right walls
        for(int y = 1; y < GameHeight; y++){
            walls.add(new Coordinate(0,y));
            walls.add(new Coordinate(GameWidth-1, y));
        }
    }
    //Adding the game's collectables, in this case: Apples
    private void AddApples(){
        Coordinate coordinate = null;
        boolean added = false;

        while ( !added ){
            int x = 1 + random.nextInt( GameWidth - 2 );
            int y = 1 + random.nextInt( GameHeight - 2 );
            coordinate = new Coordinate(x,y);
            boolean collision = false;

            for(Coordinate s : snake) {
                if( s.equals(coordinate)){
                    collision = true;
                    break;
                }
            }

            for(Coordinate a : apples){
                if(a.equals(coordinate)){
                    collision = true;
                    break;
                }
            }
            added = !collision;
        }
        apples.add(coordinate);
    }
    //Called upon updating the game
    public GameState getCurrentGameState(){
        return currentGameState;
    }

}
