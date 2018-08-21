import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.application.PathManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
        InputStream inputStream = getClass().getResourceAsStream("/file/checkstyle.xml");

        try {
            copyFile(inputStream, targetPath);
        } catch (IOException e) {
            Notifications.Bus.notify(new Notification("io.vivy.idea.checkstyle",
                    "CheckStyle File Reading Error",
                    e.getMessage(),
                    NotificationType.ERROR));
        }

    }

    private static void copyFile(InputStream sourceFile, String targetDirectory)
            throws IOException {
        Path targetPath = new File(targetDirectory + File.separator + "checkstyle")
                .toPath();
        Files.copy(sourceFile, targetPath, StandardCopyOption.REPLACE_EXISTING);
        Notifications.Bus.notify(new Notification("io.vivy.idea.checkstyle",
                "Copying CheckStyle file",
                "CheckStyle file copied successfully to the IDE directory",
                NotificationType.INFORMATION));

    }


}
