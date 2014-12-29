package test;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import java.util.Observable;

/**
 * Created by David Recuenco on 18/12/2014.
 */
public class TestReceiver extends Observable implements Receiver {

    private static final int NOTE_ON = 0x90;
    private static final int NOTE_OFF = 0x80;
    private static final int NOTES = 118;
    /*private static char[] row = new char[NOTES];

    {
        for(int i=0; i<NOTES; i++){
            row[i] = '-';
        }
    }*/
    private static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
    private int[] data = new int[2];

    @Override
    public void send(MidiMessage message, long timeStamp) {
        if (message instanceof ShortMessage) {
            ShortMessage sm = (ShortMessage) message;
            if (sm.getCommand() == NOTE_ON) {
                int key = sm.getData1();
                data[1] = (key / 12)-1;
                data[0] = key % 12;
                String noteName = NOTE_NAMES[data[0]];

                setChanged();
                notifyObservers(data);
                System.out.println(setNoteAtPosition(sm.getChannel(),noteName));
                //row[note] = '+';
            }
            /*else if(sm.getCommand() == NOTE_OFF){
                int key = sm.getData1();
                int note = key % NOTES;
                System.out.println("\n" + new String(row));
                row[note] = '-';
            }*/
        }
    }

    @Override
    public void close() {
        System.out.println("Bye");
    }

    private String setNoteAtPosition(int index,String note){
        String dummy = "";
        for(int i = 0; i<16;i++){
            if(i==index){
                dummy = dummy.concat(String.format("%2s|",note));
            }else{
                dummy = dummy.concat("--|");
            }
        }
        return dummy;
    }
}
