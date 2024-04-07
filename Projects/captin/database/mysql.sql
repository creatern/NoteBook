-- 删除旧的 captin 数据库
drop database captin;

-- 创建并使用 captin 数据库
create database captin;
use captin;

-- repositories 仓库表
create table if not exists repositories (
	id int auto_increment comment '仓库id',
	category varchar(255) not null comment '类别',
	constraint repositories_id_pk primary key (id),
	constraint repositories_category_uk unique (category)
);

-- documents 文档表
create table if not exists documents (
	id int auto_increment comment '文档id',
	repositories_id int not null comment '仓库id',
	title varchar(255) not null comment '文档标题',
	category varchar(255) not null comment '文档类型 例如系统篇、shell篇',
	_level int not null comment '文档级别',
	_order int not null comment '文档顺序 对文档在仓库内进行排序',
	constraint documents_id_pk primary key (id),
	constraint documents_repositories_id_fk foreign key (repositories_id) references repositories(id)
		on delete cascade
		on update cascade,
	constraint documents_title_repositories_id_uk unique (title, repositories_id)
);

-- clips 片段表（存储文档表内的各个标题的内容）
create table if not exists clips (
	id int auto_increment comment '片段id',
	documents_id int not null comment '文档id',
	title varchar(255) not null comment '片段标题',
	_level int not null comment '片段级别 0-表示为文档的简介',
	_order int not null comment '片段顺序 对片段在文档内进行排序',
	context text comment '片段内容',
	constraint clips_id_pk primary key (id),
	constraint clips_title_uk unique (title),
	constraint clips_documents_id_fk foreign key (documents_id) references documents(id)
		on delete cascade
		on update cascade
);

-- keywords 关键词表
create table if not exists keywords (
	id int auto_increment comment '关键词id',
	keyword varchar(255) comment '关键词' not null,
	context text comment '关键词的解释内容',
	links text comment '关键词相关片段的路由链接',
	constraint keywords_id_pk primary key (id),
	constraint keywords_keyword_uk unique (keyword)
);

-- clips_keywords 片段与关键词的联系
create table if not exists clips_keywords (
	clips_id int comment '片段id',
	keywords_id int comment '关键词id',
	constraint clips_keywords_ckid_pk primary key (clips_id, keywords_id),
	constraint clips_keywords_clips_id_fk foreign key (clips_id) references clips(id)
		on delete cascade
		on update cascade,
	constraint clips_keywords_keywords_id_fk foreign key (keywords_id) references keywords(id)
		on delete cascade
		on update cascade
);

-- medias 媒体表 管理着图片、视频等媒体的url
create table if not exists medias (
	id int auto_increment comment '媒体id',
	url varchar(255) not null comment '媒体资源的url',
	type int not null comment '媒体的类型 1-png,2-jpg,3-gif,4-mp4,5-pdf',
	constraint medias_id_pk primary key (id),
	constraint medias_url_uk unique (url) 
);

-- 媒体与片段的联系
create table if not exists medias_clips (
	medias_id int comment '媒体id',
	clips_id int comment '片段id',
	constraint medias_clips_mcid_pk primary key (medias_id, clips_id),
	constraint medias_clips_medias_id_fk foreign key (medias_id) references medias(id)
		on delete cascade
		on update cascade,
	constraint medias_clips_clips_id_fk foreign key (clips_id) references clips(id)
		on delete cascade
		on update cascade
);

-- 媒体与关键词的联系
create table if not exists medias_keywords (
	medias_id int comment '媒体id',
	keywords_id int comment '关键词id',
	constraint medias_keywords_mkid_pk primary key (medias_id, keywords_id),
	constraint medias_keywords_medias_id_fk foreign key (medias_id) references medias(id)
		on delete cascade
		on update cascade,
	constraint medias_keywords_keywords_id_fk foreign key (keywords_id) references keywords(id)
		on delete cascade
		on update cascade
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
