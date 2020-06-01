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

 Date: 30/05/2020 16:45:17
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
  `start_date` datetime(0) NULL DEFAULT NULL COMMENT '开始日期',
  `end_date` datetime(0) NULL DEFAULT NULL COMMENT '结束日期',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '活动地址',
  `member_count` int(11) NULL DEFAULT NULL COMMENT '参与人数',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `handle_at` datetime(0) NULL DEFAULT NULL COMMENT '处理时间',
  `state` int(1) NULL DEFAULT NULL COMMENT '活动状态: 0 -> “未审核”; 1 -> \"审核通过\"; 2 -> \"已发布\"; 3 -> \"审核未通过\"; 4 -> \"已结束\";  5->\"已删除\";',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `club_id`(`club_id`) USING BTREE,
  INDEX `state_id`(`state`) USING BTREE,
  CONSTRAINT `cms_activity_ibfk_1` FOREIGN KEY (`club_id`) REFERENCES `cms_club` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '社团活动' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_activity
-- ----------------------------
INSERT INTO `cms_activity` VALUES (1, 5000, 'AA啊', 'welcome to 啊啊', '2020-04-01 20:47:00', 'https://images.unsplash.com/photo-1556035511-3168381ea4d4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=967&q=80', '2020-04-01 20:47:00', '2020-05-02 20:47:00', '风雨操场', 22, '2020-04-25 23:47:46', NULL, 5);
INSERT INTO `cms_activity` VALUES (2, 5000, 'AA啊', 'welcome to 啊啊', '2020-04-01 20:47:00', 'https://images.unsplash.com/photo-1556035511-3168381ea4d4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=967&q=80', '2020-04-01 20:47:00', '2020-05-02 20:47:00', '风雨操场', 22, '2020-04-25 23:47:46', NULL, 4);
INSERT INTO `cms_activity` VALUES (3, 5000, '才艺展示活动', '才艺展示活动', '这个名字听起来很老套，才艺比赛不要太多哦。可是最近大火的创造 101 充分表示：形式老的活动也能玩出新花样！比如我们也可以就针对大学女生，举行女生的风采大赛，不仅有个人形象展示、还有才艺表演，也可以加上知识问答，把零碎的小活动有序地安排成一个大活动。', 'https://cdn7-static.tshe.com/uploads/images/TopicImage/1572858622189timg%20(3)fesf.jpg', '2020-04-25 23:47:21', '2020-04-30 23:47:25', '风雨操场', 22, '2020-04-25 23:47:46', NULL, 1);
INSERT INTO `cms_activity` VALUES (4, 5000, '创意志愿者活动', '创意志愿者活动', '志愿者活动是大学必需的活动形式，对于此种活动意义的重要性远大于创意，但是精益求精也可以寻找一些创意点来充斥到活动中，比如部分社团，比如心理学社团可以表演心理剧甚至适合社区小孩看的心理教育小剧场，和动漫社、话剧社合作。', 'https://images.unsplash.com/photo-1556035511-3168381ea4d4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=967&q=80', '2020-04-25 23:47:21', '2020-04-30 23:47:25', '风雨操场', 22, '2020-04-25 23:47:46', '2020-05-25 19:33:12', 4);
INSERT INTO `cms_activity` VALUES (5, 10000, 'act5', 'welcome to act5', 'this is amazing!', 'https://images.unsplash.com/photo-1556035511-3168381ea4d4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=967&q=80', '2020-04-25 23:47:21', '2020-04-30 23:47:25', '风雨操场', 22, '2020-04-25 23:47:46', '2020-05-29 15:01:39', 4);
INSERT INTO `cms_activity` VALUES (6, 5000, 'act6', 'welcome to act6', 'this is amazing!', 'https://images.unsplash.com/photo-1556035511-3168381ea4d4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=967&q=80', '2020-04-25 23:47:21', '2020-04-30 23:47:25', '风雨操场', 22, '2020-04-25 23:47:46', '2020-05-09 21:20:49', 4);
INSERT INTO `cms_activity` VALUES (7, 5000, 'act7', 'welcome to act7', 'this is amazing!', 'https://images.unsplash.com/photo-1556035511-3168381ea4d4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=967&q=80', '2020-04-25 23:47:21', '2020-04-30 23:47:25', '风雨操场', 22, '2020-04-25 23:47:46', NULL, 4);
INSERT INTO `cms_activity` VALUES (8, 10000, 'AA啊', 'welcome to 啊啊', '2020-06-01 20:47:00', 'https://images.unsplash.com/photo-1556035511-3168381ea4d4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=967&q=80', '2020-06-01 20:47:00', '2020-05-20 20:47:00', '风雨操场', 22, '2020-04-25 23:47:46', '2020-05-07 10:15:06', 4);
INSERT INTO `cms_activity` VALUES (10, 5000, 'act', 'this is a title', 'this is amazing!', 'https://images.unsplash.com/photo-1556035511-3168381ea4d4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=967&q=80', '2018-04-19 00:00:00', '2018-04-22 00:00:00', '三区', 21, '2020-04-27 16:20:41', '2020-05-06 10:41:41', 1);
INSERT INTO `cms_activity` VALUES (11, 10001, 'act', 'this is a title', 'this is amazing!', 'https://images.unsplash.com/photo-1556035511-3168381ea4d4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=967&q=80', '2018-04-19 00:00:00', '2018-04-22 00:00:00', '三区', 10, '2020-04-27 16:20:41', '2020-05-06 10:48:36', 1);
INSERT INTO `cms_activity` VALUES (12, 10021, '一起去野炊', '野炊吧', '一起去野炊', '', '2020-05-20 00:00:00', '2020-05-22 00:00:00', '生活一区', 5, '2020-05-09 17:03:33', '2020-05-09 17:06:08', 4);
INSERT INTO `cms_activity` VALUES (13, 10000, '测试活动模块', '测试活动模块', '测试活动模块', '', '2020-05-22 00:00:00', '2020-05-29 00:01:02', '青春广场', 10, '2020-05-09 21:07:50', '2020-05-09 21:09:21', 4);
INSERT INTO `cms_activity` VALUES (14, 5000, '活动模块测试2', '活动模块测试2', '活动模块测试2', '', '2020-05-15 00:00:00', '2020-05-21 00:00:00', '青春广场', 5, '2020-05-09 21:31:00', '2020-05-09 21:34:32', 5);
INSERT INTO `cms_activity` VALUES (15, 5000, '活动模块测试3', '活动模块测试2', '活动模块测试3', '', '2020-05-29 00:00:00', '2020-05-30 00:00:00', '青春广场', 1, '2020-05-09 21:32:13', NULL, 0);
INSERT INTO `cms_activity` VALUES (16, 10025, 'test', 'dddd', 'tstese', '', '2020-05-19 00:00:00', '2020-05-27 00:00:00', '生活一区', 3, '2020-05-12 11:47:33', '2020-05-12 11:48:53', 1);
INSERT INTO `cms_activity` VALUES (17, 10021, '11', '11', '11', '', '2020-05-20 00:00:00', '2020-05-30 00:00:00', '青春广场', 2, '2020-05-18 22:47:54', NULL, 0);
INSERT INTO `cms_activity` VALUES (18, 10019, '非社团成员评论积分获取测试', '测试', '测试用', '', '2020-05-20 00:00:00', '2020-05-21 00:00:00', '青春广场', 1, '2020-05-19 11:15:06', '2020-05-19 11:15:57', 4);
INSERT INTO `cms_activity` VALUES (19, 10021, 'qweqw', 'qeqweq', 'ewqewq', '', '2020-05-27 00:00:00', '2020-05-28 00:00:00', '风雨操场', 3, '2020-05-26 10:13:24', NULL, 0);
INSERT INTO `cms_activity` VALUES (20, 10021, '12312312', '12312312', '123123', '', '2020-05-27 00:00:00', '2020-05-30 00:00:00', '生活一区', 3, '2020-05-26 10:15:02', NULL, 0);
INSERT INTO `cms_activity` VALUES (21, 10021, '123', '23123', '312312', '', '2020-05-28 00:00:00', '2020-05-30 00:00:00', '生活二区', 1, '2020-05-26 10:15:56', NULL, 0);
INSERT INTO `cms_activity` VALUES (22, 10021, '2312312', '132123123', '123123', '', '2020-05-27 00:00:00', '2020-05-30 00:00:00', '生活二区', 4, '2020-05-26 11:07:09', NULL, 0);
INSERT INTO `cms_activity` VALUES (23, 10021, 'aaa', 'aaa', 'aa', '', '2020-05-28 00:00:00', '2020-05-29 00:00:00', '风雨操场', 1, '2020-05-26 11:10:18', NULL, 0);
INSERT INTO `cms_activity` VALUES (24, 10021, 'bb', 'bb', 'bb', '', '2020-05-29 00:00:00', '2020-05-30 00:00:00', '生活一区', 1, '2020-05-26 11:14:20', NULL, 0);
INSERT INTO `cms_activity` VALUES (25, 10021, 'tt', 'ttt', 'tttt', '', '2020-05-28 00:00:00', '2020-05-31 00:00:00', '生活一区', 120, '2020-05-26 11:15:03', NULL, 0);
INSERT INTO `cms_activity` VALUES (26, 10021, 'hhh', 'hhh', 'hh', '', '2020-05-29 00:00:00', '2020-05-30 00:00:00', '生活三区', 1, '2020-05-26 11:19:07', NULL, 0);
INSERT INTO `cms_activity` VALUES (27, 10000, 'test', 'dsds', 'dsds', '', '2020-05-30 00:00:00', '2020-05-30 00:00:00', '生活一区', 1, '2020-05-27 14:28:38', '2020-05-29 14:45:09', 5);
INSERT INTO `cms_activity` VALUES (28, 10016, 'test', 'what', 'test', '', '2020-05-28 00:00:00', '2020-05-30 00:00:00', '生活一区', 1, '2020-05-27 14:40:58', NULL, 0);
INSERT INTO `cms_activity` VALUES (29, 10000, '123', '123', '213', '', '2020-05-29 00:00:00', '2020-05-30 00:00:00', '生活二区', 1, '2020-05-27 15:03:57', '2020-05-27 15:05:39', 5);
INSERT INTO `cms_activity` VALUES (30, 10000, 'AAC', 'AC', 'C', '', '2020-05-28 00:00:00', '2020-05-30 00:00:00', '青春广场', 2, '2020-05-27 15:12:15', NULL, 0);
INSERT INTO `cms_activity` VALUES (31, 10000, '33', '33', '33', '', '2020-05-29 00:00:00', '2020-05-30 00:00:00', '风雨操场', 1, '2020-05-27 15:17:04', NULL, 0);
INSERT INTO `cms_activity` VALUES (32, 10000, 'x', 'xx', 'x', NULL, '2020-05-28 00:00:00', '2020-05-29 00:00:00', '青春广场', 1, '2020-05-27 19:11:03', '2020-05-27 19:15:14', 5);
INSERT INTO `cms_activity` VALUES (33, 10000, 'kkk', 'kkk', 'kkk', NULL, '2020-05-28 00:00:00', '2020-05-30 00:00:00', '风雨操场', 1, '2020-05-27 19:17:06', NULL, 0);
INSERT INTO `cms_activity` VALUES (34, 5000, 'aa', 'aa', 'asdsd', 'http://localhost:9520/files/images/cb45d6b250e14203a68d6cdd6d3d884c.jpg', '2020-06-07 09:01:47', '2020-06-08 09:01:47', NULL, 1, '2020-05-27 20:54:53', NULL, 0);
INSERT INTO `cms_activity` VALUES (35, 5000, 'aaa', 'aaa', 'asdsda', 'http://101.200.193.180:9520/files/images/d0c4f816a87f443cb697577df1a2eaf8.jpg', '2020-06-07 09:01:47', '2020-06-08 09:01:47', NULL, 1, '2020-05-27 21:06:40', NULL, 0);
INSERT INTO `cms_activity` VALUES (36, 5000, 'aaaa', 'aaaa', 'asdsdaa', 'http://101.200.193.180:9520/files/images/25ed889a95654064b1a01561b7853085.jpg', '2020-06-04 09:01:47', '2020-06-07 09:01:47', NULL, 1, '2020-05-27 21:13:44', NULL, 0);
INSERT INTO `cms_activity` VALUES (37, 10015, 'zz', 'zz', 'zz', 'http://101.200.193.180:9520/files/images/fc0eb2f924ce418893a92443e5fe5ea0.jpg', '2020-05-29 00:00:00', '2020-05-30 00:00:00', '青春广场', 1, '2020-05-28 10:22:16', '2020-05-28 10:37:13', 5);
INSERT INTO `cms_activity` VALUES (38, 10015, 'kk', '有空', '这种', 'http://101.200.193.180:9520/files/images/544d651231064703bdc35296615a8b4b.jpg', '2020-05-29 00:00:00', '2020-05-30 00:00:00', '风雨操场', 1, '2020-05-28 10:23:08', '2020-05-28 10:37:15', 5);
INSERT INTO `cms_activity` VALUES (39, 10015, 'l', 'l', 'l', 'http://101.200.193.180:9520/files/images/bcda9528676842559a6a91cc71baff0f.jpg', '2020-05-29 00:00:00', '2020-05-30 00:00:00', '风雨操场', 1, '2020-05-28 10:23:38', '2020-05-28 10:37:16', 5);
INSERT INTO `cms_activity` VALUES (40, 10015, 'zz', 'zz', 'zz', 'http://101.200.193.180:9520/files/images/ee2971cf3d634fc9bab2885d3b7766b0.jpg', '2020-05-29 00:00:00', '2020-05-30 00:00:00', '风雨操场', 1, '2020-05-28 10:38:33', NULL, 0);
INSERT INTO `cms_activity` VALUES (41, 10015, 'zz,1', 'zz,23', 'zz,2', 'http://101.200.193.180:9520/files/images/ec7478e27ece4c07a4d43c6334fcbd3f.jpg', '2020-05-29 00:00:00', '2020-05-30 00:00:00', '风雨操场,生活二区', 1, '2020-05-28 10:38:53', NULL, 0);
INSERT INTO `cms_activity` VALUES (42, 10015, 'zz,1,1', 'zz,23,2', 'zz,2,3', 'http://101.200.193.180:9520/files/images/c70056f0af574e2e9aba143699614a7a.jpg', '2020-05-29 00:00:00', '2020-05-30 00:00:00', '风雨操场,生活二区,风雨操场', 1, '2020-05-28 10:39:46', NULL, 0);
INSERT INTO `cms_activity` VALUES (43, 10000, 'zzz', 'zzz', '23', 'http://101.200.193.180:9520/files/images/4b9caad439ff40d8bd8e6ee192c18738.jpg', '2020-05-31 15:54:15', '2020-06-01 15:54:15', '生活二区', 3, '2020-05-30 15:56:07', '2020-05-30 15:56:43', 2);
INSERT INTO `cms_activity` VALUES (44, 10000, 'ki上校', 'ki上校', 'ku', 'http://101.200.193.180:9520/files/images/1ce48ce5432146119817de585f237548.png', '2020-05-31 15:58:15', '2020-06-01 15:58:15', '风雨操场', 5, '2020-05-30 15:59:19', '2020-05-30 16:00:05', 2);
INSERT INTO `cms_activity` VALUES (45, 10000, '123', '4', '2', 'http://101.200.193.180:9520/files/images/77c89d0644424174a215a018720a1536.jpg', '2020-05-31 16:11:05', '2020-06-01 16:11:05', '生活二区', 1, '2020-05-30 16:11:21', NULL, 0);
INSERT INTO `cms_activity` VALUES (46, 10000, 'zz', 'zz', 'zz', 'http://101.200.193.180:9520/files/images/390d4692d5d94ca2949e0eddc8872301.png', '2020-05-31 16:11:55', '2020-06-01 16:11:55', '风雨操场', 1, '2020-05-30 16:12:09', NULL, 0);

