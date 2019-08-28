package com.yami.springboot_security.config;

import org.springframework.security.crypto.password.PasswordEncoder;

public class MyPasswordEncoder implements PasswordEncoder {
    // 对密码进行加密
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }
    // 对密码进行判断匹配
    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
