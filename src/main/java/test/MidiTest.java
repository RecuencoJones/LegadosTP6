package test;

import obs.ColorObserver;

import javax.sound.midi.*;
import javax.swing.*;
import java.io.File;
import java.util.Scanner;

/**
 * Created by David Recuenco on 17/12/2014.
 */
public class MidiTest implements Runnable {

    private static TestReceiver receiver;
    private static String FILENAME;
    private static int MODE;
    public static final int NOTE_ON = 0x90;
    public static final int NOTE_OFF = 0x80;
    public static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
    public static final String[] MOVES =      {"^", "^",  "^", "v",  "v", "v", "<",  "<", "<",  ">", ">",  ">"};

    public MidiTest(TestReceiver receiver){
        this.receiver = receiver;
    }

    public void run() {

        askForFile();
        //askForMode();
        Sequence sequence = null;
        try {
            sequence = this.getFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
            ==== WARNING: USELESS METHOD ====
            At first this preparsing was necessary to obtain the track with the most variation of notes
            so a program fed with those notes translated to program input would not be monotonous.

            It's so beautiful that I didn't comment it to see the output tho.
            I commented it bc reasons.
         */
        //int bestTrack = app.preparsing(sequence);

        Sequencer sq = null;
        try {
            sq = MidiSystem.getSequencer();
            sq.setSequence(sequence);

            //We actually don't need this anymore :D
            //sq.setTrackSolo(bestTrack - 1, true);

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
        String val = JOptionPane.showInputDialog("Select file [1-15]: ");
        try {
            int index = Integer.parseInt(val);
            if(index < 1 || index > 15){
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

    private int preparsing(Sequence sequence){
        int trackNumber = 0;
        int bestTrack = 0;
        //count measures number of ShortMessages
        //count2 measures number of MidiMessages
        int count = 0, count2 = 0;
        //value used to calculate a maximum
        int uberDummy = Integer.MIN_VALUE;
        for (Track track :  sequence.getTracks()) {
            trackNumber++;
            System.out.println("Track " + trackNumber + ": size = " + track.size());
            System.out.println();
            int[] note_times = new int[12];
            for(int dummy: note_times){ dummy = 0; }
            for (int i=0; i < track.size(); i++) {
                MidiEvent event = track.get(i);
                MidiMessage message = event.getMessage();
                count2++;
                if (message instanceof ShortMessage) {
                    ShortMessage sm = (ShortMessage) message;
                    count++;
                    if (sm.getCommand() == NOTE_ON) {
                        int key = sm.getData1();
                        int note = key % 12;
                        note_times[note]++;
                    }
                }
            }
            int nonZero = 0;
            int sum = 0;
            for(int j = 0; j < NOTE_NAMES.length; j++){
                if(note_times[j] != 0) {
                    nonZero++;
                    sum += note_times[j];
                    System.out.println("\t" + NOTE_NAMES[j] + ": " + note_times[j] + " times.");
                }
            }
            int ratio = (sum/(13-nonZero));
            System.out.println("\tNonZero notes: "+nonZero+".");
            System.out.println("\tInverse ratio: "+ratio);
            System.out.println("\tEvents registered: "+count+", "+count2);

            if(ratio > uberDummy) {
                uberDummy = ratio;
                bestTrack = trackNumber;
            }

            System.out.println();
        }

        System.out.println("Best track is: "+bestTrack);
        //Track trs = sequence.getTracks()[bestTrack-1];

        return bestTrack;
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
