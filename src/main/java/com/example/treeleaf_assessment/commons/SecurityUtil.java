package com.example.treeleaf_assessment.commons;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

public class SecurityUtil {

    private SecurityUtil() {

    }

    public static void main(String[] args) {

    }

    public static String encode(String plainText) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(plainText.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert messageDigest != null;
        byte[] digest = messageDigest.digest();
        return base64Encoder(digest);
    }

    public static String generateUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    private static String base64Encoder(byte[] var) {
        return Base64.getEncoder().encodeToString(var);
    }
}
