
create database my_framework default charset utf8 collate utf8_unicode_ci;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_auth_code
-- ----------------------------
DROP TABLE IF EXISTS my_framework.`t_auth_code`;
CREATE TABLE my_framework.`t_auth_code` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(150) NOT NULL,
  `CODE` varchar(150) NOT NULL,
  `SHOW_ORDER` decimal(10,0) DEFAULT NULL,
  `COMMENTS` varchar(500) DEFAULT NULL,
  `UNIT_ID` varchar(32) NOT NULL,
  `STATUS` char(1) NOT NULL,
  `IS_SYSTEM` char(1) NOT NULL,
  `CREATE_USER_ID` varchar(32) DEFAULT NULL,
  `CREATE_USER_NAME` varchar(150) DEFAULT NULL,
  `CREATE_TIME` varchar(20) DEFAULT NULL,
  `UPDATE_USER_ID` varchar(32) DEFAULT NULL,
  `UPDATE_USER_NAME` varchar(150) DEFAULT NULL,
  `UPDATE_TIME` varchar(20) DEFAULT NULL,
  `PARENT_ID` varchar(32) DEFAULT NULL,
  `PARENT_CODE` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ;

-- ----------------------------
-- Records of t_auth_code
-- ----------------------------
INSERT INTO my_framework.`t_auth_code` VALUES ('-1', '编码管理', 'bmgl', '1', '编码管理', '-1', '0', '0', null, null, null, null, null, null, '-2', null);
INSERT INTO my_framework.`t_auth_code` VALUES ('1', '组织类型', 'orgType', '2', '组织类型', '-1', '0', '0', null, null, null, null, null, null, '-1', 'bmgl');
INSERT INTO my_framework.`t_auth_code` VALUES ('2', '单位', '1', '3', '单位', '-1', '0', '0', null, null, null, null, null, null, '1', 'orgType');
INSERT INTO my_framework.`t_auth_code` VALUES ('3', '部门', '2', '4', '部门', '-1', '0', '0', null, null, null, null, null, null, '1', 'orgType');
INSERT INTO my_framework.`t_auth_code` VALUES ('4', '性别', 'sexData', '5', '性别', '-1', '0', '0', null, null, null, null, null, null, '-1', 'bmgl');
INSERT INTO my_framework.`t_auth_code` VALUES ('5', '男', '1', '6', '男', '-1', '0', '0', null, null, null, null, null, null, '4', 'sexData');
INSERT INTO my_framework.`t_auth_code` VALUES ('6', '女', '0', '7', '女', '-1', '0', '0', null, null, null, null, null, null, '4', 'sexData');
INSERT INTO my_framework.`t_auth_code` VALUES ('7', '资源类型', 'resType', '8', '资源类型', '-1', '0', '0', null, null, null, null, null, null, '-1', 'bmgl');
INSERT INTO my_framework.`t_auth_code` VALUES ('8', '按钮', '0', '9', '按钮', '-1', '0', '0', null, null, null, null, null, null, '7', 'resType');
INSERT INTO my_framework.`t_auth_code` VALUES ('9', '菜单', '1', '10', '菜单', '-1', '0', '0', null, null, null, null, null, null, '7', 'resType');
INSERT INTO my_framework.`t_auth_code` VALUES ('10', '系统', '2', '11', '系统', '-1', '0', '0', null, null, null, null, null, null, '7', 'resType');
INSERT INTO my_framework.`t_auth_code` VALUES ('11', '是或否', 'yesOrNo', '12', '是或否', '-1', '0', '0', null, null, null, null, null, null, '-1', 'bmgl');
INSERT INTO my_framework.`t_auth_code` VALUES ('12', '是', '0', '14', '是', '-1', '0', '0', null, null, null, null, null, null, '11', 'yesOrNo');
INSERT INTO my_framework.`t_auth_code` VALUES ('13', '否', '1', '13', '否', '-1', '0', '0', null, null, null, null, null, null, '11', 'yesOrNo');
INSERT INTO my_framework.`t_auth_code` VALUES ('14', '政治面貌', 'politicalData', '15', '政治面貌', '-1', '0', '0', null, null, null, null, null, null, '-1', 'bmgl');
INSERT INTO my_framework.`t_auth_code` VALUES ('15', '中共党员', '0', '18', '中共党员', '-1', '0', '0', null, null, null, null, null, null, '14', 'politicalData');
INSERT INTO my_framework.`t_auth_code` VALUES ('16', '共青团员', '1', '17', '共青团员', '-1', '0', '0', null, null, null, null, null, null, '14', 'politicalData');
INSERT INTO my_framework.`t_auth_code` VALUES ('17', '群众', '2', '16', '群众', '-1', '0', '0', null, null, null, null, null, null, '14', 'politicalData');
INSERT INTO my_framework.`t_auth_code` VALUES ('18', 'url', '3', '10', 'url', '-1', '0', '0', null, null, null, null, null, null, '7', 'resType');

