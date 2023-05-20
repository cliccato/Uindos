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
import java.awt.GraphicsEnvironment;
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
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class ImpostazioniWindowsFrame {

    public static final Dimension FRAME_DIMENSION = new Dimension(1000, 500);

    private JFrame frame;
    private DesktopFrame desktopFrame;
    private JPanel pnlInfoUtente;
    private JScrollPane scrollPane;
    private JLabel lblNomeUtente;
    private JLabel lblPasswordUtente;
    private JCheckBox checkBoxMostraPassword;
    private JButton btnCambiaSfondo;
    private JLabel lblCambiaPassword, lblEliminaUtente;
    private String passwordUtente;
    private String nomeUtente;
    private JButton btnCambiaCursore;
    private JComboBox <String> fontComboBox;

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


        // btnEliminaUtente = new JButton("Elimina utente");

        lblCambiaPassword = new JLabel("<html><u>Cambia password</u></html>");
        lblCambiaPassword.setHorizontalAlignment(SwingConstants.LEFT);
        lblCambiaPassword.setForeground(Color.BLUE);
        lblCambiaPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ImpostazioniWindowsFrame myImpostazioniWindowsFrame = this;

lblEliminaUtente = new JLabel("Elimina utente");

lblEliminaUtente.setHorizontalAlignment(SwingConstants.LEFT);
lblEliminaUtente.setForeground(Color.RED);
lblEliminaUtente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

ListenerEliminaUtente listenerEliminaUtente = new ListenerEliminaUtente(this, lblEliminaUtente);
        if (username.equals("username") || username.equals("admin")) {
            lblEliminaUtente.setEnabled(false);
            lblCambiaPassword.setEnabled(false);
        } else {
            lblCambiaPassword.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    frame.dispose();
                    new CambiaPasswordFrame(myImpostazioniWindowsFrame);
                }
            });
            // btnEliminaUtente.addActionListener(new ListenerEliminaUtente(this));
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

                    GestoreConfig.changeConfig(username, GestoreConfig.BACKGROUND, relativePath);
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



        fontComboBox = new JComboBox <> (getAvailableFontNames());
        fontComboBox.setRenderer(new FontComboBoxRenderer());

        JButton btnConfermaCambioFont = new JButton("Conferma");
        JPanel pnlCambioFont = new JPanel(new BorderLayout());
        pnlCambioFont.add(fontComboBox, BorderLayout.CENTER);
        pnlCambioFont.add(btnConfermaCambioFont, BorderLayout.EAST);

        btnConfermaCambioFont.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedFontName = (String) fontComboBox.getSelectedItem();
                Font selectedFont = new Font(selectedFontName, Font.PLAIN, 15);

                GestoreConfig.changeConfig(username, GestoreConfig.FONT, selectedFontName + ";" + selectedFont.getStyle() + ";" + selectedFont.getSize());
                GestoreFrame.chiudiTuttiFrame();
                new DesktopFrame(username, password);
            }
        });

        

lblEliminaUtente.addMouseListener(listenerEliminaUtente);

       pnlInfoUtente.add(new JLabel("Nome utente ->"));
pnlInfoUtente.add(lblNomeUtente);
pnlInfoUtente.add(checkBoxMostraPassword);
pnlInfoUtente.add(lblPasswordUtente);
pnlInfoUtente.add(new JLabel());
pnlInfoUtente.add(new JLabel());
pnlInfoUtente.add(new JSeparator());
pnlInfoUtente.add(new JSeparator());
pnlInfoUtente.add(btnCambiaSfondo);
pnlInfoUtente.add(pnlCambioFont);
pnlInfoUtente.add(lblCambiaPassword);
pnlInfoUtente.add(lblEliminaUtente);


        scrollPane = new JScrollPane(pnlInfoUtente);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(lblTitolo, BorderLayout.NORTH);
        GestoreFrame.aggiungiFrame(frame);
    }

    private String[] getAvailableFontNames() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font[] fonts = ge.getAllFonts();
        String[] fontNames = new String[fonts.length];

        for (int i = 0; i < fonts.length; i++) {
            fontNames[i] = fonts[i].getFontName();
        }

        return fontNames;
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