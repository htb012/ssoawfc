/*
MySQL Data Transfer
Source Host: localhost
Source Database: pj_ms
Target Host: localhost
Target Database: pj_ms
Date: 2010-03-06 23:02:12
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for agent
-- ----------------------------
DROP TABLE IF EXISTS `agent`;
CREATE TABLE `agent` (
  `agentid` int(11) NOT NULL COMMENT '代理商编号',
  `agentname` varchar(20) NOT NULL COMMENT '代理商名称',
  `agentphone` varchar(20) NOT NULL COMMENT '代理商联系电话',
  `agentemail` varchar(30) default NULL COMMENT '代理商联系mail',
  `agentextend` char(10) default NULL COMMENT '扩展',
  PRIMARY KEY  (`agentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代理商';

-- ----------------------------
-- Table structure for authgrp_auth
-- ----------------------------
DROP TABLE IF EXISTS `authgrp_auth`;
CREATE TABLE `authgrp_auth` (
  `authgroupid` int(11) NOT NULL,
  `resid` int(11) NOT NULL COMMENT '编号',
  `metaauthid` int(11) NOT NULL COMMENT '元权限的id',
  `authid` int(11) NOT NULL,
  PRIMARY KEY  (`resid`,`metaauthid`,`authgroupid`,`authid`),
  KEY `FK290826A1FEF3255` (`authgroupid`),
  KEY `FK290826A7349A062` (`resid`,`metaauthid`,`authid`),
  CONSTRAINT `FK290826A1FEF3255` FOREIGN KEY (`authgroupid`) REFERENCES `authoritygroup` (`authgroupid`),
  CONSTRAINT `FK290826A7349A062` FOREIGN KEY (`resid`, `metaauthid`, `authid`) REFERENCES `authority` (`resid`, `metaauthid`, `authid`),
  CONSTRAINT `FK_authgrp_auth` FOREIGN KEY (`authgroupid`) REFERENCES `authoritygroup` (`authgroupid`),
  CONSTRAINT `FK_authgrp_auth2` FOREIGN KEY (`resid`, `metaauthid`, `authid`) REFERENCES `authority` (`resid`, `metaauthid`, `authid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='包含有';

-- ----------------------------
-- Table structure for authgrp_user
-- ----------------------------
DROP TABLE IF EXISTS `authgrp_user`;
CREATE TABLE `authgrp_user` (
  `authgroupid` int(11) NOT NULL,
  `userid` int(11) NOT NULL COMMENT '用户编号',
  PRIMARY KEY  (`authgroupid`,`userid`),
  KEY `FK299908D1FEF3255` (`authgroupid`),
  KEY `FK299908DD6EC06B8` (`userid`),
  CONSTRAINT `FK299908D1FEF3255` FOREIGN KEY (`authgroupid`) REFERENCES `authoritygroup` (`authgroupid`),
  CONSTRAINT `FK299908DD6EC06B8` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`),
  CONSTRAINT `FK_authgrp_user` FOREIGN KEY (`authgroupid`) REFERENCES `authoritygroup` (`authgroupid`),
  CONSTRAINT `FK_authgrp_user2` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='单独分派';

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `authid` int(11) NOT NULL,
  `resid` int(11) NOT NULL COMMENT '编号',
  `metaauthid` int(11) NOT NULL COMMENT '元权限的id',
  `authextend` char(10) default NULL,
  `authdiscription` text,
  PRIMARY KEY  (`resid`,`metaauthid`,`authid`),
  KEY `FK57F407433731CCFF` (`metaauthid`),
  KEY `FK57F407435E6C0F10` (`resid`),
  CONSTRAINT `FK57F407433731CCFF` FOREIGN KEY (`metaauthid`) REFERENCES `metaauthority` (`metaauthid`),
  CONSTRAINT `FK57F407435E6C0F10` FOREIGN KEY (`resid`) REFERENCES `resource` (`resid`),
  CONSTRAINT `FK_metaauth` FOREIGN KEY (`metaauthid`) REFERENCES `metaauthority` (`metaauthid`),
  CONSTRAINT `FK_res` FOREIGN KEY (`resid`) REFERENCES `resource` (`resid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表。\r\n           集合资源和元权限的记录�';

-- ----------------------------
-- Table structure for authoritygroup
-- ----------------------------
DROP TABLE IF EXISTS `authoritygroup`;
CREATE TABLE `authoritygroup` (
  `authgroupid` int(11) NOT NULL,
  `authgroupname` varchar(30) NOT NULL,
  `authgroupcreatetime` datetime NOT NULL COMMENT '权限组创建时间',
  `authgroupdiscription` text,
  `authgroupextend` char(10) default NULL,
  PRIMARY KEY  (`authgroupid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限组。\r\n           可将多个权限归纳到一个�';

-- ----------------------------
-- Table structure for bulletin
-- ----------------------------
DROP TABLE IF EXISTS `bulletin`;
CREATE TABLE `bulletin` (
  `bulletinid` int(11) NOT NULL auto_increment,
  `userid` int(11) NOT NULL COMMENT '用户编号',
  `publishdate` date NOT NULL COMMENT '发布日期',
  `content` text,
  `bulletintype` char(10) NOT NULL COMMENT '公告的类型',
  `bulletinextend` char(10) default NULL,
  `bulletintitle` varchar(50) default NULL,
  PRIMARY KEY  (`bulletinid`),
  KEY `FKAFD939A7D6EC06B8` (`userid`),
  CONSTRAINT `FKAFD939A7D6EC06B8` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`),
  CONSTRAINT `FK_publish` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公告。其中属性bulletintype表示公告的类型。公';

-- ----------------------------
-- Table structure for contact
-- ----------------------------
DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact` (
  `contactid` int(11) NOT NULL auto_increment,
  `repairorderid` int(11) NOT NULL,
  `userid` int(11) NOT NULL COMMENT '用户编号',
  `contactdatetime` datetime default NULL COMMENT '连络时间',
  `contactvalidate` char(10) default NULL COMMENT '其他',
  `note` text COMMENT '记录',
  `customresponse` text COMMENT '用户回复',
  `customrequire` text COMMENT '用户请求',
  `contactextend` char(10) default NULL,
  PRIMARY KEY  (`contactid`,`repairorderid`,`userid`),
  KEY `FK38B72420EE1CC1F6` (`repairorderid`),
  KEY `FK38B72420D6EC06B8` (`userid`),
  CONSTRAINT `contact_ibfk_3` FOREIGN KEY (`repairorderid`) REFERENCES `repairorder` (`repairorderid`) ON DELETE CASCADE,
  CONSTRAINT `FK38B72420D6EC06B8` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`),
  CONSTRAINT `FK_contacter` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`),
  CONSTRAINT `FK_note` FOREIGN KEY (`repairorderid`) REFERENCES `repairorder` (`repairorderid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='与顾客间的联络信息。';

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `customerid` int(11) NOT NULL,
  `customername` char(32) NOT NULL,
  `customerphone` varchar(20) NOT NULL COMMENT '维修客户的联系电话',
  `customeremail` varchar(30) default NULL,
  `customerextend` char(10) default NULL,
  PRIMARY KEY  (`customerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for metaauthority
-- ----------------------------
DROP TABLE IF EXISTS `metaauthority`;
CREATE TABLE `metaauthority` (
  `metaauthid` int(11) NOT NULL COMMENT '元权限的id',
  `metaauthname` varchar(30) NOT NULL COMMENT '元权限名',
  `metaauthextend` char(10) default NULL COMMENT '用于扩展，如权限表达式等',
  `metaauthdiscription` text COMMENT '说明',
  PRIMARY KEY  (`metaauthid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限元数据\r\n           用于定义权限和权限的�';

-- ----------------------------
-- Table structure for model
-- ----------------------------
DROP TABLE IF EXISTS `model`;
CREATE TABLE `model` (
  `modelid` int(11) NOT NULL,
  `modelname` varchar(30) NOT NULL,
  `modelphoto` varchar(100) default NULL,
  `parameter` text,
  `linkmark` text,
  `modelextend` char(10) default NULL,
  PRIMARY KEY  (`modelid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for org_user
-- ----------------------------
DROP TABLE IF EXISTS `org_user`;
CREATE TABLE `org_user` (
  `orgid` int(11) NOT NULL,
  `userid` int(11) NOT NULL COMMENT '用户编号',
  PRIMARY KEY  (`orgid`,`userid`),
  KEY `FK4E5DA4461EAB1F9` (`orgid`),
  KEY `FK4E5DA446D6EC06B8` (`userid`),
  CONSTRAINT `FK4E5DA4461EAB1F9` FOREIGN KEY (`orgid`) REFERENCES `organization` (`orgid`),
  CONSTRAINT `FK4E5DA446D6EC06B8` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`),
  CONSTRAINT `FK_org_user` FOREIGN KEY (`orgid`) REFERENCES `organization` (`orgid`),
  CONSTRAINT `FK_org_user2` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织\r\n           一个部门组织拥有的员工';

-- ----------------------------
-- Table structure for organization
-- ----------------------------
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization` (
  `orgid` int(11) NOT NULL,
  `authgroupid` int(11) default NULL,
  `orgname` varchar(30) NOT NULL,
  `manager` int(11) default NULL,
  `uplevel` int(11) default NULL COMMENT '上级组织结构',
  `orgextend` char(10) default NULL,
  `orgdiscription` text,
  PRIMARY KEY  (`orgid`),
  KEY `FK4644ED331FEF3255` (`authgroupid`),
  CONSTRAINT `FK4644ED331FEF3255` FOREIGN KEY (`authgroupid`) REFERENCES `authoritygroup` (`authgroupid`),
  CONSTRAINT `FK_correspondence` FOREIGN KEY (`authgroupid`) REFERENCES `authoritygroup` (`authgroupid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织结构。\r\n           现实中的部门或组别结�';

-- ----------------------------
-- Table structure for repairorder
-- ----------------------------
DROP TABLE IF EXISTS `repairorder`;
CREATE TABLE `repairorder` (
  `repairorderid` int(11) NOT NULL,
  `modelid` int(11) default NULL,
  `agentid` int(11) default NULL COMMENT '代理商编号',
  `orgid` int(11) default NULL,
  `userid` int(11) default NULL COMMENT '用户编号',
  `customerid` int(11) default NULL,
  `rmano` varchar(20) NOT NULL,
  `startdate` date NOT NULL,
  `checkindatetime` datetime default NULL,
  `location` varchar(20) NOT NULL COMMENT '机器目前所在地',
  `parts` varchar(30) default NULL COMMENT '要更换的部件',
  `stock` tinyint(1) default NULL COMMENT '是否有修理供应品',
  `waitparts` varchar(30) default NULL COMMENT '等待的部件名',
  `sn` varchar(20) default NULL,
  `pn` text,
  `validate` char(10) default NULL COMMENT '验证字段10位，代表10个不同含义',
  `problem` text NOT NULL COMMENT '故障描述',
  `photo` text COMMENT '照片地址',
  `isagentrepair` tinyint(1) default NULL COMMENT '是否是代理商维修',
  `repairorderstate` varchar(10) NOT NULL COMMENT '维修状态',
  `repairorderexpend` char(10) default NULL,
  PRIMARY KEY  (`repairorderid`),
  KEY `FK645C17E1CEFD15FE` (`agentid`),
  KEY `FK645C17E11EAB1F9` (`orgid`),
  KEY `FK645C17E1322CBA9E` (`customerid`),
  KEY `FK645C17E158090D46` (`modelid`),
  KEY `FK645C17E1D6EC06B8` (`userid`),
  CONSTRAINT `FK645C17E11EAB1F9` FOREIGN KEY (`orgid`) REFERENCES `organization` (`orgid`),
  CONSTRAINT `FK645C17E1322CBA9E` FOREIGN KEY (`customerid`) REFERENCES `customer` (`customerid`),
  CONSTRAINT `FK645C17E158090D46` FOREIGN KEY (`modelid`) REFERENCES `model` (`modelid`),
  CONSTRAINT `FK645C17E1CEFD15FE` FOREIGN KEY (`agentid`) REFERENCES `agent` (`agentid`),
  CONSTRAINT `FK645C17E1D6EC06B8` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`),
  CONSTRAINT `FK_needrepair` FOREIGN KEY (`modelid`) REFERENCES `model` (`modelid`),
  CONSTRAINT `FK_repaierGroup` FOREIGN KEY (`orgid`) REFERENCES `organization` (`orgid`),
  CONSTRAINT `FK_repairer` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`),
  CONSTRAINT `FK_repairrequest` FOREIGN KEY (`customerid`) REFERENCES `customer` (`customerid`),
  CONSTRAINT `FK_repairsrequest` FOREIGN KEY (`agentid`) REFERENCES `agent` (`agentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='个人用户的维修订单，在原有excel文件中称为E';

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `resid` int(11) NOT NULL COMMENT '编号',
  `resname` varchar(50) NOT NULL COMMENT '资源名称',
  `resextend` char(10) default NULL COMMENT '扩展',
  `resdiscription` text,
  PRIMARY KEY  (`resid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='受保护的资源';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` int(11) NOT NULL auto_increment COMMENT '用户编号',
  `username` varchar(30) NOT NULL COMMENT '用户名',
  `realname` varchar(30) default NULL COMMENT '用户真实名',
  `password` varchar(30) NOT NULL COMMENT '用户密码',
  `sex` varchar(8) default NULL COMMENT '性别',
  `phone` varchar(20) default NULL COMMENT '联系电话',
  `address` varchar(50) default NULL COMMENT '地址',
  `email` varchar(30) default NULL COMMENT 'Email地址',
  `state` varchar(10) NOT NULL COMMENT '状态',
  `securitykey` varchar(32) default NULL COMMENT '安全码',
  `userextend` char(10) default NULL,
  `userdiscription` text,
  PRIMARY KEY  (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表，员工表等。';

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `agent` VALUES ('1', '丸红', '123456', 'wanhong@tom.jp', null);
INSERT INTO `agent` VALUES ('2', '小山', '789012', 'xiaoshan@tom.jp', null);
INSERT INTO `authgrp_auth` VALUES ('1', '12', '1', '1');
INSERT INTO `authgrp_user` VALUES ('1', '1');
INSERT INTO `authority` VALUES ('8', '1', '1', null, 'gsdfgsdf');
INSERT INTO `authority` VALUES ('6', '1', '3', null, 'fasdfsad');
INSERT INTO `authority` VALUES ('7', '8', '3', null, 'fasdf');
INSERT INTO `authority` VALUES ('5', '11', '4', null, 'dsadasd');
INSERT INTO `authority` VALUES ('1', '12', '1', null, 'fsdfsd');
INSERT INTO `authoritygroup` VALUES ('1', 'adminGroup', '2010-03-05 18:19:42', 'testfsdfs', null);
INSERT INTO `authoritygroup` VALUES ('2', 'gsfdg', '2010-03-06 22:30:40', 'fsdgdf', null);
INSERT INTO `bulletin` VALUES ('106', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('107', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('108', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('109', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('110', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('111', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('112', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('113', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('114', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('115', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('116', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('117', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('118', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('119', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('120', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('121', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('122', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('123', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('124', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('125', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('126', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('127', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('128', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('129', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('130', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('131', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('132', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('133', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('134', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('135', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('136', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('137', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('138', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('139', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('140', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('141', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('142', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('143', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('144', '1', '2010-03-02', 'testインフォメーション掲示板 \r\n\r\nインフォメーション掲示板 \r\n\r\nインフォメーション掲示板 \r\n\r\nインフォメーション掲示板 \r\n\r\nインフォメーション掲示板 \r\n\r\nインフォメーション掲示板 \r\n\r\nインフォメーション掲示板 \r\n\r\nインフォメーション掲示板 \r\n\r\nインフォメーション掲示板 \r\n\r\nインフォメーション掲示板 \r\n\r\nインフォメーション掲示板 \r\n\r\nインフォメーション掲示板 \r\n\r\n', 'all', null, '');
INSERT INTO `bulletin` VALUES ('147', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('149', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('150', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('151', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('152', '1', '2010-03-02', 'test', 'svcCenter', null, '');
INSERT INTO `bulletin` VALUES ('153', '1', '2010-03-02', 'test', 'all', null, null);
INSERT INTO `bulletin` VALUES ('154', '1', '2010-03-04', 'testgdf', 'all', null, 'gfdfg');
INSERT INTO `bulletin` VALUES ('156', '1', '2010-03-02', 'test53453', 'sysRepair', null, '54534');
INSERT INTO `bulletin` VALUES ('158', '1', '2010-03-02', '代理代理代理代理代理インフォメーション掲示板 \r\n\r\nインフォメーション掲示板 \r\n\r\nインフォメーション掲示板 \r\n\r\n', 'svcCenter', null, '代理');
INSERT INTO `bulletin` VALUES ('159', '1', '2010-03-04', 'sfdgfd', 'sysRepair', null, 'gfsdg');
INSERT INTO `customer` VALUES ('0', 'tom', 'NULL', null, null);
INSERT INTO `customer` VALUES ('1', 'tomer', 'NULL', null, null);
INSERT INTO `metaauthority` VALUES ('1', 'all', '0000000001', '读取，修改，删除，添加');
INSERT INTO `metaauthority` VALUES ('2', 'read', '0000000010', '读取');
INSERT INTO `metaauthority` VALUES ('3', 'edit', '0000000100', '修改');
INSERT INTO `metaauthority` VALUES ('4', 'delete', '0000001000', '删除');
INSERT INTO `metaauthority` VALUES ('5', 'add', '0000010000', '添加');
INSERT INTO `model` VALUES ('1', 'CX/45E', '', 'rwerer', 'test', null);
INSERT INTO `organization` VALUES ('1', null, '软件部', '1', '-1', null, null);
INSERT INTO `organization` VALUES ('2', null, '维修组A', '2', '0', null, null);
INSERT INTO `organization` VALUES ('3', '1', '维修组B', '1', '1', null, 'afasd');
INSERT INTO `repairorder` VALUES ('2', '1', '1', null, null, '0', 'wqx', '2010-03-03', null, 'testLocation', '', null, '', '', '', null, 'no problem', '', '1', '更新済み', null);
INSERT INTO `repairorder` VALUES ('3', '1', null, null, null, '0', 'hua', '2010-03-03', null, 'testLocation', null, null, null, null, null, null, 'no problem', null, '0', '登録済み', null);
INSERT INTO `resource` VALUES ('1', '/pj_ms/', null, '日本华硕维修系统');
INSERT INTO `resource` VALUES ('8', 'wrewr', null, 'werwer');
INSERT INTO `resource` VALUES ('11', 'rqewr', null, 'ewqrewq');
INSERT INTO `resource` VALUES ('12', '/pj_ms/orderMgr.do?task=view&scope=repairer', null, '维修人员链接');
INSERT INTO `resource` VALUES ('13', '/pj_ms', null, '日本华硕维修系统');
INSERT INTO `user` VALUES ('1', 'admin', 'adminx', 'admin', 'male', '123', 'fsd', 'htb@htb.cn', 'onLine', '4AAA5093FB4FC74EFA5930AA44604831', null, null);
INSERT INTO `user` VALUES ('2', 'test', 'test', 'test', 'male', '524234', 'testfsd', 'test@test.com', 'outLine', null, null, null);
