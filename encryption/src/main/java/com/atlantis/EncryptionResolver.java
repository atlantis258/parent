package com.atlantis;

import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class EncryptionResolver implements EncryptablePropertyResolver {

    /** 默认key */
    public final static String KEY = "ScAKC0XhadTHT3Al0QIDAQAB";

    @Autowired
    private EncryptionService encryptionService;

    @Override
    public String resolvePropertyValue(String value) {
        if (StringUtils.isEmpty(value)) {
            return value;
        }

        // 值以DES@开头的均为DES加密,需要解密
        if (value.startsWith("DES@")) {
            return resolvePropertyValue(value.substring(4));
        }

        // 不需要解密的值直接返回
        return value;
    }

    private String resolveDESValue(String value) {
        // 自定义DES密文解密
        return encryptionService.decrypt(value, KEY);
    }
}
