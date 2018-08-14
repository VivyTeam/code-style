import com.intellij.openapi.application.PathManager;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class CodeStyleManager {

    private String projectBasePath;

    CodeStyleManager(String projectBasePath) {
        this.projectBasePath = projectBasePath;
    }

    void sync() {

        if(projectBasePath == null) return;

            checkForExistingCodestyles();

    }

    private boolean createFolder(String path) {
        return new File(path).mkdirs();
    }

    private void checkForExistingCodestyles() {

        String configPath = PathManager.getConfigPath();
        createFolder(configPath + "/codestyles");

        String sourceUrl = "https://raw.githubusercontent.com/oyewaleoyelami/check/master/checkstyle.xml";
        String targetPath = configPath + "/codestyles";
        try {
            copyFile(sourceUrl,targetPath);
        } catch ( IOException e ) {
            e.printStackTrace();
        }

    }

    private static void copyFile(String sourceURL, String targetDirectory) throws IOException
    {
        URL url = new URL(sourceURL);
        String fileName = sourceURL.substring(sourceURL.lastIndexOf('/') + 1, sourceURL.length());
        Path targetPath = new File(targetDirectory + File.separator + fileName).toPath();
        Files.copy(url.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

    }


}
