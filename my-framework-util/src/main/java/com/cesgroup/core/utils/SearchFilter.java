
package com.cesgroup.core.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;

/**
 * 简单过滤条件，与spring specification 复杂条件做转换
 * 
 * @author 国栋
 *
 */
public class SearchFilter
{

	/**
	 * 操作符
	 * 
	 * @author 国栋
	 *
	 */
	public enum Operator
	{
		EQ, LIKE, GT, LT, GTE, LTE, NEQ, IN, IGCEQ
	}

	public String fieldName;	//属性名
	public Object value;		//对应值
	public Operator operator;	//计算符

	/**
	 * 创建简单条件对象
	 * 
	 * @param fieldName
	 *            字段名，实体的字段名，非数据库字段
	 * @param operator
	 *            操作符
	 * @param value
	 *            值域
	 */
	public SearchFilter(String fieldName, Operator operator, Object value)
	{
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
	}

	/**
	 * searchParams中key的格式为OPERATOR_FIELDNAME
	 */
	public static Map<String, SearchFilter> parse(Map<String, Object> searchParams)
	{
		Map<String, SearchFilter> filters = Maps.newHashMap();

		for (Entry<String, Object> entry : searchParams.entrySet())
		{
			// 过滤掉空值
			String key = entry.getKey();
			Object value = entry.getValue();
			if (value == null)
			{
				continue;
			}

			// 拆分operator与filedAttribute
			String[] names = StringUtils.split(key, "_");
			if (names.length != 2)
			{
				throw new IllegalArgumentException(key + " 不是一个有效的字段名");
			}
			String filedName = names[1];
			Operator operator = Operator.valueOf(names[0]);

			// 创建searchFilter
			SearchFilter filter = new SearchFilter(filedName, operator, value);
			filters.put(key, filter);
		}

		return filters;
	}

	/**
	 * 根据前台传入的 参数，解析成searchfilter 数组
	 * 
	 * @param searchParams
	 *            数组中间包含的字符串为 name_LIKE_value
	 * @return
	 */
	public static List<SearchFilter> parse(String[] searchParams)
	{
		List<SearchFilter> result = new ArrayList<SearchFilter>();
		if (searchParams != null && searchParams.length > 0)
		{
			SearchFilter searchFilter;
			for (String sfP : searchParams)
			{
				//根据操作符去截取fieldName和value
				String fieldName = "";
				String value = "";
				String operator = "";
				for(Object obj : Operator.values()){
					String str = "_"+obj+"_";
					if(sfP.contains(str)){
						fieldName = sfP.substring(0,sfP.indexOf(str));
						value = sfP.substring(sfP.indexOf(str)+str.length(),sfP.length());
						operator = String.valueOf(obj);
						break;
					}
				}
				if (StringUtils.isNotBlank(fieldName) && StringUtils.isNotBlank(operator) && StringUtils.isNotBlank(value))
				{
					searchFilter = new SearchFilter(fieldName, Operator.valueOf(operator), value);
					result.add(searchFilter);
				}
			}
		}
		return result;
	}

	public static String parseOperatorToString(SearchFilter sf)
	{
		StringBuilder result = new StringBuilder();
		switch (sf.operator)
		{
		case EQ:
			result.append(sf.fieldName).append("='").append(sf.value).append("'");
			break;
		case LIKE:
			result.append(sf.fieldName).append(" like '%").append(sf.value).append("%'");
			break;
		case GT:
			result.append(sf.fieldName).append(">'").append(sf.value).append("'");
			break;
		case LT:
			result.append(sf.fieldName).append("<'").append(sf.value).append("'");
			break;
		case GTE:
			result.append(sf.fieldName).append(">='").append(sf.value).append("'");
			break;
		case LTE:
			result.append(sf.fieldName).append("<='").append(sf.value).append("'");
			break;
		case NEQ:
			result.append(sf.fieldName).append("!='").append(sf.value).append("'");
			break;
		case IN:
			String value = "'" + sf.value.toString().replaceAll(",", "','") + "'";
			result.append(sf.fieldName).append("in (").append(value).append(")");
			break;
		case IGCEQ:
			result.append("upper(").append(sf.fieldName).append(") ").append("='").append(sf.value.toString().toUpperCase()).append("'");
			break;
		default:
			break;

		}
		return result.toString();
	}
}
