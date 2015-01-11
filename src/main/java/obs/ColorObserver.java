package obs;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by David Recuenco on 28/12/2014.
 */
public class ColorObserver implements Observer {

    private static Color[] colors = new Color[12];
    {
        colors[0] = Color.RED;
        colors[1] = Color.MAGENTA;
        colors[2] = Color.PINK;
        colors[3] = Color.BLUE;
        colors[4] = Color.CYAN;
        colors[5] = Color.GREEN;
        colors[6] = Color.YELLOW;
        colors[7] = Color.ORANGE;
        colors[8] = Color.decode("0xA87507");
        colors[9] = Color.DARK_GRAY;
        colors[10] = Color.GRAY;
        colors[11] = Color.WHITE;
    }
    private static JPanel panel;

    public ColorObserver(){
        JFrame jf = new JFrame();
        jf.setTitle("Color Observer");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel = new JPanel();
        panel.setBackground(Color.BLACK);
        jf.add(panel);
        jf.pack();
        jf.setSize(500,500);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = ge.getDefaultScreenDevice();
        Rectangle rectangle = device.getDefaultConfiguration().getBounds();
        int x = (int) (rectangle.getMaxX()*0.4);
        int y = (int) (rectangle.getMaxY()*0.25);
        jf.setLocation(x, y);
        jf.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof int[]) {
            int[] note = (int[]) arg;
            if (note[3] == 1) {
                switch (note[1]) {
                    case 0:
                        panel.setBackground(colors[note[0]].darker().darker().darker().darker().darker());
                        break;
                    case 1:
                        panel.setBackground(colors[note[0]].darker().darker().darker().darker());
                        break;
                    case 2:
                        panel.setBackground(colors[note[0]].darker().darker().darker());
                        break;
                    case 3:
                        panel.setBackground(colors[note[0]].darker().darker());
                        break;
                    case 4:
                        panel.setBackground(colors[note[0]].darker());
                        break;
                    case 5:
                        panel.setBackground(colors[note[0]]);
                        break;
                    case 6:
                        panel.setBackground(colors[note[0]].brighter());
                        break;
                    case 7:
                        panel.setBackground(colors[note[0]].brighter().brighter());
                        break;
                    case 8:
                        panel.setBackground(colors[note[0]].brighter().brighter().brighter());
                        break;
                    case 9:
                        panel.setBackground(colors[note[0]].brighter().brighter().brighter().brighter());
                        break;
                    case 10:
                        panel.setBackground(colors[note[0]].brighter().brighter().brighter().brighter().brighter());
                        break;
                    default:
                        panel.setBackground(colors[note[0]]);
                }
            }
        }
    }
}
