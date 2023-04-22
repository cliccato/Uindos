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
    private static final int MAX_OUTPUT_LINES = 500;
    
    private final TerminalFrame terminal;
    private Path workingDirectory;

    public TerminalListener(TerminalFrame terminal, Path workingDirectory) {
        this.terminal = terminal;
        this.workingDirectory = workingDirectory;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = terminal.getTxtInputField().getText();
        terminal.getTxtInputField().setText("");
        terminal.getTaOutput().append(input + "\n");

        if (terminal.getTaOutput().getLineCount() >= MAX_OUTPUT_LINES) {
            removeFirstLine();
        }

        switch (input) {
            case "cls":
                clearScreen();
                break;
            case "cd ..":
                goUpDirectory();
                break;
            default:
                executeCommand(input);
                break;
        }
    }

    private void removeFirstLine() {
        int index = terminal.getTaOutput().getText().indexOf("\n") + 1;
        terminal.getTaOutput().replaceRange("", 0, index);
    }

    private void clearScreen() {
        terminal.getTaOutput().setText(workingDirectory + ">");
    }

    private void goUpDirectory() {
        workingDirectory = workingDirectory.getParent().normalize();
        terminal.getTaOutput().append(workingDirectory + ">");
    }

    private void executeCommand(String input) {
        try {
            ProcessBuilder builder = new ProcessBuilder("cmd", "/c", "cd", workingDirectory.toString(), "&&", input);
            builder.redirectErrorStream(true);
            Process process = builder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                terminal.getTaOutput().append(line + "\n");
            }
            terminal.getTaOutput().append(workingDirectory + ">");
        } catch (IOException ex) {
            terminal.getTaOutput().append("Error executing command " + ex);
        }
    }
}