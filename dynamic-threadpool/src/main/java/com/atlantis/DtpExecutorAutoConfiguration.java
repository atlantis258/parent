package com.atlantis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnProperty(prefix = "dtp", name = "enable", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(DtpProperties.class)
@Import({DtpBeanPostProcessor.class,  DtpImportBeanDefinitionRegistrar.class})
public class DtpExecutorAutoConfiguration {

    @Bean
    public NacosListener nacosListener(){
        return new NacosListener();
    }

    @Bean
    public DtpMonitor dtpMonitor(){
        return new DtpMonitor();
    }


}
