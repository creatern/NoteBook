-- 删除旧的 captin 数据库
drop database captin;

-- 创建并使用 captin 数据库
create database captin;
use captin;

-- users 用户表
create table if not exists users (
	id int auto_increment comment '用户id',
	name varchar(50) not null comment '用户名称',
	head varchar(255) default '' comment '用户头像 url',
	account varchar(255) not null comment '用户账号',
	password varchar(255) not null comment '用户密码',
	constraint users_id_pk primary key (id),
	constraint users_account_uk unique (account)
);

-- documents 文档表
create table if not exists documents (
	id int auto_increment comment '文档id',
	users_id int not null comment '用户id',
	title varchar(255) not null comment '文档标题',
	level int not null comment '文档级别',
	constraint documents_id_pk primary key (id),
	constraint documents_users_id_fk foreign key (users_id) references users(id)
		on delete cascade
		on update cascade,
	constraint documents_title_users_id_uk unique (title, users_id)
);
