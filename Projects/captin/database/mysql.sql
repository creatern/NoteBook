-- 删除旧的 captin 数据库
drop database captin;

-- 创建并使用 captin 数据库
create database captin;
use captin;

-- documents 文档表
create table if not exists documents (
	title varchar(255) comment '文档标题',
	_order int not null comment '文档顺序 对文档在仓库内进行排序',
	url varchar(255) not null comment '文档路径',
	constraint documents_title_pk primary key (title)
);

-- 文档之间的父子关系
create table if not exists documents_relations (
	parent_title varchar(255) comment '父文档的标题',
	child_title varchar(255) comment '子文档的标题',
	constraint documents_relations_parent_child_pk primary key (parent_title, child_title),
	constraint documents_relations_parent_title_fk foreign key (parent_title) references documents(title)
		on delete cascade
		on update cascade,
	constraint documents_relations_child_title_fk foreign key (child_title) references documents(title)
		on delete cascade
		on update cascade
);

-- keywords 关键词表
create table if not exists keywords (
	keyword varchar(255) comment '关键词',
	context varchar(255) comment '关键词的解释内容 url',
	constraint keywords_keyword_pk primary key (keyword)
);

-- documents_keywords 文档与关键词的联系
-- 之后通过该联系在关键词表的解释内容后面追加对应文档的链接
-- 当keywords对应的keyword在该联系中找不到时，则删除该keywords实体
create table if not exists clips_keywords (
	title varchar(255) comment '文档标题',
	keyword varchar(255) comment '关键词keyword',
	constraint documents_keywords_dktk_pk primary key (title, keyword),
	constraint documents_keywords_title_fk foreign key (title) references documents(title)
		on delete cascade
		on update cascade,
	constraint documents_keywords_keyword_fk foreign key (keyword) references keywords(keyword)
		on delete cascade
		on update cascade
);

-- medias 媒体表 管理着图片、视频等媒体的url
create table if not exists medias (
	id int auto_increment comment '媒体id',
	url varchar(255) not null comment '媒体资源的url',
	times int not null comment '媒体的引用次数，当次数为0时进行删除',
	constraint medias_id_pk primary key (id),
	constraint medias_url_uk unique (url) 
);

-- todos todo表
create table if not exists todos (
	id int auto_increment comment 'todo id',
	context varchar(255) not null comment 'todo的内容', 
	state int not null comment 'todo的完成状态：0-未完成，1-完成',
	start_time datetime comment '开始时间',
	end_time datetime comment '结束时间',
	constraint todos_id_pk primary key (id)
);

-- records 日志表
create table if not exists records (
	id int auto_increment comment '日志id',
	context varchar(255) comment '日志内容',
	create_time datetime comment '创建的日期时间',
	constraint records_id primary key (id)
);
