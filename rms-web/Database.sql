# CREATE DATABASE rms_db;

USE rms_db;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `user_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '用户id',
                        `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账号',
                        `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
                        `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
                        `identity` int(0) NOT NULL DEFAULT 0 COMMENT '身份',
                        PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '张三', 'admin', 2);
INSERT INTO `user` VALUES (2, 'tom', '李四', 'tom', 0);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
                           `msg_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '消息id',
                           `sender_id` bigint(0) NOT NULL COMMENT '发送人id',
                           `receiver` bigint(0) NOT NULL COMMENT '接收人id',
                           `msg_date` bigint(0) NULL DEFAULT NULL COMMENT '发送时间',
                           `read` int(1) NULL DEFAULT NULL COMMENT '是否已读',
                           `msg_body` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '消息内容',
                           PRIMARY KEY (`msg_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1, 1, 2, 0, 0, 'hello,tom');
INSERT INTO `message` VALUES (2, 1, 2, 0, 0, 'hi,tom');
INSERT INTO `message` VALUES (3, 2, 1, 0, 0, 'hi,admin');
INSERT INTO `message` VALUES (4, 2, 1, 0, 0, 'hello,admin');

-- ----------------------------
-- Table structure for application
-- ----------------------------
DROP TABLE IF EXISTS `application`;
CREATE TABLE `application` (
                               `app_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '申请id',
                               `app_date` bigint(0) NULL DEFAULT NULL COMMENT '申请时间',
                               `applicant_id` bigint(0) NOT NULL COMMENT '申请人id',
                               `machine_id` bigint(0) NOT NULL COMMENT '机器id',
                               `state` int(1) NULL DEFAULT NULL COMMENT '状态',
                               `app_body` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '申请内容',
                               `rep_body` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '审批内容',
                               PRIMARY KEY (`app_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of application
-- ----------------------------
INSERT INTO `application` VALUES (1, 0, 2, 1, 0, 'I need it', '');
INSERT INTO `application` VALUES (2, 0, 2, 2, 0, 'I need it', '');

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
                       `log_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '日志id',
                       `log_date` bigint(0) NULL DEFAULT NULL COMMENT '日志时间',
                       `operator_id` bigint(0) NOT NULL COMMENT '操作人id',
                       `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '操作内容',
                       PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES (1, 0, 1, 'login');
INSERT INTO `log` VALUES (2, 0, 1, 'logout');
INSERT INTO `log` VALUES (3, 0, 2, 'login');
INSERT INTO `log` VALUES (4, 0, 2, 'logout');

-- ----------------------------
-- Table structure for machine
-- ----------------------------
DROP TABLE IF EXISTS `machine`;
CREATE TABLE `machine` (
                           `machine_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '机器id',
                           `ip` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'ip地址',
                           `name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '机器名字',
                           `sid` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '机器sid',
                           `config` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '机器配置',
                           `register_date` bigint(0) NOT NULL COMMENT '注册时间',
                           `user_id` bigint(0) NOT NULL COMMENT '使用者id',
                           `env` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '环境',
                           PRIMARY KEY (`machine_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of machine
-- ----------------------------
INSERT INTO `machine` VALUES (1, '10.0.0.1', 'mc_1', 'mc_1', 'null', 0, 0,'env1');
INSERT INTO `machine` VALUES (2, '10.0.0.2', 'mc_2', 'mc_2', 'null', 0, 1,'env1');

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
                           `acc_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '账号id',
                           `desp` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '描述',
                           `username` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账号',
                           `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
                           `owner_id` bigint(0) NOT NULL COMMENT '拥有者id',
                           PRIMARY KEY (`acc_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (1, 'account for machine 1', 'admin', 'admin', 2);
INSERT INTO `account` VALUES (2, 'account for machine 2', 'admin', 'admin', 2);
INSERT INTO `account` VALUES (3, 'account for machine 1', 'tom', 'tom', 1);

-- ----------------------------
-- Table structure for share
-- ----------------------------
DROP TABLE IF EXISTS `share`;
CREATE TABLE `share` (
                         `sh_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '分享记录id',
                         `acc_id` bigint(0) NOT NULL COMMENT '账号id',
                         `towho_id` bigint(0) NOT NULL COMMENT '被分享者id',
                         `sh_date` bigint(0) NOT NULL COMMENT '分享时间',
                         `duration` bigint(0) NOT NULL COMMENT '持续时间',
                         PRIMARY KEY (`sh_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of share
-- ----------------------------
INSERT INTO `share` VALUES (1, 1, 1, 0, 360000);
INSERT INTO `share` VALUES (2, 2, 1, 0, 360000);
INSERT INTO `share` VALUES (3, 3, 2, 0, 360000);