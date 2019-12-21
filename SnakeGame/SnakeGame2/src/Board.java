import javafx.scene.control.skin.TextInputControlSkin;

import java.util.Random;

public class Board {
    private final int width;
    private final int height;
    //array to track cells covered by snake
    private boolean[][] isSnake;
    private Snake snake;
    private Cell food;
    public boolean isDirectionChanged = false;
    private int score = 0;
    String snakeDirection = "LEFT"; //Initial snake direction

    Board(int w, int h){
        width = w;
        height = h;
        isSnake = new boolean[width][height];
    }

    //Create snake of length 3 including head
    public Snake createSnake(){
        snake = new Snake();
        for(int i=0;i<3;i++){
            snake.addTail(new Cell(i+width/2,height/2));
            isSnake[i+width/2][height/2] = true;
        }
        return snake;
    }

    //Creating food at random point which is not occupied by snake
    public Cell createFood() {
        int x,y;
        do {
            x = new Random( ).nextInt(width);
            y = new Random( ).nextInt(height);
        } while (isSnake[x][y] == true);
        food = new Cell(x, y);
        return food;
    }

    public boolean nextRound() {                                     //follow the direction and move one step
        if (isMoveValid(snakeDirection)) {
            Cell move = snake.move(snakeDirection);
            if (snake.isEatingFood(food)) {                             //if ate food, add the Node moved at tail
                snake.addTail(move);
                createFood();
                score++;
            } else isSnake[move.getX()][move.getY()] = false;
            return true;
        } else return false;
    }

    private boolean isMoveValid(String direction) {
        int headX = snake.getHead().getX();
        int headY = snake.getHead().getY();
        switch(direction) {
            case "UP" :
                headY--;
                break;
            case "RIGHT" :
                headX++;
                break;
            case "DOWN" :
                headY++;
                break;
            case "LEFT" :
                headX--;
                break;
        }
        if (headX < 0 || headX >= width || headY < 0 || headY >= height) return false;
        if (isSnake[headX][headY] == true) return false;
        isSnake[headX][headY] = true;
        return true;
    }

    public void changeDirection(String newDirection) {
        snakeDirection = newDirection;
        isDirectionChanged = true;
    }

    //Getters
    public Snake getSnake() {
        return snake;
    }

    public Cell getFood() {
        return food;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getScore() {
        return score;
    }

}
