package System.app.Windows_settings;

import System.Desktop.DesktopFrame;
import utils.GestoreFrame;

import utils.GestoreConfig;
import utils.UindosDirectoryName;
import utils.UindosPath;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class ImpostazioniWindowsFrame {

    public static final Dimension FRAME_DIMENSION = new Dimension(400, 300);

    private JFrame frame;
    private DesktopFrame desktopFrame;
    private JPanel pnlInfoUtente;
    private JScrollPane scrollPane;
    private JLabel lblNomeUtente;
    private JLabel lblPasswordUtente;
    private JCheckBox checkBoxMostraPassword;
    private JButton btnCambiaSfondo;
    private JLabel lblCambiaPassword;
    private String passwordUtente;
    private String nomeUtente;
    private JButton btnEliminaUtente;
    private JButton btnCambiaCursore;

    public ImpostazioniWindowsFrame(String username, String password, DesktopFrame desktopFrame) {
        frame = new JFrame("Impostazioni");

        this.desktopFrame = desktopFrame;
        frame.setIconImage(new ImageIcon(UindosPath.IMPOSTAZIONI_LOGO_PATH).getImage());
        frame.setSize(FRAME_DIMENSION);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Creazione dei componenti 
        JLabel lblTitolo = new JLabel("Impostazioni");
        lblTitolo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitolo.setAlignmentX(Component.CENTER_ALIGNMENT);

        pnlInfoUtente = new JPanel(new GridLayout(7, 2));

        passwordUtente = password;
        nomeUtente = username;

        char[] pass = passwordUtente.toCharArray();
        for (int i = 0; i < pass.length; i++) {
            pass[i] = '*';
        }

        lblNomeUtente = new JLabel(username);
        lblPasswordUtente = new JLabel(new String(pass));

        checkBoxMostraPassword = new JCheckBox("Mostra password");
        checkBoxMostraPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (checkBoxMostraPassword.isSelected()) {
                    lblPasswordUtente.setText(passwordUtente);
                } else {
                    String password = lblPasswordUtente.getText();
                    char[] pass = password.toCharArray();
                    for (int i = 0; i < pass.length; i++) {
                        pass[i] = '*';
                    }
                    lblPasswordUtente.setText(new String(pass));
                }
            }
        });


        btnEliminaUtente = new JButton("Elimina utente");

        lblCambiaPassword = new JLabel("<html><u>Cambia password</u></html>");
        lblCambiaPassword.setHorizontalAlignment(SwingConstants.LEFT);
        lblCambiaPassword.setForeground(Color.BLUE);
        lblCambiaPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ImpostazioniWindowsFrame myImpostazioniWindowsFrame = this;

        if (username.equals("username") || username.equals("admin")) {
            btnEliminaUtente.setEnabled(false);
            lblCambiaPassword.setEnabled(false);
        } else {
            lblCambiaPassword.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    frame.dispose();
                    new CambiaPasswordFrame(myImpostazioniWindowsFrame);
                }
            });
            btnEliminaUtente.addActionListener(new ListenerEliminaUtente(this));
        }

        btnCambiaSfondo = new JButton("Cambia sfondo");
        btnCambiaSfondo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(UindosPath.USER_FOLDER_PATH + DesktopFrame.getUsername() + "/" + UindosDirectoryName.DIRECTORY_FOTO)); // Set the working directory as the initial folder
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String absolutePath = selectedFile.getAbsolutePath();
                    String currentPath = System.getProperty("user.dir");
                    Path pathAbsolute = Paths.get(absolutePath);
                    Path pathBase = Paths.get(currentPath);
                    Path pathRelative = pathBase.relativize(pathAbsolute);
                    String relativePath = pathRelative.toString();

                    System.out.println(relativePath);
                    // System.out.println(absolutePath);

                    GestoreConfig.changeConfig(username, GestoreConfig.CAMPO_BACKGROUND, relativePath);
                    GestoreFrame.chiudiTuttiFrame();
                    new DesktopFrame(username, password);
                }
            }
        });

        btnCambiaCursore = new JButton("Cambia cursore");
        btnCambiaCursore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                
            }
        });

        pnlInfoUtente.add(new JLabel("Nome utente ->"));
        pnlInfoUtente.add(lblNomeUtente);
        pnlInfoUtente.add(checkBoxMostraPassword);
        pnlInfoUtente.add(lblPasswordUtente);
        pnlInfoUtente.add(new JLabel());
        pnlInfoUtente.add(new JLabel());
        pnlInfoUtente.add(new JSeparator());
        pnlInfoUtente.add(new JSeparator());
        pnlInfoUtente.add(btnCambiaSfondo);
        pnlInfoUtente.add(btnEliminaUtente);
        pnlInfoUtente.add(lblCambiaPassword);

        // pnlInfoUtente.add(new JLabel());
        // pnlInfoUtente.add(new JLabel());
        // pnlInfoUtente.add(new JLabel());

        scrollPane = new JScrollPane(pnlInfoUtente);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(lblTitolo, BorderLayout.NORTH);
        GestoreFrame.aggiungiFrame(frame);
    }

    public void setPasswordUtente(String passwordUtente) {
        this.passwordUtente = passwordUtente;
    }

    public String getPasswordUtente() {
        return passwordUtente;
    }

    public String getNomeUtente() {
        return nomeUtente;
    }

    public JCheckBox getCheckBoxMostraPassword() {
        return checkBoxMostraPassword;
    }

    public JLabel getLblPasswordUtente() {
        return lblPasswordUtente;
    }

    public DesktopFrame getDesktopFrame() {
        return desktopFrame;
    }

    public void chiudiFrame() {
        frame.dispose();
    }
}