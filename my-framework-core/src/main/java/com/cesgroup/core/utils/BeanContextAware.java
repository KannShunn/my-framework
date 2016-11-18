package com.cesgroup.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring Bean管理辅助类.
 * <p>描述:根据名称或类型获取Bean</p>
 */
@Component
public class BeanContextAware implements ApplicationContextAware {
    /** Spring上下文环境. */
    private ApplicationContext ctx;

    /**
     * 从Spring中获取Bean.
     */
    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> beanClass) {
        return ctx.getBean(beanClass);
    /*    String[] beanNames = ctx.getBeanNamesForType(beanClass);

        if (beanNames != null && beanNames.length > 0) {
            String className = beanClass.getSimpleName();

            for (String beanName : beanNames) {
                if (className.equalsIgnoreCase(beanName)) {
                    return (T)getBean(beanName);
                }
            }

            return (T)getBean(beanNames[beanNames.length-1]);
        }

        return null;*/
    }

    /**
     * 从Spring中获取Bean.
     */
    public Object getBean(String beanName) {
        return ctx.getBean(beanName);
    }

    public void setApplicationContext(ApplicationContext ctx)
            throws BeansException {
        this.ctx = ctx;
    }
}
