package me.markgerald.generators;

import io.quarkus.test.junit.QuarkusTest;
import me.markgerald.model.PaymentProvider;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
public class TokenGeneratorProviderTest {

    @InjectMocks
    TokenGeneratorProvider tokenGeneratorProvider;

    @Mock
    MastercardTokenGenerator mastercardTokenGenerator;

    @Mock
    VisaTokenGenerator visaTokenGenerator;

    @Test
    public void testGetTokenGeneratorForMastercard() {
        when(mastercardTokenGenerator.generateToken(anyString(), anyString()))
                .thenReturn("mastercardToken");
        TokenGenerator generator = tokenGeneratorProvider.getTokenGenerator(PaymentProvider.MASTERCARD);
        assertTrue(generator instanceof MastercardTokenGenerator);

        assertEquals("mastercardToken", generator.generateToken("1234567890123456", "123"));
    }

    @Test
    public void testGetTokenGeneratorForVisa() {
        when(visaTokenGenerator.generateToken(anyString(), anyString()))
                .thenReturn("visaToken");

        TokenGenerator generator = tokenGeneratorProvider.getTokenGenerator(PaymentProvider.VISA);

        assertTrue(generator instanceof VisaTokenGenerator);
        assertEquals("visaToken", generator.generateToken("1234567890123456", "123"));
    }

    @Test
    public void testGetTokenGeneratorWithInvalidProvider() {
        assertThrows(IllegalArgumentException.class, () -> {
            tokenGeneratorProvider.getTokenGenerator(null);
        });
    }
}
