package app.Calendar;

import javax.swing.*;

import utils.GestoreConfig;
import utils.GestoreFrame;
import utils.UindosPath;
import utils.WindowsStyleComponents;

import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;

public class CalendarApp {

    private JFrame frame;
    private JTable calendarTable;
    private ImmutableTableModel tableModel;
    private JComboBox<String> monthComboBox;
    private JComboBox<String> yearComboBox;
    private Font font;

    public CalendarApp(String username) {
        frame = new JFrame("Calendar app");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setIconImage(new ImageIcon(UindosPath.CALENDAR_LOGO_PATH).getImage());
        
        font = (Font) GestoreConfig.getConfig(username, GestoreConfig.FONT);

        GestoreFrame.aggiungiFrame(frame);
        // Creazione del modello immutabile della tabella
        tableModel = new ImmutableTableModel(new Object[]{"Lun", "Mar", "Mer", "Gio", "Ven", "Sab", "Dom"}, 0);

    
    // Creazione della tabella
    calendarTable = new JTable(tableModel);
    calendarTable.setRowHeight(40);
    calendarTable.getTableHeader().setReorderingAllowed(false);
    calendarTable.getTableHeader().setResizingAllowed(false);
    calendarTable.setCellSelectionEnabled(false);
    calendarTable.setFocusable(false);
        calendarTable.setDefaultRenderer(Object.class, new UneditableTableCellRenderer());
        calendarTable.setFont(font);

        // Impostazione delle dimensioni delle colonne
        for (int i = 0; i < 7; i++) {
            calendarTable.getColumnModel().getColumn(i).setPreferredWidth(150);
        }

        // Creazione dei componenti per selezionare il mese e l'anno
        monthComboBox = new JComboBox<>(new String[]{"Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno",
                "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"});
        yearComboBox = new JComboBox<>(getYearList());
        WindowsStyleComponents.customizeComboBox(monthComboBox);
        WindowsStyleComponents.customizeComboBox(yearComboBox);

        monthComboBox.setFont(font);
        yearComboBox.setFont(font);
        // Aggiunta dell'azione di cambio mese/anno
        ActionListener changeDateListener = e -> {
            int selectedMonthIndex = monthComboBox.getSelectedIndex();
            int selectedYear = Integer.parseInt(yearComboBox.getSelectedItem().toString());

            YearMonth yearMonth = YearMonth.of(selectedYear, selectedMonthIndex + 1);
            updateCalendar(yearMonth);
        };
        monthComboBox.addActionListener(changeDateListener);
        yearComboBox.addActionListener(changeDateListener);

        // Creazione del pannello contenitore per i componenti di selezione
        JPanel selectionPanel = new JPanel();
        selectionPanel.add(monthComboBox);
        selectionPanel.add(yearComboBox);

        // Creazione del pannello contenitore per la tabella
        JPanel calendarPanel = new JPanel(new BorderLayout());
        calendarPanel.add(selectionPanel, BorderLayout.NORTH);
        calendarPanel.add(new JScrollPane(calendarTable), BorderLayout.CENTER);

        // Impostazione della dimensione del pannello del calendario
        calendarPanel.setPreferredSize(new Dimension(800, 300)); // Imposta la dimensione desiderata

        // Aggiunta del pannello al frame
        frame.add(calendarPanel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Aggiornamento del calendario corrente
        LocalDate currentDate = LocalDate.now();
        YearMonth currentYearMonth = YearMonth.of(currentDate.getYear(), currentDate.getMonthValue());
        monthComboBox.setSelectedIndex(currentDate.getMonthValue() - 1);
        yearComboBox.setSelectedItem(String.valueOf(currentDate.getYear()));
        updateCalendar(currentYearMonth);
    }

    private void updateCalendar(YearMonth yearMonth) {
        tableModel.setRowCount(0); // Rimuove tutte le righe esistenti

        int year = yearMonth.getYear();
        int month = yearMonth.getMonthValue();

        // Ottenere il primo giorno del mese
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);

        // Determina il numero di righe necessarie nella tabella del calendario
        int numRows = (firstDayOfMonth.lengthOfMonth() + firstDayOfMonth.getDayOfWeek().getValue() - 2) / 7 + 1;

        // Aggiunge le righe alla tabella
        for (int i = 0; i < numRows; i++) {
            tableModel.addRow(new Object[7]);
        }

        // Popola la tabella con i giorni del mese
        LocalDate date = firstDayOfMonth;
        int row = 0;
        int col = firstDayOfMonth.getDayOfWeek().getValue() - 1;
        while (date.getMonthValue() == month) {
            tableModel.setValueAt(date.getDayOfMonth(), row, col);
            date = date.plusDays(1);
            col++;
            if (col == 7) {
                col = 0;
                row++;
            }
        }
    }

    private String[] getYearList() {
        int currentYear = Year.now().getValue();
        int startYear = 1000; // Anno di inizio desiderato (es. Medioevo)
        int numYears = currentYear - startYear + 1;
        String[] years = new String[numYears];
        for (int i = 0; i < numYears; i++) {
            years[i] = String.valueOf(currentYear - i);
        }
        return years;
    }
}