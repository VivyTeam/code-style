package io.vivy.checkstyle.custom;

import com.puppycrawl.tools.checkstyle.Checker;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SingleArgumentLambdaShouldStartOnSame {

    TestGenericFunctions genericFunction = new TestGenericFunctions();

    @Test
    public void checkForSingleArgumentLambdaOnSameLine() throws Exception {
        Checker checker = genericFunction.prepareCheckStyleChecker(SingleArgsLambdaSameLineCheck.class);
        List<File> files = genericFunction.prepareFilesToBeChecked("TestSingleLambdaArgument.java");
        int numberOfErrors = checker.process(files);
        assertThat(numberOfErrors, is(0));
    }

}
