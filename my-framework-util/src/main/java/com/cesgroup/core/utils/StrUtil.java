package com.cesgroup.core.utils;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class StrUtil{
	
	public static boolean isEmpty(String str){
		return StringUtils.isEmpty(str);
	}
	
	public static boolean isNotEmpty(String str){
		return StringUtils.isNotEmpty(str);
	}
	
	public static String null2Emtpy(Object obj){
		return obj == null ? "" : obj.toString();
	}
	
	/**
	 * 此方法用于将键值字符串转化为存放键值的Set集合。
	 * 
	 * @param idStr
	 * @return
	 */
	public static Set<Long> strToKey(String idStr){
		
		if(idStr==null||idStr.equals("")){
			
			return null;
			
		}else{
			
			Set<Long> idSet=new HashSet<Long>();
			String[] idArray=idStr.split(";");
			
			for(int i=0;i<idArray.length;i++){
				
				idSet.add(Long.valueOf(idArray[i]));
				
			}
			
			return idSet;
			
		}
		
	}
	
	/**
	 * 此方法用于将键—值字符串转化为Map数据结构。
	 * 
	 * @param keyValueStr
	 * @return
	 */
	public static Map<Long,Double> strToKeyValueMap(String keyValueStr){
		
		if(keyValueStr==null || keyValueStr.equals("")){
			
			return null;
			
		}else{
			
			Map<Long,Double> keyValue=new HashMap<Long,Double>();
			String[] keyValueArray=keyValueStr.split(";");
			
			for(int i=0;i<keyValueArray.length;i++){
				
				String tempStr=keyValueArray[i];
				String[] str=tempStr.split(",");
				keyValue.put(Long.valueOf(str[0]), Double.valueOf(str[1]));
	
			}
			
			return keyValue;
		}
	}
	
	public static StringBuilder LongArrayToString(Long[] id){
		StringBuilder sb = new StringBuilder();
		for (Long l : id) {
			if (l != null) {
				sb.append(l).append(",");
			}
		}
		return sb;
	}
	
	/**
	 * 根据分割符将字符串进行分割.
	 * 例：<pre>
	 * StringUtils.split(null,"${","}")  = null
	 * StringUtils.split("","${","}")  = null
	 * StringUtils.split("你好","${","}")  = String[]{"你好","",""}
	 * StringUtils.split("你好${吗？","${","}")  = String[]{"你好${吗？","",""}
	 * StringUtils.split("你好}吗？","${","}")  = String[]{"你好}吗？","",""}
	 * StringUtils.split("你好${userName}:你所在组织为${orgName}.","${","}")  = String[]{"你好","userName",":你所在组织为${orgName}."}
	 * </pre>
	 * @author Reamy(杨木江 yangmujiang@sohu.com)
	 * @date 2013-06-21  14:45:57
	 */
	public static String[] split(String str, String separatorChars, String separatorEndChars) {
		if (str == null) return null;
		
		int pos = str.indexOf(separatorChars);
		int ePos = str.indexOf(separatorEndChars);
		
		if (pos == -1 || ePos == -1) return new String[]{str,"",""};
		return new String[]{str.substring(0,pos),str.substring(pos+separatorChars.length(),ePos),str.substring(ePos+separatorEndChars.length())};
	}
	
	/**
	 * 剪切文本。如果进行了剪切，则在文本后加上"..."
	 * 
	 * @param s
	 *            剪切对象。
	 * @param len
	 *            编码小于256的作为一个字符，大于256的作为两个字符。
	 * @return
	 */
	public static String textCut(String s, int len, String append) {
		if (s == null) {
			return null;
		}
		int slen = s.length();
		if (slen <= len) {
			return s;
		}
		// 最大计数（如果全是英文）
		int maxCount = len * 2;
		int count = 0;
		int i = 0;
		for (; count < maxCount && i < slen; i++) {
			if (s.codePointAt(i) < 256) {
				count++;
			} else {
				count += 2;
			}
		}
		if (i < slen) {
			if (count > maxCount) {
				i--;
			}
			if (!StringUtils.isBlank(append)) {
				if (s.codePointAt(i - 1) < 256) {
					i -= 2;
				} else {
					i--;
				}
				return s.substring(0, i) + append;
			} else {
				return s.substring(0, i);
			}
		} else {
			return s;
		}
	}
}