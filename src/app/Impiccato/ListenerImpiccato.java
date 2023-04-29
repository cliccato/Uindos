/*
AUTORE: NICOLO' MEZZANZANICA
DATA: 06/02/2023
LUOGO: CASA
OGGETTO: CLASSE LISTENER IMPICCATO
*/

/** TODO
 *  - Sistemare switch case del mostra punteggio, case 0 significa immagine 0, 
 * non e un errore ma è per avere un senso logico 
 *  - controllare se l'array di char puo essere sostituito in qualche modo dalle stringhe
 * 
*/

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ListenerImpiccato implements ActionListener {

    //impiccato
    private Impiccato impiccato;

    //metodo costruttore della classe ListenerImpiccato
    public ListenerImpiccato(Impiccato impiccato){
        this.impiccato = impiccato;
    }

    //metodo per visualizzare il personaggio
    private void mostraPersonaggio(){
        switch (impiccato.getTentativi()){
            case 0:
                impiccato.getLblImmagine().setIcon(impiccato.getImages()[6]);
                break;
            case 1:
                impiccato.getLblImmagine().setIcon(impiccato.getImages()[5]);
                break;
            case 2:
                impiccato.getLblImmagine().setIcon(impiccato.getImages()[4]);
                break;
            case 3:
                impiccato.getLblImmagine().setIcon(impiccato.getImages()[3]);
                break;
            case 4:
                impiccato.getLblImmagine().setIcon(impiccato.getImages()[2]);
                break;
            case 5:
                impiccato.getLblImmagine().setIcon(impiccato.getImages()[1]);
                break;
            case 6:
                impiccato.getLblImmagine().setIcon(impiccato.getImages()[0]);
                break;
        }
    }

    //metodo che restituisce true se la lettera è presente altrimenti restituisce false
    private boolean letteraPresente(){
        char[] lettera = impiccato.getCslParolaUtente().getText().toCharArray();
        char[] parola = impiccato.getLblParolaNascosta().getText().toCharArray();
        boolean istrovato = false;
        for(int i=1; i<impiccato.getLblParolaNascosta().getText().length()-1; i++){
            if(lettera[0] == impiccato.getParolaEstratta().charAt(i)){
                parola[i] = impiccato.getParolaEstratta().charAt(i);
                impiccato.getLblParolaNascosta().setText(new String(parola));
                istrovato = true;
            }
        }
        return istrovato;
    }

    //metodo che restituisce true se la lettera è stata già detta altrimenti restituisce false
    private boolean letteraGiaDetta(){
        char[] lettera = impiccato.getCslParolaUtente().getText().toCharArray();
        for (int i = 0; i < impiccato.getLettereGiaDette().size(); i++){
            if(impiccato.getLettereGiaDette().get(i) == lettera[0]){
                return true;
            }
        }
        return false;
    }

    //metodo per indovinare una lettera nella parola
    private void indovinaLettera(){
        boolean isLetteraGiaLetta = letteraGiaDetta();
        boolean isLetteraPresente = letteraPresente();
        boolean isParolaNonIndovinata = true;
        if(!isLetteraGiaLetta && isLetteraPresente) {
            if(impiccato.getParolaEstratta().equals(impiccato.getLblParolaNascosta().getText())){
                impiccato.getLblIndovinato().setText("Hai indovinato!");
                impiccato.getLblIndovinato().setForeground(Color.green);
                impiccato.getLblParolaNascosta().setText(impiccato.getParolaEstratta());
                impiccato.getCslParolaUtente().setEditable(false);
                impiccato.finisciGioco();
                isParolaNonIndovinata = false;
            } else {
                impiccato.getLblIndovinato().setText("Lettera " + impiccato.getCslParolaUtente().getText() + " presente nella parola!");
                impiccato.getLblIndovinato().setForeground(Color.green);
            }
        } else if (isLetteraGiaLetta) {
            impiccato.setTentativi(impiccato.getTentativi()-1);
            impiccato.getLblIndovinato().setText("Lettera " + impiccato.getCslParolaUtente().getText() + " già detta! \nTentativi rimasti: " + impiccato.getTentativi());
            impiccato.getLblIndovinato().setForeground(Color.red);
            mostraPersonaggio();
        } else {
            impiccato.setTentativi(impiccato.getTentativi()-1);
            impiccato.getLblIndovinato().setText("Lettera " + impiccato.getCslParolaUtente().getText() + " non presente! \nTentativi rimasti: " + impiccato.getTentativi());
            impiccato.getLblIndovinato().setForeground(Color.red);
            mostraPersonaggio();
        }

        Vector<Character> lettereGiaDette = impiccato.getLettereGiaDette();
        lettereGiaDette.add(impiccato.getCslParolaUtente().getText().toCharArray()[0]);
        impiccato.setLettereGiaDette(lettereGiaDette);
        if(isParolaNonIndovinata){
            impiccato.getCslParolaUtente().setText("");
        }
    }

    //metodo che restituisce true se la parola è stata già detta altrimenti restituisce false
    private boolean parolaGiaDetta(){
        String parola = impiccato.getCslParolaUtente().getText();
        for (int i = 0; i < impiccato.getParoleGiaDette().size(); i++){
            if(impiccato.getParoleGiaDette().get(i).equals(parola)){
                return true;
            }
        }
        return false;
    }

    //metodo per indovinare la parola direttamente
    private void indovinaParola(){
        boolean isParolaGiaDetta = parolaGiaDetta();
        boolean isParolaNonIndovinata = true;
        if (!isParolaGiaDetta && impiccato.getParolaEstratta().equals(impiccato.getCslParolaUtente().getText())){
            impiccato.getLblIndovinato().setText("Hai indovinato!");
            impiccato.getLblIndovinato().setForeground(Color.green);
            impiccato.getLblParolaNascosta().setText(impiccato.getParolaEstratta());
            impiccato.getCslParolaUtente().setEditable(false);
            isParolaNonIndovinata = false;
            impiccato.finisciGioco();
        } else if (isParolaGiaDetta){
            impiccato.setTentativi(impiccato.getTentativi()-1);
            impiccato.getLblIndovinato().setText("Parola " + impiccato.getCslParolaUtente().getText() + " già detta! \nTentativi rimasti: " + impiccato.getTentativi());
            impiccato.getLblIndovinato().setForeground(Color.red);
            mostraPersonaggio();
        } else {
            impiccato.setTentativi(impiccato.getTentativi()-1);
            impiccato.getLblIndovinato().setText("Parola " + impiccato.getCslParolaUtente().getText() + " non corretta! \nTentativi rimasti: " + impiccato.getTentativi());
            impiccato.getLblIndovinato().setForeground(Color.red);
            mostraPersonaggio();
        }
        Vector<String> paroleGiaDette = impiccato.getParoleGiaDette();
        paroleGiaDette.add(impiccato.getCslParolaUtente().getText());
        impiccato.setParoleGiaDette(paroleGiaDette);
        if(isParolaNonIndovinata){
            impiccato.getCslParolaUtente().setText("");
        }
    }

    //metodo actionPerformed
    public void actionPerformed(ActionEvent e){
        if(!impiccato.getIsGiocoFinito()){
            if(impiccato.getCslParolaUtente().getText().length() == 1) {
                indovinaLettera();
            } else {
                indovinaParola();
            }

            if(impiccato.getTentativi() == 0){
                impiccato.getLblIndovinato().setText("GAME OVER!");
                impiccato.getLblIndovinato().setForeground(Color.red);
                impiccato.getLblParolaNascosta().setText(impiccato.getParolaEstratta());
                impiccato.finisciGioco();
            }
        } else {
            impiccato.getLblIndovinato().setText("GIOCO CONCLUSO!");
            impiccato.getLblIndovinato().setForeground(Color.BLACK);
        }
    }
}