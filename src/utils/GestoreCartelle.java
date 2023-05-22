package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class GestoreCartelle {
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
    public static void copyFolder(Path source, Path destination) throws IOException {
        // Create the destination folder if it doesn't exist
        if (!Files.exists(destination)) {
            Files.createDirectories(destination);
        }

        // Copy all files and sub-folders recursively
        Files.walk(source)
                .forEach(sourcePath -> {
                    try {
                        Path destinationPath = destination.resolve(source.relativize(sourcePath));
                        Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        System.err.println("Error occurred while copying: " + e.getMessage());
                    }
                });
    }
}