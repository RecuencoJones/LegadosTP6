package obs;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by David Recuenco on 28/12/2014.
 */
public class Objective extends JPanel {

    private static Ellipse2D.Double objective;
    private static final double radius = 30.0;

    public Objective() {
        this.objective = new Ellipse2D.Double(250.0,250.0,radius,radius);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.GREEN);
        g2d.fillRect(250,250,30,30);
        g2d.setPaint(Color.BLACK);
        g2d.drawRect(250,250,30,30);
    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(30,30);
    }

    public Ellipse2D.Double getThis(){
        return objective;
    }
}
