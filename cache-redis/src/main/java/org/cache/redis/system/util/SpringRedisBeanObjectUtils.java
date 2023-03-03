package org.cache.redis.system.util;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringRedisBeanObjectUtils implements ApplicationContextAware {

    public static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <R> R getBean(Class<R> classType){
        return applicationContext.getBean(classType);
    }

    public static Object getBean(String beanName){
        return applicationContext.getBean(beanName);
    }

}
