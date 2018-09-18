package io.vivy.checkstyle.custom;

public class TestMethodCallParenMatchCheckErrors {

    public void testMethod() {

        doNothing(
            "Nothing"
        );

        TestMethodCallParenMatchCheckErrors.nothing(
            4
        );

    }


    public static String doNothingOne(String nothing) {

        return nothing.concat(
           "Nothing"
        );
    }


    public static String doNothing(String nothing) {

        return nothing
           .concat(
              "Nothing"
           );
    }

    public static String doNothingThird(String nothing) {

        return nothing.concat(
           "Nothing"
        );
    }

    public static void nothing(int a) {

    }

    public static String doNothingFourth(String nothing) {
        return StringBuffer(
           nothing.toUpperCase()
        );
    }

    public static doNothingTest(String nothing) {
        new nothing.concat(
           "Nothing"
        );
    }

    public void testClosedCurly() {

        accessTokenConverter.setUserTokenConverter(new DefaultUserAuthenticationConverter() {
            @Override
            public Authentication extractAuthentication(java.util.Map<String, ?> map) {
                OAuthUser user = new OAuthUser(
                   OAuthUser.Type.valueOf(((String) map.get("stype")).toUpperCase()),
                   (String) map.get("id")
                );
                PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(user, null);
                authentication.setAuthenticated(true);
                return authentication;
                                                       }
        });
    }

}

