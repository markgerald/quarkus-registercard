package me.markgerald.generators;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import me.markgerald.model.PaymentProvider;

@ApplicationScoped
public class TokenGeneratorProvider {

    @Inject
    private MastercardTokenGenerator mastercardTokenGenerator;

    @Inject
    private VisaTokenGenerator visaTokenGenerator;

    public TokenGenerator getTokenGenerator(PaymentProvider paymentProvider) {
        switch (paymentProvider) {
            case MASTERCARD:
                return mastercardTokenGenerator;
            case VISA:
                return visaTokenGenerator;
            default:
                throw new IllegalArgumentException("Invalid Payment Provider");
        }
    }
}
