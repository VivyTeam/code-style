
public class TestCustomIfLiteralCheckErrors {

    public void LiteralIfMethod() {

        if((a < 4) || (a == b)){
            if(optimistic){
                System.out.println("Custom IF Literal");
            }

        }
    }
}
