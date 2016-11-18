package com.cesgroup.core.service.impl;

import com.cesgroup.core.dao.BaseDao;
import com.cesgroup.core.entity.BaseEntity;
import com.cesgroup.core.entity.HasSysEntity;
import com.cesgroup.core.entity.SortEntity;
import com.cesgroup.core.interfaces.CUDListener;
import com.cesgroup.core.service.BaseService;
import com.cesgroup.core.utils.BeanContextAware;
import com.cesgroup.core.utils.Collections3;
import com.cesgroup.core.utils.DynamicSpecifications;
import com.cesgroup.core.utils.SearchFilter;
import com.cesgroup.core.utils.SearchFilter.Operator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * baseservice 基本实现。
 * 
 * @author 国栋
 *
 * @param <T>
 */
//@Service
@Transactional
public abstract class BaseServiceImpl<T extends BaseEntity<String>,D extends BaseDao<T>> implements BaseService<T>,CUDListener<T>
{
	protected EntityManager entityManager;

	/** Spring Bean管理服务类. */
	@Autowired
	protected BeanContextAware context;

	protected D dao;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public D getDao(){
		return dao;
	}

	public abstract void setDao(D baseDao);

	/**
	 * 从Spring上下文中获取Dao.
	 * @param daoClass dao类
	 * @return Dao对象
	 */
	@SuppressWarnings("rawtypes")
	protected  <D extends BaseDao> D getDao(Class<D> daoClass) {
		return context.getBean(daoClass);
	}

