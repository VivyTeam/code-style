package io.vivy.checkstyle.custom;

import com.puppycrawl.tools.checkstyle.Checker;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MethodParameterShouldBeOnePerLine {

    TestGenericFunctions genericFunction = new TestGenericFunctions();

    @Test
    void checkForParametersOnDifferentLines() throws Exception {
        Checker checker = genericFunction.prepareCheckStyleChecker(MethodParameterNumberCheck.class);
        List<File> files = genericFunction.prepareFilesToBeChecked("TestMethodNumberCheckErrors.java");
        int numberOfErrors = checker.process(files);
        assertEquals(0, numberOfErrors);
    }

}
