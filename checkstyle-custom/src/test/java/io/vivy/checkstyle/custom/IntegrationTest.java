package io.vivy.checkstyle.custom;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTest {

    @ParameterizedTest
    @MethodSource("checks")
    void shouldPassTheCheck(Class<? extends AbstractCheck> check, String source) throws Exception {
        int numberOfErrors = TestGenericFunctions
            .createChecker(check)
            .process(TestGenericFunctions.files(source));
        assertEquals(0, numberOfErrors);
    }

    static Stream<Arguments> checks() {
        return Stream.of(
            Arguments.of(SingleArgsLambdaSameLineCheck.class, "TestSwitchExpression.java"),
            Arguments.of(EmptyLineAfterMethodDefinitionCheck.class, "TestBlankLineAfterMethodDefinition.java"),
            Arguments.of(IfLiteralCustomCheck.class, "TestCustomIfLiteralCheckErrors.java"),
            Arguments.of(MethodCallParenMatchCheck.class, "TestMethodCallParenMatchCheckErrors.java"),
            Arguments.of(MethodParameterNumberCheck.class, "TestMethodNumberCheckErrors.java"),
            Arguments.of(MethodParametersOnSameLineCheck.class, "TestMethodParametersOnSameLineCheckErrors.java"),
            Arguments.of(NoLineBreakAfterAssignCheck.class, "TestNoLineBreakAfterAssignCheckErrors.java"),
            Arguments.of(SingleArgsLambdaSameLineCheck.class, "TestSingleLambdaArgument.java"),
            Arguments.of(SingleArgsLambdaSameLineCheck.class, "TestSingleLambdaArgument.java")
        );
    }

}
