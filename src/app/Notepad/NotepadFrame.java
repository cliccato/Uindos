package app.Notepad;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NotepadFrame {
    static int HEIGHT, WIDTH; {
        HEIGHT = 1200;
        WIDTH = 600;
    }

    private JFrame frame;
    private JTextArea textArea;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem openMenuItem, saveMenuItem, newMenuItem, exitMenuItem, infoMenutItem;
    private JLabel lblSalvataggio;
    private JFileChooser fileChooser;

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

        NotepadListener notepadListener = new NotepadListener(this);

        newMenuItem.addActionListener(notepadListener);
        openMenuItem.addActionListener(notepadListener);
        saveMenuItem.addActionListener(notepadListener);
        exitMenuItem.addActionListener(notepadListener);
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
        frame = new JFrame("Notepad - (Nuovo)");
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

    public JFrame getFrame(){
        return frame;
    }

    public JLabel getLblSalvataggio(){
        return lblSalvataggio;
    }

    public JFileChooser getFileChooser(){
        return fileChooser;
    }

    public JTextArea getTextArea(){
        return textArea;
    }
}