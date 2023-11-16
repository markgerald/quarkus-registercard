package me.markgerald.generators;

public interface TokenGenerator {
    String generateToken(String cardNumber, String cvv);
}
