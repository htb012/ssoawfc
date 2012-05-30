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
   resid                int not null comment '���',
   metaauthid           int not null comment 'ԪȨ�޵�id',
   authid               int not null,
   primary key (resid, metaauthid, authgroupid, authid)
)
comment = "������";

/*==============================================================*/
/* Table: authgrp_user                                          */
/*==============================================================*/
create table authgrp_user
(
   authgroupid          int not null,
   userid               int not null comment '�û����',
   primary key (authgroupid, userid)
)
comment = "��������";

/*==============================================================*/
/* Table: authority                                             */
/*==============================================================*/
create table authority
(
   resid                int not null comment '���',
   metaauthid           int not null comment 'ԪȨ�޵�id',
   authid               int not null,
   authextend           char(10),
   authdiscription      text,
   primary key (resid, metaauthid, authid)
)
comment = "Ȩ�ޱ�
           ������Դ��ԪȨ�޵ļ�¼��
           �����A����Դ��¼��B��ԪȨ�޼�¼����ô����A��B��Ȩ�޼�¼
           ";

/*==============================================================*/
/* Table: authoritygroup                                        */
/*==============================================================*/
create table authoritygroup
(
   authgroupid          int not null,
   authgroupname        varchar(30) not null,
   authgroupcreatetime  datetime not null comment 'Ȩ���鴴��ʱ��',
   authgroupextend      char(10),
   authgroupdiscription text,
   primary key (authgroupid)
)
comment = "Ȩ���顣
           �ɽ����Ȩ�޹��ɵ�һ��Ȩ�����С����ѽ�����Ȩ��������һ���û���";

/*==============================================================*/
/* Table: metaauthority                                         */
/*==============================================================*/
create table metaauthority
(
   metaauthid           int not null comment 'ԪȨ�޵�id',
   metaauthname         varchar(30) not null comment 'ԪȨ����',
   metaauthextend       char(10) comment '������չ����Ȩ�ޱ��ʽ��',
   metaauthdiscription  text comment '˵��',
   primary key (metaauthid)
)
comment = "Ȩ��Ԫ����
           ���ڶ���Ȩ�޺�Ȩ�޵�˵�������ʽ�ȡ�";

/*==============================================================*/
/* Table: org_user                                              */
/*==============================================================*/
create table org_user
(
   orgid                int not null,
   userid               int not null comment '�û����',
   primary key (orgid, userid)
)
comment = "��֯
           һ��������֯ӵ�е�Ա��";

/*==============================================================*/
/* Table: organization                                          */
/*==============================================================*/
create table organization
(
   orgid                int not null,
   authgroupid          int,
   orgname              varchar(30) not null,
   manager              int,
   uplevel              int comment '�ϼ���֯�ṹ',
   orgextend            char(10),
   orgdiscription       text,
   primary key (orgid)
)
comment = "��֯�ṹ��
           ��ʵ�еĲ��Ż����ṹ��ÿ����¼����һ�����š�ÿ����������һ�����ŵ�ID����߲��ŵı��Ϊ0�������ϼ����ű��Ϊ-1.
           ��ͬ��Ȩ���顣";

/*==============================================================*/
/* Table: resource                                              */
/*==============================================================*/
create table resource
(
   resid                int not null comment '���',
   resname              varchar(50) not null comment '��Դ����',
   resextend            char(10) comment '��չ',
   resdiscription       text,
   primary key (resid)
)
comment = "�ܱ�������Դ";

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   userid               int not null comment '�û����',
   username             varchar(30) not null comment '�û���',
   realname             varchar(30) comment '�û���ʵ��',
   password             varchar(30) not null comment '�û�����',
   sex                  varchar(8) comment '�Ա�',
   phone                varchar(20) comment '��ϵ�绰',
   address              varchar(50) comment '��ַ',
   email                varchar(30) comment 'Email��ַ',
   state                varchar(10) not null comment '״̬',
   securitykey          varchar(32) comment '��ȫ��',
   userextend           char(10),
   userdiscription      text,
   primary key (userid)
)
comment = "�û���Ա����ȡ�";

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

