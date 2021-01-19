package com.work.library.util;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * @author Administrator
 * @Description 处理token的工具
 * @Date 2021/1/18 11:40
 */
@Component
public class CryptoUtil {
    /**
     * 加密 token - MD5
     */
    private String encryptionMD5(String key) {
        String value = "";


        return value;
    }

    /**
     * 加密 - Base64
     */
    private String encryptionBase64(String key) {
        return null;
    }
    /**
     * 加密encode - 算法 - base64
     */
    private static String encodeBase64(byte[] bytes) {
        return new BASE64Encoder().encode(bytes);
    }

    /**
     * 加密 - 算法 - MD5
     */
    private static String encodeMd5(byte[] bytes) {
        return MD5Encoder.encode(bytes);
    }

    /**
     * 自定义 - 加密算法
     */
    private static String encodeMy(byte[] bytes) {

        return null;
    }

    /**
     * 解密 - Md5
     */
    private static String decodeMd5(String md5) {
        return null;
    }

    /**
     * 解密 - Md5
     */
    private static byte[] decodeBase64(String base64) throws IOException {
        return new BASE64Decoder().decodeBuffer(base64);
    }

    /**
     * 雪花算法
     */
    /**
     * Claims 加密/解密
     */
    /**
     * 哈希桶  (单向)
     */
    /**
     * 决策树 / 责任链
     */
}
