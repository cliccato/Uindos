package System.Login;
import System.Desktop.DesktopFrame;
import System.Registration.RegistrationFrame;

import javax.imageio.ImageIO;
import javax.swing.*;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import utils.GestoreFrame;
import utils.PlaceHolder;
import utils.UindosFileName;
import utils.UindosPath;

public class LoginFrame {

    private final Dimension TXT_INPUT_SIZE = new Dimension(200, 30);
    private final Dimension BUTTON_INPUT_SIZE = new Dimension(80, 30);
    private final Dimension LABEL_NEW_ACCOUNT_SIZE = new Dimension(150, 30);

    private JFrame frame;
    private BufferedImage img;
    private JPanel pnlImage;
    private JPanel pnlFormInput;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnShowPassword;
    private JLabel lblRegistration;

    public LoginFrame() {
        createComponents();
        setBackground();
        setFrame();
        GestoreFrame.aggiungiFrame(frame);
    }

    private void createComponents() {
        frame = new JFrame("Login");
        frame.setSize(new Dimension(DesktopFrame.WIDTH, DesktopFrame.HEIGHT));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setIconImage(new ImageIcon(UindosPath.WINDOWS_LOGO_PATH).getImage());

        pnlFormInput = new JPanel(new GridBagLayout());
        pnlFormInput.setOpaque(false); // Set panel background to transparent
        // Creazione dei constraints per l'allineamento dei componenti


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;

        txtUsername = new JTextField();
        txtUsername.setPreferredSize(TXT_INPUT_SIZE);
        txtUsername.setHorizontalAlignment(JTextField.CENTER);
        constraints.gridx = 0;
        constraints.gridy = 0;
        pnlFormInput.add(txtUsername, constraints);
        PlaceHolder.addPlaceHolder(txtUsername, "username");

        txtPassword = new JPasswordField();
        txtPassword.setPreferredSize(TXT_INPUT_SIZE);
        txtPassword.setHorizontalAlignment(JTextField.CENTER);
        PlaceHolder.addPlaceHolder(txtPassword, "password");
        constraints.gridx = 0;
        constraints.gridy = 1;
        pnlFormInput.add(txtPassword, constraints);

        btnShowPassword = new JButton("Show");
        btnShowPassword.addKeyListener(new ListenerShowPassword(btnShowPassword, txtPassword));
        btnShowPassword.addActionListener(new ListenerShowPassword(btnShowPassword, txtPassword));
        btnShowPassword.setPreferredSize(BUTTON_INPUT_SIZE);
        btnShowPassword.setPreferredSize(BUTTON_INPUT_SIZE);
        constraints.gridx = 1;
        constraints.gridy = 1;
        pnlFormInput.add(btnShowPassword, constraints);

        btnLogin = new JButton("Accedi");
        btnLogin.addKeyListener(new ListenerLogin(this));
        btnLogin.addActionListener(new ListenerLogin(this));
        btnLogin.setPreferredSize(BUTTON_INPUT_SIZE);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        pnlFormInput.add(btnLogin, constraints);

        JLabel lbl = new JLabel("Non hai un account?");
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setPreferredSize(LABEL_NEW_ACCOUNT_SIZE);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        pnlFormInput.add(lbl, constraints);

        LoginFrame myLoginFrame = this;
        lblRegistration = new JLabel("<html><u>Registrati</u></html>");
        lblRegistration.setHorizontalAlignment(SwingConstants.CENTER);
        lblRegistration.setForeground(Color.BLUE);
        lblRegistration.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblRegistration.setPreferredSize(BUTTON_INPUT_SIZE);
        lblRegistration.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new RegistrationFrame(myLoginFrame);
            }
        });
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        pnlFormInput.add(lblRegistration, constraints);
        frame.add(pnlFormInput, BorderLayout.CENTER);
    }

    private void setFrame() {
        frame.setFocusTraversalKeysEnabled(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void setBackground() {
        try {
            img = ImageIO.read(new File(UindosPath.DEFAULT_BACKGROUND_FOLDER_PATH + UindosFileName.DEFAULT_BACKGROUND_FILE_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // crea un nuovo pannello con l'immagine come sfondo
        pnlImage = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, null);
            }
        };

        // Imposta il pannello con l'immagine come sfondo della finestra
        frame.setContentPane(pnlImage);
        frame.getContentPane().setLayout(new BorderLayout());

        // Crea un pannello per centrare l'input
        JPanel pnlCenter = new JPanel(new GridBagLayout());
        pnlCenter.setOpaque(false);
        pnlCenter.add(pnlFormInput);

        // Aggiungi il pannello di input dei dati di accesso al centro del pannello
        // contenuto nella finestra
        frame.getContentPane().add(pnlCenter, BorderLayout.CENTER);

    }

    public void closeFrame() {
        frame.dispose();
    }

    public String getUsername() {
        return txtUsername.getText();
    }

    public String getPassword() {
        return new String(txtPassword.getPassword());
    }

    public void clearInput() {
        txtUsername.setText("username");
        txtPassword.setText("password");
    }

    public void alertUserNotFound() {
        JOptionPane.showMessageDialog(null, "Credenziali sbagliate", "Errore", JOptionPane.ERROR_MESSAGE);
    }
}