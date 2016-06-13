package com.eastpro;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 15-6-1.
 */
public class Test {
    public static void md5_SystemWideSaltSource () {
        Md5PasswordEncoder md5 = new Md5PasswordEncoder();
        md5.setEncodeHashAsBase64(false);

        // 使用动态加密盐的只需要在注册用户的时候将第二个参数换成用户名即可
        String pwd = md5.encodePassword("SYS.ADMIN", "AAbb1122");
        System.out.println("MD5 SystemWideSaltSource: " + pwd);
    }
    public static void main(String[] args) throws NoSuchAlgorithmException {

        md5_SystemWideSaltSource(); // 使用MD5再加全局加密盐加密的方式加密
    }
}
