/*
MySQL Data Transfer
Source Host: localhost
Source Database: pj_ms
Target Host: localhost
Target Database: pj_ms
Date: 2010-03-14 16:13:15
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for agent
-- ----------------------------
DROP TABLE IF EXISTS `agent`;
CREATE TABLE `agent` (
  `agentid` int(11) NOT NULL COMMENT '´úÀíÉÌ±àºÅ',
  `agentname` varchar(20) NOT NULL COMMENT '´úÀíÉÌÃû³Æ',
  `agentphone` varchar(20) NOT NULL COMMENT '´úÀíÉÌÁªÏµµç»°',
  `agentemail` varchar(30) default NULL COMMENT '´úÀíÉÌÁªÏµmail',
  `agentextend` char(10) default NULL COMMENT 'À©Õ¹',
  PRIMARY KEY  (`agentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='´úÀíÉÌ';

-- ----------------------------
-- Table structure for authgrp_auth
-- ----------------------------
DROP TABLE IF EXISTS `authgrp_auth`;
CREATE TABLE `authgrp_auth` (
  `authgroupid` int(11) NOT NULL,
  `resid` int(11) NOT NULL COMMENT '±àºÅ',
  `metaauthid` int(11) NOT NULL COMMENT 'ÔªÈ¨ÏŞµÄid',
  `authid` int(11) NOT NULL,
  PRIMARY KEY  (`authgroupid`,`authid`),
  KEY `FK290826A1FEF3255` (`authgroupid`),
  KEY `FK290826A211CA39F` (`authid`),
  CONSTRAINT `authgrp_auth_ibfk_1` FOREIGN KEY (`authgroupid`) REFERENCES `authoritygroup` (`authgroupid`) ON DELETE CASCADE,
  CONSTRAINT `authgrp_auth_ibfk_2` FOREIGN KEY (`authid`) REFERENCES `authority` (`authid`) ON DELETE CASCADE,
  CONSTRAINT `authgrp_auth_ibfk_3` FOREIGN KEY (`authgroupid`) REFERENCES `authoritygroup` (`authgroupid`) ON DELETE CASCADE,
  CONSTRAINT `authgrp_auth_ibfk_4` FOREIGN KEY (`authid`) REFERENCES `authority` (`authid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='°üº¬ÓĞ';

-- ----------------------------
-- Table structure for authgrp_user
-- ----------------------------
DROP TABLE IF EXISTS `authgrp_user`;
CREATE TABLE `authgrp_user` (
  `authgroupid` int(11) NOT NULL,
  `userid` int(11) NOT NULL COMMENT 'ÓÃ»§±àºÅ',
  PRIMARY KEY  (`authgroupid`,`userid`),
  KEY `FK299908D1FEF3255` (`authgroupid`),
  KEY `FK299908DD6EC06B8` (`userid`),
  CONSTRAINT `authgrp_user_ibfk_1` FOREIGN KEY (`authgroupid`) REFERENCES `authoritygroup` (`authgroupid`) ON DELETE CASCADE,
  CONSTRAINT `authgrp_user_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE,
  CONSTRAINT `authgrp_user_ibfk_3` FOREIGN KEY (`authgroupid`) REFERENCES `authoritygroup` (`authgroupid`) ON DELETE CASCADE,
  CONSTRAINT `authgrp_user_ibfk_4` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='µ¥¶À·ÖÅÉ';

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `resid` int(11) NOT NULL COMMENT '±àºÅ',
  `metaauthid` int(11) NOT NULL COMMENT 'ÔªÈ¨ÏŞµÄid',
  `authid` int(11) NOT NULL,
  `authextend` char(10) default NULL,
  `authdiscription` text,
  PRIMARY KEY  (`authid`),
  KEY `FK57F407433731CCFF` (`metaauthid`),
  KEY `FK57F407435E6C0F10` (`resid`),
  CONSTRAINT `authority_ibfk_1` FOREIGN KEY (`metaauthid`) REFERENCES `metaauthority` (`metaauthid`) ON DELETE CASCADE,
  CONSTRAINT `authority_ibfk_2` FOREIGN KEY (`resid`) REFERENCES `resource` (`resid`) ON DELETE CASCADE,
  CONSTRAINT `authority_ibfk_3` FOREIGN KEY (`metaauthid`) REFERENCES `metaauthority` (`metaauthid`) ON DELETE CASCADE,
  CONSTRAINT `authority_ibfk_4` FOREIGN KEY (`resid`) REFERENCES `resource` (`resid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='È¨ÏŞ±í¡£\r\n           ¼¯ºÏ×ÊÔ´ºÍÔªÈ¨ÏŞµÄ¼ÇÂ¼¡£\r\n           Èç';

-- ----------------------------
-- Table structure for authoritygroup
-- ----------------------------
DROP TABLE IF EXISTS `authoritygroup`;
CREATE TABLE `authoritygroup` (
  `authgroupid` int(11) NOT NULL,
  `authgroupname` varchar(30) NOT NULL,
  `authgroupcreatetime` datetime NOT NULL COMMENT 'È¨ÏŞ×é´´½¨Ê±¼ä',
  `authgroupextend` char(10) default NULL,
  `authgroupdiscription` text,
  PRIMARY KEY  (`authgroupid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='È¨ÏŞ×é¡£\r\n           ¿É½«¶à¸öÈ¨ÏŞ¹éÄÉµ½Ò»¸öÈ¨ÏŞ×éÖĞ¡£²¢°Ñ½«Õ';

-- ----------------------------
-- Table structure for bulletin
-- ----------------------------
DROP TABLE IF EXISTS `bulletin`;
CREATE TABLE `bulletin` (
  `userid` int(11) NOT NULL COMMENT 'ÓÃ»§±àºÅ',
  `bulletinid` int(11) NOT NULL,
  `publishdate` datetime NOT NULL COMMENT '·¢²¼ÈÕÆÚ',
  `bulletintitle` varchar(50) default NULL,
  `content` text,
  `bulletintype` char(10) NOT NULL COMMENT '¹«¸æµÄÀàĞÍ',
  `bulletinextend` char(10) default NULL,
  PRIMARY KEY  (`bulletinid`),
  KEY `FKAFD939A7D6EC06B8` (`userid`),
  CONSTRAINT `bulletin_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE,
  CONSTRAINT `bulletin_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='¹«¸æ¡£ÆäÖĞÊôĞÔbulletintype±íÊ¾¹«¸æµÄÀàĞÍ¡£¹«¸æËù·¢²¼µÄÄ¿±êµØ';

-- ----------------------------
-- Table structure for contact
-- ----------------------------
DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact` (
  `repairorderid` int(11) NOT NULL,
  `userid` int(11) NOT NULL COMMENT 'ÓÃ»§±àºÅ',
  `contactid` int(11) NOT NULL,
  `contactdatetime` datetime default NULL COMMENT 'Á¬ÂçÊ±¼ä',
  `note` text,
  `customresponse` text COMMENT 'ÓÃ»§»Ø¸´',
  `customrequire` text COMMENT 'ÓÃ»§ÇëÇó',
  `contactvalidate` char(10) default NULL COMMENT 'È·ÈÏÊÂÏî',
  `contactextend` char(10) default NULL,
  PRIMARY KEY  (`contactid`),
  KEY `FK38B72420EE1CC1F6` (`repairorderid`),
  KEY `FK38B72420D6EC06B8` (`userid`),
  CONSTRAINT `contact_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE,
  CONSTRAINT `contact_ibfk_2` FOREIGN KEY (`repairorderid`) REFERENCES `repairorder` (`repairorderid`) ON DELETE CASCADE,
  CONSTRAINT `contact_ibfk_3` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE,
  CONSTRAINT `contact_ibfk_4` FOREIGN KEY (`repairorderid`) REFERENCES `repairorder` (`repairorderid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Óë¹Ë¿Í¼äµÄÁªÂçĞÅÏ¢¡£';

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `customerid` int(11) NOT NULL,
  `customername` char(32) NOT NULL,
  `customerphone` varchar(20) NOT NULL COMMENT 'Î¬ĞŞ¿Í»§µÄÁªÏµµç»°',
  `customeremail` varchar(30) default NULL,
  `customerextend` char(10) default NULL,
  PRIMARY KEY  (`customerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for metaauthority
-- ----------------------------
DROP TABLE IF EXISTS `metaauthority`;
CREATE TABLE `metaauthority` (
  `metaauthid` int(11) NOT NULL COMMENT 'ÔªÈ¨ÏŞµÄid',
  `metaauthname` varchar(30) NOT NULL COMMENT 'ÔªÈ¨ÏŞÃû',
  `metaauthextend` char(10) default NULL COMMENT 'ÓÃÓÚÀ©Õ¹£¬ÈçÈ¨ÏŞ±í´ïÊ½µÈ',
  `metaauthdiscription` text COMMENT 'ËµÃ÷',
  PRIMARY KEY  (`metaauthid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='È¨ÏŞÔªÊı¾İ\r\n           ÓÃÓÚ¶¨ÒåÈ¨ÏŞºÍÈ¨ÏŞµÄËµÃ÷¼°±í´ïÊ½µÈ¡£';

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
  `userid` int(11) NOT NULL COMMENT 'ÓÃ»§±àºÅ',
  PRIMARY KEY  (`orgid`,`userid`),
  KEY `FK4E5DA4461EAB1F9` (`orgid`),
  KEY `FK4E5DA446D6EC06B8` (`userid`),
  CONSTRAINT `org_user_ibfk_1` FOREIGN KEY (`orgid`) REFERENCES `organization` (`orgid`) ON DELETE CASCADE,
  CONSTRAINT `org_user_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE,
  CONSTRAINT `org_user_ibfk_3` FOREIGN KEY (`orgid`) REFERENCES `organization` (`orgid`) ON DELETE CASCADE,
  CONSTRAINT `org_user_ibfk_4` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='×éÖ¯\r\n           Ò»¸ö²¿ÃÅ×éÖ¯ÓµÓĞµÄÔ±¹¤';

-- ----------------------------
-- Table structure for organization
-- ----------------------------
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization` (
  `orgid` int(11) NOT NULL,
  `authgroupid` int(11) default NULL,
  `orgname` varchar(30) NOT NULL,
  `manager` int(11) default NULL,
  `uplevel` int(11) default NULL COMMENT 'ÉÏ¼¶×éÖ¯½á¹¹',
  `orgextend` char(10) default NULL,
  `orgdiscription` text,
  PRIMARY KEY  (`orgid`),
  KEY `FK4644ED331FEF3255` (`authgroupid`),
  CONSTRAINT `organization_ibfk_5` FOREIGN KEY (`authgroupid`) REFERENCES `authoritygroup` (`authgroupid`),
  CONSTRAINT `organization_ibfk_6` FOREIGN KEY (`authgroupid`) REFERENCES `authoritygroup` (`authgroupid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='×éÖ¯½á¹¹¡£\r\n           ÏÖÊµÖĞµÄ²¿ÃÅ»ò×é±ğ½á¹¹¡£Ã¿Ìõ¼ÇÂ¼´ú±íÒ';

-- ----------------------------
-- Table structure for repairorder
-- ----------------------------
DROP TABLE IF EXISTS `repairorder`;
CREATE TABLE `repairorder` (
  `repairorderid` int(11) NOT NULL,
  `modelid` int(11) default NULL,
  `agentid` int(11) default NULL COMMENT '´úÀíÉÌ±àºÅ',
  `orgid` int(11) default NULL,
  `userid` int(11) default NULL COMMENT 'ÓÃ»§±àºÅ',
  `customerid` int(11) default NULL,
  `rmano` varchar(20) NOT NULL,
  `startdate` date NOT NULL,
  `checkindatetime` datetime default NULL,
  `location` varchar(20) NOT NULL COMMENT '»úÆ÷Ä¿Ç°ËùÔÚµØ',
  `parts` varchar(30) default NULL COMMENT 'Òª¸ü»»µÄ²¿¼ş',
  `stock` tinyint(1) default NULL COMMENT 'ÊÇ·ñÓĞĞŞÀí¹©Ó¦Æ·',
  `waitparts` varchar(30) default NULL COMMENT 'µÈ´ıµÄ²¿¼şÃû',
  `sn` varchar(20) default NULL,
  `pn` text,
  `validate` char(10) default NULL COMMENT 'ÑéÖ¤×Ö¶Î10Î»£¬´ú±í10¸ö²»Í¬º¬Òå',
  `problem` text NOT NULL COMMENT '¹ÊÕÏÃèÊö',
  `photo` text COMMENT 'ÕÕÆ¬µØÖ·',
  `isagentrepair` tinyint(1) default NULL COMMENT 'ÊÇ·ñÊÇ´úÀíÉÌÎ¬ĞŞ',
  `repairorderstate` varchar(10) NOT NULL COMMENT 'Î¬ĞŞ×´Ì¬',
  `repairorderexpend` char(10) default NULL,
  PRIMARY KEY  (`repairorderid`),
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='¸öÈËÓÃ»§µÄÎ¬ĞŞ¶©µ¥£¬ÔÚÔ­ÓĞexcelÎÄ¼şÖĞ³ÆÎªEND±í¡£\r\n          ';

-- ----------------------------
-- Table structure for repairorderbackup
-- ----------------------------
DROP TABLE IF EXISTS `repairorderbackup`;
CREATE TABLE `repairorderbackup` (
  `repairorderid` int(11) NOT NULL,
  `modelid` int(11) default NULL,
  `agentid` int(11) default NULL COMMENT 'ä»£ç†å•†ç¼–å·',
  `orgid` int(11) default NULL,
  `userid` int(11) default NULL COMMENT 'ç”¨æˆ·ç¼–å·',
  `customerid` int(11) default NULL,
  `rmano` varchar(20) NOT NULL,
  `startdate` date NOT NULL,
  `checkindatetime` datetime default NULL,
  `location` varchar(20) NOT NULL COMMENT 'æœºå™¨ç›®å‰æ‰€åœ¨åœ°',
  `parts` varchar(30) default NULL COMMENT 'è¦æ›´æ¢çš„éƒ¨ä»¶',
  `stock` tinyint(1) default NULL COMMENT 'æ˜¯å¦æœ‰ä¿®ç†ä¾›åº”å“',
  `waitparts` varchar(30) default NULL COMMENT 'ç­‰å¾…çš„éƒ¨ä»¶å',
  `sn` varchar(20) default NULL,
  `pn` text,
  `validate` char(10) default NULL COMMENT 'éªŒè¯å­—æ®µ10ä½ï¼Œä»£è¡¨10ä¸ªä¸åŒå«ä¹‰',
  `problem` text NOT NULL COMMENT 'æ•…éšœæè¿°',
  `photo` text COMMENT 'ç…§ç‰‡åœ°å€',
  `isagentrepair` tinyint(1) default NULL COMMENT 'æ˜¯å¦æ˜¯ä»£ç†å•†ç»´ä¿®',
  `repairorderstate` varchar(10) NOT NULL COMMENT 'ç»´ä¿®çŠ¶æ€',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ä¸ªäººç”¨æˆ·çš„ç»´ä¿®è®¢å•ï¼Œåœ¨åŸæœ‰excelæ–‡ä»¶ä¸­ç§°ä¸ºE';

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `resid` int(11) NOT NULL COMMENT '±àºÅ',
  `resname` varchar(50) NOT NULL COMMENT '×ÊÔ´Ãû³Æ',
  `resextend` char(10) default NULL COMMENT 'À©Õ¹',
  `resdiscription` text,
  PRIMARY KEY  (`resid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ÊÜ±£»¤µÄ×ÊÔ´';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` int(11) NOT NULL COMMENT 'ÓÃ»§±àºÅ',
  `username` varchar(30) NOT NULL COMMENT 'ÓÃ»§Ãû',
  `realname` varchar(30) default NULL COMMENT 'ÓÃ»§ÕæÊµÃû',
  `password` varchar(30) NOT NULL COMMENT 'ÓÃ»§ÃÜÂë',
  `sex` varchar(8) default NULL COMMENT 'ĞÔ±ğ',
  `phone` varchar(20) default NULL COMMENT 'ÁªÏµµç»°',
  `address` varchar(50) default NULL COMMENT 'µØÖ·',
  `email` varchar(30) default NULL COMMENT 'EmailµØÖ·',
  `state` varchar(10) NOT NULL COMMENT '×´Ì¬',
  `securitykey` varchar(32) default NULL COMMENT '°²È«Âë',
  `userextend` char(10) default NULL,
  `userdiscription` text,
  PRIMARY KEY  (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ÓÃ»§±í£¬Ô±¹¤±íµÈ¡£';

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `agent` VALUES ('1', 'ä¸¸çº¢', '123456', 'htb@htb.cn', null);
INSERT INTO `authgrp_auth` VALUES ('1', '1', '4', '1');
INSERT INTO `authgrp_auth` VALUES ('1', '2', '4', '4');
INSERT INTO `authgrp_auth` VALUES ('1', '3', '4', '5');
INSERT INTO `authgrp_auth` VALUES ('1', '11', '4', '6');
INSERT INTO `authgrp_auth` VALUES ('1', '9', '4', '7');
INSERT INTO `authgrp_auth` VALUES ('1', '10', '1', '8');
INSERT INTO `authgrp_auth` VALUES ('1', '12', '4', '9');
INSERT INTO `authgrp_auth` VALUES ('1', '13', '4', '10');
INSERT INTO `authgrp_auth` VALUES ('1', '14', '4', '11');
INSERT INTO `authgrp_auth` VALUES ('1', '15', '4', '12');
INSERT INTO `authgrp_auth` VALUES ('1', '16', '4', '13');
INSERT INTO `authgrp_auth` VALUES ('1', '17', '4', '14');
INSERT INTO `authgrp_auth` VALUES ('1', '18', '4', '15');
INSERT INTO `authgrp_auth` VALUES ('1', '19', '4', '16');
INSERT INTO `authgrp_auth` VALUES ('2', '2', '4', '4');
INSERT INTO `authgrp_auth` VALUES ('2', '9', '4', '7');
INSERT INTO `authgrp_auth` VALUES ('2', '15', '4', '12');
INSERT INTO `authgrp_auth` VALUES ('2', '17', '4', '14');
INSERT INTO `authgrp_auth` VALUES ('2', '18', '4', '15');
INSERT INTO `authgrp_auth` VALUES ('2', '19', '4', '16');
INSERT INTO `authgrp_auth` VALUES ('3', '3', '4', '5');
INSERT INTO `authgrp_auth` VALUES ('3', '11', '4', '6');
INSERT INTO `authgrp_auth` VALUES ('3', '13', '4', '10');
INSERT INTO `authgrp_auth` VALUES ('3', '14', '4', '11');
INSERT INTO `authgrp_auth` VALUES ('3', '16', '4', '13');
INSERT INTO `authgrp_auth` VALUES ('3', '18', '4', '15');
INSERT INTO `authgrp_user` VALUES ('1', '1');
INSERT INTO `authgrp_user` VALUES ('1', '5');
INSERT INTO `authgrp_user` VALUES ('2', '2');
INSERT INTO `authgrp_user` VALUES ('2', '5');
INSERT INTO `authgrp_user` VALUES ('3', '3');
INSERT INTO `authority` VALUES ('1', '4', '1', null, 'ç³»ç»Ÿç®¡ç†æ¨¡å—ï¼Œå®Œå…¨æ“ä½œ');
INSERT INTO `authority` VALUES ('2', '4', '4', null, 'ç»´ä¿®äººå‘˜,æ“ä½œç»´ä¿®å•');
INSERT INTO `authority` VALUES ('3', '4', '5', null, 'è”ç»œäººå‘˜,æ“ä½œç»´ä¿®å•');
INSERT INTO `authority` VALUES ('11', '4', '6', null, 'æœåŠ¡ä¸­å¿ƒï¼Œå®Œå…¨æ“ä½œ');
INSERT INTO `authority` VALUES ('9', '4', '7', null, 'ç³»ç»Ÿç»´ä¿®ï¼Œå®Œå…¨æ“ä½œ');
INSERT INTO `authority` VALUES ('10', '1', '8', null, 'ç³»ç»Ÿç»´ä¿®ï¼ŒæŸ¥çœ‹æ‰€æœ‰è®¢å•');
INSERT INTO `authority` VALUES ('12', '4', '9', null, 'ç™»éŒ²æ¸ˆã¿');
INSERT INTO `authority` VALUES ('13', '4', '10', null, 'é€£çµ¡é–‹å§‹');
INSERT INTO `authority` VALUES ('14', '4', '11', null, 'æ›´æ–°æ¸ˆã¿');
INSERT INTO `authority` VALUES ('15', '4', '12', null, 'æ¤œè¨¼æ¸ˆã¿');
INSERT INTO `authority` VALUES ('16', '4', '13', null, 'é€£çµ¡å®Œäº†');
INSERT INTO `authority` VALUES ('17', '4', '14', null, 'ä¿®ç†å®Œäº†');
INSERT INTO `authority` VALUES ('18', '4', '15', null, 'æ¥­å‹™çµ‚äº†');
INSERT INTO `authority` VALUES ('19', '4', '16', null, 'ç·¨é›†æ¸ˆã¿');
INSERT INTO `authoritygroup` VALUES ('1', 'admin', '2010-03-07 01:59:44', null, 'è¶…çº§ç®¡ç†å‘˜ç»„');
INSERT INTO `authoritygroup` VALUES ('2', 'repairman', '2010-03-07 02:00:00', null, 'ç»´ä¿®äººå‘˜ç»„ç»„');
INSERT INTO `authoritygroup` VALUES ('3', 'contacter', '2010-03-11 20:01:10', null, 'è”ç»œäººå‘˜ç»„');
INSERT INTO `bulletin` VALUES ('1', '1', '2010-03-10 15:43:15', 'rwe', 'ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡', 'all', null);
INSERT INTO `bulletin` VALUES ('1', '2', '2010-03-10 15:42:31', 'werwe', 'ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡', 'sysRepair', null);
INSERT INTO `bulletin` VALUES ('1', '3', '2010-03-10 15:43:10', 'dasd', 'ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡ä»Šå¤©æ”¾å‡', 'sysRepair', null);
INSERT INTO `bulletin` VALUES ('1', '4', '2010-03-11 22:32:29', 'rwe', 'rwe', 'all', null);
INSERT INTO `bulletin` VALUES ('1', '5', '2010-03-12 00:43:05', 'test', 'testtesttest', 'sysRepair', null);
INSERT INTO `contact` VALUES ('32', '1', '1', '2010-03-13 16:49:35', null, 'fdsfs', null, '1', null);
INSERT INTO `contact` VALUES ('32', '1', '2', '2010-03-13 16:49:50', null, 'gsg', null, '1', null);
INSERT INTO `contact` VALUES ('32', '1', '3', '2010-03-13 16:51:39', null, 'gsfdg', null, '10', null);
INSERT INTO `contact` VALUES ('32', '1', '4', '2010-03-13 16:52:00', null, 'fsd', null, '1000', null);
INSERT INTO `contact` VALUES ('32', '1', '5', '2010-03-13 16:52:55', null, 'dd', null, '1000000', null);
INSERT INTO `contact` VALUES ('32', '1', '6', '2010-03-13 16:53:16', null, 'gdg', null, '1000', null);
INSERT INTO `contact` VALUES ('32', '1', '7', '2010-03-13 16:55:52', null, 'sfsd', null, '10000', null);
INSERT INTO `contact` VALUES ('32', '1', '8', '2010-03-13 16:56:26', null, 'ii', null, '1', null);
INSERT INTO `contact` VALUES ('32', '1', '9', '2010-03-13 16:59:19', null, 'fsdf', null, '1', null);
INSERT INTO `contact` VALUES ('32', '1', '10', '2010-03-13 17:00:10', null, 'dgf', null, '1000000', null);
INSERT INTO `contact` VALUES ('32', '1', '11', '2010-03-14 10:33:43', null, 'fsdf', null, '11', null);
INSERT INTO `contact` VALUES ('30', '1', '12', '2010-03-14 10:37:41', null, 'dasd', null, '11', null);
INSERT INTO `contact` VALUES ('30', '1', '13', '2010-03-14 10:38:10', null, 'adsd', null, '1110000', null);
INSERT INTO `contact` VALUES ('30', '1', '14', '2010-03-14 10:38:28', null, 'dasdasd', null, '1111111', null);
INSERT INTO `contact` VALUES ('28', '1', '15', '2010-03-14 14:43:49', null, 'fsdf', null, '1', null);
INSERT INTO `contact` VALUES ('28', '1', '16', '2010-03-14 14:52:44', null, 'gsdfgsfd', null, '110001', null);
INSERT INTO `contact` VALUES ('32', '1', '17', '2010-03-14 15:18:52', null, 'fsd', null, '1', null);
INSERT INTO `contact` VALUES ('32', '1', '18', '2010-03-14 15:19:15', null, '', null, '10', null);
INSERT INTO `contact` VALUES ('32', '1', '19', '2010-03-14 15:19:43', null, 'fdsf', null, '1000000', null);
INSERT INTO `contact` VALUES ('24', '1', '20', '2010-03-14 15:20:16', null, 'fsdf', null, '1', null);
INSERT INTO `contact` VALUES ('23', '1', '21', '2010-03-14 15:20:25', null, 'fsdfs', null, '10', null);
INSERT INTO `contact` VALUES ('32', '1', '22', '2010-03-14 15:20:42', null, 'sd', null, '1', null);
INSERT INTO `contact` VALUES ('32', '1', '23', '2010-03-14 15:45:40', null, '', null, '1000000', null);
INSERT INTO `customer` VALUES ('1', 'twet', '', null, null);
INSERT INTO `customer` VALUES ('2', 'ä¸­å›½äºº', '', null, null);
INSERT INTO `customer` VALUES ('3', 'æ”¾', '', null, null);
INSERT INTO `metaauthority` VALUES ('1', 'read', 'read', 'read');
INSERT INTO `metaauthority` VALUES ('2', 'write', 'write', 'write');
INSERT INTO `metaauthority` VALUES ('3', 'add', 'add', 'add');
INSERT INTO `metaauthority` VALUES ('4', 'all', 'all', 'all');
INSERT INTO `model` VALUES ('1', 'CX/45E', null, null, null, null);
INSERT INTO `org_user` VALUES ('0', '1');
INSERT INTO `org_user` VALUES ('1', '1');
INSERT INTO `org_user` VALUES ('1', '2');
INSERT INTO `org_user` VALUES ('2', '1');
INSERT INTO `org_user` VALUES ('2', '2');
INSERT INTO `org_user` VALUES ('3', '1');
INSERT INTO `organization` VALUES ('0', '1', 'æ—¥æœ¬åç¡•', '1', '0', null, 'test');
INSERT INTO `organization` VALUES ('1', '2', 'è½¯ä»¶éƒ¨', '1', '0', null, '');
INSERT INTO `organization` VALUES ('2', '2', ' ç»´ä¿®ç»„A', '1', '1', null, '');
INSERT INTO `organization` VALUES ('3', '1', 'dfgdfg', '1', '1', null, '');
INSERT INTO `repairorder` VALUES ('23', '1', '1', null, '1', null, 'fgds', '2010-03-13', '2010-03-11 00:00:00', 'gsfdg', '', null, '', 'dfgsfd', '', '10', '', '2007-11-12-06_1.gif;314.jpg;070723_1212~01_41.gif;', '1', 'ç™»éŒ²æ¸ˆã¿', null);
INSERT INTO `repairorder` VALUES ('24', '1', '1', null, '1', null, 'rwe', '2010-03-13', '2010-03-13 00:00:00', 'rwer', '', null, '', 'wer', '', '1', '', '314.jpg;314.jpg;', '1', 'ç™»éŒ²æ¸ˆã¿', null);
INSERT INTO `repairorder` VALUES ('25', '1', '1', null, '1', null, 'fadsf', '2010-03-13', '2010-03-13 00:00:00', 'fads', 'fads', null, '', 'adsfasd', '', '', '', '222145bj7.jpg;', '1', 'ç™»éŒ²æ¸ˆã¿', null);
INSERT INTO `repairorder` VALUES ('26', '1', '1', null, '1', null, 'dsfa', '2010-03-13', '2010-03-13 00:00:00', 'fadsf', '', null, '', 'fadsf', '', '', '', '', '1', 'ç™»éŒ²æ¸ˆã¿', null);
INSERT INTO `repairorder` VALUES ('27', '1', '1', null, '1', null, 'fads', '2010-03-13', '2010-03-13 00:00:00', 'fadsf', '', null, '', 'fads', '', '', '', '', '1', 'ç™»éŒ²æ¸ˆã¿', null);
INSERT INTO `repairorder` VALUES ('28', '1', '1', null, '1', null, 'ters', '2010-03-13', '2010-03-13 00:00:00', 'trwe', '', null, '', 'twer', '', '110111', '', '', '1', 'ç™»éŒ²æ¸ˆã¿', null);
INSERT INTO `repairorder` VALUES ('29', '1', '1', null, '1', null, 'fads', '2010-03-13', '2010-03-13 00:00:00', 'fasd', '', null, '', 'fdsaf', '', '1', 'dsfgdfg', '070723_1212~01_5.gif;', '1', 'ç™»éŒ²æ¸ˆã¿', null);
INSERT INTO `repairorder` VALUES ('30', '1', '1', null, '1', null, 'fadsf', '2010-03-13', '2010-03-13 00:00:00', 'fads', '1', null, '3', 'asdf', '2', '1100011', 'test', '070723_1212~01_5.gif;070723_1212~01_6.gif;', '1', 'ç™»éŒ²æ¸ˆã¿', null);
INSERT INTO `repairorder` VALUES ('32', '1', null, null, '1', '3', 'åŒæ—¶è®¤ä¸ºå“¦', '2010-03-13', '2010-03-13 00:00:00', 'fsad', '', null, '', 'fsd', '', '1000000', '', '070723_1212~01_6.gif;070723_1212~01_27.gif;070804_0137~01_48.gif;070723_1212~01_5.gif;070723_1212~01_5.gif;åŸºäºè½¯ä»¶å¤ç”¨çš„è‡ªåŠ¨å¤©æ°”é¢„æŠ¥é…ç½®ç³»ç»Ÿæ¦‚è¦è®¾è®¡.pdf;', '0', 'é€£çµ¡é–‹å§‹', null);
INSERT INTO `resource` VALUES ('1', '/pj_ms/manageBus', null, 'pj_msç³»ç»Ÿç®¡ç†');
INSERT INTO `resource` VALUES ('2', '/pj_ms/orderMgr.do?task=view&scope=repairer', null, 'ç»´ä¿®äººå‘˜');
INSERT INTO `resource` VALUES ('3', '/pj_ms/orderMgr.do?task=view&scope=contacter', null, 'è”ç»œäººå‘˜');
INSERT INTO `resource` VALUES ('5', '/pj_ms', null, 'pj_msç³»ç»Ÿç®¡ç†');
INSERT INTO `resource` VALUES ('6', '/pj_ms/', null, 'pj_msç³»ç»Ÿç®¡ç†');
INSERT INTO `resource` VALUES ('7', '/sso/', null, 'å•ç‚¹ç®¡ç†ç³»ç»Ÿ');
INSERT INTO `resource` VALUES ('8', '/sso', null, 'å•ç‚¹ç™»å½•');
INSERT INTO `resource` VALUES ('9', '/pj_ms/systemRepair', null, 'ç³»ç»Ÿç»´ä¿®');
INSERT INTO `resource` VALUES ('10', '/pj_ms/orderMgr.do?task=view&scope=all', null, 'æŸ¥çœ‹æ‰€æœ‰çš„ç»´ä¿®è®¢å•');
INSERT INTO `resource` VALUES ('11', '/pj_ms/serviceCenter', null, 'æœåŠ¡ä¸­å¿ƒ');
INSERT INTO `resource` VALUES ('12', '/pj_ms/contact/logoned', null, 'ç™»éŒ²æ¸ˆã¿');
INSERT INTO `resource` VALUES ('13', '/pj_ms/contact/contactStart', null, 'é€£çµ¡é–‹å§‹');
INSERT INTO `resource` VALUES ('14', '/pj_ms/contact/updated', null, 'æ›´æ–°æ¸ˆã¿');
INSERT INTO `resource` VALUES ('15', '/pj_ms/contact/checked', null, 'æ¤œè¨¼æ¸ˆã¿');
INSERT INTO `resource` VALUES ('16', '/pj_ms/contact/contacted', null, 'é€£çµ¡å®Œäº†');
INSERT INTO `resource` VALUES ('17', '/pj_ms/contact/repaired', null, 'ä¿®ç†å®Œäº†');
INSERT INTO `resource` VALUES ('18', '/pj_ms/contact/finished', null, 'æ¥­å‹™çµ‚äº†');
INSERT INTO `resource` VALUES ('19', '/pj_ms/contact/edited', null, 'ç·¨é›†æ¸ˆã¿');
INSERT INTO `user` VALUES ('1', 'admin', 'admin', 'admin', 'male', '123456', '99', 'htb@htb.cn', 'outLine', null, null, 'admin');
INSERT INTO `user` VALUES ('2', 'test', 'test', 'test', 'male', 'test', '00', 'setse', 'onLine', '619BD4A69187DCD2681408B9EB87D5D7', null, null);
INSERT INTO `user` VALUES ('3', 'jum', 'jum', 'jum', 'male', 'test', 'test', 'test', 'onLine', '5A019D6EFCC146ED9CD3F75FDAB7D5FF', null, null);
INSERT INTO `user` VALUES ('4', '', '', '', 'male', '', '', '', 'onLine', '6E7CEC2890C44DB1E45CAAE85CE49259', null, null);
INSERT INTO `user` VALUES ('5', 'J0325', 'test', '1111', 'male', '111111', '111111', '111111', 'onLine', 'A626C6E63FD7B8F281BD34BA9A0BA1A2', null, null);
INSERT INTO `user` VALUES ('6', 'test', 'éˆ´æœ¨ã€€å¤ªéƒ', 'test', 'male', 'tet', 'tewtw', 'tewtwe', 'outLine', null, null, null);
