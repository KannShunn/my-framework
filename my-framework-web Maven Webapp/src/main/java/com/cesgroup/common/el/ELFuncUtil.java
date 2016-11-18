package com.cesgroup.common.el;

import com.cesgroup.core.utils.StrUtil;

/**
 * 
 * EL表达式自定义方法类.
 * <p>描述:EL表达式自定义方法类</p>
 * @version 1.0.2015.0303
 */
public class ELFuncUtil {
	//private static final Logger logger = LoggerFactory.getLogger(ELFuncUtil.class);

	
	/**
	 * 处理名称字符过长的方法
	 * @return
	 */
	public static String textCut(String beforeStr,Integer size, String replaceStr){
		String newStr = StrUtil.textCut(beforeStr, size, replaceStr);
		return newStr;
	}
}
