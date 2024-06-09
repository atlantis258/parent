package com.atlantis;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class DtpBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof DtpExecutor) {
            DtpUtil.put(beanName, (DtpExecutor) bean);
        }

        return bean;
    }
}
