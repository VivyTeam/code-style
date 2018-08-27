import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.psi.codeStyle.CodeStyleConfigurable;
import com.intellij.psi.codeStyle.CodeStyleScheme;
import com.intellij.psi.codeStyle.CodeStyleSchemes;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import org.jetbrains.annotations.NotNull;


public class OnActionSync extends AnAction {

  @Override
  public void actionPerformed(AnActionEvent e) {
    final Project project = e.getProject();
    if (project == null) {
      return;
    }

    CodeStyleScheme vivyJava = CodeStyleSchemes.getInstance().findPreferredScheme("VivyJava");
//    System.out.println("Verdict " + vivyJava.isDefault() + "Name " + vivyJava.getName());

    new CodeStyleManager(project.getBasePath())
        .sync();



  }

}

