package test;

import javax.sound.midi.*;
import java.io.File;

/**
 * Created by David Recuenco on 17/12/2014.
 */
public class MidiTest {

    private static final String FILENAME = "midi1.mid";
    public static final int NOTE_ON = 0x90;
    public static final int NOTE_OFF = 0x80;
    public static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
    public static final String[] MOVES =      {"^", "^",  "^", "v",  "v", "v", "<",  "<", "<",  ">", ">",  ">"};

    public static void main(String[] args) throws Exception{

        MidiTest app = new MidiTest();
        Sequence sequence = app.getFile();

        /*
            ==== WARNING: USELESS METHOD ====
            At first this preparsing was necessary to obtain the track with the most variation of notes
            so a program fed with those notes translated to program input would not be monotonous.

            It's so beautiful that I didn't comment it to see the output tho.
         */
        Track trs = app.preparsing(sequence);

        Sequencer sq = MidiSystem.getSequencer();
        sq.setSequence(sequence);

        //We actually don't need this anymore :D
        //sq.setTrackSolo(bestTrack - 1, true);

        Receiver r = new TestReceiver();
        sq.getTransmitter().setReceiver(r);

        sq.open();
        sq.start();

        app.waitEndOfSong(sq);

        sq.stop();
        sq.close();
    }

    private Sequence getFile() throws Exception{
        return MidiSystem.getSequence(new File(getClass().getClassLoader().getResource(FILENAME).getFile()));
    }

    private Track preparsing(Sequence sequence){
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
        Track trs = sequence.getTracks()[bestTrack-1];

        return trs;
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
