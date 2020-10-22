package io.vivy.checkstyle.custom;

import com.puppycrawl.tools.checkstyle.Checker;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestGenericFunctions {

    protected static Checker createChecker(Class<? extends AbstractCheck> testClass) throws CheckstyleException {
        Checker checker = new Checker();
        checker.setModuleClassLoader(Thread.currentThread().getContextClassLoader());
        checker.configure(prepareConfiguration(testClass));
        return checker;
    }

    private static DefaultConfiguration prepareConfiguration(Class<? extends AbstractCheck> testClass) {
        DefaultConfiguration checks = new DefaultConfiguration("Checks");
        DefaultConfiguration treeWalker = new DefaultConfiguration("TreeWalker");
        DefaultConfiguration parameterFormatting = new DefaultConfiguration(testClass.getCanonicalName());
        checks.addChild(treeWalker);
        treeWalker.addChild(parameterFormatting);
        return checks;
    }

    protected static List<File> files(String... javaResources) {
        return Arrays.stream(javaResources)
            .map(TestGenericFunctions.class::getResource)
            .map(URL::getFile).map(File::new)
            .collect(Collectors.toList());
    }

}
