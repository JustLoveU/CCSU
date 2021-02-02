/*
 Navicat Premium Data Transfer

 Source Server         : mysql-aliyun
 Source Server Type    : MySQL
 Source Server Version : 50637
 Source Host           : 39.96.3.90:3306
 Source Schema         : lab

 Target Server Type    : MySQL
 Target Server Version : 50637
 File Encoding         : 65001

 Date: 29/06/2020 13:04:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for nav
-- ----------------------------
DROP TABLE IF EXISTS `nav`;
CREATE TABLE `nav`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` int(11) NULL DEFAULT NULL,
  `type` int(1) NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `parent_id`(`parent_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of nav
-- ----------------------------
INSERT INTO `nav` VALUES (2, '实验室概况', 0, NULL, NULL);
INSERT INTO `nav` VALUES (3, '实验室简介', 2, 1, NULL);
INSERT INTO `nav` VALUES (4, '现任领导', 2, 1, NULL);
INSERT INTO `nav` VALUES (5, '动态新闻', 0, NULL, NULL);
INSERT INTO `nav` VALUES (6, '实验室新闻', 5, 1, NULL);
INSERT INTO `nav` VALUES (7, '通知公告', 5, 1, NULL);
INSERT INTO `nav` VALUES (8, '科学研究', 0, NULL, NULL);
INSERT INTO `nav` VALUES (9, '论文专著', 8, 1, NULL);
INSERT INTO `nav` VALUES (10, '科研项目', 8, 1, NULL);
INSERT INTO `nav` VALUES (11, '研究成果', 8, 1, NULL);
INSERT INTO `nav` VALUES (12, '学术交流', 8, 1, NULL);
INSERT INTO `nav` VALUES (13, '学术梯队', 0, NULL, NULL);
INSERT INTO `nav` VALUES (14, '科研队伍', 13, 1, NULL);
INSERT INTO `nav` VALUES (15, '招聘信息', 13, 1, NULL);
INSERT INTO `nav` VALUES (26, '联系我们', 2, 1, NULL);
INSERT INTO `nav` VALUES (27, '研究生教育', 0, NULL, NULL);
INSERT INTO `nav` VALUES (28, '毕业校友', 27, 1, NULL);
INSERT INTO `nav` VALUES (29, '在校学生', 27, 1, NULL);
INSERT INTO `nav` VALUES (30, '招生信息', 27, 1, NULL);
INSERT INTO `nav` VALUES (32, '组织结构', 2, 1, NULL);

SET FOREIGN_KEY_CHECKS = 1;
