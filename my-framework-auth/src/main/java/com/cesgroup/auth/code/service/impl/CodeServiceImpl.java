package com.cesgroup.auth.code.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.auth.code.dao.CodeDao;
import com.cesgroup.auth.code.entity.Code;
import com.cesgroup.auth.code.service.CodeService;
import com.cesgroup.common.global.Constants;
import com.cesgroup.core.service.impl.BaseServiceImpl;

/**
 * 编码服务实现类.
 * <p>描述:编码服务实现类</p>
 * <p>Company:上海中信信息发展股份有限公司</p>
 * @author 管俊 guan.jun@cesgroup.com.cn
 * @date 2016年4月21日 上午11:12:29
 */
@Service
@Transactional
public class CodeServiceImpl extends BaseServiceImpl<Code, CodeDao> implements CodeService {

	//依赖注入开始

	@Override
	@Autowired
	public void setDao(CodeDao codeDao) {
		super.dao = codeDao;
	}

	//依赖注入结束

	@Override
	public void updateStatus(String ids, String op) {
		if(StringUtils.isNotEmpty(ids) && StringUtils.isNotEmpty(op)){
			String status = "start".equals(op) ? Constants.Code.IN_USE : Constants.Code.NO_USE;
			String[] idsArray = ids.split(",");
			for (String id : idsArray) {


				List<Code> codes = this.getAllChildCodeById(new ArrayList<Code>(),id);

				for (Code code : codes) {
					getDao().updateStatus(code.getId(), status);
				}
				getDao().updateStatus(id, status);
			}
		}
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<Code> getChildren(String pId, String unitId) {
		if(StringUtils.isNotEmpty(pId) && StringUtils.isNotEmpty(unitId)){
			List<String> unitIds = new ArrayList<String>();
			unitIds.add(Constants.User.SUPER_UNITID);
			unitIds.add(unitId);

			List<Code> codes = getDao().getByPIdAndUnitIds(pId, unitIds);
			for (Code code : codes) {
				code.setIsParent(hasChild(code.getId(),unitIds));
			}

			return codes;

		}
		return null;
	}

	@Override
	public List<Code> getByUnitIds(String unitId){
		if(StringUtils.isNotEmpty(unitId)){
			List<String> unitIds = new ArrayList<String>();
			unitIds.add(Constants.User.SUPER_UNITID);
			unitIds.add(unitId);

			List<Code> codes = getDao().getByUnitIds(unitIds);

			return codes;

		}
		return null;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	private boolean hasChild(String id,List<String> unitIds){
		Integer count = getDao().getCountByPIdAndUnitIds(id,unitIds);
		if(count > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Code getRootNode() {
		return getDao().findOne(Constants.Code.ROOT_NODE);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<Map<String, Object>> getComboboxDataByCode(String parentCode) {
		List<Code> codes = getDao().getByParentCode(parentCode);
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String,Object> map = null;
		for (Code code : codes) {
			map = new HashMap<String, Object>();
			map.put("text",code.getName());
			map.put("value",code.getCode());
			if(result.size() == 0){
				map.put("selected",true);
			}
			result.add(map);
		}
		return result;
	}

	@Override
	public List<Code> getAll() {
		return getDao().getAll();
	}

	@Override
	public void delete(String ids) {
		if(StringUtils.isNotEmpty(ids)){
			String[] idsArray = ids.split(",");
			for (String id : idsArray) {
				Code entity = getOneById(id);
				if(Constants.Common.YES.equals(entity.getIsSystem())){
					throw new RuntimeException("不能删除系统内置数据");
				}

				List<Code> codes = this.getAllChildCodeById(new ArrayList<Code>(),id);
				getDao().delete(id);
				getDao().delete(codes);
			}
		}
	}


	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public List<Code> getAllChildCodeById(List<Code> allCodes, String id){
		List<Code> childCodes = getDao().getByPId(id);

		for (Code code : childCodes) {
			allCodes.add(code);
			allCodes = this.getAllChildCodeById(allCodes, code.getId());
		}
		return allCodes;
	}

	@Override
	public void moveCode(String codeIds, String newPId, String newParentCode) {
		if(StringUtils.isNotEmpty(codeIds) && newPId != null){
			if(StringUtils.isEmpty(newParentCode)){
				Code code = getOneById(newPId);
				newParentCode = code.getCode();
			}
			String[] codeIdsArray = codeIds.split(",");

			for (String codeId : codeIdsArray) {
				getDao().moveCode(codeId,newPId,newParentCode);
			}
		}
	}

}
