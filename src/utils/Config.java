package utils;

import java.awt.Font;

public class Config {
    private String background;
    private String pointer;
    private Font font;

    public Config(String background, String pointer, Font font) {
        this.background = background;
        this.pointer = pointer;
    }

    public Config() {
        ; //TMCH
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public void setPointer(String pointer) {
        this.pointer = pointer;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public String getBackground() {
        return background;
    }

    public String getPointer() {
        return pointer;
    }

    public Font getFont() {
        return font;
    }
}