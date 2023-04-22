package app.Calculator;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CalculatorFrame {

  JFrame frame;
  JPanel panel;
  JTextField display;
  JButton[] buttons;
  String[] labels = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "+", "-", "*", "/", "=", "C" };

  public CalculatorFrame() {
    createElements();
    panel.setLayout(new GridLayout(4, 4));

    for (int i = 0; i < labels.length; i++) {
      buttons[i] = new JButton(labels[i]);
      panel.add(buttons[i]);
    }

    frame.setSize(400, 400);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    frame.add(display, BorderLayout.NORTH);
    frame.add(panel, BorderLayout.CENTER);
    frame.setVisible(true);

    for (int i = 0; i <= 13; i++) {
      buttons[i].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          display.setText(display.getText() + e.getActionCommand());
        }
      });
    }

    buttons[14].addActionListener(new CalculatorListener(this));
    buttons[15].addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        display.setText("");
      }
    });
  }

  public void createElements() {
    frame = new JFrame("Calculator");
    panel = new JPanel();
    display = new JTextField(20);
    buttons = new JButton[labels.length];
  }
}