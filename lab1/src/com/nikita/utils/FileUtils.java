package com.nikita.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class FileUtils {
    private final static String OUTPUT_DIRECTORY_PATH = "./public";
    private final static String DELIMITER_LINE = "==========================";

    private FileUtils() {}

    public static String getFileExtension(File file) {
        String name = file.getName();
        int index = name.lastIndexOf(".");
        return index == -1
            ? ""
            : name.substring(index + 1);
    }

    public static String getProcessedJavaSourceFile(Set<File> files) throws FileNotFoundException {
        return files.stream()
            .parallel()
            .filter(item -> FileUtils.getFileExtension(item).toLowerCase().equals("java"))
            .map(item -> {
                try {
                    return FileUtils.getProcessedJavaSourceFile(item);
                } catch (FileNotFoundException err) {
                    System.out.printf("Error reading Java file: %s%n", err.getMessage());
                    return null;
                }
            })
            .collect(Collectors.joining("\n\n"));
    }

    public static String getProcessedJavaSourceFile(File file) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            String data = String.format(
                    "%s%n%s%n",
                    file.getName(),
                    DELIMITER_LINE
            );
            while (scanner.hasNextLine()) {
                data += String.format("%s%n", scanner.nextLine());
            }
            data = data.replaceAll("(?s)/\\*.*\\*/", "");
            data = data.replaceAll("(?s)/\\*.*$", "");
            data = data.replaceAll("(?m)//.*$", "");
            data = data.replaceAll("(?m)[\\s]+$", "");
            data = data.replaceAll("(?m)^[\\s]*\\r?\\n", "");
            return data;
        }
    }

    public static void writeProcessedJavaSourceFile(String processedJavaSourceFilesText) throws IOException {
        createDirectoryIfNotExists(OUTPUT_DIRECTORY_PATH);

        String processedJavaSourceFileDirectory = Paths.get(OUTPUT_DIRECTORY_PATH, Utils.getCurrentDate()).toString();
        FileUtils.createDirectoryIfNotExists(processedJavaSourceFileDirectory);
        String processedJavaSourceFilePath = String.format(
                "%s.%s",
                Paths.get(processedJavaSourceFileDirectory, Utils.getCurrentTime()).toString(),
                "txt"
        );

        try (FileWriter writer = new FileWriter(processedJavaSourceFilePath)) {
            writer.write(processedJavaSourceFilesText);
            System.out.printf("File with processed Java source code was written: %s%n", processedJavaSourceFilePath);
        }
    }

    public static void createDirectoryIfNotExists(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
    }
}
