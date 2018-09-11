package io.vivy.checkstyle.custom;

public class TestSingleLambdaArgument {

    public  void testMethod() {
        it -> it.delayElements(
             Duration.ofMillis(200)
           )

    }

}
