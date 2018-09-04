
public class TestMethodCallParenMatchCheckErrors{

    /**
     * Method Definition without Error
     */
    public void TestMethod(){

        doNothing(
           "Nothing"
        );

        new StringBuffer(
           System.out.println("Testing Method Parent")
        );

    }


    public static String doNothing(String nothing){

        return new StringBuffer(
           nothing.toUpperCase()
        );
    }

}
