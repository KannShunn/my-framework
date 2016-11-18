package com.cesgroup.common.global;


/**
 * 
 * 系统管理平台 - 常量类
 * <p>描述:系统中所有的常量都集中在这里</p>
 */
public interface Constants {

	/**
	 * 组织模块常量
	 * @author leon
	 *
	 */
	public interface Org{
		/** 根组织id */
		public final static String TOP = "-1";
		/** 部门类型 */
		public final static String DEPARTMENT_TYPE = "2";
		/** 单位类型 */
		public final static String UNIT_TYPE = "1";
		/** 在使用的组织的状态 */
		public final static String IN_USE = "0";
		/** 不使用(删除)的组织的状态 */
		public final static String NO_USE = "1";
	}
	
	/**
	 * 用户模块常量
	 * @author leon
	 *
	 */
	public interface User{
		/** 超级管理员的id */
		public static final String SUPERADMIN_ID = "1";
		/** 默认密码  = 000000 */
		public static final String DEFAULT_PASSWORD = "000000";
		
		/** 专职  = 0 */
		public static final String FULLTIME = "0";
		/** 兼职  = 1*/
		public static final String PARTTIME = "1";
		
		/** 在职  = 0*/
		public static final String ONJOB = "0";
		/** 离职  = 1*/
		public static final String OFFJOB = "1";
		/** 是管理员 */
		public static final String IS_ADMIN = "0";
		/** 不是管理员 */
		public static final String NOT_ADMIN = "1";
		/** 超级管理员的单位id = -1 */
		public static final String SUPER_UNITID = "-1";
		/** 未锁定 */
		public static final String UNLOCK = "0";
		/** 已锁定 */
		public static final String LOCKED = "1";
		/** 临时授权*/
		public static final String IS_TEMP_ACCREDIT_YES = "0";
		/** 不是临时授权 */
		public static final String IS_TEMP_ACCREDIT_NO = "1";
		
	}
	
	/**
	 * 角色模块常量
	 * @author leon
	 *
	 */
	public interface Role{
		/** 超级管理员角色的id = 1 */
		public static final String SUPER_ROLE = "1";
		/** 默认角色的id = 2 */
		public static final String DEFAULT_ROLE = "2";
		/** 单位系统管理员角色的id = 3 */
		public static final String UNITSYS_ROLE = "3";
		
	}
	
	/**
	 * 角色分类模块常量
	 * @author leon
	 *
	 */
	public interface Roleclassifcation{
		
	}
	
	/**
	 * 资源常量
	 * @author leon
	 *
	 */
	public interface Resource{

		/** 按钮类型 */
		public static final String BUTTON_TYPE = "0";
		/** 菜单类型 */
		public static final String MENU_TYPE = "1";
		/** 系统类型 */
		public static final String SYS_TYPE = "2";
		/** url类型 */
		public static final String URL_TYPE = "3";
	}

	
	/**
	 * 树节点常量
	 * @author leon
	 *
	 */
	public interface TreeNode{
		/** 系统节点 */
		public static final String SYSTEM_NODE = "system";
		/** 资源节点  */
		public static final String RESOURCE_NODE = "resource";
		/** 角色的节点 */
		public static final String ROLECLASSIFCATION_NODE = "roleclassification";
		/** 角色分类的节点 */
		public static final String ROLE_NODE = "role";
		/** 角色的节点 */
		public static final String ORG_NODE = "org";
		/** 角色分类的节点 */
		public static final String USER_NODE = "user";
	}

	
	/**
	 * 图片常量
	 *
	 */
	public interface Image{
		/** 图片宽 */
		public final static int WIDTH = 80;
		/** 图片高 */
		public final static int HEIGHT = 80;
		
	}
	
	/**
	 * 编码常量
	 *
	 */
	public interface Code{

		/** 组织类型 */
		public final static String ORG_TYPE = "orgType";
		/** 有效 */
		public final static String IN_USE = "0";
		/** 无效 */
		public final static String NO_USE = "1";
		/** 是系统内置 */
		public final static String IS_SYSTEM = "0";
		/** 不是系统内置 */
		public final static String NOT_SYSTEM = "1";
		/** 根结点id */
		public final static String ROOT_NODE = "-1";
	}

	/**
	 * 日志常量
	 *
	 */
	public interface Log{

		/** 登录成功 */
		public final static String SUCCESS = "0";
		/** 登录失败 */
		public final static String FAILED = "1";
		
	}
	
	/**
	 * 公共常量
	 *
	 */
	public interface Common{

		/** 使用中 */
		public final static String ON_USE = "0";
		/** 不使用 */
		public final static String NO_USE = "1";

		/** 是 */
		public final static String YES = "0";
		/** 否 */
		public final static String NO = "1";
	}
	
}
