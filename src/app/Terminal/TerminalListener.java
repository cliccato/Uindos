package app.Terminal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

public class TerminalListener implements ActionListener{
    static int OUTPUT_BUFFER = 500;
    TerminalFrame TF;
    String path;

    public TerminalListener(TerminalFrame TF, String path) {
        this.TF = TF;
        this.path = path;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = TF.inputField.getText();
        TF.inputField.setText("");
        TF.outputArea.append(input + "\n");

        if(TF.outputArea.getText().length() >= OUTPUT_BUFFER) {
            TF.outputArea.setText(path + ">");
        }

        if (input.equals("cls")) {
            TF.outputArea.setText(path + ">");
        } else if (input.equals("cd ..")) {
            path = Paths.get(path).toAbsolutePath().getParent().toAbsolutePath().normalize().toString();
            TF.outputArea.append(path + ">");
        } else if (input.startsWith("cd ") && !input.equals("cd ..")) {
            path = Paths.get(path+"\\"+input.replace("cd ", "")).toAbsolutePath().normalize().toString();
            TF.outputArea.append(path + ">");
        } else {
            try {
                Process process = Runtime.getRuntime().exec("cmd /c cd "+path+" && "+input);
                BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String s = null;
                while ((s = stdInput.readLine()) != null) {
                    TF.outputArea.append(s + "\n");
                }
                TF.outputArea.append(path + ">");
            } catch (IOException ex) {
                TF.outputArea.append("Error executing command " + ex);
            }
        }
    }

    
}
