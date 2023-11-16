package me.markgerald.validation;

import jakarta.enterprise.context.ApplicationScoped;
import me.markgerald.model.PaymentProvider;

@ApplicationScoped
public class PaymentProviderValidation {
    public PaymentProvider parsePaymentProvider(String providerHeaderValue) {
        try {
            return PaymentProvider.valueOf(providerHeaderValue.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
