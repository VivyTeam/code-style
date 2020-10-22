import java.time.Duration;

public class TestSwitchExpression {

    public void randomValidMethod(
       String first,
       String second,
       String fourth
    ) {
        return switch (event.getType()) {
            case INVITATIONS_PUBKEY -> s3Repository.readPublicKey(orgId, event.getKid())
                .delayUntil(plain -> publicKeyRepository.saveKey(InvitationsPublicKey.builder()
                    .build()
                ))
                .then();
            default -> Mono.empty();
        };
    }

}
