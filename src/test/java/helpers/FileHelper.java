package helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHelper {
    private static Logger logger = LoggerFactory.getLogger(FileHelper.class);
    private final static String downloadFileDirectory = "src/test/resources/downloadedTestFiles";

    public static String fileToLoadPath() {
        String projectPath = System.getProperty("user.dir");
        return projectPath + "/src/test/resources/filesToLoad/File.txt";
    }

    public static void createDirectory() {
        try {
            Files.createDirectories(Paths.get(downloadFileDirectory));
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Created download directory");
    }

    public static File getDownloadFile() {
        return new File(downloadFileDirectory);
    }

    public static int getFilesQuantityFromDownloadDirectory() {
        int filesQuantityInDownloadDirectory = getDownloadFile().list().length;
        logger.info("Files in download directory: {}", filesQuantityInDownloadDirectory);
        return filesQuantityInDownloadDirectory;

    }

    public static boolean checkIfFileWithSpecificNameExist(String fileName) {
        File file = new File(downloadFileDirectory + "/" + fileName + ".xlsx");
        logger.info("Validating if specific file exist in directory");
        if (file.exists()) {
            logger.info("File found in directory");
            return true;
        }
        logger.error("File not found in directory");
        return false;
    }
}
