package com.cesgroup.common.listener;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cesgroup.common.global.ContextPathManager;
import com.cesgroup.core.utils.FileUtil;

/**
 * 
 * 系统管理平台数据库更新
 * <p>描述:当程序启动时, 初始化servlet上下文的同时更新数据库</p>
 */
public class SystemInitListener implements ServletContextListener {

	private static final Logger logger = LoggerFactory.getLogger(SystemInitListener.class);
	
	/** Spring框架上下文. */
	private static ApplicationContext ctx = null;
	
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		
		//初始化spring上下文
		ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());

		//初始化系统路径
		ContextPathManager.setWebRootPath(event.getServletContext().getContextPath());
		ContextPathManager.setWebAbsolutePath(FileUtil.ConvertPath(event.getServletContext().getRealPath("/")));
		
		//更新数据库
		updateDataBase();
		
		//检查上传文件目录是否存在
		checkUploadFolder();
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
//		ctx = null;
		//this.unbindResource();
		
	}

	/**
	 * 从Spring中获取Bean.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> beanClass) {
		return ctx.getBean(beanClass);
	}
	
	/**
	 * 
	 * 检查上传文件夹
	 *
	 */
	private static void checkUploadFolder(){
		//源文件目录
		File originDir = new File(ContextPathManager.getUploadOriginPath());
		
		if(!originDir.exists()){
			originDir.mkdirs();
		}
		
		//缩略图目录
		File smallDir = new File(ContextPathManager.getUploadSmallPath());
		
		if(!smallDir.exists()){
			smallDir.mkdirs();
		}
		
	}
	
	
	/**
	 * 
	 * 更新数据库
	 *
	 */
	private static void updateDataBase(){
		DataSource dataSource = (DataSource) ctx.getBean("dataSource");
		if(dataSource != null){
			Connection conn = null;
			PreparedStatement ps = null;

			try {
				logger.info("正在检查数据库是否需要更新...");
				conn = dataSource.getConnection();
				String jdbcUrl = conn.getMetaData().getURL();
				String sql = null;
				String dataSql = null;
				if (StringUtils.contains(jdbcUrl, ":dm:"))
				{
					sql = FileUtil.readAsString(ContextPathManager.getDmUpdateFilePath()).replaceAll("\\r\\n", " ").trim();;
				}
				else if (StringUtils.contains(jdbcUrl, ":oracle:"))
				{
					sql = FileUtil.readAsString(ContextPathManager.getOracleUpdateFilePath()).replaceAll("\\r\\n", " ").trim();;
				}
				else if (StringUtils.contains(jdbcUrl, ":mysql:"))
				{
					sql = FileUtil.readAsString(ContextPathManager.getMysqlUpdateFilePath()).replaceAll("\\r\\n", " ").trim();;
					ps = conn.prepareStatement("DROP PROCEDURE IF EXISTS p_update");
					ps.execute();
					ps.close();
				}
				else if (StringUtils.contains(jdbcUrl, ":sqlserver:"))
				{
					sql = FileUtil.readAsString(ContextPathManager.getSqlServerUpdateFilePath()).replaceAll("\\r\\n", " ").trim();;
					dataSql = FileUtil.readAsString(ContextPathManager.getSqlServerDataUpdateFilePath()).replaceAll("\\r\\n", " ").trim();;
				}
				//System.out.println(sql);

				ps = conn.prepareStatement(sql);
				ps.execute();
				ps.close();
				if(StringUtils.contains(jdbcUrl, ":mysql:")){
					ps = conn.prepareCall("{call p_update()}");
					ps.execute();
					ps.close();
				}
				if(StringUtils.isNotEmpty(dataSql)){
					ps = conn.prepareStatement(dataSql);
					ps.execute();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
				throw new RuntimeException(e);
			} finally {
				if(ps != null){
					try
					{
						ps.close();
					}
					catch (SQLException e)
					{
					}
				}
				if(conn != null){
					try {
						conn.commit();
					} catch (Exception e2) {
					}
					try
					{
						conn.close();
					}
					catch (SQLException e)
					{
					}
				}
			}
			logger.info("数据库检查完成");
		}
	}
	/**
	 * 从Spring中获取Bean.
	 */
	public static Object getBean(String beanName) {
		return ctx.getBean(beanName);
	}
	
//	/**
//	 * 释放Spring Data事务资源.
//	 */
//	private void unbindResource() {
//		Map<Object, Object> resource = TransactionSynchronizationManager.getResourceMap();
//		
//		if (resource != null) {
//			for (Object key : resource.keySet()) {
//				TransactionSynchronizationManager.unbindResourceIfPossible(key);
//			}
//		}
//	}
}
