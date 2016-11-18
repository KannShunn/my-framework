package com.cesgroup.core.utils;

import java.util.List;

/**
 * 数组工具类
 * 
 * <p>描述:</p>
 * <p>Company:上海中信信息发展股份有限公司</p>
 * @author Niklaus(管俊 GuanJun<a href="mailto:guan.jun@cesgroup.com.cn">guan.jun@cesgroup.com.cn</a>)
 * @date 2015年6月26日 上午1:05:28
 * @version 1.0.2015.0601
 */
public class ArrayUtil {
	
	/**
	 * 将数组转为字符串, 数组的元素用逗号分隔
	 * <p>Company:上海中信信息发展股份有限公司</p>
	 * @author Niklaus(管俊 GuanJun<a href="mailto:guan.jun@cesgroup.com.cn">guan.jun@cesgroup.com.cn</a>)
	 * @comments:<p> </p>
	 * @date 2015年6月26日 上午1:05:09
	 * @param a
	 * @return
	 */
    public static String toString(Object[] a) {
        if (a == null)
            return "null";

        int iMax = a.length - 1;
        if (iMax == -1)
            return "";

        StringBuilder b = new StringBuilder();
        for (int i = 0; ; i++) {
            b.append(String.valueOf(a[i]));
            if (i == iMax)
                return b.toString();
            b.append(",");
        }
    }
    
    public static String toString(List<String> a) {
        if (a == null)
            return "null";

        int iMax = a.size() - 1;
        if (iMax == -1)
            return "";

        StringBuilder b = new StringBuilder();
        for (int i = 0; ; i++) {
            b.append(String.valueOf(a.get(i)));
            if (i == iMax)
                return b.toString();
            b.append(",");
        }
    }
}
