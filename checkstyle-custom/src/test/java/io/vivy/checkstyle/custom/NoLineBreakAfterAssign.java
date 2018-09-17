package io.vivy.checkstyle.custom;

import com.puppycrawl.tools.checkstyle.Checker;

import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class NoLineBreakAfterAssign {

    GenericFunction genericFunction = new GenericFunction();

    @Test
    public void checkThatNoLineBreakAfterAssign() throws Exception {
        Checker checker = genericFunction.prepareCheckStyleChecker();
        List<File> files = genericFunction.prepareFilesToBeChecked("TestNoLineBreakAfterAssignCheckErrors.java");
        int numberOfErrors = checker.process(files);
        assertThat(numberOfErrors, is(0));
    }


}
