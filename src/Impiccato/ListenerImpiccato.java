/*
AUTORE: NICOLO' MEZZANZANICA
DATA: 06/02/2023
LUOGO: CASA
OGGETTO: CLASSE LISTENER IMPICCATO
*/

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ListenerImpiccato implements ActionListener {

    //impiccato
    Impiccato impiccato;

    //metodo costruttore della classe ListenerImpiccato
    public ListenerImpiccato(Impiccato impiccato){
        this.impiccato = impiccato;
    }

    //metodo per visualizzare il personaggio
    private void mostraPersonaggio(){
        switch (impiccato.getTentativi()){
            case 0:
                impiccato.lblImmagine.setIcon(impiccato.images[6]);
                break;
            case 1:
                impiccato.lblImmagine.setIcon(impiccato.images[5]);
                break;
            case 2:
                impiccato.lblImmagine.setIcon(impiccato.images[4]);
                break;
            case 3:
                impiccato.lblImmagine.setIcon(impiccato.images[3]);
                break;
            case 4:
                impiccato.lblImmagine.setIcon(impiccato.images[2]);
                break;
            case 5:
                impiccato.lblImmagine.setIcon(impiccato.images[1]);
                break;
            case 6:
                impiccato.lblImmagine.setIcon(impiccato.images[0]);
                break;
        }
    }

    //metodo che restituisce true se la lettera è presente altrimenti restituisce false
    private boolean letteraPresente(){
        char[] lettera = impiccato.cslParolaUtente.getText().toCharArray();
        char[] parola = impiccato.lblParolaNascosta.getText().toCharArray();
        boolean trovato = false;
        for(int i=1; i<impiccato.lblParolaNascosta.getText().length()-1; i++){
            if(lettera[0] == impiccato.getParolaEstratta().charAt(i)){
                parola[i] = impiccato.getParolaEstratta().charAt(i);
                impiccato.lblParolaNascosta.setText(new String(parola));
                trovato = true;
            }
        }
        return trovato;
    }

    //metodo che restituisce true se la lettera è stata già detta altrimenti restituisce false
    private boolean letteraGiaDetta(){
        char[] lettera = impiccato.cslParolaUtente.getText().toCharArray();
        for (int i = 0; i < impiccato.getLettereGiaDette().size(); i++){
            if(impiccato.getLettereGiaDette().get(i) == lettera[0]){
                return true;
            }
        }
        return false;
    }

    //metodo per indovinare una lettera nella parola
    private void indovinaLettera(){
        boolean letteraGiaDetta = letteraGiaDetta();
        boolean letteraPresente = letteraPresente();
        boolean parolaNonIndovinata = true;
        if(!letteraGiaDetta && letteraPresente) {
            if(impiccato.getParolaEstratta().equals(impiccato.lblParolaNascosta.getText())){
                impiccato.lblIndovinato.setText("Hai indovinato!");
                impiccato.lblIndovinato.setForeground(Color.green);
                impiccato.lblParolaNascosta.setText(impiccato.getParolaEstratta());
                impiccato.cslParolaUtente.setEditable(false);
                impiccato.giocoFinito = true;
                parolaNonIndovinata = false;
            } else {
                impiccato.lblIndovinato.setText("Lettera " + impiccato.cslParolaUtente.getText() + " presente nella parola!");
                impiccato.lblIndovinato.setForeground(Color.green);
            }
        } else if (letteraGiaDetta) {
            impiccato.setTentativi(impiccato.getTentativi()-1);
            impiccato.lblIndovinato.setText("Lettera " + impiccato.cslParolaUtente.getText() + " già detta! \nTentativi rimasti: " + impiccato.getTentativi());
            impiccato.lblIndovinato.setForeground(Color.red);
            mostraPersonaggio();
        } else {
            impiccato.setTentativi(impiccato.getTentativi()-1);
            impiccato.lblIndovinato.setText("Lettera " + impiccato.cslParolaUtente.getText() + " non presente! \nTentativi rimasti: " + impiccato.getTentativi());
            impiccato.lblIndovinato.setForeground(Color.red);
            mostraPersonaggio();
        }

        Vector<Character> lettereGiaDette = impiccato.getLettereGiaDette();
        lettereGiaDette.add(impiccato.cslParolaUtente.getText().toCharArray()[0]);
        impiccato.setLettereGiaDette(lettereGiaDette);
        if(parolaNonIndovinata){
            impiccato.cslParolaUtente.setText("");
        }
    }

    //metodo che restituisce true se la parola è stata già detta altrimenti restituisce false
    private boolean parolaGiaDetta(){
        String parola = impiccato.cslParolaUtente.getText();
        for (int i = 0; i < impiccato.getParoleGiaDette().size(); i++){
            if(impiccato.getParoleGiaDette().get(i).equals(parola)){
                return true;
            }
        }
        return false;
    }

    //metodo per indovinare la parola direttamente
    private void indovinaParola(){
        boolean parolaGiaDetta = parolaGiaDetta();
        boolean parolaNonIndovinata = true;
        if (!parolaGiaDetta && impiccato.getParolaEstratta().equals(impiccato.cslParolaUtente.getText())){
            impiccato.lblIndovinato.setText("Hai indovinato!");
            impiccato.lblIndovinato.setForeground(Color.green);
            impiccato.lblParolaNascosta.setText(impiccato.getParolaEstratta());
            impiccato.cslParolaUtente.setEditable(false);
            parolaNonIndovinata = false;
            impiccato.giocoFinito = true;
        } else if (parolaGiaDetta){
            impiccato.setTentativi(impiccato.getTentativi()-1);
            impiccato.lblIndovinato.setText("Parola " + impiccato.cslParolaUtente.getText() + " già detta! \nTentativi rimasti: " + impiccato.getTentativi());
            impiccato.lblIndovinato.setForeground(Color.red);
            mostraPersonaggio();
        } else {
            impiccato.setTentativi(impiccato.getTentativi()-1);
            impiccato.lblIndovinato.setText("Parola " + impiccato.cslParolaUtente.getText() + " non corretta! \nTentativi rimasti: " + impiccato.getTentativi());
            impiccato.lblIndovinato.setForeground(Color.red);
            mostraPersonaggio();
        }
        Vector<String> paroleGiaDette = impiccato.getParoleGiaDette();
        paroleGiaDette.add(impiccato.cslParolaUtente.getText());
        impiccato.setParoleGiaDette(paroleGiaDette);
        if(parolaNonIndovinata){
            impiccato.cslParolaUtente.setText("");
        }
    }

    //metodo actionPerformed
    public void actionPerformed(ActionEvent e){
        if(!impiccato.giocoFinito){
            if(impiccato.cslParolaUtente.getText().length() == 1) indovinaLettera();
            else indovinaParola();
            if(impiccato.getTentativi() == 0){
                impiccato.lblIndovinato.setText("GAME OVER!");
                impiccato.lblIndovinato.setForeground(Color.red);
                impiccato.lblParolaNascosta.setText(impiccato.getParolaEstratta());
                impiccato.giocoFinito = true;
            }
        } else {
            impiccato.lblIndovinato.setText("GIOCO CONCLUSO!");
            impiccato.lblIndovinato.setForeground(Color.BLACK);
        }
    }
}