-- ----------------------------
-- Table structure for cms_bulletin
-- ----------------------------
DROP TABLE IF EXISTS `cms_bulletin`;
CREATE TABLE `cms_bulletin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `club_id` int(11) NULL DEFAULT NULL COMMENT '社团 id',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `body` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '内容',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `club_id`(`club_id`) USING BTREE,
  CONSTRAINT `cms_bulletin_ibfk_1` FOREIGN KEY (`club_id`) REFERENCES `cms_club` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 85 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '公告' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_bulletin
-- ----------------------------
INSERT INTO `cms_bulletin` VALUES (1, 5000, 'test', '\r\ncommon list:\r\n1. JSON 的变量名是不是都是采用驼峰格式（首字符小写） \r\n2. 分页格式是不是按照文档的来（totalCount、items）\r\n\r\n前端 checklist: \r\n 1. 访问页面需要的角色是否进行了控制？\r\n 2. 页面的布局是否符合审美，div 间要留有一些空隙，页面内容是否居中（相对于空白部分，而不是屏幕）\r\n 3. 用户体验如何，返回结果是否对用户做出反馈？（吐司提示...） \r\n\r\n后端 checklist： \r\n1. 需要组合查询或者关键字查询的接口都可以用了吗？接口文档是否对应做好更新？\r\n2. 返回列表的数据是否都用 CommonPage 封装，返回正确的格式？（分页） \r\n3. 数据库的数据是否清理过，现在都是完备的？数据量是否方便测试（至少 10 条） \r\n4. 需要权限访问的接口是否在业务层进行手动验证，或者通过注解处理？', '2020-04-29 14:06:59', '2020-05-01 22:50:38');
INSERT INTO `cms_bulletin` VALUES (2, 5000, 'string1', '前端可以开始测试了，根据状态码发现错误原因，有问题在 后端项目 发 issue（描述问题，贴上错误信息）并在群里提醒后端；\r\n如 @xxx https://github.com/EyesCMS/zhishe-cms/issues/37\r\n\r\n后端先完善代码，整理数据库；\r\n\r\n后端人员大致负责模块：\r\n  我、三福   1.x 2.x  用户\r\n  嘉泓          3.x  社团\r\n  杨泽          4.x  申请与审核\r\n  铭鸿          5.x 公告\r\n  三福          6.x 活动\r\n  我             7.x 8.x 活动论坛', '2020-04-25 20:50:52', '2020-04-25 20:51:17');
INSERT INTO `cms_bulletin` VALUES (3, 10000, 'srdsds', '1. 数据库现在最好不要手动插入，目前出现了不完备的情况，我觉得还是手动改清楚吧\r\n2. 后端异常时，Asserts.fail -> 400（不知道什么错误或者参数检验错误）, notFound -> 404, forbidden -> 403，大家按这个标准来', '2020-04-22 20:51:41', '2020-04-25 20:51:46');
INSERT INTO `cms_bulletin` VALUES (4, 5000, 'string1', '演示地址：http://www.macrozheng.com/admin/\r\n账户：admin\r\n密码：macro123', '2020-04-16 20:52:45', '2020-04-22 20:52:49');
INSERT INTO `cms_bulletin` VALUES (5, 5000, 'string1', 'the important thing in life is to have a great aim, and the determination to attain it', '2020-04-16 20:52:45', '2020-05-05 10:12:57');
INSERT INTO `cms_bulletin` VALUES (23, 5000, 'this is bulletin..', 'hhh, welcome to here!', '2020-04-29 23:41:59', '2020-04-29 23:41:59');
INSERT INTO `cms_bulletin` VALUES (24, 5000, 'test123', '变成1aa变成一个正能量的人变成一个正能量的人abcc变成一个正能量的人cdd还不如现在好好努力dd人生走过一段路，风景毕竟不相同。与其指望遇到一个谁，不如指望自己能吸引那样的人；与其指望每次失落的时候会有正能量出现温暖你，不如指望自己变成一个正能量的人；与其担心未来，还不如现在好好努力', '2020-04-30 13:43:33', '2020-05-25 16:59:15');
INSERT INTO `cms_bulletin` VALUES (25, 5000, '123', 'keep trying no matter how hard it seems. it will get easier.keep trying no matter how hard it seems. it will get easier.by reading we enrich the mind; by conversation we polish it', '2020-04-30 13:43:51', '2020-05-05 10:14:30');
INSERT INTO `cms_bulletin` VALUES (28, 10000, 'test11', 'knowlegde can change your fate and english can accomplish your future.enrich your life today,. yesterday is history.tomorrow is mystery', '2020-05-01 13:50:31', '2020-05-05 10:10:19');
INSERT INTO `cms_bulletin` VALUES (29, 10000, 'test11', ' yesterday is history.tomorrow is mystery.knowlegde can change your fate and english can accomplish your future.enrich your life today,.', '2020-05-01 13:50:49', '2020-05-05 10:10:44');
INSERT INTO `cms_bulletin` VALUES (30, 10016, 'test6', 'keep trying no matter how hard it seems. it will get easier.keep trying no matter how hard it seems. it will get easier.by reading we enrich the mind; by conversation we polish it', '2020-05-01 22:44:37', '2020-05-05 10:16:33');
INSERT INTO `cms_bulletin` VALUES (31, 10016, 'test7', '11111222222keep trying no matter how hard it seems. it will get easier.keep trying no matter how hard it seems. it will get easier.by reading we enrich the mind; by conversation we polish it', '2020-05-01 22:45:05', '2020-05-05 10:16:55');
INSERT INTO `cms_bulletin` VALUES (35, 10017, 'test10', 'test2aaa', '2020-05-04 14:35:07', '2020-05-04 14:35:07');
INSERT INTO `cms_bulletin` VALUES (36, 5000, 'test9', '999911111999999keep trying no matter how hard it seems. it will get easier.keep trying no matter how hard it seems. it will get easier.by reading we enrich the mind; by conversation we polish it', '2020-05-04 15:42:09', '2020-05-05 11:42:28');
INSERT INTO `cms_bulletin` VALUES (38, 5000, 'test12', 'aaaaaaaaa999911111999999keep trying no matter how hard it seems. it will get easier.keep trying no matter how hard it seems. it will get easier.by reading we enrich the mind; by conversation we polish it', '2020-05-05 11:42:43', '2020-05-05 11:42:43');
INSERT INTO `cms_bulletin` VALUES (41, 10000, '软件工程', '真好玩，头发都没了', '2020-05-05 16:27:49', '2020-05-05 16:27:49');
INSERT INTO `cms_bulletin` VALUES (42, 5000, 'test13', '世上有两种人，一种人一经打击就心灰意冷，从此消沉下去；一种人在和挫败挣扎一番之后，他总会找到一条更平坦更光明的路，使自己更坚强，无论是在精神上或在事实上，他都有机会以胜利者的姿态再度活跃起来', '2020-05-05 20:57:48', '2020-05-05 20:57:48');
INSERT INTO `cms_bulletin` VALUES (43, 5000, 'test14', '人生走过一段路，风景毕竟不相同。与其指望遇到一个谁，不如指望自己能吸引那样的人；与其指望每次失落的时候会有正能量出现温暖你，不如指望自己变成一个正能量的人；与其担心未来，还不如现在好好努力', '2020-05-05 20:58:09', '2020-05-05 20:58:09');
INSERT INTO `cms_bulletin` VALUES (44, 5000, 'test15', '111人生走过一段路，风景毕竟不相同。与其指望遇到一个谁，不如指望自己能吸引那样的人；与其指望每次失落的时候会有正能量出现温暖你，不如指望自己变成一个正能量的人；与其担心未来，还不如现在好好努力', '2020-05-05 20:58:24', '2020-05-05 20:58:24');
INSERT INTO `cms_bulletin` VALUES (45, 5000, 'test16', '111222人生走过一段路，风景毕竟不相同。与其指望遇到一个谁，不如指望自己能吸引那样的人；与其指望每次失落的时候会有正能量出现温暖你，不如指望自己变成一个正能量的人；与其担心未来，还不如现在好好努力', '2020-05-05 20:58:31', '2020-05-05 20:58:31');
INSERT INTO `cms_bulletin` VALUES (46, 10000, 'test16', '111222333人生走过一段路，风景毕竟不相同。与其指望遇到一个谁，不如指望自己能吸引那样的人；与其指望每次失落的时候会有正能量出现温暖你，不如指望自己变成一个正能量的人；与其担心未来，还不如现在好好努力', '2020-05-05 20:59:03', '2020-05-05 20:59:03');
INSERT INTO `cms_bulletin` VALUES (47, 10000, 'test101', '人生走过一段路，风景毕竟不相同。与其指望遇到一个谁，不如指望自己能吸引那样的人；与其指望每次失落的时候会有正能量出现温暖你，不如指望自己变成一个正能量的人；与其担心未来，还不如现在好好努力', '2020-05-05 20:59:13', '2020-05-29 14:26:55');
INSERT INTO `cms_bulletin` VALUES (49, 10000, 'test19', '111222333444455556666人生走过一段路，风景毕竟不相同。与其指望遇到一个谁，不如指望自己能吸引那样的人；与其指望每次失落的时候会有正能量出现温暖你，不如指望自己变成一个正能量的人；与其担心未来，还不如现在好好努力', '2020-05-05 20:59:27', '2020-05-05 20:59:27');
INSERT INTO `cms_bulletin` VALUES (51, 10015, 'test21', '11aaa人生走过一段路，风景毕竟不相同。与其指望遇到一个谁，不如指望自己能吸引那样的人；与其指望每次失落的时候会有正能量出现温暖你，不如指望自己变成一个正能量的人；与其担心未来，还不如现在好好努力', '2020-05-05 21:00:09', '2020-05-05 21:00:09');
INSERT INTO `cms_bulletin` VALUES (52, 10015, 'test22', '11aaabbb人生走过一段路，风景毕竟不相同。与其指望遇到一个谁，不如指望自己能吸引那样的人；与其指望每次失落的时候会有正能量出现温暖你，不如指望自己变成一个正能量的人；与其担心未来，还不如现在好好努力', '2020-05-05 21:00:17', '2020-05-05 21:00:17');
INSERT INTO `cms_bulletin` VALUES (53, 10015, 'test23', '11aaabbbccc人生走过一段路，风景毕竟不相同。与其指望遇到一个谁，不如指望自己能吸引那样的人；与其指望每次失落的时候会有正能量出现温暖你，不如指望自己变成一个正能量的人；与其担心未来，还不如现在好好努力', '2020-05-05 21:00:27', '2020-05-05 21:00:27');
INSERT INTO `cms_bulletin` VALUES (54, 10015, 'test24', '11aaabbbcccdddd人生走过一段路，风景毕竟不相同。与其指望遇到一个谁，不如指望自己能吸引那样的人；与其指望每次失落的时候会有正能量出现温暖你，不如指望自己变成一个正能量的人；与其担心未来，还不如现在好好努力', '2020-05-05 21:00:33', '2020-05-05 21:00:33');
INSERT INTO `cms_bulletin` VALUES (55, 10015, 'test25', '还不如现在好好努力11aaabbbcccdddd人生走过一段路，风景毕竟不相同。与其指望遇到一个谁，不如指望自己能吸引那样的人；与其指望每次失落的时候会有正能量出现温暖你，不如指望自己变成一个正能量的人；与其担心未来，还不如现在好好努力', '2020-05-05 21:00:57', '2020-05-05 21:00:57');
INSERT INTO `cms_bulletin` VALUES (56, 10015, 'test26', '还不如现在好好努力11aaab还不如现在好好努力bbcccdddd人生走过一段路，风景毕竟不相同。与其指望遇到一个谁，不如指望自己能吸引那样的人；与其指望每次失落的时候会有正能量出现温暖你，不如指望自己变成一个正能量的人；与其担心未来，还不如现在好好努力', '2020-05-05 21:01:05', '2020-05-05 21:01:05');
INSERT INTO `cms_bulletin` VALUES (57, 10015, 'test27', '还不如现在好好努力 还不如现在好好努力bbcccdd还不如现在好好努力dd人生走过一段路，风景毕竟不相同。与其指望遇到一个谁，不如指望自己能吸引那样的人；与其指望每次失落的时候会有正能量出现温暖你，不如指望自己变成一个正能量的人；与其担心未来，还不如现在好好努力', '2020-05-05 21:01:12', '2020-05-25 19:54:21');
INSERT INTO `cms_bulletin` VALUES (58, 10016, 'test28', '变成一个正能量的人11aaab还不如现在好好努力bbcccdd还不如现在好好努力dd人生走过一段路，风景毕竟不相同。与其指望遇到一个谁，不如指望自己能吸引那样的人；与其指望每次失落的时候会有正能量出现温暖你，不如指望自己变成一个正能量的人；与其担心未来，还不如现在好好努力', '2020-05-05 21:01:57', '2020-05-05 21:01:57');
INSERT INTO `cms_bulletin` VALUES (59, 10016, 'test29', '变成一个正能量的人11aa变成一个正能量的人ab还不如现在好好努力bbcccdd还不如现在好好努力dd人生走过一段路，风景毕竟不相同。与其指望遇到一个谁，不如指望自己能吸引那样的人；与其指望每次失落的时候会有正能量出现温暖你，不如指望自己变成一个正能量的人；与其担心未来，还不如现在好好努力', '2020-05-05 21:02:03', '2020-05-05 21:02:03');
INSERT INTO `cms_bulletin` VALUES (60, 10016, 'test30', '变成一个正能量的人11aa变成一个正能量的人abcccdd还不如现在好好努力dd人生走过一段路，风景毕竟不相同。与其指望遇到一个谁，不如指望自己能吸引那样的人；与其指望每次失落的时候会有正能量出现温暖你，不如指望自己变成一个正能量的人；与其担心未来，还不如现在好好努力', '2020-05-05 21:02:14', '2020-05-05 21:02:14');
INSERT INTO `cms_bulletin` VALUES (61, 10016, 'test31', '变成一个正能量的人11aa变成一个正能量的人abcc变成一个正能量的人cdd还不如现在好好努力dd人生走过一段路，风景毕竟不相同。与其指望遇到一个谁，不如指望自己能吸引那样的人；与其指望每次失落的时候会有正能量出现温暖你，不如指望自己变成一个正能量的人；与其担心未来，还不如现在好好努力', '2020-05-05 21:02:20', '2020-05-05 21:02:20');
INSERT INTO `cms_bulletin` VALUES (62, 10016, 'test32', '变成1aa变成一个正能量的人abcc变成一个正能量的人cdd还不如现在好好努力dd人生走过一段路，风景毕竟不相同。与其指望遇到一个谁，不如指望自己能吸引那样的人；与其指望每次失落的时候会有正能量出现温暖你，不如指望自己变成一个正能量的人；与其担心未来，还不如现在好好努力', '2020-05-05 21:02:36', '2020-05-05 21:02:36');
INSERT INTO `cms_bulletin` VALUES (63, 10016, 'test33', '变成1aa变成一个正能量的人变成一个正能量的人abcc变成一个正能量的人cdd还不如现在好好努力dd人生走过一段路，风景毕竟不相同。与其指望遇到一个谁，不如指望自己能吸引那样的人；与其指望每次失落的时候会有正能量出现温暖你，不如指望自己变成一个正能量的人；与其担心未来，还不如现在好好努力', '2020-05-05 21:02:42', '2020-05-05 21:02:42');
INSERT INTO `cms_bulletin` VALUES (65, 10020, 'test36', '变成1aa变成一个正能量的人变成一个正能量的人abcc变成一个正能量的人cdd还不如现在好好努力dd人生走过一段路，风景毕竟不相同。与其指望遇到一个谁，不如指望自己能吸引那样的人；与其指望每次失落的时候会有正能量出现温暖你，不如指望自己变成一个正能量的人；与其担心未来，还不如现在好好努力', '2020-05-05 21:04:19', '2020-05-05 21:04:19');
INSERT INTO `cms_bulletin` VALUES (67, 10000, '联调第三天1', '今天前端把 issue 继续处理 issue;\n后端把单元测试完成，还有测试报告部分。', '2020-05-08 08:06:42', '2020-05-29 16:47:01');
INSERT INTO `cms_bulletin` VALUES (72, 10021, '入社须知', '这是一个互相交流的社团 😁 ~ ', '2020-05-09 17:12:56', '2020-05-09 17:13:05');
INSERT INTO `cms_bulletin` VALUES (74, 10021, '测试公告', '去问驱蚊器零五科技强烈渴求继往开来合理而且我看了今晚去看二七五九二二九七二九七五而近期伪军五千2二级；而近期闻鸡起舞2；二级；二九五七二七五九二；二级；而近期闻鸡起舞；了', '2020-05-09 21:47:37', '2020-05-09 21:47:37');
INSERT INTO `cms_bulletin` VALUES (77, 10021, '1231', '测试修改内容功能', '2020-05-09 22:33:12', '2020-05-29 15:00:11');
INSERT INTO `cms_bulletin` VALUES (78, 10021, '1231', '21321', '2020-05-09 22:33:14', '2020-05-29 17:28:09');
INSERT INTO `cms_bulletin` VALUES (80, 10025, 'test', 'dsdds', '2020-05-12 11:48:18', '2020-05-12 11:48:18');
INSERT INTO `cms_bulletin` VALUES (82, 10021, '发布', '测试发布功能，', '2020-05-29 15:00:45', '2020-05-29 15:00:57');
INSERT INTO `cms_bulletin` VALUES (83, 10021, '测试分页', '看看能不能分页；\n我再改改看', '2020-05-29 22:30:16', '2020-05-29 22:30:36');
INSERT INTO `cms_bulletin` VALUES (84, 10001, '这是第一天条公告', '大家一起来', '2020-05-29 23:20:36', '2020-05-29 23:20:36');

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
) ENGINE = InnoDB AUTO_INCREMENT = 10044 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '社长换届申请' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_chief_change_apply
-- ----------------------------
INSERT INTO `cms_chief_change_apply` VALUES (3, 5000, 10088, 10090, 'make friends', '2020-04-28 09:08:24', '2020-04-30 16:32:24', 1);
INSERT INTO `cms_chief_change_apply` VALUES (4, 10011, 10094, 10095, 'make friends', '2020-04-28 09:24:46', '2020-04-30 16:33:50', 2);
INSERT INTO `cms_chief_change_apply` VALUES (6, 5000, 10090, 10095, 'make friends', '2020-04-30 18:33:42', '2020-04-30 21:11:36', 2);
INSERT INTO `cms_chief_change_apply` VALUES (12, 10000, 10088, 10089, '接口测试', '2020-05-07 23:53:43', '2020-05-08 09:41:49', 1);
INSERT INTO `cms_chief_change_apply` VALUES (13, 10015, 10088, 10089, '测试一下', '2020-05-08 10:49:23', '2020-05-08 11:01:16', 2);
INSERT INTO `cms_chief_change_apply` VALUES (14, 10016, 10088, 10089, '管理员别同意，测试一下', '2020-05-08 10:58:10', '2020-05-08 11:03:11', 2);
INSERT INTO `cms_chief_change_apply` VALUES (15, 10000, 10089, 10088, '测试一下', '2020-05-08 10:59:02', '2020-05-08 11:03:11', 2);
INSERT INTO `cms_chief_change_apply` VALUES (16, 10015, 10088, 10089, '测试一下，管理员退回', '2020-05-08 11:01:54', '2020-05-08 11:03:12', 2);
INSERT INTO `cms_chief_change_apply` VALUES (17, 10015, 10088, 10089, '测试一下，管理员不要同意~~', '2020-05-08 11:03:49', '2020-05-08 11:05:54', 2);
INSERT INTO `cms_chief_change_apply` VALUES (18, 10015, 10088, 10089, '测试一下，管理员别同意~~', '2020-05-08 11:06:28', '2020-05-09 17:22:34', 2);
INSERT INTO `cms_chief_change_apply` VALUES (10000, 10013, 10094, 10088, '单元测试，千万别审核', '2020-05-07 23:10:27', NULL, 0);
INSERT INTO `cms_chief_change_apply` VALUES (10001, 10013, 10094, 10088, '单元测试', '2020-05-07 23:10:27', '2020-05-07 23:10:59', 2);
INSERT INTO `cms_chief_change_apply` VALUES (10013, 10000, 10089, 10088, '我就试试', '2020-05-09 12:51:57', '2020-05-09 12:59:35', 1);
INSERT INTO `cms_chief_change_apply` VALUES (10018, 10021, 10089, 10088, '123', '2020-05-09 20:50:59', '2020-05-09 20:53:09', 1);
INSERT INTO `cms_chief_change_apply` VALUES (10019, 10021, 10088, 10089, '管理的社团太多了', '2020-05-09 20:56:26', '2020-05-09 20:56:49', 1);
INSERT INTO `cms_chief_change_apply` VALUES (10020, 10000, 10088, 10089, '测试', '2020-05-09 21:27:54', NULL, 0);
INSERT INTO `cms_chief_change_apply` VALUES (10028, 10025, 10088, 10090, 'testdds', '2020-05-12 11:48:00', '2020-05-12 11:49:06', 1);
INSERT INTO `cms_chief_change_apply` VALUES (10039, 10026, 10088, 10107, '无', '2020-05-20 15:49:49', '2020-05-20 15:50:16', 1);
INSERT INTO `cms_chief_change_apply` VALUES (10040, 10016, 10088, 10089, '123', '2020-05-29 14:39:59', '2020-05-29 14:41:53', 1);
INSERT INTO `cms_chief_change_apply` VALUES (10041, 10015, 10088, 10089, 'zzz', '2020-05-29 14:43:42', '2020-05-29 14:44:19', 1);
INSERT INTO `cms_chief_change_apply` VALUES (10042, 10015, 10089, 10088, '123', '2020-05-29 14:46:45', '2020-05-29 14:47:00', 2);
INSERT INTO `cms_chief_change_apply` VALUES (10043, 10015, 10089, 10088, 'zrr', '2020-05-29 14:48:05', '2020-05-29 14:48:45', 1);

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
  `credit` int(11) NOT NULL DEFAULT 0 COMMENT '社团积分',
  `grade_id` int(11) NOT NULL DEFAULT 1 COMMENT '社团等级ID',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `delete_status` int(1) NULL DEFAULT 0 COMMENT '删除状态：0->未删除；1->已删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `chief_id`(`chief_id`) USING BTREE,
  CONSTRAINT `cms_club_ibfk_1` FOREIGN KEY (`chief_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cms_club_ibfk_2` FOREIGN KEY (`chief_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10029 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '社团' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_club
