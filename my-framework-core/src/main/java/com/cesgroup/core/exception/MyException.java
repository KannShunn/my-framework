package com.cesgroup.core.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 自定义业务Exception
 * 
 * @author 国栋
 *
 */
public class MyException extends RuntimeException
{
	private static final long serialVersionUID = -4952353567561884241L;

	/**
	 * 此异常对应的错误码
	 */
	private ErrorCode errorCode;

	/**
	 * 使用指定的信息创建异常对象
	 * 
	 * @param string
	 */
	public MyException(String string)
	{
		super(string);
	}

	/**
	 * 使用指定的信息和原因对象构建Exception
	 * 
	 * @param message
	 * @param e
	 */
	public MyException(String message, Throwable e)
	{
		super(message, e);
	}

	/**
	 * 使用指定的信息和原因对象构建Exception
	 *
	 * @param errorCode
	 */
	public MyException(ErrorCode errorCode)
	{
		super(errorCode.getComment());
		this.errorCode = errorCode;
	}

	public MyException(ErrorCode errorCode, Throwable e)
	{
		super(errorCode.getComment(),e);
		this.errorCode = errorCode;
	}

	/**
	 * 将CheckedException转换为UncheckedException.
	 */
	public static RuntimeException unchecked(Throwable ex)
	{
		if (ex instanceof RuntimeException)
		{
			return (RuntimeException) ex;
		}
		else
		{
			return new RuntimeException(ex);
		}
	}

	/**
	 * 将ErrorStack转化为String.
	 */
	public static String getStackTraceAsString(Throwable ex)
	{
		StringWriter stringWriter = new StringWriter();
		ex.printStackTrace(new PrintWriter(stringWriter));
		return stringWriter.toString();
	}

	/**
	 * 获取组合本异常信息与底层异常信息的异常描述, 适用于本异常为统一包装异常类，底层异常才是根本原因的情况。
	 */
	public static String getErrorMessageWithNestedException(Throwable ex)
	{
		Throwable nestedException = ex.getCause();
		return new StringBuilder().append(ex.getMessage()).append(" nested exception is ").append(nestedException.getClass().getName()).append(":").append(nestedException.getMessage()).toString();
	}

	/**
	 * 获取异常的Root Cause.
	 */
	public static Throwable getRootCause(Throwable ex)
	{
		Throwable cause;
		while ((cause = ex.getCause()) != null)
		{
			ex = cause;
		}
		return ex;
	}

	/**
	 * 判断异常是否由某些底层的异常引起.
	 */
	public static boolean isCausedBy(Exception ex, Class<? extends Exception>... causeExceptionClasses)
	{
		Throwable cause = ex;
		while (cause != null)
		{
			for (Class<? extends Exception> causeClass : causeExceptionClasses)
			{
				if (causeClass.isInstance(cause))
				{
					return true;
				}
			}
			cause = cause.getCause();
		}
		return false;
	}

	public ErrorCode getErrorCode()
	{
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode)
	{
		this.errorCode = errorCode;
	}
}
