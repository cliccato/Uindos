package System.Registration;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import System.Desktop.DesktopFrame;
import System.Login.ListenerLogin;
import System.Login.LoginFrame;
import utils.GestoreCartelle;
import utils.UindosPath;

public class ListenerRegistration implements ActionListener,KeyListener {

    private RegistrationFrame registrationFrame;
    private LoginFrame loginFrame;
    private String username;
    private String password;

    public ListenerRegistration(RegistrationFrame registrationFrame, LoginFrame loginFrame){
        this.registrationFrame = registrationFrame;
        this.loginFrame = loginFrame;
    }
    
    private boolean isUserAlreadyPresent(){
        try (BufferedReader fIN = new BufferedReader(new FileReader(new File(UindosPath.USERS_FILE_PATH)))) {
            String fileLine;
            while ((fileLine = fIN.readLine()) != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(fileLine, ListenerLogin.FIELD_DELIMITATOR);
                String usernameFile = stringTokenizer.nextToken();
                if (usernameFile.equals(username)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected exception: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    private boolean isPasswordEffective(String password) {
        if (password.length() < 8) {
            JOptionPane.showMessageDialog(registrationFrame, "La password deve contenere almeno 8 caratteri", "Errore", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!password.matches(".*[a-z].*")) {
            JOptionPane.showMessageDialog(registrationFrame, "La password deve contenere almeno una lettera minuscola", "Errore", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!password.matches(".*[A-Z].*")) {
            JOptionPane.showMessageDialog(registrationFrame, "La password deve contenere almeno una lettera maiuscola", "Errore", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!password.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(registrationFrame, "La password deve contenere almeno un numero", "Errore", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            JOptionPane.showMessageDialog(registrationFrame, "La password deve contenere almeno un carattere speciale", "Errore", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    private boolean authenticateUser(){

        username = registrationFrame.getUsername();
        password =  new String(registrationFrame.getPassword().getPassword());
        String confirmPassword = new String(registrationFrame.getConfirmPassword().getPassword());
        
        // Controllo che i campi non siano vuoti
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(registrationFrame, "Compila tutti i campi", "Errore", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Controllo che l'utente non sia già presente nel file CSV
        if (isUserAlreadyPresent()) {
            JOptionPane.showMessageDialog(registrationFrame, "Utente già esistente", "Errore", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Controllo che le password siano uguali
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(registrationFrame, "Password non corrispondono", "Errore", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Controllo che la password sia efficace
        if (!isPasswordEffective(password)) {
            return false;
        }
        // Controllo che l'utente non sia già presente nel file CSV
        if (isUserAlreadyPresent()) {
            JOptionPane.showMessageDialog(registrationFrame, "Utente già esistente", "Errore", JOptionPane.ERROR_MESSAGE);
            return false;
        }

       

        JOptionPane.showMessageDialog(registrationFrame, "Registrazione effettuata!", "Registrazione", JOptionPane.INFORMATION_MESSAGE);
        return true;        
    }

    private void addUserToCSV(){
        try {
            // Creo un FileWriter per scrivere nel file
            FileWriter fw = new FileWriter(new File(UindosPath.USERS_FILE_PATH), true);
            fw.write("\n" + username + ListenerLogin.FIELD_DELIMITATOR + password);
            fw.close();
        } catch (Exception e) {
            //  TMCH
        }
    }

    private void createDirectory(){
        try  {
            GestoreCartelle.copyFolder(Paths.get(UindosPath.DEFAULT_USER_FOLDER_PATH), Paths.get(UindosPath.USER_FOLDER_PATH + username + "/"));

            } catch(IOException e) {
                System.out.println(e.getMessage());
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registrationFrame.getRegisterButton()) {  
            if (authenticateUser()) {
                username = registrationFrame.getUsername();
                password = new String(registrationFrame.getPassword().getPassword());
                addUserToCSV();
                createDirectory();
                new DesktopFrame(username, password);
                registrationFrame.dispose();
                loginFrame.closeFrame();
            } else {
                registrationFrame.clearInput();
            }
        } else {
            registrationFrame.dispose();     
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource() == registrationFrame.getRegisterButton() && e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (authenticateUser()) {
                username = registrationFrame.getUsername();
                password = new String(registrationFrame.getPassword().getPassword());
                addUserToCSV();
                createDirectory();
                new DesktopFrame(username, password);
                registrationFrame.dispose();
                loginFrame.closeFrame();
            } else {
                registrationFrame.clearInput();
            }   
        } else {
            registrationFrame.dispose();     
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    }
}