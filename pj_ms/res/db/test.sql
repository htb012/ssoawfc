/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2010-02-22 14:37:15                          */
/*==============================================================*/


drop table if exists book;

drop table if exists has;

drop table if exists readTest;

drop table if exists school;

drop table if exists student;

drop table if exists study;

/*==============================================================*/
/* Table: book                                                  */
/*==============================================================*/
create table book
(
   bookid               int not null,
   bookname             varchar(30) not null,
   price                float,
   primary key (bookid)
);

/*==============================================================*/
/* Table: has                                                   */
/*==============================================================*/
create table has
(
   schid                int not null,
   bookid               int not null,
   primary key (schid, bookid)
);

/*==============================================================*/
/* Table: readTest                                              */
/*==============================================================*/
create table readTest
(
   stuid                int not null,
   bookid               int not null,
   primary key (stuid, bookid)
);

/*==============================================================*/
/* Table: school                                                */
/*==============================================================*/
create table school
(
   schid                int not null,
   schname              varchar(20) not null,
   schPhone             varchar(20),
   primary key (schid)
);

/*==============================================================*/
/* Table: student                                               */
/*==============================================================*/
create table student
(
   stuid                int not null,
   stuname              varchar(20) not null,
   sex                  bool,
   primary key (stuid)
);

/*==============================================================*/
/* Table: study                                                 */
/*==============================================================*/
create table study
(
   stuid                int not null,
   schid                int not null,
   primary key (stuid, schid)
);

alter table has add constraint FK_has foreign key (schid)
      references school (schid) on delete restrict on update restrict;

alter table has add constraint FK_has2 foreign key (bookid)
      references book (bookid) on delete restrict on update restrict;

alter table readTest add constraint FK_readTest foreign key (stuid)
      references student (stuid) on delete restrict on update restrict;

alter table readTest add constraint FK_readTest2 foreign key (bookid)
      references book (bookid) on delete restrict on update restrict;

alter table study add constraint FK_study foreign key (stuid)
      references student (stuid) on delete restrict on update restrict;

alter table study add constraint FK_study2 foreign key (schid)
      references school (schid) on delete restrict on update restrict;

INSERT INTO `agent` VALUES ('1', '丸红', '123456', 'wanhong@tom.jp', null);
INSERT INTO `agent` VALUES ('2', '小山', '789012', 'xiaoshan@tom.jp', null);
INSERT INTO `customer` VALUES ('0', 'NULL', 'NULL', null, null);
INSERT INTO `metaauthority` VALUES ('1', 'all', '0000000001', '读取，修改，删除，加');
INSERT INTO `metaauthority` VALUES ('2', 'read', '0000000010', '读取');
INSERT INTO `metaauthority` VALUES ('3', 'edit', '0000000100', '修改');
INSERT INTO `metaauthority` VALUES ('4', 'delete', '0000001000', '删除');
INSERT INTO `metaauthority` VALUES ('5', 'add', '0000010000', '添加');
INSERT INTO `model` VALUES ('1', 'CX/45E', null, null, null, null);
INSERT INTO `org_user` VALUES ('1', '1');
INSERT INTO `org_user` VALUES ('2', '2');
INSERT INTO `organization` VALUES ('1', null, '软件部', '1', '-1', null, null);
INSERT INTO `organization` VALUES ('2', null, '维修组A', '2', '0', null, null);
INSERT INTO `organization` VALUES ('3', null, '维修组B', '2', '0', null, null);
INSERT INTO `user` VALUES ('1', 'admin', 'admin', 'admin', '', null, null, null, 'outLine', '', null, null);
INSERT INTO `user` VALUES ('2', 'htb', '何台兵', 'admin', null, null, null, null, 'outLine', null, null, null);
