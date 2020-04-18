/*
 Navicat Premium Data Transfer

 Source Server         : MySQL - root
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : zhishedb

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 18/04/2020 14:49:37
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
  `state` int(11) NULL DEFAULT NULL COMMENT '活动状态: 0 -> “未审核”; 1 -> \"审核通过\"; 2 -> \"已发布\"; 3 -> \"审核未通过\"; 4 -> \"已结束\"',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `club_id`(`club_id`) USING BTREE,
  INDEX `state_id`(`state`) USING BTREE,
  CONSTRAINT `cms_activity_ibfk_1` FOREIGN KEY (`club_id`) REFERENCES `cms_club` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '社团活动' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_activity
-- ----------------------------

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '公告' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_bulletin
-- ----------------------------

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '社长换届申请' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_chief_change_apply
-- ----------------------------

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
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `chief_id`(`chief_id`) USING BTREE,
  CONSTRAINT `cms_club_ibfk_1` FOREIGN KEY (`chief_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cms_club_ibfk_2` FOREIGN KEY (`chief_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '社团' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_club
-- ----------------------------
INSERT INTO `cms_club` VALUES (1, 'clubName', 1, NULL, NULL, NULL, NULL, 1, 'type', NULL, '2020-04-13 22:08:49');
INSERT INTO `cms_club` VALUES (2, 'clubName2', 1, NULL, NULL, NULL, NULL, 0, 'type', NULL, '2020-04-13 22:18:49');
INSERT INTO `cms_club` VALUES (3, 'clubName3', 1, NULL, NULL, NULL, NULL, 0, 'type', NULL, '2020-04-13 22:15:16');

-- ----------------------------
-- Table structure for cms_club_create_apply
-- ----------------------------
DROP TABLE IF EXISTS `cms_club_create_apply`;
CREATE TABLE `cms_club_create_apply`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `applicant` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '申请人',
  `club_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '社团名称',
  `official_state` int(1) NULL DEFAULT NULL COMMENT '官方状态: 0 -> 非正式; 1 -> 正式',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '社团类别',
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '申请原因',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '申请时间',
  `handle_at` datetime(0) NULL DEFAULT NULL COMMENT '处理时间',
  `state` int(1) NULL DEFAULT NULL COMMENT '申请状态：0 -> 未审核; 1 -> 审核通过; 2 -> 审核未通过;',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `cms_club_create_apply_ibfk_1`(`user_id`) USING BTREE,
  CONSTRAINT `cms_club_create_apply_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '社团创建申请' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_club_create_apply
-- ----------------------------

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '解散社团申请' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_club_disband_apply
-- ----------------------------

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '加入社团申请' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_club_join_apply
-- ----------------------------

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '标签' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '成员头衔' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_member_honor
-- ----------------------------

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '社团认证申请' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_official_change_apply
-- ----------------------------

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '社员退出通知' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_quit_notice
-- ----------------------------

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '活动评论关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_user_activity_remark
-- ----------------------------

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户社团关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_user_club_rel
-- ----------------------------

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户标签关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_user_label
-- ----------------------------

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单编码',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `permission_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限编码',
  `permission_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `required_permission` int(11) NULL DEFAULT NULL COMMENT '必要状态: 0 -> 非必须; 1 -> 必须;',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色权限关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission_rel
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `avatar_url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像链接',
  `major` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '专业',
  `email` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
  `slogan` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标语',
  `login_question` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密保问题',
  `login_answer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密保答案',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'wang', 'test', 'tkwang', NULL, NULL, NULL, NULL, NULL, 'what\'s your name?', 'hhh');

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role_rel
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
