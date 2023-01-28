import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Paths;

public class TerminalFrame {
    public TerminalFrame() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Terminal");
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.BLACK);

        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setBackground(Color.BLACK);
        outputArea.setForeground(Color.white);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setAutoscrolls(true);
        frame.add(scrollPane, BorderLayout.CENTER);

        JTextField inputField = new JTextField();
        inputField.setBackground(Color.BLACK);
        inputField.setForeground(Color.white);
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(outputArea.getText().length() >= 500) {
                    outputArea.setText(Paths.get(".").toAbsolutePath().normalize().toString() + ">");
                }
                String input = inputField.getText();
                inputField.setText("");
                if(input.equals("cls")) {
                    outputArea.setText(Paths.get(".").toAbsolutePath().normalize().toString() + ">");
                } else {
                    try {
                        outputArea.append(input + "\n");
                        Process process = Runtime.getRuntime().exec("cmd /c "+input);
                        BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
                        String s = null;
                        while ((s = stdInput.readLine()) != null) {
                            outputArea.append(s + "\n");
                        }
                        outputArea.append(Paths.get(".").toAbsolutePath().normalize().toString() + ">");
                    } catch (IOException ex) {
                        outputArea.append("Error executing command " + ex);
                    }
                }
            }
        });
        frame.add(inputField, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        outputArea.append(Paths.get(".").toAbsolutePath().normalize().toString() + ">");
    }
}
