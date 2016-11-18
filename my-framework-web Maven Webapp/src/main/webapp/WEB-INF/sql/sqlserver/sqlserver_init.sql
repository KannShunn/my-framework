

-- ----------------------------
-- Table structure for T_AUTH_CODE
-- ----------------------------
DROP TABLE [dbo].[T_AUTH_CODE]
GO
CREATE TABLE [dbo].[T_AUTH_CODE] (
[ID] varchar(32) NOT NULL ,
[NAME] varchar(150) NOT NULL ,
[CODE] varchar(150) NOT NULL ,
[SHOW_ORDER] decimal(10) NULL ,
[COMMENTS] varchar(500) NULL ,
[UNIT_ID] varchar(32) NOT NULL ,
[STATUS] char(1) NOT NULL ,
[IS_SYSTEM] char(1) NOT NULL ,
[CREATE_USER_ID] varchar(32) NULL ,
[CREATE_USER_NAME] varchar(150) NULL ,
[CREATE_TIME] varchar(20) NULL ,
[UPDATE_USER_ID] varchar(32) NULL ,
[UPDATE_USER_NAME] varchar(150) NULL ,
[UPDATE_TIME] varchar(20) NULL ,
[PARENT_ID] varchar(32) NULL ,
[PARENT_CODE] varchar(150) NULL 
)


GO

-- ----------------------------
-- Records of T_AUTH_CODE
-- ----------------------------
INSERT INTO [dbo].[T_AUTH_CODE] ([ID], [NAME], [CODE], [SHOW_ORDER], [COMMENTS], [UNIT_ID], [STATUS], [IS_SYSTEM], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [PARENT_ID], [PARENT_CODE]) VALUES (N'-1', N'编码管理', N'bmgl', N'1', N'编码管理', N'-1', N'0', N'0', null, null, null, null, null, null, N'-2', null)
GO
GO
INSERT INTO [dbo].[T_AUTH_CODE] ([ID], [NAME], [CODE], [SHOW_ORDER], [COMMENTS], [UNIT_ID], [STATUS], [IS_SYSTEM], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [PARENT_ID], [PARENT_CODE]) VALUES (N'1', N'组织类型', N'orgType', N'2', N'组织类型', N'-1', N'0', N'0', null, null, null, null, null, null, N'-1', N'bmgl')
GO
GO
INSERT INTO [dbo].[T_AUTH_CODE] ([ID], [NAME], [CODE], [SHOW_ORDER], [COMMENTS], [UNIT_ID], [STATUS], [IS_SYSTEM], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [PARENT_ID], [PARENT_CODE]) VALUES (N'2', N'单位', N'1', N'3', N'单位', N'-1', N'0', N'0', null, null, null, null, null, null, N'1', N'orgType')
GO
GO
INSERT INTO [dbo].[T_AUTH_CODE] ([ID], [NAME], [CODE], [SHOW_ORDER], [COMMENTS], [UNIT_ID], [STATUS], [IS_SYSTEM], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [PARENT_ID], [PARENT_CODE]) VALUES (N'3', N'部门', N'2', N'4', N'部门', N'-1', N'0', N'0', null, null, null, null, null, null, N'1', N'orgType')
GO
GO
INSERT INTO [dbo].[T_AUTH_CODE] ([ID], [NAME], [CODE], [SHOW_ORDER], [COMMENTS], [UNIT_ID], [STATUS], [IS_SYSTEM], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [PARENT_ID], [PARENT_CODE]) VALUES (N'4', N'性别', N'sexData', N'5', N'性别', N'-1', N'0', N'0', null, null, null, null, null, null, N'-1', N'bmgl')
GO
GO
INSERT INTO [dbo].[T_AUTH_CODE] ([ID], [NAME], [CODE], [SHOW_ORDER], [COMMENTS], [UNIT_ID], [STATUS], [IS_SYSTEM], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [PARENT_ID], [PARENT_CODE]) VALUES (N'5', N'男', N'1', N'6', N'男', N'-1', N'0', N'0', null, null, null, null, null, null, N'4', N'sexData')
GO
GO
INSERT INTO [dbo].[T_AUTH_CODE] ([ID], [NAME], [CODE], [SHOW_ORDER], [COMMENTS], [UNIT_ID], [STATUS], [IS_SYSTEM], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [PARENT_ID], [PARENT_CODE]) VALUES (N'6', N'女', N'0', N'7', N'女', N'-1', N'0', N'0', null, null, null, null, null, null, N'4', N'sexData')
GO
GO
INSERT INTO [dbo].[T_AUTH_CODE] ([ID], [NAME], [CODE], [SHOW_ORDER], [COMMENTS], [UNIT_ID], [STATUS], [IS_SYSTEM], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [PARENT_ID], [PARENT_CODE]) VALUES (N'7', N'资源类型', N'resType', N'8', N'资源类型', N'-1', N'0', N'0', null, null, null, null, null, null, N'-1', N'bmgl')
GO
GO
INSERT INTO [dbo].[T_AUTH_CODE] ([ID], [NAME], [CODE], [SHOW_ORDER], [COMMENTS], [UNIT_ID], [STATUS], [IS_SYSTEM], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [PARENT_ID], [PARENT_CODE]) VALUES (N'8', N'按钮', N'0', N'9', N'按钮', N'-1', N'0', N'0', null, null, null, null, null, null, N'7', N'resType')
GO
GO
INSERT INTO [dbo].[T_AUTH_CODE] ([ID], [NAME], [CODE], [SHOW_ORDER], [COMMENTS], [UNIT_ID], [STATUS], [IS_SYSTEM], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [PARENT_ID], [PARENT_CODE]) VALUES (N'9', N'菜单', N'1', N'10', N'菜单', N'-1', N'0', N'0', null, null, null, null, null, null, N'7', N'resType')
GO
GO
INSERT INTO [dbo].[T_AUTH_CODE] ([ID], [NAME], [CODE], [SHOW_ORDER], [COMMENTS], [UNIT_ID], [STATUS], [IS_SYSTEM], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [PARENT_ID], [PARENT_CODE]) VALUES (N'10', N'系统', N'2', N'11', N'系统', N'-1', N'0', N'0', null, null, null, null, null, null, N'7', N'resType')
GO
GO
INSERT INTO [dbo].[T_AUTH_CODE] ([ID], [NAME], [CODE], [SHOW_ORDER], [COMMENTS], [UNIT_ID], [STATUS], [IS_SYSTEM], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [PARENT_ID], [PARENT_CODE]) VALUES (N'11', N'是或否', N'yesOrNo', N'12', N'是或否', N'-1', N'0', N'0', null, null, null, null, null, null, N'-1', N'bmgl')
GO
GO
INSERT INTO [dbo].[T_AUTH_CODE] ([ID], [NAME], [CODE], [SHOW_ORDER], [COMMENTS], [UNIT_ID], [STATUS], [IS_SYSTEM], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [PARENT_ID], [PARENT_CODE]) VALUES (N'12', N'是', N'0', N'14', N'是', N'-1', N'0', N'0', null, null, null, null, null, null, N'11', N'yesOrNo')
GO
GO
INSERT INTO [dbo].[T_AUTH_CODE] ([ID], [NAME], [CODE], [SHOW_ORDER], [COMMENTS], [UNIT_ID], [STATUS], [IS_SYSTEM], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [PARENT_ID], [PARENT_CODE]) VALUES (N'13', N'否', N'1', N'13', N'否', N'-1', N'0', N'0', null, null, null, null, null, null, N'11', N'yesOrNo')
GO
GO
INSERT INTO [dbo].[T_AUTH_CODE] ([ID], [NAME], [CODE], [SHOW_ORDER], [COMMENTS], [UNIT_ID], [STATUS], [IS_SYSTEM], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [PARENT_ID], [PARENT_CODE]) VALUES (N'14', N'政治面貌', N'politicalData', N'15', N'政治面貌', N'-1', N'0', N'0', null, null, null, null, null, null, N'-1', N'bmgl')
GO
GO
INSERT INTO [dbo].[T_AUTH_CODE] ([ID], [NAME], [CODE], [SHOW_ORDER], [COMMENTS], [UNIT_ID], [STATUS], [IS_SYSTEM], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [PARENT_ID], [PARENT_CODE]) VALUES (N'15', N'中共党员', N'0', N'18', N'中共党员', N'-1', N'0', N'0', null, null, null, null, null, null, N'14', N'politicalData')
GO
GO
INSERT INTO [dbo].[T_AUTH_CODE] ([ID], [NAME], [CODE], [SHOW_ORDER], [COMMENTS], [UNIT_ID], [STATUS], [IS_SYSTEM], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [PARENT_ID], [PARENT_CODE]) VALUES (N'16', N'共青团员', N'1', N'17', N'共青团员', N'-1', N'0', N'0', null, null, null, null, null, null, N'14', N'politicalData')
GO
GO
INSERT INTO [dbo].[T_AUTH_CODE] ([ID], [NAME], [CODE], [SHOW_ORDER], [COMMENTS], [UNIT_ID], [STATUS], [IS_SYSTEM], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [PARENT_ID], [PARENT_CODE]) VALUES (N'17', N'群众', N'2', N'16', N'群众', N'-1', N'0', N'0', null, null, null, null, null, null, N'14', N'politicalData')
GO
GO
INSERT INTO [dbo].[T_AUTH_CODE] ([ID], [NAME], [CODE], [SHOW_ORDER], [COMMENTS], [UNIT_ID], [STATUS], [IS_SYSTEM], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [PARENT_ID], [PARENT_CODE]) VALUES (N'18', N'url', N'3', N'10', N'url', N'-1', N'0', N'0', null, null, null, null, null, null, N'7', N'resType')
GO
GO

