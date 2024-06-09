package com.atlantis;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

public class DtpImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {

        DtpProperties dtpProperties = new DtpProperties();
        Binder binder = Binder.get(environment);
        ResolvableType type = ResolvableType.forClass(DtpProperties.class);
        Bindable<?> target = Bindable.of(type).withExistingValue(dtpProperties);
        binder.bind("dtp", target);


        for (DtpProperties.DtpExecutorProperties entry : dtpProperties.getExecutor()) {

            String poolName = entry.getPoolName();
            AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
            beanDefinition.setBeanClass(DtpExecutor.class);
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(entry.getCorePoolSize());
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(entry.getMaximumPoolSize());

            registry.registerBeanDefinition(poolName, beanDefinition);
        }

    }
}
