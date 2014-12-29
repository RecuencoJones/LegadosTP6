package obs;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by David Recuenco on 28/12/2014.
 */
public class Player extends JComponent {

    public static enum Move {UP, DOWN, LEFT, RIGHT};

    private static Ellipse2D.Double player;
    private static Ellipse2D.Double objective;
    private static final double radius = 30.0;

    public Player(Ellipse2D.Double objective){
        this.objective = objective;
        this.player = new Ellipse2D.Double(500,500,radius ,radius );
    }

    public void move(Move m){
        switch(m){
            case UP:
                if(player.getY() >= (radius *2)) {
                    player.setFrame(player.getX(),
                            player.getY() - radius,
                            radius ,
                            radius );
                }
                break;
            case DOWN:
                if(player.getY() <= GameObserver.BOUNDARY - (radius *2)) {
                    player.setFrame(player.getX(),
                            player.getY() + radius,
                            radius ,
                            radius );
                }
                break;
            case LEFT:
                if(player.getX() >= (radius *2)) {
                    player.setFrame(player.getX() - radius,
                            player.getY(),
                            radius ,
                            radius );
                }
                break;
            case RIGHT:
                if(player.getX() <= GameObserver.BOUNDARY - (radius *2)) {
                    player.setFrame(player.getX() + radius,
                            player.getY(),
                            radius ,
                            radius );
                }
                break;
            default:
                break;
        }
        checkSolution();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.RED);
        g2d.fill(player);
        g2d.draw(player);
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.RED);
        g2d.fill(player);
        g2d.draw(player);
    }

    private void checkSolution(){
        if(!player.getBounds2D().createIntersection(objective.getBounds2D()).isEmpty()){
            System.exit(0);
        }
    }
}
