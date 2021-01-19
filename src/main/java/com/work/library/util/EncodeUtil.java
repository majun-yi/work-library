package com.work.library.util;

import cn.hutool.core.util.HexUtil;
import lombok.SneakyThrows;
import org.apache.tomcat.util.security.MD5Encoder;
import sun.misc.BASE64Encoder;
import sun.security.provider.SHA;
import sun.security.rsa.RSASignature;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @author Administrator
 * @Description 加密工具
 * @Date 2021/1/18 14:13
 * 数据加密 的基本过程，就是对原来为 明文 的文件或数据按 某种算法 进行处理，使其成为 不可读 的一段代码，通常称为 “密文”。
 * 通过这样的途径，来达到 保护数据 不被 非法人窃取、阅读的目的。主要分为对称加密和非对称加密,具体如下:
 * 1.对称加密和非对称加密:
 * <p>
 * 加密算法分 对称加密 和 非对称加密，其中对称加密算法的加密与解密 密钥相同，非对称加密算法的加密密钥与解密 密钥不同，此外，还有一类 不需要密钥 的 散列算法。
 * <p>
 * 常见的 对称加密 算法主要有 DES、3DES、AES 等，常见的 非对称算法 主要有 RSA、DSA 等，散列算法 主要有 SHA-1、MD5 等。
 * <p>
 * 1.1 对称加密
 * <p>
 * 对称加密算法 是应用较早的加密算法，又称为 共享密钥加密算法。在 对称加密算法 中，使用的密钥只有一个，发送 和 接收 双方都使用这个密钥对数据进行 加密 和 解密。
 * 这就要求加密和解密方事先都必须知道加密的密钥。
 * 数据加密过程：在对称加密算法中，数据发送方 将 明文 (原始数据) 和 加密密钥 一起经过特殊 加密处理，生成复杂的 加密密文 进行发送。
 * 数据解密过程：数据接收方 收到密文后，若想读取原数据，则需要使用 加密使用的密钥 及相同算法的 逆算法 对加密的密文进行解密，才能使其恢复成 可读明文。
 * <p>
 * 1.2 非对称加密
 * <p>
 * 非对称加密算法，又称为 公开密钥加密算法。它需要两个密钥，一个称为 公开密钥 (public key)，即 公钥，另一个称为 私有密钥 (public key)，即 私钥。
 * 因为 加密 和 解密 使用的是两个不同的密钥，所以这种算法称为 非对称加密算法。
 * 如果使用 公钥 对数据 进行加密，只有用对应的 私钥 才能 进行解密。
 * 如果使用 私钥 对数据 进行加密，只有用对应的 公钥 才能 进行解密。
 * 例子：甲方生成 一对密钥 并将其中的一把作为 公钥 向其它人公开，得到该公钥的 乙方 使用该密钥对机密信息 进行加密 后再发送给甲方，
 * 甲方再使用自己保存的另一把 专用密钥 (私钥)，对 加密 后的信息 进行解密。
 */
public class EncodeUtil {

    /**
     * 加密 - 算法 - base64
     * Base64是网络上最常见的用于传输8Bit字节代码的编码方式之一，大家可以查看RFC2045～RFC2049，上面有MIME的详细规范。
     * Base64编码可用于在HTTP环境下传递较长的标识信息。例如，在Java Persistence系统Hibernate中，
     * 就采用了Base64来将一个较长的唯一标识符（一般为128-bit的UUID）编码为一个字符串，用作HTTP表单和HTTP GET URL中的参数。
     * 在其他应用程序中，也常常需要把二进制数据编码为适合放在URL（包括隐藏表单域）中的形式。
     * 此时，采用Base64编码具有不可读性，即所编码的数据不会被人用肉眼所直接看到。
     */
    public static String encodeBase64(byte[] bytes) {
        return new BASE64Encoder().encode(bytes);
    }

    /**
     * 加密 - 算法 - MD5
     * MD5即Message-Digest Algorithm 5（信息-摘要算法5），用于确保信息传输完整一致。是计算机广泛使用的杂凑算法之一（又译摘要算法、哈希算法），
     * 主流编程语言普遍已有MD5实现。将数据（如汉字）运算为另一固定长度值，是杂凑算法的基础原理，MD5的前身有MD2、MD3和MD4。
     * 广泛用于加密和解密技术，常用于文件校验。校验？不管文件多大，经过MD5后都能生成唯一的MD5值。好比现在的ISO校验，都是MD5校验。
     */
    public static String encodeMd5(byte[] bytes) {
        return MD5Encoder.encode(bytes);
    }

    @SneakyThrows
    public static String encodeMd5(String str) {
        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        return new BASE64Encoder().encode(md5.digest(str.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * MD5 加盐算法 - 可自由控制盐 (固定 或 随机产生,随机产生请将盐值存入)
     *
     * @param str  需要加密的字符串
     * @param salt 盐值
     * @return MD5 + 盐 的加密串
     */
    @SneakyThrows
    public static String encodeMd5(String str, String salt) {
        //将 传入的盐值 反转
        String reverseSalt = new StringBuffer(salt).reverse().toString();
        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        
        byte[] bytes = md5.digest(str.getBytes(StandardCharsets.UTF_8));
        //加盐
        return new String(HexUtil.encodeHex(bytes));
    }

    /**
     * 加密算法 - SHA 安全哈希算法
     * Secure Hash Algorithm
     * 安全哈希算法（Secure Hash Algorithm）主要适用于数字签名标准（Digital Signature Standard DSS）里面定义的数字签名算法（Digital Signature Algorithm DSA）。
     * 对于长度小于2^64位的消息，SHA1会产生一个160位的消息摘要。该算法经过加密专家多年来的发展和改进已日益完善，并被广泛使用。该算法的思想是接收一段明文，
     * 然后以一种不可逆的方式将它转换成一段（通常更小）密文，也可以简单的理解为取一串输入码（称为预映射或信息），并把它们转化为长度较短、
     * 位数固定的输出序列即散列值（也称为信息摘要或信息认证代码）的过程。散列函数值可以说是对明文的一种"指纹"或是"摘要"所以对散列值的数字签名就可以视为对此明文的数字签名。
     */
    public static String encodeSha() {

        return null;
    }

    /**
     * 加密算法 - HMAC 酸了消息鉴别码(Hash Message Authentication Code)
     * 散列消息鉴别码，基于密钥的Hash算法的认证协议。消息鉴别码实现鉴别的原理是，用公开函数和密钥产生一个固定长度的值作为认证标识，用这个标识鉴别消息的完整性。
     * 使用一个密钥生成一个固定大小的小数据块，即MAC，并将其加入到消息中，然后传输。接收方利用与发送方共享的密钥进行鉴别认证等。
     */
    public static String encodeHmac() {
        return null;
    }

    /**
     * 自定义 - 加密算法
     * 该加密
     */
    public static String encodeMy(byte[] bytes) {

        return null;
    }

}
