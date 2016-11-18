package com.cesgroup.core.aop;

import com.cesgroup.core.annotation.CesLog;
import com.cesgroup.core.entity.IModel;
import com.cesgroup.core.interfaces.CesLogProcessor;
import com.cesgroup.core.utils.ArrayUtil;
import com.cesgroup.core.utils.StrUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 动态日志切面
 * 
 * @author niklaus
 *
 */
@Component
@Aspect
public class LogAop
{
	private static final Logger logger = LoggerFactory.getLogger(LogAop.class);
//	private static final int CORE_POOL_SIZE = 3;
//	private static final int MAXIMUM_POOL_SIZE = 10;
//	private static final int KEEP_ALIVE_TIME = 0;
//	private static final int CAPACITY = 100;
//	private static final ThreadPoolExecutor POOL = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(CAPACITY));

	private static final String ELEXPRESSION_PRE = "${";
	private static final String ELEXPRESSION_SUF = "}";
	

	@Resource
	HttpServletRequest request;
	@Autowired(required = false)
	CesLogProcessor cesLogProcessor;
	
	
	/**
	 * 在所有标注@CesLog的地方后
	 * 
	 * @param joinPoint
	 */
	//@SuppressWarnings("unchecked")
	@Around("@annotation(com.cesgroup.core.annotation.CesLog)")
	public Object log(ProceedingJoinPoint joinPoint)
	{
		Object result = null;

		try {
			//执行方法
			result = joinPoint.proceed();
			//方法执行完记录日志
			saveLog(joinPoint, true);
		} catch (Throwable e) {
			//方法执行报错记录日志
			saveLog(joinPoint, false);
			logger.error(e.getMessage() + "; 参数值:"+ ArrayUtil.toString(joinPoint.getArgs()), e);
			throw new RuntimeException(e);
		}
		return result;
	}

	private void saveLog(ProceedingJoinPoint joinPoint, boolean isSuccess){

		MethodSignature ms = (MethodSignature) joinPoint.getSignature();
		Method method = ms.getMethod();

		if (method.isAnnotationPresent(CesLog.class))
		{
			//获取注解信息
			CesLog cesLog = method.getAnnotation(CesLog.class);
			
			if (cesLog != null){
				Object thisObj = joinPoint.getThis();
				String type = processELExpression(cesLog.type());
				if(StringUtils.isEmpty(type) && thisObj instanceof IModel){
					type = ((IModel) thisObj).getModelName();
				}
				String operate = processELExpression(cesLog.operate());
				String message = processELExpression(cesLog.message());
				String note = processELExpression(cesLog.note());
				logger.warn(type + " | " + operate + " | " + message);

				if (cesLogProcessor != null) {
					cesLogProcessor.saveLog(cesLog.isLog(),cesLog.isLogin(),type,operate,message,note,isSuccess);
				}
			}
			/*cesLog.message();
			
			Object[] args = joinPoint.getArgs();
			Annotation[][] annotations = method.getParameterAnnotations();
			for (int i = 0; i < annotations.length; i++)
			{
				Annotation[] at = annotations[i];
				for (Annotation a : at)
				{
					if (a.annotationType().isAssignableFrom(CesLogBean.class))
					{
						if (args != null)
						{
							if (args[i] != null && args[i] instanceof OperateLogEntity)
							{
								operateLogDao.save((OperateLogEntity)args[i]);
								break;
							}
						}
					}
				}
			}*/
			
		}
	}
	


	/**
	 * 处理EL表达式.
	 */
	private String processELExpression(String str) {
		String[] result = StrUtil.split(str, ELEXPRESSION_PRE, ELEXPRESSION_SUF);
		
		if (result == null) return null;
		
		if (!"".equals(result[1])) {
			String value = request.getParameter(result[1]);
/*			if (StrUtil.isEmpty(value)) {
				Object params = context.getParameters().get(result[1]);
				
				if (params != null) {
					if (params.getClass().isArray()) {
						value = String.valueOf(((Object[])params)[0]);
					} else {
						value = String.valueOf(params);
					}
				}
			}
			
			if (StrUtil.isEmpty(value)) {
				value = context.getSession().get(result[1])==null?null:context.getSession().get(result[1]).toString();
			}*/
			if(value == null){
				value = "[]";
			}

			result[1] = value;
		}
		
		if (!"".equals(result[2])) {
			result[2] = processELExpression(result[2]);
		}
		
		return result[0]+result[1]+result[2];
	}

}
