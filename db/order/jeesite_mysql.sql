SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables*/

DROP TABLE IF EXISTS sys_order;


/* Create Tables */

CREATE TABLE sys_order
(
	id varchar(64) NOT NULL COMMENT '编号',
	cid varchar(32)  COMMENT 'CID',
	productType varchar(64) COMMENT '商品类型',
	product varchar(64) COMMENT '商品名称',
	order_date datetime COMMENT '下单时间',
	amount decimal(10, 2) COMMENT '商品金额',
	customer_name varchar(64) COMMENT '客户名称',
	customer_phone varchar(64) COMMENT '客户电话',
	customer_address varchar(512) COMMENT '收货地址',
	order_name varchar(256) COMMENT '订购套餐',
	remarks varchar(256) COMMENT '备注信息',
	status  varchar(13) COMMENT '订单状态',
	operRemarks varchar(512) COMMENT '客服备注信息',
  linkAddr varchar(512) COMMENT '下单页面',
	create_by varchar(64) COMMENT '创建者',
	create_date datetime COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime COMMENT '更新时间',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '订单';

CREATE TABLE sys_col
(
	id varchar(64) NOT NULL COMMENT '编号',
	parent_id varchar(64) NOT NULL COMMENT '父级编号',
	parent_ids varchar(2000) NOT NULL COMMENT '所有父级编号',
	name varchar(100) NOT NULL COMMENT '名称',
	sort decimal(10,0) NOT NULL COMMENT '排序',
	code varchar(100) COMMENT '类目编码',
	lev char(1) COMMENT '类目层级',
	create_by varchar(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '类目表';


CREATE TABLE sys_product
(
	id varchar(64) NOT NULL COMMENT '编号',
	name varchar(100) NOT NULL COMMENT '名称',
	code varchar(100) COMMENT '编码',
	col_code varchar(100) NOT NULL COMMENT '类目编码',
	sort decimal(10,0) NOT NULL COMMENT '排序',
	price decimal(10,2) NOT NULL COMMENT '价格',
	create_by varchar(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '产品表';

/* Create Indexes */



