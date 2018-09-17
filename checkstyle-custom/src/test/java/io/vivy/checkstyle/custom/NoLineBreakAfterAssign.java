package io.vivy.checkstyle.custom;

import com.puppycrawl.tools.checkstyle.Checker;

import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class NoLineBreakAfterAssign {

    TestGenericFunctions genericFunction = new TestGenericFunctions();

    @Test
    public void checkThatNoLineBreakAfterAssign() throws Exception {
        Checker checker = genericFunction.prepareCheckStyleChecker(NoLineBreakAfterAssignCheck.class);
        List<File> files = genericFunction.prepareFilesToBeChecked("TestNoLineBreakAfterAssignCheckErrors.java");
        int numberOfErrors = checker.process(files);
        assertThat(numberOfErrors, is(0));
    }


}
