package io.vivy.checkstyle.custom;

import com.puppycrawl.tools.checkstyle.Checker;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TestGenericFunctions {

    protected Checker prepareCheckStyleChecker(Class testClass) throws CheckstyleException {
        Checker checker = new Checker();
        checker.setModuleClassLoader(Thread.currentThread().getContextClassLoader());
        checker.configure(prepareConfiguration(testClass));
        return checker;
    }

    private DefaultConfiguration prepareConfiguration(Class testClass) {
        DefaultConfiguration checks = new DefaultConfiguration("Checks");
        DefaultConfiguration treeWalker = new DefaultConfiguration("TreeWalker");
        DefaultConfiguration parameterFormatting = new DefaultConfiguration(testClass.getCanonicalName());
        checks.addChild(treeWalker);
        treeWalker.addChild(parameterFormatting);
        return checks;
    }

    protected List<File> prepareFilesToBeChecked(String testFileName) {
        URL testFileUrl = getClass().getResource(testFileName);
        File testFile = new File(testFileUrl.getFile());
        List<File> files = new ArrayList<>();
        files.add(testFile);
        return files;
    }


}
