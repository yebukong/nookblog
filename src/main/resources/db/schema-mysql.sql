DROP TABLE IF EXISTS user;

CREATE TABLE `user` (
  `test_id` bigint(20) NOT NULL COMMENT '主键ID',
  `tenant_id` bigint(20) NOT NULL COMMENT '租户ID',
  `name` varchar(30) DEFAULT NULL COMMENT '名称',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `test_type` int(11) DEFAULT NULL COMMENT '测试下划线字段命名类型',
  `test_date` datetime DEFAULT NULL COMMENT '日期',
  `role` bigint(20) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号码',
  PRIMARY KEY (`test_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

