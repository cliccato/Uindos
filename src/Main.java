import System.Desktop.DesktopFrame;
import System.Login.LoginFrame;

public class Main {
    public static void main(String[] args) {
        new LoginFrame();//login di default come admin, nella macchina virtuale login come altro utente //commentare questa linea di codice nel case in cui non vogliate passare per il login
        // new DesktopFrame("admin"); //decommentare questa linea di codice nel caso in cui vogliate passare direttamente al desktop
    }
}