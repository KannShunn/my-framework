/**
 * 
 */
package com.cesgroup.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志记录注解
 * 
 * @author niklaus
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface CesLog{
	
	/** 模块名称 */
	String type() default "";
	
	/** 操作动作 */
	String operate() default "";
	
	/** 操作内容 */
	String message() default "";
	
	/** 备注 */
	String note() default "";
	
	/** 是否记录日志，默认为记录日志  */
	boolean isLog() default true;
	
	/** 是否是登录日志,默认false。  */
	boolean isLogin() default false;
}
