package obs;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by David Recuenco on 28/12/2014.
 */
public class Objective extends JComponent {

    private static Ellipse2D.Double objective;
    private static final double radius = 30.0;

    public Objective() {
        this.objective = new Ellipse2D.Double(250.0,250.0,radius*2 ,radius*2 );
        objective.setFrame(250,250,radius*2,radius*2);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.GREEN);
        g2d.fill(objective);
        g2d.drawRect(250,250,(int) radius*2,(int) radius*2);
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.GREEN);
        g2d.fill(objective);
        g2d.drawRect(250,250,(int) radius*2,(int) radius*2);
    }

    public Ellipse2D.Double getThis(){
        return objective;
    }
}
