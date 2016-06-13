package com.eastpro.util;

import org.springframework.security.authentication.encoding.PasswordEncoder;

public class EabPasswordEncoder implements PasswordEncoder {


    @Override
    public String encodePassword(String s, Object salt) {
        if(salt!=null && !"".equals(salt))
            return SimpleEncrypt.encrypt(salt.toString(), s, false);
        else
            return "";
    }

    @Override
    public boolean isPasswordValid(String s, String s2, Object salt) {
        String pass = encodePassword(s2, salt.toString());
        return s.equalsIgnoreCase(pass);
    }
}
