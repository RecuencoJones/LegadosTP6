package game;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Semaphore;

import no.geosoft.cc.graphics.*;
import obs.Player;

/**
 * Created by David Recuenco on 30/12/2014.
 */
public class myDemo extends JFrame implements GInteraction {

    private Game game;
    private GameBoard gameBoard;
    private Semaphore s = new Semaphore(1), p = new Semaphore(1);

    public static void main(String[] args) {
        int boardSize = 8;
        new myDemo(boardSize);
    }

    public myDemo(int boardSize){
        super("G Graphics Library - myDemo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the GUI
        JPanel topLevel = new JPanel();
        topLevel.setLayout(new BorderLayout());
        getContentPane().add(topLevel);

        // Create the graphic canvas
        GWindow window = new GWindow(new Color(220, 220, 220));
        topLevel.add(window.getCanvas(), BorderLayout.CENTER);

        // Create scene
        GScene scene = new GScene(window);
        double w0[] = {0.0, 0.0, 0.0};
        double w1[] = {boardSize + 2.0, 0.0, 0.0};
        double w2[] = {0.0, boardSize + 2.0, 0.0};
        scene.setWorldExtent(w0, w1, w2);

        // Create the game and graphics representation
        game = new Game(boardSize);
        gameBoard = new GameBoard(game);
        scene.add(gameBoard);
		gameBoard.drawBase();

        pack();
        setSize(new Dimension(500, 500));
        setVisible(true);

        // Make sure plot can be scrolled
        window.startInteraction(this);
    }

    @Override
    public void event(GScene scene, int event, int x, int y) {
        if (scene == null) return;
        if(game.isGameEnded()) return;
    }

    public void move(Player.Move move){
        /*try {
            p.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        int i = game.getPlayerX(), j = game.getPlayerY();
        switch (move) {
            case UP:
				if(game.isLegalMove(i,j+1) && !game.isGameEnded()){
					movePos(i,j+1);
				}
                break;
            case DOWN:
				if(game.isLegalMove(i,j-1) && !game.isGameEnded()){
					movePos(i,j-1);
				}
                break;
            case RIGHT:
				if(game.isLegalMove(i+1,j) && !game.isGameEnded()){
					movePos(i+1,j);
				}
                break;
            case LEFT:
				if(game.isLegalMove(i-1,j) && !game.isGameEnded()){
					movePos(i-1,j);
				}
                break;
        }
        //p.release();
    }

    private void movePos(int i, int j){
        /*try {
            s.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
		gameBoard.removePlayer();
		game.move(i, j);
		gameBoard.getScene().redraw();
		gameBoard.getScene().refresh();
        //s.release();
    }
}
