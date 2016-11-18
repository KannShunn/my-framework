package com.cesgroup.core.utils;

/**
 * 驼峰名工具类
 * 
 * <p>描述:可快速互相转换下划线名称和驼峰名称</p>
 * <p>Company:上海中信信息发展股份有限公司</p>
 * @author Niklaus(管俊 GuanJun<a href="mailto:guan.jun@cesgroup.com.cn">guan.jun@cesgroup.com.cn</a>)
 * @date 2015年6月23日 下午10:09:00
 * @version 1.0.2015.0601
 */
public class CamelCaseUtils {
	 
    private static final char SEPARATOR = '_';
 
    /**
     * 驼峰转为下划线
     * 
     * <p>Company:上海中信信息发展股份有限公司</p>
     * @author Niklaus(管俊 GuanJun<a href="mailto:guan.jun@cesgroup.com.cn">guan.jun@cesgroup.com.cn</a>)
     * @comments:<p> </p>
     * @date 2015年6月23日 下午10:14:43
     * @param s
     * @return
     */
    public static String toUnderlineName(String s) {
        if (s == null) {
            return null;
        }
 
        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
 
            boolean nextUpperCase = true;
 
            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }
 
            if ((i >= 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    if (i > 0) sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }
 
            sb.append(Character.toLowerCase(c));
        }
 
        return sb.toString().trim();
    }
 
    /**
     * 下划线转驼峰, 首字母小写
     * 
     * <p>Company:上海中信信息发展股份有限公司</p>
     * @author Niklaus(管俊 GuanJun<a href="mailto:guan.jun@cesgroup.com.cn">guan.jun@cesgroup.com.cn</a>)
     * @comments:<p> </p>
     * @date 2015年6月23日 下午10:14:57
     * @param s
     * @return
     */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }
 
        s = s.toLowerCase();
 
        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
 
            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }
 
        return sb.toString().trim();
    }
 
    /**
     * 下划线转驼峰, 首字母大写
     * 
     * <p>Company:上海中信信息发展股份有限公司</p>
     * @author Niklaus(管俊 GuanJun<a href="mailto:guan.jun@cesgroup.com.cn">guan.jun@cesgroup.com.cn</a>)
     * @comments:<p> </p>
     * @date 2015年6月23日 下午10:14:57
     * @param s
     * @return
     */
    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = toCamelCase(s);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
 
    public static void main(String[] args) {
        System.out.println(CamelCaseUtils.toUnderlineName("ISOCertifiedStaff"));
        System.out.println(CamelCaseUtils.toUnderlineName("CertifiedStaff"));
        System.out.println(CamelCaseUtils.toUnderlineName("UserID"));
        System.out.println(CamelCaseUtils.toCamelCase(" iso_certified_staff "));
        System.out.println(CamelCaseUtils.toCamelCase(" certified_staff "));
        System.out.println(CamelCaseUtils.toCamelCase(" user_id "));
        System.out.println(CamelCaseUtils.toCapitalizeCamelCase(" user_id "));
    }
}