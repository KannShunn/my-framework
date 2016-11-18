package com.cesgroup.auth.org.service.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.auth.code.entity.Code;
import com.cesgroup.auth.code.service.CodeService;
import com.cesgroup.auth.org.dao.OrgDao;
import com.cesgroup.auth.org.entity.Org;
import com.cesgroup.auth.org.service.OrgCUDListener;
import com.cesgroup.auth.org.service.OrgService;
import com.cesgroup.auth.org.vo.OrgGridVo;
import com.cesgroup.auth.user.dao.OrgUserDao;
import com.cesgroup.auth.user.entity.OrgUser;
import com.cesgroup.auth.user.entity.User;
import com.cesgroup.auth.user.service.UserService;
import com.cesgroup.common.global.Constants;
import com.cesgroup.core.service.impl.BaseServiceImpl;
import com.cesgroup.core.utils.DynamicSpecifications;
import com.cesgroup.core.utils.SearchFilter;
import com.cesgroup.core.utils.SearchFilter.Operator;
import com.cesgroup.core.utils.Util;

@Service
@Transactional
public class OrgServiceImpl extends BaseServiceImpl<Org,OrgDao> implements OrgService{
	
	//依赖注入开始
	@Autowired
	public OrgUserDao orgUserDao;
	@Autowired
	public CodeService codeService;
/*	@Autowired
	public UserService userService;*/
	@Autowired(required = false)
	public OrgCUDListener orgCUDListener;
	@Autowired
	@Override
	public void setDao(OrgDao orgDao) {
		super.dao = orgDao;
	}

	//依赖注入结束


	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<Org> getLeftOrgSyncTree(String unitId) {
		if(unitId == null){
			return null;
		}
		if(Constants.User.SUPER_UNITID.equals(unitId)){
			return getDao().getAll();
		}
		return getByUnitId(unitId);
	}

