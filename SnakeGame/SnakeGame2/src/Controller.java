import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements Runnable , KeyListener {

    private final Board board;
    private final GameView gameView;
    private boolean gameRunning;

    public Controller(Board board, GameView gameView) {
        this.board = board;
        this.gameView = gameView;
        this.gameRunning = true;
    }

    @Override
    public void run() {
        while (gameRunning) {
            try {
                Thread.sleep(SnakeGame.difficulty);                     //DEFAULT_MOVE_INTERVAL
            } catch (InterruptedException e) {
                break;
            }
            board.isDirectionChanged = false;
            if (board.nextRound() == true) {
                gameView.refresh();
            } else {
                gameView.showGameOverMessage(board.getScore());
                gameRunning = false;
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        //System.out.println("key typed");

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        //System.out.println("key pressed");
        int keyCode = keyEvent.getKeyCode();
        if (board.isDirectionChanged == false) {
            switch (keyCode) {
                case KeyEvent.VK_UP :
                    board.changeDirection("UP");
                    break;
                case KeyEvent.VK_RIGHT :
                    board.changeDirection("RIGHT");
                    break;
                case KeyEvent.VK_DOWN :
                    board.changeDirection("DOWN");
                    break;
                case KeyEvent.VK_LEFT :
                    board.changeDirection("LEFT");
                    break;
                case KeyEvent.VK_SPACE :
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        //System.out.println("key released");
    }
}
