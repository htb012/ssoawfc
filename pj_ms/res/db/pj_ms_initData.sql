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
  `agentid` int(11) NOT NULL COMMENT '¥˙¿Ì…Ã±‡∫≈',
  `agentname` varchar(20) NOT NULL COMMENT '¥˙¿Ì…Ã√˚≥∆',
  `agentphone` varchar(20) NOT NULL COMMENT '¥˙¿Ì…Ã¡™œµµÁª∞',
  `agentemail` varchar(30) DEFAULT NULL COMMENT '¥˙¿Ì…Ã¡™œµmail',
  `agentextend` char(10) DEFAULT NULL COMMENT '¿©’π',
  PRIMARY KEY (`agentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='¥˙¿Ì…Ã';

-- ----------------------------
-- Table structure for authgrp_auth
-- ----------------------------
DROP TABLE IF EXISTS `authgrp_auth`;
CREATE TABLE `authgrp_auth` (
  `authgroupid` int(11) NOT NULL,
  `resid` int(11) NOT NULL COMMENT '±‡∫≈',
  `metaauthid` int(11) NOT NULL COMMENT '‘™»®œﬁµƒid',
  `authid` int(11) NOT NULL,
  PRIMARY KEY (`authgroupid`,`authid`),
  KEY `FK290826A1FEF3255` (`authgroupid`),
  KEY `FK290826A211CA39F` (`authid`),
  CONSTRAINT `authgrp_auth_ibfk_1` FOREIGN KEY (`authgroupid`) REFERENCES `authoritygroup` (`authgroupid`) ON DELETE CASCADE,
  CONSTRAINT `authgrp_auth_ibfk_2` FOREIGN KEY (`authid`) REFERENCES `authority` (`authid`) ON DELETE CASCADE,
  CONSTRAINT `authgrp_auth_ibfk_3` FOREIGN KEY (`authgroupid`) REFERENCES `authoritygroup` (`authgroupid`) ON DELETE CASCADE,
  CONSTRAINT `authgrp_auth_ibfk_4` FOREIGN KEY (`authid`) REFERENCES `authority` (`authid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='∞¸∫¨”–';

-- ----------------------------
-- Table structure for authgrp_user
-- ----------------------------
DROP TABLE IF EXISTS `authgrp_user`;
CREATE TABLE `authgrp_user` (
  `authgroupid` int(11) NOT NULL,
  `userid` int(11) NOT NULL COMMENT '”√ªß±‡∫≈',
  PRIMARY KEY (`authgroupid`,`userid`),
  KEY `FK299908D1FEF3255` (`authgroupid`),
  KEY `FK299908DD6EC06B8` (`userid`),
  CONSTRAINT `authgrp_user_ibfk_1` FOREIGN KEY (`authgroupid`) REFERENCES `authoritygroup` (`authgroupid`) ON DELETE CASCADE,
  CONSTRAINT `authgrp_user_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE,
  CONSTRAINT `authgrp_user_ibfk_3` FOREIGN KEY (`authgroupid`) REFERENCES `authoritygroup` (`authgroupid`) ON DELETE CASCADE,
  CONSTRAINT `authgrp_user_ibfk_4` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='µ•∂¿∑÷≈…';

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `resid` int(11) NOT NULL COMMENT '±‡∫≈',
  `metaauthid` int(11) NOT NULL COMMENT '‘™»®œﬁµƒid',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='»®œﬁ±Ì°£\r\n           ºØ∫œ◊ ‘¥∫Õ‘™»®œﬁµƒº«¬º°£\r\n           »Á';

-- ----------------------------
-- Table structure for authoritygroup
-- ----------------------------
DROP TABLE IF EXISTS `authoritygroup`;
CREATE TABLE `authoritygroup` (
  `authgroupid` int(11) NOT NULL,
  `authgroupname` varchar(30) NOT NULL,
  `authgroupcreatetime` datetime NOT NULL COMMENT '»®œﬁ◊È¥¥Ω® ±º‰',
  `authgroupextend` char(10) DEFAULT NULL,
  `authgroupdiscription` text,
  PRIMARY KEY (`authgroupid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='»®œﬁ◊È°£\r\n           ø…Ω´∂‡∏ˆ»®œﬁπÈƒ…µΩ“ª∏ˆ»®œﬁ◊È÷–°£≤¢∞—Ω´’';

-- ----------------------------
-- Table structure for bulletin
-- ----------------------------
DROP TABLE IF EXISTS `bulletin`;
CREATE TABLE `bulletin` (
  `userid` int(11) NOT NULL COMMENT '”√ªß±‡∫≈',
  `bulletinid` int(11) NOT NULL,
  `publishdate` datetime NOT NULL COMMENT '∑¢≤º»’∆⁄',
  `bulletintitle` varchar(50) DEFAULT NULL,
  `content` text,
  `bulletintype` char(10) NOT NULL COMMENT 'π´∏Êµƒ¿‡–Õ',
  `bulletinextend` char(10) DEFAULT NULL,
  PRIMARY KEY (`bulletinid`),
  KEY `FKAFD939A7D6EC06B8` (`userid`),
  CONSTRAINT `bulletin_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE,
  CONSTRAINT `bulletin_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='π´∏Ê°£∆‰÷– Ù–‘bulletintype±Ì æπ´∏Êµƒ¿‡–Õ°£π´∏ÊÀ˘∑¢≤ºµƒƒø±Íµÿ';

-- ----------------------------
-- Table structure for contact
-- ----------------------------
DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact` (
  `repairorderid` int(11) NOT NULL,
  `userid` int(11) NOT NULL COMMENT '”√ªß±‡∫≈',
  `contactid` int(11) NOT NULL,
  `contactdatetime` datetime DEFAULT NULL COMMENT '¡¨¬Á ±º‰',
  `note` text,
  `customresponse` text COMMENT '”√ªßªÿ∏¥',
  `customrequire` text COMMENT '”√ªß«Î«Û',
  `contactvalidate` char(10) DEFAULT NULL COMMENT '»∑»œ ¬œÓ',
  `contactextend` char(10) DEFAULT NULL,
  PRIMARY KEY (`contactid`),
  KEY `FK38B72420EE1CC1F6` (`repairorderid`),
  KEY `FK38B72420D6EC06B8` (`userid`),
  CONSTRAINT `contact_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE,
  CONSTRAINT `contact_ibfk_2` FOREIGN KEY (`repairorderid`) REFERENCES `repairorder` (`repairorderid`) ON DELETE CASCADE,
  CONSTRAINT `contact_ibfk_3` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE,
  CONSTRAINT `contact_ibfk_4` FOREIGN KEY (`repairorderid`) REFERENCES `repairorder` (`repairorderid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='”ÎπÀøÕº‰µƒ¡™¬Á–≈œ¢°£';

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `customerid` int(11) NOT NULL,
  `customername` char(32) NOT NULL,
  `customerphone` varchar(20) NOT NULL COMMENT 'Œ¨–ﬁøÕªßµƒ¡™œµµÁª∞',
  `customeremail` varchar(30) DEFAULT NULL,
  `customerextend` char(10) DEFAULT NULL,
  PRIMARY KEY (`customerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for metaauthority
-- ----------------------------
DROP TABLE IF EXISTS `metaauthority`;
CREATE TABLE `metaauthority` (
  `metaauthid` int(11) NOT NULL COMMENT '‘™»®œﬁµƒid',
  `metaauthname` varchar(30) NOT NULL COMMENT '‘™»®œﬁ√˚',
  `metaauthextend` char(10) DEFAULT NULL COMMENT '”√”⁄¿©’π£¨»Á»®œﬁ±Ì¥Ô Ωµ»',
  `metaauthdiscription` text COMMENT 'Àµ√˜',
  PRIMARY KEY (`metaauthid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='»®œﬁ‘™ ˝æ›\r\n           ”√”⁄∂®“Â»®œﬁ∫Õ»®œﬁµƒÀµ√˜º∞±Ì¥Ô Ωµ»°£';

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
  `userid` int(11) NOT NULL COMMENT '”√ªß±‡∫≈',
  PRIMARY KEY (`orgid`,`userid`),
  KEY `FK4E5DA4461EAB1F9` (`orgid`),
  KEY `FK4E5DA446D6EC06B8` (`userid`),
  CONSTRAINT `org_user_ibfk_1` FOREIGN KEY (`orgid`) REFERENCES `organization` (`orgid`) ON DELETE CASCADE,
  CONSTRAINT `org_user_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE,
  CONSTRAINT `org_user_ibfk_3` FOREIGN KEY (`orgid`) REFERENCES `organization` (`orgid`) ON DELETE CASCADE,
  CONSTRAINT `org_user_ibfk_4` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='◊È÷Ø\r\n           “ª∏ˆ≤ø√≈◊È÷Ø”µ”–µƒ‘±π§';

-- ----------------------------
-- Table structure for organization
-- ----------------------------
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization` (
  `orgid` int(11) NOT NULL,
  `authgroupid` int(11) DEFAULT NULL,
  `orgname` varchar(30) NOT NULL,
  `manager` int(11) DEFAULT NULL,
  `uplevel` int(11) DEFAULT NULL COMMENT '…œº∂◊È÷ØΩ·ππ',
  `orgextend` char(10) DEFAULT NULL,
  `orgdiscription` text,
  PRIMARY KEY (`orgid`),
  KEY `FK4644ED331FEF3255` (`authgroupid`),
  CONSTRAINT `organization_ibfk_5` FOREIGN KEY (`authgroupid`) REFERENCES `authoritygroup` (`authgroupid`),
  CONSTRAINT `organization_ibfk_6` FOREIGN KEY (`authgroupid`) REFERENCES `authoritygroup` (`authgroupid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='◊È÷ØΩ·ππ°£\r\n           œ÷ µ÷–µƒ≤ø√≈ªÚ◊È±Ω·ππ°£√øÃıº«¬º¥˙±Ì“';

-- ----------------------------
-- Table structure for repairorder
-- ----------------------------
DROP TABLE IF EXISTS `repairorder`;
CREATE TABLE `repairorder` (
  `repairorderid` int(11) NOT NULL,
  `modelid` int(11) DEFAULT NULL,
  `agentid` int(11) DEFAULT NULL COMMENT '¥˙¿Ì…Ã±‡∫≈',
  `orgid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL COMMENT '”√ªß±‡∫≈',
  `customerid` int(11) DEFAULT NULL,
  `rmano` varchar(20) NOT NULL,
  `startdate` date NOT NULL,
  `checkindatetime` datetime DEFAULT NULL,
  `location` varchar(20) NOT NULL COMMENT 'ª˙∆˜ƒø«∞À˘‘⁄µÿ',
  `parts` varchar(30) DEFAULT NULL COMMENT '“™∏¸ªªµƒ≤øº˛',
  `stock` tinyint(1) DEFAULT NULL COMMENT ' «∑Ò”––ﬁ¿Ìπ©”¶∆∑',
  `waitparts` varchar(30) DEFAULT NULL COMMENT 'µ»¥˝µƒ≤øº˛√˚',
  `sn` varchar(20) DEFAULT NULL,
  `pn` text,
  `warranty` char(10) DEFAULT NULL,
  `validate` char(10) DEFAULT NULL COMMENT '—È÷§◊÷∂Œ10Œª£¨¥˙±Ì10∏ˆ≤ªÕ¨∫¨“Â',
  `problem` text NOT NULL COMMENT 'π ’œ√Ë ˆ',
  `photo` text COMMENT '’’∆¨µÿ÷∑',
  `isagentrepair` tinyint(1) DEFAULT NULL COMMENT ' «∑Ò «¥˙¿Ì…ÃŒ¨–ﬁ',
  `repairorderstate` varchar(10) NOT NULL COMMENT 'Œ¨–ﬁ◊¥Ã¨',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='∏ˆ»À”√ªßµƒŒ¨–ﬁ∂©µ•£¨‘⁄‘≠”–excelŒƒº˛÷–≥∆Œ™END±Ì°£\r\n          ';

-- ----------------------------
-- Table structure for repairorderbackup
-- ----------------------------
DROP TABLE IF EXISTS `repairorderbackup`;
CREATE TABLE `repairorderbackup` (
  `repairorderid` int(11) NOT NULL,
  `modelid` int(11) DEFAULT NULL,
  `agentid` int(11) DEFAULT NULL COMMENT '‰ª£ÁêÜÂïÜÁºñÂè∑',
  `orgid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL COMMENT 'Áî®Êà∑ÁºñÂè∑',
  `customerid` int(11) DEFAULT NULL,
  `rmano` varchar(20) NOT NULL,
  `startdate` date NOT NULL,
  `checkindatetime` datetime DEFAULT NULL,
  `location` varchar(20) NOT NULL COMMENT 'Êú∫Âô®ÁõÆÂâçÊâÄÂú®Âú∞',
  `parts` varchar(30) DEFAULT NULL COMMENT 'Ë¶ÅÊõ¥Êç¢ÁöÑÈÉ®‰ª∂',
  `stock` tinyint(1) DEFAULT NULL COMMENT 'ÊòØÂê¶Êúâ‰øÆÁêÜ‰æõÂ∫îÂìÅ',
  `waitparts` varchar(30) DEFAULT NULL COMMENT 'Á≠âÂæÖÁöÑÈÉ®‰ª∂Âêç',
  `sn` varchar(20) DEFAULT NULL,
  `pn` text,
  `validate` char(10) DEFAULT NULL COMMENT 'È™åËØÅÂ≠óÊÆµ10‰ΩçÔºå‰ª£Ë°®10‰∏™‰∏çÂêåÂê´‰πâ',
  `problem` text NOT NULL COMMENT 'ÊïÖÈöúÊèèËø∞',
  `photo` text COMMENT 'ÁÖßÁâáÂú∞ÂùÄ',
  `isagentrepair` tinyint(1) DEFAULT NULL COMMENT 'ÊòØÂê¶ÊòØ‰ª£ÁêÜÂïÜÁª¥‰øÆ',
  `repairorderstate` varchar(10) NOT NULL COMMENT 'Áª¥‰øÆÁä∂ÊÄÅ',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='‰∏™‰∫∫Áî®Êà∑ÁöÑÁª¥‰øÆËÆ¢ÂçïÔºåÂú®ÂéüÊúâexcelÊñá‰ª∂‰∏≠Áß∞‰∏∫E';

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `resid` int(11) NOT NULL COMMENT '±‡∫≈',
  `resname` varchar(50) NOT NULL COMMENT '◊ ‘¥√˚≥∆',
  `resextend` char(10) DEFAULT NULL COMMENT '¿©’π',
  `resdiscription` text,
  PRIMARY KEY (`resid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' ‹±£ª§µƒ◊ ‘¥';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` int(11) NOT NULL COMMENT '”√ªß±‡∫≈',
  `username` varchar(30) NOT NULL COMMENT '”√ªß√˚',
  `realname` varchar(30) DEFAULT NULL COMMENT '”√ªß’Ê µ√˚',
  `password` varchar(30) NOT NULL COMMENT '”√ªß√‹¬Î',
  `sex` varchar(8) DEFAULT NULL COMMENT '–‘±',
  `phone` varchar(20) DEFAULT NULL COMMENT '¡™œµµÁª∞',
  `address` varchar(50) DEFAULT NULL COMMENT 'µÿ÷∑',
  `email` varchar(30) DEFAULT NULL COMMENT 'Emailµÿ÷∑',
  `state` varchar(10) NOT NULL COMMENT '◊¥Ã¨',
  `securitykey` varchar(32) DEFAULT NULL COMMENT '∞≤»´¬Î',
  `userextend` char(10) DEFAULT NULL,
  `userdiscription` text,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='”√ªß±Ì£¨‘±π§±Ìµ»°£';

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `agent` VALUES ('1', '‰∏∏Á¥Ö„Ç§„É≥„Éï„Ç©„ÉÜ„ÉÉ„ÇØ„à±', '03-5665-8511', null, null);
INSERT INTO `agent` VALUES ('2', '„à±„É¶„Éã„ÉÜ„Ç£', '03-5812-6121', '', null);
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
INSERT INTO `authority` VALUES ('1', '1', '1', null, 'Á§æÂÜÖÁî®„Ç™„É≥„É©„Ç§„É≥Ê•≠Âãô„Ç∑„Çπ„ÉÜ„É†');
INSERT INTO `authority` VALUES ('2', '1', '2', null, 'Á§æÂÜÖ„Ç™„É≥„É©„Ç§„É≥Ê•≠Âãô„Ç∑„Çπ„ÉÜ„É†');
INSERT INTO `authority` VALUES ('3', '1', '3', null, 'ÁÆ°ÁêÜÊ•≠Âãô');
INSERT INTO `authority` VALUES ('4', '1', '4', null, '„Ç∑„Çπ„ÉÜ„É†„É™„Éö„Ç¢Ê•≠Âãô');
INSERT INTO `authority` VALUES ('5', '1', '5', null, '„Çµ„Éº„Éì„Çπ„Çª„É≥„Çø„ÉºÊ•≠Âãô');
INSERT INTO `authority` VALUES ('6', '1', '6', null, 'ÂõûÁ≠îÂæÖ„Å°');
INSERT INTO `authority` VALUES ('7', '1', '10', null, 'ÁôªÈå≤Ê∏à„Åø');
INSERT INTO `authority` VALUES ('8', '1', '11', null, 'ÈÄ£Áµ°ÈñãÂßã');
INSERT INTO `authority` VALUES ('9', '1', '12', null, 'Êõ¥Êñ∞Ê∏à„Åø');
INSERT INTO `authority` VALUES ('11', '1', '14', null, 'ÈÄ£Áµ°ÂÆå‰∫Ü');
INSERT INTO `authority` VALUES ('12', '1', '15', null, '‰øÆÁêÜÂÆå‰∫Ü');
INSERT INTO `authority` VALUES ('13', '1', '16', null, 'Ê•≠ÂãôÁµÇ‰∫Ü');
INSERT INTO `authority` VALUES ('14', '1', '17', null, 'ssoÁÆ°ÁêÜ„Ç∑„Çπ„ÉÜ„É†');
INSERT INTO `authority` VALUES ('15', '1', '18', null, 'ssoÁÆ°ÁêÜ„Ç∑„Çπ„ÉÜ„É† ');
INSERT INTO `authority` VALUES ('16', '1', '19', null, 'ssoÂçïÁÇπÁôªÂΩïÁÆ°ÁêÜÈìæÊé•È°µÈù¢');
INSERT INTO `authority` VALUES ('17', '1', '20', null, 'ssoÂçïÁÇπÁôªÂΩïÁÆ°ÁêÜÈ¶ñÈ°µÈù¢');
INSERT INTO `authoritygroup` VALUES ('1', 'ÁÆ°ÁêÜÊ•≠Âãô', '2010-03-14 17:19:39', null, 'ÁÆ°ÁêÜÊ•≠Âãô„Å´Èñ¢„Åô„ÇãÊ®©Èôê');
INSERT INTO `authoritygroup` VALUES ('2', '„Ç∑„Çπ„ÉÜ„É†„É™„Éö„Ç¢Ê•≠Âãô', '2010-03-14 17:20:47', null, '„Ç∑„Çπ„ÉÜ„É†„É™„Éö„Ç¢Ê•≠Âãô„Å´Èñ¢„Åô„ÇãÊ®©Èôê');
INSERT INTO `authoritygroup` VALUES ('3', '„Çµ„Éº„Éì„Çπ„Çª„É≥„Çø„ÉºÊ•≠Âãô', '2010-03-14 17:21:20', null, '„Çµ„Éº„Éì„Çπ„Çª„É≥„Çø„ÉºÊ•≠Âãô„Å´Èñ¢„Åô„ÇãÊ®©Èôê');
INSERT INTO `metaauthority` VALUES ('1', 'all', '1111', 'Ë™≠„ÅøËæº„ÇÄ„ÄÅÊõ∏„ÅçËæº„ÇÄ„ÄÅÁ∑®ÈõÜ„ÄÅÂâäÈô§');
INSERT INTO `metaauthority` VALUES ('2', 'write', '111', 'Êõ∏„ÅçËæº„ÇÄ');
INSERT INTO `metaauthority` VALUES ('3', 'add', '11', 'ËøΩÂä†');
INSERT INTO `metaauthority` VALUES ('4', 'read', '1', 'Ë™≠„ÅøËæº„ÇÄ');
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
INSERT INTO `model` VALUES ('25', 'Eee PC 1101HA (Windows¬Æ 7)', '', '', '', null);
INSERT INTO `model` VALUES ('26', 'Eee PC T91MT', '', '', '', null);
INSERT INTO `model` VALUES ('27', 'Eee PC 1005PE', '', '', '', null);
INSERT INTO `model` VALUES ('28', 'Eee PC 1008KR', '', '', '', null);
INSERT INTO `org_user` VALUES ('0', '1');
INSERT INTO `org_user` VALUES ('1', '3');
INSERT INTO `org_user` VALUES ('2', '2');
INSERT INTO `organization` VALUES ('0', '1', '„Éö„Ç¨„Éà„É≠„É≥„Éª„Ç∏„É£„Éë„É≥„à±', '1', '0', null, '„Éö„Ç¨„Éà„É≠„É≥„Éª„Ç∏„É£„Éë„É≥„à±');
INSERT INTO `organization` VALUES ('1', '2', 'EeePC', '1', '0', null, 'EeePC„É™„Éö„Ç¢Ôºà„Ç∑„Çπ„ÉÜ„É†Á≥ªÔºâ');
INSERT INTO `organization` VALUES ('2', '3', '„Çµ„Éº„Éì„Çπ„Çª„É≥„Çø„Éº', '1', '0', null, '„Çµ„Éº„Éì„ÇπÁ™ìÂè£');
INSERT INTO `resource` VALUES ('1', '/pj_ms/', null, 'Á§æÂÜÖ„Ç™„É≥„É©„Ç§„É≥Ê•≠ÂãôÁÆ°ÁêÜ„Ç∑„Çπ„ÉÜ„É†');
INSERT INTO `resource` VALUES ('2', '/pj_ms', null, 'Á§æÂÜÖ„Ç™„É≥„É©„Ç§„É≥Ê•≠ÂãôÁÆ°ÁêÜ„Ç∑„Çπ„ÉÜ„É†');
INSERT INTO `resource` VALUES ('3', '/pj_ms/manageBus', null, 'ÁÆ°ÁêÜÊ•≠Âãô');
INSERT INTO `resource` VALUES ('4', '/pj_ms/systemRepair', null, '„É™„Éö„Ç¢Ê•≠Âãô');
INSERT INTO `resource` VALUES ('5', '/pj_ms/serviceCenter', null, '„Çµ„Éº„Éì„Çπ„Çª„É≥„Çø„ÉºÊ•≠Âãô');
INSERT INTO `resource` VALUES ('6', '/pj_ms/contact/waitResponse', null, 'ÂõûÁ≠îÂæÖ„Å°');
INSERT INTO `resource` VALUES ('7', '/pj_ms/contact/logoned', null, 'ÁôªÈå≤Ê∏à„Åø');
INSERT INTO `resource` VALUES ('8', '/pj_ms/contact/contactStart', null, 'ÈÄ£Áµ°ÈñãÂßã');
INSERT INTO `resource` VALUES ('9', '/pj_ms/contact/updated', null, 'Êõ¥Êñ∞Ê∏à„Åø');
INSERT INTO `resource` VALUES ('11', '/pj_ms/contact/contacted', null, 'ÈÄ£Áµ°ÂÆå‰∫Ü');
INSERT INTO `resource` VALUES ('12', '/pj_ms/contact/repaired', null, '‰øÆÁêÜÂÆå‰∫Ü');
INSERT INTO `resource` VALUES ('13', '/pj_ms/contact/finished', null, 'Ê•≠ÂãôÁµÇ‰∫Ü');
INSERT INTO `resource` VALUES ('14', '/sso/', null, 'ssoÁÆ°ÁêÜ„Ç∑„Çπ„ÉÜ„É†');
INSERT INTO `resource` VALUES ('15', '/sso', null, 'ssoÁÆ°ÁêÜ„Ç∑„Çπ„ÉÜ„É† ');
INSERT INTO `resource` VALUES ('16', '/sso/title.html', null, 'ssoÂçïÁÇπÁôªÂΩïÁÆ°ÁêÜÈìæÊé•È°µÈù¢');
INSERT INTO `resource` VALUES ('17', '/sso/index.jsp', null, 'ssoÂçïÁÇπÁôªÂΩïÁÆ°ÁêÜÈ¶ñÈ°µ');
INSERT INTO `user` VALUES ('1', 'admin', 'ÁÆ°ÁêÜËÄÖ', 'admin', 'male', '000-0000-0000', 'Á∑èÂãôË™≤', 'admin@pegatroncorp.com', 'outLine', null, null, null);
INSERT INTO `user` VALUES ('2', 'repairer', 'repairÂæìÊ•≠Âì°', 'repairer', 'male', '000-0000-0000', '', 'repairer@pegatroncorp.com', 'outLine', null, null, null);
INSERT INTO `user` VALUES ('3', 'contacter', 'contactÂæìÊ•≠Âì°', 'contacter', 'male', '000-0000-0000', '', 'contacter@pegatroncorp.com', 'onLine', '2CA0B9F164C6001B7D961FED1E54ECDF', null, null);
