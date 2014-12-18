package test;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

/**
 * Created by David Recuenco on 18/12/2014.
 */
public class TestReceiver implements Receiver {

    private static final int NOTE_ON = 0x90;
    private static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};

    @Override
    public void send(MidiMessage message, long timeStamp) {
        if (message instanceof ShortMessage) {
            ShortMessage sm = (ShortMessage) message;
            if (sm.getCommand() == NOTE_ON) {
                int key = sm.getData1();
                int note = key % 12;
                String noteName = NOTE_NAMES[note];
                System.out.println(setNoteAtPosition(sm.getChannel(),noteName));
            }
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
