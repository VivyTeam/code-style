import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.application.PathManager;

import java.io.File;
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
    try (InputStream resourceAsStream = getClass().getResourceAsStream("/META-INF/file/checkstyle.xml")) {
      copyFile(resourceAsStream, targetPath);

    } catch (Exception ee) {
      ee.printStackTrace();

    }

  }


  private static void copyFile(InputStream sourceURL, String targetDirectory)  {
    try {

      Path targetPath = new File(targetDirectory + File.separator + "checkstyle.xml").toPath();

      Files.copy(sourceURL, targetPath, StandardCopyOption.REPLACE_EXISTING);
      Notifications.Bus.notify(new Notification("io.vivy.idea.checkstyle",
           "Copying CheckStyle file",
           "checkstyle.xml copied successfully to the IDE directory",
           NotificationType.INFORMATION));
    } catch (Exception e) {
      Notifications.Bus.notify(new Notification("io.vivy.idea.checkstyle",
              "CheckStyle File Reading Error",
              e.getMessage(),
              NotificationType.ERROR));
    }
  }


}