-- ----------------------------
-- Table structure for T_AUTH_GENERATOR
-- ----------------------------
DROP TABLE [dbo].[T_AUTH_GENERATOR]
GO
CREATE TABLE [dbo].[T_AUTH_GENERATOR] (
[ID] varchar(50) NOT NULL ,
[NAME] varchar(50) NOT NULL ,
[PREFIX] varchar(20) NULL ,
[SYS_NUMBER] decimal(10) NULL ,
[SUFFIX] varchar(20) NULL 
)


GO

-- ----------------------------
-- Records of T_AUTH_GENERATOR
-- ----------------------------
INSERT INTO [dbo].[T_AUTH_GENERATOR] ([ID], [NAME], [PREFIX], [SYS_NUMBER], [SUFFIX]) VALUES (N'1', N'工号生成规则', N'A', N'10000', N'')
GO
GO

-- ----------------------------
-- Table structure for T_AUTH_ID_SEQUENCE
-- ----------------------------
DROP TABLE [dbo].[T_AUTH_ID_SEQUENCE]
GO
CREATE TABLE [dbo].[T_AUTH_ID_SEQUENCE] (
[ID_NAME] varchar(50) NULL ,
[MAXVAL] decimal(10) NULL 
)


GO

-- ----------------------------
-- Records of T_AUTH_ID_SEQUENCE
-- ----------------------------

-- ----------------------------
-- Table structure for T_AUTH_LOGIN_FAILED_LOG
-- ----------------------------
DROP TABLE [dbo].[T_AUTH_LOGIN_FAILED_LOG]
GO
CREATE TABLE [dbo].[T_AUTH_LOGIN_FAILED_LOG] (
[ID] varchar(32) NOT NULL ,
[LOGIN_NAME] varchar(150) NULL ,
[LOGIN_IP] varchar(30) NULL ,
[LOGIN_FAILED_TIME] varchar(20) NULL 
)


GO

-- ----------------------------
-- Records of T_AUTH_LOGIN_FAILED_LOG
-- ----------------------------

-- ----------------------------
-- Table structure for T_AUTH_OPERATELOG
-- ----------------------------
DROP TABLE [dbo].[T_AUTH_OPERATELOG]
GO
CREATE TABLE [dbo].[T_AUTH_OPERATELOG] (
[ID] varchar(32) NOT NULL ,
[TYPE] varchar(200) NULL ,
[USER_ID] varchar(32) NOT NULL ,
[USER_NAME] varchar(150) NOT NULL ,
[LOG_DATE] varchar(20) NULL ,
[OPERATE] varchar(200) NULL ,
[MESSAGE] varchar(2000) NULL ,
[APPKEY] varchar(50) NULL ,
[URL] varchar(500) NULL ,
[NOTE] varchar(200) NULL ,
[UNIT_ID] varchar(32) NOT NULL 
)


GO

-- ----------------------------
-- Records of T_AUTH_OPERATELOG
-- ----------------------------

