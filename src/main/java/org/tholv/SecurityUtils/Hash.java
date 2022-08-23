package org.tholv.SecurityUtils;

import java.security.NoSuchAlgorithmException;

public class Hash {
    private static final char[] hex = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    public static  String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }
    public static  String MD2(String md2) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD2");
            byte[] array = md.digest(md2.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }


    private   String byteArray2Hex(byte[] bytes) {
        StringBuffer sb = new StringBuffer(bytes.length * 2);
        for(final byte b : bytes) {
            sb.append(hex[(b & 0xF0) >> 4]);
            sb.append(hex[b & 0x0F]);
        }
        return sb.toString();
    }

    public static String getStringFromSHA1(String stringToEncrypt) throws NoSuchAlgorithmException {
        java.security.MessageDigest messageDigest = java.security.MessageDigest.getInstance("SHA-1");
        messageDigest.update(stringToEncrypt.getBytes());
        return new Hash().byteArray2Hex(messageDigest.digest());
    }

    public static  String SHA_224(String SHA_224) throws Exception{
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-224");
            byte[] array = md.digest(SHA_224.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
       throw new Exception("NoSuchAlgorithmException");
    }
    public static String getStringFromSHA256(String stringToEncrypt) throws NoSuchAlgorithmException {
        java.security.MessageDigest messageDigest = java.security.MessageDigest.getInstance("SHA-256");
        messageDigest.update(stringToEncrypt.getBytes());
        return new Hash().byteArray2Hex(messageDigest.digest());
    }
    public static String getStringFromSHA512(String stringToEncrypt) throws NoSuchAlgorithmException {
        java.security.MessageDigest messageDigest = java.security.MessageDigest.getInstance("SHA-512");
        messageDigest.update(stringToEncrypt.getBytes());
        return new Hash().byteArray2Hex(messageDigest.digest());
    }
}
