package io.vivy.checkstyle.custom;

import com.puppycrawl.tools.checkstyle.Checker;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MethodCallParenShouldMatch {

    GenericFunction genericFunction = new GenericFunction();

    @Test
    public void checkThatMethodCallParenMatches() throws Exception {
        Checker checker = genericFunction.prepareCheckStyleChecker();
        List<File> files = genericFunction.prepareFilesToBeChecked("TestMethodCallParenMatchCheckErrors.java");
        int numberOfErrors = checker.process(files);
        assertThat(numberOfErrors, is(0));
    }

}