-- ----------------------------
-- Table structure for T_AUTH_ORG
-- ----------------------------
DROP TABLE [dbo].[T_AUTH_ORG]
GO
CREATE TABLE [dbo].[T_AUTH_ORG] (
[ORGANIZE_ID] varchar(32) NOT NULL ,
[PARENT_ID] varchar(32) NULL ,
[ORGANIZE_NAME] varchar(150) NOT NULL ,
[COMMENTS] varchar(500) NULL ,
[SHOW_ORDER] decimal(10) NULL ,
[ABBREVIATION] varchar(150) NULL ,
[ORGANIZE_CODE] varchar(150) NOT NULL ,
[FLAG1] varchar(200) NULL ,
[FLAG2] varchar(200) NULL ,
[FLAG3] varchar(200) NULL ,
[FLAG4] varchar(200) NULL ,
[FLAG5] varchar(200) NULL ,
[FLAG6] varchar(200) NULL ,
[FLAG7] varchar(200) NULL ,
[FLAG8] varchar(200) NULL ,
[FLAG9] varchar(200) NULL ,
[FLAG10] varchar(200) NULL ,
[ORGANIZE_TYPE_ID] decimal(1) NOT NULL ,
[STATUS] char(1) NOT NULL ,
[UNIT_ID] varchar(32) NOT NULL ,
[CREATE_USER_ID] varchar(32) NULL ,
[CREATE_USER_NAME] varchar(150) NULL ,
[CREATE_TIME] varchar(20) NULL ,
[UPDATE_USER_ID] varchar(32) NULL ,
[UPDATE_USER_NAME] varchar(150) NULL ,
[UPDATE_TIME] varchar(20) NULL ,
[IS_SYSTEM] char(1) NOT NULL 
)


GO

-- ----------------------------
-- Records of T_AUTH_ORG
-- ----------------------------
INSERT INTO [dbo].[T_AUTH_ORG] ([ORGANIZE_ID], [PARENT_ID], [ORGANIZE_NAME], [COMMENTS], [SHOW_ORDER], [ABBREVIATION], [ORGANIZE_CODE], [FLAG1], [FLAG2], [FLAG3], [FLAG4], [FLAG5], [FLAG6], [FLAG7], [FLAG8], [FLAG9], [FLAG10], [ORGANIZE_TYPE_ID], [STATUS], [UNIT_ID], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM]) VALUES (N'-1', null, N'组织机构', N'组织根结点', N'1', null, N'-1', null, null, null, null, null, null, null, null, null, null, N'-1', N'0', N'-1', null, null, null, null, null, null, N'0')
GO
GO

-- ----------------------------
-- Table structure for T_AUTH_ORG_USER
-- ----------------------------
DROP TABLE [dbo].[T_AUTH_ORG_USER]
GO
CREATE TABLE [dbo].[T_AUTH_ORG_USER] (
[USER_ID] varchar(32) NOT NULL ,
[ORGANIZE_ID] varchar(32) NOT NULL ,
[USER_SHOWORDER] decimal(10) NULL ,
[USERTYPE] char(1) NOT NULL ,
[ORG_USER_ID] varchar(32) NOT NULL ,
[UNIT_ID] varchar(32) NOT NULL 
)


GO

-- ----------------------------
-- Records of T_AUTH_ORG_USER
-- ----------------------------

-- ----------------------------
-- Table structure for T_AUTH_RESOURCE
-- ----------------------------
DROP TABLE [dbo].[T_AUTH_RESOURCE]
GO
CREATE TABLE [dbo].[T_AUTH_RESOURCE] (
[RESOURCE_ID] varchar(32) NOT NULL ,
[PARENT_ID] varchar(32) NULL ,
[URL] varchar(200) NULL ,
[NAME] varchar(150) NOT NULL ,
[COMMENTS] varchar(500) NULL ,
[NAVIGATE_URL] varchar(300) NULL ,
[BUSINESS_URL] varchar(300) NULL ,
[OTHER_URL] varchar(300) NULL ,
[USE_FUNCTION] varchar(300) NULL ,
[RESOURCE_IMG] varchar(300) NULL ,
[RESOURCEKEY] varchar(500) NULL ,
[SHOW_ORDER] decimal(10) NULL ,
[RES_TYPE] char(1) NOT NULL ,
[MODULE_ID] varchar(200) NULL ,
[SOURCE_FILE] varchar(200) NULL ,
[CREATE_USER_ID] varchar(32) NULL ,
[CREATE_USER_NAME] varchar(150) NULL ,
[CREATE_TIME] varchar(20) NULL ,
[UPDATE_USER_ID] varchar(32) NULL ,
[UPDATE_USER_NAME] varchar(150) NULL ,
[UPDATE_TIME] varchar(20) NULL ,
[IS_SYSTEM] char(1) NOT NULL ,
[IS_DEFAULT] char(10) NOT NULL 
)


GO

