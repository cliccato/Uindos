package app.Browser;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.html.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;
import utils.GestoreFrame;
import utils.UindosPath;

public class BrowserApp extends JFrame implements HyperlinkListener {
  private JButton buttonBack = new JButton("<"), buttonForward = new JButton(">");
  private JTextField locationTextField = new JTextField(35);
  private JEditorPane displayEditorPane = new JEditorPane();
  private ArrayList pageList = new ArrayList();

  public BrowserApp() {
    setTitle("Brouser");
    setSize(640, 480);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setVisible(true);
    setIconImage(new ImageIcon(UindosPath.BROWSER_LOGO_PATH).getImage());
    JPanel bttnPanel = new JPanel();

    buttonBack.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        backActn();
      }
    });

    buttonBack.setEnabled(false);
    bttnPanel.add(buttonBack);
    buttonForward.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        forwardActn();
      }
    });

    buttonForward.setEnabled(false);
    bttnPanel.add(buttonForward);
    locationTextField.addKeyListener(new KeyAdapter() {
      public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          actionGo();
        }
      }
    });

    bttnPanel.add(locationTextField);
    JButton bttnGo = new JButton("GO");
    bttnGo.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        actionGo();
      }
    });

    bttnPanel.add(bttnGo);
    displayEditorPane.setContentType("text/html");
    displayEditorPane.setEditable(false);
    displayEditorPane.addHyperlinkListener(this);

    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(bttnPanel, BorderLayout.NORTH);
    getContentPane().add(new JScrollPane(displayEditorPane), BorderLayout.CENTER);
    GestoreFrame.aggiungiFrame(this);
  }

  private void backActn() {
    URL currentUrl = displayEditorPane.getPage();
    int pageIndex = pageList.indexOf(currentUrl.toString());
    try {
      showPage(new URL((String) pageList.get(pageIndex - 1)), false);
    } catch (Exception e) {
    }
  }

  private void forwardActn() {
    URL currentUrl = displayEditorPane.getPage();
    int pageIndex = pageList.indexOf(currentUrl.toString());
    try {
      showPage(new URL((String) pageList.get(pageIndex + 1)), false);
    } catch (Exception e) {
    }
  }

  private void actionGo() {
    URL verifiedUrl = verifyUrl(locationTextField.getText());
    if (verifiedUrl != null) {
      showPage(verifiedUrl, true);
    } else {
      System.out.println("Invalid URL");
    }
  }

  private URL verifyUrl(String url) {
    if (!url.toLowerCase().startsWith("http://")&&!url.toLowerCase().startsWith("https://"))
      return null;

    URL verifiedUrl = null;
    try {
      verifiedUrl = new URL(url);
    } catch (Exception e) {
      return null;
    }

    return verifiedUrl;
  }

  private void showPage(URL pageUrl, boolean addToList) {
    try {
      URL currentUrl = displayEditorPane.getPage();
      displayEditorPane.setPage(pageUrl);
      URL newUrl = displayEditorPane.getPage();
      if (addToList) {
        int listSize = pageList.size();
        if (listSize <= 0) {
          return;
        }
        int pageIndex = pageList.indexOf(currentUrl.toString());
        if (pageIndex >= listSize - 1) {
          return;
        }
        for (int i = listSize - 1; i > pageIndex; i--) {
          pageList.remove(i);
        }
        pageList.add(newUrl.toString());
      }
      locationTextField.setText(newUrl.toString());
      updateBttns();
    } catch (Exception e) {
      //System.out.println("Unable to load page");
    }
  }

  private void updateBttns() {
    if (pageList.size() < 2) {
      buttonBack.setEnabled(false);
      buttonForward.setEnabled(false);
    } else {
      URL currentUrl = displayEditorPane.getPage();
      int pageIndex = pageList.indexOf(currentUrl.toString());
      buttonBack.setEnabled(pageIndex > 0);
      buttonForward.setEnabled(pageIndex < (pageList.size() - 1));
    }
  }

  public void hyperlinkUpdate(HyperlinkEvent event) {
    HyperlinkEvent.EventType eventType = event.getEventType();
    if (eventType == HyperlinkEvent.EventType.ACTIVATED) {
      if (event instanceof HTMLFrameHyperlinkEvent) {
        HTMLFrameHyperlinkEvent linkEvent = (HTMLFrameHyperlinkEvent) event;
        HTMLDocument document = (HTMLDocument) displayEditorPane.getDocument();
        document.processHTMLFrameHyperlinkEvent(linkEvent);
      } else {
        showPage(event.getURL(), true);
      }
    }
  }

}
