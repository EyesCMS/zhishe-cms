-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 2020-04-19 11:45:27
-- 服务器版本： 5.7.27-0ubuntu0.16.04.1
-- PHP Version: 7.0.33-0ubuntu0.16.04.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `zhishedb`
--

-- --------------------------------------------------------

--
-- 表的结构 `cms_activity`
--

CREATE TABLE `cms_activity` (
  `id` int(11) NOT NULL,
  `club_id` int(11) DEFAULT NULL COMMENT '社团 id',
  `name` varchar(50) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `body` varchar(255) DEFAULT NULL COMMENT '内容',
  `img_url` varchar(255) DEFAULT NULL COMMENT '图片 url 链接',
  `star_date` datetime DEFAULT NULL COMMENT '开始日期',
  `end_data` datetime DEFAULT NULL COMMENT '结束日期',
  `location` varchar(255) DEFAULT NULL COMMENT '活动地址',
  `member_count` int(11) DEFAULT NULL COMMENT '参与人数',
  `create_at` datetime DEFAULT NULL COMMENT '创建时间',
  `handle_at` datetime DEFAULT NULL COMMENT '处理时间',
  `state` tinyint(1) DEFAULT NULL COMMENT '活动状态: 0 -> “未审核”; 1 -> "审核通过"; 2 -> "已发布"; 3 -> "审核未通过"; 4 -> "已结束"'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='社团活动' ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- 表的结构 `cms_bulletin`
--

CREATE TABLE `cms_bulletin` (
  `id` int(11) NOT NULL,
  `club_id` int(11) DEFAULT NULL COMMENT '社团 id',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `body` varchar(255) DEFAULT NULL COMMENT '内容',
  `create_at` datetime DEFAULT NULL COMMENT '发布时间',
  `update_at` datetime DEFAULT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公告' ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- 表的结构 `cms_chief_change_apply`
--

CREATE TABLE `cms_chief_change_apply` (
  `id` int(11) NOT NULL,
  `club_id` int(11) NOT NULL COMMENT '社团 id',
  `old_chief_id` int(11) NOT NULL COMMENT '旧社长 id',
  `new_chief_id` int(11) DEFAULT NULL COMMENT '新社长 id',
  `reason` varchar(255) DEFAULT NULL COMMENT '换届原因',
  `create_at` datetime DEFAULT NULL COMMENT '创建时间',
  `handle_at` datetime DEFAULT NULL COMMENT '处理时间',
  `state` tinyint(1) DEFAULT NULL COMMENT '申请状态：0 -> 未审核; 1 -> 审核通过; 2 -> 审核未通过;'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='社长换届申请' ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- 表的结构 `cms_club`
--

CREATE TABLE `cms_club` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `chief_id` int(11) DEFAULT NULL COMMENT '社长 id',
  `member_count` int(11) DEFAULT NULL COMMENT '成员数量',
  `qq_group` varchar(10) DEFAULT NULL COMMENT 'QQ 群号码',
  `slogan` varchar(1000) DEFAULT NULL COMMENT '标语',
  `avatar_url` varchar(255) DEFAULT NULL COMMENT '头像链接',
  `official_state` int(1) DEFAULT NULL COMMENT '官方状态: 0 -> 非官方; 1 -> 官方认证;',
  `type` varchar(20) DEFAULT NULL COMMENT '社团类型',
  `grade` int(11) DEFAULT NULL COMMENT '等级',
  `create_at` datetime DEFAULT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='社团' ROW_FORMAT=DYNAMIC;

--
-- 转存表中的数据 `cms_club`
--

INSERT INTO `cms_club` (`id`, `name`, `chief_id`, `member_count`, `qq_group`, `slogan`, `avatar_url`, `official_state`, `type`, `grade`, `create_at`) VALUES
(5000, '足球社', 10086, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(10000, '软件学社', 10086, 1, '10000', NULL, NULL, NULL, NULL, 2017, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `cms_club_create_apply`
--

CREATE TABLE `cms_club_create_apply` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `applicant` varchar(20) DEFAULT NULL COMMENT '申请人',
  `club_name` varchar(50) DEFAULT NULL COMMENT '社团名称',
  `official_state` tinyint(1) DEFAULT NULL COMMENT '官方状态: 0 -> 非正式; 1 -> 正式;',
  `type` varchar(255) DEFAULT NULL COMMENT '社团类别',
  `reason` varchar(255) DEFAULT NULL COMMENT '申请原因',
  `create_at` datetime DEFAULT NULL COMMENT '申请时间',
  `handle_at` datetime DEFAULT NULL COMMENT '处理时间',
  `state` tinyint(1) DEFAULT NULL COMMENT '申请状态：0 -> 未审核; 1 -> 审核通过; 2 -> 审核未通过;'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='社团创建申请' ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- 表的结构 `cms_club_disband_apply`
--

CREATE TABLE `cms_club_disband_apply` (
  `id` int(11) NOT NULL,
  `club_id` int(11) DEFAULT NULL COMMENT '社团 id',
  `reason` varchar(255) DEFAULT NULL COMMENT '申请原因',
  `create_at` datetime DEFAULT NULL COMMENT '创建时间',
  `handle_at` datetime DEFAULT NULL COMMENT '处理时间',
  `state` int(1) DEFAULT NULL COMMENT '申请状态：0 -> 未审核; 1 -> 审核通过; 2 -> 审核未通过;'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='解散社团申请' ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- 表的结构 `cms_club_join_apply`
--

CREATE TABLE `cms_club_join_apply` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL COMMENT '用户 id',
  `club_id` int(11) DEFAULT NULL COMMENT '社团 id',
  `reason` varchar(255) DEFAULT NULL COMMENT '申请原因',
  `create_at` datetime DEFAULT NULL COMMENT '创建时间',
  `handle_at` datetime DEFAULT NULL COMMENT '处理时间',
  `state` tinyint(1) DEFAULT NULL COMMENT '申请状态：0 -> 未审核; 1 -> 审核通过; 2 -> 审核未通过;'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='加入社团申请' ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- 表的结构 `cms_club_label_rel`
--

CREATE TABLE `cms_club_label_rel` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `label_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- 表的结构 `cms_label`
--

CREATE TABLE `cms_label` (
  `id` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL COMMENT '标签值'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='标签' ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- 表的结构 `cms_member_honor`
--

CREATE TABLE `cms_member_honor` (
  `id` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL COMMENT '头衔值',
  `description` varchar(1000) DEFAULT NULL COMMENT '描述'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='成员头衔' ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- 表的结构 `cms_official_change_apply`
--

CREATE TABLE `cms_official_change_apply` (
  `id` int(11) NOT NULL,
  `club_id` int(11) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `handle_at` datetime DEFAULT NULL,
  `state` tinyint(1) DEFAULT NULL COMMENT '申请状态：0 -> 未审核; 1 -> 审核通过; 2 -> 审核未通过;'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='社团认证申请' ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- 表的结构 `cms_quit_notice`
--

CREATE TABLE `cms_quit_notice` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `club_id` int(11) DEFAULT NULL,
  `qiut_date` date DEFAULT NULL COMMENT '退社日期',
  `readon` varchar(1000) DEFAULT NULL COMMENT '原因'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='社员退出通知' ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- 表的结构 `cms_user_activity_remark`
--

CREATE TABLE `cms_user_activity_remark` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `activity_id` int(11) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL COMMENT '内容',
  `create_at` datetime DEFAULT NULL COMMENT '创建时间',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动评论关系' ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- 表的结构 `cms_user_activity_star`
--

CREATE TABLE `cms_user_activity_star` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `activity_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- 表的结构 `cms_user_club_rel`
--

CREATE TABLE `cms_user_club_rel` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `club_id` int(11) DEFAULT NULL,
  `credit` int(11) DEFAULT NULL COMMENT '积分',
  `honor_id` int(11) DEFAULT NULL COMMENT '用户头衔 id',
  `join_date` datetime DEFAULT NULL COMMENT '加入时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户社团关系' ROW_FORMAT=DYNAMIC;

--
-- 转存表中的数据 `cms_user_club_rel`
--

INSERT INTO `cms_user_club_rel` (`id`, `user_id`, `club_id`, `credit`, `honor_id`, `join_date`) VALUES
(1, 10086, 10000, NULL, NULL, NULL),
(2, 10086, 5000, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `cms_user_label`
--

CREATE TABLE `cms_user_label` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `label_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户标签关系' ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- 表的结构 `sys_permission`
--

CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL,
  `menu_code` varchar(50) DEFAULT NULL COMMENT '菜单编码',
  `menu_name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `permission_code` varchar(50) DEFAULT NULL COMMENT '权限编码',
  `permission_name` varchar(50) DEFAULT NULL COMMENT '权限名称',
  `required_permission` int(11) DEFAULT NULL COMMENT '必要状态: 0 -> 非必须; 1 -> 必须;'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限' ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- 表的结构 `sys_role`
--

CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `description` varchar(1000) DEFAULT NULL COMMENT '描述'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色' ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- 表的结构 `sys_role_permission_rel`
--

CREATE TABLE `sys_role_permission_rel` (
  `id` int(11) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `permission_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关系' ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- 表的结构 `sys_user`
--

CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL,
  `username` varchar(20) DEFAULT NULL COMMENT '用户名',
  `password` varchar(15) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(20) DEFAULT NULL COMMENT '昵称',
  `avatar_url` varchar(100) DEFAULT NULL COMMENT '头像链接',
  `major` varchar(20) DEFAULT NULL COMMENT '专业',
  `email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) DEFAULT NULL COMMENT '电话',
  `slogan` varchar(1000) DEFAULT NULL COMMENT '标语',
  `login_question` varchar(255) DEFAULT NULL COMMENT '密保问题',
  `login_answer` varchar(255) DEFAULT NULL COMMENT '密保答案',
  `is_admin` tinyint(1) NOT NULL DEFAULT '0' COMMENT '管理状态：0 -> 普通用户；1 -> 管理员；'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户' ROW_FORMAT=DYNAMIC;

--
-- 转存表中的数据 `sys_user`
--

INSERT INTO `sys_user` (`id`, `username`, `password`, `nickname`, `avatar_url`, `major`, `email`, `phone`, `slogan`, `login_question`, `login_answer`, `is_admin`) VALUES
(10086, '彭彭彭', '123456', 'ppp', 'www', 'cs', '522@qq.com', '10086', NULL, NULL, NULL, 0);

-- --------------------------------------------------------

--
-- 表的结构 `sys_user_role_rel`
--

CREATE TABLE `sys_user_role_rel` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `club_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关系' ROW_FORMAT=DYNAMIC;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cms_activity`
--
ALTER TABLE `cms_activity`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `club_id` (`club_id`) USING BTREE,
  ADD KEY `state_id` (`state`) USING BTREE;

--
-- Indexes for table `cms_bulletin`
--
ALTER TABLE `cms_bulletin`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `club_id` (`club_id`) USING BTREE;

--
-- Indexes for table `cms_chief_change_apply`
--
ALTER TABLE `cms_chief_change_apply`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `cms_chief_change_apply_ibfk_1` (`club_id`) USING BTREE,
  ADD KEY `cms_chief_change_apply_ibfk_2` (`new_chief_id`) USING BTREE;

--
-- Indexes for table `cms_club`
--
ALTER TABLE `cms_club`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `chief_id` (`chief_id`) USING BTREE;

--
-- Indexes for table `cms_club_create_apply`
--
ALTER TABLE `cms_club_create_apply`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `cms_club_create_apply_ibfk_1` (`user_id`) USING BTREE;

--
-- Indexes for table `cms_club_disband_apply`
--
ALTER TABLE `cms_club_disband_apply`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `club_id` (`club_id`) USING BTREE;

--
-- Indexes for table `cms_club_join_apply`
--
ALTER TABLE `cms_club_join_apply`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `cms_club_join_apply_ibfk_1` (`user_id`) USING BTREE,
  ADD KEY `cms_club_join_apply_ibfk_2` (`club_id`) USING BTREE;

--
-- Indexes for table `cms_club_label_rel`
--
ALTER TABLE `cms_club_label_rel`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `user_id` (`user_id`) USING BTREE,
  ADD KEY `label_id` (`label_id`) USING BTREE;

--
-- Indexes for table `cms_label`
--
ALTER TABLE `cms_label`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `cms_member_honor`
--
ALTER TABLE `cms_member_honor`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `cms_official_change_apply`
--
ALTER TABLE `cms_official_change_apply`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `cms_official_change_apply_ibfk_1` (`club_id`) USING BTREE;

--
-- Indexes for table `cms_quit_notice`
--
ALTER TABLE `cms_quit_notice`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `user_id` (`user_id`) USING BTREE,
  ADD KEY `club_id` (`club_id`) USING BTREE;

--
-- Indexes for table `cms_user_activity_remark`
--
ALTER TABLE `cms_user_activity_remark`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `user_id` (`user_id`) USING BTREE,
  ADD KEY `activity_id` (`activity_id`) USING BTREE;

--
-- Indexes for table `cms_user_activity_star`
--
ALTER TABLE `cms_user_activity_star`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `user_id` (`user_id`) USING BTREE,
  ADD KEY `activity_id` (`activity_id`) USING BTREE;

--
-- Indexes for table `cms_user_club_rel`
--
ALTER TABLE `cms_user_club_rel`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `user_id` (`user_id`) USING BTREE,
  ADD KEY `club_id` (`club_id`) USING BTREE,
  ADD KEY `honor_id` (`honor_id`) USING BTREE;

--
-- Indexes for table `cms_user_label`
--
ALTER TABLE `cms_user_label`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `user_id` (`user_id`) USING BTREE,
  ADD KEY `label_id` (`label_id`) USING BTREE;

--
-- Indexes for table `sys_permission`
--
ALTER TABLE `sys_permission`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `sys_role`
--
ALTER TABLE `sys_role`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `sys_role_permission_rel`
--
ALTER TABLE `sys_role_permission_rel`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `role_id` (`role_id`) USING BTREE,
  ADD KEY `permission_id` (`permission_id`) USING BTREE;

--
-- Indexes for table `sys_user`
--
ALTER TABLE `sys_user`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `sys_user_role_rel`
--
ALTER TABLE `sys_user_role_rel`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `user_id` (`user_id`) USING BTREE,
  ADD KEY `role_id` (`role_id`) USING BTREE,
  ADD KEY `club_id` (`club_id`) USING BTREE;

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `cms_activity`
--
ALTER TABLE `cms_activity`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `cms_bulletin`
--
ALTER TABLE `cms_bulletin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `cms_chief_change_apply`
--
ALTER TABLE `cms_chief_change_apply`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `cms_club`
--
ALTER TABLE `cms_club`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10001;
--
-- 使用表AUTO_INCREMENT `cms_club_create_apply`
--
ALTER TABLE `cms_club_create_apply`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `cms_club_disband_apply`
--
ALTER TABLE `cms_club_disband_apply`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `cms_club_join_apply`
--
ALTER TABLE `cms_club_join_apply`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `cms_club_label_rel`
--
ALTER TABLE `cms_club_label_rel`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `cms_label`
--
ALTER TABLE `cms_label`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `cms_member_honor`
--
ALTER TABLE `cms_member_honor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `cms_official_change_apply`
--
ALTER TABLE `cms_official_change_apply`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `cms_quit_notice`
--
ALTER TABLE `cms_quit_notice`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `cms_user_activity_remark`
--
ALTER TABLE `cms_user_activity_remark`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `cms_user_activity_star`
--
ALTER TABLE `cms_user_activity_star`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `cms_user_club_rel`
--
ALTER TABLE `cms_user_club_rel`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- 使用表AUTO_INCREMENT `cms_user_label`
--
ALTER TABLE `cms_user_label`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `sys_permission`
--
ALTER TABLE `sys_permission`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `sys_role`
--
ALTER TABLE `sys_role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `sys_role_permission_rel`
--
ALTER TABLE `sys_role_permission_rel`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `sys_user`
--
ALTER TABLE `sys_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10087;
--
-- 使用表AUTO_INCREMENT `sys_user_role_rel`
--
ALTER TABLE `sys_user_role_rel`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- 限制导出的表
--

--
-- 限制表 `cms_activity`
--
ALTER TABLE `cms_activity`
  ADD CONSTRAINT `cms_activity_ibfk_1` FOREIGN KEY (`club_id`) REFERENCES `cms_club` (`id`);

--
-- 限制表 `cms_bulletin`
--
ALTER TABLE `cms_bulletin`
  ADD CONSTRAINT `cms_bulletin_ibfk_1` FOREIGN KEY (`club_id`) REFERENCES `cms_club` (`id`);

--
-- 限制表 `cms_chief_change_apply`
--
ALTER TABLE `cms_chief_change_apply`
  ADD CONSTRAINT `cms_chief_change_apply_ibfk_1` FOREIGN KEY (`club_id`) REFERENCES `cms_club` (`id`),
  ADD CONSTRAINT `cms_chief_change_apply_ibfk_2` FOREIGN KEY (`new_chief_id`) REFERENCES `sys_user` (`id`);

--
-- 限制表 `cms_club`
--
ALTER TABLE `cms_club`
  ADD CONSTRAINT `cms_club_ibfk_1` FOREIGN KEY (`chief_id`) REFERENCES `sys_user` (`id`),
  ADD CONSTRAINT `cms_club_ibfk_2` FOREIGN KEY (`chief_id`) REFERENCES `sys_user` (`id`);

--
-- 限制表 `cms_club_create_apply`
--
ALTER TABLE `cms_club_create_apply`
  ADD CONSTRAINT `cms_club_create_apply_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`);

--
-- 限制表 `cms_club_disband_apply`
--
ALTER TABLE `cms_club_disband_apply`
  ADD CONSTRAINT `cms_club_disband_apply_ibfk_2` FOREIGN KEY (`club_id`) REFERENCES `cms_club` (`id`);

--
-- 限制表 `cms_club_join_apply`
--
ALTER TABLE `cms_club_join_apply`
  ADD CONSTRAINT `cms_club_join_apply_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  ADD CONSTRAINT `cms_club_join_apply_ibfk_2` FOREIGN KEY (`club_id`) REFERENCES `cms_club` (`id`);

--
-- 限制表 `cms_club_label_rel`
--
ALTER TABLE `cms_club_label_rel`
  ADD CONSTRAINT `cms_club_label_rel_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `cms_club` (`id`),
  ADD CONSTRAINT `cms_club_label_rel_ibfk_2` FOREIGN KEY (`label_id`) REFERENCES `cms_label` (`id`);

--
-- 限制表 `cms_official_change_apply`
--
ALTER TABLE `cms_official_change_apply`
  ADD CONSTRAINT `cms_official_change_apply_ibfk_1` FOREIGN KEY (`club_id`) REFERENCES `cms_club` (`id`);

--
-- 限制表 `cms_quit_notice`
--
ALTER TABLE `cms_quit_notice`
  ADD CONSTRAINT `cms_quit_notice_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  ADD CONSTRAINT `cms_quit_notice_ibfk_2` FOREIGN KEY (`club_id`) REFERENCES `cms_club` (`id`);

--
-- 限制表 `cms_user_activity_remark`
--
ALTER TABLE `cms_user_activity_remark`
  ADD CONSTRAINT `cms_user_activity_remark_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  ADD CONSTRAINT `cms_user_activity_remark_ibfk_2` FOREIGN KEY (`activity_id`) REFERENCES `cms_activity` (`id`);

--
-- 限制表 `cms_user_activity_star`
--
ALTER TABLE `cms_user_activity_star`
  ADD CONSTRAINT `cms_user_activity_star_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  ADD CONSTRAINT `cms_user_activity_star_ibfk_2` FOREIGN KEY (`activity_id`) REFERENCES `cms_activity` (`id`);

--
-- 限制表 `cms_user_club_rel`
--
ALTER TABLE `cms_user_club_rel`
  ADD CONSTRAINT `cms_user_club_rel_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  ADD CONSTRAINT `cms_user_club_rel_ibfk_2` FOREIGN KEY (`club_id`) REFERENCES `cms_club` (`id`),
  ADD CONSTRAINT `cms_user_club_rel_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  ADD CONSTRAINT `cms_user_club_rel_ibfk_4` FOREIGN KEY (`club_id`) REFERENCES `cms_club` (`id`),
  ADD CONSTRAINT `cms_user_club_rel_ibfk_5` FOREIGN KEY (`honor_id`) REFERENCES `cms_member_honor` (`id`);

--
-- 限制表 `cms_user_label`
--
ALTER TABLE `cms_user_label`
  ADD CONSTRAINT `cms_user_label_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  ADD CONSTRAINT `cms_user_label_ibfk_2` FOREIGN KEY (`label_id`) REFERENCES `cms_label` (`id`);

--
-- 限制表 `sys_role_permission_rel`
--
ALTER TABLE `sys_role_permission_rel`
  ADD CONSTRAINT `sys_role_permission_rel_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`),
  ADD CONSTRAINT `sys_role_permission_rel_ibfk_2` FOREIGN KEY (`permission_id`) REFERENCES `sys_permission` (`id`);

--
-- 限制表 `sys_user_role_rel`
--
ALTER TABLE `sys_user_role_rel`
  ADD CONSTRAINT `sys_user_role_rel_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  ADD CONSTRAINT `sys_user_role_rel_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`),
  ADD CONSTRAINT `sys_user_role_rel_ibfk_3` FOREIGN KEY (`club_id`) REFERENCES `cms_club` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
