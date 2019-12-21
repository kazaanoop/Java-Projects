import javax.swing.*;
import java.awt.*;

public class GameView {
    public JPanel getCanvas() {
        return canvas;
    }

    private JPanel canvas;
    private final Board board;
    public final int DEFAULT_NODE_SIZE = 15;
    public GameView(Board board) {
        this.board = board;
        draw();
    }
    public void draw() {
        canvas = new JPanel() {
            @Override // need to override paint component to create custom graphic
            public void paintComponent(Graphics graphics) {
                //to clear the panel
                super.paintComponent(graphics);
                //Draw snake with existing length and location
                drawSnake(graphics, board.getSnake());
                //Draw food as per randomly set location
                drawFood(graphics, board.getFood());
            }
        };
    }
    public void drawSnake(Graphics graphics, Snake snake) {
        int i =0;
        for (Cell cell : snake.getBody()) {
            if(i==0){
                drawCircle(graphics, cell, Color.BLUE);
            }
            else
            drawSquare(graphics, cell, Color.GREEN);
            i++;
        }
    }
    public void drawFood(Graphics graphics, Cell squareArea) {
        drawCircle(graphics, squareArea, Color.RED);
    }
    private void drawSquare(Graphics graphics, Cell cell, Color color) {
        graphics.setColor(color);
        graphics.fillRect(cell.getX() * DEFAULT_NODE_SIZE, cell.getY() * DEFAULT_NODE_SIZE
                ,DEFAULT_NODE_SIZE - 1, DEFAULT_NODE_SIZE - 1);
    }
    private void drawCircle(Graphics graphics, Cell cell, Color color) {
        graphics.setColor(color);
        graphics.fillOval(cell.getX() * DEFAULT_NODE_SIZE-1, cell.getY() * DEFAULT_NODE_SIZE-1
                , DEFAULT_NODE_SIZE, DEFAULT_NODE_SIZE);
    }

    public void showGameOverMessage(int score) {
        JOptionPane.showMessageDialog(null, "Game Over! Your score is : "+score, "GameOver"
                , JOptionPane.INFORMATION_MESSAGE);
    }

    public void refresh() {
        canvas.repaint();
    }
}