-- ----------------------------
-- Records of T_AUTH_RESOURCE
-- ----------------------------
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'-1', null, N'', N'资源信息', null, null, null, null, null, null, N'<NULL>', N'0', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'3', N'1', N'/auth/org', N'组织管理', null, null, null, null, N'<NULL>', N'<NULL>', N'<NULL>', N'3', N'1', N'<NULL>', null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'4', N'1', N'/auth/resource', N'资源管理', null, null, null, null, N'<NULL>', N'<NULL>', N'<NULL>', N'4', N'1', N'<NULL>', null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'5', N'1', N'/auth/role', N'角色管理', null, null, null, null, N'<NULL>', N'<NULL>', N'<NULL>', N'5', N'1', N'<NULL>', null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'6', N'12', N'/auth/log/sysLog', N'登录日志', null, null, null, null, N'<NULL>', N'<NULL>', N'<NULL>', N'6', N'1', N'<NULL>', null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'7', N'12', N'/auth/log/operateLog', N'操作日志', null, null, null, null, N'<NULL>', N'<NULL>', N'<NULL>', N'7', N'1', N'<NULL>', null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'8', N'1', N'/auth/code', N'编码维护', null, null, null, null, N'<NULL>', N'<NULL>', N'<NULL>', N'8', N'1', N'<NULL>', null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'9', N'1', N'/auth/unitChange', N'单位调度', null, null, null, null, N'<NULL>', null, N'<NULL>', N'9', N'1', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'10', N'1', N'/auth/roleclassification', N'角色分类管理', null, null, null, null, N'<NULL>', null, N'<NULL>', N'10', N'1', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'2', N'1', N'/auth/user', N'用户管理', null, null, null, null, N'<NULL>', N'<NULL>', N'<NULL>', N'2', N'1', N'<NULL>', null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'12', N'1', N'/auth/log', N'日志管理', null, null, null, null, null, null, null, N'12', N'1', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'13', N'1', N'/auth/config', N'配置管理', null, null, null, null, null, null, null, N'13', N'1', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'1', N'-1', N'/auth', N'系统管理', null, null, null, null, null, null, null, N'1', N'2', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'19', N'2', N'/auth/user/changeOrg', N'改变组织', null, null, null, null, null, null, null, N'19', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'20', N'2', N'/auth/user/partTimeJob', N'兼职', null, null, null, null, null, null, null, N'20', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'21', N'2', N'/auth/user/removePartTimeJob', N'撤销兼职', null, null, null, null, null, null, null, N'21', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'22', N'2', N'/auth/user/initPassword', N'初始化密码', null, null, null, null, null, null, null, N'22', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'23', N'2', N'/auth/user/changePassword', N'修改密码', null, null, null, null, null, null, null, N'23', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'14', N'2', N'/auth/user/getUserPage', N'列表', null, null, null, null, null, null, null, N'14', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'15', N'2', N'/auth/user/create', N'新增', null, null, null, null, null, null, null, N'15', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'16', N'2', N'/auth/user/update', N'修改', null, null, null, null, null, null, null, N'16', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'17', N'2', N'/auth/user/delete', N'删除', null, null, null, null, null, null, null, N'17', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'18', N'2', N'/auth/user/accreditRole', N'授予角色', null, null, null, null, null, null, null, N'18', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'24', N'2', N'/auth/user/renewOldToNew', N'恢复用户', null, null, null, null, null, null, null, N'24', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'25', N'3', N'/auth/org/create', N'新增', null, null, null, null, null, null, null, N'25', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'26', N'3', N'/auth/org/update', N'修改', null, null, null, null, null, null, null, N'26', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'27', N'3', N'/auth/org/delete', N'删除', null, null, null, null, null, null, null, N'27', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'28', N'3', N'/auth/org/moveOrg', N'移动组织', null, null, null, null, null, null, null, N'28', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'29', N'3', N'/auth/org/mergeOrg', N'组织合并', null, null, null, null, null, null, null, N'29', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'30', N'3', N'/auth/org/page', N'列表', null, null, null, null, null, null, null, N'30', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'31', N'5', N'/auth/role/page', N'列表', null, null, null, null, null, null, null, N'31', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'32', N'5', N'/auth/role/create', N'新增', null, null, null, null, null, null, null, N'32', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'33', N'5', N'/auth/role/update', N'修改', null, null, null, null, null, null, null, N'33', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'34', N'5', N'/auth/role/delete', N'删除', null, null, null, null, null, null, null, N'34', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'35', N'5', N'/auth/role/grantResource', N'授予资源', null, null, null, null, null, null, null, N'35', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'36', N'5', N'/auth/role/grantUser', N'授予用户', null, null, null, null, null, null, null, N'36', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'37', N'4', N'/auth/resource/page', N'列表', null, null, null, null, null, null, null, N'37', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'38', N'4', N'/auth/resource/create', N'新增', null, null, null, null, null, null, null, N'38', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'39', N'4', N'/auth/resource/update', N'修改', null, null, null, null, null, null, null, N'39', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'40', N'4', N'/auth/resource/delete', N'删除', null, null, null, null, null, null, null, N'40', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'41', N'4', N'/auth/resource/moveResource', N'移动资源', null, null, null, null, null, null, null, N'41', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'42', N'6', N'/auth/log/sysLog/page', N'列表', null, null, null, null, null, null, null, N'42', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'43', N'6', N'/auth/log/sysLog/export', N'导出日志', null, null, null, null, null, null, null, N'43', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'44', N'7', N'/auth/log/operateLog/page', N'列表', null, null, null, null, null, null, null, N'44', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'45', N'7', N'/auth/log/operateLog/export', N'导出日志', null, null, null, null, null, null, null, N'45', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'46', N'8', N'/auth/code/page', N'列表', null, null, null, null, null, null, null, N'46', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'47', N'8', N'/auth/code/create', N'新增', null, null, null, null, null, null, null, N'47', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'48', N'8', N'/auth/code/update', N'修改', null, null, null, null, null, null, null, N'48', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'49', N'8', N'/auth/code/delete', N'删除', null, null, null, null, null, null, null, N'49', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'50', N'8', N'/auth/code/updateStatus', N'启用停用', null, null, null, null, null, null, null, N'50', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'51', N'9', N'/auth/unitChange/getUserPage', N'列表', null, null, null, null, null, null, null, N'51', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'52', N'9', N'/auth/unitChange/changeOrg', N'改变组织', null, null, null, null, null, null, null, N'52', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'53', N'9', N'/auth/unitChange/partTimeJob', N'兼职', null, null, null, null, null, null, null, N'53', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'54', N'10', N'/auth/roleclassification/page', N'列表', null, null, null, null, null, null, null, N'54', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'55', N'10', N'/auth/roleclassification/create', N'新增', null, null, null, null, null, null, null, N'55', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'56', N'10', N'/auth/roleclassification/update', N'修改', null, null, null, null, null, null, null, N'56', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'57', N'10', N'/auth/roleclassification/delete', N'删除', null, null, null, null, null, null, null, N'57', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'59', N'8', N'/auth/code/getAsyncCodeTree', N'加载左侧树', null, null, null, null, null, null, null, N'59', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'61', N'8', N'/auth/code/load', N'加载对象', null, null, null, null, null, null, null, N'61', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'62', N'3', N'/auth/org/getLeftOrgSyncTree', N'加载左侧树', null, null, null, null, null, null, null, N'62', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'64', N'3', N'/auth/org/load', N'加载对象', null, null, null, null, null, null, null, N'64', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'65', N'4', N'/auth/resource/load', N'加载对象', null, null, null, null, null, null, null, N'65', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'67', N'4', N'/auth/resource/loadMoveResourceTree', N'加载移动资源树', null, null, null, null, null, null, null, N'67', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'68', N'4', N'/auth/resource/getLeftSyncResourceTree', N'加载左侧树', null, null, null, null, null, null, null, N'68', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'69', N'5', N'/auth/role/load', N'加载对象', null, null, null, null, null, null, null, N'69', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'70', N'5', N'/auth/role/loadOrgUserTree', N'加载授予用户树', null, null, null, null, null, null, null, N'70', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'71', N'5', N'/auth/role/loadRoleResourceTree', N'加载授予资源树', null, null, null, null, null, null, null, N'71', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'72', N'5', N'/auth/role/getLeftRoleClassificationSyncTree', N'加载左侧树', null, null, null, null, null, null, null, N'72', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'73', N'10', N'/auth/roleclassification/getLeftRoleClassificationSyncTree', N'加载左侧树', null, null, null, null, null, null, null, N'73', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'74', N'10', N'/auth/roleclassification/load', N'加载对象', null, null, null, null, null, null, null, N'74', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'75', N'2', N'/auth/user/updateOldUser', N'修改老用户', null, null, null, null, null, null, null, N'75', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'76', N'2', N'/auth/user/resumeUser', N'恢复用户(新增时)', null, null, null, null, null, null, null, N'76', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'77', N'2', N'/auth/user/getAccreditRoleTree', N'加载授予角色树', null, null, null, null, null, null, null, N'77', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'78', N'2', N'/auth/user/upload', N'上传', null, null, null, null, null, null, null, N'78', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'79', N'2', N'/auth/user/getLeftOrgSyncTree', N'加载左侧树', null, null, null, null, null, null, null, N'79', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'80', N'9', N'/auth/unitChange/getUnitChangeTree', N'加载左侧树', null, null, null, null, null, null, null, N'80', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'81', N'2', N'/auth/user/load', N'加载对象', null, null, null, null, null, null, null, N'81', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'82', N'2', N'/auth/user/page', N'老用户列表', null, null, null, null, null, null, null, N'82', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'83', N'2', N'/auth/user/unlockUser', N'解锁', null, null, null, null, null, null, null, N'83', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'84', N'2', N'/auth/user/openLookRoleDialog', N'查看角色', null, null, null, null, null, null, null, N'84', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'85', N'2', N'/auth/user/openLookOrgDialog', N'查看部门', null, null, null, null, null, null, null, N'85', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'86', N'2', N'/auth/user/lockUser', N'锁定', null, null, null, null, null, null, null, N'86', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'87', N'9', N'/auth/unitChange/getAllOrgTree', N'加载全组织树', null, null, null, null, null, null, null, N'87', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'88', N'-1', N'/auth/user/changeSelfPassword', N'修改个人密码', null, null, null, null, null, null, null, N'88', N'0', null, null, null, null, null, null, null, null, N'0', N'0         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'89', N'-1', N'/auth/user/update', N'修改个人资料', null, null, null, null, null, null, null, N'89', N'0', null, null, null, null, null, null, null, null, N'0', N'0         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'90', N'1', N'/auth/usergroup', N'用户组管理', null, null, null, null, null, null, null, N'90', N'1', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'91', N'90', N'/auth/usergroup/page', N'列表', null, null, null, null, null, null, null, N'91', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'92', N'90', N'/auth/usergroup/create', N'新增', null, null, null, null, null, null, null, N'92', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'93', N'90', N'/auth/usergroup/update', N'修改', null, null, null, null, null, null, null, N'93', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'94', N'90', N'/auth/usergroup/delete', N'删除', null, null, null, null, null, null, null, N'94', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'95', N'90', N'/auth/usergroup/getUserPage', N'用户列表', null, null, null, null, null, null, null, N'95', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'96', N'90', N'/auth/usergroup/addUser', N'添加用户', null, null, null, null, null, null, null, N'96', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'97', N'90', N'/auth/usergroup/removeUser', N'移除用户', null, null, null, null, null, null, null, N'97', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'98', N'-1', N'/auth/user/load', N'加载个人资料', null, null, null, null, null, null, null, N'98', N'0', null, null, null, null, null, null, null, null, N'0', N'0         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'99', N'-1', N'/auth/user/upload', N'上传个人头像', null, null, null, null, null, null, null, N'99', N'0', null, null, null, null, null, null, null, null, N'0', N'0         ')
GO
GO
INSERT INTO [dbo].[T_AUTH_RESOURCE] ([RESOURCE_ID], [PARENT_ID], [URL], [NAME], [COMMENTS], [NAVIGATE_URL], [BUSINESS_URL], [OTHER_URL], [USE_FUNCTION], [RESOURCE_IMG], [RESOURCEKEY], [SHOW_ORDER], [RES_TYPE], [MODULE_ID], [SOURCE_FILE], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM], [IS_DEFAULT]) VALUES (N'100', N'8', N'/auth/code/moveCode', N'移动编码', null, null, null, null, null, null, null, N'100', N'0', null, null, null, null, null, null, null, null, N'0', N'1         ')
GO
GO

