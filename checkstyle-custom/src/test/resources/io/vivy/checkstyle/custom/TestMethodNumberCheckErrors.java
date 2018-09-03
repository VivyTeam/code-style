package io.vivy.checkstyle.custom;

public class TestMethodNumberCheckErrors {

    /**
     * Method Definition without Error
     */
    public void randomValidMethod(
            String first,
                                       String second,
                                         String third,
                                         String fourth
    ){

        System.out.println("Violation if there are more than max:3 parameters in total \n and more than one on the same line");
    }

}
