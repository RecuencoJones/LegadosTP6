package main;

import obs.ColorObserver;
import obs.GameObserver;
import obs.SynthObserver;
import test.MidiTest;
import test.TestReceiver;

/**
 * Created by David Recuenco on 28/12/2014.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        final TestReceiver tr = new TestReceiver();
        final ColorObserver co = new ColorObserver();
        final GameObserver go = new GameObserver();
        final SynthObserver so = new SynthObserver();
        tr.addObserver(co);
        tr.addObserver(go);
        tr.addObserver(so);
        final MidiTest mt = new MidiTest(tr);

        Thread t = new Thread(mt);
        t.start();

        t.join();
        System.exit(0);

    }
}
