package settings;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;


public class ProjectSettings {
    private static final String PREFIX = "VivyJavaPlugin";

    public static final String VivyJava_ENABLED_FOR_PROJECT = PREFIX + "EnabledForProject";

    public static boolean isVivyJavaEnabledForProject(@NotNull final Project project){
        return isEnabled(project,VivyJava_ENABLED_FOR_PROJECT);

    }
    public static void setLombokEnabledInProject(@NotNull final Project project, boolean value) {
        setEnabled(project, VivyJava_ENABLED_FOR_PROJECT, value);
    }

    public static boolean isEnabled(@NotNull Project project, final String propertyName) {
        return isEnabled(PropertiesComponent.getInstance(project), propertyName);
    }


    public static boolean isEnabled(PropertiesComponent properties, String propertyName) {
        return isEnabled(properties, propertyName, true);
    }

    public static boolean isEnabled(PropertiesComponent properties, String propertyName, boolean defaultValue) {
        return properties.getBoolean(propertyName, defaultValue);
    }

    public static void setEnabled(@NotNull Project project, final String propertyName, boolean value) {
        setEnabled(PropertiesComponent.getInstance(project), propertyName, value);
    }

    public static void setEnabled(PropertiesComponent properties, String propertyName, boolean value) {
        properties.setValue(propertyName, String.valueOf(value), "");
    }

}
