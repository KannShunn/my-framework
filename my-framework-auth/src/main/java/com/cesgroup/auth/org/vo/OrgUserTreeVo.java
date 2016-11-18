package com.cesgroup.auth.org.vo;

import java.util.ArrayList;
import java.util.List;

import com.cesgroup.common.global.Constants;

/**
 * 
 * 用于前台显示一棵角色分类和角色组合的树
 * <p>描述:用于前台显示一棵角色分类和角色组合的树</p>
 * <p>Company:上海中信信息发展股份有限公司</p>
 * @author 管俊 guan.jun@cesgroup.com.cn
 * @date 2016年4月27日 下午1:51:42
 */
public class OrgUserTreeVo {

	/** id */
	private String id;
	
	/** 父id */
	private String pId;
	
	/** 名称 */
	private String name;
	
	/** 节点类型 , role代表是角色节点, roleclassification代表角色分类节点 */
	private String type;
	
	/** 是否显示checkbox属性, 角色分类节点不显示checkbox, 角色节点显示checkbox */
	private boolean nocheck = false;

	/** 是否选中 */
	private boolean checked = false;
	
	/** 是否禁选 */
	private boolean chkDisabled = false;
	
	/** 子节点 */
	private List<OrgUserTreeVo> children = new ArrayList<OrgUserTreeVo>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean getNocheck() {
		return Constants.TreeNode.ORG_NODE.equals(type) ? true : false;
	}

	public void setNocheck(boolean nocheck) {
		this.nocheck = nocheck;
	}

	public boolean getChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean getChkDisabled() {
		return chkDisabled;
	}

	public void setChkDisabled(boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}

	public List<OrgUserTreeVo> getChildren() {
		return children;
	}

	public void setChildren(List<OrgUserTreeVo> children) {
		this.children = children;
	}
	
	
}
