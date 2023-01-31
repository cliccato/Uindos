import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;

public class NotepadListener implements ActionListener{
    NotepadFrame NF;

    public NotepadListener(NotepadFrame NF) {
        this.NF = NF;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "New":
                NF.textArea.setText("");
                NF.lblSalvataggio.setText("File nuovo");
                break;
            case "Save":
                int saveVal = NF.fileChooser.showOpenDialog(NF.frame);
                if (saveVal == JFileChooser.APPROVE_OPTION) {
                    File file = NF.fileChooser.getSelectedFile();
                    try {
                        NF.textArea.write(new FileWriter(file));
                        NF.lblSalvataggio.setText("File salvato");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                break;
            case "Open":
                int openVal = NF.fileChooser.showOpenDialog(NF.frame);
                if (openVal == JFileChooser.APPROVE_OPTION) {
                    File file = NF.fileChooser.getSelectedFile();
                    try {
                        NF.textArea.read(new FileReader(file), null);
                        NF.lblSalvataggio.setText("File non salvato");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                break;
            case "Exit":
                System.exit(0);
                break;
            default:
                break;
        }
    }

}
