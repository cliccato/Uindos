package utils;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class GestoreFrame {
    private static List<JFrame> frameAperti = new ArrayList<>();

    public static void aggiungiFrame(JFrame frame) {
        frameAperti.add(frame);
    }

    public static void rimuoviFrame(JFrame frame) {
        frameAperti.remove(frame);
    }

    public static void chiudiTuttiFrame() {
        for (JFrame frame : frameAperti) {
            frame.dispose();
        }
        frameAperti.clear();
    }
}
