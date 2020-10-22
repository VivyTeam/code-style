package io.vivy.checkstyle.custom;

import com.puppycrawl.tools.checkstyle.Checker;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoLineBreakAfterAssign {

    TestGenericFunctions genericFunction = new TestGenericFunctions();

    @Test
    void checkThatNoLineBreakAfterAssign() throws Exception {
        Checker checker = genericFunction.prepareCheckStyleChecker(NoLineBreakAfterAssignCheck.class);
        List<File> files = genericFunction.prepareFilesToBeChecked("TestNoLineBreakAfterAssignCheckErrors.java");
        int numberOfErrors = checker.process(files);
        assertEquals(0, numberOfErrors);
    }

}
