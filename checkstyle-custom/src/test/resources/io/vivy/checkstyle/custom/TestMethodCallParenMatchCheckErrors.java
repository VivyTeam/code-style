package io.vivy.checkstyle.custom;

import java.time.Duration;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
        nothing.concat(
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

    public void newTestCase(int b) {
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

    public void additionalTestCase() {
        Map<String, Object> token = Map.of(
                "id", user.getId(),
                "stype", user.getType(),
                "exp", Instant.now().plusSeconds(3600).getEpochSecond()
        );
    }

    public void lambdaChainTestCase() {
        await().atMost(FIVE_SECONDS)
                .untilAsserted(() ->
                        given(requestSpecification)
                                .auth().oauth2(getAccessToken(user))
                                .get(SUBJECT_PUBLIC_EMERGENCY)
                                .then()
                                .statusCode(200)
                                .body("emergency", empty())
                );
    }

    public void testChainStartsOnaLine(){
        await().atMost(5, TimeUnit.SECONDS)
                .untilAsserted(() ->
                        given(publicEmergencyRequest()).get()
                                .then()
                                .statusCode(200)
                                .body("emergency", empty())
                );
    }

    ReactiveRequestRateLimiter illegalAttemptsRateLimiter(RedisRateLimiterFactory rateLimiterFactory) {
        return rateLimiterFactory.getInstanceReactive(Set.of(
                RequestLimitRule.of(Duration.ofMinutes(5), 10)
        ));
    }

    public void testCase(){
        withBody(objectMapper.writeValueAsBytes(Map.of(
                "link", "http://" + client.remoteAddress().getHostName() + ":" + client.remoteAddress().getPort() + "/external/redirect/" + uuid + AWS_LINK_PARAMS,
                "encryption",
                Map.of(
                        "contentType", "application/json",
                        "compression", "gzip"
                )
        ))).withStatusCode(302);
    }

    void shouldNotPublishWrongEvents() {
        assertThatThrownBy(() -> userEventLogService.publish(user.toSubjectId(), new BadEvent())
                .block(Duration.ofSeconds(10)))
                .isInstanceOf(StatusRuntimeException.class)
                .hasMessageContaining("No schema for 'bad/event'");
    }
}

