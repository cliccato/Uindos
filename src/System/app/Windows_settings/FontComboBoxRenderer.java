package System.app.Windows_settings;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class FontComboBoxRenderer extends JLabel implements ListCellRenderer <String> {
    public FontComboBoxRenderer() {
        setOpaque(true);
        setHorizontalAlignment(LEFT);
        setVerticalAlignment(CENTER);
    }

    public Component getListCellRendererComponent(JList < ? extends String > list, String value, int index,
        boolean isSelected, boolean cellHasFocus) {
        setText(value);
        setFont(new Font(value, Font.PLAIN, 12)); // Imposta il tuo stile e dimensione del font desiderati

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        return this;
    }
}