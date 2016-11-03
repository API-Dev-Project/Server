package com.banking.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by graham on 03/11/16.
 */
public class Encyptor {
    private static MessageDigest messageDigest = null;

    public static String sha256(String message) {
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        messageDigest.update(message.getBytes());
        return new String(messageDigest.digest());
    }
}
