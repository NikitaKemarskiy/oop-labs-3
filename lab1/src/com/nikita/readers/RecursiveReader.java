package com.nikita.readers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class RecursiveReader {
    private File file;
    private ExecutorService service;

    public RecursiveReader(File file) throws IOException {
        setFile(file);
    }

    public Set<File> readRecursively() {
        service = Executors.newCachedThreadPool();
        Set<File> files = readRecursively(file);
        service.shutdown();
        return files;
    }

    public Set<File> readRecursively(File file) {
        Set<File> files = new HashSet<>();

        if (file.isFile()) { // File is not a directory
            files.add(file);
            return files; // Return set with an only one file
        }

        File[] filesInCurrentDirectory = file.listFiles();
        List<Future<Set<File>>> futureList = new ArrayList<>();

        for (File fileInCurrentDirectory : filesInCurrentDirectory) {
            if (fileInCurrentDirectory.isFile()) {
                files.add(fileInCurrentDirectory);
            } else if (fileInCurrentDirectory.isDirectory()) {
                Callable<Set<File>> callable = () -> readRecursively(fileInCurrentDirectory);
                futureList.add(service.submit(callable));
            }
        }

        if (futureList.size() > 0) {
            for (Future<Set<File>> future : futureList) {
                try {
                    files.addAll(future.get());
                } catch (ExecutionException | InterruptedException err) {
                    System.out.printf("Thread reading directory error: %s%n", err.getMessage());
                }
            }
        }

        return files;
    }

    public void setFile(File file) throws IOException {
        if (!file.exists()) {
            throw new IOException("File does not exist");
        }
        this.file = file;
    }
}
