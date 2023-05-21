package app.indovina_immagini.src;
import javax.swing.*;

import utils.GestoreConfig;
import utils.GestoreFrame;

import java.awt.*;
import java.awt.event.ActionEvent;

public class ClassificaCategoriaImmaginiGUI {

    private JFrame frmClassificaCategoriaImmagini;      // frame per classifica su una categoria d'immagini
    private JTable tabellaClassifica;                   // tabella per la classifica
    private JScrollPane scrollPane;                     // scroll pane
    private JButton btnIndietro;                        // bottone per tornare indietro
    private Font font;                        // bottone per tornare indietro

    // costruttore
    public ClassificaCategoriaImmaginiGUI(JFrame frmPrincipale, String[][] classificaCategoriaImmagini, String categoriaImmagini, String username){

        //creazione finestra
        frmClassificaCategoriaImmagini = new JFrame("Classifica su immagini di " + categoriaImmagini);
        font = (Font) GestoreConfig.getConfig(username, GestoreConfig.FONT);

        // creazione button
        btnIndietro = new JButton("INDIETRO");
        btnIndietro.addActionListener((ActionEvent e) -> {
            frmPrincipale.setVisible(true);
            frmClassificaCategoriaImmagini.setVisible(false);
        });

        // campi
        String[] campi = {"Nome utente", "Punteggio"};
        // creazione table
        tabellaClassifica = new JTable(classificaCategoriaImmagini, campi);
        tabellaClassifica.setEnabled(false);
        tabellaClassifica.setGridColor(Color.YELLOW);
        tabellaClassifica.setBackground(Color.CYAN);
        tabellaClassifica.setFont(font);

        btnIndietro.setFont(font);
tabellaClassifica.setFont(font);
        //creazione scroll pane
        scrollPane = new JScrollPane(tabellaClassifica);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //aggiunta componenti al frame
        frmClassificaCategoriaImmagini.add(btnIndietro, BorderLayout.WEST);
        frmClassificaCategoriaImmagini.add(scrollPane, BorderLayout.CENTER);
        frmClassificaCategoriaImmagini.setSize(600, 600);
        frmClassificaCategoriaImmagini.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frmClassificaCategoriaImmagini.setVisible(true);
        frmClassificaCategoriaImmagini.setLocationRelativeTo(null);
        frmPrincipale.setVisible(false);
        GestoreFrame.aggiungiFrame(frmPrincipale);
    }
}