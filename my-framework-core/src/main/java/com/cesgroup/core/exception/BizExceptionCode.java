package com.cesgroup.core.exception;

/**
 * 业务异常状态吗
 * 
 * @author 国栋
 *
 */
public class BizExceptionCode implements ErrorCode
{

	private final int code;
	private final String comment;

	public BizExceptionCode(int code, String comment)
	{
		this.code = code;
		this.comment = comment;
	}

	@Override
	public int getCode()
	{
		return code;
	}

	@Override
	public String getComment()
	{
		return comment;
	}

}
