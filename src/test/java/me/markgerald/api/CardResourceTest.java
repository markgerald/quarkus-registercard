package me.markgerald.api;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import me.markgerald.generators.TokenGeneratorProvider;
import me.markgerald.model.PaymentProvider;
import me.markgerald.request.CardRequest;
import me.markgerald.validation.PaymentProviderValidation;
import org.junit.jupiter.api.Test;
import jakarta.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@QuarkusTest
public class CardResourceTest {

    @InjectMock
    TokenGeneratorProvider tokenGeneratorProvider;


    @InjectMock
    PaymentProviderValidation paymentProviderValidation;

    @Test
    public void testRegisterCardSuccess() {
        CardResource resource = new CardResource();
        resource.tokenGeneratorProvider = tokenGeneratorProvider;
        resource.paymentProviderValidation = paymentProviderValidation;
        CardRequest request = new CardRequest("1234567890123456", "123");
        when(paymentProviderValidation.parsePaymentProvider("MASTERCARD"))
                .thenReturn(PaymentProvider.MASTERCARD);
        when(tokenGeneratorProvider.getTokenGenerator(any(PaymentProvider.class)))
                .thenReturn((cardNumber, cvv) -> "1234567890123456");
        Response response = resource.registerCard(request, "customerId", "MASTERCARD");

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testRegisterCardWithInvalidProvider() {
        CardResource resource = new CardResource();
        resource.tokenGeneratorProvider = tokenGeneratorProvider;
        resource.paymentProviderValidation = paymentProviderValidation;
        CardRequest request = new CardRequest("1234567890123456", "123");
        when(paymentProviderValidation
                .parsePaymentProvider("INVALID")).thenReturn(null);
        Response response = resource.registerCard(request, "customerId", "INVALID");

        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void testRegisterCardWithMissingHeaders() {
        CardResource resource = new CardResource();
        resource.tokenGeneratorProvider = tokenGeneratorProvider;
        resource.paymentProviderValidation = paymentProviderValidation;
        CardRequest request = new CardRequest("1234567890123456", "123");
        Response response = resource
                .registerCard(request, null, "MASTERCARD");

        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }
}
