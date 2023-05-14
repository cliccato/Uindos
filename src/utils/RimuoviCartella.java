package utils;

import java.io.File;

public class RimuoviCartella {
    public static boolean rimuoviCartella(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    rimuoviCartella(file);
                }
            }
        }
        return directory.delete();
    }
}