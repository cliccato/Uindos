package System.Desktop;

import app.Browser.BrowserApp;

public class DesktopListener{
    String name;

    public DesktopListener(String name) {
        this.name = name;
        
        switch (name) {
            case "brouser":
                new BrowserApp();
                break;
        }
    }
}
