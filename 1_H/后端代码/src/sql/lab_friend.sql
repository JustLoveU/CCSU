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

 Date: 29/06/2020 14:04:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for lab_friend
-- ----------------------------
DROP TABLE IF EXISTS `lab_friend`;
CREATE TABLE `lab_friend`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名字',
  `status` bit(1) NULL DEFAULT NULL COMMENT '是否显示',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
