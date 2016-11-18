package com.cesgroup.common.global;


/**
 * 
 * 系统路径管理类
 * <p>描述:系统路径管理类</p>
 */
public class ContextPathManager {
	
	public static final String URL_SEPARATOR = "/";
	private static String webRootPath;

	private static String webAbsolutePath;
	
	//FileUtil.ConvertPath(event.getServletContext().getRealPath("/"));
	/**
	 * 获取webApp根路径
	 * @return
	 */
	public static String getWebRootPath(){
		return webRootPath;
	}

	public static void setWebRootPath(String webRootPath) {
		ContextPathManager.webRootPath = webRootPath;
	}
	
	/** 获取达梦更新脚本文件路径 */
	public static String getDmUpdateFilePath(){
		return webAbsolutePath + "WEB-INF/sql/dm/dm_update.sql";
	}
	
	/** 获取oracle更新脚本文件路径 */
	public static String getOracleUpdateFilePath(){
		return webAbsolutePath + "WEB-INF/sql/oracle/oracle_update.sql";
	}
	
	/** 获取头像原图路径 */
	public static String getUploadOriginPath(){
		return webAbsolutePath + "upload/images/origin/";
	}
	
	/** 获取头像缩略图路径 */
	public static String getUploadSmallPath(){
		return webAbsolutePath + "upload/images/small/";
	}
	
	/**
	 * 获取系统的绝对路径
	 *
	 */
	public static String getWebAbsolutePath(){
		return webAbsolutePath;
	}

	public static void setWebAbsolutePath(String webAbsolutePath) {
		ContextPathManager.webAbsolutePath = webAbsolutePath;
	}

	public static String getMysqlUpdateFilePath() {
		return webAbsolutePath + "WEB-INF/sql/mysql/mysql_update.sql";
	}

	public static String getSqlServerUpdateFilePath() {
		return webAbsolutePath + "WEB-INF/sql/sqlserver/sqlserver_update.sql";
	}

	public static String getSqlServerDataUpdateFilePath() {
		return webAbsolutePath + "WEB-INF/sql/sqlserver/sqlserver_updateData.sql";
	}
}
