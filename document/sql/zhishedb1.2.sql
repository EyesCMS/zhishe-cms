/*
 Navicat Premium Data Transfer

 Source Server         : sanfu-db
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : 101.200.193.180:3306
 Source Schema         : zhishedb

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 29/04/2020 11:38:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cms_activity
-- ----------------------------
DROP TABLE IF EXISTS `cms_activity`;
CREATE TABLE `cms_activity`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `club_id` int(11) NULL DEFAULT NULL COMMENT '社团 id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `body` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '内容',
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片 url 链接',
  `star_date` datetime(0) NULL DEFAULT NULL COMMENT '开始日期',
  `end_data` datetime(0) NULL DEFAULT NULL COMMENT '结束日期',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '活动地址',
  `member_count` int(11) NULL DEFAULT NULL COMMENT '参与人数',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `handle_at` datetime(0) NULL DEFAULT NULL COMMENT '处理时间',
  `state` int(1) NULL DEFAULT NULL COMMENT '活动状态: 0 -> “未审核”; 1 -> \"审核通过\"; 2 -> \"已发布\"; 3 -> \"审核未通过\"; 4 -> \"已结束\";  5->\"已删除\";',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `club_id`(`club_id`) USING BTREE,
  INDEX `state_id`(`state`) USING BTREE,
  CONSTRAINT `cms_activity_ibfk_1` FOREIGN KEY (`club_id`) REFERENCES `cms_club` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '社团活动' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_activity
-- ----------------------------
INSERT INTO `cms_activity` VALUES (1, 5000, 'act1', 'welcome to act1', 'this is amazing!', 'https://unsplash.com/photos/tvc5imO5pXk', '2020-04-25 23:47:21', '2020-04-30 23:47:25', '风雨操场', 22, '2020-04-25 23:47:46', NULL, 5);
INSERT INTO `cms_activity` VALUES (2, 5000, 'act2', 'welcome to act2', 'this is amazing!', 'https://unsplash.com/photos/tvc5imO5pXk', '2020-04-25 23:47:21', '2020-04-30 23:47:25', '风雨操场', 22, '2020-04-25 23:47:46', NULL, 4);
INSERT INTO `cms_activity` VALUES (3, 5000, 'act3', 'welcome to act3', 'this is amazing!', 'https://unsplash.com/photos/tvc5imO5pXk', '2020-04-25 23:47:21', '2020-04-30 23:47:25', '风雨操场', 22, '2020-04-25 23:47:46', NULL, 1);
INSERT INTO `cms_activity` VALUES (4, 5000, 'act4', 'welcome to act4', 'this is amazing!', 'https://unsplash.com/photos/tvc5imO5pXk', '2020-04-25 23:47:21', '2020-04-30 23:47:25', '风雨操场', 22, '2020-04-25 23:47:46', NULL, 1);
INSERT INTO `cms_activity` VALUES (5, 10000, 'act5', 'welcome to act5', 'this is amazing!', 'https://unsplash.com/photos/tvc5imO5pXk', '2020-04-25 23:47:21', '2020-04-30 23:47:25', '风雨操场', 22, '2020-04-25 23:47:46', NULL, 1);
INSERT INTO `cms_activity` VALUES (6, 5000, 'act6', 'welcome to act6', 'this is amazing!', 'https://unsplash.com/photos/tvc5imO5pXk', '2020-04-25 23:47:21', '2020-04-30 23:47:25', '风雨操场', 22, '2020-04-25 23:47:46', NULL, 1);
INSERT INTO `cms_activity` VALUES (7, 5000, 'act7', 'welcome to act7', 'this is amazing!', 'https://unsplash.com/photos/tvc5imO5pXk', '2020-04-25 23:47:21', '2020-04-30 23:47:25', '风雨操场', 22, '2020-04-25 23:47:46', NULL, 2);
INSERT INTO `cms_activity` VALUES (8, 10000, 'act8', '模糊查找', 'this is amazing!', 'https://unsplash.com/photos/tvc5imO5pXk', '2020-04-25 23:47:21', '2020-04-30 23:47:25', '风雨操场', 22, '2020-04-25 23:47:46', NULL, 1);
INSERT INTO `cms_activity` VALUES (10, 5000, 'act', 'this is a title', NULL, NULL, '2018-04-19 00:00:00', '2018-04-22 00:00:00', '三区', NULL, '2020-04-27 16:20:41', NULL, 0);

-- ----------------------------
-- Table structure for cms_bulletin
-- ----------------------------
DROP TABLE IF EXISTS `cms_bulletin`;
CREATE TABLE `cms_bulletin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `club_id` int(11) NULL DEFAULT NULL COMMENT '社团 id',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `body` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '内容',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `club_id`(`club_id`) USING BTREE,
  CONSTRAINT `cms_bulletin_ibfk_1` FOREIGN KEY (`club_id`) REFERENCES `cms_club` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '公告' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_bulletin
-- ----------------------------
INSERT INTO `cms_bulletin` VALUES (2, 5000, 'string1', 'dssdsdds', '2020-04-25 20:50:52', '2020-04-25 20:51:17');
INSERT INTO `cms_bulletin` VALUES (3, 10000, 'srdsds', 'dssdsdds', '2020-04-22 20:51:41', '2020-04-25 20:51:46');
INSERT INTO `cms_bulletin` VALUES (4, 5000, 'string1', 'dssdsdds', '2020-04-16 20:52:45', '2020-04-22 20:52:49');
INSERT INTO `cms_bulletin` VALUES (5, 5000, 'string1', 'dssdsdds', '2020-04-16 20:52:45', '2020-04-29 20:53:18');

-- ----------------------------
-- Table structure for cms_chief_change_apply
-- ----------------------------
DROP TABLE IF EXISTS `cms_chief_change_apply`;
CREATE TABLE `cms_chief_change_apply`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `club_id` int(11) NOT NULL COMMENT '社团 id',
  `old_chief_id` int(11) NOT NULL COMMENT '旧社长 id',
  `new_chief_id` int(11) NULL DEFAULT NULL COMMENT '新社长 id',
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '换届原因',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `handle_at` datetime(0) NULL DEFAULT NULL COMMENT '处理时间',
  `state` int(1) NULL DEFAULT NULL COMMENT '申请状态：0 -> 未审核; 1 -> 审核通过; 2 -> 审核未通过;',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `cms_chief_change_apply_ibfk_1`(`club_id`) USING BTREE,
  INDEX `cms_chief_change_apply_ibfk_2`(`new_chief_id`) USING BTREE,
  CONSTRAINT `cms_chief_change_apply_ibfk_1` FOREIGN KEY (`club_id`) REFERENCES `cms_club` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cms_chief_change_apply_ibfk_2` FOREIGN KEY (`new_chief_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '社长换届申请' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_chief_change_apply
-- ----------------------------
INSERT INTO `cms_chief_change_apply` VALUES (3, 5000, 10088, 10090, 'make friends', '2020-04-28 09:08:24', NULL, 0);
INSERT INTO `cms_chief_change_apply` VALUES (4, 10011, 10094, 10095, 'make friends', '2020-04-28 09:24:46', NULL, 0);

-- ----------------------------
-- Table structure for cms_club
-- ----------------------------
DROP TABLE IF EXISTS `cms_club`;
CREATE TABLE `cms_club`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `chief_id` int(11) NULL DEFAULT NULL COMMENT '社长 id',
  `member_count` int(11) NULL DEFAULT NULL COMMENT '成员数量',
  `qq_group` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'QQ 群号码',
  `slogan` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标语',
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像链接',
  `official_state` int(1) NULL DEFAULT NULL COMMENT '官方状态: 0 -> 非官方; 1 -> 官方认证;',
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '社团类型',
  `grade` int(11) NULL DEFAULT NULL COMMENT '等级',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `delete_status` int(1) NULL DEFAULT 0 COMMENT '删除状态：0->未删除；1->已删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `chief_id`(`chief_id`) USING BTREE,
  CONSTRAINT `cms_club_ibfk_1` FOREIGN KEY (`chief_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cms_club_ibfk_2` FOREIGN KEY (`chief_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10014 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '社团' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_club
-- ----------------------------
INSERT INTO `cms_club` VALUES (5000, '足球社', 10090, 1, NULL, NULL, 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cms_club` VALUES (10000, '软件学社', 10089, 2, '10000', NULL, 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', NULL, NULL, 2017, NULL, NULL);
INSERT INTO `cms_club` VALUES (10001, 'xx社', 10088, 1, NULL, NULL, 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 1, NULL, NULL, '2020-04-23 21:27:56', NULL);
INSERT INTO `cms_club` VALUES (10003, 'xxyyzz社', 10088, 2, NULL, NULL, 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 1, NULL, NULL, '2020-04-24 20:20:23', NULL);
INSERT INTO `cms_club` VALUES (10011, '5社', 10094, 1, NULL, NULL, NULL, 1, NULL, NULL, '2020-04-26 23:09:44', 1);
INSERT INTO `cms_club` VALUES (10012, '6社', 10094, 1, NULL, NULL, NULL, 0, NULL, NULL, '2020-04-28 12:02:17', 0);
INSERT INTO `cms_club` VALUES (10013, '7社', 10094, 1, NULL, NULL, NULL, 0, NULL, NULL, '2020-04-28 19:53:37', 0);

-- ----------------------------
-- Table structure for cms_club_create_apply
-- ----------------------------
DROP TABLE IF EXISTS `cms_club_create_apply`;
CREATE TABLE `cms_club_create_apply`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `applicant` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '申请人',
  `club_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '社团名称',
  `official_state` tinyint(1) NULL DEFAULT NULL COMMENT '官方状态: 0 -> 非正式; 1 -> 正式;',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '社团类别',
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '申请原因',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '申请时间',
  `handle_at` datetime(0) NULL DEFAULT NULL COMMENT '处理时间',
  `state` int(1) NULL DEFAULT NULL COMMENT '申请状态：0 -> 未审核; 1 -> 审核通过; 2 -> 审核未通过;',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `cms_club_create_apply_ibfk_1`(`user_id`) USING BTREE,
  CONSTRAINT `cms_club_create_apply_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '社团创建申请' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_club_create_apply
-- ----------------------------
INSERT INTO `cms_club_create_apply` VALUES (3, 10088, 'test', 'xx社', 1, '运动类', 'make friends', '2020-04-23 21:21:40', '2020-04-23 21:27:57', 1);
INSERT INTO `cms_club_create_apply` VALUES (4, 10088, 'test', 'xxyy社', 1, '运动类', 'make friends', '2020-04-23 21:28:47', '2020-04-23 23:01:44', 1);
INSERT INTO `cms_club_create_apply` VALUES (5, 10088, 'test', 'xxyyzz社', 1, '运动类', 'make friends', '2020-04-24 20:15:29', '2020-04-24 20:21:02', 1);
INSERT INTO `cms_club_create_apply` VALUES (6, 10089, 'test', 'xxyyzzqqqq社', 1, '运动类', 'make friends', '2020-04-26 17:38:37', '2020-04-26 17:39:09', 1);
INSERT INTO `cms_club_create_apply` VALUES (7, 10091, 'test2', 'xxyyzzqqqq1社', 1, '运动类', 'make friends', '2020-04-26 18:32:10', '2020-04-26 18:32:37', 1);
INSERT INTO `cms_club_create_apply` VALUES (8, 10092, 'test3', '1社', 1, '运动类', 'make friends', '2020-04-26 18:35:41', '2020-04-26 18:36:09', 1);
INSERT INTO `cms_club_create_apply` VALUES (9, 10093, 'test3', '2社', 1, '运动类', 'make friends', '2020-04-26 18:44:34', '2020-04-26 18:45:14', 1);
INSERT INTO `cms_club_create_apply` VALUES (10, 10093, 'test3', '3社', 1, '运动类', 'make friends', '2020-04-26 18:57:10', '2020-04-26 18:57:28', 1);
INSERT INTO `cms_club_create_apply` VALUES (11, 10093, 'test3', '4社', 1, '运动类', 'make friends', '2020-04-26 18:59:37', '2020-04-26 18:59:51', 1);
INSERT INTO `cms_club_create_apply` VALUES (12, 10094, 'test3', 'xxx', 1, '没什么类', 'make friends', '2020-04-26 20:41:13', '2020-04-26 20:45:20', 2);
INSERT INTO `cms_club_create_apply` VALUES (13, 10094, 'test3', '5社', 1, '没什么类', 'make friends', '2020-04-26 23:09:19', '2020-04-26 23:09:45', 1);
INSERT INTO `cms_club_create_apply` VALUES (14, 10094, 'test6', '6社', 0, '没什么类', 'make friends', '2020-04-28 12:01:38', '2020-04-28 12:02:17', 1);
INSERT INTO `cms_club_create_apply` VALUES (15, 10094, 'test6', '7社', 0, '没什么XX类', 'make friends', '2020-04-28 19:45:06', '2020-04-28 19:53:37', 1);

-- ----------------------------
-- Table structure for cms_club_disband_apply
-- ----------------------------
DROP TABLE IF EXISTS `cms_club_disband_apply`;
CREATE TABLE `cms_club_disband_apply`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `club_id` int(11) NULL DEFAULT NULL COMMENT '社团 id',
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '申请原因',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `handle_at` datetime(0) NULL DEFAULT NULL COMMENT '处理时间',
  `state` int(1) NULL DEFAULT NULL COMMENT '申请状态：0 -> 未审核; 1 -> 审核通过; 2 -> 审核未通过;',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `club_id`(`club_id`) USING BTREE,
  CONSTRAINT `cms_club_disband_apply_ibfk_2` FOREIGN KEY (`club_id`) REFERENCES `cms_club` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '解散社团申请' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_club_disband_apply
-- ----------------------------
INSERT INTO `cms_club_disband_apply` VALUES (1, 10001, '没为什么', '2020-04-23 22:44:04', '2020-04-24 00:16:31', 2);
INSERT INTO `cms_club_disband_apply` VALUES (4, 10003, '也没为什么', '2020-04-25 00:34:07', '2020-04-25 00:35:01', 2);
INSERT INTO `cms_club_disband_apply` VALUES (16, 10003, '也没为什么', '2020-04-26 20:10:46', '2020-04-26 20:11:12', 2);
INSERT INTO `cms_club_disband_apply` VALUES (17, 10003, '也没为什么', '2020-04-26 20:11:19', '2020-04-26 20:11:35', 2);
INSERT INTO `cms_club_disband_apply` VALUES (18, 10011, '也没为什么', '2020-04-26 23:10:37', NULL, 2);

-- ----------------------------
-- Table structure for cms_club_join_apply
-- ----------------------------
DROP TABLE IF EXISTS `cms_club_join_apply`;
CREATE TABLE `cms_club_join_apply`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户 id',
  `club_id` int(11) NULL DEFAULT NULL COMMENT '社团 id',
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '申请原因',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `handle_at` datetime(0) NULL DEFAULT NULL COMMENT '处理时间',
  `state` int(1) NULL DEFAULT NULL COMMENT '申请状态：0 -> 未审核; 1 -> 审核通过; 2 -> 审核未通过;',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `cms_club_join_apply_ibfk_1`(`user_id`) USING BTREE,
  INDEX `cms_club_join_apply_ibfk_2`(`club_id`) USING BTREE,
  CONSTRAINT `cms_club_join_apply_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cms_club_join_apply_ibfk_2` FOREIGN KEY (`club_id`) REFERENCES `cms_club` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '加入社团申请' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_club_join_apply
-- ----------------------------
INSERT INTO `cms_club_join_apply` VALUES (1, 10088, 5000, '没为什么', '2020-04-24 20:51:22', '2020-04-24 22:53:27', 1);
INSERT INTO `cms_club_join_apply` VALUES (2, 10089, 5000, '也没为什么', '2020-04-24 21:36:38', '2020-04-24 22:53:44', 2);
INSERT INTO `cms_club_join_apply` VALUES (8, 10090, 5000, '也没为什么hhsssh', '2020-04-27 20:07:08', '2020-04-27 20:07:45', 1);

-- ----------------------------
-- Table structure for cms_club_label_rel
-- ----------------------------
DROP TABLE IF EXISTS `cms_club_label_rel`;
CREATE TABLE `cms_club_label_rel`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `label_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `label_id`(`label_id`) USING BTREE,
  CONSTRAINT `cms_club_label_rel_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `cms_club` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cms_club_label_rel_ibfk_2` FOREIGN KEY (`label_id`) REFERENCES `cms_label` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_club_label_rel
-- ----------------------------

-- ----------------------------
-- Table structure for cms_label
-- ----------------------------
DROP TABLE IF EXISTS `cms_label`;
CREATE TABLE `cms_label`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '标签' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_label
-- ----------------------------

-- ----------------------------
-- Table structure for cms_member_honor
-- ----------------------------
DROP TABLE IF EXISTS `cms_member_honor`;
CREATE TABLE `cms_member_honor`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头衔值',
  `description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '成员头衔' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_member_honor
-- ----------------------------
INSERT INTO `cms_member_honor` VALUES (1, '潜水', '活跃度<20');
INSERT INTO `cms_member_honor` VALUES (2, '冒泡', '20<=活跃度<50');

-- ----------------------------
-- Table structure for cms_official_change_apply
-- ----------------------------
DROP TABLE IF EXISTS `cms_official_change_apply`;
CREATE TABLE `cms_official_change_apply`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `club_id` int(11) NULL DEFAULT NULL,
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_at` datetime(0) NULL DEFAULT NULL,
  `handle_at` datetime(0) NULL DEFAULT NULL,
  `state` int(1) NULL DEFAULT NULL COMMENT '申请状态：0 -> 未审核; 1 -> 审核通过; 2 -> 审核未通过;',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `cms_official_change_apply_ibfk_1`(`club_id`) USING BTREE,
  CONSTRAINT `cms_official_change_apply_ibfk_1` FOREIGN KEY (`club_id`) REFERENCES `cms_club` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '社团认证申请' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_official_change_apply
-- ----------------------------
INSERT INTO `cms_official_change_apply` VALUES (1, 10012, '没为什么就是认证一下牛逼一点', '2020-04-28 12:03:13', NULL, 0);

-- ----------------------------
-- Table structure for cms_quit_notice
-- ----------------------------
DROP TABLE IF EXISTS `cms_quit_notice`;
CREATE TABLE `cms_quit_notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `club_id` int(11) NULL DEFAULT NULL,
  `qiut_date` date NULL DEFAULT NULL COMMENT '退社日期',
  `readon` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '原因',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `club_id`(`club_id`) USING BTREE,
  CONSTRAINT `cms_quit_notice_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cms_quit_notice_ibfk_2` FOREIGN KEY (`club_id`) REFERENCES `cms_club` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '社员退出通知' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_quit_notice
-- ----------------------------
INSERT INTO `cms_quit_notice` VALUES (1, 10089, 5000, '2020-04-27', '也没为什么hhsssh');
INSERT INTO `cms_quit_notice` VALUES (2, 10090, 5000, '2020-04-27', '没为什么xxx');

-- ----------------------------
-- Table structure for cms_user_activity_remark
-- ----------------------------
DROP TABLE IF EXISTS `cms_user_activity_remark`;
CREATE TABLE `cms_user_activity_remark`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `activity_id` int(11) NULL DEFAULT NULL,
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '内容',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `activity_id`(`activity_id`) USING BTREE,
  CONSTRAINT `cms_user_activity_remark_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cms_user_activity_remark_ibfk_2` FOREIGN KEY (`activity_id`) REFERENCES `cms_activity` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '活动评论关系' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_user_activity_remark
-- ----------------------------
INSERT INTO `cms_user_activity_remark` VALUES (1, 10088, 1, 'amzing', '2020-04-26 09:37:43', '2020-04-26 09:33:48');
INSERT INTO `cms_user_activity_remark` VALUES (2, 10088, 1, 'amzing', '2020-04-26 10:33:43', '2020-04-26 09:33:48');
INSERT INTO `cms_user_activity_remark` VALUES (3, 10088, 1, 'amzing', '2020-04-26 11:33:43', '2020-04-26 09:33:48');
INSERT INTO `cms_user_activity_remark` VALUES (4, 10088, 2, 'amzing', '2020-04-26 12:33:43', '2020-04-26 09:33:48');
INSERT INTO `cms_user_activity_remark` VALUES (5, 10089, 1, 'amzing', '2020-04-26 13:33:43', '2020-04-26 09:33:48');
INSERT INTO `cms_user_activity_remark` VALUES (6, 10088, 2, 'amzing', '2020-04-26 14:33:43', '2020-04-26 09:33:48');
INSERT INTO `cms_user_activity_remark` VALUES (7, 10089, 3, 'amzing', '2020-04-26 15:33:43', '2020-04-26 09:33:48');

-- ----------------------------
-- Table structure for cms_user_activity_star
-- ----------------------------
DROP TABLE IF EXISTS `cms_user_activity_star`;
CREATE TABLE `cms_user_activity_star`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `activity_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `activity_id`(`activity_id`) USING BTREE,
  CONSTRAINT `cms_user_activity_star_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cms_user_activity_star_ibfk_2` FOREIGN KEY (`activity_id`) REFERENCES `cms_activity` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_user_activity_star
-- ----------------------------

-- ----------------------------
-- Table structure for cms_user_club_rel
-- ----------------------------
DROP TABLE IF EXISTS `cms_user_club_rel`;
CREATE TABLE `cms_user_club_rel`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `club_id` int(11) NULL DEFAULT NULL,
  `credit` int(11) NULL DEFAULT NULL COMMENT '积分',
  `honor_id` int(11) NULL DEFAULT NULL COMMENT '用户头衔 id',
  `join_date` datetime(0) NULL DEFAULT NULL COMMENT '加入时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `club_id`(`club_id`) USING BTREE,
  INDEX `honor_id`(`honor_id`) USING BTREE,
  CONSTRAINT `cms_user_club_rel_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cms_user_club_rel_ibfk_2` FOREIGN KEY (`club_id`) REFERENCES `cms_club` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cms_user_club_rel_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cms_user_club_rel_ibfk_4` FOREIGN KEY (`club_id`) REFERENCES `cms_club` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cms_user_club_rel_ibfk_5` FOREIGN KEY (`honor_id`) REFERENCES `cms_member_honor` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户社团关系' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_user_club_rel
-- ----------------------------
INSERT INTO `cms_user_club_rel` VALUES (1, 10088, 10000, NULL, NULL, NULL);
INSERT INTO `cms_user_club_rel` VALUES (3, 10089, 10000, NULL, NULL, '2020-04-25 14:53:51');
INSERT INTO `cms_user_club_rel` VALUES (4, 10088, 5000, NULL, NULL, '2020-04-24 14:54:49');
INSERT INTO `cms_user_club_rel` VALUES (18, 10094, 10012, NULL, NULL, '2020-04-28 12:02:17');
INSERT INTO `cms_user_club_rel` VALUES (19, 10094, 10013, NULL, NULL, '2020-04-28 19:53:37');

-- ----------------------------
-- Table structure for cms_user_label
-- ----------------------------
DROP TABLE IF EXISTS `cms_user_label`;
CREATE TABLE `cms_user_label`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `label_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `label_id`(`label_id`) USING BTREE,
  CONSTRAINT `cms_user_label_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cms_user_label_ibfk_2` FOREIGN KEY (`label_id`) REFERENCES `cms_label` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户标签关系' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_user_label
-- ----------------------------

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限编码',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, 'cms:club:read', '查看社团');
INSERT INTO `sys_permission` VALUES (2, 'cms:club:create', '添加社团');
INSERT INTO `sys_permission` VALUES (3, 'cms:club:update', '修改社团');
INSERT INTO `sys_permission` VALUES (4, 'cms:club:delete', '删除社团');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'student', '普通学生');
INSERT INTO `sys_role` VALUES (2, 'member', '社员');
INSERT INTO `sys_role` VALUES (3, 'chief', '社长');
INSERT INTO `sys_role` VALUES (4, 'admin', '管理员');

-- ----------------------------
-- Table structure for sys_role_permission_rel
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission_rel`;
CREATE TABLE `sys_role_permission_rel`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NULL DEFAULT NULL,
  `permission_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  INDEX `permission_id`(`permission_id`) USING BTREE,
  CONSTRAINT `sys_role_permission_rel_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sys_role_permission_rel_ibfk_2` FOREIGN KEY (`permission_id`) REFERENCES `sys_permission` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色权限关系' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_permission_rel
-- ----------------------------
INSERT INTO `sys_role_permission_rel` VALUES (1, 1, 1);
INSERT INTO `sys_role_permission_rel` VALUES (2, 2, 1);
INSERT INTO `sys_role_permission_rel` VALUES (3, 3, 1);
INSERT INTO `sys_role_permission_rel` VALUES (4, 4, 1);
INSERT INTO `sys_role_permission_rel` VALUES (5, 4, 2);
INSERT INTO `sys_role_permission_rel` VALUES (6, 3, 3);
INSERT INTO `sys_role_permission_rel` VALUES (7, 4, 3);
INSERT INTO `sys_role_permission_rel` VALUES (8, 4, 4);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `avatar_url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像链接',
  `major` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '专业',
  `email` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '宿舍地址',
  `slogan` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标语',
  `login_question` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密保问题',
  `login_answer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密保答案',
  `is_admin` int(1) NOT NULL DEFAULT 0 COMMENT '管理状态：0 -> 普通用户；1 -> 管理员；',
  `current_role` int(1) NULL DEFAULT 1 COMMENT '当前用户角色，可能随着前端路由改变而切换',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10098 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (10088, 'test', '$2a$10$VXZ9nkZF4R0jABsT1kxXvekwqtV4XSGaOFobOZePhANeZOcdgMo12', 'Bob', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 'software', 'bob@gmail.com', '12345678901', NULL, 'good good study, day day up', NULL, NULL, 0, 1);
INSERT INTO `sys_user` VALUES (10089, 'test1', '$2a$10$kVtzRg63mc/749mP4verpuO3j.17AN0ryTeNmS/65vOilETImdfL2', 'Jane', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 'SE', 'jane@gmail.com', '137xxxxxxxx', NULL, 'good good study, day day up', NULL, NULL, 0, 1);
INSERT INTO `sys_user` VALUES (10090, 'test2', '$2a$10$H9qdVn/Daiu33vhTlf5OHu1TxeNT0PGFPvVInat8sI/jp/gZh//5q', 'Davy', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '生物科学', 'davy@gmail.com', '137xxxxxxxx', NULL, 'good good study, day day up', NULL, NULL, 0, 1);
INSERT INTO `sys_user` VALUES (10091, 'test3', '$2a$10$0s49lMhn0V2EzN58gM7B/.wxCiQ.ZDuEkG0XShXxZC4PnXpuWyYtq', 'Tom', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 'SE', 'tom@gmail.com', '137xxxxxxxx', NULL, 'good good study, day day up', NULL, NULL, 0, 1);
INSERT INTO `sys_user` VALUES (10092, 'test4', '$2a$10$4AtDmk5ZYsUs/fHzlDDqQ.cDNChFno1yt4HA4rlmTuY77W8rQAuQ2', 'What', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 'SE', 'what@gmail.com', '137xxxxxxxx', NULL, 'good good study, day day up', NULL, NULL, 0, 1);
INSERT INTO `sys_user` VALUES (10093, 'test5', '$2a$10$tdX3hagfwjVnCh1gK8mDZOIUpEEUjXmy2eo4y9K9qh1v9GB0j3PVO', 'What', 'http://b-ssl.duitang.com/uploads/item/201810/28/20181028170516_hnpsx.thumb.700_0.jpeg', 'SE', 'what@gmail.com', '137xxxxxxxx', NULL, 'good good study, day day up', NULL, NULL, 0, 1);
INSERT INTO `sys_user` VALUES (10094, 'test6', '$2a$10$D2gRsqYEqrpjXV3mf8/ppe8jwbJfWW/7Bo.f7rVqpdcM4iX9uq41K', 'What', 'http://b-ssl.duitang.com/uploads/item/201810/28/20181028170516_hnpsx.thumb.700_0.jpeg', 'SE', 'what@gmail.com', '137xxxxxxxx', NULL, 'good good study, day day up', NULL, NULL, 0, 1);
INSERT INTO `sys_user` VALUES (10095, 'test7', '$2a$10$s7JEkmb.TM7ELufVjuclkOFnwSGJKFfpRO/VItlfhYE2NsvD6NKW2', 'What', 'http://b-ssl.duitang.com/uploads/item/201810/28/20181028170516_hnpsx.thumb.700_0.jpeg', 'SE', 'what@gmail.com', '137xxxxxxxx', NULL, 'good good study, day day up', NULL, NULL, 0, 1);
INSERT INTO `sys_user` VALUES (10096, 'test8', '$2a$10$P76CBsv3kQHwNGXFj8eWpuqHNCf3KasBH1nKz9MZ/lDo2k/Z2TWIu', 'What', 'http://b-ssl.duitang.com/uploads/item/201810/28/20181028170516_hnpsx.thumb.700_0.jpeg', 'SE', 'what@gmail.com', '137xxxxxxxx', NULL, 'good good study, day day up', NULL, NULL, 0, 1);
INSERT INTO `sys_user` VALUES (10097, 'test9', '$2a$10$wi2kaqYDQhaNicA.wv6wKO7ftDXST8RyXjnjsoMQcSgpZE/Awo5C2', 'What', 'http://b-ssl.duitang.com/uploads/item/201810/28/20181028170516_hnpsx.thumb.700_0.jpeg', 'SE', 'what@gmail.com', '137xxxxxxxx', NULL, 'good good study, day day up', NULL, '好的', 1, 1);

-- ----------------------------
-- Table structure for sys_user_role_rel
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role_rel`;
CREATE TABLE `sys_user_role_rel`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL,
  `club_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  INDEX `club_id`(`club_id`) USING BTREE,
  CONSTRAINT `sys_user_role_rel_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sys_user_role_rel_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sys_user_role_rel_ibfk_3` FOREIGN KEY (`club_id`) REFERENCES `cms_club` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色关系' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role_rel
-- ----------------------------
INSERT INTO `sys_user_role_rel` VALUES (1, 10088, 3, 5000);
INSERT INTO `sys_user_role_rel` VALUES (2, 10088, 3, 10001);
INSERT INTO `sys_user_role_rel` VALUES (3, 10088, 3, 10003);
INSERT INTO `sys_user_role_rel` VALUES (4, 10089, 3, 10000);
INSERT INTO `sys_user_role_rel` VALUES (5, 10094, 3, 10011);
INSERT INTO `sys_user_role_rel` VALUES (6, 10088, 2, 10000);
INSERT INTO `sys_user_role_rel` VALUES (7, 10088, 2, 10011);

SET FOREIGN_KEY_CHECKS = 1;
