package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class GestoreConfig {
    public static final int CAMPO_BACKGROUND = 1;

    public static Config loadConfig(String username) {
        Config config = new Config();

        try (FileReader fileReader = new FileReader(UindosPath.USER_FOLDER_PATH + username + "/" + UindosFileName.CONFIG_FILE_NAME);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
                line = bufferedReader.readLine();
            // config.setBackground(bufferedReader.readLine());
            System.out.println(line);
            config.setBackground(line);

        } catch (IOException e) {
            System.err.println("Error occurred while reading the file: " + e.getMessage());
        }

        return config;
    }

    public static void changeConfig(String username, int lineConfig, String newConfig) {
            String filePath = UindosPath.USER_FOLDER_PATH + username + "/" + UindosFileName.CONFIG_FILE_NAME;
            try {
                // Create a temporary file for writing the modified content
                File tempFile = File.createTempFile("temp", null);
                Path tempPath = tempFile.toPath();

                // Read the original file and write the modified content to the temporary file
                try (BufferedReader reader = Files.newBufferedReader(Path.of(filePath));
                    BufferedWriter writer = Files.newBufferedWriter(tempPath)) {

                    String line;
                    int lineNumber = 1;
                                   while ((line = reader.readLine()) != null) {
                    if (lineNumber == lineConfig) {
                        writer.write(newConfig);
                    } else {
                        writer.write(line);
                    }

                    if (reader.ready()) {
                        writer.newLine();
                    }

                    lineNumber++;
                }
                }

                // Replace the original file with the temporary file
                Files.move(tempPath, Path.of(filePath), StandardCopyOption.REPLACE_EXISTING);

                System.out.println("Line replaced successfully!");

            } catch (IOException e) {
                System.err.println("Error occurred while replacing the line: " + e.getMessage());
            }
        }
}