	/**
	 * 从Spring上下文中获取服务实例.
	 * @param serviceClass 服务类
	 */
	@SuppressWarnings("rawtypes")
	public <S extends BaseService> S getService(Class<S> serviceClass) {
		return context.getBean(serviceClass);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public T getOneById(String id)
	{
		if (id != null)
		{
			return getDao().findOne(id);
		}
		return null;
	}

	/**
	 * 返回所有当前实体
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<T> getAll()
	{
		return (List<T>) getDao().findAll();
	}

	/**
	 * 返回所有当前实体
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<T> getAll(String sort)
	{
		Sort sorter = DynamicSpecifications.parseSorter(sort);
		return (List<T>) getDao().findAll(sorter);
	}
	
	/**
	 * 通过简单条件查询集合
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<T> getAllByCondition(Specification<T> spec, Sort sort)
	{
		if (spec != null && sort != null)
		{
			return getDao().findAll(spec, sort);
		}
		else if (spec != null && sort == null)
		{
			return getDao().findAll(spec);
		}
		else if (spec == null && sort != null)
		{
			return (List<T>) getDao().findAll(sort);
		}
		else
		{
			throw new RuntimeException("根据条件获取实体数组出错，参数不能为空");
		}
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<T> getAllByCondition(List<SearchFilter> sfs, Sort sort)
	{
		List<T> result = null;
		if (sfs != null && sfs.size() > 0)
		{
			@SuppressWarnings("unchecked")
			Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
			Specification<T> spec = DynamicSpecifications.bySearchFilter(sfs, entityClass);
			if (sort != null)
			{
				result = getDao().findAll(spec, sort);
			}
			else
			{
				result = getDao().findAll(spec);
			}
		}
		else if (sort != null)
		{
			result = (List<T>) getDao().findAll(sort);
		}
		return result;
	}

	/**
	 * 通过条件分页查询
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Page<T> getByPage(String filter, int pageNumber, int pageSize, Sort sort)
	{
		
		PageRequest pr = null;
		if (sort != null)
		{
			pr = new PageRequest(pageNumber, pageSize, sort);
		}
		else
		{
			pr = new PageRequest(pageNumber, pageSize);
		}

		if (StringUtils.isNotEmpty(filter))
		{
			List<SearchFilter> sfs = SearchFilter.parse(filter.split(";"));
			Specification<T> spec = DynamicSpecifications.bySearchFilter(sfs, getEntityClass());
			return getDao().findAll(spec, pr);
		}
		else
		{
			return getDao().findAll(pr);
		}
	}

	/**
	 * 根据名字查询对象
	 * 
	 * @param name
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<T> getByName(String name)
	{
		if (StringUtils.isNotBlank(name))
		{
			final String n = name;
			return getDao().findAll(new Specification<T>()
			{
				@Override
				public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb)
				{
					Path<String> expression = root.get("name");
					return cb.equal(expression, n);
				}
			});
		}
		return null;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@SuppressWarnings("unchecked")
	public Long getMaxShowOrder(String entityName){
		Query query = entityManager.createQuery(" select max(showOrder) from  "+entityName);// +entityName);
		Object result = query.getSingleResult() == null ? 0 : query.getSingleResult();
		Long maxOrderNo = Long.valueOf(String.valueOf(result));
		return maxOrderNo + 1L;
	}
	
	@Transactional
	public void createBatch(List<T> t)
	{
		if (Collections3.isNotEmpty(t))
		{
			for (int i = 0; i < t.size(); i++)
			{
				entityManager.persist(t.get(i));
				if (i % 50 == 0)
				{
					entityManager.flush();
					entityManager.clear();
				}
			}
		}
	}

	@Transactional
	public void updateBatch(List<T> t)
	{
		if (Collections3.isNotEmpty(t))
		{
			for (int i = 0; i < t.size(); i++)
			{
				entityManager.merge(t.get(i));
				if (i % 50 == 0)
				{
					entityManager.flush();
					entityManager.clear();
				}
			}
		}
	}

	public void delete(T t)
	{
		if (t != null)
		{
			delete(t.getId());
		}
	}


	/**
	 * 排序
	 * 
	 */
	public boolean sort(String sortBeforeIDs, String sortAfterIDs)
	{
		String[] beforeIDs = sortBeforeIDs.split(",");
		String[] afterIDs = sortAfterIDs.split(",");
		if (afterIDs.length == 0)
			return false;
		T entity = getOneById(beforeIDs[0]);
		
		if(!(entity instanceof SortEntity)){
			throw new RuntimeException("实体类没有集成 SortEntity接口,无法排序");
		}
		SortEntity sortEntity = (SortEntity) entity;
		Long showOrder = 1L;
		if(sortEntity.getShowOrder()!=null){
			showOrder = sortEntity.getShowOrder();
		}
		
		for (int i = 0; i < afterIDs.length; i++) {
			entity = this.getOneById(afterIDs[i]);
			sortEntity = (SortEntity) entity;
			sortEntity.setShowOrder(showOrder);
			this.getDao().save(entity);
			showOrder++;
		}
		return true;
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public boolean checkUnique(String id, String filter){
		List<SearchFilter> sfs = SearchFilter.parse(filter.split(";"));
		
		if (id != null)
		{
			// 如果传入 ID，那么忽略自己
			sfs.add(new SearchFilter("id", Operator.NEQ, id));
		}
		Long count = count(sfs);
		if (count != null)
		{
			if (count > 0)
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		return false;
	}

	public void onCreateStart(T entity){
	}

	public T onCreateSuccess(T entity){
		return entity;
	}

	public void onCreateError(Exception e, T entity){
	}

	public void onCreateComplete(T entity){
	}

	public T create(T entity){
		if (entity != null)
		{
			try {
				onCreateStart(entity);
				if(entity instanceof SortEntity){
					SortEntity sortEntity = (SortEntity) entity;
					sortEntity.setShowOrder(this.getMaxShowOrder(entity.getClass().getName()));
				}
				T result = getDao().save(entity);
				result = onCreateSuccess(result);
				return result;
			} catch (Exception e){
				onCreateError(e,entity);
				throw new RuntimeException(e);
			} finally {
				onCreateComplete(entity);
			}
		}
		return null;
	}

	public void onUpdateStart(T entity){
	}

	public T onUpdateSuccess(T entity){
		return entity;
	}

	public void onUpdateError(Exception e, T entity){
	}

	public void onUpdateComplete(T entity){
	}

	public T update(T entity){
		if(entity != null){
			try {
				onUpdateStart(entity);
				if(entity instanceof HasSysEntity){
					String isSystem = ((HasSysEntity) entity).getIsSystem();
					if(IS_SYSTEM_YES.equals(isSystem)){
						throw new RuntimeException("无法修改系统内置数据");
					}
				}
				T result = getDao().save(entity);
				result = onUpdateSuccess(entity);
				return result;
			} catch (Exception e) {
				onUpdateError(e,entity);
				throw new RuntimeException(e);
			} finally {
				onUpdateComplete(entity);
			}

		}
		return null;
	}

	public void onDeleteStart(String ids){
	}

	public void onDeleteSuccess(String ids){

	}

	public void onDeleteError(Exception e, String ids){
	}

	public void onDeleteComplete(String ids){
	}

	public static final String IS_SYSTEM_YES = "0";

	public void delete(String ids){
		if(StringUtils.isNotEmpty(ids)){

			try {
				onDeleteStart(ids);
				String[] idsArray = ids.split(",");
				T entity = null;
				for (String id : idsArray) {
					entity = getOneById(id);
					if(entity instanceof HasSysEntity){
						String isSystem = ((HasSysEntity) entity).getIsSystem();
						if(IS_SYSTEM_YES.equals(isSystem)){
							throw new RuntimeException("无法删除系统内置数据");
						}
					}
					getDao().delete(entity);
                }
				onDeleteSuccess(ids);
			} catch (Exception e) {
				onDeleteError(e,ids);
				throw new RuntimeException(e);
			} finally {
				onDeleteComplete(ids);
			}
		}
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Long count(List<SearchFilter> sfs)
	{
		if (Collections3.isNotEmpty(sfs))
		{
			return getDao().count(DynamicSpecifications.bySearchFilter(sfs, getEntityClass()));
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Class<T> getEntityClass(){
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	
}
