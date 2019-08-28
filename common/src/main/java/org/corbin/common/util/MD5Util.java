package org.corbin.common.util;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 采用MD5加密解密
 */
@Slf4j
public class MD5Util {

  /***
   * MD5加码 生成32位md5码
   */
  public static String string2MD5(String inStr) {
    MessageDigest md5 = null;
    try {
      md5 = MessageDigest.getInstance("MD5");
    } catch (Exception e) {
      log.error(e.toString());
      return "";
    }
    char[] charArray = inStr.toCharArray();
    byte[] byteArray = new byte[charArray.length];

    for (int i = 0; i < charArray.length; i++) {
      byteArray[i] = (byte) charArray[i];
    }
    byte[] md5Bytes = md5.digest(byteArray);
    StringBuffer hexValue = new StringBuffer();
    for (int i = 0; i < md5Bytes.length; i++) {
      int val = ((int) md5Bytes[i]) & 0xff;
      if (val < 16) {
        hexValue.append("0");
      }
      hexValue.append(Integer.toHexString(val));
    }
    return hexValue.toString();

  }

  public static String getSHA256Str(String str) {
    MessageDigest messageDigest = null;
    try {
      messageDigest = MessageDigest.getInstance("SHA-256");
      messageDigest.reset();

      messageDigest.update(str.getBytes("UTF-8"));
    } catch (NoSuchAlgorithmException e) {
      System.out.println("NoSuchAlgorithmException caught!");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    byte[] byteArray = messageDigest.digest();
    StringBuffer sha256StrBuff = new StringBuffer();
    for (int i = 0; i < byteArray.length; i++) {
      if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
        sha256StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
      } else {
        sha256StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
      }
    }

    return sha256StrBuff.toString();
  }

  /**
   * 加密解密算法 执行一次加密，两次解密
   */
  public static String convertMD5(String inStr) {

    char[] a = inStr.toCharArray();
    for (int i = 0; i < a.length; i++) {
      a[i] = (char) (a[i] ^ 't');
    }
    String s = new String(a);
    return s;

  }

}
