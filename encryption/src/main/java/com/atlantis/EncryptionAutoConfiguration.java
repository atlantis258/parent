package com.atlantis;

import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncryptionAutoConfiguration {
    @Bean(name = "encryptablePropertyResolver")
    public EncryptablePropertyResolver encryptablePropertyResolver() {
        return new EncryptionResolver();
    }

    @Bean(name = "encryptionService")
    public EncryptionService encryptionService() {
        return new EncryptionService();
    }
}
