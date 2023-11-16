package me.markgerald.storage;

import java.util.HashMap;
import java.util.Map;
public class TokenStorage {
    private static final Map<String, String> tokens = new HashMap<>();

    public static void saveToken(String customerId, String token) {
        tokens.put(customerId, token);
    }

    public static String getToken(String customerId) {
        return tokens.get(customerId);
    }
}
