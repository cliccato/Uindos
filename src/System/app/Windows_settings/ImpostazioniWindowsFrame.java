package System.app.Windows_settings;

import System.Desktop.DesktopFrame;
import utils.GestoreConfig;
import utils.GestoreFrame;
import utils.UindosDirectoryName;
import utils.UindosPath;
import utils.WindowsStyleComponents;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.*;

public class ImpostazioniWindowsFrame {
    public static final Dimension FRAME_DIMENSION = new Dimension(1000, 500);

    private JFrame frame;
    private DesktopFrame desktopFrame;
    private JPanel pnlInfoUtente;
    private JLabel lblNomeUtente;
    private JLabel lblPasswordUtente;
    private JCheckBox checkBoxMostraPassword;
    private JButton btnCambiaSfondo;
    private JLabel lblCambiaPassword, lblEliminaUtente;
    private String passwordUtente;
    private String nomeUtente;
    private JComboBox<String> fontComboBox;

    public ImpostazioniWindowsFrame(String username, String password, DesktopFrame desktopFrame) {
        frame = new JFrame("Impostazioni");
        this.desktopFrame = desktopFrame;

        frame.setIconImage(new ImageIcon(UindosPath.IMPOSTAZIONI_LOGO_PATH).getImage());
        frame.setSize(FRAME_DIMENSION);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        pnlInfoUtente = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon(UindosPath.BACKGROUND_SETTINGS_PATH);
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        pnlInfoUtente.setOpaque(false);

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

        lblCambiaPassword = new JLabel("<html><u>Cambia password</u></html>");
        lblCambiaPassword.setHorizontalAlignment(SwingConstants.LEFT);
        lblCambiaPassword.setForeground(Color.BLUE);
        lblCambiaPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        lblEliminaUtente = new JLabel("<html><u>Elimina utente</u></html>");
        lblEliminaUtente.setHorizontalAlignment(SwingConstants.LEFT);
        lblEliminaUtente.setForeground(Color.RED);
        lblEliminaUtente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        if (username.equals("username") || username.equals("admin")) {
            lblEliminaUtente.setEnabled(false);
            lblCambiaPassword.setEnabled(false);
        } else {
            lblCambiaPassword.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    frame.dispose();
                    new CambiaPasswordFrame(ImpostazioniWindowsFrame.this);
                }
            });
            ListenerEliminaUtente listenerEliminaUtente = new ListenerEliminaUtente(this, lblEliminaUtente);
            lblEliminaUtente.addMouseListener(listenerEliminaUtente);
        }

        btnCambiaSfondo = new JButton("Cambia sfondo");
        WindowsStyleComponents.customizeButton(btnCambiaSfondo);
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

                    GestoreConfig.changeConfig(username, GestoreConfig.BACKGROUND, relativePath);
                    GestoreFrame.chiudiTuttiFrame();
                    new DesktopFrame(username, password);
                }
            }
        });

        fontComboBox = new JComboBox<>(getAvailableFontNames());
        WindowsStyleComponents.customizeComboBox(fontComboBox);
        fontComboBox.setRenderer(new FontComboBoxRenderer());

        JButton btnConfermaCambioFont = new JButton("Conferma");
        WindowsStyleComponents.customizeButton(btnConfermaCambioFont);
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

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        pnlInfoUtente.add(new JLabel("Nome utente ->"), gbc);

        gbc.gridx = 1;
        pnlInfoUtente.add(lblNomeUtente, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        pnlInfoUtente.add(checkBoxMostraPassword, gbc);

        gbc.gridx = 1;
        pnlInfoUtente.add(lblPasswordUtente, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        pnlInfoUtente.add(new JSeparator(), gbc);

        gbc.gridy = 3;
        pnlInfoUtente.add(btnCambiaSfondo, gbc);

        gbc.gridy = 4;
        pnlInfoUtente.add(pnlCambioFont, gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        pnlInfoUtente.add(lblCambiaPassword, gbc);

        gbc.gridx = 1;
        pnlInfoUtente.add(lblEliminaUtente, gbc);

        // Crea un oggetto GridBagConstraints per posizionare pnlInfoUtente
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weightx = 1.0; // Imposta il peso orizzontale per far espandere il componente
        constraints.weighty = 1.0; // Imposta il peso verticale per far espandere il componente
        constraints.fill = GridBagConstraints.BOTH; // Fai sì che il componente riempia l'area disponibile

        // Aggiungi pnlInfoUtente al layout GridBagLayout con le impostazioni di GridBagConstraints
        frame.getContentPane().setLayout(new GridBagLayout());
        frame.getContentPane().add(pnlInfoUtente, constraints);

        GestoreFrame.aggiungiFrame(frame);

        frame.setVisible(true);
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
