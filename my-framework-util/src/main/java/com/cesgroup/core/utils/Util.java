package com.cesgroup.core.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 应用系统通用功能函数
 * 
 * @author
 * 
 */
public class Util {
	/**
	 * 日志
	 */
	private static Log logger = LogFactory.getLog(Util.class);

	/**
	 * 判断是否为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNull(Object obj) {
		if ((obj != null) && (!"".equals(obj)))
			return false;
		else
			return true;
	}

	/**
	 * 判断是否非空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean notNull(Object obj) {
		if ((obj != null) && (!"".equals(obj.toString())))
			return true;
		else
			return false;
	}

	/**
	 * 判断是否非空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean notNull(Object... obj) {
		for (int i = 0; i < obj.length; i++) {
			if(obj[i] == null){
				return false;
			}
		}
		return true;
	}
	
	final public static SimpleDateFormat DF_DATE = new SimpleDateFormat(
			"yyyy-MM-dd");
	final public static SimpleDateFormat DF_TIME = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * 将字符串转为日期型返回，转换异常返回null
	 * 
	 * @param s
	 *            param sf
	 * @return
	 */
	public static Date toDate(String s, SimpleDateFormat sf) {
		try {
			if (s == null)
				return null;
			return sf.parse(s);
		} catch (Throwable e) {
			return null;
		}
	}

	/**
	 * 将Date类型的对象转化为String
	 * 
	 * @param date
	 * @param sf
	 * @return
	 */
	public static String toStr(Date date, SimpleDateFormat sf) {
		if (isNull(date))
			return "";
		return sf.format(date);
	}

	/**
	 * 取得当前日期，格式为2008-01-01 01:01
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		return Util.getCurrentDate(true);
	}

	/**
	 * 取得当前日期,hasTime为true 格式为2008-01-01 01:01,hasTime为false 格式为2008-01-01
	 * 
	 * @param hasTime
	 * @return
	 */
	public static String getCurrentDate(boolean hasTime) {
		return Util.toStr(new Date(), hasTime ? Util.DF_TIME : Util.DF_DATE);
	}

	/**
	 * 将日期毫秒数转化为年月格式（YYYYMM），例如20080607
	 * 
	 * @param time
	 *            日期毫秒数
	 * @return
	 */
	public static String toYearMonth(Long time) {
		Calendar d = Calendar.getInstance();
		d.setTimeInMillis(time);
		int year = d.get(Calendar.YEAR);
		int month = d.get(Calendar.MONTH) + 1;
		return "" + year + (month > 9 ? ("" + month) : ("0" + month));
	}

	/**
	 * 将日期毫秒数转化为年月日格式（YYYYMMDD），例如2008060708
	 * 
	 * @param time
	 *            日期毫秒数
	 * @return
	 */
	public static String toDate(Long time) {
		Calendar d = Calendar.getInstance();
		d.setTimeInMillis(time);
		int year = d.get(Calendar.YEAR);
		int month = d.get(Calendar.MONTH) + 1;
		int date = d.get(Calendar.DATE);
		return "" + year + "-" + (month > 9 ? ("" + month) : ("0" + month))
				+ "-" + (date > 9 ? ("" + date) : ("0" + date));
	}

	/**
	 * 判断是否为字母a-z,A-Z
	 * 
	 * @param c
	 * @return
	 */
	static public boolean isCharator(char c) {
		return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
	}

