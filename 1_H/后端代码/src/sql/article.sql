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

 Date: 29/06/2020 12:55:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `view` int(11) NULL DEFAULT NULL,
  `data` datetime(0) NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nav_id` int(11) NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ischart` int(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `nav_id`(`nav_id`) USING BTREE,
  CONSTRAINT `article_ibfk_1` FOREIGN KEY (`nav_id`) REFERENCES `nav` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 65 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
