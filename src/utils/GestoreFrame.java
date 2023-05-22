package utils;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import java.awt.*;

public class GestoreFrame {
    private static List<JFrame> frameAperti = new ArrayList<>();

    public static void aggiungiFrame(JFrame frame) {
        frameAperti.add(frame);
        frame.setLocationRelativeTo(null);
        setPointer(frame);
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

    public static void setPointer(JFrame frame) {
        Point point = new Point(0,0);
        Toolkit tkit=Toolkit.getDefaultToolkit();
        Image img = tkit.getImage(UindosPath.POINTER_PATH);
        Cursor cursor = tkit.createCustomCursor(img, point, "");
        frame.setCursor(cursor);
    }

    public static List<JFrame> getAllFrames() {
        return frameAperti;
    }
}

