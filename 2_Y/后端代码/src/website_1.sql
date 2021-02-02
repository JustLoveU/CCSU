/*
 Navicat Premium Data Transfer

 Source Server         : mysql-aliyun
 Source Server Type    : MySQL
 Source Server Version : 50637
 Source Host           : 39.96.3.90:3306
 Source Schema         : website_1

 Target Server Type    : MySQL
 Target Server Version : 50637
 File Encoding         : 65001

 Date: 07/07/2020 17:07:07
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
  `nav` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ischart` int(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `nav_id`(`nav`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 85 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (80, 0, '2020-07-04 04:08:09', '![83F7B9165652A3C56137A11A8B3_2A072FDE_83BE.jpg](/article_img/2020_07_04_12_07_44_83F7B9165652A3C56137A11A8B3_2A072FDE_83BE.jpg)', '我校举办庆祝中国共产党成立99周年暨表彰大会', '/article_img/2020_07_04_12_07_44_83F7B9165652A3C56137A11A8B3_2A072FDE_83BE.jpg', '工作动态', 'http://www.ccsu.cn/info/1322/24256.htm', 1);
INSERT INTO `article` VALUES (81, 6, '2020-07-04 04:09:53', '<div class=\"v_news_content\">\n<p class=\"vsbcontent_start\"><span style=\"font-size: 16px;\">全校各单位：</span></p>\n<p><span style=\"font-size: 16px;\">经学校研究决定，现将今年暑假及下学期开学时间安排通知如下：</span></p>\n<p><span style=\"font-size: 16px;\">1.本学期定于7月11日（星期六）开始放暑假。在履行岗位职责、确保学校正常工作秩序的前提下，全校教职工7月11日起安排轮休。</span></p>\n<p><span style=\"font-size: 16px;\">2.秋季学期在校生定于9月5日（星期六）、6日（星期日）返校报到注册，9月7日（星期一）正式上课，第一周为考试周。2020级新生报到时间另行通知。</span></p>\n<p><span style=\"font-size: 16px;\">疫情防控期间，请广大师生从严落实暑期居家疫情防控要求，减少不必要外出，原则上不前往国外和国内中高风险地区、不跨省域长途旅行，确需离开居住地所在市（州）的，中层以上干部须按有关规定履行报备手续，其他教职工须向二级单位负责人报告，学生须向学生辅导员报告，按学校要求坚持每日健康打卡。</span></p>\n<p><span style=\"font-size: 16px;\">暑期相关具体工作安排将另行通知。</span></p>\n<p style=\"text-align: right;\"><span style=\"font-size: 16px;\">党政办公室 </span></p>\n<p style=\"text-align: right;\"><span style=\"font-size: 16px;\">2020年7月3日</span></p>\n</div>', '关于2020年放暑假及秋季学期开学时间的通知', NULL, '国家政策', NULL, 0);
INSERT INTO `article` VALUES (82, 4, '2020-07-04 04:10:57', '<div class=\"v_news_content\">\n<p class=\"vsbcontent_img\" style=\"text-align: center;\"><img width=\"553\" height=\"369\" src=\"http://www.ccsu.cn/__local/A/B7/30/C3DC03690F8B399EA40E14AA87A_AA948F02_84BA.jpg\" vwidth=\"553\" vheight=\"369\" vurl=\"/_vsl/AB730C3DC03690F8B399EA40E14AA87A/AA948F02/84BA\" vsbhref=\"vurl\" orisrc=\"/__local/A/B7/30/C3DC03690F8B399EA40E14AA87A_AA948F02_84BA.jpg\" class=\"img_vsb_content\"></p>\n<p style=\"text-align: center;\"><span style=\"font-family: 仿宋_gb2312,fangsong_gb2312; font-size: 21px;\">活动现场。通讯员供图</span></p>\n<p><span style=\"font-family: 仿宋_gb2312,fangsong_gb2312; font-size: 21px;\">&nbsp;星辰在线6月28日讯(通讯员汤尚)6月24日，长沙学院第六届“一校一书”阅读推广之“悦读长大·真人图书馆”活动在新图书馆一楼大厅倾情开展，活动主题为“难忘毕业季”。本次活动的两本“真人书”分别是全球孔子学院“先进个人”获得者、长沙学院影视艺术与文化传播学院院长黄柏青教授，和湖南省普通高等学校“教学奉献奖”获得者长沙学院机电工程学院郝诗明副教授，两位老师以人为书，依托腾讯会议平台，以“云分享”的方式给毕业生上“最后一堂课”。</span></p>\n<p><span style=\"font-family: 仿宋_gb2312,fangsong_gb2312; font-size: 21px;\">&nbsp;黄柏青老师分享自己2018年在利比里亚大学孔子学院担任中方院长期间遭遇严重车祸，三天四夜跨国救援终转危为安的惊心动魄经历，表达了“背后有一个强大的祖国”的深情感慨，感受到中国速度背后的中国力量。同学们为黄老师异国他乡的艰苦条件下，不畏艰辛，舍生忘死推广传播中华文化的执着精神和勇敢品质所震撼。通过品读黄老师这本“书”，同学们深感钦佩，表示获益匪浅。“黄老师的分享让我明白，人一定要有理想，要将个人的理想融入祖国发展中;同时，要有英勇无畏的品质，它是实现理想的力量之源。”16播音4班陈芳同学表示。</span></p>\n<p><span style=\"font-family: 仿宋_gb2312,fangsong_gb2312; font-size: 21px;\">&nbsp;郝诗明老师讲述了某初入职场的毕业生因工作条件艰苦想要跳槽，经过自己多次耐心开导后，该毕业生牢记郝老师“干一行、爱一行、钻一行”的教诲，扎根基层，脚踏实地，练就扎实本领，最终在机械行业取得不错成绩的故事。郝老师严慈并举，以实际行动阐释着何为“爱生如子，大爱无私”。“郝老师是我们的‘好老师’，学习上给我们悉心指导;也是我们的‘好妈妈’，生活上给我们无微不至的关心。”16机械一班何泽忠说。</span></p>\n<p><span style=\"font-family: 仿宋_gb2312,fangsong_gb2312; font-size: 21px;\">&nbsp;身在甘肃兰州老家的毕业生杨心怡同学在电脑另一端通过“云分享”参加了真人图书活动，她在评论区留言道“受疫情影响，没能返校参加毕业答辩，心中有些许遗憾。在即将踏入社会之际，能以这种特殊方式品读老师这本好‘书’，很有纪念意义，我将以老师为榜样，写好自己人生这本书。”</span></p>\n<p><span style=\"font-family: 仿宋_gb2312,fangsong_gb2312; font-size: 21px;\"><br></span></p>\n<p><span style=\"font-family: 仿宋_gb2312,fangsong_gb2312; font-size: 21px;\">来源：星辰在线</span></p>\n<p><span style=\"font-family: 仿宋_gb2312,fangsong_gb2312; font-size: 21px;\">原文链接：</span><a href=\"https://app.changsha.cn/html/113239/20200628/83156_m.html\" style=\"font-family: 仿宋_gb2312,fangsong_gb2312; font-size: 14px; text-decoration: underline;\"><span style=\"font-family: 仿宋_gb2312,fangsong_gb2312; font-size: 14px;\">https://app.changsha.cn/html/113239/20200628/83156_m.html</span></a></p>\n<p><br></p>\n<p><span style=\"font-family: 仿宋_gb2312,fangsong_gb2312; font-size: 21px;\">编辑：朱 珊</span></p>\n<p class=\"vsbcontent_end\"><span style=\"font-family: 仿宋_gb2312,fangsong_gb2312; font-size: 21px;\">审核：祝 磊</span></p>\n</div>', '星辰在线：难忘毕业季，长沙学院教师“云分享”为毕业学生上“最后一堂课”', NULL, '学者新论', NULL, 0);
INSERT INTO `article` VALUES (83, 0, '2020-07-04 04:12:29', '', '青春在磨砺中绽放绚丽之花——致长沙学院2020届毕业生的一封信', NULL, '学术活动', 'http://www.ccsu.cn/info/1046/24240.htm', NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of lab_friend
-- ----------------------------
INSERT INTO `lab_friend` VALUES (14, 'http://www.ccsu.cn/', '长沙学院', b'1');
INSERT INTO `lab_friend` VALUES (15, 'http://jyt.hunan.gov.cn/', '湖南省教育厅官网', b'1');

-- ----------------------------
-- Table structure for lab_other
-- ----------------------------
DROP TABLE IF EXISTS `lab_other`;
CREATE TABLE `lab_other`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of lab_other
-- ----------------------------
INSERT INTO `lab_other` VALUES (1, '实验室简介', '这里是简介 可以修改的1');
INSERT INTO `lab_other` VALUES (2, '页脚', '\n                <p style=\"color: #ffffff;\">长沙学院 版权所有2018   地址：长沙市开福区洪山路98号<br>\n                    招生就业热线：0731-84261436  84261447   邮编：410022 <br>\n                </p>\n\nCopyright &copy; Changsha University All rights reserved.乡村振兴研究院 <a href=\"/index\" style=\"color: #ffffff;\">首页</a>');

-- ----------------------------
-- Table structure for lab_profile
-- ----------------------------
DROP TABLE IF EXISTS `lab_profile`;
CREATE TABLE `lab_profile`  (
  `profile_picpath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `profile_id` int(11) NOT NULL AUTO_INCREMENT,
  `profile_intro` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `profile_cnname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `profile_enname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `profile_education` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `profile_position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `profile_telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `profile_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `profile_studydir` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `profile_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `profile_workexp` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `profile_edubackground` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `profile_status` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`profile_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 130 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of lab_profile
-- ----------------------------
INSERT INTO `lab_profile` VALUES ('profile_img/2020_07_04_16_07_04_132A4F30B726EB407FA8A937096_CA39A25A_CD2E.jpg', 126, '湘潭大学硕士研究生导师，长沙市第十二届政协委员。先后主持国家自然科学基金、中国博士后基金、湖南省自然科学基金和湖南省社会科学基金等项目近10项；在《中国物理快报》等发表《Effect of Eliminating Edges on Robustness of Scale-Free Networks under Intentional Attacks》等学术论文近30篇，其中被SCI、EI、CSSCI、CSCD等检索论文23篇；拥有专利1项，软件著作权8个；撰写《物流保障网络级联失效抗毁性研究》等专著5部；成果《网络系统可靠性、抗毁性分析与评价技术》获得军队科技进步二等奖；指导学生参加比赛获得省级奖项6项，其中一等奖2项目，二等奖2项，三等奖2项；指导学生创业，注册成立7家公司；指导学生考研，培养研究生12名，博士3名。', '李勇', NULL, '博士', '副教授', '', '', '舆情传播、控制工程与自动化', '湖南省长沙市开福区洪山路98号', '长沙学院', '哈尔滨工业大学、国防科技大学', b'1');
INSERT INTO `lab_profile` VALUES ('profile_img/2020_07_04_16_16_03_064F776A5D0F03C43A686FFED25_C0AD1067_D18F.png', 127, '', '高丁亚', NULL, '硕士', '助理实验师', '', '617248656@qq.com', '商务策划管理、教育经济与管理', '湖南省长沙市开福区洪山路98号', '长沙学院', '湖南工业大学 、华南师范大学 ', b'1');
INSERT INTO `lab_profile` VALUES ('profile_img/2020_07_04_12_23_15_B20170302421.jpg', 128, '', '唐三', NULL, '', '超人', '', '', '', '', '', '', b'0');
INSERT INTO `lab_profile` VALUES ('profile_img/2020_07_04_12_23_23_B20170302421.jpg', 129, '', '唐四', NULL, '', 'spiderman', '', '', '', '', '', '', b'0');

-- ----------------------------
-- Table structure for lab_user
-- ----------------------------
DROP TABLE IF EXISTS `lab_user`;
CREATE TABLE `lab_user`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `uuid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of lab_user
-- ----------------------------
INSERT INTO `lab_user` VALUES ('CCSU', 'CCSU', 'CCSU', 'CCSU', 1);
INSERT INTO `lab_user` VALUES ('lomtom', '123', '开发者', 'lomtom', 2);
INSERT INTO `lab_user` VALUES ('superadmin', '123', '超级管理员', '超级管理员', 3);

SET FOREIGN_KEY_CHECKS = 1;
