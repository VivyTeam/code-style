package io.vivy.checkstyle.custom;

import com.puppycrawl.tools.checkstyle.Checker;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTest {

    TestGenericFunctions genericFunction = new TestGenericFunctions();

    @Test
    void shouldWorkWithSwitchExpression() throws Exception {
        Checker checker = genericFunction.prepareCheckStyleChecker(MethodParameterNumberCheck.class);
        List<File> files = genericFunction.prepareFilesToBeChecked("TestSwitchExpression.java");
        int numberOfErrors = checker.process(files);
        assertEquals(0, numberOfErrors);
    }

}
