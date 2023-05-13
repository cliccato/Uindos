package app.Paint;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class PaintApp {
    private static final String LOGO_PATH = "images/logo/paint-logo.png";
    private JFrame frame;
    private JPanel canvas;
    private Color currentColor = Color.white;
    private boolean isDrawing = false;
    private String name;
    private BufferedImage image;

    public PaintApp() {
        frame = new JFrame("Peint");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setIconImage(new ImageIcon(LOGO_PATH).getImage());

        canvas = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (image == null) {
                    image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
                }
                g.drawImage(image, 0, 0, null);
            }
        };
        canvas.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                isDrawing = true;
                draw(e.getX(), e.getY());
            }

            public void mouseReleased(MouseEvent e) {
                isDrawing = false;
            }
        });
        canvas.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (isDrawing) {
                    draw(e.getX(), e.getY());
                }
            }
        });
        canvas.setBackground(Color.black); // Imposta lo sfondo su bianco

        JButton colorBtn = new JButton("Select Color");
        colorBtn.addActionListener(e -> selectColor());

        JButton clearBtn = new JButton("Clear");
        clearBtn.addActionListener(e -> clearCanvas());

        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(e -> saveCanvas());

        JPanel controlsPanel = new JPanel();
        controlsPanel.setBackground(Color.BLACK);
        controlsPanel.add(colorBtn);
        controlsPanel.add(clearBtn);
        controlsPanel.add(saveBtn);

        frame.getContentPane().add(canvas);
        frame.getContentPane().add(controlsPanel, "South");
        frame.setVisible(true);
    }

    private void draw(int x, int y) {
        Graphics2D g = image.createGraphics();
        g.setColor(currentColor);
        g.fillOval(x - 5, y - 5, 5, 5);
        g.dispose();

        canvas.repaint();
    }

    private void clearCanvas() {
        Graphics2D g = image.createGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        g.dispose();

        canvas.repaint();
    }

    private void saveCanvas() {
        name = JOptionPane.showInputDialog("Inserisci nome file:");

        try {
            ImageIO.write(image, "png", new File("src/app/Paint/Save/" + name + ".png")); // Salva l'immagine su disco come file PNG
            JOptionPane.showMessageDialog(frame, "Il pannello è stato salvato come immagine.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void selectColor() {
        Color newColor = JColorChooser.showDialog(frame, "Choose color", currentColor);
        if (newColor != null) {
            currentColor = newColor;
        }
    }
}