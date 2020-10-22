package io.vivy.checkstyle.custom;

import com.puppycrawl.tools.checkstyle.Checker;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IfParametersShouldBeOnDifferentLine {

    TestGenericFunctions genericFunction = new TestGenericFunctions();

    @Test
    void checkThatIfIsWellFormatted() throws Exception {
        Checker checker = genericFunction.prepareCheckStyleChecker(IfLiteralCustomCheck.class);
        List<File> files = genericFunction.prepareFilesToBeChecked("TestCustomIfLiteralCheckErrors.java");
        int numberOfErrors = checker.process(files);
        assertEquals(0, numberOfErrors);
    }

}
