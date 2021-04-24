package io.celsoagra.hashcash;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class Block {

    private static final String SHA256_ALGORITHM = "SHA-256";

    private String data;
    private long timeStamp;

    public Block(String data) {
        this.data = data;
        this.timeStamp = new Date().getTime();
    }

    public String mine(int difficulty) throws NoSuchAlgorithmException {
        String target = new String(new char[difficulty]).replace('\0', '0');
        int nonce = 0;
        String hash = null;

        do {
            hash = hash(String.format("%s.%s.%s", data, timeStamp, ++nonce));
        } while (!hash.substring(0, difficulty).equals(target));

        return hash;
    }

    public static String hash(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(SHA256_ALGORITHM);
        byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexStr = new StringBuilder();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);

            if (hex.length() == 1)
                hexStr.append('0');

            hexStr.append(hex);
        }
        return hexStr.toString();
    }
}
