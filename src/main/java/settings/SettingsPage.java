package settings;

import com.intellij.icons.AllIcons;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.ui.TitledSeparator;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SettingsPage implements Configurable {

    private static final Logger LOG = Logger.getInstance(SettingsPage.class);

    private final Project project;

    private JPanel myGeneralPanel;
    private JPanel myVivyPanel;

    private JCheckBox enableVivyCodeStyleForProject;

    private PropertiesComponent myPropertiesComponent;

    public SettingsPage(@NotNull Project project){

        this.project = project;

    }

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "VivyJava CodeStyle";
    }

    @Nullable
    @Override
    public JComponent createComponent() {

        initialSettings();

        //add a listener to uncheck all the boxes if plugin is deactivated
        enableVivyCodeStyleForProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBox checkbox = (JCheckBox) e.getSource();
                boolean selected = checkbox.getModel().isSelected();
                myVivyPanel.setEnabled(selected);
            }
            }
        );

        myGeneralPanel = new JPanel(new BorderLayout());
        JCheckBox enableCheckBox = new JCheckBox();
        enableCheckBox.setText("Enable VivyJava CodeStyle for this Project");
        myGeneralPanel.add(enableCheckBox);
     return  myGeneralPanel;
    }

    public void initialSettings(){
        enableVivyCodeStyleForProject.setSelected(ProjectSettings.isEnabled(myPropertiesComponent, ProjectSettings.VivyJava_ENABLED_FOR_PROJECT));

    }
    @Override
    public boolean isModified() {
        return enableVivyCodeStyleForProject.isSelected() != ProjectSettings.isEnabled(myPropertiesComponent,ProjectSettings.VivyJava_ENABLED_FOR_PROJECT);
    }

    @Override
    public void apply() throws ConfigurationException {
        ProjectSettings.setEnabled(myPropertiesComponent,ProjectSettings.VivyJava_ENABLED_FOR_PROJECT, enableVivyCodeStyleForProject.isSelected());

    }

    private JPanel buildRuleFilePanel() {

        final JPanel container = new JPanel(new BorderLayout());

        return container;
    }
}