	/**
	 * 重写getAll, 只查有效的组织
	 * @return
     */
	@Override
	public List<Org> getAll(){
		return getDao().getAll();
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<Org> getByUnitId(String unitId){
		return getDao().getByUnitId(unitId);
	}
	
	@Override
	public Org create(Org entity) {
		entity.setStatus(Constants.Org.IN_USE);
		entity.setIsSystem(Constants.Common.NO);

		//如果新增的是单位, 需要将新增后的组织id同时update到单位id字段上, 否则就查找该组织的直接单位, 为其赋上单位id再新增
		if(Constants.Org.UNIT_TYPE.equals(String.valueOf(entity.getOrganizeTypeId()))){
			entity.setUnitId("-");//由于数据库的非空约束, 此处单位id不能为空, 所以先给予一个无意义的值, 等到插入后有ID了, 再更新成正确的值
			entity = super.create(entity);
			entity.setUnitId(entity.getId());
			return super.update(entity);
		} else {
			entity.setUnitId(getUnitOrgByOrgId(entity.getpId()).getId());
			return super.create(entity);
		}
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Org getUnitOrgByOrgId(String orgId){
		Org org=getDao().findOne(orgId);
		if(!Constants.Org.UNIT_TYPE.equals(org.getOrganizeTypeId()+"")){
			org=getUnitOrgByOrgId(org.getpId());
		}
		return org;
	}

	/**
	 * 级联删除组织和该组织下的所有子孙组织.
	 * 
	 * 删除组织时, 判断组织下有没有用户, 有则不允许删除. 没有则删除.
	 */
	@Override
//	@Transactional
	public void delete(String ids) {
		if(StringUtils.isNotEmpty(ids)){
			List<Org> allChildOrgs = null;
			String[] idsArray = ids.split(",");
			for (String id : idsArray) {
				Org org = getOneById(id);
				if(Constants.Common.YES.equals(org.getIsSystem())){
					throw new RuntimeException("不能删除系统内置数据");
				}
				int userCount = orgUserDao.getCountByOrgId(org.getId());
				if(userCount > 0){
					throw new RuntimeException("["+org.getName()+"]该组织下含有用户, 请先删除该组织下所有用户再删除组织");
				}
				org.setStatus(Constants.Org.NO_USE);
				update(org);

				allChildOrgs = new ArrayList<Org>();
				allChildOrgs = this.getAllChildOrgById(allChildOrgs, id);
				
				for (Org childOrg : allChildOrgs) {
					userCount = orgUserDao.getCountByOrgId(childOrg.getId());
					if(userCount > 0){
						throw new RuntimeException("["+childOrg.getName()+"]该组织下含有用户, 请先删除该组织下所有用户再删除组织");
					}
					childOrg.setStatus(Constants.Org.NO_USE);
					update(childOrg);
				}
				allChildOrgs.add(org);
//				super.updateBatch(allChildOrgs); //这句会报no transaction的错误.其他地方调updateBatch却不报, 原因不知6
			}
		}
	}

//	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<Org> getAllChildOrgById(List<Org> allOrgs, String id){
		List<Org> childOrgs = getDao().getByPId(id);
		
		for (Org org : childOrgs) {
			allOrgs.add(org);
			allOrgs = this.getAllChildOrgById(allOrgs, org.getId());
		}
		return allOrgs;
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<Org> getChildOrgById(String id){
		return getDao().getByPId(id);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<Map<String, Object>> getorganizeTypeIdData(String orgId) {
		List<Map<String,Object>> jsonArray = new ArrayList<Map<String,Object>>();
		Map<String,Object> data = null;
		if(Constants.Org.TOP.equals(orgId)){
			data = new HashMap<String, Object>();
			data.put("text", "单位");
			data.put("value", Constants.Org.UNIT_TYPE);
			jsonArray.add(data);
		}else{
			List<SearchFilter> sfs = new ArrayList<SearchFilter>();
			SearchFilter sf1 = new SearchFilter("parentCode", Operator.EQ, Constants.Code.ORG_TYPE); //value > 1, 即所有非单位类型
			SearchFilter sf2 = new SearchFilter("code", Operator.NEQ, Constants.Org.UNIT_TYPE); //value <> 1, 即所有非单位类型
			sfs.add(sf1);
			sfs.add(sf2);
			
			Specification<Code> spec = DynamicSpecifications.bySearchFilter(sfs, Code.class);
			
			List<Code> codeItems = codeService.getAllByCondition(spec, null);
			for (int i = 0; i < codeItems.size(); i++) {
				data = new HashMap<String, Object>();
				data.put("text", codeItems.get(i).getName());
				data.put("value", codeItems.get(i).getCode());
				jsonArray.add(data);
			}
		}
		return jsonArray;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<Org> getAllUnit() {
		return getDao().getAllUnit();
	}
	

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<OrgGridVo> getOrgByUserId(String userId) {
		StringBuilder jpql = new StringBuilder("select new com.cesgroup.auth.org.vo.OrgGridVo(o.id,o.pId,o.name,o.abbreviation,o.organizeCode,o.organizeTypeId,o.comments,ou.userType,unit.name,unit.organizeCode) from Org o,OrgUser ou,Org unit where o.id = ou.organizeId and o.unitId = unit.id and o.status = "+Constants.Org.IN_USE+" and ou.userId = :userId");
		
		TypedQuery<OrgGridVo> query = entityManager.createQuery(jpql.toString(),OrgGridVo.class);
		query.setParameter("userId", userId);
		
		return query.getResultList();
	}

	@Override
	public void moveOrg(String orgId, String unitId, String targetOrgId, String targetUnitId) {
		if(Util.notNull(orgId,unitId,targetOrgId,targetUnitId)){
			//没有跨单位移动组织, 此时直接更改一下org的pId即可
			if(unitId.equals(targetUnitId)){
				Org org = getOneById(orgId);
				org.setpId(targetOrgId);
				this.update(org);
			}
			//跨单位移动组织, 此时不仅仅要更改org的pId, 还要更改该组织以及其所有子孙组织的单位id, 该组织和子孙组织下所有用户的单位id, 组织和用户间的单位id
			else{
				
				//更新自己的单位id
				Org org = getOneById(orgId);
				org.setpId(targetOrgId);
				org.setUnitId(targetUnitId); 
				this.update(org);
				
				//更新组织和用户关系的单位id
				List<OrgUser> orgUsers = orgUserDao.getByOrgId(orgId);
				for (OrgUser orgUser : orgUsers) {
					orgUser.setUnitId(targetUnitId);
				}
				orgUserDao.save(orgUsers);

				UserService userService = getService(UserService.class);
				//更新该组织下专职用户的直属单位id
				List<User> users = userService.getUsersByOrgId(orgId, Constants.User.FULLTIME);
				for (User user : users) {
					user.setUnitId(targetUnitId);
				}
				userService.updateBatch(users);
				
				//更新子孙组织的单位id
				List<Org> childOrgs = getAllChildOrgById(new ArrayList<Org>(), orgId);
				for (Org childOrg : childOrgs) {
					childOrg.setUnitId(targetUnitId);
					
					//更新子孙组织和用户关系的单位id
					List<OrgUser> childOrgUsers = orgUserDao.getByOrgId(childOrg.getId());
					for (OrgUser orgUser : childOrgUsers) {
						orgUser.setUnitId(targetUnitId);
					}
					orgUserDao.save(childOrgUsers);
					
					//更新子孙组织下专职用户的直属单位id
					List<User> childUsers = userService.getUsersByOrgId(childOrg.getId(), Constants.User.FULLTIME);
					for (User user : childUsers) {
						user.setUnitId(targetUnitId);
					}
					userService.updateBatch(childUsers);
				}
				this.updateBatch(childOrgs);
				
			}
		}else{
			throw new RuntimeException(MessageFormat.format("参数不正确,orgId:{0},unitId:{1},targetOrgId:{2},targetUnitId:{3}", orgId,unitId,targetOrgId,targetUnitId));
		}
	}

	@Override
	public Org mergeOrg(String mergeOrgIds, Org org) {
		//1. 将新组织保存到数据库, 获取id
		//2. 将待合并的组织的直接子组织的父id更新为新组织的id
		//3. 将待合并的组织和用户关系表中的组织id更新为新组织的id
		//4. 删除待合并的组织
		if(Util.notNull(mergeOrgIds,org)){
			org = this.create(org);
			
			String[] mergeOrgIdArray = mergeOrgIds.split(",");
			for (String mergeOrgId : mergeOrgIdArray) {
				//将待合并的组织的直接子组织的父id更新为新组织的id
				List<OrgUser> orgUsers = orgUserDao.getByOrgId(mergeOrgId);
				for (OrgUser orgUser : orgUsers) {
					orgUser.setOrganizeId(org.getId());
				}
				orgUserDao.save(orgUsers);
				
				//将待合并的组织和用户关系表中的组织id更新为新组织的id
				List<Org> childOrgs = this.getChildOrgById(mergeOrgId);
				for (Org childOrg : childOrgs) {
					childOrg.setpId(org.getId());
				}
				this.updateBatch(childOrgs);
				
			}
			this.delete(mergeOrgIds);
			
			return org;
		}else{
			throw new RuntimeException(MessageFormat.format("参数不正确,mergeOrgIds:{0},org:{1},targetOrgId:{2},targetUnitId:{3}", mergeOrgIds,org));
			
		}
	}
	
	
	//增删改事件监听开始
	@Override
	public void onCreateStart(Org entity) {
		if (orgCUDListener != null) {
			orgCUDListener.onCreateStart(entity);
		}
	}

	@Override
	public Org onCreateSuccess(Org entity) {
		if(orgCUDListener != null){
			return orgCUDListener.onCreateSuccess(entity);
		}
		return entity;
	}

	@Override
	public void onCreateError(Exception e, Org entity) {
		if(orgCUDListener != null){
			orgCUDListener.onCreateError(e,entity);
		}
	}

	@Override
	public void onCreateComplete(Org entity) {
		if(orgCUDListener != null){
			orgCUDListener.onCreateComplete(entity);
		}
	}

	@Override
	public void onUpdateStart(Org entity) {
		if(orgCUDListener != null){
			orgCUDListener.onUpdateStart(entity);
		}
	}

	@Override
	public Org onUpdateSuccess(Org entity) {
		if(orgCUDListener != null){
			return orgCUDListener.onUpdateSuccess(entity);
		}
		return entity;
	}

	@Override
	public void onUpdateError(Exception e, Org entity) {
		if(orgCUDListener != null){
			orgCUDListener.onUpdateError(e,entity);
		}
	}

	@Override
	public void onUpdateComplete(Org entity) {
		if(orgCUDListener != null){
			orgCUDListener.onUpdateComplete(entity);
		}
	}

	@Override
	public void onDeleteStart(String ids) {
		if(orgCUDListener != null){
			orgCUDListener.onDeleteStart(ids);
		}
	}

	@Override
	public void onDeleteSuccess(String ids) {
		if(orgCUDListener != null){
			orgCUDListener.onDeleteSuccess(ids);
		}
	}

	@Override
	public void onDeleteError(Exception e, String ids) {
		if(orgCUDListener != null){
			orgCUDListener.onDeleteError(e,ids);
		}
	}

	@Override
	public void onDeleteComplete(String ids) {
		if(orgCUDListener != null){
			orgCUDListener.onDeleteComplete(ids);
		}
	}
	//增删改事件监听结束
}
