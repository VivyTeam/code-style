
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

        return new StringBuffer(
           nothing.toUpperCase()
        );
    }

    public static void nothing(int a) {

    }

}
