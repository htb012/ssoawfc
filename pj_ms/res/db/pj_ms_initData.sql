/*
MySQL Data Transfer
Source Host: localhost
Source Database: pj_ms
Target Host: localhost
Target Database: pj_ms
Date: 2010/3/26 22:51:46
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
  `agentemail` varchar(30) DEFAULT NULL COMMENT '代理商联系mail',
  `agentextend` char(10) DEFAULT NULL COMMENT '扩展',
  PRIMARY KEY (`agentid`)
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
  PRIMARY KEY (`authgroupid`,`authid`),
  KEY `FK290826A1FEF3255` (`authgroupid`),
  KEY `FK290826A211CA39F` (`authid`),
  CONSTRAINT `authgrp_auth_ibfk_1` FOREIGN KEY (`authgroupid`) REFERENCES `authoritygroup` (`authgroupid`) ON DELETE CASCADE,
  CONSTRAINT `authgrp_auth_ibfk_2` FOREIGN KEY (`authid`) REFERENCES `authority` (`authid`) ON DELETE CASCADE,
  CONSTRAINT `authgrp_auth_ibfk_3` FOREIGN KEY (`authgroupid`) REFERENCES `authoritygroup` (`authgroupid`) ON DELETE CASCADE,
  CONSTRAINT `authgrp_auth_ibfk_4` FOREIGN KEY (`authid`) REFERENCES `authority` (`authid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='包含有';

-- ----------------------------
-- Table structure for authgrp_user
-- ----------------------------
DROP TABLE IF EXISTS `authgrp_user`;
CREATE TABLE `authgrp_user` (
  `authgroupid` int(11) NOT NULL,
  `userid` int(11) NOT NULL COMMENT '用户编号',
  PRIMARY KEY (`authgroupid`,`userid`),
  KEY `FK299908D1FEF3255` (`authgroupid`),
  KEY `FK299908DD6EC06B8` (`userid`),
  CONSTRAINT `authgrp_user_ibfk_1` FOREIGN KEY (`authgroupid`) REFERENCES `authoritygroup` (`authgroupid`) ON DELETE CASCADE,
  CONSTRAINT `authgrp_user_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE,
  CONSTRAINT `authgrp_user_ibfk_3` FOREIGN KEY (`authgroupid`) REFERENCES `authoritygroup` (`authgroupid`) ON DELETE CASCADE,
  CONSTRAINT `authgrp_user_ibfk_4` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='单独分派';

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `resid` int(11) NOT NULL COMMENT '编号',
  `metaauthid` int(11) NOT NULL COMMENT '元权限的id',
  `authid` int(11) NOT NULL,
  `authextend` char(10) DEFAULT NULL,
  `authdiscription` text,
  PRIMARY KEY (`authid`),
  KEY `FK57F407433731CCFF` (`metaauthid`),
  KEY `FK57F407435E6C0F10` (`resid`),
  CONSTRAINT `authority_ibfk_1` FOREIGN KEY (`metaauthid`) REFERENCES `metaauthority` (`metaauthid`) ON DELETE CASCADE,
  CONSTRAINT `authority_ibfk_2` FOREIGN KEY (`resid`) REFERENCES `resource` (`resid`) ON DELETE CASCADE,
  CONSTRAINT `authority_ibfk_3` FOREIGN KEY (`metaauthid`) REFERENCES `metaauthority` (`metaauthid`) ON DELETE CASCADE,
  CONSTRAINT `authority_ibfk_4` FOREIGN KEY (`resid`) REFERENCES `resource` (`resid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表。\r\n           集合资源和元权限的记录。\r\n           如';

-- ----------------------------
-- Table structure for authoritygroup
-- ----------------------------
DROP TABLE IF EXISTS `authoritygroup`;
CREATE TABLE `authoritygroup` (
  `authgroupid` int(11) NOT NULL,
  `authgroupname` varchar(30) NOT NULL,
  `authgroupcreatetime` datetime NOT NULL COMMENT '权限组创建时间',
  `authgroupextend` char(10) DEFAULT NULL,
  `authgroupdiscription` text,
  PRIMARY KEY (`authgroupid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限组。\r\n           可将多个权限归纳到一个权限组中。并把将�';

-- ----------------------------
-- Table structure for bulletin
-- ----------------------------
DROP TABLE IF EXISTS `bulletin`;
CREATE TABLE `bulletin` (
  `userid` int(11) NOT NULL COMMENT '用户编号',
  `bulletinid` int(11) NOT NULL,
  `publishdate` datetime NOT NULL COMMENT '发布日期',
  `bulletintitle` varchar(50) DEFAULT NULL,
  `content` text,
  `bulletintype` char(10) NOT NULL COMMENT '公告的类型',
  `bulletinextend` char(10) DEFAULT NULL,
  PRIMARY KEY (`bulletinid`),
  KEY `FKAFD939A7D6EC06B8` (`userid`),
  CONSTRAINT `bulletin_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE,
  CONSTRAINT `bulletin_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公告。其中属性bulletintype表示公告的类型。公告所发布的目标地';

-- ----------------------------
-- Table structure for contact
-- ----------------------------
DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact` (
  `repairorderid` int(11) NOT NULL,
  `userid` int(11) NOT NULL COMMENT '用户编号',
  `contactid` int(11) NOT NULL,
  `contactdatetime` datetime DEFAULT NULL COMMENT '连络时间',
  `note` text,
  `customresponse` text COMMENT '用户回复',
  `customrequire` text COMMENT '用户请求',
  `contactvalidate` char(10) DEFAULT NULL COMMENT '确认事项',
  `contactextend` char(10) DEFAULT NULL,
  PRIMARY KEY (`contactid`),
  KEY `FK38B72420EE1CC1F6` (`repairorderid`),
  KEY `FK38B72420D6EC06B8` (`userid`),
  CONSTRAINT `contact_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE,
  CONSTRAINT `contact_ibfk_2` FOREIGN KEY (`repairorderid`) REFERENCES `repairorder` (`repairorderid`) ON DELETE CASCADE,
  CONSTRAINT `contact_ibfk_3` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE,
  CONSTRAINT `contact_ibfk_4` FOREIGN KEY (`repairorderid`) REFERENCES `repairorder` (`repairorderid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='与顾客间的联络信息。';

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `customerid` int(11) NOT NULL,
  `customername` char(32) NOT NULL,
  `customerphone` varchar(20) NOT NULL COMMENT '维修客户的联系电话',
  `customeremail` varchar(30) DEFAULT NULL,
  `customerextend` char(10) DEFAULT NULL,
  PRIMARY KEY (`customerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for metaauthority
-- ----------------------------
DROP TABLE IF EXISTS `metaauthority`;
CREATE TABLE `metaauthority` (
  `metaauthid` int(11) NOT NULL COMMENT '元权限的id',
  `metaauthname` varchar(30) NOT NULL COMMENT '元权限名',
  `metaauthextend` char(10) DEFAULT NULL COMMENT '用于扩展，如权限表达式等',
  `metaauthdiscription` text COMMENT '说明',
  PRIMARY KEY (`metaauthid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限元数据\r\n           用于定义权限和权限的说明及表达式等。';

-- ----------------------------
-- Table structure for model
-- ----------------------------
DROP TABLE IF EXISTS `model`;
CREATE TABLE `model` (
  `modelid` int(11) NOT NULL,
  `modelname` varchar(30) NOT NULL,
  `modelphoto` varchar(100) DEFAULT NULL,
  `parameter` text,
  `linkmark` text,
  `modelextend` char(10) DEFAULT NULL,
  PRIMARY KEY (`modelid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for org_user
-- ----------------------------
DROP TABLE IF EXISTS `org_user`;
CREATE TABLE `org_user` (
  `orgid` int(11) NOT NULL,
  `userid` int(11) NOT NULL COMMENT '用户编号',
  PRIMARY KEY (`orgid`,`userid`),
  KEY `FK4E5DA4461EAB1F9` (`orgid`),
  KEY `FK4E5DA446D6EC06B8` (`userid`),
  CONSTRAINT `org_user_ibfk_1` FOREIGN KEY (`orgid`) REFERENCES `organization` (`orgid`) ON DELETE CASCADE,
  CONSTRAINT `org_user_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE,
  CONSTRAINT `org_user_ibfk_3` FOREIGN KEY (`orgid`) REFERENCES `organization` (`orgid`) ON DELETE CASCADE,
  CONSTRAINT `org_user_ibfk_4` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织\r\n           一个部门组织拥有的员工';

-- ----------------------------
-- Table structure for organization
-- ----------------------------
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization` (
  `orgid` int(11) NOT NULL,
  `authgroupid` int(11) DEFAULT NULL,
  `orgname` varchar(30) NOT NULL,
  `manager` int(11) DEFAULT NULL,
  `uplevel` int(11) DEFAULT NULL COMMENT '上级组织结构',
  `orgextend` char(10) DEFAULT NULL,
  `orgdiscription` text,
  PRIMARY KEY (`orgid`),
  KEY `FK4644ED331FEF3255` (`authgroupid`),
  CONSTRAINT `organization_ibfk_5` FOREIGN KEY (`authgroupid`) REFERENCES `authoritygroup` (`authgroupid`),
  CONSTRAINT `organization_ibfk_6` FOREIGN KEY (`authgroupid`) REFERENCES `authoritygroup` (`authgroupid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织结构。\r\n           现实中的部门或组别结构。每条记录代表�';

-- ----------------------------
-- Table structure for repairorder
-- ----------------------------
DROP TABLE IF EXISTS `repairorder`;
CREATE TABLE `repairorder` (
  `repairorderid` int(11) NOT NULL,
  `modelid` int(11) DEFAULT NULL,
  `agentid` int(11) DEFAULT NULL COMMENT '代理商编号',
  `orgid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL COMMENT '用户编号',
  `customerid` int(11) DEFAULT NULL,
  `rmano` varchar(20) NOT NULL,
  `startdate` date NOT NULL,
  `checkindatetime` datetime DEFAULT NULL,
  `location` varchar(20) NOT NULL COMMENT '机器目前所在地',
  `parts` varchar(30) DEFAULT NULL COMMENT '要更换的部件',
  `stock` tinyint(1) DEFAULT NULL COMMENT '是否有修理供应品',
  `waitparts` varchar(30) DEFAULT NULL COMMENT '等待的部件名',
  `sn` varchar(20) DEFAULT NULL,
  `pn` text,
  `warranty` char(10) DEFAULT NULL,
  `validate` char(10) DEFAULT NULL COMMENT '验证字段10位，代表10个不同含义',
  `problem` text NOT NULL COMMENT '故障描述',
  `photo` text COMMENT '照片地址',
  `isagentrepair` tinyint(1) DEFAULT NULL COMMENT '是否是代理商维修',
  `repairorderstate` varchar(10) NOT NULL COMMENT '维修状态',
  `repairorderexpend` char(10) DEFAULT NULL,
  PRIMARY KEY (`repairorderid`),
  KEY `FK645C17E1CEFD15FE` (`agentid`),
  KEY `FK645C17E11EAB1F9` (`orgid`),
  KEY `FK645C17E1322CBA9E` (`customerid`),
  KEY `FK645C17E158090D46` (`modelid`),
  KEY `FK645C17E1D6EC06B8` (`userid`),
  CONSTRAINT `repairorder_ibfk_10` FOREIGN KEY (`agentid`) REFERENCES `agent` (`agentid`) ON DELETE CASCADE,
  CONSTRAINT `repairorder_ibfk_11` FOREIGN KEY (`orgid`) REFERENCES `organization` (`orgid`) ON DELETE NO ACTION,
  CONSTRAINT `repairorder_ibfk_12` FOREIGN KEY (`customerid`) REFERENCES `customer` (`customerid`) ON DELETE CASCADE,
  CONSTRAINT `repairorder_ibfk_13` FOREIGN KEY (`modelid`) REFERENCES `model` (`modelid`) ON DELETE NO ACTION,
  CONSTRAINT `repairorder_ibfk_14` FOREIGN KEY (`agentid`) REFERENCES `agent` (`agentid`) ON DELETE CASCADE,
  CONSTRAINT `repairorder_ibfk_15` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE NO ACTION,
  CONSTRAINT `repairorder_ibfk_16` FOREIGN KEY (`modelid`) REFERENCES `model` (`modelid`) ON DELETE NO ACTION,
  CONSTRAINT `repairorder_ibfk_17` FOREIGN KEY (`orgid`) REFERENCES `organization` (`orgid`) ON DELETE NO ACTION,
  CONSTRAINT `repairorder_ibfk_18` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE NO ACTION,
  CONSTRAINT `repairorder_ibfk_19` FOREIGN KEY (`customerid`) REFERENCES `customer` (`customerid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='个人用户的维修订单，在原有excel文件中称为END表。\r\n          ';

-- ----------------------------
-- Table structure for repairorderbackup
-- ----------------------------
DROP TABLE IF EXISTS `repairorderbackup`;
CREATE TABLE `repairorderbackup` (
  `repairorderid` int(11) NOT NULL,
  `modelid` int(11) DEFAULT NULL,
  `agentid` int(11) DEFAULT NULL COMMENT '浠ｇ悊鍟嗙紪鍙�',
  `orgid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL COMMENT '鐢ㄦ埛缂栧彿',
  `customerid` int(11) DEFAULT NULL,
  `rmano` varchar(20) NOT NULL,
  `startdate` date NOT NULL,
  `checkindatetime` datetime DEFAULT NULL,
  `location` varchar(20) NOT NULL COMMENT '鏈哄櫒鐩墠鎵�鍦ㄥ湴',
  `parts` varchar(30) DEFAULT NULL COMMENT '瑕佹洿鎹㈢殑閮ㄤ欢',
  `stock` tinyint(1) DEFAULT NULL COMMENT '鏄惁鏈変慨鐞嗕緵搴斿搧',
  `waitparts` varchar(30) DEFAULT NULL COMMENT '绛夊緟鐨勯儴浠跺悕',
  `sn` varchar(20) DEFAULT NULL,
  `pn` text,
  `validate` char(10) DEFAULT NULL COMMENT '楠岃瘉瀛楁10浣嶏紝浠ｈ〃10涓笉鍚屽惈涔�',
  `problem` text NOT NULL COMMENT '鏁呴殰鎻忚堪',
  `photo` text COMMENT '鐓х墖鍦板潃',
  `isagentrepair` tinyint(1) DEFAULT NULL COMMENT '鏄惁鏄唬鐞嗗晢缁翠慨',
  `repairorderstate` varchar(10) NOT NULL COMMENT '缁翠慨鐘舵��',
  `repairorderexpend` char(10) DEFAULT NULL,
  PRIMARY KEY (`repairorderid`),
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='涓汉鐢ㄦ埛鐨勭淮淇鍗曪紝鍦ㄥ師鏈塭xcel鏂囦欢涓О涓篍';

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `resid` int(11) NOT NULL COMMENT '编号',
  `resname` varchar(50) NOT NULL COMMENT '资源名称',
  `resextend` char(10) DEFAULT NULL COMMENT '扩展',
  `resdiscription` text,
  PRIMARY KEY (`resid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='受保护的资源';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` int(11) NOT NULL COMMENT '用户编号',
  `username` varchar(30) NOT NULL COMMENT '用户名',
  `realname` varchar(30) DEFAULT NULL COMMENT '用户真实名',
  `password` varchar(30) NOT NULL COMMENT '用户密码',
  `sex` varchar(8) DEFAULT NULL COMMENT '性别',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `address` varchar(50) DEFAULT NULL COMMENT '地址',
  `email` varchar(30) DEFAULT NULL COMMENT 'Email地址',
  `state` varchar(10) NOT NULL COMMENT '状态',
  `securitykey` varchar(32) DEFAULT NULL COMMENT '安全码',
  `userextend` char(10) DEFAULT NULL,
  `userdiscription` text,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表，员工表等。';

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `agent` VALUES ('1', '涓哥磪銈ゃ兂銉曘偐銉嗐儍銈埍', '03-5665-8511', null, null);
INSERT INTO `agent` VALUES ('2', '銏便儲銉嬨儐銈�', '03-5812-6121', '', null);
INSERT INTO `authgrp_auth` VALUES ('1', '1', '1', '1');
INSERT INTO `authgrp_auth` VALUES ('1', '2', '1', '2');
INSERT INTO `authgrp_auth` VALUES ('1', '3', '1', '3');
INSERT INTO `authgrp_auth` VALUES ('1', '4', '1', '4');
INSERT INTO `authgrp_auth` VALUES ('1', '5', '1', '5');
INSERT INTO `authgrp_auth` VALUES ('1', '6', '1', '6');
INSERT INTO `authgrp_auth` VALUES ('1', '7', '1', '10');
INSERT INTO `authgrp_auth` VALUES ('1', '8', '1', '11');
INSERT INTO `authgrp_auth` VALUES ('1', '9', '1', '12');
INSERT INTO `authgrp_auth` VALUES ('1', '11', '1', '14');
INSERT INTO `authgrp_auth` VALUES ('1', '12', '1', '15');
INSERT INTO `authgrp_auth` VALUES ('1', '13', '1', '16');
INSERT INTO `authgrp_auth` VALUES ('1', '14', '1', '17');
INSERT INTO `authgrp_auth` VALUES ('1', '15', '1', '18');
INSERT INTO `authgrp_auth` VALUES ('1', '16', '1', '19');
INSERT INTO `authgrp_auth` VALUES ('1', '17', '1', '20');
INSERT INTO `authgrp_auth` VALUES ('2', '1', '1', '1');
INSERT INTO `authgrp_auth` VALUES ('2', '2', '1', '2');
INSERT INTO `authgrp_auth` VALUES ('2', '4', '1', '4');
INSERT INTO `authgrp_auth` VALUES ('2', '9', '1', '12');
INSERT INTO `authgrp_auth` VALUES ('2', '12', '1', '15');
INSERT INTO `authgrp_auth` VALUES ('3', '1', '1', '1');
INSERT INTO `authgrp_auth` VALUES ('3', '2', '1', '2');
INSERT INTO `authgrp_auth` VALUES ('3', '5', '1', '5');
INSERT INTO `authgrp_auth` VALUES ('3', '6', '1', '6');
INSERT INTO `authgrp_auth` VALUES ('3', '8', '1', '11');
INSERT INTO `authgrp_auth` VALUES ('3', '11', '1', '14');
INSERT INTO `authgrp_auth` VALUES ('3', '13', '1', '16');
INSERT INTO `authority` VALUES ('1', '1', '1', null, '绀惧唴鐢ㄣ偑銉炽儵銈ゃ兂妤嫏銈枫偣銉嗐儬');
INSERT INTO `authority` VALUES ('2', '1', '2', null, '绀惧唴銈兂銉┿偆銉虫キ鍕欍偡銈广儐銉�');
INSERT INTO `authority` VALUES ('3', '1', '3', null, '绠＄悊妤嫏');
INSERT INTO `authority` VALUES ('4', '1', '4', null, '銈枫偣銉嗐儬銉儦銈㈡キ鍕�');
INSERT INTO `authority` VALUES ('5', '1', '5', null, '銈点兗銉撱偣銈汇兂銈裤兗妤嫏');
INSERT INTO `authority` VALUES ('6', '1', '6', null, '鍥炵瓟寰呫仭');
INSERT INTO `authority` VALUES ('7', '1', '10', null, '鐧婚尣娓堛伩');
INSERT INTO `authority` VALUES ('8', '1', '11', null, '閫ｇ怠闁嬪');
INSERT INTO `authority` VALUES ('9', '1', '12', null, '鏇存柊娓堛伩');
INSERT INTO `authority` VALUES ('11', '1', '14', null, '閫ｇ怠瀹屼簡');
INSERT INTO `authority` VALUES ('12', '1', '15', null, '淇悊瀹屼簡');
INSERT INTO `authority` VALUES ('13', '1', '16', null, '妤嫏绲備簡');
INSERT INTO `authority` VALUES ('14', '1', '17', null, 'sso绠＄悊銈枫偣銉嗐儬');
INSERT INTO `authority` VALUES ('15', '1', '18', null, 'sso绠＄悊銈枫偣銉嗐儬 ');
INSERT INTO `authority` VALUES ('16', '1', '19', null, 'sso鍗曠偣鐧诲綍绠＄悊閾炬帴椤甸潰');
INSERT INTO `authority` VALUES ('17', '1', '20', null, 'sso鍗曠偣鐧诲綍绠＄悊棣栭〉闈�');
INSERT INTO `authoritygroup` VALUES ('1', '绠＄悊妤嫏', '2010-03-14 17:19:39', null, '绠＄悊妤嫏銇枹銇欍倠妯╅檺');
INSERT INTO `authoritygroup` VALUES ('2', '銈枫偣銉嗐儬銉儦銈㈡キ鍕�', '2010-03-14 17:20:47', null, '銈枫偣銉嗐儬銉儦銈㈡キ鍕欍伀闁€仚銈嬫ī闄�');
INSERT INTO `authoritygroup` VALUES ('3', '銈点兗銉撱偣銈汇兂銈裤兗妤嫏', '2010-03-14 17:21:20', null, '銈点兗銉撱偣銈汇兂銈裤兗妤嫏銇枹銇欍倠妯╅檺');
INSERT INTO `metaauthority` VALUES ('1', 'all', '1111', '瑾伩杈笺個銆佹浉銇嶈炯銈�銆佺法闆嗐�佸墛闄�');
INSERT INTO `metaauthority` VALUES ('2', 'write', '111', '鏇搞亶杈笺個');
INSERT INTO `metaauthority` VALUES ('3', 'add', '11', '杩藉姞');
INSERT INTO `metaauthority` VALUES ('4', 'read', '1', '瑾伩杈笺個');
INSERT INTO `model` VALUES ('1', 'Eee PC 701', '', '', '', null);
INSERT INTO `model` VALUES ('2', 'Eee PC 701SD', '', '', '', null);
INSERT INTO `model` VALUES ('3', 'Eee PC 900', '', '', '', null);
INSERT INTO `model` VALUES ('4', 'Eee PC 900HA', '', '', '', null);
INSERT INTO `model` VALUES ('5', 'Eee PC 901-12G', '', '', '', null);
INSERT INTO `model` VALUES ('6', 'Eee PC 901-16G', '', '', '', null);
INSERT INTO `model` VALUES ('7', 'Eee PC S101', '', '', '', null);
INSERT INTO `model` VALUES ('8', 'Eee PC S101H', '', '', '', null);
INSERT INTO `model` VALUES ('9', 'Eee PC 1000H', '', '', '', null);
INSERT INTO `model` VALUES ('10', 'Eee PC 1000HT', '', '', '', null);
INSERT INTO `model` VALUES ('11', 'Eee PC 1000HE', '', '', '', null);
INSERT INTO `model` VALUES ('12', 'Eee PC 1000HD', '', '', '', null);
INSERT INTO `model` VALUES ('13', 'Eee PC 1002HAE', '', '', '', null);
INSERT INTO `model` VALUES ('14', 'Eee PC 1002HA', '', '', '', null);
INSERT INTO `model` VALUES ('15', 'Eee PC 1003HAG', '', '', '', null);
INSERT INTO `model` VALUES ('16', 'Eee PC 1005HR', '', '', '', null);
INSERT INTO `model` VALUES ('17', 'Eee PC 1005HA Business', '', '', '', null);
INSERT INTO `model` VALUES ('18', 'Eee PC 1005HAG', '', '', '', null);
INSERT INTO `model` VALUES ('19', 'Eee PC 1005HA (Seashell)', '', '', '', null);
INSERT INTO `model` VALUES ('20', 'Eee PC 1008HA (Seashell)', '', '', '', null);
INSERT INTO `model` VALUES ('21', 'Eee PC 1101HA (Seashell)', '', '', '', null);
INSERT INTO `model` VALUES ('22', 'Disney Netbook', '', '', '', null);
INSERT INTO `model` VALUES ('23', 'Eee PC 1001HA (Seashell)', '', '', '', null);
INSERT INTO `model` VALUES ('24', 'Eee PC 1005HE', '', '', '', null);
INSERT INTO `model` VALUES ('25', 'Eee PC 1101HA (Windows庐 7)', '', '', '', null);
INSERT INTO `model` VALUES ('26', 'Eee PC T91MT', '', '', '', null);
INSERT INTO `model` VALUES ('27', 'Eee PC 1005PE', '', '', '', null);
INSERT INTO `model` VALUES ('28', 'Eee PC 1008KR', '', '', '', null);
INSERT INTO `org_user` VALUES ('0', '1');
INSERT INTO `org_user` VALUES ('1', '3');
INSERT INTO `org_user` VALUES ('2', '2');
INSERT INTO `organization` VALUES ('0', '1', '銉氥偓銉堛儹銉炽兓銈搞儯銉戙兂銏�', '1', '0', null, '銉氥偓銉堛儹銉炽兓銈搞儯銉戙兂銏�');
INSERT INTO `organization` VALUES ('1', '2', 'EeePC', '1', '0', null, 'EeePC銉儦銈紙銈枫偣銉嗐儬绯伙級');
INSERT INTO `organization` VALUES ('2', '3', '銈点兗銉撱偣銈汇兂銈裤兗', '1', '0', null, '銈点兗銉撱偣绐撳彛');
INSERT INTO `resource` VALUES ('1', '/pj_ms/', null, '绀惧唴銈兂銉┿偆銉虫キ鍕欑鐞嗐偡銈广儐銉�');
INSERT INTO `resource` VALUES ('2', '/pj_ms', null, '绀惧唴銈兂銉┿偆銉虫キ鍕欑鐞嗐偡銈广儐銉�');
INSERT INTO `resource` VALUES ('3', '/pj_ms/manageBus', null, '绠＄悊妤嫏');
INSERT INTO `resource` VALUES ('4', '/pj_ms/systemRepair', null, '銉儦銈㈡キ鍕�');
INSERT INTO `resource` VALUES ('5', '/pj_ms/serviceCenter', null, '銈点兗銉撱偣銈汇兂銈裤兗妤嫏');
INSERT INTO `resource` VALUES ('6', '/pj_ms/contact/waitResponse', null, '鍥炵瓟寰呫仭');
INSERT INTO `resource` VALUES ('7', '/pj_ms/contact/logoned', null, '鐧婚尣娓堛伩');
INSERT INTO `resource` VALUES ('8', '/pj_ms/contact/contactStart', null, '閫ｇ怠闁嬪');
INSERT INTO `resource` VALUES ('9', '/pj_ms/contact/updated', null, '鏇存柊娓堛伩');
INSERT INTO `resource` VALUES ('11', '/pj_ms/contact/contacted', null, '閫ｇ怠瀹屼簡');
INSERT INTO `resource` VALUES ('12', '/pj_ms/contact/repaired', null, '淇悊瀹屼簡');
INSERT INTO `resource` VALUES ('13', '/pj_ms/contact/finished', null, '妤嫏绲備簡');
INSERT INTO `resource` VALUES ('14', '/sso/', null, 'sso绠＄悊銈枫偣銉嗐儬');
INSERT INTO `resource` VALUES ('15', '/sso', null, 'sso绠＄悊銈枫偣銉嗐儬 ');
INSERT INTO `resource` VALUES ('16', '/sso/title.html', null, 'sso鍗曠偣鐧诲綍绠＄悊閾炬帴椤甸潰');
INSERT INTO `resource` VALUES ('17', '/sso/index.jsp', null, 'sso鍗曠偣鐧诲綍绠＄悊棣栭〉');
INSERT INTO `user` VALUES ('1', 'admin', '绠＄悊鑰�', 'admin', 'male', '000-0000-0000', '绶忓嫏瑾�', 'admin@pegatroncorp.com', 'outLine', null, null, null);
INSERT INTO `user` VALUES ('2', 'repairer', 'repair寰撴キ鍝�', 'repairer', 'male', '000-0000-0000', '', 'repairer@pegatroncorp.com', 'outLine', null, null, null);
INSERT INTO `user` VALUES ('3', 'contacter', 'contact寰撴キ鍝�', 'contacter', 'male', '000-0000-0000', '', 'contacter@pegatroncorp.com', 'onLine', '2CA0B9F164C6001B7D961FED1E54ECDF', null, null);
