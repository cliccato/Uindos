package app.Terminal;
import javax.swing.*;

import java.awt.*;
import java.nio.file.Paths;

public class TerminalFrame {
    static int HEIGHT, WIDTH; {
        HEIGHT = 800;
        WIDTH = 600;
    }
    static String path = Paths.get(".").toAbsolutePath().normalize().toString();

    JFrame frame;
    JTextArea outputArea;
    JTextField inputField;

    public TerminalFrame() {
        createElements();

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame.setSize(HEIGHT, WIDTH);
        frame.setIconImage(new ImageIcon("images\\terminal-logo.png").getImage());
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        outputArea.setEditable(false);
        outputArea.setBackground(Color.BLACK);
        outputArea.setForeground(Color.white);
        
        inputField.setBackground(Color.BLACK);
        inputField.setForeground(Color.white);
        inputField.addActionListener(new TerminalListener(this, path));

        frame.add(outputArea, BorderLayout.CENTER);
        frame.add(inputField, BorderLayout.SOUTH);
        
        outputArea.setText("Maicrosoft Uindos [Versione 3.14]\n(c) Maicrosoft Corporescion. Tutti i diritti sono riservati.\n\n");
        outputArea.append(path + ">");
        frame.setVisible(true);
    }

    public void createElements() {
        frame = new JFrame("Terminal");
        outputArea = new JTextArea();
        inputField = new JTextField();
    }
}
