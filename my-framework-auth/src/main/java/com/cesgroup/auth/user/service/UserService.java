package com.cesgroup.auth.user.service;

import com.cesgroup.auth.org.entity.Org;
import com.cesgroup.auth.org.vo.OrgUserTreeVo;
import com.cesgroup.auth.resource.entity.Resource;
import com.cesgroup.auth.user.entity.User;
import com.cesgroup.auth.user.vo.UserGridVo;
import com.cesgroup.core.utils.SearchFilter;
import com.cesgroup.core.service.BaseService;
import com.cesgroup.core.vo.PageVo;

import java.util.List;
import java.util.Map;

/**
 * 用户相关服务接口
 * 
 * @author niklaus
 *
 */
public interface UserService extends BaseService<User>
{
	/**
	 * 根据登录名获取用户对象
	 * 
	 * @param loginName
	 * @return
	 */
	User getUserByLoginName(String loginName);

	/**
	 * 获取用户管理的左侧树
	 * 
	 * <p>描述:</p>
	 * @param unitId
	 * @return
	 *
	 */
	List<Org> getLeftOrgSyncTree(String unitId);

	/**
	 * 根据组织id查询用户列表(分页)
	 * 
	 * <p>描述: 根据组织id查询用户列表(分页)</p>
	 * @param orgId 组织id
	 * @param pageNumber 当前页码
	 * @param pageSize 分页大小
	 * @param sort 排序方式
	 * @param sfList 搜索过滤条件
	 * @param isClickRootNode 是否点击的是根结点
	 * @param isSuperadmin 是否是超级管理员登录的
	 * @return 用户列表
	 *
	 */
	PageVo getUsersByOrgId(String orgId, String currentUnitId, int pageNumber, int pageSize, String sorts, List<SearchFilter> sfList, boolean isClickRootNode, boolean isSuperadmin);

	/**
	 * 根据组织id查询用户列表(不分页)
	 * 
	 * <p>描述:</p>
	 * @param orgId 部门id
	 * @param userType 专职还是兼职, 传null则都要
	 * @return
	 *
	 */
	List<User> getUsersByOrgId(String orgId,String userType);
	
	
	/**
	 * 新增用户
	 * 
	 * <p>描述:</p>
	 * @param user 用户对象
	 * @param orgId 部门id
	 * @param roleId 角色id
	 * @return
	 *
	 */
	User create(User user, String orgId, String currentUnitId, String roleId);

	/**
	 * 初始化密码
	 * 
	 * <p>描述: 初始化密码</p>
	 * @param userIds 单个或多个用户id
	 *
	 */
	void initPassword(String userIds);

	/**
	 * 
	 * 修改密码
	 * <p>描述:修改密码</p>
	 * @param password 修改后的密码
	 * @param id 用户id
	 *
	 */
	void changePassword(String password, String id);

	/**
	 * 授予角色保存事件
	 * 
	 * @param userId 用户id
	 * @param addRoleIds 添加的角色id
	 * @param deleteRoleIds 删除的角色id
	 * @param unitId 单位id
	 *
	 */
	void accreditRole(String userId, String addRoleIds,String deleteRoleIds, String unitId,boolean isTempAccredit,String tempAccreditDateStart,String tempAccreditDateEnd);

	/**
	 * 改变组织, 如果目标部门已存在该用户的兼职, 则删掉该兼职
	 * 
	 * <p>描述: 改变组织, 如果目标部门已存在该用户的兼职, 则删掉该兼职</p>
	 * @param userId 用户id
	 * @param unitId 单位id
	 *
	 */
	void changeOrg(String userId, String newOrgId, String oldOrgId, String unitId);

	/**
	 * 用户兼职
	 * 
	 * <p>描述: 用户兼职</p>
	 * @param userId 用户id
	 * @param orgIds 兼职部门ids
	 * @param unitId 单位id
	 *
	 */
	void partTimeJob(String userId, String orgIds, String unitId);

	/**
	 * 撤销兼职
	 * 
	 * <p>描述:撤销兼职, userIds和orgIds和unitIds必须是一一对应的,currentUnitId为当前的登陆人的单位id</p>
	 * @param userIds 被撤销兼职的用户id
	 * @param orgIds 被撤销兼职用户所属的兼职部门id
	 * @param unitIds 被撤销兼职用户的专职单位id
	 * @param currentUnitId 当前登录人的单位id
	 *
	 */
	void removePartTimeJob(String userIds, String orgIds, String unitIds,String currentUnitId);

	/**
	 * 把离职用户恢复回来
	 * @param userId 恢复的用户id
	 * @param orgId 恢复到的部门id
	 * @return 返回恢复成功或失败的消息
	 */
	void renewOldToNew(String userId, String orgId);

	/**
	 * 修改老用户, 只修改用户名称和登录名和工号, 其他都不修改
	 * 
	 * @param user
	 * @return
	 *
	 */
	User updateOldUser(User user);
	
	/**
	 * 根据角色id获取该角色下的用户集合
	 * 
	 * <p>描述: 根据角色id获取该角色下的用户集合</p>
	 * @param roleId
	 * @return 用户集合
	 *
	 */
	List<User> getUsersOfRole(String roleId);

	/**
	 * 获取一棵组织和用户同步树
	 * 
	 * <p>描述:</p>
	 * @param unitId 单位id
	 * @param roleId 角色授予用户时的用户id, 用于check已有该角色的用户的节点
	 * @return 
	 *
	 */
	List<OrgUserTreeVo> getAllOrgUserTree(String unitId,String roleId);

	/**
	 * 获取当前单位下的所有用户, 包括兼职
	 * 
	 * @param unitId
	 * @return
	 *
	 */
	List<UserGridVo> getByUnitId(String unitId);

	/**
	 * 该登录名是否是离职用户
	 * 
	 * <p>描述:该登录名是否是离职用户, 0:不是, 1:是, 2:虽然是, 但是却是其他单位下有</p>
	 * @param loginName
	 * @return
	 *
	 */
	Map<String, String> isOffJobUser(String loginName, String unitId);
	
	/**
	 * 新增用户时, 恢复本单位已离职用户
	 * 
	 * <p>描述: 新增用户时, 恢复本单位已离职用户</p>
	 * @param user
	 * @param orgId
	 *
	 */
	void resumeUser(User user, String orgId);

	/**
	 * 根据用户id和单位id 获取对应的资源
	 * @param userId 用户id
	 * @param unitId 单位id
	 * @return
     */
	List<Resource> getResourcesByUserId(String userId,String unitId);

	void unlockUser(String id);

	void lockUser(String id);
}
