

public class TestMethodParametersOnSameLineCheckErrors{

    public static String checkSameLineMethod(String first, String second, String third){

        return System.out.println("Violation if there are less or equal to 3 parameters in total \n and they are on different line from the definition");
    }

    likeConstructor() {

    }

    public likeContsructor(String a, int b) {

    }

    public void likeMethod(String a,String b,String c){


    }

    public static List<String> likeMethod() {

    }

    public Mono<ResponseEntity> findAll(
       @AuthenticationPrincipal(errorOnInvalidType = true) VivyUser vivyUser,
       @RequestParam(required = false, name = "dateTo") String dateToString
    ) {

    }


    public Mono<ResponseEntity> findAll(VivyUser vivyUser, @RequestParam(required = false, name = "dateTo") String dateToString){

    }

}