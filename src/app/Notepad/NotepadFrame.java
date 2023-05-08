package app.Notepad;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NotepadFrame {
    static int HEIGHT, WIDTH; {
        HEIGHT = 1200;
        WIDTH = 600;
    }

    JFrame frame;
    JTextArea textArea;
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenuItem openMenuItem, saveMenuItem, newMenuItem, exitMenuItem, infoMenutItem;
    JLabel lblSalvataggio;
    JFileChooser fileChooser;

    public NotepadFrame() {
        createElements();

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(HEIGHT, WIDTH);
        frame.setIconImage(new ImageIcon("images\\logo\\notepad-logo.png").getImage());
        frame.setLayout(new BorderLayout());

        textArea.setFont(new Font("Verdana", Font.PLAIN, 20));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        menuBar.add(infoMenutItem);

        newMenuItem.addActionListener(new NotepadListener(this));
        openMenuItem.addActionListener(new NotepadListener(this));
        saveMenuItem.addActionListener(new NotepadListener(this));
        exitMenuItem.addActionListener(new NotepadListener(this));
        infoMenutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Non ci sono info");
            }
        });

        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(lblSalvataggio, BorderLayout.SOUTH);
        frame.getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);

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
        exitMenuItem = new JMenuItem("Exit");
        lblSalvataggio = new JLabel("File nuovo");
        fileChooser = new JFileChooser();
        infoMenutItem = new JMenuItem("Info");
    }
}
