-- 数据库初始化脚本

--不同数据库版本 字段名称有的需要加引号 有的不需要
--MySQL5.5以后默认使用InnoDB存储引擎，其中InnoDB和BDB提供事务安全表，其它存储引擎都是非事务安全表。
-- 若要修改默认引擎，可以修改配置文件中的default-storage-engine。可以通过：show variables like 'default_storage_engine';查看当前数据库到默认引擎

--mysql 修改表名
alter table table_name rename table_new_name;




-- 创建数据库
CREATE DATABASE seckill;
-- 使用数据库
use seckill;
-- 创建秒杀数据库表
CREATE TABLE seckill(
seckill_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
name varchar(120) NOT NULL COMMENT '商品名称',
number int NOT NULL COMMENT '库存数量',
start_time TIMESTAMP NOT NULL COMMENT '秒杀开始时间',
end_time TIMESTAMP NOT NULL COMMENT '秒杀结束时间',
create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (seckill_id),
key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';


-- 初始化数据
insert INTO
seckill(name,number,start_time,end_time)
VALUES
('1000元秒杀iphone6',100,'2015-11-01 00:00:00','2015-11-02 00:00:00'),
('500元秒杀ipad2',200,'2015-11-01 00:00:00','2015-11-02 00:00:00'),
('300元秒杀小米4',300,'2015-11-01 00:00:00','2015-11-02 00:00:00'),
('200元秒杀红米note',400,'2015-11-01 00:00:00','2015-11-02 00:00:00');

-- 秒杀成功明细
-- 用户登录认证相关信息
CREATE TABLE success_killid(
seckill_id bigint NOT NULL COMMENT '秒杀商品id',
user_phone bigint NOT NULL COMMENT '用户手机号',
state TINYINT NOT NULL DEFAULT -1 COMMENT '状态标示：-1：无效 0：成功 1：已付款',
create_time TIMESTAMP NOT NULL COMMENT '创建时间',
PRIMARY KEY(seckill_id,user_phone),/*联合主键*/
key idx_create_time(create_time)
)ENGINE=INNODB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';

-- --初始化化数据
-- INSERT INTO seckill(name,number,start_time,end_time)
-- VALUES
--     ('1000元秒杀iphone7',100,'2016-09-18 00:00:00','2016-09-19 00:00:00'),
--     ('1元抢iphone6s',200,'2016-09-18 00:00:00','2016-09-19 00:00:00'),
--     ('99元秒杀小米5',300,'2016-09-18 00:00:00','2016-09-19 00:00:00'),
--     ('88元秒杀macbook',400,'2016-09-18 00:00:00','2016-09-19 00:00:00');
--
--
--
-- -- 秒杀成功记录表
-- CREATE TABLE success_killed(
--  'seckill_id' bigint NOT NULL AUTO_INCREMENT COMMENT '秒杀商品id',
--  'user_phone' bigint NOT NULL COMMENT '用户手机号',
--  'state' tinyint NOT NULL DEFAULT -1 COMMENT '状态标识:-1:无效 0:成功 1:付款 2:发货',
--  'create_time' TIMESTAMP NOT NULL  COMMENT '秒杀成功时间',
--  PRIMARY KEY (seckill_id,user_phone), /* 联合主键 */
--  KEY idx_create_time(create_time)
-- )ENGINE=InnoDB AUTO_INCREMENT = 1000 DEFAULT CHARSET=utf8 COMMENT = "秒杀成功明细表"
--


--连接mysql
mysql -uroot -p 1;