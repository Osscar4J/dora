package com.zhao.dorambg.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * spring工具类
 * @Author zhaolianqi
 */
@Component
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext =null;

    /**
     * 获取applicationContext
     */
    public static ApplicationContext getApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicationContext属性未注入, 请在SpringBoot启动类中注册SpringContextHolder.");
        } else {
            return applicationContext;
        }
    }

    /**
     * 通过name获取 Bean
     * @param name 类名称
     * @return 实例对象
     */
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean
     */
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 获取指定类型的bean实例列表
     * @return bean实例map，格式：{ key: bean名称, value: bean实例 }
     */
    public static <T> Map<String, T> getBeans(Class<T> clazz){
        return getApplicationContext().getBeansOfType(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     */
    public static <T> T getBean(String name, Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }


    @Override
    public void destroy() {
        applicationContext = null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }
}
