package com.nikita;

import com.nikita.readers.RecursiveReader;
import com.nikita.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private final static Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }

    private static void scanFileOrDirectory() throws IOException {
        System.out.print("Input file or directory path: ");
        String path = scanner
            .nextLine()
            .trim();
        // Create file from path
        File file = new File(path);
        // Create new recursive reader and read files recursively
        RecursiveReader recursiveReader = new RecursiveReader(file);
        Set<File> files = recursiveReader.readRecursively();
        // Get processed java source files text
        String processedJavaSourceFilesText = FileUtils.getProcessedJavaSourceFile(files);
        // Write this processed text to output file
        FileUtils.writeProcessedJavaSourceFile(processedJavaSourceFilesText);
    }

    public static void main(String args[]){
        do {
            try {
                scanFileOrDirectory();
                break;
            } catch (Exception err) {
                System.out.printf("Error occurred: %s%n", err.getMessage());
                continue;
            }
        } while (true);
    }
}