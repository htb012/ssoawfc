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
  `agentid` int(11) NOT NULL COMMENT '�����̱��',
  `agentname` varchar(20) NOT NULL COMMENT '����������',
  `agentphone` varchar(20) NOT NULL COMMENT '��������ϵ�绰',
  `agentemail` varchar(30) DEFAULT NULL COMMENT '��������ϵmail',
  `agentextend` char(10) DEFAULT NULL COMMENT '��չ',
  PRIMARY KEY (`agentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='������';

-- ----------------------------
-- Table structure for authgrp_auth
-- ----------------------------
DROP TABLE IF EXISTS `authgrp_auth`;
CREATE TABLE `authgrp_auth` (
  `authgroupid` int(11) NOT NULL,
  `resid` int(11) NOT NULL COMMENT '���',
  `metaauthid` int(11) NOT NULL COMMENT 'ԪȨ�޵�id',
  `authid` int(11) NOT NULL,
  PRIMARY KEY (`authgroupid`,`authid`),
  KEY `FK290826A1FEF3255` (`authgroupid`),
  KEY `FK290826A211CA39F` (`authid`),
  CONSTRAINT `authgrp_auth_ibfk_1` FOREIGN KEY (`authgroupid`) REFERENCES `authoritygroup` (`authgroupid`) ON DELETE CASCADE,
  CONSTRAINT `authgrp_auth_ibfk_2` FOREIGN KEY (`authid`) REFERENCES `authority` (`authid`) ON DELETE CASCADE,
  CONSTRAINT `authgrp_auth_ibfk_3` FOREIGN KEY (`authgroupid`) REFERENCES `authoritygroup` (`authgroupid`) ON DELETE CASCADE,
  CONSTRAINT `authgrp_auth_ibfk_4` FOREIGN KEY (`authid`) REFERENCES `authority` (`authid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='������';

-- ----------------------------
-- Table structure for authgrp_user
-- ----------------------------
DROP TABLE IF EXISTS `authgrp_user`;
CREATE TABLE `authgrp_user` (
  `authgroupid` int(11) NOT NULL,
  `userid` int(11) NOT NULL COMMENT '�û����',
  PRIMARY KEY (`authgroupid`,`userid`),
  KEY `FK299908D1FEF3255` (`authgroupid`),
  KEY `FK299908DD6EC06B8` (`userid`),
  CONSTRAINT `authgrp_user_ibfk_1` FOREIGN KEY (`authgroupid`) REFERENCES `authoritygroup` (`authgroupid`) ON DELETE CASCADE,
  CONSTRAINT `authgrp_user_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE,
  CONSTRAINT `authgrp_user_ibfk_3` FOREIGN KEY (`authgroupid`) REFERENCES `authoritygroup` (`authgroupid`) ON DELETE CASCADE,
  CONSTRAINT `authgrp_user_ibfk_4` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��������';

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `resid` int(11) NOT NULL COMMENT '���',
  `metaauthid` int(11) NOT NULL COMMENT 'ԪȨ�޵�id',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Ȩ�ޱ�\r\n           ������Դ��ԪȨ�޵ļ�¼��\r\n           ��';

-- ----------------------------
-- Table structure for authoritygroup
-- ----------------------------
DROP TABLE IF EXISTS `authoritygroup`;
CREATE TABLE `authoritygroup` (
  `authgroupid` int(11) NOT NULL,
  `authgroupname` varchar(30) NOT NULL,
  `authgroupcreatetime` datetime NOT NULL COMMENT 'Ȩ���鴴��ʱ��',
  `authgroupextend` char(10) DEFAULT NULL,
  `authgroupdiscription` text,
  PRIMARY KEY (`authgroupid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Ȩ���顣\r\n           �ɽ����Ȩ�޹��ɵ�һ��Ȩ�����С����ѽ��';

-- ----------------------------
-- Table structure for bulletin
-- ----------------------------
DROP TABLE IF EXISTS `bulletin`;
CREATE TABLE `bulletin` (
  `userid` int(11) NOT NULL COMMENT '�û����',
  `bulletinid` int(11) NOT NULL,
  `publishdate` datetime NOT NULL COMMENT '��������',
  `bulletintitle` varchar(50) DEFAULT NULL,
  `content` text,
  `bulletintype` char(10) NOT NULL COMMENT '���������',
  `bulletinextend` char(10) DEFAULT NULL,
  PRIMARY KEY (`bulletinid`),
  KEY `FKAFD939A7D6EC06B8` (`userid`),
  CONSTRAINT `bulletin_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE,
  CONSTRAINT `bulletin_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='���档��������bulletintype��ʾ��������͡�������������Ŀ���';

-- ----------------------------
-- Table structure for contact
-- ----------------------------
DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact` (
  `repairorderid` int(11) NOT NULL,
  `userid` int(11) NOT NULL COMMENT '�û����',
  `contactid` int(11) NOT NULL,
  `contactdatetime` datetime DEFAULT NULL COMMENT '����ʱ��',
  `note` text,
  `customresponse` text COMMENT '�û��ظ�',
  `customrequire` text COMMENT '�û�����',
  `contactvalidate` char(10) DEFAULT NULL COMMENT 'ȷ������',
  `contactextend` char(10) DEFAULT NULL,
  PRIMARY KEY (`contactid`),
  KEY `FK38B72420EE1CC1F6` (`repairorderid`),
  KEY `FK38B72420D6EC06B8` (`userid`),
  CONSTRAINT `contact_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE,
  CONSTRAINT `contact_ibfk_2` FOREIGN KEY (`repairorderid`) REFERENCES `repairorder` (`repairorderid`) ON DELETE CASCADE,
  CONSTRAINT `contact_ibfk_3` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE,
  CONSTRAINT `contact_ibfk_4` FOREIGN KEY (`repairorderid`) REFERENCES `repairorder` (`repairorderid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��˿ͼ��������Ϣ��';

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `customerid` int(11) NOT NULL,
  `customername` char(32) NOT NULL,
  `customerphone` varchar(20) NOT NULL COMMENT 'ά�޿ͻ�����ϵ�绰',
  `customeremail` varchar(30) DEFAULT NULL,
  `customerextend` char(10) DEFAULT NULL,
  PRIMARY KEY (`customerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for metaauthority
-- ----------------------------
DROP TABLE IF EXISTS `metaauthority`;
CREATE TABLE `metaauthority` (
  `metaauthid` int(11) NOT NULL COMMENT 'ԪȨ�޵�id',
  `metaauthname` varchar(30) NOT NULL COMMENT 'ԪȨ����',
  `metaauthextend` char(10) DEFAULT NULL COMMENT '������չ����Ȩ�ޱ��ʽ��',
  `metaauthdiscription` text COMMENT '˵��',
  PRIMARY KEY (`metaauthid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Ȩ��Ԫ����\r\n           ���ڶ���Ȩ�޺�Ȩ�޵�˵�������ʽ�ȡ�';

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
  `userid` int(11) NOT NULL COMMENT '�û����',
  PRIMARY KEY (`orgid`,`userid`),
  KEY `FK4E5DA4461EAB1F9` (`orgid`),
  KEY `FK4E5DA446D6EC06B8` (`userid`),
  CONSTRAINT `org_user_ibfk_1` FOREIGN KEY (`orgid`) REFERENCES `organization` (`orgid`) ON DELETE CASCADE,
  CONSTRAINT `org_user_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE,
  CONSTRAINT `org_user_ibfk_3` FOREIGN KEY (`orgid`) REFERENCES `organization` (`orgid`) ON DELETE CASCADE,
  CONSTRAINT `org_user_ibfk_4` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��֯\r\n           һ��������֯ӵ�е�Ա��';

-- ----------------------------
-- Table structure for organization
-- ----------------------------
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization` (
  `orgid` int(11) NOT NULL,
  `authgroupid` int(11) DEFAULT NULL,
  `orgname` varchar(30) NOT NULL,
  `manager` int(11) DEFAULT NULL,
  `uplevel` int(11) DEFAULT NULL COMMENT '�ϼ���֯�ṹ',
  `orgextend` char(10) DEFAULT NULL,
  `orgdiscription` text,
  PRIMARY KEY (`orgid`),
  KEY `FK4644ED331FEF3255` (`authgroupid`),
  CONSTRAINT `organization_ibfk_5` FOREIGN KEY (`authgroupid`) REFERENCES `authoritygroup` (`authgroupid`),
  CONSTRAINT `organization_ibfk_6` FOREIGN KEY (`authgroupid`) REFERENCES `authoritygroup` (`authgroupid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��֯�ṹ��\r\n           ��ʵ�еĲ��Ż����ṹ��ÿ����¼�����';

-- ----------------------------
-- Table structure for repairorder
-- ----------------------------
DROP TABLE IF EXISTS `repairorder`;
CREATE TABLE `repairorder` (
  `repairorderid` int(11) NOT NULL,
  `modelid` int(11) DEFAULT NULL,
  `agentid` int(11) DEFAULT NULL COMMENT '�����̱��',
  `orgid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL COMMENT '�û����',
  `customerid` int(11) DEFAULT NULL,
  `rmano` varchar(20) NOT NULL,
  `startdate` date NOT NULL,
  `checkindatetime` datetime DEFAULT NULL,
  `location` varchar(20) NOT NULL COMMENT '����Ŀǰ���ڵ�',
  `parts` varchar(30) DEFAULT NULL COMMENT 'Ҫ�����Ĳ���',
  `stock` tinyint(1) DEFAULT NULL COMMENT '�Ƿ�������ӦƷ',
  `waitparts` varchar(30) DEFAULT NULL COMMENT '�ȴ��Ĳ�����',
  `sn` varchar(20) DEFAULT NULL,
  `pn` text,
  `warranty` char(10) DEFAULT NULL,
  `validate` char(10) DEFAULT NULL COMMENT '��֤�ֶ�10λ������10����ͬ����',
  `problem` text NOT NULL COMMENT '��������',
  `photo` text COMMENT '��Ƭ��ַ',
  `isagentrepair` tinyint(1) DEFAULT NULL COMMENT '�Ƿ��Ǵ�����ά��',
  `repairorderstate` varchar(10) NOT NULL COMMENT 'ά��״̬',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='�����û���ά�޶�������ԭ��excel�ļ��г�ΪEND��\r\n          ';

-- ----------------------------
-- Table structure for repairorderbackup
-- ----------------------------
DROP TABLE IF EXISTS `repairorderbackup`;
CREATE TABLE `repairorderbackup` (
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
  `resid` int(11) NOT NULL COMMENT '���',
  `resname` varchar(50) NOT NULL COMMENT '��Դ����',
  `resextend` char(10) DEFAULT NULL COMMENT '��չ',
  `resdiscription` text,
  PRIMARY KEY (`resid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='�ܱ�������Դ';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` int(11) NOT NULL COMMENT '�û����',
  `username` varchar(30) NOT NULL COMMENT '�û���',
  `realname` varchar(30) DEFAULT NULL COMMENT '�û���ʵ��',
  `password` varchar(30) NOT NULL COMMENT '�û�����',
  `sex` varchar(8) DEFAULT NULL COMMENT '�Ա�',
  `phone` varchar(20) DEFAULT NULL COMMENT '��ϵ�绰',
  `address` varchar(50) DEFAULT NULL COMMENT '��ַ',
  `email` varchar(30) DEFAULT NULL COMMENT 'Email��ַ',
  `state` varchar(10) NOT NULL COMMENT '״̬',
  `securitykey` varchar(32) DEFAULT NULL COMMENT '��ȫ��',
  `userextend` char(10) DEFAULT NULL,
  `userdiscription` text,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='�û���Ա����ȡ�';

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `agent` VALUES ('1', '丸紅インフォテック㈱', '03-5665-8511', null, null);
INSERT INTO `agent` VALUES ('2', '㈱ユニティ', '03-5812-6121', '', null);
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
INSERT INTO `authority` VALUES ('1', '1', '1', null, '社内用オンライン業務システム');
INSERT INTO `authority` VALUES ('2', '1', '2', null, '社内オンライン業務システム');
INSERT INTO `authority` VALUES ('3', '1', '3', null, '管理業務');
INSERT INTO `authority` VALUES ('4', '1', '4', null, 'システムリペア業務');
INSERT INTO `authority` VALUES ('5', '1', '5', null, 'サービスセンター業務');
INSERT INTO `authority` VALUES ('6', '1', '6', null, '回答待ち');
INSERT INTO `authority` VALUES ('7', '1', '10', null, '登録済み');
INSERT INTO `authority` VALUES ('8', '1', '11', null, '連絡開始');
INSERT INTO `authority` VALUES ('9', '1', '12', null, '更新済み');
INSERT INTO `authority` VALUES ('11', '1', '14', null, '連絡完了');
INSERT INTO `authority` VALUES ('12', '1', '15', null, '修理完了');
INSERT INTO `authority` VALUES ('13', '1', '16', null, '業務終了');
INSERT INTO `authority` VALUES ('14', '1', '17', null, 'sso管理システム');
INSERT INTO `authority` VALUES ('15', '1', '18', null, 'sso管理システム ');
INSERT INTO `authority` VALUES ('16', '1', '19', null, 'sso单点登录管理链接页面');
INSERT INTO `authority` VALUES ('17', '1', '20', null, 'sso单点登录管理首页面');
INSERT INTO `authoritygroup` VALUES ('1', '管理業務', '2010-03-14 17:19:39', null, '管理業務に関する権限');
INSERT INTO `authoritygroup` VALUES ('2', 'システムリペア業務', '2010-03-14 17:20:47', null, 'システムリペア業務に関する権限');
INSERT INTO `authoritygroup` VALUES ('3', 'サービスセンター業務', '2010-03-14 17:21:20', null, 'サービスセンター業務に関する権限');
INSERT INTO `metaauthority` VALUES ('1', 'all', '1111', '読み込む、書き込む、編集、削除');
INSERT INTO `metaauthority` VALUES ('2', 'write', '111', '書き込む');
INSERT INTO `metaauthority` VALUES ('3', 'add', '11', '追加');
INSERT INTO `metaauthority` VALUES ('4', 'read', '1', '読み込む');
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
INSERT INTO `model` VALUES ('25', 'Eee PC 1101HA (Windows® 7)', '', '', '', null);
INSERT INTO `model` VALUES ('26', 'Eee PC T91MT', '', '', '', null);
INSERT INTO `model` VALUES ('27', 'Eee PC 1005PE', '', '', '', null);
INSERT INTO `model` VALUES ('28', 'Eee PC 1008KR', '', '', '', null);
INSERT INTO `org_user` VALUES ('0', '1');
INSERT INTO `org_user` VALUES ('1', '3');
INSERT INTO `org_user` VALUES ('2', '2');
INSERT INTO `organization` VALUES ('0', '1', 'ペガトロン・ジャパン㈱', '1', '0', null, 'ペガトロン・ジャパン㈱');
INSERT INTO `organization` VALUES ('1', '2', 'EeePC', '1', '0', null, 'EeePCリペア（システム系）');
INSERT INTO `organization` VALUES ('2', '3', 'サービスセンター', '1', '0', null, 'サービス窓口');
INSERT INTO `resource` VALUES ('1', '/pj_ms/', null, '社内オンライン業務管理システム');
INSERT INTO `resource` VALUES ('2', '/pj_ms', null, '社内オンライン業務管理システム');
INSERT INTO `resource` VALUES ('3', '/pj_ms/manageBus', null, '管理業務');
INSERT INTO `resource` VALUES ('4', '/pj_ms/systemRepair', null, 'リペア業務');
INSERT INTO `resource` VALUES ('5', '/pj_ms/serviceCenter', null, 'サービスセンター業務');
INSERT INTO `resource` VALUES ('6', '/pj_ms/contact/waitResponse', null, '回答待ち');
INSERT INTO `resource` VALUES ('7', '/pj_ms/contact/logoned', null, '登録済み');
INSERT INTO `resource` VALUES ('8', '/pj_ms/contact/contactStart', null, '連絡開始');
INSERT INTO `resource` VALUES ('9', '/pj_ms/contact/updated', null, '更新済み');
INSERT INTO `resource` VALUES ('11', '/pj_ms/contact/contacted', null, '連絡完了');
INSERT INTO `resource` VALUES ('12', '/pj_ms/contact/repaired', null, '修理完了');
INSERT INTO `resource` VALUES ('13', '/pj_ms/contact/finished', null, '業務終了');
INSERT INTO `resource` VALUES ('14', '/sso/', null, 'sso管理システム');
INSERT INTO `resource` VALUES ('15', '/sso', null, 'sso管理システム ');
INSERT INTO `resource` VALUES ('16', '/sso/title.html', null, 'sso单点登录管理链接页面');
INSERT INTO `resource` VALUES ('17', '/sso/index.jsp', null, 'sso单点登录管理首页');
INSERT INTO `user` VALUES ('1', 'admin', '管理者', 'admin', 'male', '000-0000-0000', '総務課', 'admin@pegatroncorp.com', 'outLine', null, null, null);
INSERT INTO `user` VALUES ('2', 'repairer', 'repair従業員', 'repairer', 'male', '000-0000-0000', '', 'repairer@pegatroncorp.com', 'outLine', null, null, null);
INSERT INTO `user` VALUES ('3', 'contacter', 'contact従業員', 'contacter', 'male', '000-0000-0000', '', 'contacter@pegatroncorp.com', 'onLine', '2CA0B9F164C6001B7D961FED1E54ECDF', null, null);
