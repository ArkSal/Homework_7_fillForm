package helpers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHelper {
    private final static String downloadFileDirectory = "src/test/resources/downloadedTestFiles";

    public static String fileToLoadPath() {
        String projectPath = System.getProperty("user.dir");
        return projectPath + "/src/test/resources/filesToLoad/File.txt";
    }

    public static void createDirectory(){
        try {
            Files.createDirectories(Paths.get(downloadFileDirectory));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File getDownloadFile() {
        return new File(downloadFileDirectory);
    }

    public static int getFilesQuantityFromDownloadDirectory() {
        return (getDownloadFile()).list().length;
    }

    public static boolean checkIfFileWithSpecificNameExist(String fileName) {
        File file = new File(downloadFileDirectory + "/" + fileName + ".xlsx");
        if (file.exists()) {
            return true;
        }
        return false;
    }
}
