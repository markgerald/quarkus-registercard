package me.markgerald.request;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;

public class CardRequest {

    @JsonbProperty("cardNumber")
    private String cardNumber;
    @JsonbProperty("cvv")
    private String cvv;

    @JsonbCreator
    public CardRequest(@JsonbProperty("cardNumber") String cardNumber, @JsonbProperty("cvv") String cvv) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

}