	/**
	 * 判断是否为数字0-9
	 * 
	 * @param c
	 * @return
	 */
	static public boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}

	/**
	 * 对集合排序
	 * 
	 * @param data
	 * @return
	 */
	@SuppressWarnings("unchecked")
	static public <T extends Comparable> List<T> sort(Collection<T> data) {
		List<T> list = new ArrayList<T>(data);
		Collections.sort(list);
		return list;
	}

	/**
	 * 指定属性名和属性值，利用反射调用set方法
	 * 
	 * @param obj
	 * @param fieldName
	 * @param value
	 * @throws Exception
	 */
	public static void invokeSetMethod(Object obj, String fieldName,
			Object value) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String setMethodName = "set" + firstLetter + fieldName.substring(1);
			if (value != null) {
				Method m = obj.getClass().getMethod(setMethodName,
						value.getClass());
				m.invoke(obj, new Object[] { value });
			} else {
				Method[] methods = obj.getClass().getMethods();
				for (Method m : methods) {
					if (m.getName().equals(setMethodName)
							&& (m.getParameterTypes().length == 1)) {
						m.invoke(obj, new Object[] { null });
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 指定属性名，利用反射调用get方法，返回属性值
	 * 
	 * @param obj
	 * @param fieldName
	 * @return
	 * @throws Exception
	 */
	public static Object invokeGetMethod(Object obj, String fieldName) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getMethodName = "get" + firstLetter + fieldName.substring(1);
			Method m = obj.getClass().getMethod(getMethodName);
			return m.invoke(obj, new Object[] {});
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 指定方法名和参数，利用反射调用方法
	 * 
	 * @param obj
	 * @param methodName
	 * @param parameter
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static Object invokeMethod(Object obj, String methodName,
			Object[] parameter) {
		try {
			Method[] methods = obj.getClass().getMethods();
			for (int i = 0; i < methods.length; i++) {
				Method m = methods[i];
				if (m.getName().equals(methodName)) {
					Class<?>[] paraType = m.getParameterTypes();
					if (paraType != null && paraType.length == parameter.length) {
						Object[] newParam = new Object[paraType.length];
						for (int j = 0; j < paraType.length; j++) {
							String typeName = paraType[j].getName();
							String typeShortName = typeName.substring(typeName
									.lastIndexOf(".") + 1);
							if (Util.isNull(parameter[j]))
								newParam[j] = null;
							else {
								if (typeShortName.equals("Long")
										|| typeShortName.equals("long")) {
									newParam[j] = new Long(parameter[j]
											.toString());
								} else if (typeShortName.equals("Integer")
										|| typeShortName.equals("int")) {
									newParam[j] = new Integer(parameter[j]
											.toString());
								} else if (typeShortName.equals("Float")
										|| typeShortName.equals("float")) {
									newParam[j] = new Float(parameter[j]
											.toString());
								} else if (typeShortName.equals("Double")
										|| typeShortName.equals("double")) {
									newParam[j] = new Double(parameter[j]
											.toString());
								} else if (typeShortName.equals("String")) {
									newParam[j] = parameter[j].toString();
								} else {
									newParam[j] = parameter[j];
								}
							}
						}
						return m.invoke(obj, newParam);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	/**
	 * 加密消息摘要码
	 * 
	 * @param b
	 * @return
	 */
	public static String MD5Encrypt(String str) {
		return md5Encode(str.getBytes());
	}

	/**
	 * 加密消息摘要码
	 * 
	 * @param b
	 * @return
	 */
	public static String MD5Encrypt(byte[] b) {
		try {
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(b);
			byte[] md = mdInst.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < md.length; i++) {
				int val = ((int) md[i]) & 0xff;
				if (val < 16)
					sb.append("0");
				sb.append(Integer.toHexString(val));

			}
			return sb.toString();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得消息摘要码
	 * 
	 * @param b
	 * @return
	 */
	public static String md5Encode(byte[] b) {
		String resultString = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(b));
		} catch (Exception ex) {
			resultString = null;
		}
		return resultString;
	}

	/**
	 * 取得消息摘要码
	 * 
	 * @param fileName
	 * @return
	 */
	public static String md5Encode(String str) {
		return md5Encode(str.getBytes());
	}

	/**
	 * 取得消息摘要码
	 * 
	 * @param file
	 * @return
	 */
	public static String md5Encode(File file) {
		if (file.exists() && file.isFile()) {
			FileInputStream in = null;
			try {
				byte[] b = new byte[1024];
				MessageDigest md = MessageDigest.getInstance("MD5");
				in = new FileInputStream(file);
				while ((in.read(b)) != -1) {
					md.update(b);
				}
				return Util.byteArrayToHexString(md.digest());
			} catch (Exception e) {
				logger.error(e.toString());
				return "";
			} finally {
				try {
					in.close();
					in = null;
				} catch (Exception e) {
					logger.error(e.toString());
				}
			}
		}
		return "";
	}

	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static <T> T copyBean(final T bean) {
		if (bean == null)
			return null;
		try {
			T newBean = (T) bean.getClass().newInstance();
			Method[] methods = bean.getClass().getMethods();
			String name;
			Object value;
			Method get;
			for (Method m : methods) {
				if (!Modifier.isPublic(m.getModifiers()))
					continue;
				if (!m.getName().startsWith("set"))
					continue;
				if (m.getParameterTypes() == null
						|| m.getParameterTypes().length > 1)
					continue;
				name = m.getName().substring(3);
				try {
					get = bean.getClass().getMethod("get" + name);
					if (get == null)
						get = bean.getClass().getMethod("is" + name);
					if (get == null)
						continue;
					value = get.invoke(bean);
					if (value != null
							&& !(value instanceof Number // || value
									// instanceof
									// Collection
									|| value instanceof String
									|| value instanceof Boolean
									|| value instanceof Character
									|| value.getClass().isArray() || value
									.getClass().isEnum()))
						continue;
					m.invoke(newBean, new Object[] { value });
				} catch (Exception e) {
					continue;
				}
			}
			return newBean;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 复制
	 * 
	 * @param <T>
	 * @param bean
	 * @return
	 */
	public static <T> T copy(final T bean) {
		if (bean == null)
			return null;
		try {
			T newBean = (T) bean.getClass().newInstance();
			for (Method m : bean.getClass().getMethods()) {
				if (!m.getName().startsWith("set"))
					continue;
				if (m.getParameterTypes() != null
						&& m.getParameterTypes().length > 1)
					continue;
				String name = m.getName().substring(3);
				try {
					Method get = bean.getClass().getMethod("get" + name);
					if (get == null)
						get = bean.getClass().getMethod("is" + name);
					if (get == null)
						continue;
					Object value = get.invoke(bean);
					m.invoke(newBean, new Object[] { value });
				} catch (Exception e) {
					continue;
				}
			}
			return newBean;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 复制源对象中不为空的值到目标对象
	 * 
	 * @param <T>
	 *            java bean
	 * @param src
	 *            源对象
	 * @param dest
	 *            目标对象
	 * @author xjf 09-05-27
	 */
	public static <T> void copyProperties(T src, T dest) {
		if (src == null || dest == null) {
			return;
		}
		try {
			for (Method set : src.getClass().getMethods()) {
				if (!set.getName().startsWith("set")) {
					continue;
				}
				String name = set.getName().substring(3);
				Method get = src.getClass().getMethod("get" + name);
				if (get == null)
					get = src.getClass().getMethod("is" + name);
				if (get == null)
					continue;
				Object value = get.invoke(src);
				System.out.println("(" + value + ")");
				if (value != null) {// && !"".equals(value)
					if (value instanceof Character) {
						char c = (Character) value;
						if (c == '\u0000')
							continue;
					}
					set.invoke(dest, value);
				}
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 复制文件
	 * 
	 * @param sourceFile
	 * @param targetFile
	 * @throws IOException
	 */
	public static void copyFile(File sourceFile, File targetFile) {
		try {
			if (!sourceFile.exists()) {
				System.out.println("源文件不存在！已跳过！");
				return;
			}
			if (!targetFile.getParentFile().exists()) {
				targetFile.getParentFile().mkdirs();
			}
			// 新建文件输入流并对它进行缓冲
			FileInputStream input = new FileInputStream(sourceFile);
			BufferedInputStream inBuff = new BufferedInputStream(input);

			// 新建文件输出流并对它进行缓冲
			FileOutputStream output = new FileOutputStream(targetFile);
			BufferedOutputStream outBuff = new BufferedOutputStream(output);

			// 缓冲数组
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			// 刷新此缓冲的输出流
			outBuff.flush();

			// 关闭流
			inBuff.close();
			outBuff.close();
			output.close();
			input.close();
		} catch (IOException e) {
			logger.debug("复制文件失败！");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除文件,或者目录下面的所有文件和目录。
	 * 
	 * @param f
	 *            如果是文件，直接删除，是目录则递归删除
	 * @since 2009-8-19
	 */
	public static void deleteFiles(File f) throws IOException {
		if (!f.exists())
			return;
		if (f.isFile()) {
			f.delete();
			return;
		}
		//因为 listfiles 有可能会返回 null ！
		File[] files = f.listFiles();
		if (files != null)
		{
			for (File file : files) {
				if (file.isDirectory() && file.list().length > 0) {
					deleteFiles(file);
				}
				file.delete();
			}
		}
	}

	/**
	 * convert queryMap to Bean, the value of "null" will be ignored.
	 * 
	 * @param map
	 * @param bean
	 *            void
	 * @author liuliang 2009-6-15
	 */
	public static void propertyMap2Bean(final Map<String, Object> map,
			final Object bean) {
		if (bean == null || map == null)
			return;
		try {
			String name;
			Object value;
			Method set;
			for (String key : map.keySet()) {
				name = key.trim();
				value = map.get(key);
				if (value == null)
					continue;
				try {
					name = name.substring(0, 1).toUpperCase()
							+ name.substring(1);
					set = bean.getClass().getMethod("set" + name);
					if (set == null || !Modifier.isPublic(set.getModifiers()))
						continue;
					if (set.getParameterTypes() == null
							|| set.getParameterTypes().length > 1)
						continue;
					if (!(set.getParameterTypes())[0].isInstance(value))
						continue;
					set.invoke(bean, new Object[] { value });
				} catch (Exception e) {
					continue;
				}
			}
		} catch (Exception e) {
		}
	}

	/**
	 * 和JS中的join方法功能一样
	 * 
	 * @param ids
	 * @param symbol
	 * @return
	 * @since 2009-8-11
	 */
	public static String join(Long[] ids, String symbol) {
		StringBuffer buf = new StringBuffer();
		for (Long id : ids) {
			buf.append(symbol + id);
		}
		return buf.substring(1);
	}

	public static List<String> toList(String content, int size) {
		List<String> list = new ArrayList<String>();
		if (Util.isNull(content))
			return list;
		int i = 0;
		while (i + size <= content.length()) {
			list.add(content.substring(i, i + size));
			i += size;
		}
		if (i < content.length())
			list.add(content.substring(i));
		return list;
	}

	public static String toString(List<String> list) {
		StringBuffer str = new StringBuffer(3000);
		for (String s : list) {
			// logger.debug(s);
			str.append(s);
		}
		return str.toString();
	}
	
	/**
     * 返回一个唯一标志数字，可用作主键值
     * 
     * @return
     */
    public static String generateId() {
    	String s = UUID.randomUUID().toString(); 
        //去掉“-”符号 
        return s.replace("-", "");
    }

	public static int obj2int(Object obj){
		if(obj == null){
			return 0;
		}
		if(obj instanceof BigDecimal){
			return ((BigDecimal) obj).intValue();
		}
		if(obj instanceof BigInteger){
			return ((BigInteger) obj).intValue();
		}
		if(obj instanceof Long){
			return ((Long) obj).intValue();
		}
		if(obj instanceof Integer){
			return ((Integer) obj).intValue();
		}
		if(obj instanceof String || obj instanceof Character){
			if (StringUtils.isNumeric(obj.toString())) {
				return Integer.parseInt(obj.toString());
			}
		}
		return 0;
	}
}
