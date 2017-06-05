package com.cesgroup.core.utils;

/**
 * 
 * 生产环境下, 需要对配置文件中的敏感信息进行加密
 * <p>描述:对配置文件中的敏感信息进行加密</p>
 */
public class EncryptSpringPropertyConfigurer extends SpringPropertyConfigurer {


	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		
		if (	propertyName.indexOf("jdbc.url") > -1
				|| propertyName.indexOf("jdbc.username") > -1
				|| propertyName.indexOf("jdbc.password") > -1) {
			propertyValue = DESUtil.decrypt(propertyValue);
		}
		return super.convertProperty(propertyName, propertyValue);
	}
	
}
