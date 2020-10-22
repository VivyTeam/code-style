package io.vivy.checkstyle.custom;

import com.puppycrawl.tools.checkstyle.Checker;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MethodParametersShouldBeOnSameLine {

    TestGenericFunctions genericFunction = new TestGenericFunctions();

    @Test
    void checkForParametersOnSameLine() throws Exception {
        Checker checker = genericFunction.prepareCheckStyleChecker(MethodParametersOnSameLineCheck.class);
        List<File> files = genericFunction.prepareFilesToBeChecked("TestMethodParametersOnSameLineCheckErrors.java");
        int numberOfErrors = checker.process(files);
        assertEquals(0, numberOfErrors);
    }

}
