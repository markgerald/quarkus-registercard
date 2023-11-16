package me.markgerald.generators;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VisaTokenGenerator implements TokenGenerator {
    @Override
    public String generateToken(String cardNumber, String cvv) {
        int rotations = Integer.parseInt(cvv);

        int[] cardArray = cardNumber.chars()
                .map(Character::getNumericValue)
                .toArray();

        for (int i = 0; i < rotations; i++) {
            int lastElement = cardArray[cardArray.length - 1];
            System.arraycopy(cardArray, 0, cardArray, 1, cardArray.length - 1);
            cardArray[0] = lastElement;
        }

        StringBuilder token = new StringBuilder();
        for (int digit : cardArray) {
            token.append(digit);
        }

        return token.toString();
    }
}
