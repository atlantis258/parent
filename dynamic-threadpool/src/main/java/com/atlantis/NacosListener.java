package com.atlantis;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;
import org.springframework.core.ResolvableType;
import org.springframework.core.io.ByteArrayResource;

import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NacosListener implements Listener, InitializingBean {

    @NacosInjected
    private ConfigService configService;

    @Override
    public void afterPropertiesSet() throws Exception {
        configService.addListener("dtp.yaml", "DEFAULT_GROUP", this);
    }

    @Override
    public Executor getExecutor() {
        return Executors.newFixedThreadPool(1);
    }

    @Override
    public void receiveConfigInfo(String configInfo) {
        YamlPropertiesFactoryBean bean = new YamlPropertiesFactoryBean();
        bean.setResources(new ByteArrayResource(configInfo.getBytes()));
        Properties properties = bean.getObject();
        System.out.println(properties);

        DtpProperties dtpProperties = new DtpProperties();
        ConfigurationPropertySource sources = new MapConfigurationPropertySource(properties);
        Binder binder = new Binder(sources);
        ResolvableType type = ResolvableType.forClass(DtpProperties.class);
        Bindable<?> target = Bindable.of(type).withExistingValue(dtpProperties);
        binder.bind("dtp", target);

        for (DtpProperties.DtpExecutorProperties executorProperties : dtpProperties.getExecutor()) {
            DtpExecutor dtpExecutor = DtpUtil.getDtpExecutor(executorProperties.getPoolName());
            dtpExecutor.setCorePoolSize(executorProperties.getCorePoolSize());
            dtpExecutor.setMaximumPoolSize(executorProperties.getMaximumPoolSize());
        }


    }


}
