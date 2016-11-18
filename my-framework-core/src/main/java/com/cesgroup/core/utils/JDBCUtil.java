package com.cesgroup.core.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import com.cesgroup.core.vo.PageVo;

public class JDBCUtil {

	private static Query setParams4Query(Query query,Object[] params){
		if(params==null||params.length==0){
			return query;
		}
		for(int i=0;i<params.length;i++){
			query.setParameter(i+1, params[i]);
		}
		return query;
	}
	
	private static <T>TypedQuery<T> setParams4TypedQuery(TypedQuery<T> query,Object[] params){
		if(params==null||params.length==0){
			return query;
		}
		for(int i=0;i<params.length;i++){
			query.setParameter(i+1, params[i]);
		}
		return query;
	}
	
	/**
	 * 将大写的字段名转为驼峰标识的属性名
	 * @param contents
	 * @return
	 */
	private static List<Map<String,String>> transfer2Upper(List<Map<String,String>> contents){
		List<Map<String,String>> contentMap = new ArrayList<Map<String,String>>();
		for(Map<String,String> content:contents){
			Map<String,String> map = new HashMap<String,String>();
			for(String key:content.keySet()){
				String value = "";
				Object obj = content.get(key);
				if(obj!=null){
					value = obj.toString();
				}
				key = key.toLowerCase();
				if(key.indexOf("_")!=-1){
					key = CamelCaseUtils.toCamelCase(key);
				}
				map.put(key, value);
			}
			contentMap.add(map);
		}
		return contentMap;
	}
	
	
	public static PageVo queryForSQL(EntityManager entityManager,int pageSize,int pageNumber,String sql,Object...params){
		

    	String countSQL = sql.substring(sql.toLowerCase().indexOf("from"));
    	countSQL = countSQL.substring(0, countSQL.toLowerCase().lastIndexOf(" order "));
    	countSQL ="select count(*) "+countSQL;
		
		Query query = entityManager.createNativeQuery(sql);
		query = setParams4Query(query, params);
		query.setFirstResult(pageNumber*pageSize);
		query.setMaxResults(pageSize);
		//返回结果为map形式
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);  
		
		List<Map<String,String>> contents = query.getResultList();
		List<Map<String,String>> contentMap = JDBCUtil.transfer2Upper(contents);
		
		Long total = 0L;
		Query queryTotal = entityManager.createNativeQuery(countSQL);
		queryTotal = setParams4Query(queryTotal, params);
		Object obj = queryTotal.getSingleResult();
		total = Long.valueOf(String.valueOf(obj));

		PageVo result = new PageVo();
		result.setData(contentMap);
		result.setPageNumber((pageNumber + 1));
		result.setPageSize(pageSize);
		result.setTotal(total);
		result.setTotalPages((int) ((total + pageSize - 1)/pageSize));
		return result;
	}
	
	
	public static PageVo queryForJPQL(EntityManager entityManager,int pageSize,int pageNumber,StringBuilder jpql,String prefix,Class<?> clazz,List<SearchFilter> sfList,String sorts,Object...params){
		

    	String countSQL = jpql.substring(jpql.indexOf("from"));
    	StringBuilder jpqlCount = new StringBuilder("select count(*) "+countSQL);
    	
		if (Collections3.isNotEmpty(sfList))
		{
			for (SearchFilter sf : sfList)
			{
				jpql.append(" and "+prefix+".").append(SearchFilter.parseOperatorToString(sf));
				jpqlCount.append(" and "+prefix+".").append(SearchFilter.parseOperatorToString(sf));
			}
		}
		if (StringUtils.isNotBlank(sorts))
		{
			jpql.append(" order by ");
			jpqlCount.append(" order by ");
			String[] orders = sorts.split(",");
			for (String o : orders)
			{
				if (StringUtils.isNotBlank(o))
				{
					String[] cD = o.split("_");
					jpql.append(""+prefix+".").append(cD[0]).append(" ").append(cD[1]);
					jpqlCount.append(""+prefix+".").append(cD[0]).append(" ").append(cD[1]);
				}
			}
		}
		
		TypedQuery<?> query = entityManager.createQuery(jpql.toString(),clazz);
		query = setParams4TypedQuery(query, params);
		query.setFirstResult(pageNumber*pageSize);
		query.setMaxResults(pageSize);
		
		Long total = 0L;
		TypedQuery<Long> queryTotal = entityManager.createQuery(jpqlCount.toString(),Long.class);
		queryTotal = setParams4TypedQuery(queryTotal, params);
		Object obj = queryTotal.getSingleResult();
		total = Long.valueOf(String.valueOf(obj));
		
		PageVo result = new PageVo();
		result.setData(query.getResultList());
		result.setPageNumber((pageNumber + 1));
		result.setPageSize(pageSize);
		result.setTotal(total);
		result.setTotalPages((int) ((total + pageSize - 1)/pageSize));
		return result;
	}
}
