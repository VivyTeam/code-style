

public class TestSingleLambdaArguement {

    public  void testMethod(){

        (it) -> it.delayElements(
              Duration.ofMillis(200)
           )

    }

}
