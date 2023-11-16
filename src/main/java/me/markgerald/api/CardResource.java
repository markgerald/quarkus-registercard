package me.markgerald.api;

import jakarta.inject.Inject;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import me.markgerald.generators.TokenGenerator;
import me.markgerald.generators.TokenGeneratorProvider;
import me.markgerald.model.PaymentProvider;
import me.markgerald.request.CardRequest;
import me.markgerald.response.CardResponse;
import me.markgerald.storage.TokenStorage;
import me.markgerald.validation.PaymentProviderValidation;

@Path("/")
public class CardResource {
    @Inject
    TokenGeneratorProvider tokenGeneratorProvider;

    @Inject
    PaymentProviderValidation paymentProviderValidation;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerCard(
            CardRequest cardRequest,
            @HeaderParam("customerId") @NotBlank @NotNull String customerId,
            @HeaderParam("provider") @NotBlank @NotNull String providerHeaderValue
    ) {
        String cardNumber = cardRequest.getCardNumber();
        String cvv = cardRequest.getCvv();
        PaymentProvider selectedProvider = paymentProviderValidation
                .parsePaymentProvider(providerHeaderValue);

        if (selectedProvider == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid provider specified")
                    .build();
        }

        TokenGenerator tokenGenerator = tokenGeneratorProvider
                .getTokenGenerator(selectedProvider);
        String token = tokenGenerator.generateToken(cardNumber, cvv);
        TokenStorage.saveToken(customerId, token);

        return Response.ok(new CardResponse(token)).build();
    }


}
