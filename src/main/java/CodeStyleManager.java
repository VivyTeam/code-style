import com.intellij.openapi.application.PathManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class CodeStyleManager {

    private String projectBasePath;

    CodeStyleManager(String projectBasePath) {
        this.projectBasePath = projectBasePath;
    }

    void sync() {

        if (projectBasePath == null) {
            return;
        }

        checkForExistingCodestyles();

    }

    private boolean createFolder(String path) {
        return new File(path).mkdirs();
    }

    private void checkForExistingCodestyles() {

        String configPath = PathManager.getConfigPath();
        createFolder(configPath + "/codestyles");

        String targetPath = configPath + "/codestyles";
        ClassLoader classLoader = getClass().getClassLoader();
        File sourceFile = new File(classLoader.getResource("file/checkstyle.xml").getFile());

        try {
            copyFile(sourceFile, targetPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void copyFile(File sourceFile, String targetDirectory) throws IOException {
        Path targetPath = new File(targetDirectory + File.separator + sourceFile.getName())
                .toPath();
        Files.copy(sourceFile.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);

    }


}
