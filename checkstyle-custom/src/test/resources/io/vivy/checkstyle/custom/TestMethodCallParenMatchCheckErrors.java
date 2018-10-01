package io.vivy.checkstyle.custom;

public class TestMethodCallParenMatchCheckErrors {

//    public void testMethod() {
//
//        doNothing(
//            "Nothing"
//        );

//        TestMethodCallParenMatchCheckErrors.nothing(
//            4
//        );

//    }
//
//
//    public static String doNothingOne(String nothing) {
//
//        return nothing.concat(
//           "Nothing"
//        );
//    }
//
//
//    public static String doNothing(String nothing) {
//
//        return nothing
//           .concat(
//              "Nothing"
//           );
//    }
//
//    public static String doNothingThird(String nothing) {
//
//        return nothing.concat(
//           "Nothing"
//        );
//    }
//
//    public static void nothing(int a) {
//
//    }
//
//    public static String doNothingFourth(String nothing) {
//        return StringBuffer(
//           nothing.toUpperCase()
//        );
//    }
////
//    public static doNothingTest(String nothing) {
//        new nothing.concat(
//           "Nothing"
//        );
//    }
//
//    public void testClosedCurly() {
//
//        accessTokenConverter.setUserTokenConverter(new DefaultUserAuthenticationConverter() {
//            @Override
//            public Authentication extractAuthentication(java.util.Map<String, ?> map) {
//                OAuthUser user = new OAuthUser(
//                   OAuthUser.Type.valueOf(((String) map.get("stype")).toUpperCase()),
//                   (String) map.get("id")
//                );
//                PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(user, null);
//                authentication.setAuthenticated(true);
//                return authentication;
//                                                       }
//        });
//    }

    public void newTestCase(int b){
        given(requestSpecification)
                .contentType(ContentType.JSON)
                .auth().oauth2(getAccessToken(user))
                .body(Map.of(
                        "fileId", fileId,
                        "signature", "a:" + UUID.randomUUID()
                ))
                .put(SUBJECT_PUBLIC_EMERGENCY_BY_PUB_ID, publicId)
                .then()
                .statusCode(200);
    }

}