-- ----------------------------
-- Table structure for T_AUTH_ROLE
-- ----------------------------
DROP TABLE [dbo].[T_AUTH_ROLE]
GO
CREATE TABLE [dbo].[T_AUTH_ROLE] (
[ROLE_ID] varchar(32) NOT NULL ,
[ROLE_NAME] varchar(150) NOT NULL ,
[COMMENTS] varchar(500) NULL ,
[ROLE_KEY] varchar(100) NOT NULL ,
[SHOW_ORDER] decimal(10) NULL ,
[ROLECLASSIFICATION_ID] varchar(32) NOT NULL ,
[UNIT_ID] varchar(32) NULL ,
[CREATE_USER_ID] varchar(32) NULL ,
[CREATE_USER_NAME] varchar(150) NULL ,
[CREATE_TIME] varchar(20) NULL ,
[UPDATE_USER_ID] varchar(32) NULL ,
[UPDATE_USER_NAME] varchar(150) NULL ,
[UPDATE_TIME] varchar(20) NULL ,
[IS_SYSTEM] char(1) NOT NULL 
)


GO

-- ----------------------------
-- Records of T_AUTH_ROLE
-- ----------------------------
INSERT INTO [dbo].[T_AUTH_ROLE] ([ROLE_ID], [ROLE_NAME], [COMMENTS], [ROLE_KEY], [SHOW_ORDER], [ROLECLASSIFICATION_ID], [UNIT_ID], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM]) VALUES (N'1', N'超级管理员', null, N'superrole', null, N'-1', N'-1', null, null, null, null, null, null, N'0')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE] ([ROLE_ID], [ROLE_NAME], [COMMENTS], [ROLE_KEY], [SHOW_ORDER], [ROLECLASSIFICATION_ID], [UNIT_ID], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM]) VALUES (N'3', N'单位系统管理员', N'内置的单位系统管理员角色，相当于各个单位的超级管理员。不可修改删除。', N'unitsysrole', null, N'-1', N'-1', null, null, null, null, null, null, N'0')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE] ([ROLE_ID], [ROLE_NAME], [COMMENTS], [ROLE_KEY], [SHOW_ORDER], [ROLECLASSIFICATION_ID], [UNIT_ID], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM]) VALUES (N'2', N'默认角色', N'新增用户时会默认赋予默认角色, 可配置一些通用的权限。可授予系统管理的资源。不可修改删除。', N'defaultrole', null, N'1', N'-1', null, null, null, null, null, null, N'0')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE] ([ROLE_ID], [ROLE_NAME], [COMMENTS], [ROLE_KEY], [SHOW_ORDER], [ROLECLASSIFICATION_ID], [UNIT_ID], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM]) VALUES (N'4', N'演示角色', N'内置的一个空角色，可任由用户处理。可以授予系统管理的资源。不可修改删除。', N'demorole', null, N'1', N'-1', null, null, null, null, null, null, N'0')
GO
GO

