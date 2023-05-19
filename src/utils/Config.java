package utils;

public class Config {
    private String background;
    private String pointer;

    public Config(String background, String pointer) {
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

    public String getBackground() {
        return background;
    }

    public String getPointer() {
        return pointer;
    }
}