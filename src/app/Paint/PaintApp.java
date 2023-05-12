package app.Paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class PaintApp {
    private static final String LOGO_PATH = "images/logo/paint-logo.png";
    private JFrame frame;
    private JPanel canvas;
    private Color currentColor = Color.BLACK;
    private boolean isDrawing = false;
    
    public PaintApp() {
        frame = new JFrame("Peint");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setIconImage(new ImageIcon(LOGO_PATH).getImage());
        
        canvas = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };
        canvas.setBackground(Color.WHITE);
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
        
        JButton colorBtn = new JButton("Select Color");
        colorBtn.addActionListener(e -> selectColor());
        
        JButton clearBtn = new JButton("Clear");
        clearBtn.addActionListener(e -> clearCanvas());
        
        JPanel controlsPanel = new JPanel();
        controlsPanel.setBackground(Color.BLACK);
        controlsPanel.add(colorBtn);
        controlsPanel.add(clearBtn);
        
        frame.getContentPane().add(canvas);
        frame.getContentPane().add(controlsPanel, "South");
        frame.setVisible(true);
    }
    
    private void draw(int x, int y) {
        Graphics g = canvas.getGraphics();
        g.setColor(currentColor);
        g.fillOval(x - 5, y - 5, 5, 5);
    }
    
    private void clearCanvas() {
        Graphics g = canvas.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
    
    private void selectColor() {
        Color newColor = JColorChooser.showDialog(frame, "Choose color", currentColor);
        if (newColor != null) {
            currentColor = newColor;
        }
    }
}
