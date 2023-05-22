package app.Notepad;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import utils.GestoreConfig;
import utils.GestoreFrame;
import utils.UindosPath;

public class NotepadFrame {
    public static int HEIGHT = 1200;
    public static int WIDTH = 600;

    private JFrame frame;
    private JTextArea textArea;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem openMenuItem, saveMenuItem, newMenuItem, exitMenuItem, infoMenutItem;
    private JFileChooser fileChooser;
    private Font font;
    private String username;
    private String name;

    public NotepadFrame(String username) {
        this.username = username;
        createElements();

        name = "";
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(HEIGHT, WIDTH);
        frame.setIconImage(new ImageIcon(UindosPath.NOTEPAD_LOGO_PATH).getImage());
        frame.setLayout(new BorderLayout());

        textArea.setFont(font);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        fileMenu.setFont(font);

        infoMenutItem.setFont(font);
        menuBar.add(fileMenu);
        menuBar.add(infoMenutItem);
        menuBar.setFont(font);

        NotepadListener notepadListener = new NotepadListener(this);

        newMenuItem.addActionListener(notepadListener);
        newMenuItem.setFont(font);
        openMenuItem.addActionListener(notepadListener);
        openMenuItem.setFont(font);
        saveMenuItem.addActionListener(notepadListener);
        saveMenuItem.setFont(font);
        exitMenuItem.addActionListener(notepadListener);
        exitMenuItem.setFont(font);

        infoMenutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Non ci sono info");
            }
        });

        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);

        frame.setVisible(true);
        GestoreFrame.aggiungiFrame(frame);
    }

    public void createElements() {
        frame = new JFrame("Notepad - (Nuovo)");
        textArea = new JTextArea();
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        openMenuItem = new JMenuItem("Open");
        saveMenuItem = new JMenuItem("Save");
        newMenuItem = new JMenuItem("New");
        exitMenuItem = new JMenuItem("Exit");
        fileChooser = new JFileChooser();
        infoMenutItem = new JMenuItem("Info");
        font = (Font) GestoreConfig.getConfig(username, GestoreConfig.FONT);
    }

    public JFrame getFrame(){
        return frame;
    }

    public JFileChooser getFileChooser(){
        return fileChooser;
    }

    public JTextArea getTextArea(){
        return textArea;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
