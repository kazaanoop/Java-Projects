import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakeGame  {
    private static final int BOARD_WIDTH = 30;
    private static final int BOARD_HEIGHT = 30;
    private static GameView gameView;
    private  static Controller control;
    static int difficulty;

    public static void main(String[] args) {
        //Initial frame to set difficulty
        JFrame diff = new JFrame("Difficulty");
        JButton b1;JButton b2;JButton b3;
        b1 = new JButton("Easy");
        b2 = new JButton("Medium");
        b3 = new JButton("Hard");
        b1.setBounds(50,60,80, 30);
        b2.setBounds(150,60,80, 30);
        b3.setBounds(250,60,80, 30);
        diff.add(b1);
        diff.add(b2);
        diff.add(b3);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                difficulty = 150;
                startGame();
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                difficulty = 100;
                startGame();
            }
        });
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                difficulty = 50;
                startGame();
            }
        });

        diff.setLayout(null);
        diff.setSize(400,200);
        diff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        diff.setVisible(true);
    }

    static void startGame(){
        //Creating frame
        JFrame frame = new JFrame("Anoop's Snake Game");

        //Getting content pane to place custom graphics of snake and food
        Container contentPane = frame.getContentPane();

        //Create board
        Board board = new Board(BOARD_WIDTH,BOARD_HEIGHT);

        //Create snake
        board.createSnake();

        //Create food
        board.createFood();

        //Draw snake and food on content pane
        gameView = new GameView(board);

        // set JPanel's size
        gameView.getCanvas().setPreferredSize(new Dimension(BOARD_WIDTH * gameView.DEFAULT_NODE_SIZE,
                BOARD_HEIGHT * gameView.DEFAULT_NODE_SIZE));

        contentPane.add(gameView.getCanvas(), BorderLayout.CENTER);

        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        //Start game
        control = new Controller(board,gameView);
        frame.addKeyListener(control);
        new Thread(control).start();
    }

}
