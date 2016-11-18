package com.cesgroup.core.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.MySQL5InnoDBDialect;
import org.hibernate.dialect.Oracle10gDialect;
import org.hibernate.dialect.PostgreSQL82Dialect;
import org.hibernate.dialect.SQLServer2008Dialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * hibernate 操作工具类
 * 
 * @author 国栋
 *
 */
public class Hibernates
{
	private static final Logger LOG = LoggerFactory.getLogger(Hibernates.class);
	private static String dataBaseName;
	private static String dataBaseVersion;

	/**
	 * 加载延迟初始化的属性值
	 * <P>
	 * 比如Hibernates.initLazyProperty(user.getGroups());
	 * 
	 * @param proxyedPropertyValue
	 */
	public static void initLazyProperty(Object proxyedPropertyValue)
	{
		Hibernate.initialize(proxyedPropertyValue);
	}

	/**
	 * 从DataSoure中取出connection, 根据connection的metadata中的jdbcUrl判断Dialect类型.
	 * 仅支持Oracle, H2, MySql, PostgreSql, SQLServer，如需更多数据库类型，请仿照此类自行编写。
	 */
	public static String getDialect(DataSource dataSource)
	{
		String jdbcUrl = getJdbcUrlFromDataSource(dataSource);

		String dbName;
		// 根据jdbc url判断dialect
		if (StringUtils.contains(jdbcUrl, ":dm:"))
		{
			dbName = DmDialect.class.getName();
		}
		else if (StringUtils.contains(jdbcUrl, ":oracle:"))
		{
			dbName = Oracle10gDialect.class.getName();
		}
		else if (StringUtils.contains(jdbcUrl, ":h2:"))
		{
			dbName = H2Dialect.class.getName();
		}
		else if (StringUtils.contains(jdbcUrl, ":mysql:"))
		{
			dbName = MySQL5InnoDBDialect.class.getName();
		}
		else if (StringUtils.contains(jdbcUrl, ":postgresql:"))
		{
			dbName = PostgreSQL82Dialect.class.getName();
		}
		else if (StringUtils.contains(jdbcUrl, ":sqlserver:"))
		{
			dbName = SQLServer2008Dialect.class.getName();
		}
		else
		{
			throw new IllegalArgumentException("无法识别数据库 " + jdbcUrl);
		}
//		LOG.info("数据库[{}] 版本[{}] 方言[{}]", dataBaseName, dataBaseVersion, dbName);
		return dbName;
	}

	private static String getJdbcUrlFromDataSource(DataSource dataSource)
	{
		Connection connection = null;
		try
		{
			connection = dataSource.getConnection();

			if (connection == null)
			{
				throw new IllegalStateException("数据源 【" + dataSource + "】 返回的连接为null");
			}
			dataBaseName = connection.getMetaData().getDatabaseProductName();
			dataBaseVersion = connection.getMetaData().getDatabaseProductVersion();
			return connection.getMetaData().getURL();
		}
		catch (SQLException e)
		{
			throw new RuntimeException("无法获取数据库连接 ", e);
		}
		finally
		{
			if (connection != null)
			{
				try
				{
					connection.close();
				}
				catch (SQLException e)
				{
				}
			}
		}
	}
}
