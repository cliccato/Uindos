package app.Calculator;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import utils.GestoreConfig;
import utils.GestoreFrame;
import utils.UindosPath;

public class CalculatorFrame {

  private JFrame frame;
  private JPanel panel;
  private JTextField display;
  private JButton[] buttons;
  private String[] labels = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "+", "-", "*", "/", "=", "C" };
  private Font font;

  public CalculatorFrame(String username) {
    createElements();
    panel.setLayout(new GridLayout(4, 4));

    GestoreConfig.getConfig(username, GestoreConfig.FONT);

    display.setFont(font);

    for (int i = 0; i < labels.length; i++) {
      buttons[i] = new JButton(labels[i]);
      buttons[i].setFont(font);
      panel.add(buttons[i]);
    }

    frame.setSize(400, 400);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setIconImage(new ImageIcon(UindosPath.CALCULATOR_LOGO_PATH).getImage());
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
    GestoreFrame.aggiungiFrame(frame);
  }

  public void createElements() {
    frame = new JFrame("Calculator");
    panel = new JPanel();
    display = new JTextField(20);
    buttons = new JButton[labels.length];
  }

  public JTextField getDisplay() {
    return display;
  }
}