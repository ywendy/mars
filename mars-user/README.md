## mars-user 分库表策略


### 分库分表
+ 2 个库
+ 16 张表
+ 敏感信息加密

### 模拟数据


```sql

DELETE FROM t_user_0;
DELETE FROM t_user_2;
DELETE FROM t_user_4;
DELETE FROM t_user_6;


DELETE FROM t_user_1;
DELETE FROM t_user_3;
DELETE FROM t_user_5;
DELETE FROM t_user_7;


DROP TABLE IF EXISTS t_user_0;
DROP TABLE IF EXISTS t_user_1;
DROP TABLE IF EXISTS t_user_2;
DROP TABLE IF EXISTS t_user_3;
DROP TABLE IF EXISTS t_user_4;
DROP TABLE IF EXISTS t_user_5;
DROP TABLE IF EXISTS t_user_6;
DROP TABLE IF EXISTS t_user_7;





CREATE TABLE `t_user_0` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` bigint(20) NOT NULL COMMENT '用户编号',
  `login_name` varchar(64) NOT NULL DEFAULT '' COMMENT '登录名',
  `nick_name` varchar(64) DEFAULT '' COMMENT '昵称',
  `name` varchar(32) DEFAULT '' COMMENT '真实姓名',
  `age` int(11) DEFAULT '0' COMMENT '年龄',
  `gender` tinyint(4) DEFAULT '0' COMMENT '0未知  1 男 2 女',
  `pwd` varchar(255) DEFAULT '' COMMENT '密码',
  `salt` varchar(255) NOT NULL DEFAULT '' COMMENT '密码盐',
  `avatar` varchar(255) DEFAULT '' COMMENT '头像地址',
  `phone` varchar(11) DEFAULT '' COMMENT '手机',
  `email` varchar(64) DEFAULT '' COMMENT '邮箱',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uid_idx` (`uid`) USING BTREE COMMENT '用户编号唯一',
  UNIQUE KEY `login_name_idx` (`login_name`) USING BTREE COMMENT '用户登录名'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;






```