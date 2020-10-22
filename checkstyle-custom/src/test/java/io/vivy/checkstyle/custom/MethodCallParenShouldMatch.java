package io.vivy.checkstyle.custom;

import com.puppycrawl.tools.checkstyle.Checker;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MethodCallParenShouldMatch {

    TestGenericFunctions genericFunction = new TestGenericFunctions();

    @Test
    void checkThatMethodCallParenMatches() throws Exception {
        Checker checker = genericFunction.prepareCheckStyleChecker(MethodCallParenMatchCheck.class);
        List<File> files = genericFunction.prepareFilesToBeChecked("TestMethodCallParenMatchCheckErrors.java");
        int numberOfErrors = checker.process(files);
        assertEquals(0, numberOfErrors);
    }

}
