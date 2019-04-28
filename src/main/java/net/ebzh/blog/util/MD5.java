package net.ebzh.blog.util;/*
 *
 * @auther Liufm
 * @date 2018/10/13 21:35
 *
 */

import java.security.MessageDigest;

public class MD5 {

    /***
     * MD5加码 生成32位md5码
     */
    public static String stringMD5(String inStr){
        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++){
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }

    /**
     * 加密解密算法 执行一次加密，两次解密
     */
    public static String convertMD5(String inStr){
         char[] a = inStr.toCharArray();
         for (int j = 0; j < a.length; j++){
            a[j] = (char) (a[j] ^ 't');
         }
         String  filter = new String(a);
         return filter;
    }


    public static String convertMD52(String inStr){
        String filter = convertMD5(inStr);
        return convertMD5(filter);
    }

//    // 测试主函数
//    public static void main(String args[]) {
//        String s = new String("123456");
//        System.out.println("原始：" + s);
//        System.out.println("MD5后：" + stringMD5(s));
//        System.out.println("加密的：" + convertMD5(s));
//        System.out.println("解密的：" + convertMD52(s));
//    }
}
