package me.markgerald.generators;

import jakarta.enterprise.context.ApplicationScoped;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@ApplicationScoped
public class MastercardTokenGenerator implements TokenGenerator {

    @Override
    public String generateToken(String cardNumber, String cvv) {
        try {
            String dataToHash = cardNumber + cvv;
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(dataToHash.getBytes());

            StringBuilder token = new StringBuilder();
            for (byte b : hashBytes) {
                token.append(String.format("%02x", b));
            }

            return token.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating token for Provider A");
        }
    }
}
