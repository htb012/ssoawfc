/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2012/5/27 1:49:58                            */
/*==============================================================*/


drop table if exists authgrp_auth;

drop table if exists authgrp_user;

drop table if exists authority;

drop table if exists authoritygroup;

drop table if exists metaauthority;

drop table if exists org_user;

drop table if exists organization;

drop table if exists resource;

drop table if exists user;

/*==============================================================*/
/* Table: authgrp_auth                                          */
/*==============================================================*/
create table authgrp_auth
(
   authgroupid          int not null,
   resid                int not null comment '编号',
   metaauthid           int not null comment '元权限的id',
   authid               int not null,
   primary key (resid, metaauthid, authgroupid, authid)
)
comment = "包含有";

/*==============================================================*/
/* Table: authgrp_user                                          */
/*==============================================================*/
create table authgrp_user
(
   authgroupid          int not null,
   userid               int not null comment '用户编号',
   primary key (authgroupid, userid)
)
comment = "单独分派";

/*==============================================================*/
/* Table: authority                                             */
/*==============================================================*/
create table authority
(
   resid                int not null comment '编号',
   metaauthid           int not null comment '元权限的id',
   authid               int not null,
   authextend           char(10),
   authdiscription      text,
   primary key (resid, metaauthid, authid)
)
comment = "权限表。
           集合资源和元权限的记录。
           如果有A个资源记录、B个元权限记录。那么就有A×B个权限记录
           ";

/*==============================================================*/
/* Table: authoritygroup                                        */
/*==============================================================*/
create table authoritygroup
(
   authgroupid          int not null,
   authgroupname        varchar(30) not null,
   authgroupcreatetime  datetime not null comment '权限组创建时间',
   authgroupextend      char(10),
   authgroupdiscription text,
   primary key (authgroupid)
)
comment = "权限组。
           可将多个权限归纳到一个权限组中。并把将整个权限组授予一个用户。";

/*==============================================================*/
/* Table: metaauthority                                         */
/*==============================================================*/
create table metaauthority
(
   metaauthid           int not null comment '元权限的id',
   metaauthname         varchar(30) not null comment '元权限名',
   metaauthextend       char(10) comment '用于扩展，如权限表达式等',
   metaauthdiscription  text comment '说明',
   primary key (metaauthid)
)
comment = "权限元数据
           用于定义权限和权限的说明及表达式等。";

/*==============================================================*/
/* Table: org_user                                              */
/*==============================================================*/
create table org_user
(
   orgid                int not null,
   userid               int not null comment '用户编号',
   primary key (orgid, userid)
)
comment = "组织
           一个部门组织拥有的员工";

/*==============================================================*/
/* Table: organization                                          */
/*==============================================================*/
create table organization
(
   orgid                int not null,
   authgroupid          int,
   orgname              varchar(30) not null,
   manager              int,
   uplevel              int comment '上级组织结构',
   orgextend            char(10),
   orgdiscription       text,
   primary key (orgid)
)
comment = "组织结构。
           现实中的部门或组别结构。每条记录代表一个部门。每个部门有上一级部门的ID。最高部门的编号为0。它的上级部门编号为-1.
           不同于权限组。";

/*==============================================================*/
/* Table: resource                                              */
/*==============================================================*/
create table resource
(
   resid                int not null comment '编号',
   resname              varchar(50) not null comment '资源名称',
   resextend            char(10) comment '扩展',
   resdiscription       text,
   primary key (resid)
)
comment = "受保护的资源";

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   userid               int not null comment '用户编号',
   username             varchar(30) not null comment '用户名',
   realname             varchar(30) comment '用户真实名',
   password             varchar(30) not null comment '用户密码',
   sex                  varchar(8) comment '性别',
   phone                varchar(20) comment '联系电话',
   address              varchar(50) comment '地址',
   email                varchar(30) comment 'Email地址',
   state                varchar(10) not null comment '状态',
   securitykey          varchar(32) comment '安全码',
   userextend           char(10),
   userdiscription      text,
   primary key (userid)
)
comment = "用户表，员工表等。";

alter table authgrp_auth add constraint FK_authgrp_auth foreign key (authgroupid)
      references authoritygroup (authgroupid) on delete restrict on update restrict;

alter table authgrp_auth add constraint FK_authgrp_auth2 foreign key (resid, metaauthid, authid)
      references authority (resid, metaauthid, authid) on delete restrict on update restrict;

alter table authgrp_user add constraint FK_authgrp_user foreign key (authgroupid)
      references authoritygroup (authgroupid) on delete restrict on update restrict;

alter table authgrp_user add constraint FK_authgrp_user2 foreign key (userid)
      references user (userid) on delete restrict on update restrict;

alter table authority add constraint FK_metaauth foreign key (metaauthid)
      references metaauthority (metaauthid) on delete restrict on update restrict;

alter table authority add constraint FK_res foreign key (resid)
      references resource (resid) on delete restrict on update restrict;

alter table org_user add constraint FK_org_user foreign key (orgid)
      references organization (orgid) on delete restrict on update restrict;

alter table org_user add constraint FK_org_user2 foreign key (userid)
      references user (userid) on delete restrict on update restrict;

alter table organization add constraint FK_correspondence foreign key (authgroupid)
      references authoritygroup (authgroupid) on delete restrict on update restrict;