-- ----------------------------
-- Table structure for T_AUTH_ROLE_RESOURCE
-- ----------------------------
DROP TABLE [dbo].[T_AUTH_ROLE_RESOURCE]
GO
CREATE TABLE [dbo].[T_AUTH_ROLE_RESOURCE] (
[ROLE_ID] varchar(32) NOT NULL ,
[RESOURCE_ID] varchar(32) NOT NULL ,
[UNIT_ID] varchar(32) NOT NULL ,
[ROLE_RESOURCE_ID] varchar(32) NOT NULL 
)


GO

-- ----------------------------
-- Records of T_AUTH_ROLE_RESOURCE
-- ----------------------------
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'1', N'-1', N'-1', N'1')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'1', N'-1', N'2')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'2', N'-1', N'3')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'14', N'-1', N'4')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'15', N'-1', N'5')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'16', N'-1', N'6')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'17', N'-1', N'7')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'18', N'-1', N'8')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'19', N'-1', N'9')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'20', N'-1', N'10')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'21', N'-1', N'11')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'22', N'-1', N'12')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'23', N'-1', N'13')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'24', N'-1', N'14')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'75', N'-1', N'15')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'76', N'-1', N'16')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'77', N'-1', N'17')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'78', N'-1', N'18')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'79', N'-1', N'19')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'3', N'-1', N'20')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'25', N'-1', N'21')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'26', N'-1', N'22')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'27', N'-1', N'23')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'28', N'-1', N'24')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'29', N'-1', N'25')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'30', N'-1', N'26')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'62', N'-1', N'27')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'64', N'-1', N'28')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'5', N'-1', N'29')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'31', N'-1', N'30')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'32', N'-1', N'31')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'33', N'-1', N'32')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'34', N'-1', N'33')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'35', N'-1', N'34')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'36', N'-1', N'35')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'69', N'-1', N'36')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'70', N'-1', N'37')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'71', N'-1', N'38')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'72', N'-1', N'39')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'8', N'-1', N'40')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'46', N'-1', N'41')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'47', N'-1', N'42')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'48', N'-1', N'43')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'49', N'-1', N'44')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'50', N'-1', N'45')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'59', N'-1', N'46')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'61', N'-1', N'47')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'9', N'-1', N'48')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'51', N'-1', N'49')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'52', N'-1', N'50')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'53', N'-1', N'51')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'80', N'-1', N'52')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'10', N'-1', N'53')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'54', N'-1', N'54')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'55', N'-1', N'55')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'56', N'-1', N'56')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'57', N'-1', N'57')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'73', N'-1', N'58')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'74', N'-1', N'59')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'12', N'-1', N'60')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'6', N'-1', N'61')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'42', N'-1', N'62')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'43', N'-1', N'63')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'7', N'-1', N'64')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'44', N'-1', N'65')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'45', N'-1', N'66')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'81', N'-1', N'67')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'82', N'-1', N'68')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'83', N'-1', N'69')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'84', N'-1', N'70')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'85', N'-1', N'71')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'86', N'-1', N'72')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'87', N'-1', N'73')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'88', N'-1', N'74')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'89', N'-1', N'75')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'90', N'-1', N'76')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'91', N'-1', N'77')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'92', N'-1', N'78')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'93', N'-1', N'79')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'94', N'-1', N'80')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'95', N'-1', N'81')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'96', N'-1', N'82')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'97', N'-1', N'83')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'98', N'-1', N'84')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'99', N'-1', N'85')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'2', N'88', N'-1', N'86')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'2', N'89', N'-1', N'87')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'2', N'98', N'-1', N'88')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'2', N'99', N'-1', N'89')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'4', N'88', N'-1', N'90')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'4', N'89', N'-1', N'91')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'4', N'98', N'-1', N'92')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'4', N'99', N'-1', N'93')
GO
GO
INSERT INTO [dbo].[T_AUTH_ROLE_RESOURCE] ([ROLE_ID], [RESOURCE_ID], [UNIT_ID], [ROLE_RESOURCE_ID]) VALUES (N'3', N'100', N'-1', N'94')
GO
GO

-- ----------------------------
-- Table structure for T_AUTH_ROLE_USER
-- ----------------------------
DROP TABLE [dbo].[T_AUTH_ROLE_USER]
GO
CREATE TABLE [dbo].[T_AUTH_ROLE_USER] (
[ROLE_ID] varchar(32) NOT NULL ,
[USER_ID] varchar(32) NOT NULL ,
[UNIT_ID] varchar(32) NOT NULL ,
[ROLE_USER_ID] varchar(32) NOT NULL ,
[IS_TEMP_ACCREDIT] char(1) NULL ,
[TEMP_ACCREDIT_DATE_START] varchar(20) NULL ,
[TEMP_ACCREDIT_DATE_END] varchar(20) NULL 
)


GO

-- ----------------------------
-- Records of T_AUTH_ROLE_USER
-- ----------------------------
INSERT INTO [dbo].[T_AUTH_ROLE_USER] ([ROLE_ID], [USER_ID], [UNIT_ID], [ROLE_USER_ID], [IS_TEMP_ACCREDIT], [TEMP_ACCREDIT_DATE_START], [TEMP_ACCREDIT_DATE_END]) VALUES (N'1', N'1', N'-1', N'100', N'1', null, null)
GO
GO