-- ----------------------------
INSERT INTO `cms_club` VALUES (5000, '足球社', 10090, 5, NULL, NULL, 'http://101.200.193.180:9520/files/images/955b523f658e4a8ab919bbcb706264ac.jpg', 1, '运动', 18, 1, '2020-04-01 00:00:00', 0);
INSERT INTO `cms_club` VALUES (10000, '软件学社', 10088, 7, '10000', '好好学习天天向上', 'http://101.200.193.180:9520/files/images/0af15e81f114424887f2f015a361a2a3.jpg', 1, '学习', 26, 1, '2020-04-01 00:00:00', 0);
INSERT INTO `cms_club` VALUES (10001, '体育社', 10088, 3, NULL, NULL, 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 1, '运动', 1, 1, '2020-04-23 21:27:56', 0);
INSERT INTO `cms_club` VALUES (10003, '音乐社', 10088, 2, NULL, NULL, 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 1, '运动', 0, 1, '2020-04-24 20:20:23', 1);
INSERT INTO `cms_club` VALUES (10011, '书画社', 10094, 1, NULL, NULL, NULL, 1, '学习', 0, 1, '2020-04-26 23:09:44', 1);
INSERT INTO `cms_club` VALUES (10012, '舞蹈社', 10094, 1, NULL, NULL, NULL, 1, '其他', 0, 1, '2020-04-28 12:02:17', 1);
INSERT INTO `cms_club` VALUES (10013, '7社', 10094, 1, NULL, NULL, 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2919792551,2033781687&fm=26&gp=0.jpg', 1, '学习', 3, 1, '2020-04-28 19:53:37', 0);
INSERT INTO `cms_club` VALUES (10014, '8社', 10094, 2, NULL, NULL, 'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1950608743,1874914300&fm=26&gp=0.jpg', 0, '艺术', 1, 1, '2020-05-01 15:00:13', 0);
INSERT INTO `cms_club` VALUES (10015, 'what club', 10088, 2, '2854125871', '好好社团', 'https://file02.16sucai.com/d/file/2014/0829/372edfeb74c3119b666237bd4af92be5.jpg', 0, '艺术', 1, 1, '2020-05-01 15:18:56', 0);
INSERT INTO `cms_club` VALUES (10016, '测试1社', 10089, 2, '123456', '和我们一起快乐码代码', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1589016099279&di=8ad04572ce76e3aeceafe890a9cc9d90&imgtype=0&src=http%3A%2F%2Fimg2.imgtn.bdimg.com%2Fit%2Fu%3D824188197%2C3321145112%26fm%3D214%26gp%3D0.jpg', 1, '艺术', 2, 1, '2020-05-01 18:04:03', 0);
INSERT INTO `cms_club` VALUES (10017, '测试2社', 10095, 3, NULL, NULL, NULL, 1, '艺术', 0, 1, '2020-05-01 18:04:07', 1);
INSERT INTO `cms_club` VALUES (10018, '测试3社', 10090, 1, NULL, NULL, NULL, 1, '其他', 0, 1, '2020-05-01 18:05:53', 1);
INSERT INTO `cms_club` VALUES (10019, '测试4社', 10095, 1, NULL, NULL, NULL, 0, '休闲', 0, 1, '2020-05-05 00:13:48', 0);
INSERT INTO `cms_club` VALUES (10020, '测试5社', 10097, 1, NULL, NULL, NULL, 1, '其他', 0, 1, '2020-05-05 00:50:05', 0);
INSERT INTO `cms_club` VALUES (10021, '羽毛球社', 10089, 2, NULL, '喜欢羽毛球就加入吧', 'http://101.200.193.180:9520/files/images/b4b1ac8ccbe34c298e70354572e4b563.jpg', 1, '运动', 2, 1, '2020-05-09 16:57:32', 0);
INSERT INTO `cms_club` VALUES (10022, '测试一下', 10088, 1, NULL, NULL, 'https://file02.16sucai.com/d/file/2014/0829/372edfeb74c3119b666237bd4af92be5.jpg', 0, '其他', 0, 1, '2020-05-09 17:20:31', 0);
INSERT INTO `cms_club` VALUES (10023, '测试M09', 10094, 1, NULL, NULL, NULL, 1, '艺术', 0, 1, '2020-05-09 20:46:23', 0);
INSERT INTO `cms_club` VALUES (10024, '测试创建社团', 10088, 1, NULL, NULL, NULL, 0, '其他', 0, 1, '2020-05-09 20:52:27', 1);
INSERT INTO `cms_club` VALUES (10025, 'test club 3', 10090, 2, NULL, NULL, NULL, 1, '运动', 1, 1, '2020-05-12 11:45:15', 0);
INSERT INTO `cms_club` VALUES (10026, '测试走马灯社', 10107, 2, NULL, '爱来不来！', 'http://101.200.193.180:9520/files/images/ca96465672344c01b30cdc61a416ec13.jpg', 0, '其他', 5, 1, '2020-05-19 17:49:04', 0);
INSERT INTO `cms_club` VALUES (10027, '电竞社', 10088, 1, NULL, NULL, NULL, 0, '休闲', 0, 1, '2020-05-28 16:31:01', 0);
INSERT INTO `cms_club` VALUES (10028, '荣耀社', 10122, 1, NULL, NULL, NULL, 0, '其他', 0, 1, '2020-05-30 16:43:59', 0);

-- ----------------------------
-- Table structure for cms_club_create_apply
-- ----------------------------
DROP TABLE IF EXISTS `cms_club_create_apply`;
CREATE TABLE `cms_club_create_apply`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `applicant` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '申请人',
  `club_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '社团名称',
  `official_state` int(1) NULL DEFAULT NULL COMMENT '官方状态: 0 -> 非正式; 1 -> 正式;',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '社团类别',
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '申请原因',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '申请时间',
  `handle_at` datetime(0) NULL DEFAULT NULL COMMENT '处理时间',
  `state` int(1) NULL DEFAULT NULL COMMENT '申请状态：0 -> 未审核; 1 -> 审核通过; 2 -> 审核未通过;',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `cms_club_create_apply_ibfk_1`(`user_id`) USING BTREE,
  CONSTRAINT `cms_club_create_apply_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10052 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '社团创建申请' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_club_create_apply
-- ----------------------------
INSERT INTO `cms_club_create_apply` VALUES (3, 10088, 'test', 'xx社', 1, '运动', 'make friends', '2020-04-23 21:21:40', '2020-04-23 21:27:57', 1);
INSERT INTO `cms_club_create_apply` VALUES (4, 10088, 'test', 'xxyy社', 1, '运动', 'make friends', '2020-04-23 21:28:47', '2020-04-23 23:01:44', 1);
INSERT INTO `cms_club_create_apply` VALUES (5, 10088, 'test', 'xxyyzz社', 1, '运动', 'make friends', '2020-04-24 20:15:29', '2020-04-24 20:21:02', 1);
INSERT INTO `cms_club_create_apply` VALUES (6, 10088, 'test1', 'xxyyzzqqqq社', 1, '运动', 'make friends', '2020-04-26 17:38:37', '2020-04-26 17:39:09', 1);
INSERT INTO `cms_club_create_apply` VALUES (7, 10091, 'test3', 'xxyyzzqqqq1社', 1, '运动', 'make friends', '2020-04-26 18:32:10', '2020-04-26 18:32:37', 1);
INSERT INTO `cms_club_create_apply` VALUES (8, 10092, 'test3', '1社', 1, '运动', 'make friends', '2020-04-26 18:35:41', '2020-04-26 18:36:09', 1);
INSERT INTO `cms_club_create_apply` VALUES (9, 10093, 'test3', '2社', 1, '运动', 'make friends', '2020-04-26 18:44:34', '2020-04-26 18:45:14', 1);
INSERT INTO `cms_club_create_apply` VALUES (10, 10093, 'test3', '3社', 1, '运动', 'make friends', '2020-04-26 18:57:10', '2020-04-26 18:57:28', 1);
INSERT INTO `cms_club_create_apply` VALUES (11, 10093, 'test3', '4社', 1, '休闲', 'make friends', '2020-04-26 18:59:37', '2020-04-26 18:59:51', 1);
INSERT INTO `cms_club_create_apply` VALUES (12, 10094, 'test3', 'xxx', 1, '艺术', 'make friends', '2020-04-26 20:41:13', '2020-04-26 20:45:20', 2);
INSERT INTO `cms_club_create_apply` VALUES (13, 10094, 'test3', '5社', 1, '学习', 'make friends', '2020-04-26 23:09:19', '2020-04-26 23:09:45', 1);
INSERT INTO `cms_club_create_apply` VALUES (14, 10094, 'test6', '6社', 0, '其他', 'make friends', '2020-04-28 12:01:38', '2020-04-28 12:02:17', 1);
INSERT INTO `cms_club_create_apply` VALUES (15, 10094, 'test6', '7社', 0, '学习', 'make friends', '2020-04-28 19:45:06', '2020-04-28 19:53:37', 1);
INSERT INTO `cms_club_create_apply` VALUES (16, 10094, 'test6', '8社', 0, '艺术', 'make friends', '2020-04-30 18:16:24', '2020-05-01 15:00:13', 1);
INSERT INTO `cms_club_create_apply` VALUES (17, 10088, 'test', '1', 0, '学习', '1', '2020-05-01 14:32:03', '2020-05-01 15:18:56', 1);
INSERT INTO `cms_club_create_apply` VALUES (18, 10088, 'test', '测试1社', 1, '艺术', 'make friends', '2020-05-01 18:02:47', '2020-05-01 18:04:03', 1);
INSERT INTO `cms_club_create_apply` VALUES (19, 10089, 'test1', '测试2社', 0, '艺术', 'make', '2020-05-01 18:03:32', '2020-05-01 18:04:07', 1);
INSERT INTO `cms_club_create_apply` VALUES (20, 10090, 'test2', '测试3社', 1, '其他', 'make', '2020-05-01 18:05:41', '2020-05-01 18:05:53', 1);
INSERT INTO `cms_club_create_apply` VALUES (21, 10088, 'test', '11', 0, '休闲', '11', '2020-05-01 22:06:20', '2020-05-05 00:41:22', 2);
INSERT INTO `cms_club_create_apply` VALUES (22, 10095, 'test7', '测试4社', 0, '休闲', 'make', '2020-05-05 00:12:28', '2020-05-05 00:13:48', 1);
INSERT INTO `cms_club_create_apply` VALUES (23, 10097, 'test9', '测试5社', 1, '其他', 'make', '2020-05-05 00:16:51', '2020-05-05 00:21:35', 2);
INSERT INTO `cms_club_create_apply` VALUES (24, 10097, 'test9', '测试5社', 1, '休闲', 'make', '2020-05-05 00:49:26', '2020-05-05 00:50:05', 1);
INSERT INTO `cms_club_create_apply` VALUES (25, 10088, 'test', '玩耍社', 0, '休闲', '嗨皮一下', '2020-05-06 12:35:41', NULL, 0);
INSERT INTO `cms_club_create_apply` VALUES (29, 10088, 'test', 'programming team', 0, '学习', 'I love programming!', '2020-05-08 08:45:58', NULL, 0);
INSERT INTO `cms_club_create_apply` VALUES (30, 10088, 'test', 'pingpong', 0, '运动', 'want to exercise', '2020-05-08 08:49:00', NULL, 0);
INSERT INTO `cms_club_create_apply` VALUES (31, 10088, 'test', '桌游社', 0, '休闲', '快乐一下', '2020-05-08 12:00:21', NULL, 0);
INSERT INTO `cms_club_create_apply` VALUES (32, 10088, 'test', '测试', 0, '艺术', '管理员不用同意~~', '2020-05-08 12:17:07', NULL, 0);
INSERT INTO `cms_club_create_apply` VALUES (33, 10088, 'test', '码农社', 0, '学习', '测试一下', '2020-05-08 12:19:53', NULL, 0);
INSERT INTO `cms_club_create_apply` VALUES (34, 10088, 'test', '嗨皮社', 0, '休闲', '开心开心', '2020-05-08 12:26:16', NULL, 0);
INSERT INTO `cms_club_create_apply` VALUES (35, 10088, 'test', '玩耍111社', 0, '运动', '测试', '2020-05-08 12:42:04', NULL, 0);
INSERT INTO `cms_club_create_apply` VALUES (36, 10088, 'test', '想不出叫什么了社', 0, '其他', '再测试一下', '2020-05-08 12:46:43', NULL, 0);
INSERT INTO `cms_club_create_apply` VALUES (37, 10088, 'test', '嗨起来', 0, '休闲', '和大家一起玩', '2020-05-08 15:42:32', '2020-05-09 17:20:51', 2);
INSERT INTO `cms_club_create_apply` VALUES (10000, 10088, 'test', '单元测试', 0, '其他', '单元测试用，管理员不要进行审核', '2020-05-07 12:33:47', NULL, 0);
INSERT INTO `cms_club_create_apply` VALUES (10001, 10088, 'test', '单元测试2', 0, '其他', '单元测试用，管理员退回申请', '2020-05-07 12:42:48', '2020-05-07 12:54:27', 2);
INSERT INTO `cms_club_create_apply` VALUES (10002, 10094, 'test6', '单元测试用的', 0, '运动', 'want to exercise', '2020-05-08 08:49:00', NULL, 0);
INSERT INTO `cms_club_create_apply` VALUES (10003, 10088, 'test', 'pingpong', 0, '运动', 'want to exercise', '2020-05-08 08:49:00', NULL, 0);
INSERT INTO `cms_club_create_apply` VALUES (10007, 10088, 'test', '测试一下', 0, '其他', '123', '2020-05-08 22:49:27', '2020-05-09 17:20:31', 1);
INSERT INTO `cms_club_create_apply` VALUES (10020, 10089, 'test1', '羽毛球社', 0, '运动', '和大家一起快乐地游戏！', '2020-05-09 16:56:05', '2020-05-09 16:57:32', 1);
INSERT INTO `cms_club_create_apply` VALUES (10021, 10094, 'test6', '测试M09', 0, '艺术', '测试', '2020-05-09 20:45:17', '2020-05-09 20:46:23', 1);
INSERT INTO `cms_club_create_apply` VALUES (10022, 10094, 'test6', '测试M092', 0, '艺术', '测试', '2020-05-09 20:47:01', '2020-05-09 20:47:27', 2);
INSERT INTO `cms_club_create_apply` VALUES (10023, 10088, 'test', '测试创建社团', 0, '其他', '测试创建社团功能', '2020-05-09 20:50:44', '2020-05-09 20:52:27', 1);
INSERT INTO `cms_club_create_apply` VALUES (10033, 10088, 'test', 'test club 3', 0, '运动', 'add a club', '2020-05-12 11:44:39', '2020-05-12 11:45:15', 1);
INSERT INTO `cms_club_create_apply` VALUES (10044, 10088, 'test', '测试走马灯社', 0, '其他', '测试走马灯', '2020-05-19 17:48:47', '2020-05-19 17:49:04', 1);
INSERT INTO `cms_club_create_apply` VALUES (10045, 10088, 'test', '测试50社', 1, '运动类', 'make', '2020-05-25 16:46:32', '2020-05-25 16:47:25', 2);
INSERT INTO `cms_club_create_apply` VALUES (10046, 10088, 'test', '相声社', 0, '艺术', '一起听相声吧~', '2020-05-26 14:09:55', NULL, 0);
INSERT INTO `cms_club_create_apply` VALUES (10047, 10088, 'test', '电竞社', 0, '休闲', '111', '2020-05-28 16:30:38', '2020-05-28 16:31:01', 1);
INSERT INTO `cms_club_create_apply` VALUES (10048, 10094, 'test6', '9999', 0, '艺术', '9999', '2020-05-29 10:37:59', NULL, 0);
INSERT INTO `cms_club_create_apply` VALUES (10049, 10088, 'test', '测试500社', 1, '运动类', 'make', '2020-05-29 17:53:53', NULL, 0);
INSERT INTO `cms_club_create_apply` VALUES (10050, 10088, 'test', '测试用的', 0, '艺术', '当然是测试', '2020-05-29 18:40:17', NULL, 0);
INSERT INTO `cms_club_create_apply` VALUES (10051, 10122, 'zzm', '荣耀社', 0, '其他', '一起上王者', '2020-05-30 16:34:32', '2020-05-30 16:43:59', 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 10044 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '解散社团申请' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_club_disband_apply
-- ----------------------------
INSERT INTO `cms_club_disband_apply` VALUES (1, 10001, '没为什么', '2020-04-23 22:44:04', '2020-04-24 00:16:31', 2);
INSERT INTO `cms_club_disband_apply` VALUES (4, 10003, '也没为什么', '2020-04-25 00:34:07', '2020-04-25 00:35:01', 2);
INSERT INTO `cms_club_disband_apply` VALUES (16, 10003, '也没为什么', '2020-04-26 20:10:46', '2020-04-26 20:11:12', 2);
INSERT INTO `cms_club_disband_apply` VALUES (17, 10003, '也没为什么', '2020-04-26 20:11:19', '2020-04-26 20:11:35', 2);
INSERT INTO `cms_club_disband_apply` VALUES (18, 10011, '也没为什么', '2020-04-26 23:10:37', NULL, 2);
INSERT INTO `cms_club_disband_apply` VALUES (19, 10012, '也没为什么', '2020-04-30 18:25:38', '2020-05-01 18:10:41', 2);
INSERT INTO `cms_club_disband_apply` VALUES (20, 10018, '也没什么', '2020-05-01 18:10:03', '2020-05-01 18:10:45', 1);
INSERT INTO `cms_club_disband_apply` VALUES (21, 10020, 'meiyisi', '2020-05-05 00:51:03', '2020-05-05 00:51:33', 2);
INSERT INTO `cms_club_disband_apply` VALUES (22, 10003, 'meiyisi', '2020-05-05 09:21:32', '2020-05-05 09:22:26', 1);
INSERT INTO `cms_club_disband_apply` VALUES (23, 10012, '保持数据库数据完整性', '2020-05-05 09:30:53', '2020-05-05 09:31:18', 1);
INSERT INTO `cms_club_disband_apply` VALUES (24, 10017, '保持数据库数据完整性', '2020-05-05 09:34:25', '2020-05-05 09:34:47', 1);
INSERT INTO `cms_club_disband_apply` VALUES (28, 10016, '啊啊', '2020-05-07 23:18:20', '2020-05-09 17:21:37', 2);
INSERT INTO `cms_club_disband_apply` VALUES (10000, 10014, '单元测试，千万不要进行审核', '2020-05-07 21:59:47', NULL, 0);
INSERT INTO `cms_club_disband_apply` VALUES (10001, 10014, '单元测试', '2020-05-07 21:53:27', '2020-05-07 21:54:16', 2);
INSERT INTO `cms_club_disband_apply` VALUES (10002, 10014, '单元测试', '2020-05-07 21:53:27', NULL, 0);
INSERT INTO `cms_club_disband_apply` VALUES (10010, 10015, '没有原因', '2020-05-09 10:45:07', '2020-05-09 10:51:39', 2);
INSERT INTO `cms_club_disband_apply` VALUES (10011, 10015, '没有原因', '2020-05-09 10:52:59', '2020-05-09 11:20:00', 2);
INSERT INTO `cms_club_disband_apply` VALUES (10012, 10015, '我就是试试', '2020-05-09 11:20:49', '2020-05-09 11:22:48', 2);
INSERT INTO `cms_club_disband_apply` VALUES (10013, 10015, '测试', '2020-05-09 11:23:08', '2020-05-09 11:25:43', 2);
INSERT INTO `cms_club_disband_apply` VALUES (10014, 10015, '测试', '2020-05-09 11:26:06', NULL, 0);
INSERT INTO `cms_club_disband_apply` VALUES (10023, 10024, '测试解散社团', '2020-05-09 20:53:14', '2020-05-09 20:53:36', 1);
INSERT INTO `cms_club_disband_apply` VALUES (10043, 10027, '无', '2020-05-29 15:39:46', NULL, 0);

-- ----------------------------
-- Table structure for cms_club_grade
-- ----------------------------
DROP TABLE IF EXISTS `cms_club_grade`;
CREATE TABLE `cms_club_grade`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '等级id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '等级名称',
  `lower_limit` int(11) NOT NULL DEFAULT 0 COMMENT '积分下限',
  `upper_limit` int(11) NOT NULL DEFAULT 0 COMMENT '积分上限',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '社团等级表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_club_grade
-- ----------------------------
INSERT INTO `cms_club_grade` VALUES (1, 'Lv1', 0, 999);
INSERT INTO `cms_club_grade` VALUES (2, 'Lv2', 1000, 1999);
INSERT INTO `cms_club_grade` VALUES (3, 'Lv3', 2000, 2999);
INSERT INTO `cms_club_grade` VALUES (4, 'Lv4', 3000, 3999);
INSERT INTO `cms_club_grade` VALUES (5, 'Lv5', 4000, 4999);

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
) ENGINE = InnoDB AUTO_INCREMENT = 10070 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '加入社团申请' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_club_join_apply
-- ----------------------------
INSERT INTO `cms_club_join_apply` VALUES (2, 10089, 5000, '也没为什么', '2020-04-24 21:36:38', '2020-04-24 22:53:44', 2);
INSERT INTO `cms_club_join_apply` VALUES (8, 10090, 5000, '也没为什么hhsssh', '2020-04-27 20:07:08', '2020-04-27 20:07:45', 1);
INSERT INTO `cms_club_join_apply` VALUES (9, 10088, 10013, '也没为什么哈哈哈hhsssh', '2020-05-01 14:23:33', '2020-05-01 14:24:50', 1);
INSERT INTO `cms_club_join_apply` VALUES (10, 10089, 10016, '也没为什么哈哈哈hhsssh', '2020-05-01 18:15:14', '2020-05-01 18:15:34', 1);
INSERT INTO `cms_club_join_apply` VALUES (13, 10088, 5000, '啊啊啊', '2020-05-01 22:20:25', '2020-05-09 17:11:03', 2);
INSERT INTO `cms_club_join_apply` VALUES (16, 10088, 10001, '我想加入', '2020-05-06 13:31:14', '2020-05-29 23:20:48', 1);
INSERT INTO `cms_club_join_apply` VALUES (17, 10088, 10013, '没为啥就是测试多加几条记录', '2020-05-07 09:32:27', '2020-05-07 09:36:17', 1);
INSERT INTO `cms_club_join_apply` VALUES (18, 10089, 10013, '没为啥这是第二条记录', '2020-05-07 09:34:24', '2020-05-07 09:36:18', 1);
INSERT INTO `cms_club_join_apply` VALUES (19, 10090, 10013, '当然还是为了测试啦', '2020-05-07 09:34:57', '2020-05-07 09:36:19', 1);
INSERT INTO `cms_club_join_apply` VALUES (20, 10089, 5000, 'hhhqeqwe', '2020-05-07 10:27:31', '2020-05-29 14:58:08', 1);
INSERT INTO `cms_club_join_apply` VALUES (21, 10089, 10000, 'cess', '2020-05-07 10:29:55', '2020-05-07 10:30:17', 1);
INSERT INTO `cms_club_join_apply` VALUES (22, 10090, 10000, 'w', '2020-05-07 14:58:03', '2020-05-09 13:43:40', 1);
INSERT INTO `cms_club_join_apply` VALUES (23, 10088, 10013, '单元测试', '2020-05-07 23:06:39', '2020-05-07 23:07:04', 1);
INSERT INTO `cms_club_join_apply` VALUES (24, 10089, 10015, '测试一下', '2020-05-08 10:48:50', '2020-05-08 10:49:08', 1);
INSERT INTO `cms_club_join_apply` VALUES (25, 10089, 10016, '测试一下', '2020-05-08 10:57:27', '2020-05-08 10:57:43', 1);
INSERT INTO `cms_club_join_apply` VALUES (26, 10094, 10000, '单元测试用，管理员通过', '2020-05-08 20:48:48', '2020-05-08 20:49:26', 1);
INSERT INTO `cms_club_join_apply` VALUES (33, 10088, 10014, '单元测试用，通过', '2020-05-08 21:17:12', '2020-05-08 21:17:36', 1);
INSERT INTO `cms_club_join_apply` VALUES (10000, 10089, 10013, '单元测试用，千万不要审核', '2020-05-08 21:00:41', NULL, 0);
INSERT INTO `cms_club_join_apply` VALUES (10016, 10088, 10021, '和你一起玩', '2020-05-09 16:59:00', '2020-05-09 17:00:06', 1);
INSERT INTO `cms_club_join_apply` VALUES (10017, 10088, 5000, '一起玩呀', '2020-05-09 17:12:36', '2020-05-29 14:58:11', 1);
INSERT INTO `cms_club_join_apply` VALUES (10018, 10090, 10021, '123', '2020-05-09 17:25:22', '2020-05-09 17:25:36', 1);
INSERT INTO `cms_club_join_apply` VALUES (10019, 10090, 10021, '123', '2020-05-09 17:27:17', '2020-05-09 17:27:25', 1);
INSERT INTO `cms_club_join_apply` VALUES (10020, 10094, 10000, '测试', '2020-05-09 20:54:13', '2020-05-09 22:20:05', 1);
INSERT INTO `cms_club_join_apply` VALUES (10021, 10089, 10014, '111222', '2020-05-09 21:46:38', NULL, 0);
INSERT INTO `cms_club_join_apply` VALUES (10022, 10095, 10000, '我爱代码', '2020-05-09 21:51:23', '2020-05-09 21:52:19', 1);
INSERT INTO `cms_club_join_apply` VALUES (10026, 10089, 10001, '1', '2020-05-09 22:23:32', NULL, 0);
INSERT INTO `cms_club_join_apply` VALUES (10033, 10090, 10025, 'hhhh', '2020-05-12 11:46:15', '2020-05-12 11:47:03', 1);
INSERT INTO `cms_club_join_apply` VALUES (10044, 10107, 10000, '无', '2020-05-20 13:50:39', '2020-05-20 13:51:22', 2);
INSERT INTO `cms_club_join_apply` VALUES (10045, 10107, 10000, '想加入', '2020-05-20 13:51:58', '2020-05-20 13:52:35', 1);
INSERT INTO `cms_club_join_apply` VALUES (10046, 10107, 10026, '加入', '2020-05-20 15:49:16', '2020-05-20 15:49:31', 1);
INSERT INTO `cms_club_join_apply` VALUES (10047, 10088, 10023, '想加入呀', '2020-05-26 14:07:29', NULL, 0);
INSERT INTO `cms_club_join_apply` VALUES (10048, 10088, 10019, '111', '2020-05-26 14:08:22', NULL, 0);
INSERT INTO `cms_club_join_apply` VALUES (10049, 10090, 10022, '666', '2020-05-26 19:56:34', NULL, 0);
INSERT INTO `cms_club_join_apply` VALUES (10050, 10090, 10015, '666', '2020-05-26 19:57:17', NULL, 0);
INSERT INTO `cms_club_join_apply` VALUES (10051, 10113, 5000, '666', '2020-05-26 20:21:27', '2020-05-26 20:35:15', 1);
INSERT INTO `cms_club_join_apply` VALUES (10052, 10090, 10014, '22', '2020-05-27 10:18:03', NULL, 0);
INSERT INTO `cms_club_join_apply` VALUES (10053, 10090, 10013, '33', '2020-05-27 10:26:55', NULL, 0);
INSERT INTO `cms_club_join_apply` VALUES (10054, 10090, 10000, '去问我去饿额额额额', '2020-05-27 10:27:59', '2020-05-29 15:13:55', 1);
INSERT INTO `cms_club_join_apply` VALUES (10055, 10091, 10000, '66', '2020-05-27 10:29:43', '2020-05-29 15:13:53', 1);
INSERT INTO `cms_club_join_apply` VALUES (10056, 10091, 10001, '去问驱蚊器为', '2020-05-27 10:30:03', NULL, 0);
INSERT INTO `cms_club_join_apply` VALUES (10057, 10090, 10019, '111', '2020-05-28 14:07:08', NULL, 0);
INSERT INTO `cms_club_join_apply` VALUES (10058, 10090, 10021, '222', '2020-05-28 14:10:45', NULL, 0);
INSERT INTO `cms_club_join_apply` VALUES (10059, 10113, 10016, '666', '2020-05-28 16:07:34', NULL, 0);
INSERT INTO `cms_club_join_apply` VALUES (10060, 10094, 5000, '测试', '2020-05-29 09:53:34', '2020-05-29 14:58:09', 1);
INSERT INTO `cms_club_join_apply` VALUES (10061, 10094, 10001, '测试', '2020-05-29 10:02:08', '2020-05-29 23:20:46', 1);
INSERT INTO `cms_club_join_apply` VALUES (10062, 10113, 10021, '6666', '2020-05-29 14:25:09', '2020-05-29 14:49:30', 2);
INSERT INTO `cms_club_join_apply` VALUES (10063, 10113, 10021, '6666', '2020-05-29 14:50:40', '2020-05-29 15:04:11', 1);
INSERT INTO `cms_club_join_apply` VALUES (10064, 10088, 10013, '11', '2020-05-29 14:57:30', NULL, 0);
INSERT INTO `cms_club_join_apply` VALUES (10065, 10088, 5000, '再加一下', '2020-05-29 15:05:03', '2020-05-29 15:05:30', 1);
INSERT INTO `cms_club_join_apply` VALUES (10066, 10090, 10023, '测试', '2020-05-29 15:19:39', NULL, 0);
INSERT INTO `cms_club_join_apply` VALUES (10067, 10088, 5000, '无', '2020-05-29 15:20:30', NULL, 0);
INSERT INTO `cms_club_join_apply` VALUES (10068, 10122, 10014, '666', '2020-05-30 16:34:55', NULL, 0);
INSERT INTO `cms_club_join_apply` VALUES (10069, 10122, 10000, '555', '2020-05-30 16:35:07', NULL, 0);

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
-- Table structure for cms_club_picture
-- ----------------------------
DROP TABLE IF EXISTS `cms_club_picture`;
CREATE TABLE `cms_club_picture`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `club_id` int(11) NULL DEFAULT NULL,
  `pic1_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pic2_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pic3_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pic4_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pic5_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_club_picture
-- ----------------------------
INSERT INTO `cms_club_picture` VALUES (1, 5000, 'http://101.200.193.180:9520/files/images/a372f0d7d1854f5fa161a0a6a254e092.jpg', 'http://101.200.193.180:9520/files/images/6d4f9ba2c5cf4b26a0679ec4d95fc3a0.png', NULL, NULL, NULL);
INSERT INTO `cms_club_picture` VALUES (2, 10000, 'http://101.200.193.180:9520/files/images/f871c8e11d2147efa79d0b1dea1e35cb.png', 'http://101.200.193.180:9520/files/images/6bcd0ee4fa674f7e9cf17771c30c3b4a.jpeg', 'http://101.200.193.180:9520/files/images/4a4a54a5ea35411a920257554740840a.jpg', 'http://101.200.193.180:9520/files/images/1ddf0e3b8b5f4aadaf5a54979cfa085e.jpg', 'http://101.200.193.180:9520/files/images/e62a367c6a9240a9aec72dde5fb7afbd.jpg');
INSERT INTO `cms_club_picture` VALUES (3, 10001, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cms_club_picture` VALUES (4, 10003, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cms_club_picture` VALUES (5, 10011, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cms_club_picture` VALUES (6, 10012, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cms_club_picture` VALUES (7, 10013, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cms_club_picture` VALUES (8, 10014, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cms_club_picture` VALUES (9, 10015, 'http://101.200.193.180:9520/files/images/2bb0f8c3c16e4b958c2da0502129beb1.jpg', 'http://101.200.193.180:9520/files/images/b608bb83a1eb4703811e2f2bbda933da.jpg', NULL, NULL, NULL);
INSERT INTO `cms_club_picture` VALUES (10, 10016, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cms_club_picture` VALUES (11, 10017, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cms_club_picture` VALUES (12, 10018, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cms_club_picture` VALUES (13, 10019, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cms_club_picture` VALUES (14, 10020, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cms_club_picture` VALUES (15, 10021, 'http://101.200.193.180:9520/files/images/e931825524a248deb6f17adaa219f073.jpg', 'http://101.200.193.180:9520/files/images/6c8cba7b034c4fdfa41a3db2147473ee.jpg', 'http://101.200.193.180:9520/files/images/9a592e7495e447cf915cafb5a7236739.png', 'http://101.200.193.180:9520/files/images/0f99559988fc45d7af4900e32be7ecd0.jpg', 'http://101.200.193.180:9520/files/images/37b2aba36a3142438872ee7e32de3b81.png');
INSERT INTO `cms_club_picture` VALUES (16, 10022, 'http://101.200.193.180:9520/files/images/6218f8a433374d2bb797b95873ae6d02.png', 'http://101.200.193.180:9520/files/images/81dcbce4221a456aa6efc1149e2266a7.png', 'http://101.200.193.180:9520/files/images/aa2c675d976741fb84c504a8d704210a.jpg', 'http://101.200.193.180:9520/files/images/16a894d571d243a19d8a1f9c73a48e4b.jpg', 'http://101.200.193.180:9520/files/images/a8677eb385d44edb9ab83129ff6b8569.jpg');
INSERT INTO `cms_club_picture` VALUES (17, 10023, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cms_club_picture` VALUES (18, 10024, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cms_club_picture` VALUES (19, 10025, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cms_club_picture` VALUES (20, 10026, 'http://101.200.193.180:9520/files/images/a53015d72776430691d192ff650f8957.jpg', 'http://101.200.193.180:9520/files/images/cde72dad4e6c4b739ec0b7be31f328cc.jpg', 'http://101.200.193.180:9520/files/images/51971c9f7d114beabb54557013c4e0ed.jpg', 'http://101.200.193.180:9520/files/images/abb73ca497b4456bb24737eb951dad3a.png', 'http://101.200.193.180:9520/files/images/e3e9056a9afe481791b602e650470211.jpg');
INSERT INTO `cms_club_picture` VALUES (21, 10027, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cms_club_picture` VALUES (22, 10028, NULL, NULL, NULL, NULL, NULL);

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
  `lower_limit` int(11) NOT NULL DEFAULT 0 COMMENT '积分下限',
  `upper_limit` int(11) NOT NULL DEFAULT 0 COMMENT '积分上限',
  `description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '成员头衔' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_member_honor
-- ----------------------------
INSERT INTO `cms_member_honor` VALUES (1, '潜水', 0, 99, '0<=活跃度<100');
INSERT INTO `cms_member_honor` VALUES (2, '冒泡', 100, 249, '100<=活跃度<250');
INSERT INTO `cms_member_honor` VALUES (3, '吐槽', 250, 399, '250<=活跃度<400');
INSERT INTO `cms_member_honor` VALUES (4, '活跃', 400, 599, '400<=活跃度<600');
INSERT INTO `cms_member_honor` VALUES (5, '话痨', 600, 849, '600<=活跃度<850');
INSERT INTO `cms_member_honor` VALUES (6, '传说', 850, 1149, '850<=活跃度');

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
) ENGINE = InnoDB AUTO_INCREMENT = 10040 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '社团认证申请' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_official_change_apply
-- ----------------------------
INSERT INTO `cms_official_change_apply` VALUES (2, 10013, '也没为什么就是认证一下牛逼一点', '2020-04-30 18:43:56', '2020-05-01 15:22:02', 2);
INSERT INTO `cms_official_change_apply` VALUES (3, 10013, '7', '2020-05-01 15:46:11', '2020-05-01 18:55:33', 2);
INSERT INTO `cms_official_change_apply` VALUES (5, 10013, '6', '2020-05-02 08:38:31', '2020-05-04 18:31:59', 2);
INSERT INTO `cms_official_change_apply` VALUES (6, 10013, '7', '2020-05-05 10:23:07', '2020-05-05 10:31:05', 2);
INSERT INTO `cms_official_change_apply` VALUES (7, 10013, '7', '2020-05-05 10:31:49', '2020-05-05 10:34:11', 2);
INSERT INTO `cms_official_change_apply` VALUES (8, 10013, '6', '2020-05-05 10:34:44', '2020-05-05 10:39:26', 2);
INSERT INTO `cms_official_change_apply` VALUES (9, 10013, '7', '2020-05-05 10:40:04', '2020-05-09 16:47:25', 1);
INSERT INTO `cms_official_change_apply` VALUES (11, 10015, '没有原因', '2020-05-08 16:47:40', NULL, 0);
INSERT INTO `cms_official_change_apply` VALUES (10000, 10013, '单元测试，千万别审核', '2020-05-07 23:07:42', NULL, 0);
INSERT INTO `cms_official_change_apply` VALUES (10001, 10014, '单元测试', '2020-05-07 23:07:42', '2020-05-07 23:11:23', 2);
INSERT INTO `cms_official_change_apply` VALUES (10016, 10021, '本社团比较优秀', '2020-05-09 20:43:11', '2020-05-09 20:44:47', 1);
INSERT INTO `cms_official_change_apply` VALUES (10017, 10022, 'test9', '2020-05-09 21:06:59', NULL, 0);
INSERT INTO `cms_official_change_apply` VALUES (10018, 10023, 'test', '2020-05-09 21:10:45', '2020-05-09 21:14:02', 2);
INSERT INTO `cms_official_change_apply` VALUES (10019, 10014, 'test', '2020-05-09 21:11:57', '2020-05-09 21:14:02', 2);
INSERT INTO `cms_official_change_apply` VALUES (10020, 10023, 'test', '2020-05-09 21:14:33', '2020-05-29 20:57:01', 2);
INSERT INTO `cms_official_change_apply` VALUES (10021, 10014, 'test', '2020-05-09 21:15:29', '2020-05-09 21:15:29', 2);
INSERT INTO `cms_official_change_apply` VALUES (10025, 10025, 'test', '2020-05-12 11:47:48', '2020-05-12 11:49:51', 1);
INSERT INTO `cms_official_change_apply` VALUES (10036, 10027, '认证不', '2020-05-29 15:35:20', NULL, 0);
INSERT INTO `cms_official_change_apply` VALUES (10037, 10023, '测试', '2020-05-29 20:57:50', '2020-05-29 20:58:58', 2);
INSERT INTO `cms_official_change_apply` VALUES (10038, 10023, '测试', '2020-05-29 20:59:23', '2020-05-29 21:02:54', 2);
INSERT INTO `cms_official_change_apply` VALUES (10039, 10023, '5', '2020-05-29 21:03:15', '2020-05-29 21:18:21', 1);

-- ----------------------------
-- Table structure for cms_quit_notice
-- ----------------------------
DROP TABLE IF EXISTS `cms_quit_notice`;
CREATE TABLE `cms_quit_notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `club_id` int(11) NULL DEFAULT NULL,
  `quit_date` date NULL DEFAULT NULL COMMENT '退社日期',
  `reason` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '原因',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `club_id`(`club_id`) USING BTREE,
  CONSTRAINT `cms_quit_notice_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cms_quit_notice_ibfk_2` FOREIGN KEY (`club_id`) REFERENCES `cms_club` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '社员退出通知' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_quit_notice
-- ----------------------------
INSERT INTO `cms_quit_notice` VALUES (6, 10089, 10000, '2020-05-07', '测试');
INSERT INTO `cms_quit_notice` VALUES (7, 10088, 10013, '2020-05-07', '增加记录1');
INSERT INTO `cms_quit_notice` VALUES (8, 10089, 10013, '2020-05-07', '增加记录2');
INSERT INTO `cms_quit_notice` VALUES (9, 10090, 10013, '2020-05-07', '增加记录3');
INSERT INTO `cms_quit_notice` VALUES (25, 10090, 10021, '2020-05-09', '123');
INSERT INTO `cms_quit_notice` VALUES (26, 10090, 10021, '2020-05-09', '测试');
INSERT INTO `cms_quit_notice` VALUES (27, 10094, 10000, '2020-05-09', '测试M09');
INSERT INTO `cms_quit_notice` VALUES (45, 10090, 10000, '2020-05-27', '31321');
INSERT INTO `cms_quit_notice` VALUES (46, 10088, 10013, '2020-05-29', '没有理由');
INSERT INTO `cms_quit_notice` VALUES (47, 10088, 5000, '2020-05-29', '偷偷试一下能不能退社');
INSERT INTO `cms_quit_notice` VALUES (48, 10088, 5000, '2020-05-29', '测试');
INSERT INTO `cms_quit_notice` VALUES (49, 10113, 10021, '2020-05-29', '5555');

-- ----------------------------
-- Table structure for cms_user_club_rel
-- ----------------------------
DROP TABLE IF EXISTS `cms_user_club_rel`;
CREATE TABLE `cms_user_club_rel`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `club_id` int(11) NULL DEFAULT NULL,
  `role_id` int(11) NOT NULL DEFAULT 2 COMMENT '角色id(默认为2社员)',
  `credit` int(11) NULL DEFAULT 0 COMMENT '积分',
  `honor_id` int(11) NULL DEFAULT 1 COMMENT '用户头衔 id',
  `check_in_date` datetime(0) NULL DEFAULT NULL COMMENT '上次签到时间',
  `join_date` datetime(0) NULL DEFAULT NULL COMMENT '加入时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `club_id`(`club_id`) USING BTREE,
  INDEX `honor_id`(`honor_id`) USING BTREE,
  INDEX `cms_user_club_rel_ibfk_3`(`role_id`) USING BTREE,
  CONSTRAINT `cms_user_club_rel_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cms_user_club_rel_ibfk_2` FOREIGN KEY (`club_id`) REFERENCES `cms_club` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cms_user_club_rel_ibfk_3` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cms_user_club_rel_ibfk_4` FOREIGN KEY (`honor_id`) REFERENCES `cms_member_honor` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 73 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户社团关系' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_user_club_rel
-- ----------------------------
INSERT INTO `cms_user_club_rel` VALUES (1, 10088, 10000, 3, 113, 2, '2020-05-29 14:18:42', '2020-04-28 14:56:26');
INSERT INTO `cms_user_club_rel` VALUES (4, 10090, 5000, 3, 9, 1, '2020-05-28 14:18:08', '2020-04-24 14:54:49');
INSERT INTO `cms_user_club_rel` VALUES (19, 10094, 10013, 3, 0, 1, NULL, '2020-04-28 19:53:37');
INSERT INTO `cms_user_club_rel` VALUES (22, 10094, 10014, 3, 0, 1, NULL, '2020-05-01 15:00:13');
INSERT INTO `cms_user_club_rel` VALUES (23, 10088, 10015, 3, 3, 1, '2020-05-27 17:16:08', '2020-05-01 15:18:56');
INSERT INTO `cms_user_club_rel` VALUES (24, 10088, 10016, 2, 2, 1, '2020-05-29 15:04:39', '2020-05-01 18:04:03');
INSERT INTO `cms_user_club_rel` VALUES (31, 10095, 5000, 2, 0, 1, NULL, '2020-05-01 00:00:00');
INSERT INTO `cms_user_club_rel` VALUES (34, 10095, 10019, 3, 0, 1, NULL, '2020-05-05 00:13:48');
INSERT INTO `cms_user_club_rel` VALUES (35, 10097, 10020, 3, 0, 1, NULL, '2020-05-05 00:50:05');
INSERT INTO `cms_user_club_rel` VALUES (39, 10089, 10000, 2, 2, 1, NULL, '2020-05-07 10:30:17');
INSERT INTO `cms_user_club_rel` VALUES (41, 10089, 10015, 2, 0, 1, NULL, '2020-05-08 10:49:08');
INSERT INTO `cms_user_club_rel` VALUES (42, 10089, 10016, 3, 0, 1, NULL, '2020-05-08 10:57:43');
INSERT INTO `cms_user_club_rel` VALUES (44, 10088, 10014, 2, 1, 1, '2020-05-29 14:42:43', '2020-05-08 21:17:36');
INSERT INTO `cms_user_club_rel` VALUES (46, 10089, 10021, 3, 4, 1, '2020-05-30 16:38:42', '2020-05-09 16:57:32');
INSERT INTO `cms_user_club_rel` VALUES (47, 10088, 10021, 2, 3, 1, '2020-05-30 16:30:54', '2020-05-09 17:00:06');
INSERT INTO `cms_user_club_rel` VALUES (48, 10088, 10022, 3, 0, 1, NULL, '2020-05-09 17:20:31');
INSERT INTO `cms_user_club_rel` VALUES (51, 10094, 10023, 3, 0, 1, NULL, '2020-05-09 20:46:23');
INSERT INTO `cms_user_club_rel` VALUES (53, 10095, 10000, 2, 0, 1, NULL, '2020-05-09 21:52:19');
INSERT INTO `cms_user_club_rel` VALUES (54, 10094, 10000, 2, 5, 1, '2020-05-29 10:08:24', '2020-05-09 22:20:05');
INSERT INTO `cms_user_club_rel` VALUES (55, 10088, 10025, 2, 2, 1, '2020-05-26 15:51:40', '2020-05-12 11:45:15');
INSERT INTO `cms_user_club_rel` VALUES (56, 10090, 10025, 3, 0, 1, NULL, '2020-05-12 11:47:03');
INSERT INTO `cms_user_club_rel` VALUES (57, 10088, 10026, 2, 2, 1, '2020-05-30 16:32:04', '2020-05-19 17:49:04');
INSERT INTO `cms_user_club_rel` VALUES (58, 10107, 10000, 2, 1, 1, '2020-05-29 23:01:25', '2020-05-20 13:52:35');
INSERT INTO `cms_user_club_rel` VALUES (59, 10107, 10026, 3, 4, 1, '2020-05-29 16:54:12', '2020-05-20 15:49:31');
INSERT INTO `cms_user_club_rel` VALUES (60, 10113, 5000, 2, 7, 1, '2020-05-30 13:48:57', '2020-05-26 20:35:15');
INSERT INTO `cms_user_club_rel` VALUES (61, 10088, 10027, 3, 0, 1, NULL, '2020-05-28 16:31:01');
INSERT INTO `cms_user_club_rel` VALUES (62, 10089, 5000, 2, 0, 1, NULL, '2020-05-29 14:58:08');
INSERT INTO `cms_user_club_rel` VALUES (63, 10094, 5000, 2, 0, 1, NULL, '2020-05-29 14:58:09');
INSERT INTO `cms_user_club_rel` VALUES (67, 10091, 10000, 2, 0, 1, NULL, '2020-05-29 15:13:53');
INSERT INTO `cms_user_club_rel` VALUES (68, 10090, 10000, 2, 5, 1, '2020-05-29 15:55:19', '2020-05-29 15:13:55');
INSERT INTO `cms_user_club_rel` VALUES (69, 10088, 10001, 3, 2, 1, '2020-05-30 16:31:49', '2020-05-27 23:19:16');
INSERT INTO `cms_user_club_rel` VALUES (70, 10094, 10001, 2, 0, 1, NULL, '2020-05-29 23:20:46');
INSERT INTO `cms_user_club_rel` VALUES (71, 10088, 10001, 2, 0, 1, NULL, '2020-05-29 23:20:48');
INSERT INTO `cms_user_club_rel` VALUES (72, 10122, 10028, 3, 0, 1, NULL, '2020-05-30 16:43:59');

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
-- Table structure for fms_post
-- ----------------------------
DROP TABLE IF EXISTS `fms_post`;
CREATE TABLE `fms_post`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `poster_id` int(11) NULL DEFAULT NULL COMMENT '发帖人 id，可以是用户或者社团',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片链接',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '发帖时间',
  `type` int(1) NULL DEFAULT NULL COMMENT '帖子类别：0 -> 个人帖；1 -> 社团活动帖',
  `like_count` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '点赞数量',
  `delete_state` int(1) NULL DEFAULT 0 COMMENT '删除状态：0 -> 未删除；1 -> 已删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fms_post_ibfk_1`(`poster_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 94 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fms_post
-- ----------------------------
INSERT INTO `fms_post` VALUES (1, 10088, 'LINUX PID 1 和 SYSTEMD', '要说清 Systemd，得先从 Linux 操作系统的启动说起。Linux 操作系统的启动首先从 BIOS 开始，然后由 Boot Loader 载入内核，并初始化内核。内核初始化的最后一步就是启动 init 进程。这个进程是系统的第一个进程，PID 为 1，又叫超级进程，也叫根进程。它负责产生其他所有用户进程。所有的进程都会被挂在这个进程下，如果这个进程退出了，那么所有的进程都被 kill 。如果一个子进程的父进程退了，那么这个子进程会被挂到 PID 1 下面。（注：PID 0 是内核的一部分，主要用于内进换页，参看：Process identifier）\r\n\r\nSysV Init\r\nPID 1 这个进程非常特殊，其主要就任务是把整个操作系统带入可操作的状态。比如：启动 UI – Shell 以便进行人机交互，或者进入 X 图形窗口。传统上，PID 1 和传统的 Unix System V 相兼容的，所以也叫 sysvinit，这是使用得最悠久的 init 实现。Unix System V 于 1983 年 release。\r\n\r\n在 sysvint 下，有好几个运行模式，又叫 runlevel。比如：常见的 3 级别指定启动到多用户的字符命令行界面，5 级别指定启起到图形界面，0 表示关机，6 表示重启。其配置在 /etc/inittab 文件中。', 'https://coolshell.cn/wp-content/uploads/2017/07/systemd.jpeg', '2020-04-29 14:05:15', 0, 0, 1);
INSERT INTO `fms_post` VALUES (2, 5000, '创意志愿者活动', '志愿者活动是大学必需的活动形式，对于此种活动意义的重要性远大于创意，但是精益求精也可以寻找一些创意点来充斥到活动中，比如部分社团，比如心理学社团可以表演心理剧甚至适合社区小孩看的心理教育小剧场，和动漫社、话剧社合作。', 'https://cdn7-static.tshe.com/uploads/images/TopicImage/1572858659442timg%20(1).jpg', '2020-04-29 14:06:47', 1, 1, 0);
INSERT INTO `fms_post` VALUES (3, 5000, '创意绿色环保活动', '现在大学里都有什么地球 1 小时断电这种小活动，但不妨将环保活动做大做好，比如可以与绘画大赛相结合，办一个手绘环保袋、制作环保艺术品等活动，也可以和服装设计或是环艺室内设计比赛相结合，环保品服装大赛 or 废物利用美化环境大赛等。\r\n\r\n \r\n\r\n大学社团活动 T 恤定制，就来 T 社定制。', 'https://www.tshe.com/items/67', '2020-04-29 14:06:47', 1, 1, 1);
INSERT INTO `fms_post` VALUES (4, 5000, '活动2', '这是一个社团的活动2', 'https://images.unsplash.com/photo-1556035511-3168381ea4d4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=967&q=80', '2020-04-29 14:16:47', 1, 2, 0);
INSERT INTO `fms_post` VALUES (5, 5000, '活动3', '这是一个社团的活动3', 'https://images.unsplash.com/photo-1556035511-3168381ea4d4?ixlib=rb-https://images.unsplash.com/photo-1581080972419-6ded4f6889d0?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60', '2020-04-29 13:06:47', 1, 0, 0);
INSERT INTO `fms_post` VALUES (6, 10000, '活动4', '这是一个社团的活动4', 'https://images.unsplash.com/photo-1556035511-3168381ea4d4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=967&q=80', '2020-04-29 15:06:47', 1, 1, 0);
INSERT INTO `fms_post` VALUES (7, 10000, '活动xx', '这是一个社团的活动xx', 'https://images.unsplash.com/photo-1556035511-3168381ea4d4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=967&q=80', '2020-04-29 14:33:47', 1, 0, 1);
INSERT INTO `fms_post` VALUES (8, 5000, '活动1xx', '这是一个社团的活动1xx', 'https://images.unsplash.com/photo-1558317751-bc3ed6f85f72?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60', '2020-04-29 14:56:47', 1, 0, 1);
INSERT INTO `fms_post` VALUES (9, 10089, '我看绩效考核', '（本来，这篇文章应该在 5 月份完成，我拖延症让我今天才完成）\r\n\r\n前些天，有几个网友找我谈绩效考核的事，都是在绩效上被差评的朋友。在大致了解情况后，我发现他们感到沮丧和郁闷的原因，不全是自己没有做好事情，他们对于自己没有做好公司交给的事，一方面，持一些疑义，因为我很明显地感到他们和公司对一件是否做好的标准定义有误差，另一方面，他们对于自己的工作上的问题也承认。不过，让他们更多感到沮丧的原因则是，公司、经理或 HR 和他们的谈话，让他们感觉整个人都被完全否定了，甚至有一种被批斗的感觉。这个感觉实在是太糟糕了。\r\n\r\n因为我也有相似的经历，所以，我想在这里写下一篇文章，谈谈自己的对一些绩效考核的感受。先放出我的两个观点：\r\n\r\n1）制定目标和绩效，目的不是用来考核人的，而用来改善提高组织和人员业绩和效率的。\r\n\r\n2）人是复杂的，人是有状态波动的，任何时候都不应该轻易否定人，绩效考核应该考核的是事情，而不是人。\r\n\r\n我个人比较坚持的认为 —— 绩效分应该打给项目，打给产品，打给部门，打给代码，而不是打给人。然而现在的管理体制基本上都是打给人，而很多根本不擅长管理的经理和 HR 以及很多不会独立思考的吃瓜群众基本上都会把矛头指向个人，所以，当然会有开批斗会的感觉。', 'https://coolshell.cn/wp-content/uploads/2017/07/performance_review-360x200.jpg', '2020-05-01 00:40:37', 0, 1, 0);
INSERT INTO `fms_post` VALUES (10, 10088, 'GO 语言的修饰器编程', '之前写过一篇《Python 修饰器的函数式编程》，这种模式很容易的可以把一些函数装配到另外一些函数上，可以让你的代码更为的简单，也可以让一些 “小功能型” 的代码复用性更高，让代码中的函数可以像乐高玩具那样自由地拼装。所以，一直以来，我对修饰器 decoration 这种编程模式情有独钟，这里写一篇 Go 语言相关的文章。\r\n\r\n看过 Python 修饰器那篇文章的同学，一定知道这是一种函数式编程的玩法 —— 用一个高阶函数来包装一下。多唠叨一句，关于函数式编程，可以参看我之前写过一篇文章《函数式编程》，这篇文章主要是，想通过从过程式编程的思维方式过渡到函数式编程的思维方式，从而带动更多的人玩函数式编程，所以，如果你想了解一下函数式编程，那么可以移步先阅读一下。所以，Go 语言的修饰器编程模式，其实也就是函数式编程的模式。\r\n\r\n不过，要提醒注意的是，Go 语言的 “糖” 不多，而且又是强类型的静态无虚拟机的语言，所以，无法做到像 Java 和 Python 那样的优雅的修饰器的代码。当然，也许是我才才疏学浅，如果你知道有更多的写法，请你一定告诉我。先谢过了。', 'https://coolshell.cn/wp-content/uploads/2017/06/go-hardhat.png', '2020-05-01 00:41:11', 0, 0, 1);
INSERT INTO `fms_post` VALUES (12, 10088, '如何重构 “箭头型” 代码', '本文主要起因是，一次在微博上和朋友关于嵌套好几层的 if-else 语句的代码重构的讨论（微博原文），在微博上大家有各式各样的问题和想法。按道理来说这些都是编程的基本功，似乎不太值得写一篇文章，不过我觉得很多东西可以从一个简单的东西出发，到达本质，所以，我觉得有必要在这里写一篇的文章。不一定全对，只希望得到更多的讨论，因为有了更深入的讨论才能进步。\r\n\r\n文章有点长，我在文章最后会给出相关的思考和总结陈词，你可以跳到结尾。\r\n\r\n所谓箭头型代码，基本上来说就是下面这个图片所示的情况。\r\n\r\n\r\n\r\n那么，这样 “箭头型” 的代码有什么问题呢？看上去也挺好看的，有对称美。但是……\r\n\r\n关于箭头型代码的问题有如下几个：', 'https://coolshell.cn/wp-content/uploads/2017/04/IMG_7411.jpg', '2020-05-01 13:00:00', 0, 0, 1);
INSERT INTO `fms_post` VALUES (13, 10088, 'AWS 的 S3 故障回顾和思考', '继 Gitlab 的误删除数据事件没几天，“不沉航母” AWS S3 （Simple Storage Service）几天前也 “沉” 了 4 个小时，墙外的半个互联网也跟着挂了。如约，按 AWS 惯例，AWS 今天给出了一个简单的故障报告《Summary of the Amazon S3 Service Disruption in the Northern Virginia (US-EAST-1) Region》。这个故障和简单来说和 Gitlab 一样，也是人员误操作。先简单的说一下这份报中说了什么。\r\n\r\n故障原因\r\n简单来说，这天，有一个 AWS 工程师在调查 Northern Virginia (US-EAST-1) Region 上 S3 的一个和账务系统相关的问题，这个问题是 S3 的账务系统变慢了（我估计这个故障在 Amazon 里可能是 Sev2 级，Sev2 级的故障在 Amazon 算是比较大的故障，需要很快解决），Oncall 的开发工程师（注：Amazon 的运维都是由开发工程师来干的，所以 Amazon 内部嬉称 SDE-Software Developer Engineer 为 Someone Do Everything）想移除一个账务系统里的一个子系统下的一些少量的服务器（估计这些服务器上有问题，所以想移掉后重新部署），结果呢，有一条命令搞错了，导致了移除了大量的 S3 的控制系统。包括两个很重要的子系统：', 'https://coolshell.cn/wp-content/uploads/2017/03/Amazon-Web-Services-Down-494x270.png', '2020-05-01 13:02:27', 0, 0, 1);
INSERT INTO `fms_post` VALUES (14, 10089, '从 GITLAB 误删除数据库想到的', '昨天，Gitlab.com 发生了一个大事，某同学误删了数据库，这个事看似是个低级错误，不过，因为 Gitlab 把整个过程的细节都全部暴露出来了，所以，可以看到很多东西，而对于类似这样的事情，我自己以前也干过，而在最近的两公司中我也见过（Amazon 中见过一次，阿里中见过至少四次），正好通过这个事来说说一下自己的一些感想和观点吧。我先放个观点：你觉得有备份系统就不会丢数据了吗？\r\n\r\n事件回顾\r\n整个事件的回顾 Gitlab.com 在第一时间就放到了 Google Doc 上，事后，又发了一篇 Blog 来说明这个事，在这里，我简单的回顾一下这个事件的过程。\r\n\r\n首先，一个叫 YP 的同学在给 gitlab 的线上数据库做一些负载均衡的工作，在做这个工作时的时候突发了一个情况，Gitlab 被 DDoS 攻击，数据库的使用飙高，在 block 完攻击者的 IP 后，发现有个 staging 的数据库 (db2.staging) 已经落后生产库 4GB 的数据，于是 YP 同学在 Fix 这个 staging 库的同步问题的时候，发现 db2.staging 有各种问题都和主库无法同步，在这个时候，YP 同学已经工作的很晚了，在尝试过多个方法后，发现 db2.staging 都 hang 在那里，无法同步，于是他想把 db2.staging 的数据库删除了，这样全新启动一个新的复制，结果呢，删除数据库的命令错误的敲在了生产环境上（db1.cluster），结果导致整个生产数据库被误删除。（陈皓注：这个失败基本上就是 “工作时间过长” + “在多数终端窗口中切换中迷失掉了”）', 'https://coolshell.cn/wp-content/uploads/2017/02/gitlab-600-377x270.jpg', '2020-05-01 21:08:17', 0, 1, 0);
INSERT INTO `fms_post` VALUES (15, 10088, 'CHROME 开发者工具的小技巧', 'Chrome 的开发者工具是个很强大的东西，相信程序员们都不会陌生，不过有些小功能可能并不为大众所知，所以，写下这篇文章罗列一下可能你所不知道的功能，有的功能可能会比较实用，有的则不一定，也欢迎大家补充交流。\r\n\r\n话不多话，我们开始。\r\n\r\n代码格式化\r\n有很多 css/js 的代码都会被 minify 掉，你可以点击代码窗口左下角的那个 { }  标签，chrome 会帮你给格式化掉。\r\n\r\n', 'https://coolshell.cn/wp-content/uploads/2017/01/pretty-code.gif', '2020-05-04 23:36:19', 0, 0, 1);
INSERT INTO `fms_post` VALUES (16, 10088, '从 MONGODB “赎金事件” 看安全问题', '今天上午（2017 年 1 月 7 日），我的微信群中同时出现了两个 MongoDB 被黑掉要赎金的情况，于是在调查过程中，发现了这个事件。这个事件应该是 2017 年开年的第一次比较大的安全事件吧，发现国内居然没有什么报道，国内安全圈也没有什么动静（当然，他们也许知道，只是不想说吧），Anyway，让我这个非安全领域的人来帮补补位。\r\n\r\n事件回顾\r\n这个事情应该是从 2017 年 1 月 3 日进入公众视野的，是由安全圈的大拿 Victor Gevers （网名：0xDUDE，GDI.foundation 的 Chairman），其实，他早在 2016 年 12 月 27 日就发现了一些在互联网上用户的 MongoDB 没有任何的保护措施，被攻击者把数据库删除了，并留下了一个叫 WARNING 的数据库，这张表的内容如下：\r\n\r\n{\r\n    \"_id\" : ObjectId(\"5859a0370b8e49f123fcc7da\"),\r\n    \"mail\" : \"harak1r1@sigaint.org\",\r\n    \"note\" : \"SEND 0.2 BTC TO THIS ADDRESS 13zaxGVjj9MNc2jyvDRhLyYpkCh323MsMq AND CONTACT THIS EMAIL WITH YOUR IP OF YOUR SERVER TO RECOVER YOUR DATABASE !\"\r\n}\r\n基本上如下所示：', 'https://coolshell.cn/wp-content/uploads/2017/01/MongoDB-360x200.jpg', '2019-04-01 09:55:38', 0, 0, 1);
INSERT INTO `fms_post` VALUES (17, 10088, '技术人员的发展之路', '2012 年的时候写过一篇叫《程序算法与人生选择》的文章，我用算法来类比如何做选择，说白了就是怎么去计算，但是并没有讲程序员可以发展的方向有哪些。 所以，就算是有这些所谓的方法论，我们可能对自己的发展还是会很纠结和无所事从，尤其是人到了 30 岁，这种彷徨和迷惑越来越重。虽然我之前也写过一篇《编程年龄和编程技能》的文章，但是还是有很多做技术的人对于自己能否在年纪大时还能去做技术感到没有信心。我猜测，这其中，最大的问题的是，目前从事技术工作的种种负面的经历（比如经常性的加班，被当成棋子或劳动力等等），让人完全看不到希望和前途，尤其是随着年纪越来越大，对未来的越来越没有信心。\r\n\r\n同时，也是因为在 GIAC 的大会被问到，程序员老了怎么办？而在年底这段时间，也和几个朋友在交流中不断地重复谈到个人发展的这个话题。我的人生过半，活到 “不惑” 的年纪，自然经常性的对什么事都会回头看看总结归纳，所以，在交谈过程中和交谈过后，自己也有一些思考想记录下来。因为我本人也是在这条路上的人，所以，谈不上给他人指导，我同样也是在瞎乱折腾同样每天在思考自己要去哪儿的 “一尘世间迷途老生”。况且，我的经历和眼界非常有限，因此，下面的这些关于个人发展的文字和思考必然是受我的眼界和经历所局限的。也欢迎大家补充和指正。\r\n\r\n这些东西不一定对，也不一定就是全部，期许可以让你在年底的时候有所思考，在明年的时候有所计划。\r\n\r\n一个重要阶段和标志\r\n在讲个人发展之前，我需要先说一下人生中的一个非常重要的阶段 ——20 到 30 岁！\r\n\r\n这个阶段的首要任务，就是提升自己学习能力和解决难题的能力。这是一个非常非常关键的时间段！这个时间段几乎决定着你的未来。', 'https://coolshell.cn/wp-content/uploads/2016/12/people-360x200.jpg', '2020-05-05 11:02:48', 0, 0, 1);
INSERT INTO `fms_post` VALUES (47, 10088, '天哪怎么这么开心', '加油，明天会更好！', 'https://imgsa.baidu.com/forum/w%3D580/sign=58c65c9c34f33a879e6d0012f65e1018/8afcc9c8a786c917f2c18d95c73d70cf3ac75716.jpg', '2020-05-06 13:06:16', 0, 1, 0);
INSERT INTO `fms_post` VALUES (48, 10089, '测试23', '扯完hi', '', '2020-05-06 14:15:26', 0, 0, 1);
INSERT INTO `fms_post` VALUES (49, 10000, 'welcome to act5', '周二，众议院议员 Moria Lucas（I-NC）提出了一项法案，该提案如果成为法律，将要求基金会释放那些在过去六个月内没有在公共场合爆发过违反北卡罗来纳州法律的特定异常效应的异常人类。可以理解的是，该提案引起了几个异常人民权利组织和报告机构的注意 —— 包括但不限于蛇之手和反基金会律师事务所 Leeward＆Warner 法律援助中心，以及信息泄露出版物 —— 他们很快发表了自己的意见，支持议员的提案。1。\n\n我是一个 E 类 II 级斯克兰顿型异常人类；简而言之，这意味着现实在我周围不能正常运作，而‘E 型’部分表明我无法控制这种效果何时或如何发生。随着 11 月的临近，每个政党的候选人都将他们对基金会的立场纳入竞选活动中，我觉得有必要解释一下为什么强迫基金会跟随一个外部政府代表团来进行收容是一个危险的想法。\n\n我 89% 的异常效应可以用特定类型的技术来对抗 —— 斯克兰顿现实稳定锚 —— 并使用一个名为休谟的指数来进行经验式追踪测量。（基金会和我自己都弄不明白我剩下 11% 的效应 —— 我二舅也弄不明白，他是我们家里唯一体验过这种效应的人。）\n\n只有这种设备的结果才能使我在任何一天都没有危险；我并不总是带有这些效应，如果基金会人员在他们第一次出现的那天没有出现，我和其他几个（甚至更多）人应该会死掉。当我在隔间里时（各有一个设备连接在镶嵌于墙壁、地板和天花板上的网格中），如果固定在一个完全围绕着我的阵列中的故障设备没有及时修好，那么我很有可能会直接跌落到固体地表以下几百米的地方，并在与基岩相同的物理空间中重新物化，或创造一个局部 “不太真实” 的空间异常，而非周围的房间，然后困在里面被饿死或者因为时间的推移被困在里面足够长的时间内部慢 400 倍，或遇到些其他危险问题。 （这两个例子都是真实的，分别发生在 2022 年和 2023 年；E 类现实扭曲者是异常人类中第二多的类型了，因此，对于那些声称对这些问题有所了解的政客来说，这一切都不应成为新闻。）', 'https://images.unsplash.com/photo-1556035511-3168381ea4d4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=967&q=80', '2020-05-06 14:59:01', 1, 0, 0);
INSERT INTO `fms_post` VALUES (50, 10000, 'API 设计原则 – QT 官网的设计实践总结', '原文链接：API Design Principles – Qt Wiki\r\n基于 Gary 的影响力上  Gary Gao 的译文稿：C++ 的 API 设计指导\r\n\r\n译序\r\n\r\n\r\nQt 的设计水准在业界很有口碑，一致、易于掌握和强大的 API 是 Qt 最著名的优点之一。此文既是 Qt 官网上的 API 设计指导准则，也是 Qt 在 API 设计上的实践总结。虽然 Qt 用的是 C++，但其中设计原则和思考是具有普适性的（如果你对 C++ 还不精通，可以忽略与 C++ 强相关或是过于细节的部分，仍然可以学习或梳理关于 API 设计最有价值的内容）。整个篇幅中有很多示例，是关于 API 设计一篇难得的好文章。\r\n\r\n需要注意的是，这篇 Wiki 有一些内容并不完整，所以，可能会有一些阅读上的问题，我们对此做了一些相关的注释。\r\n\r\nPS：翻译中肯定会有不足和不对之处，欢迎评论 & 交流；另译文源码在 GitHub 的这个仓库中，可以提交 Issue/Fork 后提交代码来建议 / 指正。\r\n\r\nAPI 设计原则\r\n一致、易于掌握和强大的 API 是 Qt 最著名的优点之一。此文总结了我们在设计 Qt 风格 API 的过程中所积累的诀窍（know-how）。其中许多是通用准则；而其他的则更偏向于约定，遵循这些约定主要是为了与已有的 API 保持一致。\r\n\r\n虽然这些准则主要用于对外的 API（public API），但在设计对内的 API（private API）时也推荐遵循相同的技巧（techniques），作为开发者之间协作的礼仪（courtesy）。', 'https://images.unsplash.com/photo-1556035511-3168381ea4d4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=967&q=80', '2020-05-07 10:15:06', 1, 2, 0);
INSERT INTO `fms_post` VALUES (51, 10088, '装逼', '今天在微博上看到了 有人分享了下面的这段函数式代码，我把代码贴到下面，不过我对原来的代码略有改动，对于函数式的版本，咋一看，的确令人非常费解，仔细看一下，你可能就晕掉了，似乎完全就是天书，看上去非常装逼，哈哈。不过，我感觉解析那段函数式的代码可能会一个比较有趣过程，而且，我以前写过一篇《函数式编程》的入门式的文章，正好可以用这个例子，再升华一下原来的那篇文章，顺便可以向大家更好的介绍很多基础知识，所以写下这篇文章。\r\n\r\n先看代码\r\n这个代码平淡无奇，就是从一个数组中找到一个数，O (n) 的算法，找不到就返回 null。\r\n\r\n下面是正常的 old-school 的方式。不用多说。\r\n\r\n// 正常的版本\r\nfunction find (x, y) {\r\n  for ( let i = 0; i < x.length; i++ ) {\r\n    if ( x[i] == y ) return i;\r\n  }\r\n  return null;\r\n}\r\nlet arr = [0,1,2,3,4,5]\r\nconsole.log(find(arr, 2))\r\nconsole.log(find(arr, 8))\r\n结果到了函数式成了下面这个样子（好像上面的那些代码在下面若影若现，不过又有点不太一样，为了消掉 if 语言，让其看上去更像一个表达式，动用了？号表达式）：\r\n\r\n// 函数式的版本\r\nconst find = ( f => f(f) ) ( f =>\r\n  (next => (x, y, i = 0) =>\r\n    ( i >= x.length) ?  null :\r\n      ( x[i] == y ) ? i :\r\n        next(x, y, i+1))((...args) =>\r\n          (f(f))(...args)))\r\nlet arr = [0,1,2,3,4,5]\r\nconsole.log(find(arr, 2))\r\nconsole.log(find(arr, 8))\r\n为了讲清这个代码，需要先补充一些知识。', 'https://coolshell.cn/wp-content/uploads/2016/10/drawing-recursive-397x270.jpg', '2020-05-08 00:38:21', 0, 1, 0);
INSERT INTO `fms_post` VALUES (52, 10088, '联调第三天', '今天要做收尾工作了', '', '2020-05-08 23:09:07', 0, 2, 0);
INSERT INTO `fms_post` VALUES (53, 10088, 'try', '入团英语答案', '', '2020-05-09 14:35:35', 0, 0, 1);
INSERT INTO `fms_post` VALUES (54, 10088, '大师傅', '阿斯蒂芬啊', '', '2020-05-09 14:36:25', 0, 0, 1);
INSERT INTO `fms_post` VALUES (55, 10021, '野炊吧', '一起去野炊', '', '2020-05-09 17:06:08', 1, 3, 0);
INSERT INTO `fms_post` VALUES (56, 10000, '测试活动模块', '测试活动模块', '', '2020-05-09 21:09:21', 1, 3, 0);
INSERT INTO `fms_post` VALUES (57, 5000, 'welcome to act6', 'this is amazing!', 'https://images.unsplash.com/photo-1556035511-3168381ea4d4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=967&q=80', '2020-05-09 21:20:49', 1, 2, 0);
INSERT INTO `fms_post` VALUES (58, 10088, '皓月当空', 'i want to upadte post', '', '2020-05-09 21:26:10', 0, 1, 0);
INSERT INTO `fms_post` VALUES (59, 10090, 'tests', 'dssdsds', 'dsddss', '2020-05-12 11:52:07', 0, 1, 0);
INSERT INTO `fms_post` VALUES (60, 10019, '测试', '测试用', '', '2020-05-19 11:15:57', 1, 2, 0);
INSERT INTO `fms_post` VALUES (61, 10088, 'test', '显示时间问题', '', '2020-05-19 17:56:55', 0, 2, 1);
INSERT INTO `fms_post` VALUES (62, 10107, '用户小王测试平台', '用户小王正在找八阿哥', 'wewew', '2020-05-19 23:17:30', 0, 2, 0);
INSERT INTO `fms_post` VALUES (63, 10088, '测试', '测试看看', '', '2020-05-25 13:38:53', 0, 2, 1);
INSERT INTO `fms_post` VALUES (64, 5000, '创意志愿者活动', '志愿者活动是大学必需的活动形式，对于此种活动意义的重要性远大于创意，但是精益求精也可以寻找一些创意点来充斥到活动中，比如部分社团，比如心理学社团可以表演心理剧甚至适合社区小孩看的心理教育小剧场，和动漫社、话剧社合作。', 'https://images.unsplash.com/photo-1556035511-3168381ea4d4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=967&q=80', '2020-05-25 19:33:12', 1, 2, 0);
INSERT INTO `fms_post` VALUES (67, 10088, 'beta 04', 'It\'s last day of programming', 'http://localhost:8888/files/images/193611ff710845c3bd48a0c1eadde6eb.jpg', '2020-05-27 15:50:07', 0, 0, 1);
INSERT INTO `fms_post` VALUES (68, 10088, 'beta test', 'Its last day of sprint', 'http://localhost:8888/files/images/59012e74fa064a9092045935a8bb85bf.jpg', '2020-05-27 16:26:37', 0, 0, 1);
INSERT INTO `fms_post` VALUES (69, 10089, 'title', 'contetn', 'http://101.200.193.180:9520/files/images/ce63bffdf3854a1f96f4d69b92bbd023.jpg', '2020-05-28 16:03:45', 0, 2, 0);
INSERT INTO `fms_post` VALUES (70, 10088, '123', '123', 'http://101.200.193.180:9520/files/images/7376315a04964744bc5c2b9a3f507a18.jpg', '2020-05-28 16:52:26', 0, 0, 1);
INSERT INTO `fms_post` VALUES (71, 10113, 'hello', '666', 'http://101.200.193.180:9520/files/images/1a37901917a74efebe5e303b9baba8e8.jpg', '2020-05-28 19:24:48', 0, 1, 0);
INSERT INTO `fms_post` VALUES (72, 10000, 'welcome to act5', 'this is amazing!', 'https://images.unsplash.com/photo-1556035511-3168381ea4d4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=967&q=80', '2020-05-29 15:01:39', 1, 1, 0);
INSERT INTO `fms_post` VALUES (73, 10094, 'cc', '123', 'http://101.200.193.180:9520/files/images/418d23cd45ab4d56a569f34492f2c12d.jpg', '2020-05-29 17:36:49', 0, 1, 0);
INSERT INTO `fms_post` VALUES (74, 10094, '999', '123456789', 'http://101.200.193.180:9520/files/images/27f3835f8a8e4760924e5e24be5b07e3.jpg', '2020-05-29 17:43:26', 0, 0, 1);
INSERT INTO `fms_post` VALUES (75, 10088, 'dasf ', 'adsf ', 'http://101.200.193.180:9520/files/images/d44f9c539b694f5e82c1f3baa12e7953.jpg', '2020-05-29 18:56:16', 0, 0, 1);
INSERT INTO `fms_post` VALUES (76, 10088, 'dasf ', 'dasf ', 'http://101.200.193.180:9520/files/images/f2fa9881ab85467685c692c0ffabefc7.jpg', '2020-05-29 18:57:43', 0, 0, 1);
INSERT INTO `fms_post` VALUES (77, 10094, '测试', '1523', 'http://101.200.193.180:9520/files/images/4d7c9178317f4821ba2fcb8502b81309.jpg', '2020-05-29 18:57:57', 0, 0, 0);
INSERT INTO `fms_post` VALUES (79, 10116, 'Hello World', 'Its last day of sprint', NULL, '2020-05-29 19:52:35', 0, 0, 0);
INSERT INTO `fms_post` VALUES (80, 10088, '123', '123', NULL, '2020-05-30 15:53:03', 0, 0, 1);
INSERT INTO `fms_post` VALUES (81, 10088, '1234', '1234', NULL, '2020-05-30 15:53:32', 0, 0, 1);
INSERT INTO `fms_post` VALUES (82, 10000, 'zzz', '23', 'http://101.200.193.180:9520/files/images/4b9caad439ff40d8bd8e6ee192c18738.jpg', '2020-05-30 15:56:43', 1, 0, 0);
INSERT INTO `fms_post` VALUES (83, 10088, '大师傅', '大师傅', NULL, '2020-05-30 15:56:49', 0, 0, 1);
INSERT INTO `fms_post` VALUES (84, 10088, '213', '123', NULL, '2020-05-30 15:58:09', 0, 0, 1);
INSERT INTO `fms_post` VALUES (85, 10088, '213', '231', NULL, '2020-05-30 15:58:47', 0, 0, 1);
INSERT INTO `fms_post` VALUES (86, 10000, 'ki上校', 'ku', 'http://101.200.193.180:9520/files/images/1ce48ce5432146119817de585f237548.png', '2020-05-30 16:00:05', 1, 0, 0);
INSERT INTO `fms_post` VALUES (87, 10088, '大师傅', '大师傅', 'http://101.200.193.180:9520/files/images/b79e466d1ca74bc7bfbb01fe36a03bcd.jpg', '2020-05-30 16:00:15', 0, 0, 1);
INSERT INTO `fms_post` VALUES (88, 10088, '12345', '123', 'http://101.200.193.180:9520/files/images/9943fac80c9740baafedfa0c24e06ee0.jpg', '2020-05-30 16:04:27', 0, 0, 0);
INSERT INTO `fms_post` VALUES (89, 10088, '大师傅', '大师傅', 'http://101.200.193.180:9520/files/images/698ff9a24b8148dc81bb72816ba027c7.jpg', '2020-05-30 16:05:02', 0, 0, 1);
INSERT INTO `fms_post` VALUES (90, 10088, 'xz', 'x\'z', 'http://101.200.193.180:9520/files/images/7e4d8ce2f5174ea8a1a5037454f94014.jpg', '2020-05-30 16:06:17', 0, 0, 0);
INSERT INTO `fms_post` VALUES (91, 10088, '大师傅', '大师傅', 'http://101.200.193.180:9520/files/images/4954fd50c7a347a6a089fd61966ea532.jpg', '2020-05-30 16:10:19', 0, 0, 1);
INSERT INTO `fms_post` VALUES (92, 10088, '大师傅安抚', '大师傅安抚', 'http://101.200.193.180:9520/files/images/e192f8bf0dbe472986a281dc1edbfacf.jpg', '2020-05-30 16:12:59', 0, 0, 1);
INSERT INTO `fms_post` VALUES (93, 10088, '撒地方', '安抚', NULL, '2020-05-30 16:14:09', 0, 0, 1);

-- ----------------------------
-- Table structure for fms_post_remark
-- ----------------------------
DROP TABLE IF EXISTS `fms_post_remark`;
CREATE TABLE `fms_post_remark`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) NULL DEFAULT NULL COMMENT '帖子 id 或者活动 id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户 id',
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '内容',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '发表时间',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `activity_id`(`post_id`) USING BTREE,
  CONSTRAINT `fms_post_remark_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 280 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '活动评论关系' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of fms_post_remark
-- ----------------------------
INSERT INTO `fms_post_remark` VALUES (1, 1, 10088, 'this is remark 1', '2020-04-26 09:37:43', '2020-04-26 09:33:48');
INSERT INTO `fms_post_remark` VALUES (2, 1, 10088, 'this is remark 2', '2020-04-26 10:33:43', '2020-04-26 09:33:48');
INSERT INTO `fms_post_remark` VALUES (3, 1, 10088, 'this is remark 3', '2020-04-26 11:33:43', '2020-04-26 09:33:48');
INSERT INTO `fms_post_remark` VALUES (4, 2, 10088, 'this is remark 4', '2020-04-26 12:33:43', '2020-04-26 09:33:48');
INSERT INTO `fms_post_remark` VALUES (5, 1, 10089, 'this is remark 5', '2020-04-26 13:33:43', '2020-04-26 09:33:48');
INSERT INTO `fms_post_remark` VALUES (6, 2, 10088, 'this is remark 6', '2020-04-26 14:33:43', '2020-04-26 09:33:48');
INSERT INTO `fms_post_remark` VALUES (7, 3, 10089, 'this is remark 7', '2020-04-26 15:33:43', '2020-04-26 09:33:48');
INSERT INTO `fms_post_remark` VALUES (8, 1, 10088, 'very good', '2020-05-01 13:47:31', NULL);
INSERT INTO `fms_post_remark` VALUES (9, 5, 10088, '我测试一下', '2020-05-01 17:56:35', NULL);
INSERT INTO `fms_post_remark` VALUES (10, 5, 10088, '我测试一下', '2020-05-01 17:56:54', NULL);
INSERT INTO `fms_post_remark` VALUES (11, 5, 10088, '123', '2020-05-01 17:58:26', NULL);
INSERT INTO `fms_post_remark` VALUES (12, 4, 10088, '3', '2020-05-01 18:00:33', NULL);
INSERT INTO `fms_post_remark` VALUES (13, 4, 10088, '额', '2020-05-01 18:00:53', NULL);
INSERT INTO `fms_post_remark` VALUES (14, 1, 10089, 'day09 test', '2020-05-03 18:30:45', NULL);
INSERT INTO `fms_post_remark` VALUES (15, 2, 10088, '1', '2020-05-04 15:56:15', NULL);
INSERT INTO `fms_post_remark` VALUES (16, 2, 10088, '11', '2020-05-04 15:56:28', NULL);
INSERT INTO `fms_post_remark` VALUES (17, 2, 10088, '111', '2020-05-04 15:57:32', NULL);
INSERT INTO `fms_post_remark` VALUES (18, 2, 10088, '111', '2020-05-04 17:32:43', NULL);
INSERT INTO `fms_post_remark` VALUES (19, 2, 10088, '', '2020-05-04 18:56:25', NULL);
INSERT INTO `fms_post_remark` VALUES (20, 2, 10088, '测试', '2020-05-04 18:56:36', NULL);
INSERT INTO `fms_post_remark` VALUES (21, 2, 10088, '测试评论', '2020-05-04 18:56:50', NULL);
INSERT INTO `fms_post_remark` VALUES (22, 2, 10088, '测试1', '2020-05-04 19:12:58', NULL);
INSERT INTO `fms_post_remark` VALUES (23, 2, 10088, '测试2', '2020-05-04 19:13:04', NULL);
INSERT INTO `fms_post_remark` VALUES (24, 2, 10090, '123', '2020-05-04 23:14:23', NULL);
INSERT INTO `fms_post_remark` VALUES (25, 2, 10090, '123', '2020-05-04 23:22:51', NULL);
INSERT INTO `fms_post_remark` VALUES (26, 2, 10090, '123', '2020-05-04 23:24:53', NULL);
INSERT INTO `fms_post_remark` VALUES (27, 2, 10090, '123', '2020-05-04 23:25:01', NULL);
INSERT INTO `fms_post_remark` VALUES (28, 2, 10090, '123', '2020-05-04 23:28:28', NULL);
INSERT INTO `fms_post_remark` VALUES (29, 2, 10090, '123', '2020-05-04 23:29:34', NULL);
INSERT INTO `fms_post_remark` VALUES (30, 2, 10090, '123', '2020-05-04 23:30:07', NULL);
INSERT INTO `fms_post_remark` VALUES (31, 2, 10090, '123', '2020-05-04 23:31:47', NULL);
INSERT INTO `fms_post_remark` VALUES (32, 2, 10090, '123', '2020-05-04 23:33:27', NULL);
INSERT INTO `fms_post_remark` VALUES (33, 2, 10090, 'aaa', '2020-05-04 23:34:11', NULL);
INSERT INTO `fms_post_remark` VALUES (34, 2, 10090, '123', '2020-05-04 23:40:30', NULL);
INSERT INTO `fms_post_remark` VALUES (35, 5, 10088, '', '2020-05-04 23:59:44', NULL);
INSERT INTO `fms_post_remark` VALUES (36, 2, 10090, '一个测试评论', '2020-05-05 09:40:16', NULL);
INSERT INTO `fms_post_remark` VALUES (37, 2, 10090, '第二条测试评论', '2020-05-05 09:41:37', NULL);
INSERT INTO `fms_post_remark` VALUES (38, 2, 10090, '第三台哦', '2020-05-05 09:50:04', NULL);
INSERT INTO `fms_post_remark` VALUES (39, 2, 10090, '123', '2020-05-05 10:35:09', NULL);
INSERT INTO `fms_post_remark` VALUES (40, 2, 10091, '222', '2020-05-05 10:38:22', NULL);
INSERT INTO `fms_post_remark` VALUES (41, 2, 10090, '333', '2020-05-05 10:50:04', NULL);
INSERT INTO `fms_post_remark` VALUES (42, 2, 10090, '444', '2020-05-05 10:50:25', NULL);
INSERT INTO `fms_post_remark` VALUES (43, 2, 10090, '555', '2020-05-05 10:50:28', NULL);
INSERT INTO `fms_post_remark` VALUES (44, 2, 10090, 'test', '2020-05-05 10:50:43', NULL);
INSERT INTO `fms_post_remark` VALUES (45, 2, 10090, 'tt', '2020-05-05 10:51:52', NULL);
INSERT INTO `fms_post_remark` VALUES (46, 2, 10088, '456', '2020-05-05 11:06:20', NULL);
INSERT INTO `fms_post_remark` VALUES (47, 2, 10088, '789', '2020-05-05 11:08:12', NULL);
INSERT INTO `fms_post_remark` VALUES (48, 2, 10088, '测试头像', '2020-05-05 11:10:31', NULL);
INSERT INTO `fms_post_remark` VALUES (49, 2, 10090, '123', '2020-05-05 11:14:07', NULL);
INSERT INTO `fms_post_remark` VALUES (50, 2, 10088, '头像好像有点问题', '2020-05-05 11:15:16', NULL);
INSERT INTO `fms_post_remark` VALUES (51, 2, 10090, 'cds', '2020-05-05 11:18:00', NULL);
INSERT INTO `fms_post_remark` VALUES (52, 2, 10090, '3', '2020-05-05 11:18:19', NULL);
INSERT INTO `fms_post_remark` VALUES (53, 2, 10088, '123', '2020-05-05 11:19:30', NULL);
INSERT INTO `fms_post_remark` VALUES (54, 2, 10088, '123', '2020-05-05 11:19:46', NULL);
INSERT INTO `fms_post_remark` VALUES (55, 2, 10090, 'w', '2020-05-05 11:20:01', NULL);
INSERT INTO `fms_post_remark` VALUES (56, 2, 10088, '666', '2020-05-05 11:20:07', NULL);
INSERT INTO `fms_post_remark` VALUES (57, 2, 10088, '123', '2020-05-05 11:20:15', NULL);
INSERT INTO `fms_post_remark` VALUES (58, 2, 10088, '666', '2020-05-05 11:20:22', NULL);
INSERT INTO `fms_post_remark` VALUES (59, 2, 10088, '777', '2020-05-05 11:20:30', NULL);
INSERT INTO `fms_post_remark` VALUES (60, 2, 10090, '123456789', '2020-05-05 11:22:24', NULL);
INSERT INTO `fms_post_remark` VALUES (61, 2, 10090, 'dierci', '2020-05-05 11:22:39', NULL);
INSERT INTO `fms_post_remark` VALUES (62, 2, 10088, 'hhhh', '2020-05-05 11:24:13', NULL);
INSERT INTO `fms_post_remark` VALUES (63, 2, 10088, '啊啊啊啊', '2020-05-05 11:25:10', NULL);
INSERT INTO `fms_post_remark` VALUES (64, 6, 10088, '11', '2020-05-05 14:57:32', NULL);
INSERT INTO `fms_post_remark` VALUES (65, 6, 10088, '测试', '2020-05-05 14:59:03', NULL);
INSERT INTO `fms_post_remark` VALUES (66, 6, 10088, '测试1', '2020-05-05 14:59:09', NULL);
INSERT INTO `fms_post_remark` VALUES (67, 6, 10088, '评论有点问题', '2020-05-05 14:59:19', NULL);
INSERT INTO `fms_post_remark` VALUES (68, 6, 10088, '怎么回事呢', '2020-05-05 14:59:28', NULL);
INSERT INTO `fms_post_remark` VALUES (69, 6, 10088, '12', '2020-05-06 10:26:07', NULL);
INSERT INTO `fms_post_remark` VALUES (70, 15, 10088, '打发', '2020-05-06 11:19:58', NULL);
INSERT INTO `fms_post_remark` VALUES (71, 15, 10088, '大师傅', '2020-05-06 11:20:27', NULL);
INSERT INTO `fms_post_remark` VALUES (72, 14, 10088, '俺的沙发', '2020-05-06 12:39:25', NULL);
INSERT INTO `fms_post_remark` VALUES (73, 9, 10088, '呀呀呀呀', '2020-05-06 12:39:39', NULL);
INSERT INTO `fms_post_remark` VALUES (74, 15, 10088, '开心', '2020-05-06 12:40:25', NULL);
INSERT INTO `fms_post_remark` VALUES (75, 6, 10088, '好玩！', '2020-05-06 12:56:27', NULL);
INSERT INTO `fms_post_remark` VALUES (76, 47, 10088, '奥利给', '2020-05-06 13:16:28', NULL);
INSERT INTO `fms_post_remark` VALUES (77, 9, 10088, '真会玩', '2020-05-06 13:16:48', NULL);
INSERT INTO `fms_post_remark` VALUES (78, 15, 10088, '怎么都是Bob', '2020-05-06 13:17:11', NULL);
INSERT INTO `fms_post_remark` VALUES (79, 49, 10088, '测试', '2020-05-06 15:15:14', NULL);
INSERT INTO `fms_post_remark` VALUES (80, 49, 10088, '测试2', '2020-05-06 15:15:25', NULL);
INSERT INTO `fms_post_remark` VALUES (81, 49, 10088, '测试评论', '2020-05-06 15:15:33', NULL);
INSERT INTO `fms_post_remark` VALUES (82, 49, 10088, '试一下', '2020-05-06 15:15:39', NULL);
INSERT INTO `fms_post_remark` VALUES (83, 15, 10088, 'Why why why?', '2020-05-06 20:38:10', NULL);
INSERT INTO `fms_post_remark` VALUES (84, 14, 10088, '写这么多，不累吗？', '2020-05-06 20:38:44', NULL);
INSERT INTO `fms_post_remark` VALUES (85, 9, 10088, '都不带上我 :)', '2020-05-06 20:39:00', NULL);
INSERT INTO `fms_post_remark` VALUES (86, 49, 10089, '看起来不错哟！', '2020-05-06 20:41:47', NULL);
INSERT INTO `fms_post_remark` VALUES (87, 15, 10089, '哈哈哈！农民工！', '2020-05-06 20:42:41', NULL);
INSERT INTO `fms_post_remark` VALUES (88, 6, 10089, '怎么都是 Bob? 🙂', '2020-05-06 20:44:07', NULL);
INSERT INTO `fms_post_remark` VALUES (89, 14, 10089, '刚下飞机，过来逛逛 😀', '2020-05-06 20:44:56', NULL);
INSERT INTO `fms_post_remark` VALUES (90, 9, 10089, '我是 Bob，我是 Bob😁', '2020-05-06 20:47:41', NULL);
INSERT INTO `fms_post_remark` VALUES (91, 50, 10089, '123', '2020-05-07 16:13:11', NULL);
INSERT INTO `fms_post_remark` VALUES (92, 50, 10089, '2', '2020-05-07 16:13:14', NULL);
INSERT INTO `fms_post_remark` VALUES (93, 47, 10088, '123', '2020-05-07 16:33:51', NULL);
INSERT INTO `fms_post_remark` VALUES (94, 15, 10088, '都是Bob咋滴！', '2020-05-07 16:46:34', NULL);
INSERT INTO `fms_post_remark` VALUES (95, 15, 10088, '啦啦啦', '2020-05-07 16:53:49', NULL);
INSERT INTO `fms_post_remark` VALUES (96, 47, 10088, '嗨', '2020-05-07 16:57:00', NULL);
INSERT INTO `fms_post_remark` VALUES (97, 15, 10088, '我来了', '2020-05-07 16:57:53', NULL);
INSERT INTO `fms_post_remark` VALUES (101, 47, 10088, '太难过le', '2020-05-07 18:25:56', NULL);
INSERT INTO `fms_post_remark` VALUES (102, 47, 10088, '加油', '2020-05-07 18:26:48', NULL);
INSERT INTO `fms_post_remark` VALUES (103, 47, 10088, '！！！！', '2020-05-07 18:32:47', NULL);
INSERT INTO `fms_post_remark` VALUES (104, 15, 10088, '嗯哼', '2020-05-07 21:30:20', NULL);
INSERT INTO `fms_post_remark` VALUES (105, 47, 10088, '没错明天会更好！', '2020-05-07 21:33:08', NULL);
INSERT INTO `fms_post_remark` VALUES (106, 47, 10088, '！！！！', '2020-05-07 21:34:46', NULL);
INSERT INTO `fms_post_remark` VALUES (107, 47, 10088, '加油', '2020-05-07 21:36:55', NULL);
INSERT INTO `fms_post_remark` VALUES (108, 47, 10088, '弹弹弹', '2020-05-07 21:37:41', NULL);
INSERT INTO `fms_post_remark` VALUES (109, 9, 10088, '应该可以了吧', '2020-05-07 21:38:37', NULL);
INSERT INTO `fms_post_remark` VALUES (110, 47, 10088, '你牛啤', '2020-05-07 22:09:24', NULL);
INSERT INTO `fms_post_remark` VALUES (114, 14, 10088, '厉害了', '2020-05-07 23:12:34', NULL);
INSERT INTO `fms_post_remark` VALUES (115, 47, 10088, '真的吗？', '2020-05-07 23:12:57', NULL);
INSERT INTO `fms_post_remark` VALUES (116, 50, 10088, '123', '2020-05-07 23:47:28', NULL);
INSERT INTO `fms_post_remark` VALUES (117, 50, 10088, '23', '2020-05-07 23:47:30', NULL);
INSERT INTO `fms_post_remark` VALUES (118, 50, 10089, 'qq', '2020-05-07 23:49:52', NULL);
INSERT INTO `fms_post_remark` VALUES (119, 50, 10089, '测试', '2020-05-07 23:51:14', NULL);
INSERT INTO `fms_post_remark` VALUES (120, 50, 10089, '哈哈啊', '2020-05-07 23:52:30', NULL);
INSERT INTO `fms_post_remark` VALUES (122, 4, 10088, '评论好少啊', '2020-05-08 08:10:24', NULL);
INSERT INTO `fms_post_remark` VALUES (123, 4, 10088, '那我也来发一下😜', '2020-05-08 22:42:35', NULL);
INSERT INTO `fms_post_remark` VALUES (132, 52, 10088, '试过手机看看吗', '2020-05-09 13:12:51', NULL);
INSERT INTO `fms_post_remark` VALUES (133, 52, 10088, '竖屏所有的布局都乱了', '2020-05-09 13:13:23', NULL);
INSERT INTO `fms_post_remark` VALUES (139, 55, 10089, '一起加入吧！', '2020-05-09 17:06:44', NULL);
INSERT INTO `fms_post_remark` VALUES (140, 55, 10088, 'aaa', '2020-05-09 21:04:24', NULL);
INSERT INTO `fms_post_remark` VALUES (141, 55, 10088, 'bbb', '2020-05-09 21:04:41', NULL);
INSERT INTO `fms_post_remark` VALUES (142, 55, 10088, '啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊', '2020-05-09 21:05:06', NULL);
INSERT INTO `fms_post_remark` VALUES (143, 56, 10088, '啥呀\n', '2020-05-09 21:10:04', NULL);
INSERT INTO `fms_post_remark` VALUES (144, 56, 10088, '。。。。。。\n', '2020-05-09 21:20:45', NULL);
INSERT INTO `fms_post_remark` VALUES (145, 56, 10088, '、、、、、、', '2020-05-09 21:20:54', NULL);
INSERT INTO `fms_post_remark` VALUES (146, 57, 10090, '', '2020-05-09 21:21:14', NULL);
INSERT INTO `fms_post_remark` VALUES (147, 57, 10090, '', '2020-05-09 21:21:17', NULL);
INSERT INTO `fms_post_remark` VALUES (148, 57, 10090, '', '2020-05-09 21:21:18', NULL);
INSERT INTO `fms_post_remark` VALUES (149, 57, 10090, '', '2020-05-09 21:21:20', NULL);
INSERT INTO `fms_post_remark` VALUES (150, 57, 10090, '', '2020-05-09 21:21:28', NULL);
INSERT INTO `fms_post_remark` VALUES (151, 57, 10090, '', '2020-05-09 21:21:28', NULL);
INSERT INTO `fms_post_remark` VALUES (152, 57, 10088, '。。。\n', '2020-05-09 21:21:37', NULL);
INSERT INTO `fms_post_remark` VALUES (153, 57, 10088, '。。。。。', '2020-05-09 21:21:58', NULL);
INSERT INTO `fms_post_remark` VALUES (154, 57, 10088, '写完不能通过回车键发送消息\n', '2020-05-09 21:22:23', NULL);
INSERT INTO `fms_post_remark` VALUES (155, 58, 10088, '发表评论还是没有通过回车键出发发送的函数', '2020-05-09 21:26:55', NULL);
INSERT INTO `fms_post_remark` VALUES (156, 57, 10090, '', '2020-05-09 21:29:26', NULL);
INSERT INTO `fms_post_remark` VALUES (157, 57, 10090, '', '2020-05-09 21:40:19', NULL);
INSERT INTO `fms_post_remark` VALUES (158, 57, 10090, '', '2020-05-09 21:40:21', NULL);
INSERT INTO `fms_post_remark` VALUES (159, 58, 10090, ' ', '2020-05-09 21:43:11', NULL);
INSERT INTO `fms_post_remark` VALUES (160, 58, 10090, ' ', '2020-05-09 21:43:11', NULL);
INSERT INTO `fms_post_remark` VALUES (161, 58, 10090, ' ', '2020-05-09 21:43:15', NULL);
INSERT INTO `fms_post_remark` VALUES (162, 58, 10090, ' ', '2020-05-09 21:43:16', NULL);
INSERT INTO `fms_post_remark` VALUES (163, 57, 10090, '', '2020-05-09 21:43:27', NULL);
INSERT INTO `fms_post_remark` VALUES (164, 57, 10088, '', '2020-05-09 21:53:12', NULL);
INSERT INTO `fms_post_remark` VALUES (165, 57, 10088, '11', '2020-05-09 21:54:36', NULL);
INSERT INTO `fms_post_remark` VALUES (166, 57, 10088, '123', '2020-05-09 21:58:49', NULL);
INSERT INTO `fms_post_remark` VALUES (167, 57, 10088, '测试', '2020-05-09 22:26:50', NULL);
INSERT INTO `fms_post_remark` VALUES (168, 57, 10090, '11', '2020-05-09 22:30:32', NULL);
INSERT INTO `fms_post_remark` VALUES (169, 57, 10090, '123', '2020-05-09 22:30:41', NULL);
INSERT INTO `fms_post_remark` VALUES (170, 52, 10088, 'really?', '2020-05-11 22:53:12', NULL);
INSERT INTO `fms_post_remark` VALUES (171, 56, 10090, 'testsds', '2020-05-12 11:51:44', NULL);
INSERT INTO `fms_post_remark` VALUES (172, 59, 10090, 'testsds', '2020-05-12 11:52:20', NULL);
INSERT INTO `fms_post_remark` VALUES (176, 57, 10088, '非成员评论', '2020-05-19 11:56:04', NULL);
INSERT INTO `fms_post_remark` VALUES (177, 60, 10088, '非成员评论', '2020-05-19 11:58:27', NULL);
INSERT INTO `fms_post_remark` VALUES (178, 56, 10094, '成员评论', '2020-05-19 12:00:46', NULL);
INSERT INTO `fms_post_remark` VALUES (179, 56, 10094, '成员评论1', '2020-05-19 12:01:22', NULL);
INSERT INTO `fms_post_remark` VALUES (180, 55, 10094, '个人贴评论', '2020-05-19 12:01:56', NULL);
INSERT INTO `fms_post_remark` VALUES (181, 9, 10094, '个人贴评论', '2020-05-19 12:03:14', NULL);
INSERT INTO `fms_post_remark` VALUES (182, 59, 10088, 'test', '2020-05-19 17:29:49', NULL);
INSERT INTO `fms_post_remark` VALUES (183, 59, 10088, 'tes t', '2020-05-19 17:33:19', NULL);
INSERT INTO `fms_post_remark` VALUES (184, 59, 10088, 'test', '2020-05-19 17:33:42', NULL);
INSERT INTO `fms_post_remark` VALUES (190, 58, 10088, '迪杰斯特拉', '2020-05-19 17:44:44', NULL);
INSERT INTO `fms_post_remark` VALUES (191, 58, 10088, '有问题', '2020-05-19 17:45:10', NULL);
INSERT INTO `fms_post_remark` VALUES (194, 59, 10088, 'hhhhh', '2020-05-19 17:47:27', NULL);
INSERT INTO `fms_post_remark` VALUES (195, 59, 10088, '？？？？', '2020-05-19 17:47:38', NULL);
INSERT INTO `fms_post_remark` VALUES (196, 59, 10088, '时间问题', '2020-05-19 17:53:08', NULL);
INSERT INTO `fms_post_remark` VALUES (197, 62, 10107, '找bug', '2020-05-20 13:42:52', NULL);
INSERT INTO `fms_post_remark` VALUES (198, 57, 10107, '到此一游', '2020-05-20 13:45:16', NULL);
INSERT INTO `fms_post_remark` VALUES (199, 60, 10089, '123', '2020-05-21 23:26:02', NULL);
INSERT INTO `fms_post_remark` VALUES (200, 62, 10089, '123', '2020-05-21 23:26:16', NULL);
INSERT INTO `fms_post_remark` VALUES (201, 56, 10089, '123\n', '2020-05-25 09:58:35', NULL);
INSERT INTO `fms_post_remark` VALUES (202, 56, 10089, '132\n', '2020-05-25 09:58:40', NULL);
INSERT INTO `fms_post_remark` VALUES (203, 56, 10089, '111\n', '2020-05-25 09:58:47', NULL);
INSERT INTO `fms_post_remark` VALUES (204, 56, 10089, '测试换行', '2020-05-25 10:02:03', NULL);
INSERT INTO `fms_post_remark` VALUES (205, 56, 10089, '测试换行\n\n', '2020-05-25 10:05:15', NULL);
INSERT INTO `fms_post_remark` VALUES (206, 56, 10089, '12312412512\n\n', '2020-05-25 10:05:28', NULL);
INSERT INTO `fms_post_remark` VALUES (207, 56, 10089, '5151521521\n\n', '2020-05-25 10:05:38', NULL);
INSERT INTO `fms_post_remark` VALUES (208, 56, 10089, 'OKKOKKK\n换行成功1\n换行成功2\n3333\n', '2020-05-25 10:06:07', NULL);
INSERT INTO `fms_post_remark` VALUES (209, 60, 10089, '测试换行1\n测试换行2\n', '2020-05-25 10:07:51', NULL);
INSERT INTO `fms_post_remark` VALUES (210, 60, 10089, '111\n222\n333\n444\n成功！', '2020-05-25 10:08:31', NULL);
INSERT INTO `fms_post_remark` VALUES (211, 60, 10089, '222\n333\n444\n555\n成功\n', '2020-05-25 10:08:40', NULL);
INSERT INTO `fms_post_remark` VALUES (212, 58, 10089, '123123', '2020-05-25 10:09:12', NULL);
INSERT INTO `fms_post_remark` VALUES (213, 62, 10089, '哈哈哈哈', '2020-05-25 10:09:22', NULL);
INSERT INTO `fms_post_remark` VALUES (214, 56, 10089, '12312412412', '2020-05-25 10:41:20', NULL);
INSERT INTO `fms_post_remark` VALUES (215, 56, 10089, 'yes OK！\nyes OK！\nyes OK！\nyes OK！\n', '2020-05-25 11:59:21', NULL);
INSERT INTO `fms_post_remark` VALUES (216, 56, 10089, '1111\n', '2020-05-25 12:00:30', NULL);
INSERT INTO `fms_post_remark` VALUES (217, 60, 10089, '123123123', '2020-05-25 12:01:57', NULL);
INSERT INTO `fms_post_remark` VALUES (218, 60, 10089, '1312\n', '2020-05-25 12:02:35', NULL);
INSERT INTO `fms_post_remark` VALUES (219, 60, 10089, 'yes ok\nyes ok\nyes ok\nyes ok\nyes ok\n\n', '2020-05-25 12:02:44', NULL);
INSERT INTO `fms_post_remark` VALUES (220, 60, 10088, '111', '2020-05-26 13:59:47', NULL);
INSERT INTO `fms_post_remark` VALUES (221, 63, 10088, '呀呀呀', '2020-05-26 15:36:33', NULL);
INSERT INTO `fms_post_remark` VALUES (229, 62, 10088, '评论呀', '2020-05-27 17:59:30', NULL);
INSERT INTO `fms_post_remark` VALUES (230, 62, 10088, '大家一起嗨皮皮', '2020-05-27 17:59:39', NULL);
INSERT INTO `fms_post_remark` VALUES (231, 62, 10088, '啦啦啦', '2020-05-27 17:59:46', NULL);
INSERT INTO `fms_post_remark` VALUES (232, 64, 10088, '沙发！！！', '2020-05-28 16:02:53', NULL);
INSERT INTO `fms_post_remark` VALUES (233, 64, 10113, 'dididi', '2020-05-28 16:45:03', NULL);
INSERT INTO `fms_post_remark` VALUES (234, 57, 10113, '666', '2020-05-28 16:47:40', NULL);
INSERT INTO `fms_post_remark` VALUES (235, 71, 10088, '666', '2020-05-28 20:36:27', NULL);
INSERT INTO `fms_post_remark` VALUES (236, 56, 10094, '6', '2020-05-29 10:22:28', NULL);
INSERT INTO `fms_post_remark` VALUES (237, 55, 10088, '测试\n', '2020-05-29 13:55:12', NULL);
INSERT INTO `fms_post_remark` VALUES (239, 50, 10088, '我能发表评论', '2020-05-29 14:38:23', NULL);
INSERT INTO `fms_post_remark` VALUES (240, 56, 10090, '加油~~', '2020-05-29 15:38:33', NULL);
INSERT INTO `fms_post_remark` VALUES (241, 56, 10090, '111', '2020-05-29 15:42:11', NULL);
INSERT INTO `fms_post_remark` VALUES (242, 72, 10088, '偷偷评论一下', '2020-05-29 16:40:11', NULL);
INSERT INTO `fms_post_remark` VALUES (249, 72, 10090, '111', '2020-05-29 17:05:01', NULL);
INSERT INTO `fms_post_remark` VALUES (253, 72, 10094, '123456', '2020-05-29 17:50:42', NULL);
INSERT INTO `fms_post_remark` VALUES (259, 73, 10088, '发表不了', '2020-05-29 18:35:08', NULL);
INSERT INTO `fms_post_remark` VALUES (263, 72, 10088, '呀', '2020-05-29 18:45:50', NULL);
INSERT INTO `fms_post_remark` VALUES (265, 69, 10088, '111', '2020-05-30 14:09:06', NULL);
INSERT INTO `fms_post_remark` VALUES (267, 73, 10088, '111', '2020-05-30 14:14:38', NULL);
INSERT INTO `fms_post_remark` VALUES (268, 69, 10088, '222', '2020-05-30 14:14:47', NULL);
INSERT INTO `fms_post_remark` VALUES (269, 71, 10088, '打发', '2020-05-30 15:20:13', NULL);
INSERT INTO `fms_post_remark` VALUES (270, 71, 10088, '发多少', '2020-05-30 15:20:18', NULL);
INSERT INTO `fms_post_remark` VALUES (272, 56, 10116, '我也来试试\n', '2020-05-30 15:33:41', NULL);
INSERT INTO `fms_post_remark` VALUES (279, 90, 10122, 'day day up', '2020-05-30 16:36:52', NULL);

-- ----------------------------
-- Table structure for fms_user_like_post
-- ----------------------------
DROP TABLE IF EXISTS `fms_user_like_post`;
CREATE TABLE `fms_user_like_post`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL COMMENT '点赞用户 id',
  `post_id` bigint(20) NULL DEFAULT NULL COMMENT '点赞帖子 id',
  `status` int(1) NULL DEFAULT NULL COMMENT '点赞状态: 0 -> 未点赞/取消点赞，1 -> 点赞',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fms_user_like_post_ibfk_1`(`user_id`) USING BTREE,
  INDEX `fms_user_like_post_ibfk_2`(`post_id`) USING BTREE,
  CONSTRAINT `fms_user_like_post_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fms_user_like_post_ibfk_2` FOREIGN KEY (`post_id`) REFERENCES `fms_post` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户帖子点赞表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fms_user_like_post
-- ----------------------------
INSERT INTO `fms_user_like_post` VALUES (1, 10088, 4, 1);
INSERT INTO `fms_user_like_post` VALUES (2, 10088, 2, 1);
INSERT INTO `fms_user_like_post` VALUES (3, 10089, 4, 1);
INSERT INTO `fms_user_like_post` VALUES (4, 10088, 56, 1);
INSERT INTO `fms_user_like_post` VALUES (5, 10089, 57, 1);
INSERT INTO `fms_user_like_post` VALUES (6, 10088, 60, 1);
INSERT INTO `fms_user_like_post` VALUES (7, 10089, 60, 1);
INSERT INTO `fms_user_like_post` VALUES (8, 10088, 59, 1);
INSERT INTO `fms_user_like_post` VALUES (9, 10088, 58, 1);
INSERT INTO `fms_user_like_post` VALUES (10, 10088, 52, 1);
INSERT INTO `fms_user_like_post` VALUES (11, 10088, 62, 1);
INSERT INTO `fms_user_like_post` VALUES (12, 10088, 9, 1);
INSERT INTO `fms_user_like_post` VALUES (13, 10088, 14, 1);
INSERT INTO `fms_user_like_post` VALUES (14, 10088, 47, 1);
INSERT INTO `fms_user_like_post` VALUES (15, 10088, 51, 1);
INSERT INTO `fms_user_like_post` VALUES (16, 10089, 62, 1);
INSERT INTO `fms_user_like_post` VALUES (17, 10089, 52, 1);
INSERT INTO `fms_user_like_post` VALUES (18, 10089, 55, 1);
INSERT INTO `fms_user_like_post` VALUES (19, 10089, 56, 1);
INSERT INTO `fms_user_like_post` VALUES (20, 10089, 50, 1);
INSERT INTO `fms_user_like_post` VALUES (21, 10088, 64, 1);
INSERT INTO `fms_user_like_post` VALUES (22, 10088, 55, 1);
INSERT INTO `fms_user_like_post` VALUES (23, 10088, 69, 1);
INSERT INTO `fms_user_like_post` VALUES (24, 10113, 69, 1);
INSERT INTO `fms_user_like_post` VALUES (25, 10113, 64, 1);
INSERT INTO `fms_user_like_post` VALUES (26, 10113, 57, 1);
INSERT INTO `fms_user_like_post` VALUES (27, 10088, 71, 1);
INSERT INTO `fms_user_like_post` VALUES (28, 10088, 6, 1);
INSERT INTO `fms_user_like_post` VALUES (29, 10088, 50, 1);
INSERT INTO `fms_user_like_post` VALUES (30, 10113, 55, 1);
INSERT INTO `fms_user_like_post` VALUES (31, 10088, 73, 1);
INSERT INTO `fms_user_like_post` VALUES (32, 10088, 72, 1);

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限编码',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, 'cms:user:create', '用户注册');
INSERT INTO `sys_permission` VALUES (2, 'cms:user:read', '查看个人信息');
INSERT INTO `sys_permission` VALUES (3, 'cms:user:update', '更新个人信息');
INSERT INTO `sys_permission` VALUES (4, 'cms:user:delete', '删除用户');
INSERT INTO `sys_permission` VALUES (5, 'cms:club:create_apply', '申请创建社团');
INSERT INTO `sys_permission` VALUES (6, 'cms:club:create_audit', '审核创建申请');
INSERT INTO `sys_permission` VALUES (7, 'cms:club:update', '更新介绍');
INSERT INTO `sys_permission` VALUES (8, 'cms:club:delete', '删除社团');
INSERT INTO `sys_permission` VALUES (9, 'cms:club:delete_apply', '申请解散');
INSERT INTO `sys_permission` VALUES (10, 'cms:club:delete_apply_audit', '审核解散申请');
INSERT INTO `sys_permission` VALUES (11, 'cms:club:join_apply', '申请加入社团');
INSERT INTO `sys_permission` VALUES (12, 'cms:club:join_apply_audit', '审核加入申请');
INSERT INTO `sys_permission` VALUES (13, 'cms:club:chief_change_apply', '申请换届');
INSERT INTO `sys_permission` VALUES (14, 'cms:club:chief_change_apply_audit', '审核换届申请');
INSERT INTO `sys_permission` VALUES (15, 'cms:club:cent_apply', '申请认证');
INSERT INTO `sys_permission` VALUES (16, 'cms:club:cent_apply_audit', '审核认证申请');
INSERT INTO `sys_permission` VALUES (17, 'cms:member:create', '添加成员');
INSERT INTO `sys_permission` VALUES (18, 'cms:member:read', '查看成员信息');
INSERT INTO `sys_permission` VALUES (19, 'cms:member:delete', '删除成员');
INSERT INTO `sys_permission` VALUES (20, 'cms:bulletin:create', '发布公告');
INSERT INTO `sys_permission` VALUES (21, 'cms:bulletin:read', '阅读公告');
INSERT INTO `sys_permission` VALUES (22, 'cms:bulletin:update', '更新公告');
INSERT INTO `sys_permission` VALUES (23, 'cms:bulletin:delete', '删除公告');
INSERT INTO `sys_permission` VALUES (24, 'cms:activity:create', '活动发布');
INSERT INTO `sys_permission` VALUES (25, 'cms:activity:read', '查看活动');
INSERT INTO `sys_permission` VALUES (26, 'cms:activity:update', '更新活动');
INSERT INTO `sys_permission` VALUES (27, 'cms:activity:delete', '删除活动');
INSERT INTO `sys_permission` VALUES (28, 'cms:activity:apply', '申请活动');
INSERT INTO `sys_permission` VALUES (29, 'cms:activity:audit', '审核活动申请');
INSERT INTO `sys_permission` VALUES (30, 'fms:post:create', '发布帖子');
INSERT INTO `sys_permission` VALUES (31, 'fms:post:read', '查看帖子');
INSERT INTO `sys_permission` VALUES (32, 'fms:post:update', '更新帖子');
INSERT INTO `sys_permission` VALUES (33, 'fms:post:delete', '删除帖子');
INSERT INTO `sys_permission` VALUES (34, 'fms:remark:create', '发表评论');
INSERT INTO `sys_permission` VALUES (35, 'fms:remark:read', '查看评论');
INSERT INTO `sys_permission` VALUES (36, 'fms:remark:update', '更新评论');
INSERT INTO `sys_permission` VALUES (37, 'fms:remark:delete', '删除评论');

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
INSERT INTO `sys_role` VALUES (1, 'student', '学生');
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
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色权限关系' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_permission_rel
-- ----------------------------
INSERT INTO `sys_role_permission_rel` VALUES (1, 1, 2);
INSERT INTO `sys_role_permission_rel` VALUES (2, 1, 3);
INSERT INTO `sys_role_permission_rel` VALUES (3, 4, 4);
INSERT INTO `sys_role_permission_rel` VALUES (4, 1, 5);
INSERT INTO `sys_role_permission_rel` VALUES (5, 4, 6);
INSERT INTO `sys_role_permission_rel` VALUES (6, 3, 7);
INSERT INTO `sys_role_permission_rel` VALUES (7, 4, 8);
INSERT INTO `sys_role_permission_rel` VALUES (8, 3, 9);
INSERT INTO `sys_role_permission_rel` VALUES (9, 4, 10);
INSERT INTO `sys_role_permission_rel` VALUES (10, 1, 11);
INSERT INTO `sys_role_permission_rel` VALUES (11, 3, 12);
INSERT INTO `sys_role_permission_rel` VALUES (12, 3, 13);
INSERT INTO `sys_role_permission_rel` VALUES (13, 4, 14);
INSERT INTO `sys_role_permission_rel` VALUES (14, 3, 15);
INSERT INTO `sys_role_permission_rel` VALUES (15, 4, 16);
INSERT INTO `sys_role_permission_rel` VALUES (16, 3, 17);
INSERT INTO `sys_role_permission_rel` VALUES (17, 2, 18);
INSERT INTO `sys_role_permission_rel` VALUES (18, 3, 18);
INSERT INTO `sys_role_permission_rel` VALUES (19, 3, 19);
INSERT INTO `sys_role_permission_rel` VALUES (20, 3, 20);
INSERT INTO `sys_role_permission_rel` VALUES (21, 2, 21);
INSERT INTO `sys_role_permission_rel` VALUES (22, 3, 21);
INSERT INTO `sys_role_permission_rel` VALUES (23, 3, 22);
INSERT INTO `sys_role_permission_rel` VALUES (24, 3, 23);
INSERT INTO `sys_role_permission_rel` VALUES (25, 3, 24);
INSERT INTO `sys_role_permission_rel` VALUES (26, 3, 25);
INSERT INTO `sys_role_permission_rel` VALUES (27, 3, 26);
INSERT INTO `sys_role_permission_rel` VALUES (28, 3, 27);
INSERT INTO `sys_role_permission_rel` VALUES (29, 4, 28);
INSERT INTO `sys_role_permission_rel` VALUES (30, 1, 29);
INSERT INTO `sys_role_permission_rel` VALUES (31, 1, 30);
INSERT INTO `sys_role_permission_rel` VALUES (32, 1, 31);
INSERT INTO `sys_role_permission_rel` VALUES (33, 1, 32);
INSERT INTO `sys_role_permission_rel` VALUES (34, 1, 33);
INSERT INTO `sys_role_permission_rel` VALUES (35, 1, 34);

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
  `register_date` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '注册时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10123 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (10088, 'test', '$2a$10$JbgYb/X7TwKNNmlbQA7QFem2I5HUeA7n.fXdTaQcfVZeAFS9dVhs.', 'Bob', 'http://101.200.193.180:9520/files/images/0c0b971fde4f4156a4b3b3682a1191ae.jpg', '软件工程', '123467@qq.com', '13173288926', '才不告诉你', '改代码了', '你好？', '好的', 0, '2020-05-18 16:28:53');
INSERT INTO `sys_user` VALUES (10089, 'test1', '$2a$10$wSRBQgGYHT38McLCpmi7juHMZxnNxAlW8P8jkzk6lTnQnRHaG7tdG', 'jack', 'http://101.200.193.180:9520/files/images/996ef407352049d48f6f77277e268ba9.jpg', 'software', 'jane@gmail.com', '12345678901', '旗山校区', 'good good study, day day up', '你好？', '好的', 0, '2020-05-18 16:28:53');
INSERT INTO `sys_user` VALUES (10090, 'test2', '$2a$10$df9kqEK6zW9YG1Iu.PZg2.FxZ7jHwAWOI2tNK7yRCdSV0F94fH3I6', 'jaack', 'http://101.200.193.180:9520/files/images/15f25c17c8cc4ca2ab56e2db79ef95d5.jpg', 'sare', 'davy@gmail.com', '12345678901', 'fussds', 'good good study, day day up', '你好？', '好的', 0, '2020-05-18 16:28:53');
INSERT INTO `sys_user` VALUES (10091, 'test3', '$2a$10$iVxxDW3IAVflLUbN0ctwte5ha4GS082L5f6G/d0PKAS3E727/zal6', 'Tom2', 'http://101.200.193.180:9520/files/images/b2cd2f296d7348528b446ffd8984584f.jpg', 'SE', 'tom@gmail.com', '12345678901', NULL, 'good good study, day day up', '你好？', '好的', 0, '2020-05-18 16:28:53');
INSERT INTO `sys_user` VALUES (10092, 'test4', '$2a$10$4AtDmk5ZYsUs/fHzlDDqQ.cDNChFno1yt4HA4rlmTuY77W8rQAuQ2', 'Davy', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 'SE', 'what@gmail.com', '12345678901', NULL, 'good good study, day day up', '你好？', '好的', 0, '2020-05-18 16:28:53');
INSERT INTO `sys_user` VALUES (10093, 'test5', '$2a$10$tdX3hagfwjVnCh1gK8mDZOIUpEEUjXmy2eo4y9K9qh1v9GB0j3PVO', 'Nacy', 'http://b-ssl.duitang.com/uploads/item/201810/28/20181028170516_hnpsx.thumb.700_0.jpeg', 'SE', 'what@gmail.com', '12345678901', NULL, 'good good study, day day up', '你好？', '好的', 0, '2020-05-18 16:28:53');
INSERT INTO `sys_user` VALUES (10094, 'test6', '$2a$10$oJQZ9GWUS3Uip3piC3kadeDnxFlpHB3xhkzYrVfMgFzQyqIz3x/W.', 'What', 'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=274461496,4282807049&fm=26&gp=0.jpg', 'SE', 'what@gmail.com', '12345678901', '测试', 'good good study, day day up', '你好？', '好的', 0, '2020-05-18 16:28:53');
INSERT INTO `sys_user` VALUES (10095, 'test7', '$2a$10$s7JEkmb.TM7ELufVjuclkOFnwSGJKFfpRO/VItlfhYE2NsvD6NKW2', 'What', 'http://101.200.193.180:9520/files/images/31645979fcad44d68e3372dd8b0a5595.jpg', 'SE', 'what@gmail.com', '12345678901', NULL, 'good good study, day day up', '你好？', '好的', 0, '2020-05-18 16:28:53');
INSERT INTO `sys_user` VALUES (10096, 'test8', '$2a$10$7N.APm7gt56g779NdV.kE.6YHVWEYuRFBaruM2KSbayJr4S6oxmjm', 'Hat', 'http://b-ssl.duitang.com/uploads/item/201810/28/20181028170516_hnpsx.thumb.700_0.jpeg', 'SE', 'what@gmail.com', '12345678901', NULL, 'good good study, day day up', '你好？', '好的', 0, '2020-05-18 16:28:53');
INSERT INTO `sys_user` VALUES (10097, 'test9', '$2a$10$NhqYuN7sYsuwAHLXswLVlumlxn9rxDvvITfoSoMkw.tLU8Y0mH3Ti', 'What', 'http://101.200.193.180:9520/files/images/f69c9b3a04404807b7895b618560986f.jpg', 'SE', 'what@gmail.com', '12345678901', NULL, 'good good study, day day up', '你好？', '好的', 1, '2020-05-18 16:28:53');
INSERT INTO `sys_user` VALUES (10098, 'zhishe', '$2a$10$8Mu.0h9y.1qYR4y3Wr7du.BbgdD7sk0SIisnTAUuiTGTykBJTSeXO', NULL, 'http://101.200.193.180:9520/files/images/3d6b9a1e61f54ca2a82982589ffb3800.jpg', '大数据', NULL, '12345678910', NULL, NULL, NULL, NULL, 1, '2020-05-18 16:28:53');
INSERT INTO `sys_user` VALUES (10099, 'zhixin', '$2a$10$12nXdHVdlhXv5n89/.vAFe.FCxPNrxEI3/OtF.ufR5K7QUtaVpJwu', NULL, 'http://101.200.193.180:9520/files/images/3d6b9a1e61f54ca2a82982589ffb3800.jpg', '大数据', NULL, '12345678910', NULL, NULL, NULL, NULL, 1, '2020-05-18 16:28:53');
INSERT INTO `sys_user` VALUES (10100, 'wang', '$2a$10$Mg7IqOgHySeAweC1SwCLx.oU9lKQ68RwCeXD3zqUvVOBGy62.r7zS', '管理员小王', 'http://101.200.193.180:9520/files/images/577693a86c4c48a0bb9372a00d58f656.jpg', 'SE', 'lingsiii@sina.com', '18532415172', NULL, '我是管理员小王', '你好吗', '我很好', 1, '2020-05-18 16:28:53');
INSERT INTO `sys_user` VALUES (10101, 'zhang', '$2a$10$55x6Zal5VU6QWq8t8ckegOUHMkT.OtNMYcbi2gDbjJ/ji6wPatGae', 'What', 'http://b-ssl.duitang.com/uploads/item/201810/28/20181028170516_hnpsx.thumb.700_0.jpeg', 'SE', 'what@gmail.com', '12345678901', NULL, NULL, NULL, NULL, 1, '2020-05-18 16:28:53');
INSERT INTO `sys_user` VALUES (10102, 'ddds', '$2a$10$oLVpb5ttdOmWQEbMSKhhrOzVZ7exX84Qe/luvKB0aeyYDvIXmBWPu', 'What', 'http://b-ssl.duitang.com/uploads/item/201810/28/20181028170516_hnpsx.thumb.700_0.jpeg', 'SE', 'what@gmail.com', '12345678901', NULL, NULL, '你好？', '123', 0, '2020-05-18 16:28:53');
INSERT INTO `sys_user` VALUES (10103, 'admin', '$2a$10$MKRY5g3umQ3voWBQ4hu0jOolzG6m9R8TNiUU0BzgPcqdZiDAT9Lea', 'xjliang', 'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=824188197,3321145112&fm=26&gp=0.jpg', 'SE', 'what@gmail.com', '15399010931', 'Fujian,China', 'never give up', NULL, NULL, 1, '2020-05-18 16:28:53');
INSERT INTO `sys_user` VALUES (10107, 'userwang', '$2a$10$3dE.hVhGES4ataaeh.9B7O25WeOEWea6WvBkXJ32OD0tBhVcPdGxq', '用户小王', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1242474983,1712633701&fm=26&gp=0.jpg', 'SE', 'testmail@sina.com', '12345678901', NULL, '我是用户小王', '你好吗', '不好', 0, '2020-05-18 16:28:53');
INSERT INTO `sys_user` VALUES (10108, 'test111', '$2a$10$RhkZl9c03LiQhnGbAFCWNe960pJceh0/KVDH69GVJeXc1d4gb1N0q', 'test111', NULL, '软工', '111@qq.com', '11111111111', NULL, NULL, NULL, NULL, 0, '2020-05-18 16:28:53');
INSERT INTO `sys_user` VALUES (10110, 'zhiser', '$2a$10$uxGAVcJpPfQIxbfiwoOiGOTnCIxAxW.fWT1MRcjhL.gvKCfz1ND.K', 'zhisher', NULL, 'cs', '123341141@qq.com', '17750505050', NULL, NULL, NULL, NULL, 0, '2020-05-23 22:21:08');
INSERT INTO `sys_user` VALUES (10111, 'liulaoc', '$2a$10$8u1TVxVWuJjhR9hGM1sSaO3gK4C9ZsT7ygD8BgEDS11oidMq3UNEC', 'Bin', NULL, '软件工程', '8489156156@qq.com', '15619861646', NULL, NULL, NULL, NULL, 0, '2020-05-25 18:05:07');
INSERT INTO `sys_user` VALUES (10113, 'mmh', '$2a$10$YD6i.jMTcQtfQHoaV4uaOe.UltCJNjeAnzsheUM.mFuBFvAt6/O8a', 'aaa', 'http://101.200.193.180:9520/files/images/77e98013cbba4b5ca6740bd772dca378.jpg', '', '123456789@qq.com', '18159725712', NULL, NULL, NULL, NULL, 0, '2020-05-26 20:20:28');
INSERT INTO `sys_user` VALUES (10114, 'user1', '$2a$10$RgumWgX1E1WNGub8zR.2...mB6gtYQdIEp6HdB8pZaO/WvEJADNDK', 'Kiki', 'http://101.200.193.180:9520/files/images/98f060d2f7f34f5a96b92b13f6b7939a.jpg', '日语', '1195669260@qq.com', '18259184771', 'a', NULL, NULL, NULL, 0, '2020-05-29 11:25:33');
INSERT INTO `sys_user` VALUES (10115, 'user2', '$2a$10$rxJ6sytClP3gMLKWRlcQC.e/BxlWkOqCYS6plgncNGXRZrcaohQ5q', 'Kate', NULL, '123', '11@qq.com', '18259184782', NULL, NULL, NULL, NULL, 0, '2020-05-29 11:48:25');
INSERT INTO `sys_user` VALUES (10116, 'xjliang', '$2a$10$hbr/x/ScWjzmS5qw3/.tmuAQsPhxw5fYoDLhoSuZ3F9DrgJXjAv5O', NULL, 'http://101.200.193.180:9520/files/images/9e27fbaf697a404a822b64dc6fb0353e.jpg', NULL, 'gnulxj@gmail.com', '12345678901', NULL, NULL, NULL, NULL, 0, '2020-05-29 19:12:43');
INSERT INTO `sys_user` VALUES (10118, 'zhixinlin', '$2a$10$nLsKWIC7mQ6SCGjMVe/TnejqtoW511NLvsikiJjjyDRTmuXDYP3ES', 'lin', NULL, '无', '23321634@qq.com', '13205023333', '无', '无', NULL, NULL, 0, '2020-05-30 01:03:46');
INSERT INTO `sys_user` VALUES (10119, 'lzx', '$2a$10$mn7YSGcTdcwzqbcs6THKKe37W1lYWABo9r3K7n6AU9./UEsahvRqy', 'daf', NULL, 'adsfa', '233216348@qq.com', '13205022222', 'd', 'dasf', NULL, NULL, 0, '2020-05-30 01:48:44');
INSERT INTO `sys_user` VALUES (10120, 'name', '$2a$10$lqB4Ek.uPn0DSfN4jVfm7.n1GRXjiuDnsMuY7hYbEvVla7euDWH3m', 'daf', NULL, 'adsf', '232163438@qq.com', '13205063333', 'adf', 'dasf', NULL, NULL, 0, '2020-05-30 01:51:21');
INSERT INTO `sys_user` VALUES (10122, 'zzm', '$2a$10$oJBryJyF0BBym8hZevnu1O2uVWFIKbZhxde6cY0/14/vCgO25U3ja', NULL, 'http://101.200.193.180:9520/files/images/6a849b5238404d0ba177046daf07a486.jpg', NULL, '1610710861@qq.com', NULL, NULL, NULL, NULL, NULL, 0, '2020-05-30 16:28:43');

SET FOREIGN_KEY_CHECKS = 1;
