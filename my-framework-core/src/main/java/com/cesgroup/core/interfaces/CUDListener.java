package com.cesgroup.core.interfaces;


import com.cesgroup.core.entity.BaseEntity;

/**
 * Created by Administrator on 2016-7-11.
 */
public interface CUDListener<T extends BaseEntity<String>> {

    /** 新增开始时的处理逻辑 */
    void onCreateStart(T entity);
    /** 新增成功时的处理逻辑 */
    T onCreateSuccess(T entity);
    /** 新增出错时的处理逻辑 */
    void onCreateError(Exception e, T entity);
    /** 新增完成时的处理逻辑 */
    void onCreateComplete(T entity);

    /** 修改开始时的处理逻辑 */
    void onUpdateStart(T entity);
    /** 修改成功时的处理逻辑 */
    T onUpdateSuccess(T entity);
    /** 修改出错时的处理逻辑 */
    void onUpdateError(Exception e, T entity);
    /** 修改完成时的处理逻辑 */
    void onUpdateComplete(T entity);

    /** 删除开始时的处理逻辑 */
    void onDeleteStart(String ids);
    /** 删除成功时的处理逻辑 */
    void onDeleteSuccess(String ids);
    /** 删除出错时的处理逻辑 */
    void onDeleteError(Exception e, String ids);
    /** 删除完成时的处理逻辑 */
    void onDeleteComplete(String ids);
}