-- ----------------------------
-- Table structure for T_AUTH_ROLECLASSIFICATION
-- ----------------------------
DROP TABLE [dbo].[T_AUTH_ROLECLASSIFICATION]
GO
CREATE TABLE [dbo].[T_AUTH_ROLECLASSIFICATION] (
[ROLECLASSIFICATION_ID] varchar(32) NOT NULL ,
[NAME] varchar(150) NOT NULL ,
[PARENT_ID] varchar(32) NULL ,
[ROLECLASS_KEY] varchar(150) NOT NULL ,
[COMMENTS] varchar(500) NULL ,
[SHOW_ORDER] decimal(10) NULL ,
[UNIT_ID] varchar(32) NOT NULL ,
[CREATE_USER_ID] varchar(32) NULL ,
[CREATE_USER_NAME] varchar(150) NULL ,
[CREATE_TIME] varchar(20) NULL ,
[UPDATE_USER_ID] varchar(32) NULL ,
[UPDATE_USER_NAME] varchar(150) NULL ,
[UPDATE_TIME] varchar(20) NULL ,
[IS_SYSTEM] char(1) NOT NULL 
)


GO

-- ----------------------------
-- Records of T_AUTH_ROLECLASSIFICATION
-- ----------------------------
INSERT INTO [dbo].[T_AUTH_ROLECLASSIFICATION] ([ROLECLASSIFICATION_ID], [NAME], [PARENT_ID], [ROLECLASS_KEY], [COMMENTS], [SHOW_ORDER], [UNIT_ID], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [IS_SYSTEM]) VALUES (N'1', N'默认角色分类', N'-1', N'-1.mrjsfl', N'内置的默认角色分类，不可修改删除。但是可以在它的下面建子分类或者角色。', N'1', N'-1', N'<!NULL>', null, null, null, null, null, N'0')
GO
GO

-- ----------------------------
-- Table structure for T_AUTH_SYSLOG
-- ----------------------------
DROP TABLE [dbo].[T_AUTH_SYSLOG]
GO
CREATE TABLE [dbo].[T_AUTH_SYSLOG] (
[ID] varchar(32) NOT NULL ,
[USER_ID] varchar(32) NULL ,
[USER_NAME] varchar(150) NULL ,
[LOG_DATE] varchar(20) NULL ,
[OPERATE] varchar(100) NULL ,
[MESSAGE] varchar(2000) NULL ,
[IP] varchar(60) NULL ,
[MAC] varchar(60) NULL ,
[STATUS] char(1) NULL ,
[UNIT_ID] varchar(32) NULL 
)


GO

-- ----------------------------
-- Records of T_AUTH_SYSLOG
-- ----------------------------

-- ----------------------------
-- Table structure for T_AUTH_USER
-- ----------------------------
DROP TABLE [dbo].[T_AUTH_USER]
GO
CREATE TABLE [dbo].[T_AUTH_USER] (
[USER_ID] varchar(32) NOT NULL ,
[LOGIN_NAME] varchar(150) NOT NULL ,
[PASSWORD] varchar(40) NOT NULL ,
[USER_NAME] varchar(150) NOT NULL ,
[FLAG_ACTION] char(1) NULL ,
[EMAIL] varchar(50) NULL ,
[MOBILE] varchar(20) NULL ,
[TITLE] varchar(100) NULL ,
[SHOW_ORDER] decimal(10) NULL ,
[COMMENTS] varchar(500) NULL ,
[STATION] varchar(100) NULL ,
[STATUS] char(1) NOT NULL ,
[JOB_NO] varchar(150) NULL ,
[LAST_LOGIN_DATE] datetime NULL ,
[LAST_MODIFY_PSD] datetime NULL ,
[FLAG1] varchar(200) NULL ,
[FLAG2] varchar(200) NULL ,
[FLAG3] varchar(200) NULL ,
[FLAG4] varchar(200) NULL ,
[FLAG5] varchar(200) NULL ,
[UNIT_ID] varchar(32) NOT NULL ,
[IS_ADMIN] char(1) NOT NULL ,
[FLAG8] varchar(200) NULL ,
[FLAG9] varchar(200) NULL ,
[FLAG10] varchar(200) NULL ,
[TELEPHONE] varchar(50) NULL ,
[ABBR] varchar(100) NULL ,
[CARD_NO] varchar(50) NULL ,
[SEX] char(1) NULL ,
[AGE] decimal(10,6) NULL ,
[BIRTHDAY] datetime NULL ,
[URLPATH] varchar(200) NULL ,
[FLAG6] varchar(200) NULL ,
[FLAG7] varchar(200) NULL ,
[CREATE_USER_ID] varchar(32) NULL ,
[CREATE_USER_NAME] varchar(150) NULL ,
[CREATE_TIME] varchar(20) NULL ,
[UPDATE_USER_ID] varchar(32) NULL ,
[UPDATE_USER_NAME] varchar(150) NULL ,
[UPDATE_TIME] varchar(20) NULL ,
[ON_JOB_DATE] varchar(20) NULL ,
[OFF_JOB_DATE] varchar(20) NULL ,
[IS_SECOND_ON_JOB] char(1) NULL ,
[POLITICAL] varchar(10) NULL ,
[IS_SYSTEM] char(1) NOT NULL 
)


GO

