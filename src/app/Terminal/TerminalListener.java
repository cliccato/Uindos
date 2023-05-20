/**
 * @author Mattia Califano
 * @author Giorgio Justin Fasullo
 * 
 * @vesion 1.2
 * @since 22-04-2023
 */

/* Versions:
- 1.1 funcioning basic terminal listener
- 1.2 fix code cleaned, replaced Process to ProcessBuilder 
      because of the deprecated method .exec(String)
*/

/* --- Package --- */
package app.Terminal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

public class TerminalListener implements ActionListener {
    private static final int MAX_OUTPUT_LINES = 30;
    
    private final TerminalFrame terminal;
    private Path workingDirectory;

    public TerminalListener(TerminalFrame terminal, Path workingDirectory) {
        this.terminal = terminal;
        this.workingDirectory = workingDirectory;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (terminal.getTaOutput().getLineCount() >= MAX_OUTPUT_LINES) {
            clearScreen();
        }

        String input = terminal.getTxtInputField().getText();
        terminal.getTxtInputField().setText("");
        terminal.getTaOutput().append(input + "\n");

        if (input.startsWith("cd ")) {
            if (input.compareTo("cd ..")==1) {
                goUpDirectory();
            } else {
              String newPath = input.substring(3);
                workingDirectory = workingDirectory.resolve(newPath).normalize();
                //terminal.getTaOutput().append(workingDirectory + ">");  
            }
        }

        switch (input) {
            case "cls":
                clearScreen();
                break;
            default:
                executeCommand(input);
                break;
        }
    }

    private void clearScreen() {
        terminal.getTaOutput().setText(workingDirectory + ">");
    }

    private void goUpDirectory() {
        workingDirectory = workingDirectory.getParent().normalize();
        terminal.getTaOutput().append(workingDirectory + ">");
    }

    private void executeCommand(String command) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "cd " + workingDirectory.toString() + "&&" + command);
            //processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                terminal.getTaOutput().append(line + "\n");
            }
        } catch (IOException e) {
            terminal.getTaOutput().append(e + "\n");
        }
        terminal.getTaOutput().append(workingDirectory + ">");
    }
}