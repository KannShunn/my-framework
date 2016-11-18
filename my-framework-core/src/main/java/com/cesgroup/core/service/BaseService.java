package com.cesgroup.core.service;

import com.cesgroup.core.utils.SearchFilter;
import com.cesgroup.core.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * 基础 service，提供了一些基本的增删改查动作
 * 
 * @author niklaus
 *
 * @param <T>
 */
public interface BaseService<T extends BaseEntity<String>>
{

	public <S extends BaseService> S getService(Class<S> serviceClass);


	/**
	 * 根据id查询单个对象
	 * <p>
	 * 需要注意，在某个具体的service中使用此方法，操作的均是
	 * 当前service对应的 实体对象
	 * 
	 * @param id
	 * @return
	 */
	public T getOneById(String id);

	/**
	 * 返回所有当前实体
	 * <p>
	 * 需要注意，在某个具体的service中使用此方法，操作的均是
	 * 当前service对应的 实体对象
	 * 
	 * @return
	 */
	public List<T> getAll();

	public List<T> getAll(String sort);
	/**
	 * 通过复杂条件查询集合
	 * <p>
	 * 需要注意，在某个具体的service中使用此方法，操作的均是
	 * 当前service对应的 实体对象
	 * 
	 * @return
	 */
	public List<T> getAllByCondition(Specification<T> spec, Sort sort);

	/**
	 * 通过简单条件查询结果集
	 * <p>
	 * 需要注意，在某个具体的service中使用此方法，操作的均是
	 * 
	 * @param sfs
	 * @param sort
	 * @return
	 */
	public List<T> getAllByCondition(List<SearchFilter> sfs, Sort sort);

	/**
	 * 通过条件分页查询，可以使用 PageVo.convertFromSpringJPAPage(Page
	 * <?> page)直接转换为组件库分页表格支持的数据结构
	 * <p>
	 * 需要注意，在某个具体的service中使用此方法，操作的均是
	 * 
	 * @param filter
	 *            查询条件
	 * @param pageNumber
	 *            页码
	 * @param pageSize
	 *            每页数量
	 * @param sort
	 *            排序
	 * @return spring的分页对象
	 */
	public Page<T> getByPage(String filter, int pageNumber, int pageSize, Sort sort);

	/**
	 * 根据名字查询对象
	 * 
	 * @param name
	 * @return
	 */
	public List<T> getByName(String name);

	/**
	 * 批量新增。
	 * <p>
	 * 每次提交45条。跟序列号生成做对应
	 * 
	 * @param t
	 */
	public void createBatch(List<T> t);
	
	/**
	 * 批量更新<p>
	 * 每次提交50条。
	 * @param t
	 */
	public void updateBatch(List<T> t);

	public void delete(T t);

	/**
	 * 通用拖动排序, 需实现SortEntity接口
	 * @param sortBeforeIDs
	 * @param sortAfterIDs
	 * @return
	 *
	 */
	public boolean sort(String sortBeforeIDs, String sortAfterIDs);

	/**
	 * 唯一性检验
	 * 如果传入 ID，那么忽略自己
	 * @version 1.0.2015.0409
	 * @param id
	 * @param filter
	 * @return true : 合法, false : 不合法
	 *
	 */
	public boolean checkUnique(String id, String filter);


	public T create(T entity);

	public T update(T entity);

	public void delete(String ids);

	/**
	 * 根据条件返回符合条件的记录数量
	 * 
	 * @param sfs
	 * @return
	 */
	public Long count(List<SearchFilter> sfs);
}
