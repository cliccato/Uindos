/*
 * da aggiustare
 */
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.html.*;

public class SwingHTMLBrowser extends JFrame implements ActionListener, HyperlinkListener {
    private JTextField addressBar;
    private JTextPane pane;

    SwingHTMLBrowser() {
        super("Swing HTML Browser");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addressBar = new JTextField();
        addressBar.addActionListener(this);
        pane = new JTextPane();
        pane.setEditable(false);
        pane.addHyperlinkListener(this);
        pane.setContentType("text/html");
        //add(addressBar, BorderLayout.NORTH);
        add(new JScrollPane(pane));
        setSize(new Dimension(1080, 720));
        try {
            pane.setPage("https://mirrors.edge.kernel.org/pub/");
            addressBar.setText("https://mirrors.edge.kernel.org/pub/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent evt) {
        String url = addressBar.getText();
        try {
            pane.setPage(url);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void hyperlinkUpdate(HyperlinkEvent evt) {
		if (evt.getEventType() != HyperlinkEvent.EventType.ACTIVATED) {
            return;
        }
        JTextPane srcPane = (JTextPane)evt.getSource();
        if (evt instanceof HTMLFrameHyperlinkEvent) {
            HTMLDocument doc = (HTMLDocument)pane.getDocument();
            doc.processHTMLFrameHyperlinkEvent((HTMLFrameHyperlinkEvent)evt);
        } else {
            String url = evt.getURL().toString();
            addressBar.setText(url);
            try {
                pane.setPage(url);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

	public static void main(String args[]) {
        SwingHTMLBrowser browser = new SwingHTMLBrowser();
        browser.setVisible(true);
	}
}