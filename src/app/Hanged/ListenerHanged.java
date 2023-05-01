package app.Hanged;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ListenerHanged implements ActionListener {
    
    private Hanged hanged;

    public ListenerHanged(Hanged hanged){
        this.hanged = hanged;
    }

    private void mostraPersonaggio(){
        switch (hanged.getTentativi()){
            case 0:
                hanged.getLblImmagine().setIcon(hanged.getImages()[6]);
                break;
            case 1:
                hanged.getLblImmagine().setIcon(hanged.getImages()[5]);
                break;
            case 2:
                hanged.getLblImmagine().setIcon(hanged.getImages()[4]);
                break;
            case 3:
                hanged.getLblImmagine().setIcon(hanged.getImages()[3]);
                break;
            case 4:
                hanged.getLblImmagine().setIcon(hanged.getImages()[2]);
                break;
            case 5:
                hanged.getLblImmagine().setIcon(hanged.getImages()[1]);
                break;
            case 6:
                hanged.getLblImmagine().setIcon(hanged.getImages()[0]);
                break;
        }
    }

    private boolean letteraPresente(){
        char[] lettera = hanged.getCslParolaUtente().getText().toCharArray();
        char[] parola = hanged.getLblParolaNascosta().getText().toCharArray();
        boolean istrovato = false;
        for(int i=1; i<hanged.getLblParolaNascosta().getText().length()-1; i++){
            if(lettera[0] == hanged.getParolaEstratta().charAt(i)){
                parola[i] = hanged.getParolaEstratta().charAt(i);
                hanged.getLblParolaNascosta().setText(new String(parola));
                istrovato = true;
            }
        }
        return istrovato;
    }

    private boolean letteraGiaDetta(){
        char[] lettera = hanged.getCslParolaUtente().getText().toCharArray();
        for (int i = 0; i < hanged.getLettereGiaDette().size(); i++){
            if(hanged.getLettereGiaDette().get(i) == lettera[0]){
                return true;
            }
        }
        return false;
    }

    private void indovinaLettera(){
        boolean isLetteraGiaLetta = letteraGiaDetta();
        boolean isLetteraPresente = letteraPresente();
        boolean isParolaNonIndovinata = true;
        if(!isLetteraGiaLetta && isLetteraPresente) {
            if(hanged.getParolaEstratta().equals(hanged.getLblParolaNascosta().getText())){
                hanged.getLblIndovinato().setText("Hai indovinato!");
                hanged.getLblIndovinato().setForeground(Color.green);
                hanged.getLblParolaNascosta().setText(hanged.getParolaEstratta());
                hanged.getCslParolaUtente().setEditable(false);
                hanged.finisciGioco();
                isParolaNonIndovinata = false;
            } else {
                hanged.getLblIndovinato().setText("Lettera " + hanged.getCslParolaUtente().getText() + " presente nella parola!");
                hanged.getLblIndovinato().setForeground(Color.green);
            }
        } else if (isLetteraGiaLetta) {
            hanged.setTentativi(hanged.getTentativi()-1);
            hanged.getLblIndovinato().setText("Lettera " + hanged.getCslParolaUtente().getText() + " già detta! \nTentativi rimasti: " + hanged.getTentativi());
            hanged.getLblIndovinato().setForeground(Color.red);
            mostraPersonaggio();
        } else {
            hanged.setTentativi(hanged.getTentativi()-1);
            hanged.getLblIndovinato().setText("Lettera " + hanged.getCslParolaUtente().getText() + " non presente! \nTentativi rimasti: " + hanged.getTentativi());
            hanged.getLblIndovinato().setForeground(Color.red);
            mostraPersonaggio();
        }

        Vector<Character> lettereGiaDette = hanged.getLettereGiaDette();
        lettereGiaDette.add(hanged.getCslParolaUtente().getText().toCharArray()[0]);
        hanged.setLettereGiaDette(lettereGiaDette);
        if(isParolaNonIndovinata){
            hanged.getCslParolaUtente().setText("");
        }
    }

    private boolean parolaGiaDetta(){
        String parola = hanged.getCslParolaUtente().getText();
        for (int i = 0; i < hanged.getParoleGiaDette().size(); i++){
            if(hanged.getParoleGiaDette().get(i).equals(parola)){
                return true;
            }
        }
        return false;
    }

    private void indovinaParola(){
        boolean isParolaGiaDetta = parolaGiaDetta();
        boolean isParolaNonIndovinata = true;
        if (!isParolaGiaDetta && hanged.getParolaEstratta().equals(hanged.getCslParolaUtente().getText())){
            hanged.getLblIndovinato().setText("Hai indovinato!");
            hanged.getLblIndovinato().setForeground(Color.green);
            hanged.getLblParolaNascosta().setText(hanged.getParolaEstratta());
            hanged.getCslParolaUtente().setEditable(false);
            isParolaNonIndovinata = false;
            hanged.finisciGioco();
        } else if (isParolaGiaDetta){
            hanged.setTentativi(hanged.getTentativi()-1);
            hanged.getLblIndovinato().setText("Parola " + hanged.getCslParolaUtente().getText() + " già detta! \nTentativi rimasti: " + hanged.getTentativi());
            hanged.getLblIndovinato().setForeground(Color.red);
            mostraPersonaggio();
        } else {
            hanged.setTentativi(hanged.getTentativi()-1);
            hanged.getLblIndovinato().setText("Parola " + hanged.getCslParolaUtente().getText() + " non corretta! \nTentativi rimasti: " + hanged.getTentativi());
            hanged.getLblIndovinato().setForeground(Color.red);
            mostraPersonaggio();
        }

        Vector<String> paroleGiaDette = hanged.getParoleGiaDette();
        paroleGiaDette.add(hanged.getCslParolaUtente().getText());
        hanged.setParoleGiaDette(paroleGiaDette);
        if(isParolaNonIndovinata){
            hanged.getCslParolaUtente().setText("");
        }
    }

    public void actionPerformed(ActionEvent e){
        if(!hanged.getIsGiocoFinito()){
            if(hanged.getCslParolaUtente().getText().length() == 1) {
                indovinaLettera();
            } else {
                indovinaParola();
            }

            if(hanged.getTentativi() == 0){
                hanged.getLblIndovinato().setText("GAME OVER!");
                hanged.getLblIndovinato().setForeground(Color.red);
                hanged.getLblParolaNascosta().setText(hanged.getParolaEstratta());
                hanged.finisciGioco();
                hanged.getCslParolaUtente().setEditable(false);
            }
        } else {
            hanged.getLblIndovinato().setText("GIOCO CONCLUSO!");
            hanged.getLblIndovinato().setForeground(Color.BLACK);
        }
    }
}