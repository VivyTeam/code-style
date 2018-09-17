package io.vivy.checkstyle.custom;

public class TestSingleLambdaArgument {

    public void testMethod() {

        batch -> batch.transform(liiklusRecordProcessor)
           .sample(ackInterval)
           .onBackpressureLatest()
           .delayUntil(
              reply -> Mono.defer(
                 () -> stub.ack(
                    Mono.just(
                       AckRequest.newBuilder()
                          .setAssignment(assignment)
                          .setOffset(reply.getOffset())
                          .build()
                    )
                 )
              )
                 .retryWhen(
                    it -> it.delayElements(java.time.Duration.ofMillis(200))
                 )
           )

    }

}
