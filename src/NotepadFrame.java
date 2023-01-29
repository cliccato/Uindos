import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class NotepadFrame {
    static int HEIGHT, WIDTH; {
        HEIGHT = 1200;
        WIDTH = 600;
    }
    private JFrame frame;
    private JTextArea textArea;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem openMenuItem, saveMenuItem, newMenuItem;
    private JFileChooser fileChooser;

    public NotepadFrame() {
        createElements();

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(HEIGHT, WIDTH);
        frame.setIconImage(new ImageIcon("images\\notepad-logo.png").getImage());

        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        menuBar.add(fileMenu);

        newMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
            }
        });
        openMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int returnVal = fileChooser.showOpenDialog(frame);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        textArea.read(new FileReader(file), null);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        saveMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int returnVal = fileChooser.showSaveDialog(frame);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        textArea.write(new FileWriter(file));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });


        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(new JScrollPane(textArea));

        frame.setVisible(true);
    }

    public void createElements() {
        frame = new JFrame("Notepad");
        textArea = new JTextArea();
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        openMenuItem = new JMenuItem("Open");
        saveMenuItem = new JMenuItem("Save");
        newMenuItem = new JMenuItem("New");
        fileChooser = new JFileChooser();
    }
}