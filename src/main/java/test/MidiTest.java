package test;

import javax.sound.midi.*;
import javax.swing.*;
import java.io.File;

/**
 * Created by David Recuenco on 17/12/2014.
 */
public class MidiTest implements Runnable {

    private static TestReceiver receiver;
    private static String FILENAME;

    public MidiTest(TestReceiver receiver){
        this.receiver = receiver;
    }

    public void run() {

        askForFile();
        Sequence sequence = null;
        try {
            sequence = this.getFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Sequencer sq = null;
        try {
            sq = MidiSystem.getSequencer();
            sq.setSequence(sequence);

            final Receiver r = receiver;
            sq.getTransmitter().setReceiver(r);

            sq.open();
            sq.start();

            this.waitEndOfSong(sq);

            sq.stop();
            sq.close();

            System.exit(0);

        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }

    private static void askForFile(){
        String val = JOptionPane.showInputDialog("Select file [1-12]: ");
        try {
            if(val.equals("exit")) { System.exit(0); }
            int index = Integer.parseInt(val);
            if(index < 1 || index > 12){
                System.out.println(" > Not a valid file.");
                askForFile();
            }else{
                FILENAME = "midi"+index+".mid";
            }
        }catch(Exception e){
            System.out.println(" > Not a valid file.");
            askForFile();
        }
    }

    private Sequence getFile() throws Exception{
        return MidiSystem.getSequence(new File(getClass().getClassLoader().getResource(FILENAME).getFile()));
    }

    private void waitEndOfSong(Sequencer sq){
        while(true) {
            if(sq.isRunning()) {
                try {
                    Thread.sleep(1000); // Check every second
                } catch(InterruptedException ignore) {
                    break;
                }
            } else {
                break;
            }
        }
        System.out.println("END OF SONG");
    }

}
