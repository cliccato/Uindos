/**
 * @author Mattia Califano
 * @author Giorgio Justin Fasullo
 * 
 * @since 1.1
 */

/* Versions:
- 1.1 funcioning basic terminal
*/

/* --- Package --- */
package app.Terminal;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TerminalFrame {
    private static final String LOGO_PATH = "images/logo/terminal-logo.png";
    private static final int HEIGHT = 800;
    private static final int WIDTH = 800;
    private Path path = Paths.get(".").toAbsolutePath().normalize();
    private JFrame frame;
    private JTextArea taOutput;
    private JTextField txtInputField;

    public TerminalFrame() {
        createElements();
        setLookAndFeel();
        setFrameProperties();
        addElements();
        setText();
    }

    private void createElements() {
        frame = new JFrame("Terminal");
        taOutput = new JTextArea();
        txtInputField = new JTextField();
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setFrameProperties() {
        frame.setSize(HEIGHT, WIDTH);
        frame.setIconImage(new ImageIcon(LOGO_PATH).getImage());
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    private void addElements() {
        taOutput.setEditable(false);
        taOutput.setBackground(Color.BLACK);
        taOutput.setForeground(Color.white);
        txtInputField.setBackground(Color.BLACK);
        txtInputField.setForeground(Color.white);
        txtInputField.addActionListener(new TerminalListener(this, path));
        frame.add(taOutput, BorderLayout.CENTER);
        frame.add(txtInputField, BorderLayout.SOUTH);
    }

    private void setText() {
        taOutput.setText("Maicrosoft Windows [Version 3.14]\n(c) Maicrosoft Corporation. Tutti i diritti sono riservati.\n\n");
        taOutput.append(path + ">");
    }

    public JTextField getTxtInputField() {
        return txtInputField;
    }

    public JTextArea getTaOutput() {
        return taOutput;
    }
}