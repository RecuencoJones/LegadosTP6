package obs;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by David Recuenco on 11/01/2015.
 */
public class SynthObserver implements Observer {
    private static JPanel panel;
    private JTextArea area;
    private JFrame jf;

    private static final int NOTES = 118;
    private static char[] row = new char[NOTES];
    {
        for(int i=0; i<NOTES; i++){
            row[i] = '-';
        }
    }

    public SynthObserver() {
        jf = new JFrame();
        jf.setTitle("Synth Observer");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel = new JPanel();
        area = new JTextArea(10,140);
        DefaultCaret caret = (DefaultCaret) area.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        area.setFont(new Font("Consolas", Font.BOLD, 18));
        area.setBackground(Color.BLACK);
        area.setForeground(Color.WHITE);
        area.setText("Welcome to SynthObserver, let's play some MiDi!\n");
        panel.add(new JScrollPane(area));
        jf.add(panel);
        jf.pack();
        jf.setSize(1350, 200);
        jf.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof int[]){
            int[] note = (int[]) arg;
            if(note[3] == 1) {
                row[note[2]] = 'O';
                area.append(new String(row) + "\n");
            }else{
                row[note[2]] = '-';
                area.append(new String(row) + "\n");
            }
        }
    }
}
