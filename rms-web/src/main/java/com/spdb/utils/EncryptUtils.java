package com.spdb.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

/**
 * 前后端数据传输加密工具类
 * @author monkey
 *
 */
public class EncryptUtils {

    //参数分别代表 算法名称/加密模式/数据填充方式
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    /**
     * 加密
     * @param plaintext 明文
     * @param encryptKey 密钥
     * @return
     * @throws Exception
     */
    public static String encrypt(String plaintext, String encryptKey) throws Exception {
        // sha-256
        MessageDigest messageDigest = MessageDigest.getInstance("sha-256");
        byte[] buffer = encryptKey.getBytes(StandardCharsets.UTF_8);
        buffer = messageDigest.digest(buffer);
        // AES, key(256 bit)
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(buffer, "AES"));
        byte[] b = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        // 采用base64算法进行转码,避免出现中文乱码
        return Base64.encodeBase64String(b);

    }

    /**
     * 解密
     * @param encryptStr 解密的字符串
     * @param decryptKey 解密的key值
     * @return
     * @throws Exception
     */
    public static String decrypt(String encryptStr, String decryptKey) throws Exception {
        // sha-256
        MessageDigest messageDigest = MessageDigest.getInstance("sha-256");
        byte[] buffer = decryptKey.getBytes(StandardCharsets.UTF_8);
        buffer = messageDigest.digest(buffer);
        // AES, key(256 bit)
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(buffer, "AES"));
        // 采用base64算法进行转码,避免出现中文乱码
        byte[] encryptBytes = Base64.decodeBase64(encryptStr);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }


    public static void main(String[] args) throws Exception {
        String password = "admin";
        String plaint = "admin";
        String cipher = encrypt(plaint, password);
        System.out.println(cipher);
        String pt = decrypt(cipher, password);
        System.out.println(pt);
    }
}