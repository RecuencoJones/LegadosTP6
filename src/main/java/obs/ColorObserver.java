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
    private static JPanel[] panel = new JPanel[16];

    public ColorObserver(){
        JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setLayout(new BorderLayout());

        JPanel container = new JPanel();
        container.setLayout(new GridLayout(4,4));
        for(int i = 0; i < panel.length; i++){
            panel[i] = new JPanel();
            panel[i].setBackground(Color.BLACK);
            panel[i].setSize(250,250);
            JLabel label = new JLabel("Channel "+(i+1));
            label.setForeground(Color.WHITE);
            panel[i].add(label);
            container.add(panel[i]);
        }
        jf.add(container);
        jf.pack();
        jf.setSize(1000, 1000);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof int[]){
            int[] note = (int[]) arg;
            if(note[3]==1){
                switch(note[1]){
                    case 0:
                        panel[note[2]].setBackground(colors[note[0]].darker().darker().darker().darker().darker());
                        break;
                    case 1:
                        panel[note[2]].setBackground(colors[note[0]].darker().darker().darker().darker());
                        break;
                    case 2:
                        panel[note[2]].setBackground(colors[note[0]].darker().darker().darker());
                        break;
                    case 3:
                        panel[note[2]].setBackground(colors[note[0]].darker().darker());
                        break;
                    case 4:
                        panel[note[2]].setBackground(colors[note[0]].darker());
                        break;
                    case 5:
                        panel[note[2]].setBackground(colors[note[0]]);
                        break;
                    case 6:
                        panel[note[2]].setBackground(colors[note[0]].brighter());
                        break;
                    case 7:
                        panel[note[2]].setBackground(colors[note[0]].brighter().brighter());
                        break;
                    case 8:
                        panel[note[2]].setBackground(colors[note[0]].brighter().brighter().brighter());
                        break;
                    case 9:
                        panel[note[2]].setBackground(colors[note[0]].brighter().brighter().brighter().brighter());
                        break;
                    case 10:
                        panel[note[2]].setBackground(colors[note[0]].brighter().brighter().brighter().brighter().brighter());
                        break;
                    default:
                        panel[note[2]].setBackground(colors[note[0]]);
                }
            }else{
                panel[note[2]].setBackground(Color.BLACK);
            }
        }
    }
}
