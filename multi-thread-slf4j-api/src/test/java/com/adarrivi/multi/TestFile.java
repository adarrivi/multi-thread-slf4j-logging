package com.adarrivi.multi;

import java.nio.file.Path;
import java.nio.file.Paths;

public class TestFile {

    private static final String FILE_SEPARATOR = System.getProperty("file.separator");
    private static final String PROJECT_FOLDER = ".." + FILE_SEPARATOR + "multi-thread-slf4j-api" + FILE_SEPARATOR;

    private Path filePath;

    public TestFile(String path, String fileName) {
        findValidFilePath(path, fileName);
    }

    //Intellij gives a different root path (parent usually) than other IDEs when using Path class.
    private void findValidFilePath(String path, String fileName) {
        filePath = Paths.get(path, fileName);
        if (!filePath.toFile().exists()) {
            filePath = Paths.get(PROJECT_FOLDER + path, fileName);
        }
    }

    public Path getFilePath() {
        return filePath;
    }
}