-- ----------------------------
-- Records of T_AUTH_USER
-- ----------------------------
INSERT INTO [dbo].[T_AUTH_USER] ([USER_ID], [LOGIN_NAME], [PASSWORD], [USER_NAME], [FLAG_ACTION], [EMAIL], [MOBILE], [TITLE], [SHOW_ORDER], [COMMENTS], [STATION], [STATUS], [JOB_NO], [LAST_LOGIN_DATE], [LAST_MODIFY_PSD], [FLAG1], [FLAG2], [FLAG3], [FLAG4], [FLAG5], [UNIT_ID], [IS_ADMIN], [FLAG8], [FLAG9], [FLAG10], [TELEPHONE], [ABBR], [CARD_NO], [SEX], [AGE], [BIRTHDAY], [URLPATH], [FLAG6], [FLAG7], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [ON_JOB_DATE], [OFF_JOB_DATE], [IS_SECOND_ON_JOB], [POLITICAL], [IS_SYSTEM]) VALUES (N'1', N'superadmin', N'670b14728ad9902aecba32e22fa4f6bd', N'超级管理员', N'0', N'superadmin@cesgroup.com', N'12345678999', N'superadmin', N'1', null, null, N'0', null, null, null, null, null, null, null, null, N'-1', N'0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, N'0')
GO
GO

-- ----------------------------
-- Table structure for T_AUTH_USERGROUP
-- ----------------------------
DROP TABLE [dbo].[T_AUTH_USERGROUP]
GO
CREATE TABLE [dbo].[T_AUTH_USERGROUP] (
[ID] varchar(50) NOT NULL ,
[NAME] varchar(150) NOT NULL ,
[USERGROUP_CODE] varchar(150) NOT NULL ,
[SHOW_ORDER] decimal(10) NULL ,
[CREATE_USER_ID] varchar(32) NULL ,
[CREATE_USER_NAME] varchar(150) NULL ,
[CREATE_TIME] varchar(20) NULL ,
[UPDATE_USER_ID] varchar(32) NULL ,
[UPDATE_USER_NAME] varchar(150) NULL ,
[UPDATE_TIME] varchar(20) NULL ,
[UNIT_ID] varchar(32) NOT NULL ,
[IS_SYSTEM] char(1) NOT NULL 
)


GO

-- ----------------------------
-- Records of T_AUTH_USERGROUP
-- ----------------------------
INSERT INTO [dbo].[T_AUTH_USERGROUP] ([ID], [NAME], [USERGROUP_CODE], [SHOW_ORDER], [CREATE_USER_ID], [CREATE_USER_NAME], [CREATE_TIME], [UPDATE_USER_ID], [UPDATE_USER_NAME], [UPDATE_TIME], [UNIT_ID], [IS_SYSTEM]) VALUES (N'-1', N'用户组', N'-1', N'0', null, null, null, null, null, null, N'-1', N'0')
GO
GO

-- ----------------------------
-- Table structure for T_AUTH_VERSION
-- ----------------------------
DROP TABLE [dbo].[T_AUTH_VERSION]
GO
CREATE TABLE [dbo].[T_AUTH_VERSION] (
[VERSION] decimal(10) NOT NULL 
)


GO

-- ----------------------------
-- Records of T_AUTH_VERSION
-- ----------------------------
INSERT INTO [dbo].[T_AUTH_VERSION] ([VERSION]) VALUES (N'1000')
GO
GO

-- ----------------------------
-- Table structure for T_USER_UG
-- ----------------------------
DROP TABLE [dbo].[T_USER_UG]
GO
CREATE TABLE [dbo].[T_USER_UG] (
[ID] varchar(32) NOT NULL ,
[USER_ID] varchar(32) NOT NULL ,
[USERGROUP_ID] varchar(32) NOT NULL ,
[UNIT_ID] varchar(32) NOT NULL 
)


GO

-- ----------------------------
-- Records of T_USER_UG
-- ----------------------------

-- ----------------------------
-- Indexes structure for table T_AUTH_CODE
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table T_AUTH_CODE
-- ----------------------------
ALTER TABLE [dbo].[T_AUTH_CODE] ADD PRIMARY KEY NONCLUSTERED ([ID])
GO

-- ----------------------------
-- Indexes structure for table T_AUTH_GENERATOR
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table T_AUTH_GENERATOR
-- ----------------------------
ALTER TABLE [dbo].[T_AUTH_GENERATOR] ADD PRIMARY KEY NONCLUSTERED ([ID])
GO

-- ----------------------------
-- Indexes structure for table T_AUTH_OPERATELOG
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table T_AUTH_OPERATELOG
-- ----------------------------
ALTER TABLE [dbo].[T_AUTH_OPERATELOG] ADD PRIMARY KEY NONCLUSTERED ([ID])
GO

-- ----------------------------
-- Indexes structure for table T_AUTH_ORG
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table T_AUTH_ORG
-- ----------------------------
ALTER TABLE [dbo].[T_AUTH_ORG] ADD PRIMARY KEY NONCLUSTERED ([ORGANIZE_ID])
GO

-- ----------------------------
-- Indexes structure for table T_AUTH_ORG_USER
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table T_AUTH_ORG_USER
-- ----------------------------
ALTER TABLE [dbo].[T_AUTH_ORG_USER] ADD PRIMARY KEY NONCLUSTERED ([ORG_USER_ID])
GO

-- ----------------------------
-- Indexes structure for table T_AUTH_RESOURCE
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table T_AUTH_RESOURCE
-- ----------------------------
ALTER TABLE [dbo].[T_AUTH_RESOURCE] ADD PRIMARY KEY NONCLUSTERED ([RESOURCE_ID])
GO

-- ----------------------------
-- Indexes structure for table T_AUTH_ROLE
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table T_AUTH_ROLE
-- ----------------------------
ALTER TABLE [dbo].[T_AUTH_ROLE] ADD PRIMARY KEY NONCLUSTERED ([ROLE_ID])
GO

-- ----------------------------
-- Indexes structure for table T_AUTH_ROLE_RESOURCE
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table T_AUTH_ROLE_RESOURCE
-- ----------------------------
ALTER TABLE [dbo].[T_AUTH_ROLE_RESOURCE] ADD PRIMARY KEY NONCLUSTERED ([ROLE_RESOURCE_ID])
GO

-- ----------------------------
-- Indexes structure for table T_AUTH_ROLE_USER
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table T_AUTH_ROLE_USER
-- ----------------------------
ALTER TABLE [dbo].[T_AUTH_ROLE_USER] ADD PRIMARY KEY NONCLUSTERED ([ROLE_USER_ID])
GO

-- ----------------------------
-- Indexes structure for table T_AUTH_ROLECLASSIFICATION
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table T_AUTH_ROLECLASSIFICATION
-- ----------------------------
ALTER TABLE [dbo].[T_AUTH_ROLECLASSIFICATION] ADD PRIMARY KEY NONCLUSTERED ([ROLECLASSIFICATION_ID])
GO

-- ----------------------------
-- Indexes structure for table T_AUTH_SYSLOG
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table T_AUTH_SYSLOG
-- ----------------------------
ALTER TABLE [dbo].[T_AUTH_SYSLOG] ADD PRIMARY KEY NONCLUSTERED ([ID])
GO

-- ----------------------------
-- Indexes structure for table T_AUTH_USER
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table T_AUTH_USER
-- ----------------------------
ALTER TABLE [dbo].[T_AUTH_USER] ADD PRIMARY KEY NONCLUSTERED ([USER_ID])
GO

-- ----------------------------
-- Indexes structure for table T_AUTH_USERGROUP
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table T_AUTH_USERGROUP
-- ----------------------------
ALTER TABLE [dbo].[T_AUTH_USERGROUP] ADD PRIMARY KEY NONCLUSTERED ([ID])
GO

-- ----------------------------
-- Indexes structure for table T_USER_UG
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table T_USER_UG
-- ----------------------------
ALTER TABLE [dbo].[T_USER_UG] ADD PRIMARY KEY NONCLUSTERED ([ID])
GO
