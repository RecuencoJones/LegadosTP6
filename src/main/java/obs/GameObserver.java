package obs;

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
        movements[6] = Player.Move.DOWN;
        movements[7] = Player.Move.DOWN;
        movements[8] = Player.Move.DOWN;
        movements[9] = Player.Move.RIGHT;
        movements[10] = Player.Move.RIGHT;
        movements[11] = Player.Move.RIGHT;
    }
    private static JPanel panel;
    private static Player player;
    private static Objective obj;
    private static JFrame jf;

    public GameObserver(){
        jf = new JFrame();
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        obj = new Objective();
        jf.add(obj);
        player = new Player(obj.getThis());
        jf.add(player);
        jf.pack();
        jf.setSize(BOUNDARY,BOUNDARY);
        jf.setLocationRelativeTo(null);
        jf.update(jf.getGraphics());
        jf.setVisible(true);
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        if(arg instanceof int[]) {
            int[] note = (int[]) arg;
            player.move(movements[note[0]]);
            jf.update(jf.getGraphics());
        }
    }
}
