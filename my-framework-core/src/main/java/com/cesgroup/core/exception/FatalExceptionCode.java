package com.cesgroup.core.exception;

/**
 * 系统致命异常状态码
 * 
 * @author 国栋
 *
 */
public class FatalExceptionCode implements ErrorCode
{


	private final int code;
	private final String comment;

	public FatalExceptionCode(int code, String comment)
	{
		this.code = code;
		this.comment = comment;
	}

	@Override
	public int getCode()
	{
		return 0; // To change body of implemented methods use File | Settings |
					// File Templates.
	}

	@Override
	public String getComment()
	{
		return comment;
	}
}
