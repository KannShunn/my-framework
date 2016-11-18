package com.cesgroup.auth.code.service;

import com.cesgroup.auth.code.entity.Code;
import com.cesgroup.core.service.BaseService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 
 * 编码服务接口
 * <p>描述:编码服务接口</p>
 */
public interface CodeService extends BaseService<Code> {

	/**
	 * 更改编码的状态
	 * 
	 * @param ids
	 * @param op 只有两种值,start和stop
	 *
	 */
	void updateStatus(String ids, String op);

	/**
	 * 根据 父id和单位id获取子编码
	 * @param pId
	 * @param unitId
     * @return
     */
	List<Code> getChildren(String pId, String unitId);

	/**
	 * 获取该单位+公用的所有编码
	 * @param unitId
	 * @return
     */
	List<Code> getByUnitIds(String unitId);

	/**
	 * 获取根结点
	 * @return
     */
	Code getRootNode();

	/**
	 * 根据code获取下拉框内容
	 * @param parentCode
	 * @return
     */
	List<Map<String, Object>> getComboboxDataByCode(String parentCode);

	/**
	 * 根据id获取下面所有的子code,包括孙子code
	 * @param allCodes
	 * @param id
     * @return
     */
	List<Code> getAllChildCodeById(List<Code> allCodes, String id);

	/**
	 * 移动编码
	 * @param codeIds
	 * @param newPId
	 * @param newParentCode
     */
	void moveCode(String codeIds, String newPId, String newParentCode);
}
