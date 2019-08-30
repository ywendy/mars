#
## mars-user
```sql
/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
 drop table if exists user;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nick_name` varchar(64) DEFAULT '' COMMENT '昵称',
  `name` varchar(32) DEFAULT '' COMMENT '真实姓名',
  `age` int(11) DEFAULT '0' COMMENT '年龄',
  `gender` tinyint(4) DEFAULT '0' COMMENT '0未知  1 男 2 女',
  `pwd` varchar(255) DEFAULT '' COMMENT '密码',
  `avatar` varchar(255) DEFAULT '' COMMENT '头像地址',
  `phone` varchar(11) DEFAULT '' COMMENT '手机',
  `email` varchar(20) DEFAULT '' COMMENT '邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

```