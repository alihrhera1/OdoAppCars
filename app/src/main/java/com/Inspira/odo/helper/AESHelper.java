package com.Inspira.odo.helper;


import android.util.Base64;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class AESHelper {

    private static final  String AES ="AES" ;

    public   String encrypt (String password ) throws  Exception{
         SecretKeySpec key = generateKey(password);
         Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherText = cipher.doFinal(password.getBytes("UTF-8"));
        String  encryptValu = Base64.encodeToString(cipherText ,Base64.DEFAULT );
        return  encryptValu ;

     }

    private SecretKeySpec generateKey(String password) throws  Exception {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] byteS = password.getBytes("UTF-8");
        digest.update(byteS,0,byteS.length);
        byte[] key =digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key,"AES");
        return  secretKeySpec;

    }
    public  String decrypt (String outputString ,String password) throws  Exception{
        SecretKeySpec key = generateKey(password);
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.DECRYPT_MODE, key);
         byte[]   dncrypt = Base64.decode(outputString,Base64.DEFAULT);
         byte[] decodingValue = cipher.doFinal(dncrypt);
        String decryptedString = new String(decodingValue , "UTF-8");
        return   decryptedString;


    }


}
