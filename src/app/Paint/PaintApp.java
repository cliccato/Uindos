package app.Paint;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import System.Desktop.DesktopFrame;
import utils.GestoreConfig;
import utils.GestoreFrame;
import utils.UindosDirectoryName;
import utils.UindosPath;
    
public class PaintApp {
    private JFrame frame;
    private JPanel canvas;
    private Color currentColor = Color.white;
    private boolean isDrawing = false;
    private String name;
    private BufferedImage image;
    private boolean isImageSaved;
    private Font font;

    public PaintApp(String username) {
        frame = new JFrame("Peint");
        frame.setSize(1280,720);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setIconImage(new ImageIcon(UindosPath.PAINT_LOGO_PATH).getImage());

        font = (Font) GestoreConfig.getConfig(username, GestoreConfig.FONT);

        isImageSaved = false;
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
        colorBtn.setFont(font);
        JButton clearBtn = new JButton("Clear");
        clearBtn.addActionListener(e -> clearCanvas());
        clearBtn.setFont(font);

        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(e -> saveCanvas());
        saveBtn.setFont(font);

        JButton openBtn = new JButton("Open");
        openBtn.addActionListener(e -> checkAndOpenImage());
        openBtn.setFont(font);

        JButton newBtn = new JButton("Nuovo Disegno");
        newBtn.addActionListener(e -> checkAndCreateNewCanvas());
        newBtn.setFont(font);

        JPanel controlsPanel = new JPanel();
        controlsPanel.setFont(font);

        controlsPanel.setBackground(Color.BLACK);
        controlsPanel.add(openBtn);
        controlsPanel.add(newBtn);
        controlsPanel.add(colorBtn);
        controlsPanel.add(clearBtn);
        controlsPanel.add(saveBtn);

        frame.getContentPane().add(canvas);
        frame.getContentPane().add(controlsPanel, "South");
        frame.setVisible(true);
        GestoreFrame.aggiungiFrame(frame);
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
    private boolean isCanvasModified() {
        // Verifica se il canvas è stato modificato confrontando l'immagine corrente con un'immagine vuota
        BufferedImage emptyImage = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_RGB);
        return !image.equals(emptyImage);
    }

    private void createNewCanvas() {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        clearCanvas();
        isImageSaved = false;
        name = null;
    }

    private void saveCanvas() {
        if (!isImageSaved) {
            name = JOptionPane.showInputDialog("Inserisci nome file:");
        }

        if (name != null && !name.isEmpty()) { // Verifica se il nome del file è stato inserito
            try {
                if (!isImageSaved) {
                    ImageIO.write(image, "png", new File( UindosPath.USER_FOLDER_PATH + DesktopFrame.getUsername() + "/" + UindosDirectoryName.DIRECTORY_FOTO + UindosDirectoryName.DIRECTORY_IMMAGINI_PAINT + name + ".png")); // Salva l'immagine su disco come file PNG
                } else {  
                    ImageIO.write(image, "png", new File(UindosPath.USER_FOLDER_PATH + DesktopFrame.getUsername() + "/" + UindosDirectoryName.DIRECTORY_FOTO + UindosDirectoryName.DIRECTORY_IMMAGINI_PAINT + name)); // Salva l'immagine su disco come file PNG
                }
                JOptionPane.showMessageDialog(frame, "Il pannello è stato salvato come immagine.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void checkAndCreateNewCanvas() {
        if (isCanvasModified()) {
            int option = JOptionPane.showConfirmDialog(frame, "Desideri salvare le modifiche prima di creare un nuovo disegno?", "Salva modifiche", JOptionPane.YES_NO_CANCEL_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                saveCanvas();
            } else if (option == JOptionPane.CANCEL_OPTION) {
                return;
            }
        }
        createNewCanvas();
    }

    private void selectColor() {
        Color newColor = JColorChooser.showDialog(frame, "Choose color", currentColor);
        if (newColor != null) {
            currentColor = newColor;
        }
    }
    private void checkAndOpenImage() {
        if (isCanvasModified()) {
            int option = JOptionPane.showConfirmDialog(frame, "Desideri salvare le modifiche prima di aprire un'immagine?", "Salva modifiche", JOptionPane.YES_NO_CANCEL_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                saveCanvas();
            } else if (option == JOptionPane.CANCEL_OPTION) {
                return;
            }
        }
        openImage();
    }

    private void openImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(UindosPath.USER_FOLDER_PATH + DesktopFrame.getUsername() + "/" + UindosDirectoryName.DIRECTORY_FOTO + UindosDirectoryName.DIRECTORY_IMMAGINI_PAINT)); // Imposta la directory di lavoro come cartella iniziale
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedImage newImage = ImageIO.read(selectedFile);
                if (newImage != null) {
                    image = newImage;
                    isImageSaved = true;
                    name = selectedFile.getName();
                    System.out.println(selectedFile.getName());
                    canvas.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
                    canvas.revalidate();
                    canvas.repaint();
                } else {
                    JOptionPane.showMessageDialog(frame, "Errore durante l'apertura dell'immagine.", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Errore durante l'apertura dell'immagine.", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}