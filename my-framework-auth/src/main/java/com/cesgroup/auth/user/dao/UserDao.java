package com.cesgroup.auth.user.dao;

import com.cesgroup.auth.user.entity.User;
import com.cesgroup.auth.user.vo.UserVo;
import com.cesgroup.common.global.Constants;
import com.cesgroup.core.dao.BaseDao;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 用户相关dao
 * 
 * @author niklaus
 *
 */
//@Transactional
public interface UserDao extends BaseDao<User>
{
	
	/**
	 * 根据登录名查询有效的用户对象
	 */
	@Query("select u from User u where upper(u.loginName) = upper(:loginName) and u.status = '0'")
	public User getByLoginName(@Param("loginName") String loginName);

	@Modifying
	@Query("update User set status = '"+ Constants.User.OFFJOB+"',offJobDate = :offJobDate where id = :userId")
	public void deleteByUserId(@Param("userId") String userId,@Param("offJobDate") String offJobDate);

	@Query("select u from User u where exists (select ru.userId from RoleUser ru where ru.userId = u.id and ru.roleId = ?1)")
	public List<User> getUsersOfRole(String roleId);


	@Query(value = "select " +
			" u.USER_ID as userId, " +
			" u.LOGIN_NAME as loginName, " +
			" u.USER_NAME as userName, " +
			" u.EMAIL as email, " +
			" u.MOBILE as mobile, " +
			" u.JOB_NO as jobNo, " +
			" re.URL as resUrl " +
			"from " +
			" t_auth_user u  " +
			" inner join t_auth_role_user ru  " +
			"  on u.USER_ID = ru.USER_ID  " +
			" inner join t_auth_role ro  " +
			"  on ru.ROLE_ID = ro.ROLE_ID  " +
			" inner join t_auth_role_resource rr  " +
			"  on rr.ROLE_ID = ro.ROLE_ID  " +
			" inner join t_auth_resource re  " +
			"  on re.RESOURCE_ID = rr.RESOURCE_ID " +
			"where " +
			" u.STATUS = 0 " +
			" and JOB_NO is not null " +
			" and re.IS_SYSTEM = 1 " +
			" and ro.UNIT_ID in ('-1',:unitId)", nativeQuery = true)
	List<UserVo> getAllUserResources(@Param("unitId") String unitId);
}
