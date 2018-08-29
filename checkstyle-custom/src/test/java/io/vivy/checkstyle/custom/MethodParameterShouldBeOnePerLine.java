package io.vivy.checkstyle.custom;

import com.puppycrawl.tools.checkstyle.Checker;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MethodParameterShouldBeOnePerLine {

    @Test
    public void checkForParametersOnDifferentLines() throws Exception {
        Checker checker = prepareCheckStyleChecker();
        List<File> files = prepareFilesToBeChecked();
        int numberOfErrors = checker.process(files);
        assertThat(numberOfErrors, is(0));
    }

    private Checker prepareCheckStyleChecker() throws CheckstyleException {
        Checker checker = new Checker();
        checker.setModuleClassLoader(Thread.currentThread().getContextClassLoader());
        checker.configure(prepareConfiguration());
        return checker;
    }

    private DefaultConfiguration prepareConfiguration() {
        DefaultConfiguration checks = new DefaultConfiguration("Checks");
        DefaultConfiguration treeWalker = new DefaultConfiguration("TreeWalker");
        DefaultConfiguration parameterFormating = new DefaultConfiguration(MethodParameterNumberCheck.class.getCanonicalName());
        checks.addChild(treeWalker);
        treeWalker.addChild(parameterFormating);
        return checks;
    }

    private List<File> prepareFilesToBeChecked() {
        String testFileName = "TestClassWithErrors.java";
        URL testFileUrl = getClass().getResource(testFileName);
        File testFile = new File(testFileUrl.getFile());
        List<File> files = new ArrayList<File>();
        files.add(testFile);
        return files;
    }

}
