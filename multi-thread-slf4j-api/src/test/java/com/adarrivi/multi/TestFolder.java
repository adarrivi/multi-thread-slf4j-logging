package com.adarrivi.multi;

import java.nio.file.Path;
import java.nio.file.Paths;

public class TestFolder {

    private static final String FILE_SEPARATOR = System.getProperty("file.separator");
    private static final String PROJECT_FOLDER = ".." + FILE_SEPARATOR + "multi-thread-slf4j-api" + FILE_SEPARATOR;


    private Path folderPath;

    public TestFolder(String path) {
        findValidFolderPath(path);
    }

    //Intellij gives a different root path (parent usually) than other IDEs when using Path class.
    private void findValidFolderPath(String path) {
        folderPath = Paths.get(path);
        if (!folderPath.toFile().exists()) {
            folderPath = Paths.get(PROJECT_FOLDER + path);
        }
    }

    public Path getFolderPath() {
        return folderPath;
    }
}
