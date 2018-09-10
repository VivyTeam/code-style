
public class TestMethodCallParenMatchCheckErrors {

    public void testMethod() {

        doNothing(
           "Nothing"
        );

        new StringBuffer(
           System.out.println("Testing Method Parent")
        );

        TestMethodCallParenMatchCheckErrors.nothing(
           4
        );

    }


    public static String doNothing(String nothing) {

        return nothing.concat(
           "Nothing"
               );
    }


    public static String doNothingSecond(String nothing) {

        return new nothing.concat(
           "Nothing"
               );
    }

    public static void nothing(int a) {

    }

}
