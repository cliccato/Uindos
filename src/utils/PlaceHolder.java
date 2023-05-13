package utils;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PlaceHolder {
    public static void addPlaceHolder(JPasswordField txtPasswordField, String placeHolder) {
        txtPasswordField.setForeground(Color.GRAY);
        txtPasswordField.setText(placeHolder);
        txtPasswordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (new String(txtPasswordField.getPassword()).equals(placeHolder)) {
                    txtPasswordField.setText("");
                    txtPasswordField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (new String(txtPasswordField.getPassword()).isEmpty()) {
                    txtPasswordField.setForeground(Color.GRAY);
                    txtPasswordField.setText(placeHolder);
                }
            }
        });
    }

    public static void addPlaceHolder(JTextField txtField, String placeHolder) {
        txtField.setText(placeHolder);
        txtField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtField.getText().equals(placeHolder)) {
                    txtField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtField.getText().isEmpty()) {
                    txtField.setText(placeHolder);
                }
            }
        }); 
    }
}
