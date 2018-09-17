package io.vivy.checkstyle.custom;

import com.puppycrawl.tools.checkstyle.Checker;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MethodParameterShouldBeOnePerLine {

    TestGenericFunctions genericFunction = new TestGenericFunctions();

    @Test
    public void checkForParametersOnDifferentLines() throws Exception {
        Checker checker = genericFunction.prepareCheckStyleChecker(MethodParameterNumberCheck.class);
        List<File> files = genericFunction.prepareFilesToBeChecked("TestMethodNumberCheckErrors.java");
        int numberOfErrors = checker.process(files);
        assertThat(numberOfErrors, is(0));
    }

}
