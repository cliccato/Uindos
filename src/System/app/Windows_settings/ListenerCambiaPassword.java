package System.app.Windows_settings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import System.Login.ListenerLogin;

public class ListenerCambiaPassword implements ActionListener,KeyListener {
    
    private CambiaPasswordFrame cambiaPasswordFrame;
    private ImpostazioniWindowsFrame impostazioniWindowsFrame;

    public ListenerCambiaPassword(CambiaPasswordFrame cambiaPasswordFrame, ImpostazioniWindowsFrame impostazioniWindowsFrame){
        this.cambiaPasswordFrame = cambiaPasswordFrame;
        this.impostazioniWindowsFrame = impostazioniWindowsFrame;
    }

    private boolean isPasswordEffective(String password) {
        if (password.length() < 8) {
            JOptionPane.showMessageDialog(cambiaPasswordFrame, "La password deve contenere almeno 8 caratteri", "Errore", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!password.matches(".*[a-z].*")) {
            JOptionPane.showMessageDialog(cambiaPasswordFrame, "La password deve contenere almeno una lettera minuscola", "Errore", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!password.matches(".*[A-Z].*")) {
            JOptionPane.showMessageDialog(cambiaPasswordFrame, "La password deve contenere almeno una lettera maiuscola", "Errore", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!password.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(cambiaPasswordFrame, "La password deve contenere almeno un numero", "Errore", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            JOptionPane.showMessageDialog(cambiaPasswordFrame, "La password deve contenere almeno un carattere speciale", "Errore", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean isPasswordOK(){

        if (cambiaPasswordFrame.getOldPassword().isEmpty() || cambiaPasswordFrame.getNewPassword().isEmpty() || cambiaPasswordFrame.getConfirmPassword().isEmpty()) {
            JOptionPane.showMessageDialog(cambiaPasswordFrame, "Compila tutti i campi", "Errore", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!cambiaPasswordFrame.getOldPassword().equals(cambiaPasswordFrame.getPasswordUtente())) {
            JOptionPane.showMessageDialog(cambiaPasswordFrame, "Password vecchia sbagliata", "Errore", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (cambiaPasswordFrame.getOldPassword().equals(cambiaPasswordFrame.getNewPassword()) && cambiaPasswordFrame.getNewPassword().equals(cambiaPasswordFrame.getConfirmPassword())){
            JOptionPane.showMessageDialog(cambiaPasswordFrame, "Inserito la stessa password", "Errore", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!cambiaPasswordFrame.getNewPassword().equals(cambiaPasswordFrame.getConfirmPassword())) {
            JOptionPane.showMessageDialog(cambiaPasswordFrame, "Inserite due nuove password", "Errore", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!isPasswordEffective(cambiaPasswordFrame.getNewPassword())) {
            return false;
        }
        return true;
    }

    private void updateCSV(){
        try {
            File inputFile = new File(ListenerLogin.USERS_FILE_PATH);
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            String line;
            StringBuilder updatedContent = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(line, ListenerLogin.FIELD_DELIMITATOR);
                String username = stringTokenizer.nextToken();
                String password = stringTokenizer.nextToken();
                System.out.println(username);
                System.out.println(impostazioniWindowsFrame.getNomeUtente());
                if (username.equals(impostazioniWindowsFrame.getNomeUtente())) {
                    // Modifica la password dell'utente
                    System.out.println(cambiaPasswordFrame.getNewPassword());
                    password = cambiaPasswordFrame.getNewPassword();
                    System.out.println(password);
                }
                updatedContent.append(String.join(ListenerLogin.FIELD_DELIMITATOR, new String[]{username, password})).append("\n");
            }
            reader.close();

            FileWriter writer = new FileWriter(ListenerLogin.USERS_FILE_PATH);
            writer.write(updatedContent.toString());
            writer.close();

        } catch (IOException e) {
            ;   //TMCH
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cambiaPasswordFrame.getBntConferma()){
            if (isPasswordOK()){
                impostazioniWindowsFrame.setPasswordUtente(cambiaPasswordFrame.getNewPassword());
                if (impostazioniWindowsFrame.getCheckBoxMostraPassword().isSelected()){
                    impostazioniWindowsFrame.getLblPasswordUtente().setText(cambiaPasswordFrame.getNewPassword());
                } else {
                    char[] password = cambiaPasswordFrame.getNewPassword().toCharArray();
                    for (int i = 0; i < password.length; i++) {
                        password[i] = '*';
                    }
                    impostazioniWindowsFrame.getLblPasswordUtente().setText(new String(password));
                }
                updateCSV();
                impostazioniWindowsFrame.getDesktopFrame().setPassword(cambiaPasswordFrame.getNewPassword());
                JOptionPane.showMessageDialog(cambiaPasswordFrame, "Password cambiata correttamente", "Password cambiata", JOptionPane.INFORMATION_MESSAGE);
                cambiaPasswordFrame.dispose();
            } else {
                cambiaPasswordFrame.clearInput();
            }
        } else {
            cambiaPasswordFrame.dispose();
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource() == cambiaPasswordFrame.getBntConferma() && e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (isPasswordOK()) {
                impostazioniWindowsFrame.setPasswordUtente(cambiaPasswordFrame.getNewPassword());
                if (impostazioniWindowsFrame.getCheckBoxMostraPassword().isSelected()){
                    impostazioniWindowsFrame.getLblPasswordUtente().setText(cambiaPasswordFrame.getNewPassword());
                } else {
                    char[] password = cambiaPasswordFrame.getNewPassword().toCharArray();
                    for (int i = 0; i < password.length; i++) {
                        password[i] = '*';
                    }
                    impostazioniWindowsFrame.getLblPasswordUtente().setText(new String(password));
                }
                updateCSV();
                impostazioniWindowsFrame.getDesktopFrame().setPassword(cambiaPasswordFrame.getNewPassword());
                JOptionPane.showMessageDialog(cambiaPasswordFrame, "Password cambiata correttamente", "Password cambiata", JOptionPane.INFORMATION_MESSAGE);
                cambiaPasswordFrame.dispose();
            } else {
                cambiaPasswordFrame.clearInput();
            }   
        } else {
            cambiaPasswordFrame.dispose();     
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

   

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }


}
