package com.cesgroup.core.exception;

/**
 * 系统错误码
 * 
 * @author 国栋
 *
 */
public interface ErrorCode
{
	/**
	 * 返回错误码
	 * 
	 * @return
	 */
	public int getCode();

	/**
	 * 返回错误码对应的描述
	 * 
	 * @return
	 */
	public String getComment();
}
