package com.cesgroup.core.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cesgroup.core.entity.BaseEntity;

/**
 * 基础dao，与具体实体对应的接口dao必须继承此dao。
 * <p>
 * 需要注意的是，因为此接口当前无法确定 泛型 baseentity所对应的具体实体类，所以需要标注
 * <code>@NoRepositoryBean</code>
 * 
 * @author niklaus
 *
 * @param <T>
 */
@NoRepositoryBean
public interface BaseDao<T extends BaseEntity<String>> extends PagingAndSortingRepository<T, String>, JpaSpecificationExecutor<T>
{

}
