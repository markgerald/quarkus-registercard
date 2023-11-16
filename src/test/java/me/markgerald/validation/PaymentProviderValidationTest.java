package me.markgerald.validation;

import me.markgerald.model.PaymentProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentProviderValidationTest {

    private PaymentProviderValidation paymentProviderValidation;

    @BeforeEach
    public void setup() {
        paymentProviderValidation = new PaymentProviderValidation();
    }

    @Test
    public void testParseValidMastercardProvider() {
        PaymentProvider provider = paymentProviderValidation.parsePaymentProvider("MASTERCARD");
        assertEquals(PaymentProvider.MASTERCARD, provider);
    }

    @Test
    public void testParseValidVisaProvider() {
        PaymentProvider provider = paymentProviderValidation.parsePaymentProvider("VISA");
        assertEquals(PaymentProvider.VISA, provider);
    }

    @Test
    public void testParseInvalidProvider() {
        PaymentProvider provider = paymentProviderValidation.parsePaymentProvider("INVALID_PROVIDER");
        assertNull(provider);
    }

    @Test
    public void testParseCaseInsensitiveProvider() {
        PaymentProvider provider = paymentProviderValidation.parsePaymentProvider("mastercard");
        assertEquals(PaymentProvider.MASTERCARD, provider);
    }

    @Test
    public void testParseNullProvider() {
        PaymentProvider provider = paymentProviderValidation.parsePaymentProvider(null);
        assertNull(provider);
    }
}
