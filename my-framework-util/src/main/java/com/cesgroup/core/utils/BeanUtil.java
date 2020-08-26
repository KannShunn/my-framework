package com.cesgroup.core.utils;

import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

/**
 * 类描述.
 * <p>
 * 描述:一段简短的描述
 * </p>
 * <p>
 * Company:lion_guan
 * </p>
 *
 * @author 管俊(lion_guan@foxmail.com)
 * @version 1.0
 * @date 2017/6/8 17:08
 */
public class BeanUtil {

    /**
     * 将map上的key对应的value值，赋值给t对象上
     *
     * @param clazz
     * @param map
     * @return
     */
    public static T setBeanByMap(Class<T> clazz, Map map) {
        if (map == null || clazz == null) return null;
        Method[] methods = clazz.getMethods();
        Iterator it = map.keySet().iterator();
        T obj = null;
        try {
            obj = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        while (it.hasNext()) {
            String key = it.next().toString();
            String firstLetter = key.substring(0, 1).toUpperCase();
            String setMethodName = "set" + firstLetter + key.substring(1);

            for (Method method : methods) {
                if (method.getName().equals(setMethodName)) {
                    try {
                        Object value = map.get(key);
                        if (value != null && "null".equals(value.toString())) {
                            value = null;
                        }
                        method.invoke(obj, new Object[]{value});
                    } catch (Exception e) {
                        continue;
                    }
                }
            }

        }
        return obj;
    }
}
