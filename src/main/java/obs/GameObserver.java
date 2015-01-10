package obs;

import game.myDemo;

import javax.swing.*;
import java.awt.*;
import java.util.Observer;

/**
 * Created by David Recuenco on 28/12/2014.
 */
public class GameObserver implements Observer {

    public static final int BOUNDARY = 500;

    private static Player.Move[] movements = new Player.Move[12];
    {
        movements[0] = Player.Move.UP;
        movements[1] = Player.Move.UP;
        movements[2] = Player.Move.UP;
        movements[3] = Player.Move.LEFT;
        movements[4] = Player.Move.LEFT;
        movements[5] = Player.Move.LEFT;
        movements[6] = Player.Move.RIGHT;
        movements[7] = Player.Move.RIGHT;
        movements[8] = Player.Move.RIGHT;
        movements[9] = Player.Move.DOWN;
        movements[10] = Player.Move.DOWN;
        movements[11] = Player.Move.DOWN;
    }
    private static myDemo demo;

    public GameObserver(){
        demo = new myDemo(12);
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        if(arg instanceof int[]) {
            int[] note = (int[]) arg;
            demo.move(movements[note[0]]);
        }
    }
}
