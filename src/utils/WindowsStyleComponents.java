package utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class WindowsStyleComponents {
    public static void customizeButton(JButton button) {

        button.setBackground(Color.GRAY);
        button.setForeground(Color.WHITE);

        button.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Aggiungi un listener per gestire gli eventi di cambio stato del bottone
        button.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (button.getModel().isRollover()) {
                    // Il mouse è sopra il bottone
                    button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY.darker()));
                } else {
                    // Il mouse non è sopra il bottone
                    button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                }

                if (button.getModel().isPressed()) {
                    // Il bottone è stato premuto
                    button.setBackground(Color.DARK_GRAY);
                } else {
                    // Il bottone non è stato premuto
                    button.setBackground(Color.GRAY);
                }
            }
        });

        // Rimuovi l'icona premuta predefinita (effetto azzurrino)
        button.setPressedIcon(null);
        button.setContentAreaFilled(false);
        button.setBorderPainted(true);
        button.setFocusPainted(false);
        // Imposta l'opacità del bottone a true per evitare effetti di sfumatura
        button.setOpaque(true);

        button.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                // Il bottone ha il focus
                button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY.darker()));
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Il bottone ha perso il focus
                button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                button.setBackground(Color.GRAY);
            }
        });
    }

    public static void customizeComboBox(JComboBox < String > comboBox) {
        // Personalizza la JComboBox con lo stile di Windows

        // Imposta il colore di sfondo e del testo della JComboBox
        comboBox.setBackground(Color.WHITE);
        comboBox.setForeground(Color.BLACK);

        // Imposta il bordo della JComboBox
        Border border = BorderFactory.createLineBorder(Color.GRAY);
        comboBox.setBorder(border);

        // Imposta l'aspetto del cursore quando il mouse è sopra la JComboBox
        comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Personalizza il rendering degli elementi nella JComboBox
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList < ? > list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                // Imposta il colore di sfondo e del testo degli elementi
                if (isSelected) {
                    setBackground(Color.DARK_GRAY);
                    setForeground(Color.WHITE);
                } else {
                    setBackground(Color.WHITE);
                    setForeground(Color.BLACK);
                }

                return this;
            }
        });

        // Personalizza l'aspetto della JComboBox
        comboBox.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                // Crea un JButton personalizzato per l'indicatore di apertura
                JButton arrowButton = new JButton();
                arrowButton.setIcon(new ImageIcon(UindosPath.ARROW_COMBOBOX_PATH)); // Sostituisci con l'icona desiderata
                arrowButton.setBackground(Color.WHITE);
                arrowButton.setBorder(BorderFactory.createEmptyBorder());
                arrowButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                return arrowButton;
            }
        });

        // Aggiungi un listener per gestire gli eventi di focus sulla JComboBox
        comboBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                // La JComboBox ha ottenuto il focus
                comboBox.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            }

            @Override
            public void focusLost(FocusEvent e) {
                // La JComboBox ha perso il focus
                comboBox.setBorder(border);
            }
        });

        // Aggiungi un listener per gestire gli eventi del mouse sulla JComboBox
        comboBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Il mouse è sopra la JComboBox
                comboBox.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Il mouse ha lasciato la JComboBox
                if (!comboBox.hasFocus()) {
                    comboBox.setBorder(border);
                }
            }
        });
    }
}