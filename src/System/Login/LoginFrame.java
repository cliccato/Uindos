package System.Login;

import System.Desktop.DesktopFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class LoginFrame {
    
    private JFrame frame;
    private BufferedImage img;
    private JPanel pnlImage;
    private JPanel pnlFormInput;
    private JTextField txtUsername;
    private JTextField txtPassword;
    private JButton btnLogin;
    private JLabel lblMessageLogin;

    public LoginFrame() {
        createComponents();
        //implementare interfaccia per inserimento password
        //se password corretta avviare un nuovo desktop
        setBackground();
        setFrame();

    }

    private void createComponents() {
        frame = new JFrame("Login");
        frame.setSize(new Dimension(DesktopFrame.WIDTH, DesktopFrame.HEIGHT));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setIconImage(new ImageIcon(DesktopFrame.WINDOWS_LOGO_PATH).getImage());

        pnlFormInput = new JPanel(new GridBagLayout());

        // Creazione dei constraints per l'allineamento dei componenti
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;

        txtUsername = new JTextField();
        txtUsername.setPreferredSize(new Dimension(150, 25));
        txtUsername.setHorizontalAlignment(JTextField.CENTER);
        pnlFormInput.add(txtUsername, constraints);

        txtPassword = new JTextField();
        txtPassword.setPreferredSize(new Dimension(150, 25));
        txtPassword.setHorizontalAlignment(JTextField.CENTER);
        addPlaceHolder();
        pnlFormInput.add(txtPassword, constraints);

        btnLogin = new JButton("Accedi");
        btnLogin.addActionListener(new ListenerLogin(this));
        pnlFormInput.add(btnLogin, constraints);

        lblMessageLogin = new JLabel();
    }

    private void addPlaceHolder() {
        // Aggiungi placeholder al campo di testo txtUsername
        txtUsername.setText("username");
        txtUsername.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtUsername.getText().equals("username")) {
                    txtUsername.setText("");
                }
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                if (txtUsername.getText().isEmpty()) {
                    txtUsername.setText("username");
                }
            }
        });
        // Aggiungi placeholder al campo di testo txtPassword
        txtPassword.setForeground(Color.GRAY);
        txtPassword.setText("password");
        txtPassword.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtPassword.getText().equals("password")) {
                    txtPassword.setText("");
                    txtPassword.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (txtPassword.getText().isEmpty()) {
                    txtPassword.setForeground(Color.GRAY);
                    txtPassword.setText("password");
                }
            }
        });
    }

    private void setFrame() {
        frame.setVisible(true);
    }

   private void setBackground() {
        try {
            img = ImageIO.read(new File(DesktopFrame.DEFAULT_BACKGROUND_PATH));
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

        // Aggiungi il pannello di input dei dati di accesso al centro del pannello contenuto nella finestra
        frame.getContentPane().add(pnlFormInput, BorderLayout.NORTH);

        frame.getContentPane().add(lblMessageLogin, BorderLayout.SOUTH);
    }

    public void setFrameNotVisible() {
        frame.setVisible(false);
    }

    public String getUsername() {
        return txtUsername.getText();
    }

    public String getPassword() {
        return txtPassword.getText();
    }

    public void alertUserNotFound() {
        lblMessageLogin.setText("Credential failde");
    }
}