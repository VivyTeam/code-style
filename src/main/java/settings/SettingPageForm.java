package settings;

import com.intellij.openapi.project.Project;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class SettingPageForm extends JPanel {

    private final JCheckBox suppressErrorsCheckbox = new JCheckBox();
    private static final Insets COMPONENT_INSETS = JBUI.insets(4);


    private final Project project;

    public SettingPageForm(@NotNull final Project project){
        super(new BorderLayout());

        this.project = project;

        initialise();
    }

    private void initialise() {
        add(buildConfigPanel(), BorderLayout.CENTER);
    }

    private JPanel buildConfigPanel() {


        suppressErrorsCheckbox.setText("Error Check");
        suppressErrorsCheckbox.setToolTipText("No No Tool Tip");



        final JPanel configFilePanel = new JPanel(new GridBagLayout());
        configFilePanel.setOpaque(false);

        configFilePanel.add(suppressErrorsCheckbox, new GridBagConstraints(
                0, 1, 2, 1, 1.0, 0.0, GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL, COMPONENT_INSETS, 0, 0));


        return configFilePanel;
    }
}