-- ----------------------------
-- Table structure for t_auth_generator
-- ----------------------------
DROP TABLE IF EXISTS my_framework.`t_auth_generator`;
CREATE TABLE my_framework.`t_auth_generator` (
  `ID` varchar(50) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `PREFIX` varchar(20) DEFAULT NULL,
  `SYS_NUMBER` decimal(10,0) DEFAULT NULL,
  `SUFFIX` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ;

-- ----------------------------
-- Records of t_auth_generator
-- ----------------------------
INSERT INTO my_framework.`t_auth_generator` VALUES ('1', '工号生成规则', 'A', '10000', '');

-- ----------------------------
-- Table structure for t_auth_id_sequence
-- ----------------------------
DROP TABLE IF EXISTS my_framework.`t_auth_id_sequence`;
CREATE TABLE my_framework.`t_auth_id_sequence` (
  `ID_NAME` varchar(50) DEFAULT NULL,
  `MAXVAL` decimal(10,0) DEFAULT NULL
) ;

-- ----------------------------
-- Records of t_auth_id_sequence
-- ----------------------------

-- ----------------------------
-- Table structure for t_auth_login_failed_log
-- ----------------------------
DROP TABLE IF EXISTS my_framework.`t_auth_login_failed_log`;
CREATE TABLE my_framework.`t_auth_login_failed_log` (
  `ID` varchar(32) NOT NULL,
  `LOGIN_NAME` varchar(150) DEFAULT NULL,
  `LOGIN_IP` varchar(30) DEFAULT NULL,
  `LOGIN_FAILED_TIME` varchar(20) DEFAULT NULL
) ;

-- ----------------------------
-- Records of t_auth_login_failed_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_auth_operatelog
-- ----------------------------
DROP TABLE IF EXISTS my_framework.`t_auth_operatelog`;
CREATE TABLE my_framework.`t_auth_operatelog` (
  `ID` varchar(32) NOT NULL,
  `TYPE` varchar(200) DEFAULT NULL,
  `USER_ID` varchar(32) NOT NULL,
  `USER_NAME` varchar(150) NOT NULL,
  `LOG_DATE` varchar(20) DEFAULT NULL,
  `OPERATE` varchar(200) DEFAULT NULL,
  `MESSAGE` varchar(2000) DEFAULT NULL,
  `APPKEY` varchar(50) DEFAULT NULL,
  `URL` varchar(500) DEFAULT NULL,
  `NOTE` varchar(200) DEFAULT NULL,
  `UNIT_ID` varchar(32) NOT NULL,
  PRIMARY KEY (`ID`)
) ;

-- ----------------------------
-- Records of t_auth_operatelog
-- ----------------------------

-- ----------------------------
-- Table structure for t_auth_org
-- ----------------------------
DROP TABLE IF EXISTS my_framework.`t_auth_org`;
CREATE TABLE my_framework.`t_auth_org` (
  `ORGANIZE_ID` varchar(32) NOT NULL,
  `PARENT_ID` varchar(32) DEFAULT NULL,
  `ORGANIZE_NAME` varchar(150) NOT NULL,
  `COMMENTS` varchar(500) DEFAULT NULL,
  `SHOW_ORDER` decimal(10,0) DEFAULT NULL,
  `ABBREVIATION` varchar(150) DEFAULT NULL,
  `ORGANIZE_CODE` varchar(150) NOT NULL,
  `FLAG1` varchar(200) DEFAULT NULL,
  `FLAG2` varchar(200) DEFAULT NULL,
  `FLAG3` varchar(200) DEFAULT NULL,
  `FLAG4` varchar(200) DEFAULT NULL,
  `FLAG5` varchar(200) DEFAULT NULL,
  `FLAG6` varchar(200) DEFAULT NULL,
  `FLAG7` varchar(200) DEFAULT NULL,
  `FLAG8` varchar(200) DEFAULT NULL,
  `FLAG9` varchar(200) DEFAULT NULL,
  `FLAG10` varchar(200) DEFAULT NULL,
  `ORGANIZE_TYPE_ID` decimal(1,0) NOT NULL,
  `STATUS` char(1) NOT NULL,
  `UNIT_ID` varchar(32) NOT NULL,
  `CREATE_USER_ID` varchar(32) DEFAULT NULL,
  `CREATE_USER_NAME` varchar(150) DEFAULT NULL,
  `CREATE_TIME` varchar(20) DEFAULT NULL,
  `UPDATE_USER_ID` varchar(32) DEFAULT NULL,
  `UPDATE_USER_NAME` varchar(150) DEFAULT NULL,
  `UPDATE_TIME` varchar(20) DEFAULT NULL,
  `IS_SYSTEM` char(1) NOT NULL,
  PRIMARY KEY (`ORGANIZE_ID`)
) ;

-- ----------------------------
-- Records of t_auth_org
-- ----------------------------
INSERT INTO my_framework.`t_auth_org` VALUES ('-1', null, '组织机构', '组织根结点', '1', null, '-1', null, null, null, null, null, null, null, null, null, null, '-1', '0', '-1', null, null, null, null, null, null, '0');

-- ----------------------------
-- Table structure for t_auth_org_user
-- ----------------------------
DROP TABLE IF EXISTS my_framework.`t_auth_org_user`;
CREATE TABLE my_framework.`t_auth_org_user` (
  `USER_ID` varchar(32) NOT NULL,
  `ORGANIZE_ID` varchar(32) NOT NULL,
  `USER_SHOWORDER` decimal(10,0) DEFAULT NULL,
  `USERTYPE` char(1) NOT NULL,
  `ORG_USER_ID` varchar(32) NOT NULL,
  `UNIT_ID` varchar(32) NOT NULL,
  PRIMARY KEY (`ORG_USER_ID`)
) ;

-- ----------------------------
-- Records of t_auth_org_user
-- ----------------------------

-- ----------------------------
-- Table structure for t_auth_resource
-- ----------------------------
DROP TABLE IF EXISTS my_framework.`t_auth_resource`;
CREATE TABLE my_framework.`t_auth_resource` (
  `RESOURCE_ID` varchar(32) NOT NULL,
  `PARENT_ID` varchar(32) DEFAULT NULL,
  `URL` varchar(200) NULL,
  `NAME` varchar(150) NOT NULL,
  `COMMENTS` varchar(500) DEFAULT NULL,
  `NAVIGATE_URL` varchar(300) DEFAULT NULL,
  `BUSINESS_URL` varchar(300) DEFAULT NULL,
  `OTHER_URL` varchar(300) DEFAULT NULL,
  `USE_FUNCTION` varchar(300) DEFAULT NULL,
  `RESOURCE_IMG` varchar(300) DEFAULT NULL,
  `RESOURCEKEY` varchar(500) DEFAULT NULL,
  `SHOW_ORDER` decimal(10,0) DEFAULT NULL,
  `RES_TYPE` char(1) NOT NULL,
  `MODULE_ID` varchar(200) DEFAULT NULL,
  `SOURCE_FILE` varchar(200) DEFAULT NULL,
  `CREATE_USER_ID` varchar(32) DEFAULT NULL,
  `CREATE_USER_NAME` varchar(150) DEFAULT NULL,
  `CREATE_TIME` varchar(20) DEFAULT NULL,
  `UPDATE_USER_ID` varchar(32) DEFAULT NULL,
  `UPDATE_USER_NAME` varchar(150) DEFAULT NULL,
  `UPDATE_TIME` varchar(20) DEFAULT NULL,
  `IS_SYSTEM` char(1) NOT NULL,
  `IS_DEFAULT` char(10) NOT NULL,
  PRIMARY KEY (`RESOURCE_ID`)
) ;

-- ----------------------------
-- Records of t_auth_resource
-- ----------------------------
INSERT INTO my_framework.`t_auth_resource` VALUES ('-1', null, '', '资源信息', null, null, null, null, null, null, '<NULL>', '0', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('3', '1', '/auth/org', '组织管理', null, null, null, null, '<NULL>', '<NULL>', '<NULL>', '3', '1', '<NULL>', null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('4', '1', '/auth/resource', '资源管理', null, null, null, null, '<NULL>', '<NULL>', '<NULL>', '4', '1', '<NULL>', null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('5', '1', '/auth/role', '角色管理', null, null, null, null, '<NULL>', '<NULL>', '<NULL>', '5', '1', '<NULL>', null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('6', '12', '/auth/log/sysLog', '登录日志', null, null, null, null, '<NULL>', '<NULL>', '<NULL>', '6', '1', '<NULL>', null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('7', '12', '/auth/log/operateLog', '操作日志', null, null, null, null, '<NULL>', '<NULL>', '<NULL>', '7', '1', '<NULL>', null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('8', '1', '/auth/code', '编码维护', null, null, null, null, '<NULL>', '<NULL>', '<NULL>', '8', '1', '<NULL>', null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('9', '1', '/auth/unitChange', '单位调度', null, null, null, null, '<NULL>', null, '<NULL>', '9', '1', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('10', '1', '/auth/roleclassification', '角色分类管理', null, null, null, null, '<NULL>', null, '<NULL>', '10', '1', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('2', '1', '/auth/user', '用户管理', null, null, null, null, '<NULL>', '<NULL>', '<NULL>', '2', '1', '<NULL>', null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('12', '1', '/auth/log', '日志管理', null, null, null, null, null, null, null, '12', '1', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('13', '1', '/auth/config', '配置管理', null, null, null, null, null, null, null, '13', '1', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('1', '-1', '/auth', '系统管理', null, null, null, null, null, null, null, '1', '2', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('19', '2', '/auth/user/changeOrg', '改变组织', null, null, null, null, null, null, null, '19', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('20', '2', '/auth/user/partTimeJob', '兼职', null, null, null, null, null, null, null, '20', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('21', '2', '/auth/user/removePartTimeJob', '撤销兼职', null, null, null, null, null, null, null, '21', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('22', '2', '/auth/user/initPassword', '初始化密码', null, null, null, null, null, null, null, '22', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('23', '2', '/auth/user/changePassword', '修改密码', null, null, null, null, null, null, null, '23', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('14', '2', '/auth/user/getUserPage', '列表', null, null, null, null, null, null, null, '14', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('15', '2', '/auth/user/create', '新增', null, null, null, null, null, null, null, '15', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('16', '2', '/auth/user/update', '修改', null, null, null, null, null, null, null, '16', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('17', '2', '/auth/user/delete', '删除', null, null, null, null, null, null, null, '17', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('18', '2', '/auth/user/accreditRole', '授予角色', null, null, null, null, null, null, null, '18', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('24', '2', '/auth/user/renewOldToNew', '恢复用户', null, null, null, null, null, null, null, '24', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('25', '3', '/auth/org/create', '新增', null, null, null, null, null, null, null, '25', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('26', '3', '/auth/org/update', '修改', null, null, null, null, null, null, null, '26', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('27', '3', '/auth/org/delete', '删除', null, null, null, null, null, null, null, '27', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('28', '3', '/auth/org/moveOrg', '移动组织', null, null, null, null, null, null, null, '28', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('29', '3', '/auth/org/mergeOrg', '组织合并', null, null, null, null, null, null, null, '29', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('30', '3', '/auth/org/page', '列表', null, null, null, null, null, null, null, '30', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('31', '5', '/auth/role/page', '列表', null, null, null, null, null, null, null, '31', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('32', '5', '/auth/role/create', '新增', null, null, null, null, null, null, null, '32', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('33', '5', '/auth/role/update', '修改', null, null, null, null, null, null, null, '33', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('34', '5', '/auth/role/delete', '删除', null, null, null, null, null, null, null, '34', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('35', '5', '/auth/role/grantResource', '授予资源', null, null, null, null, null, null, null, '35', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('36', '5', '/auth/role/grantUser', '授予用户', null, null, null, null, null, null, null, '36', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('37', '4', '/auth/resource/page', '列表', null, null, null, null, null, null, null, '37', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('38', '4', '/auth/resource/create', '新增', null, null, null, null, null, null, null, '38', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('39', '4', '/auth/resource/update', '修改', null, null, null, null, null, null, null, '39', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('40', '4', '/auth/resource/delete', '删除', null, null, null, null, null, null, null, '40', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('41', '4', '/auth/resource/moveResource', '移动资源', null, null, null, null, null, null, null, '41', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('42', '6', '/auth/log/sysLog/page', '列表', null, null, null, null, null, null, null, '42', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('43', '6', '/auth/log/sysLog/export', '导出日志', null, null, null, null, null, null, null, '43', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('44', '7', '/auth/log/operateLog/page', '列表', null, null, null, null, null, null, null, '44', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('45', '7', '/auth/log/operateLog/export', '导出日志', null, null, null, null, null, null, null, '45', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('46', '8', '/auth/code/page', '列表', null, null, null, null, null, null, null, '46', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('47', '8', '/auth/code/create', '新增', null, null, null, null, null, null, null, '47', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('48', '8', '/auth/code/update', '修改', null, null, null, null, null, null, null, '48', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('49', '8', '/auth/code/delete', '删除', null, null, null, null, null, null, null, '49', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('50', '8', '/auth/code/updateStatus', '启用停用', null, null, null, null, null, null, null, '50', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('51', '9', '/auth/unitChange/getUserPage', '列表', null, null, null, null, null, null, null, '51', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('52', '9', '/auth/unitChange/changeOrg', '改变组织', null, null, null, null, null, null, null, '52', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('53', '9', '/auth/unitChange/partTimeJob', '兼职', null, null, null, null, null, null, null, '53', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('54', '10', '/auth/roleclassification/page', '列表', null, null, null, null, null, null, null, '54', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('55', '10', '/auth/roleclassification/create', '新增', null, null, null, null, null, null, null, '55', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('56', '10', '/auth/roleclassification/update', '修改', null, null, null, null, null, null, null, '56', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('57', '10', '/auth/roleclassification/delete', '删除', null, null, null, null, null, null, null, '57', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('59', '8', '/auth/code/getAsyncCodeTree', '加载左侧树', null, null, null, null, null, null, null, '59', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('61', '8', '/auth/code/load', '加载对象', null, null, null, null, null, null, null, '61', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('62', '3', '/auth/org/getLeftOrgSyncTree', '加载左侧树', null, null, null, null, null, null, null, '62', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('64', '3', '/auth/org/load', '加载对象', null, null, null, null, null, null, null, '64', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('65', '4', '/auth/resource/load', '加载对象', null, null, null, null, null, null, null, '65', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('67', '4', '/auth/resource/loadMoveResourceTree', '加载移动资源树', null, null, null, null, null, null, null, '67', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('68', '4', '/auth/resource/getLeftSyncResourceTree', '加载左侧树', null, null, null, null, null, null, null, '68', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('69', '5', '/auth/role/load', '加载对象', null, null, null, null, null, null, null, '69', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('70', '5', '/auth/role/loadOrgUserTree', '加载授予用户树', null, null, null, null, null, null, null, '70', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('71', '5', '/auth/role/loadRoleResourceTree', '加载授予资源树', null, null, null, null, null, null, null, '71', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('72', '5', '/auth/role/getLeftRoleClassificationSyncTree', '加载左侧树', null, null, null, null, null, null, null, '72', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('73', '10', '/auth/roleclassification/getLeftRoleClassificationSyncTree', '加载左侧树', null, null, null, null, null, null, null, '73', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('74', '10', '/auth/roleclassification/load', '加载对象', null, null, null, null, null, null, null, '74', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('75', '2', '/auth/user/updateOldUser', '修改老用户', null, null, null, null, null, null, null, '75', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('76', '2', '/auth/user/resumeUser', '恢复用户(新增时)', null, null, null, null, null, null, null, '76', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('77', '2', '/auth/user/getAccreditRoleTree', '加载授予角色树', null, null, null, null, null, null, null, '77', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('78', '2', '/auth/user/upload', '上传', null, null, null, null, null, null, null, '78', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('79', '2', '/auth/user/getLeftOrgSyncTree', '加载左侧树', null, null, null, null, null, null, null, '79', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('80', '9', '/auth/unitChange/getUnitChangeTree', '加载左侧树', null, null, null, null, null, null, null, '80', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('81', '2', '/auth/user/load', '加载对象', null, null, null, null, null, null, null, '81', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('82', '2', '/auth/user/page', '老用户列表', null, null, null, null, null, null, null, '82', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('83', '2', '/auth/user/unlockUser', '解锁', null, null, null, null, null, null, null, '83', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('84', '2', '/auth/user/openLookRoleDialog', '查看角色', null, null, null, null, null, null, null, '84', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('85', '2', '/auth/user/openLookOrgDialog', '查看部门', null, null, null, null, null, null, null, '85', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('86', '2', '/auth/user/lockUser', '锁定', null, null, null, null, null, null, null, '86', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('87', '9', '/auth/unitChange/getAllOrgTree', '加载全组织树', null, null, null, null, null, null, null, '87', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('88', '-1', '/auth/user/changeSelfPassword', '修改个人密码', null, null, null, null, null, null, null, '88', '0', null, null, null, null, null, null, null, null, '0', '0');
INSERT INTO my_framework.`t_auth_resource` VALUES ('89', '-1', '/auth/user/update', '修改个人资料', null, null, null, null, null, null, null, '89', '0', null, null, null, null, null, null, null, null, '0', '0');
INSERT INTO my_framework.`t_auth_resource` VALUES ('90', '1', '/auth/usergroup', '用户组管理', null, null, null, null, null, null, null, '90', '1', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('91', '90', '/auth/usergroup/page', '列表', null, null, null, null, null, null, null, '91', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('92', '90', '/auth/usergroup/create', '新增', null, null, null, null, null, null, null, '92', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('93', '90', '/auth/usergroup/update', '修改', null, null, null, null, null, null, null, '93', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('94', '90', '/auth/usergroup/delete', '删除', null, null, null, null, null, null, null, '94', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('95', '90', '/auth/usergroup/getUserPage', '用户列表', null, null, null, null, null, null, null, '95', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('96', '90', '/auth/usergroup/addUser', '添加用户', null, null, null, null, null, null, null, '96', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('97', '90', '/auth/usergroup/removeUser', '移除用户', null, null, null, null, null, null, null, '97', '0', null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO my_framework.`t_auth_resource` VALUES ('98', '-1', '/auth/user/load', '加载个人资料', null, null, null, null, null, null, null, '98', '0', null, null, null, null, null, null, null, null, '0', '0');
INSERT INTO my_framework.`t_auth_resource` VALUES ('99', '-1', '/auth/user/upload', '上传个人头像', null, null, null, null, null, null, null, '99', '0', null, null, null, null, null, null, null, null, '0', '0');
INSERT INTO my_framework.`t_auth_resource` VALUES ('100', '8', '/auth/code/moveCode', '移动编码', null, null, null, null, null, null, null, '100', '0', null, null, null, null, null, null, null, null, '0', '1');

-- ----------------------------
-- Table structure for t_auth_role
-- ----------------------------
DROP TABLE IF EXISTS my_framework.`t_auth_role`;
CREATE TABLE my_framework.`t_auth_role` (
  `ROLE_ID` varchar(32) NOT NULL,
  `ROLE_NAME` varchar(150) NOT NULL,
  `COMMENTS` varchar(500) DEFAULT NULL,
  `ROLE_KEY` varchar(100) NOT NULL,
  `SHOW_ORDER` decimal(10,0) DEFAULT NULL,
  `ROLECLASSIFICATION_ID` varchar(32) NOT NULL,
  `UNIT_ID` varchar(32) DEFAULT NULL,
  `CREATE_USER_ID` varchar(32) DEFAULT NULL,
  `CREATE_USER_NAME` varchar(150) DEFAULT NULL,
  `CREATE_TIME` varchar(20) DEFAULT NULL,
  `UPDATE_USER_ID` varchar(32) DEFAULT NULL,
  `UPDATE_USER_NAME` varchar(150) DEFAULT NULL,
  `UPDATE_TIME` varchar(20) DEFAULT NULL,
  `IS_SYSTEM` char(1) NOT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ;

-- ----------------------------
-- Records of t_auth_role
-- ----------------------------
INSERT INTO my_framework.`t_auth_role` VALUES ('1', '超级管理员', null, 'superrole', null, '-1', '-1', null, null, null, null, null, null, '0');
INSERT INTO my_framework.`t_auth_role` VALUES ('3', '单位系统管理员', '内置的单位系统管理员角色，相当于各个单位的超级管理员。不可修改删除。', 'unitsysrole', null, '-1', '-1', null, null, null, null, null, null, '0');
INSERT INTO my_framework.`t_auth_role` VALUES ('2', '默认角色', '新增用户时会默认赋予默认角色, 可配置一些通用的权限。可授予系统管理的资源。不可修改删除。', 'defaultrole', null, '1', '-1', null, null, null, null, null, null, '0');
INSERT INTO my_framework.`t_auth_role` VALUES ('4', '演示角色', '内置的一个空角色，可任由用户处理。可以授予系统管理的资源。不可修改删除。', 'demorole', null, '1', '-1', null, null, null, null, null, null, '0');

-- ----------------------------
-- Table structure for t_auth_roleclassification
-- ----------------------------
DROP TABLE IF EXISTS my_framework.`t_auth_roleclassification`;
CREATE TABLE my_framework.`t_auth_roleclassification` (
  `ROLECLASSIFICATION_ID` varchar(32) NOT NULL,
  `NAME` varchar(150) NOT NULL,
  `PARENT_ID` varchar(32) DEFAULT NULL,
  `ROLECLASS_KEY` varchar(150) NOT NULL,
  `COMMENTS` varchar(500) DEFAULT NULL,
  `SHOW_ORDER` decimal(10,0) DEFAULT NULL,
  `UNIT_ID` varchar(32) NOT NULL,
  `CREATE_USER_ID` varchar(32) DEFAULT NULL,
  `CREATE_USER_NAME` varchar(150) DEFAULT NULL,
  `CREATE_TIME` varchar(20) DEFAULT NULL,
  `UPDATE_USER_ID` varchar(32) DEFAULT NULL,
  `UPDATE_USER_NAME` varchar(150) DEFAULT NULL,
  `UPDATE_TIME` varchar(20) DEFAULT NULL,
  `IS_SYSTEM` char(1) NOT NULL,
  PRIMARY KEY (`ROLECLASSIFICATION_ID`)
) ;

-- ----------------------------
-- Records of t_auth_roleclassification
-- ----------------------------
INSERT INTO my_framework.`t_auth_roleclassification` VALUES ('1', '默认角色分类', '-1', '-1.mrjsfl', '内置的默认角色分类，不可修改删除。但是可以在它的下面建子分类或者角色。', '1', '-1', '<!NULL>', null, null, null, null, null, '0');

-- ----------------------------
-- Table structure for t_auth_role_resource
-- ----------------------------
DROP TABLE IF EXISTS my_framework.`t_auth_role_resource`;
CREATE TABLE my_framework.`t_auth_role_resource` (
  `ROLE_ID` varchar(32) NOT NULL,
  `RESOURCE_ID` varchar(32) NOT NULL,
  `UNIT_ID` varchar(32) NOT NULL,
  `ROLE_RESOURCE_ID` varchar(32) NOT NULL,
  PRIMARY KEY (`ROLE_RESOURCE_ID`)
) ;

-- ----------------------------
-- Records of t_auth_role_resource
-- ----------------------------
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('1', '-1', '-1', '1');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '1', '-1', '2');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '2', '-1', '3');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '14', '-1', '4');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '15', '-1', '5');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '16', '-1', '6');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '17', '-1', '7');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '18', '-1', '8');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '19', '-1', '9');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '20', '-1', '10');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '21', '-1', '11');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '22', '-1', '12');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '23', '-1', '13');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '24', '-1', '14');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '75', '-1', '15');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '76', '-1', '16');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '77', '-1', '17');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '78', '-1', '18');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '79', '-1', '19');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '3', '-1', '20');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '25', '-1', '21');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '26', '-1', '22');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '27', '-1', '23');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '28', '-1', '24');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '29', '-1', '25');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '30', '-1', '26');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '62', '-1', '27');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '64', '-1', '28');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '5', '-1', '29');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '31', '-1', '30');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '32', '-1', '31');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '33', '-1', '32');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '34', '-1', '33');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '35', '-1', '34');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '36', '-1', '35');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '69', '-1', '36');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '70', '-1', '37');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '71', '-1', '38');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '72', '-1', '39');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '8', '-1', '40');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '46', '-1', '41');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '47', '-1', '42');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '48', '-1', '43');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '49', '-1', '44');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '50', '-1', '45');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '59', '-1', '46');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '61', '-1', '47');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '9', '-1', '48');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '51', '-1', '49');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '52', '-1', '50');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '53', '-1', '51');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '80', '-1', '52');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '10', '-1', '53');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '54', '-1', '54');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '55', '-1', '55');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '56', '-1', '56');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '57', '-1', '57');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '73', '-1', '58');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '74', '-1', '59');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '12', '-1', '60');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '6', '-1', '61');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '42', '-1', '62');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '43', '-1', '63');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '7', '-1', '64');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '44', '-1', '65');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '45', '-1', '66');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '81', '-1', '67');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '82', '-1', '68');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '83', '-1', '69');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '84', '-1', '70');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '85', '-1', '71');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '86', '-1', '72');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '87', '-1', '73');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '88', '-1', '74');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '89', '-1', '75');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '90', '-1', '76');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '91', '-1', '77');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '92', '-1', '78');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '93', '-1', '79');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '94', '-1', '80');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '95', '-1', '81');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '96', '-1', '82');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '97', '-1', '83');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '98', '-1', '84');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '99', '-1', '85');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('2', '88', '-1', '86');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('2', '89', '-1', '87');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('2', '98', '-1', '88');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('2', '99', '-1', '89');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('4', '88', '-1', '90');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('4', '89', '-1', '91');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('4', '98', '-1', '92');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('4', '99', '-1', '93');
INSERT INTO my_framework.`t_auth_role_resource` VALUES ('3', '100', '-1', '94');

-- ----------------------------
-- Table structure for t_auth_role_user
-- ----------------------------
DROP TABLE IF EXISTS my_framework.`t_auth_role_user`;
CREATE TABLE my_framework.`t_auth_role_user` (
  `ROLE_ID` varchar(32) NOT NULL,
  `USER_ID` varchar(32) NOT NULL,
  `UNIT_ID` varchar(32) NOT NULL,
  `ROLE_USER_ID` varchar(32) NOT NULL,
  `IS_TEMP_ACCREDIT` char(1) DEFAULT NULL,
  `TEMP_ACCREDIT_DATE_START` varchar(20) DEFAULT NULL,
  `TEMP_ACCREDIT_DATE_END` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ROLE_USER_ID`)
) ;

-- ----------------------------
-- Records of t_auth_role_user
-- ----------------------------
INSERT INTO my_framework.`t_auth_role_user` VALUES ('1', '1', '-1', '100', '1', null, null);

-- ----------------------------
-- Table structure for t_auth_syslog
-- ----------------------------
DROP TABLE IF EXISTS my_framework.`t_auth_syslog`;
CREATE TABLE my_framework.`t_auth_syslog` (
  `ID` varchar(32) NOT NULL,
  `USER_ID` varchar(32) DEFAULT NULL,
  `USER_NAME` varchar(150) DEFAULT NULL,
  `LOG_DATE` varchar(20) DEFAULT NULL,
  `OPERATE` varchar(100) DEFAULT NULL,
  `MESSAGE` varchar(2000) DEFAULT NULL,
  `IP` varchar(60) DEFAULT NULL,
  `MAC` varchar(60) DEFAULT NULL,
  `STATUS` char(1) DEFAULT NULL,
  `UNIT_ID` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ;

-- ----------------------------
-- Records of t_auth_syslog
-- ----------------------------

-- ----------------------------
-- Table structure for t_auth_user
-- ----------------------------
DROP TABLE IF EXISTS my_framework.`t_auth_user`;
CREATE TABLE my_framework.`t_auth_user` (
  `USER_ID` varchar(32) NOT NULL,
  `LOGIN_NAME` varchar(150) NOT NULL,
  `PASSWORD` varchar(40) NOT NULL,
  `USER_NAME` varchar(150) NOT NULL,
  `FLAG_ACTION` char(1) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `MOBILE` varchar(20) DEFAULT NULL,
  `TITLE` varchar(100) DEFAULT NULL,
  `SHOW_ORDER` decimal(10,0) DEFAULT NULL,
  `COMMENTS` varchar(500) DEFAULT NULL,
  `STATION` varchar(100) DEFAULT NULL,
  `STATUS` char(1) NOT NULL,
  `JOB_NO` varchar(150) DEFAULT NULL,
  `LAST_LOGIN_DATE` timestamp NULL DEFAULT NULL,
  `LAST_MODIFY_PSD` timestamp NULL DEFAULT NULL,
  `FLAG1` varchar(200) DEFAULT NULL,
  `FLAG2` varchar(200) DEFAULT NULL,
  `FLAG3` varchar(200) DEFAULT NULL,
  `FLAG4` varchar(200) DEFAULT NULL,
  `FLAG5` varchar(200) DEFAULT NULL,
  `UNIT_ID` varchar(32) NOT NULL,
  `IS_ADMIN` char(1) NOT NULL,
  `FLAG8` varchar(200) DEFAULT NULL,
  `FLAG9` varchar(200) DEFAULT NULL,
  `FLAG10` varchar(200) DEFAULT NULL,
  `TELEPHONE` varchar(50) DEFAULT NULL,
  `ABBR` varchar(100) DEFAULT NULL,
  `CARD_NO` varchar(50) DEFAULT NULL,
  `SEX` char(1) DEFAULT NULL,
  `AGE` decimal(10,6) DEFAULT NULL,
  `BIRTHDAY` timestamp NULL DEFAULT NULL,
  `URLPATH` varchar(200) DEFAULT NULL,
  `FLAG6` varchar(200) DEFAULT NULL,
  `FLAG7` varchar(200) DEFAULT NULL,
  `CREATE_USER_ID` varchar(32) DEFAULT NULL,
  `CREATE_USER_NAME` varchar(150) DEFAULT NULL,
  `CREATE_TIME` varchar(20) DEFAULT NULL,
  `UPDATE_USER_ID` varchar(32) DEFAULT NULL,
  `UPDATE_USER_NAME` varchar(150) DEFAULT NULL,
  `UPDATE_TIME` varchar(20) DEFAULT NULL,
  `ON_JOB_DATE` varchar(20) DEFAULT NULL,
  `OFF_JOB_DATE` varchar(20) DEFAULT NULL,
  `IS_SECOND_ON_JOB` char(1) DEFAULT NULL,
  `POLITICAL` varchar(10) DEFAULT NULL,
  `IS_SYSTEM` char(1) NOT NULL,
  PRIMARY KEY (`USER_ID`)
) ;

-- ----------------------------
-- Records of t_auth_user
-- ----------------------------
INSERT INTO my_framework.`t_auth_user` VALUES ('1', 'superadmin', '670b14728ad9902aecba32e22fa4f6bd', '超级管理员', '0', 'superadmin@cesgroup.com', '12345678999', 'superadmin', '1', null, null, '0', null, null, null, null, null, null, null, null, '-1', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0');

-- ----------------------------
-- Table structure for t_auth_usergroup
-- ----------------------------
DROP TABLE IF EXISTS my_framework.`t_auth_usergroup`;
CREATE TABLE my_framework.`t_auth_usergroup` (
  `ID` varchar(50) NOT NULL,
  `NAME` varchar(150) NOT NULL,
  `USERGROUP_CODE` varchar(150) NOT NULL,
  `SHOW_ORDER` decimal(10,0) DEFAULT NULL,
  `CREATE_USER_ID` varchar(32) DEFAULT NULL,
  `CREATE_USER_NAME` varchar(150) DEFAULT NULL,
  `CREATE_TIME` varchar(20) DEFAULT NULL,
  `UPDATE_USER_ID` varchar(32) DEFAULT NULL,
  `UPDATE_USER_NAME` varchar(150) DEFAULT NULL,
  `UPDATE_TIME` varchar(20) DEFAULT NULL,
  `UNIT_ID` varchar(32) NOT NULL,
  `IS_SYSTEM` char(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ;

-- ----------------------------
-- Records of t_auth_usergroup
-- ----------------------------
INSERT INTO my_framework.`t_auth_usergroup` VALUES ('-1', '用户组', '-1', '0', null, null, null, null, null, null, '-1', '0');

-- ----------------------------
-- Table structure for t_auth_version
-- ----------------------------
DROP TABLE IF EXISTS my_framework.`t_auth_version`;
CREATE TABLE my_framework.`t_auth_version` (
  `VERSION` decimal(10,0) NOT NULL
) ;

-- ----------------------------
-- Records of t_auth_version
-- ----------------------------
INSERT INTO my_framework.`t_auth_version` VALUES ('1000');

-- ----------------------------
-- Table structure for t_user_ug
-- ----------------------------
DROP TABLE IF EXISTS my_framework.`t_user_ug`;
CREATE TABLE my_framework.`t_user_ug` (
  `ID` varchar(32) NOT NULL,
  `USER_ID` varchar(32) NOT NULL,
  `USERGROUP_ID` varchar(32) NOT NULL,
  `UNIT_ID` varchar(32) NOT NULL,
  PRIMARY KEY (`ID`)
) ;

-- ----------------------------
-- Records of t_user_ug
-- ----------------------------
