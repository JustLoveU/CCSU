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

 Date: 29/06/2020 12:57:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
  PRIMARY KEY (`profile_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 125 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of lab_profile
-- ----------------------------
INSERT INTO `lab_profile` VALUES ('images/likeqin.jpg', 1, '李克勤，IEEE Fellow、湖南大学信息科学与工程学院教授（岳麓学者领军岗）、博士生导师、院学术委员会主席、超级计算与云计算研究所所长。目前研究课题集中在云计算、雾计算和移动边缘计算、高能效计算和通信、物联网和信息物理系统、异构计算系统、大数据计算、高性能计算、CPU-GPU混合协同计算、计算机系统结构、计算机网络、机器学习、智能计算等。在学术著作、研究期刊、和国际会议上共发表论文660篇以上（其中IEEE/ACM Transactions/Journals论文超过170篇，SCI期刊论文超过410篇）。他与人合著的最新学术著作Scheduling Parallel Applications on Heterogeneous Distributed Systems（异构分布式系统中的并行应用调度）由国际顶尖的斯普林格（Springer）学术出版社于2019年出版。他曾多次荣获国际学术会议最佳论文奖。他多次担任国际学术会议主席，并且担任2017年召开的第7届IEEE国际云计算和服务计算年会和2019年召开的第21届IEEE高性能计算和通信会议的主席。他主持国家自然科学基金委员会的重点项目“面向激光聚变模拟的大规模异构众核系统可扩展并行算法与优化方法”；主持国家自然科学基金委和新加坡国家研究基金会数据科学合作研究项目“面向列车故障检测的深度学习模型及其异构并行处理技术”；承担科技部863重大项目“高性能计算环境应用服务优化关键技术”和“基于内存计算的并行处理系统研究与开发”；并且主持科技部国家重点研发计划“面向异构体系结构的高性能分布式数据处理技术与系统” 的子项目“面向大数据集的数据流编程模型及高通量视频示范应用”；同时在“大规模异构并行系统的高效能调度理论与方法”领域带领湖南省自然科学基金创新研究群体。他是IEEE Transactions on Parallel and Distributed Systems, IEEE Transactions on Computers, IEEE Transactions on Cloud Computing, IEEE Transactions on Services Computing, IEEE Transactions on Sustainable Computing等著名和顶级学术刊物的编委。', '李克勤', NULL, '博士', '教授', NULL, 'likq@hnu.edu.cn, lkl@hnu.edu.cn', '云计算、雾计算和移动边缘计算、高能效计算和通信、物联网和信息物理系统、异构计算系统、大数据计算、高性能计算、CPU-GPU混合协同计算、计算机系统结构、计算机网络、机器学习、智能计算', '湖南省长沙市岳麓区麓山南路2号,湖南大学信息科学与工程学院（410082）', NULL, NULL);
INSERT INTO `lab_profile` VALUES ('images/likenli.png', 2, '李肯立，湖南大学信息科学与工程学院院长，国家超级计算长沙中心主任，教育部``长江学者\'\'特聘教授、国家杰出青年科学基金获得者、中组部``万人计划\'\'科技创新领军人才、科技部创新人才推进计划入选者。教育部高效能计算学科创新引智基地负责人、高性能计算应用软件教育部工程中心主任、数据分析湖南省工程技术研究中心主任。担任国家超级计算创新联盟副理事长、新一代人工智能产业技术创新联盟专家委员会委员、IEEE高级会员、CCF理事、CCF杰出会员、CCF高性能计算专业委员会常务委员、湖南省计算机学会秘书长等学术兼职，IEEE-TC编委，IEEE-TII等特刊编委。作为项目负责人主持国家重点研发计划项目、国家自然科学基金重点项目等国家和省部级项目40余项。作为主要成员获得国家科技进步一等奖(创新团队)，以排名第一获得国家科技进步二等奖、湖南省专利金奖一等奖、湖南省技术发明一等奖、湖南省科技进步一等奖、湖南省自然科学奖二等奖、教育部科技进步二等奖和湖南省教学成果二等奖等。主要研究领域为并行与分布式处理、超级计算与云计算、面向大数据和人工智能的高效能计算等。在IEEE-TC、IEEE-TPDS、AAAI、DAC等国内外高水平计算机学术刊物和会议上发表论文300余篇，其中IEEE/ACM汇刊及CCF推荐A类会议论文80余篇。获授权专利和软件著作权共15项。', '李肯立', NULL, '博士', '教授', '13036781568', 'lkl@hnu.edu.cn; lkl510@263.net', '并行分布式处理、超级计算与云计算、面向大数据和人工智能的高效能计算', '湖南省长沙市岳麓区麓山南路2号,湖南大学信息科学与工程学院（410082）', '现为湖南大学信息科学与工程学院院长、湖南大学国家超级计算长沙中心主任、教授、博士生导师', '1997-2000,中南大学应用数学与应用软件系，攻读应用数学硕士学位\r\n\r\n2000-2003，华中科技大学计算机学院，攻读计算机软件与理论博士学位');
INSERT INTO `lab_profile` VALUES ('images/tangzhuo.png', 3, '湖南大学信息科学与工程学院教授，博士生导师。湖南大学超级计算与云计算副所长，教育部-广东省省部科技特派员，湖南省青少年信息学奥利匹克竞赛委员会副主任委员，长沙市大数据产业链科技特派员，OpenStack云计算开源社区CoreMember。 ', '唐卓', NULL, '博士', '教授', NULL, NULL, '大数据并行处理体系结构，分布式机器学习。专注于资源虚拟化池，OpenStack私有云体系结构，基于MapReduce和Spark的机器学习算法的并行化，面向数据特征的Spark和Hadoop任务调度和体系结构优化', '湖南省长沙市岳麓区麓山南路2号,湖南大学信息科学与工程学院（410082）', '在IEEE TIFS、IEEE TCC、IEEE TPDS、FGCS、IPDPS等国内外重要刊物和会议上发表论文80余篇。研发了Lucloud云资源管理软件和CloudMining大数据并行处理与分析平台，已成功应用于智能制造、医疗、金融等应用领域，提供数据存储、分析和挖掘服务。是全国智能制造示范工程：东莞劲胜智能工厂大数据平台的主要设计者和实现者。曾带领团队在OpenStack R版BP贡献中取得全球第九，国内第三的成绩。\r\n\r\n担任多个SCI期刊的客座编辑，先后主持科技部重点研发计划（课题1项、任务1项）、国家自然科学基金青年项目（1项）、面上项目（2项）、国家自然科学基金应急项目（2项）、广东省经信委项目、产学研合作项目、中国博士后科学基金等国家及其他省部级课题十余项，获湖南省技术发明一等奖（第二），教育部科技进步二等奖及湖南省自然科学奖，湖南省优秀博士后奖各一项。\r\n', NULL);
INSERT INTO `lab_profile` VALUES ('images/yangwangdong.png', 4, '长期致力于基于加速器的并行算法设计和实现研究，为天河一号、二号、神威太湖之光等国产超级计算机开发基础函数库。另外利用加速器对智能交通的视频数据进行加速处理技术进行了深入研究，并在英伟达GPU和华为AI智能芯片研发高通量和多通道交通视频处理技术，所研究的混合交通视频处理技术获得湖南省科技进步奖。发表SCI/EI检索论文40多篇，其中CCF推荐的A类期刊5篇。主持国家项目3项，省部级项目10项。 ', '杨王东', NULL, '博士', '教授', '13973741859', 'yangwd@hnu.edu.cn,yangwangdong@163.com', '高性能计算，异构并行编程，并行数值算法，机器学习', NULL, NULL, NULL);
INSERT INTO `lab_profile` VALUES ('images/quanzhe.png', 5, '2003年本科毕业于西北工业大学，2006年硕士毕业于浙江大学，2010年博士毕业于法国巴黎六大计算机及应用专业。2010-2011年在美国加州大学BerkeleyLab师从GregBronevetsky教授进行博士后工作；2011-2012年在LivermoreLab担任助理研究员；2012-2014年在国防科技大学师从廖湘科院士从事“天河2号”超级计算机的博士后研究工作；2014-2016年在国防科技大学担任助理研究员；2016年8月起在湖南大学信息科学与工程学院担任助理教授、博士生导师。目前主持国家自然科学基金项目1项，国家重点研发计划2项。\r\n热烈欢迎各位优秀人士报考湖南大学信息科学与工程学院博士和硕士研究生，请联系电子邮箱quanzhe@hnu.edu.cn。', '全哲', NULL, '博士', '副教授', NULL, 'quanzhe@hnu.edu.cn。', '高性能计算、机器学习、算法设计', '湖南大学信息科学与工程学院软件大楼329室', NULL, NULL);
INSERT INTO `lab_profile` VALUES ('images/liuchubo.png', 6, '刘楚波，男，副教授。2016年6月博士毕业于湖南大学。2016年7月至2018年9月在湖南大学从事博士后研究工作。研究方向涉及云计算、边缘计算、博弈论、近似算法与随机算法等。已在国际顶级刊物IEEE-TPDS、IEEE-TCC、IEEE-TMC、ACM-TOMPECS等发表论文10余篇，其中CCFA类论文5篇。同时担任IEEE-TPDS、IEEE-TCC、IEEE-TII等国际期刊审稿人。主持国家自然科学青年基金1项、作为课题骨干参与国家重点研发计划2项。 ', '刘楚波', NULL, '博士', '副教授', NULL, 'liuchubo@hnu.edu.cn', '云计算、边缘计算、博弈论', '湖南省长沙市岳麓区麓山南路2号,湖南大学信息科学与工程学院312室（410082）', NULL, NULL);
INSERT INTO `lab_profile` VALUES ('images/zhouxu.png', 7, '周旭，博士，副教授。2016年博士毕业于湖南大学。2016年7月至2018年12月在湖南大学从事博士后研究工作。目前研究方向为并行计算、数据管理、图计算等。近5年在国内外重要期刊发表相关领域学术论文20余篇。主持国家级项目2项，省部级项目2项，参与国家级项目4项。 ', '周旭', NULL, '博士', '副教授', '15773146964', 'zhxu@hnu.edu.cn', '并行计算、数据管理、图计算等', '湖南省长沙市岳麓区麓山南路2号,湖南大学信息科学与工程学院312室（410082）', NULL, NULL);
INSERT INTO `lab_profile` VALUES ('images/tianzhean.jpg', 8, NULL, '田泽安', NULL, '博士', '副教授', '13974843579', 'tianzean@126.com', '高性能计算、数据可视化、大数据分析', '湖南省长沙市岳麓区麓山南路2号,湖南大学信息科学与工程学院（410082）', NULL, NULL);
INSERT INTO `lab_profile` VALUES ('images/zhuningbo.jpg', 9, '学历:博士\r\n专业:模式识别与智能系统\r\n毕业院校: 南京理工大学\r\nqq联系方式:524567542\r\nE-mail: quietwave@hnu.edu.cn\r\n\r\n★专业技能\r\n\r\n?系统分析与系统集成、开发;\r\n?精通VC++,熟练掌握java，C,C++,Delphi和Lotusnotes编程技巧;\r\n?数字图像处理与智能系统的编程\r\n?计算机网络的编程与应用;\r\n?大中型计算机的应用与软件开发如IBMAS/400,RS/6000,ES/9000,容错Stratus等。', '朱宁波', NULL, '博士', '副教授', NULL, ' quietwave@hnu.edu.cn', '模式识别与智能系统，数字图像处理、网络与信息安全', '湖南省长沙市岳麓区麓山南路2号,湖南大学信息科学与工程学院（410082）', '1994.7-1995.8 燕兴中南公司(长沙)\r\n\r\n1995.9-1997.8 日本理光(RICHO) 深圳有限公司，电脑工程师\r\n\r\n1997.9-1999.8 深圳优索电子有限公司，系统分析员、项目经理\r\n\r\n2004.7-至今   湖南大学信息科学与工程学院(期间2010.10-2011.10 访问学者   英国  Surrey University    信息安全)。\r\n', '1990.9-1994.6 南京理工大学计算机及应用专业，本科毕业\r\n\r\n1999.9-2005.1 南京理工大学 模式识别与智能系统专业，硕士、博士研究生（硕博连读）\r\n');
INSERT INTO `lab_profile` VALUES ('images/yangjianye.jpeg', 10, '杨建业，男，1987年生，副教授，岳麓学者。2010年和2013年先后获西安电子科技大学学士和硕士学位，2017年获得澳大利亚新南威尔士大学博士学位。2017年8月-2019年6月，曾先后任职于华为与阿里巴巴集团。2019年6月，湖南大学人才引进（副教授职称）加入信息科学与工程学院。\r\n\r\n本人研究方向包括文本数据分析，图数据分析，大数据分析，数据挖掘等，近年来在数据库及数据挖掘领域顶级期刊和会议上发表10余篇论文，包括VLDBJ,ICDE,TKDE,KAIS,WWWJ，其中中国计算机学会推荐（CCF）A类期刊和会议论文5篇，B类期刊论文2篇。担任TKDE,ICDE,ICDM,AAAI,KAIS,CIKM等多个国际权威期刊和会议审稿人，并受邀担任CIKM和PAKDD等国际会议的PCmember。 ', '杨建业', NULL, '博士', '副教授', '13823358708', 'jyyang@hnu.edu.cn', '文本数据分析，图数据分析，大数据分析，数据挖掘', '湖南省长沙市岳麓区麓山南路2号,湖南大学信息科学与工程学院（410082）', NULL, NULL);
INSERT INTO `lab_profile` VALUES ('images/wufan.jpg', 11, '', '吴凡', NULL, '博士', '助理教授', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `lab_profile` VALUES ('images/tanguanghua.jpg', 12, '谭光华，男，博士，助理教授。主要从事CAD&CG，虚拟现实，计算机动画，计算机视觉，数字图像处理方面的研究。', '谭光华', NULL, '博士', '助理教授', NULL, NULL, 'CAD&CG，虚拟现实，计算机动画，计算机视觉，数字图像', '湖南省长沙市岳麓区麓山南路2号,湖南大学信息科学与工程学院（410082）', '2004.10-2008.10，Solikworks中国研发中心，程序员（兼职）\r\n2009.7-2009.9，百度中国科技有限公司，高级程序员\r\n2009.9-至今，湖南大学信息科学与工程学院，讲师', '2000.9-2004.7，湖南大学计算机与通信学院，本科\r\n2004.9-2009.6，浙江大学计算机科学与技术学院，硕博连读');
INSERT INTO `lab_profile` VALUES ('images/duanmingxing.jpg', 13, '博士毕业于国防科技大学计算机学院，目前在湖南大学信息科学与工程学院从事博士后相关工作。获得全国互联网+大赛银奖，国防科技大学光华奖学金（每个年级评10%，国防科技大学地方博士能获得的最高奖学金），一等助学金（国防科技大学计算机学院2017级地方博士唯一一名）。主持国家级项目3项（国家青年基金，博后面上、站前特等资助），湖南省研究生创新项目1项，2011协同创新项目1项。期刊American Journal of Neural Networks and Applications副编辑，AAAI 19,20，CVPR19会议PC menmbers, IEEE Transactions on Neural Networks and Learning Systems，IEEE Transactions on Multimedia，IEEE Transactions on Systems, Man, and Cybernetics: Systems, IEEE Transactions on Industrial Informatics, ACM Transactions on Multimedia Computing, Communications, and Applications , Computer Vision and Image Understanding - Journal - Elsevier, Neurocomputing, Journal of Computer and System Sciences - Elsevier, International Journal of Pattern Recognition and Artificial Intelligence常用审稿人。', '段明星', NULL, '博士', '博士后', '15773181305', 'duanmingxing@hnu.edu.cn', '深度学习：对张量，图深度学习，GAN，以及算法并行加速有较深兴趣，如condition GAN、Deep Convolution Generative Adversarial Networks、CycleGAN、pix2pix，GraphGAN，TensorGAN等。', '湖南大学信息科学与工程学院', '	2014.06-2015.12 地点：湖南长沙, 单位：国家超算长沙中心，职位：编程工程师，工作责任: （1）数据库数据预处理、清洗；（2）算法设计与优化；（3）设计算法系统部署和APP的应用。 	2019.02-现在。湖南大学博士后，地点：湖南大学信息科学与工程学院 ', '	学校名称：湖南大学 所学专业：计算机科学与技术 博士后 就读时间：2019.02-至今 	学校名称：国防科技大学 所学专业：计算机科学与技术 博士 就读时间：2016.01-2019.01 ');
INSERT INTO `lab_profile` VALUES ('images/huyouzuan.jpg', 36, '胡由钻，江西南昌市人。出生日期1995年10月17号。喜欢尝试新鲜事物，为人真诚友善。', '胡由钻', NULL, '硕士', NULL, '13307083605', '1591960080@qq.com', '大数据与云计算', '湖南省长沙市岳麓区湖南大学天马学生公寓1区3栋', '2019.2-至今 湖南匡安网络有限公司', '2013.9-2017.7 天津大学 船舶与海洋工程 本科   2018.9-至今 湖南大学 计算机技术 研究生');
INSERT INTO `lab_profile` VALUES ('images/caiqinyun.jpg', 50, '为团队队长入围 2017 年第六届中国创业创新大赛（厦门赛区）/ 获得第三届“白鹭之星”创新创业大赛优秀奖 \r\n学习能力强，对计算机知识抱有极大的兴趣与热情。\r\n动手能力强：有一定的社会经验和工作经验与机械行业背景，吃苦耐劳执行力好。\r\n', '蔡沁耘', 'Tsai Qinyun', '硕士', NULL, '13873061506', 'hnutsai@hnu.edu.cn', '高性能计算', '湖南大学天马学生公寓1区3栋441', '2014.9 - 2017.12 厦门厦工机械股份有限公司 工程技术研究院  动力工程师', '2010.9-2014.7	 哈尔滨工业大学	热能与动力工程专业 本科 学士学位');
INSERT INTO `lab_profile` VALUES ('images/zhangchen.png', 51, '山东人，性格豪爽，喜欢交朋友，做事认真负责，懂得团队合作', '张琛', '', '博士', NULL, '15347053707', 'zhangchen2@hnu.edu.cn', '机器学习，深度学习，自然语言处理，对抗样本', '湖南省长沙市岳麓区麓山南路252号国家超级计算长沙中心', '2013.4-2016.4 武汉建筑材料工业设计研究院有限公司 机械工程师', '2008.9-2012.6 西北工业大学 材料学院 材料成形及控制工程\r\n		  2016.9-2018.6 华中科技大学 机械科学与工程学院  机械工程\r\n 		  2018.9-至今   湖南大学     信息科学与工程学院   计算机科学与技术\r\n');
INSERT INTO `lab_profile` VALUES ('images/jiangbingting.jpg', 52, '本人有良好的数据与竞争对手分析能力，出色的互联网思维和表达能力，文档写作能力和产品原型表现能力；能够运用Axure等工具进行原型设计和项目管理。在大数据和云计算和高性能计算方向有一定的研究基础，在服务计算方向有自己的研究创新点。熟练Java编程语言，深入理解面向对象思想。熟练测试流程，能很好的使用动态测试和静态测试等等常用测试方法。熟悉常见的数据结构和算法， 熟悉c和Python', '蒋冰婷', NULL, '博士', NULL, '15673170412', '583955064@qq.com', '高性能计算、云计算', '湖南省长沙市国家超级计算长沙中心圆楼302', '2018.08-2019.05  湖南信息学院 （二本）   任软件工程专职教师', '2019.09-至今   湖南大学       计算机科学与技术       2019级博士研究生\r\n2015.09-2018.06 中南大学        软件工程               学术型硕士研究生\r\n2011.09-2015.06 湖南商学院      软件工程               大学本科\r\n');
INSERT INTO `lab_profile` VALUES ('images/chenzailong.jpg', 53, '陈再龙，男，93年生，汉族，籍贯贵州省黔南州，团员，目前研究生一年级，计算机技术专硕；我性格外向，为人坦率、热情、讲求原则；处事乐观、专心、细致、头脑清醒；富有责任心、乐于助人。', '陈再龙', NULL, '硕士', NULL, '18351452083', '18351452083@163.com', '深度学习', '湖南省长沙市岳麓区桔子洲街道阜埠河路221号湖南大学天马学生公寓3区10栋211', '2015.7—2018.1：工作于江苏省邮电规划设计院。', '2011.9—2015.6：就读于南京邮电大学，光信息科学与技术专业。');
INSERT INTO `lab_profile` VALUES ('images/dengshiqiao.jpg', 54, '学习成绩： GPA 3.656/4.0，平均成绩 86.93/100，排名 2/115；（本科）\r\n英语水平：CET 4；\r\n计算机等级考试：二级 C 优秀，三级嵌入式合格；\r\n参与省级创新项目一项——《基于 kinect 的智能陪护机器人设计与制作》\r\n竞赛经历：\r\n2017 年第七届“华为杯”中国大学生智能设计竞赛三等奖（排名第 3）\r\n2018 年“赛佰特杯”全国大学生智能互联创新应用设计大赛华中赛区二等奖（排名第 1）\r\n2018 年第十四届“博创杯”全国大学生嵌入式设计大赛分赛区二等奖（排名第 2）\r\n2016 年“北斗杯”湘潭大学电子设计竞赛二等奖（排名第 1）\r\n', '邓石桥', NULL, '硕士', NULL, '17369278523	', 'shiqiaodeng@gmail.com', '深度学习加并行计算', '	湖南省长沙市湖南大学信息科学与工程学院447', NULL, '2015.09-2019.06 湘潭大学 电子信息工程      本科毕业\r\n2019.09-至今    湖南大学 计算机科学与技术  硕士在读\r\n');
INSERT INTO `lab_profile` VALUES ('images/fupenglei.png', 55, '付鹏磊，生于1997年，籍贯河南许昌，2019级硕士生，硕士期间的研究方向为工控安全，目前在工控项目中主要负责管理平台与防火墙的数据通信工作。', '付鹏磊', NULL, '硕士', NULL, '18236538301', '18236538301@163.com', '工控安全', '湖南大学天马学生公寓三区十栋211', NULL, '2015.9-2019.7 就读于河南大学软件学院网络工程专业.');
INSERT INTO `lab_profile` VALUES ('images/wulu.jpg', 56, '本科毕业于南京邮电大学，参与过大学生数学建模国赛和美赛，取得相应名次，担任过学生干部并获得相关荣誉。\r\n作为计算机技术专业统考生进入湖南大学。目前方向为Windows底层驱动开发，负责安全卫士产品开发。\r\n', '吴璐', 'Cecilia', '硕士', NULL, '18973317043', 'wuluchn@163.com', '工业控制系统网络安全', '湖南大学天马学生公寓三区19栋511', '湖南匡安网络技术有限公司软件工程师（实习）\r\n\r\n', '2019.6至今：湖南大学信息科学与工程学院\r\n2015.9-2019.6：南京邮电大学理学院\r\n');
INSERT INTO `lab_profile` VALUES ('images/hurong.jpg', 57, '湖南娄底人', '胡蓉', 'Rong Hu	', '硕士', NULL, '18570750826', 'upupwords@qq.com', '并行计算', '湖南大学超算304', NULL, '本科就读于长安大学');
INSERT INTO `lab_profile` VALUES ('images/fangsen.jpg', 58, '本人，吃苦耐劳、勤奋好学、有着极强的团队意识，工作认真负责、勇于承担责任。', '方森', NULL, '硕士', NULL, '19896202983', 'sen.fang@hnu.edu.cn', '多媒体与视觉', '河南省驻马店市正阳县油坊店乡方庄村方庄', '2018.10-2019.5 在红有股份有限公司进行实习。期间，参与两个WEB项目的开发：储量数据库平台建设、社区减负平台。', '2015.9—2019.6：新疆大学（本科）\r\n2019.9—至今： 湖南大学（硕士）\r\n');
INSERT INTO `lab_profile` VALUES ('images/tangfan.jpg', 59, '我是唐帆，本科就读于华侨大学，现就读于湖南大学攻读学术型硕士学位。本科学习期间我努力学习，成绩良好，获得校一等奖学金、院优秀学生一等奖学金；我也积极加入科研小组，参加科创竞赛，获得2017（第10届）中国大学生计算机设计大赛三等奖、第七届“锐智杯”福建省大学生智能设计大赛一等奖；同时也担任班级副班长，班主任助理等， 获得2015-2016华侨大学计算机学院“三好学生”荣誉称号以及华侨大学2016年学生暑期社会实践活动“积极分子”称号。经过三年得不懈努力，争取到保研名额继续在湖南大学深造。\r\n读研期间我也认真对待课程学习和科研工作，学习上也取得良好的成绩，获一等奖学金；科研上也抓紧时间调研文献，在导师的指导下已完成第一篇论文的初稿，目前正在审稿当中，下阶段将继续调研文献，寻找下个研究课题。\r\n', '唐帆', 'Fan Tang', '硕士', NULL, '17877785191', 'tfcandy@163.com', '边缘计算', '湖南省长沙市岳麓区桔子洲街道麓山南路2号湖南大学信息科学与工程学院', NULL, '2014.9--2018.6  华侨大学     计算机科学与技术（本科） 绩点：4.3   排名： 3/54\r\n2015.9--2016.7  西安交通大学  计算机科学与技术（交流学习）          \r\n2018.9--至今    湖南大学     计算机科学与技术（硕士）\r\n');
INSERT INTO `lab_profile` VALUES ('images/zengruijie.png', 60, '本科曾多次获得校级优秀奖学金，优秀学生，一次国家奖学金。\r\n研究生阶段曾获一次一等学业奖学金，一次二等学业奖学金。\r\n', '曾悦婕', 'Doris', '硕士', NULL, '17342687211', '415801368@qq.com', '归零神经网络求解各类不等式及其实际应用', '湖南大学信息科学与工程学院软件大楼（没有具体实验室）', NULL, '2002.9-2008.6：就读于湖南省张家界市武陵源区军地小学；\r\n2008.9-2014.6：就读于湖南省张家界市武陵源区武陵源一中；\r\n2014.9-2018.6：就读于陕西省西安市陕西师范大学物信院电子信息科学与技术专业；\r\n2018.9-至今：就读于湖南省长沙市湖南大学信科院信息与通信工程专业。\r\n');
INSERT INTO `lab_profile` VALUES ('images/fengqijie.jpg', 61, '大家好，我是封旗杰，来自湖南邵阳，现在在湖南大学读计算机专业的研究生，在学校的主要的研究方向是基于Flink的大规模区域目标实时查找方法，目前已经在相关领域发表了一篇论文，同时还有一篇专利已经进入实审。未来想从事于Spark、Flink等大数据技术的研究，希望有志同道合的朋友们一起多多交流，多多学习。\r\n\r\n', '封旗杰', 'Michael', '硕士', NULL, '15575842043', '2932614756@qq.com', '基于Flink的大规模区域目标实时查找方法', '院楼311', NULL, '2012.9-2016.6 就读于湖南商学院（现改名湖南工商大学）\r\n2017.9-至今   就读于湖南大学\r\n');
INSERT INTO `lab_profile` VALUES ('images/fuzhongming.jpg', 62, '本人现于湖南大学信息科学与工程学院攻读博士学位，具体研究包括并行分布式体系结构优化，包括任务调度，资源管理和负载均衡优化。', '付仲明', 'Zhongming Fu', '博士', NULL, '18874028287  ', 'fuzhongming@hnu.edu.cn', '云计算与大数据', '国家超级计算长沙中心302', NULL, '2016.09 – 至今   博士（硕博连读） 湖南大学     云计算与大数据\r\n2015.09 – 2016.06 硕士             湖南大学     云计算与大数据\r\n2011.09 – 2015.06 本科             兰州理工大学 计算机科学与技术');
INSERT INTO `lab_profile` VALUES ('images/dinghaotao.jpg', 63, '本人在学习方面态度认真严谨，一直以来的学业成绩基本优秀；在性格方面，善于与人沟通，乐观开朗，乐于表达和分享，具有良好的心态和较强的责任心；在工作实践方面，担任过学校的一些职务，也在大学期间做过一些兼职，具备一定的工作经验基础。\r\n大学四年曾担任本科学校团支部书记和党支部学生秘书，在四年的工作过程中，能够很好的完成组织分配的各项工作及自身的学业任务，这段工作经历让我学到了很多，同时也培养出了出色的组织及领导能力。我相信自己能够在湖南大学这个广阔的平台来锻炼并展示自己的能力，积极为同学服务，提升自己各方面的素质。', '丁浩涛', NULL, '硕士', NULL, '18570750899', 'hnudht@hnu.edu.cn', '深度学习', '湖南省长沙市桔子洲街道阜埠河路天马学生公寓3区10栋', NULL, '本科毕业于南昌大学软件学院软件工程专业');
INSERT INTO `lab_profile` VALUES ('images/wufanyu.png', 64, '我叫吴繁宇，来自于广西南宁市，是湖南大学信息科学与工程学院计科系2019级的一名硕士研究生，目前在湖南省匡安网络技术有限公司研发部实习，主要负责后台逻辑代码的撰写。', '吴繁宇', NULL, '硕士', NULL, '18776940201', '1364621641@qq.com', '工业控制网络安全', '湖南省长沙市岳麓区湖南大学天马学生公寓10栋211', NULL, '2003.09 – 2009.07 就读于广西南宁市共和路小学\r\n2009.09 – 2012.07 就读于广西南宁市第四中学\r\n2012.09 – 2015.07 就读于广西南宁市第四中学\r\n2015.09 – 2019.07 就读于广西桂林市桂林电子科技大学\r\n2019.08 – 至今   就读于湖南长沙市湖南大学');
INSERT INTO `lab_profile` VALUES ('images/zhuyulin.jpg', 65, '男，土家族，就读于湖南大学信息科学与工程学院，计算机科学与技术全日制硕士在读生。从入学至今共发表学术论文1篇（SCI二区）。', '朱珏霖', 'Victor', '硕士', NULL, '15084748599', 'victorzoo@qq.com', '计算机视觉', '湖南大学软件大楼', NULL, '2014-2018 长沙理工大学 学士\r\n2018至今 湖南大学 硕士');
INSERT INTO `lab_profile` VALUES ('images/zhangyufeng.jpg', 66, '本科毕业于湖南大学数学院，曾获2015-2016以及2016-2017学年度“国家励志奖学金”，2016-2017学年度湖南大学“勤工助学优秀个人”，2018年研究生一等学业奖学金。\r\n学习态度端正，具有较强的逻辑思维能力、专注力及学习能力。\r\n工作认真负责，具有较强的组织能力。\r\n为人积极乐观，拥有良好的心态。', '张宇峰', 'Yufeng Zhang', '硕士', NULL, '15700726044', 'zyf447572753@qq.com', '数值计算并行算法的设计与优化', '湖南大学天马学生公寓一区3栋713', NULL, '2014.09-2018.06本科就读于湖南大学数学院。\r\n2018至今，湖南大学信息科学与工程学院18级在读硕士。');
INSERT INTO `lab_profile` VALUES ('images/linshengle.jpg', 67, '本科就读于南京邮电大学理学院信息与计算科学系。GPA 4.31/5.0，多次获得校一等奖学金、二等奖学金，连续两年（2016、2017）获校级三好学生，2017年获优秀暑期社会实践奖。2018年获美国大学生数学建模竞赛Honorable Mention奖。积极进取，热爱学习；敢于挑战，乐于钻研', '林圣乐', NULL, '硕士', NULL, '17805006099', '497851540@qq.com', '高性能计算与并行计算', '湖南大学天马学生公寓一区3栋441室', NULL, '2015.9—2019.6 南京邮电大学理学院信息与计算科学系，本科。');
INSERT INTO `lab_profile` VALUES ('images/zhouqingqing.jpg', 68, '1992年生，女，现在是湖南大学信息科学与工程学院计算机科学与技术专业2018级的一名博士研究生。博士一年级期间进行过工业大数据应用相关的研究，并发表1篇学术论文。同时，进行了分布式机器学习性能优化方面的研究，完成了1篇专利。目前的主要研究方向为多云数据传输优化', '周清清', NULL, '博士', NULL, '18874707106', 'qqzhou@hnu.edu.cn', '多云数据传输优化、工业大数据应用', '湖南长沙湖南大学信息科学与工程学院501', '2015.7.1-2015.7.30 上海昊声电子信息技术有限公司深圳分公司（实习）：2015 年暑假在上海昊声电子信息技术有限公司深圳分公司实习一个月，锻炼了自己独立解决问题与分析问题，以及广泛利用资源解决问题的能力。', '本科（2011-2015）：吉首大学 软件服务外包学院 软件工程 \r\n硕士（2015-2018）：中南大学 软件学院 软件工程\r\n博士（2018-）湖南大学 信息科学与工程学院 计算机科学与技术');
INSERT INTO `lab_profile` VALUES ('images/huzeshan.jpg', 69, '我是来自湖南衡阳的胡泽善，目前保研到湖南大学读研究生，我主要跟随肖林老师研究使用神经网络计算数学问题并研究其在控制机械臂等方面的应用。', '胡泽善', NULL, '硕士', NULL, '13212696466', 'huzeshan@hnu.edu.cn', '神经计算', '湖大天马公寓3区19栋', NULL, '本科：福州大学\r\n高中：衡阳市一中');
INSERT INTO `lab_profile` VALUES ('images/daiminglu.jpg', 70, '略', '戴民禄', NULL, '硕士', NULL, '13347390862', 'zxc1506911373mr.dai@gmail.com', '高性能计算、并行优化', '超算0号楼304室', NULL, '2013-2017年  长沙理工大学 软件工程专业          获取学士学位\r\n2019年-至今  湖南大学	  计算机科学与技术专业  攻读硕士中');
INSERT INTO `lab_profile` VALUES ('images/liuxiaowei.jpg', 71, '湖南常德人，一直寻求兴趣与利益的的最佳平衡点，期待多做实际有意义之事，希望通过自身努力满足自己的兴趣发展需求，并能有所成就，获取真正的自我满足感。', '刘晓伟', NULL, '博士', NULL, '15074991574', 'liuxiaowei@hnu.edu.cn', '深度学习与图像分析', '湖南大学信科院院楼', '2014-2017 湖南信息职院 教师', '2006-2010 湖南师范大学 信息与计算科学 本科\r\n2011-2014 湖南大学 计算机科学与技术 硕士研究生\r\n2017-至今 湖南大学 计算机科学与技术 博士研究生');
INSERT INTO `lab_profile` VALUES ('images/liwenjia.jpg', 72, '性格上乐观随和，积极向上，态度认真负责，时间观念强，能够较快的适应新的环境。\r\n学习上，能够积极主动去学习新方法，善于独立思考。本科期间多次参加学科类竞赛并有一定的成绩。\r\n生活上，积极参加集体活动，与同学相处融洽，乐于助人。', '李旻佳', NULL, '硕士', NULL, '13970884075', '393985509@qq.com', '图计算，边缘计算', '湖南大学天马宿舍19栋', NULL, '2015-2019 本科 南昌大学 计算机科学与技术\r\n2019-2022 研究生 湖南大学 计算机科学');
INSERT INTO `lab_profile` VALUES ('images/xiaosurong.jpg', 73, '我是一个积极向上、乐观开朗的人。遇到问题或困难时，我首先会理顺解决问题的思路，提出自己的想法，然后按照步骤有条理地去解决。同时，我也是一个理解能力较强的人，能够很好地理解他人想表达的想法。我喜欢计算机编程技术，有较强的逻辑思维能力、自学能力和对新环境的适应能力。同时有较好的分析和处理问题的能力。吃苦耐劳，有良好的团队合作精神。', '肖素容', 'Susan', '硕士', NULL, '18566724337', 'lilyove@qq.com', '边缘计算', '	院楼501', '实习 恒生电子 恒云产品规划与研发部 开发实习生', '2013.9-2017.6 华北电力大学 信息与计算科学 本科\r\n2017.9-2020.6 湖南大学     湖南大学       硕士\r\n');
INSERT INTO `lab_profile` VALUES ('images/bianxiangdi.jpg', 74, '山东菏泽人。现就读于湖南大学信息与工程学院，研究方向为工业控制网络安全。', '边祥迪 ', 'Xiangdi Bian', '硕士', NULL, '18570779001 ', 'bianxiangdi@hnu.edu.cn', '工业控制网络安全', '湖南大学天马学生公寓', '2017.6-2018.9 广州广船国际股份有限公司 实习\r\n2018.9-2021.6 湖南匡安网络技术有限公司 实习\r\n', '2013.9-2017.6 武汉理工大学 能源与动力工程学院 本科\r\n2018.9-2021.6 湖南大学     信息科学与工程学院 硕士\r\n学术成果：\r\n');
INSERT INTO `lab_profile` VALUES ('images/shiqing.png', 75, '中共党员，河南南阳人；\r\n科研方向主要集中于工业控制系统中的加密通信与安全认证；\r\n在工程项目中主要负责界面设计与前端开发；\r\n兴趣爱好广泛，科研之余喜欢工笔花鸟创作和CG原画设计。\r\n', '时晴', 'Shi Qing', '硕士', NULL, '17839227621', '17839227621@163.com', ' 工业控制系统安全', '湖南省长沙市岳麓区湖南大学天马学生公寓3区10栋', '湖南匡安网络技术有限公司（实习）', '2014.09 - 2018.06河南大学软件学院网络工程系 学士\r\n2018.09 –至今 湖南大学信息科学与工程学院计算机科学系 硕士\r\n');
INSERT INTO `lab_profile` VALUES ('images/xiaoxiong.jpg', 76, '本人就读于湖南大学，目前正在攻读博士学位。主要研究兴趣包括并行计算、高性能计算和分布式体系结构。', '肖雄', 'Xiong Xiao  ', '博士', NULL, '15211017136 ', '：xx@hnu.edu.cn', '并行计算、高性能计算', '湖南大学超级计算长沙中心圆楼302', NULL, '2013.9—2017.6  江西农业大学  计算机科学与技术   本科\r\n2017.9—至今    湖南大学      计算机科学与技术  硕博连读\r\n');
INSERT INTO `lab_profile` VALUES ('images/huangyang.jpg', 77, '本人是一名合格的党员，为人友善诚，做事细致，有很强的责任心；学习能力与适应能力强，思维较为开阔，有较强的动手和组织能力。\r\n学习：本科保研专业1/58，获得校一等、三等以及专项奖学金，并多次获得三好学生、优秀团干等称号。\r\n项目：参加第8、9届全国大学生服务外包创新创业大赛，获二等奖、三等奖各一次。了解C++、python、java、oracle、mysql、sqlserver等用法。编写过多个小型项目。\r\n', '黄阳', 'YangHuang', '硕士', NULL, '18605305133 ', '948864334@qq.com', '分布式图计算', '湖南大学天马学生公寓3区19栋533', '2018.10-2019.4 湖南中医药大学组织人事部 实习\r\n本科任职：系党支部副书记、班级团支书、大科协副部\r\n', '2015.9-2019.6 湖南中医药大学 计算机科学与技术专业 大学本科 学士学位\r\n2012.9-2015.6 巨野一中 \r\n');
INSERT INTO `lab_profile` VALUES ('images/liyizhan.jpg', 78, '本人思想积极向上，有较高的思想觉悟。在学习上，有良好的自主学习能力，能够做到勤奋刻苦、踏实努力、实事求是、不耻下问。在工作上，曾担任班长期间，有强烈的责任感、良好的组织与协调能力，能够做到吃苦耐劳，一丝不苟。', '李艺展', 'Yizhan Li	  ', '硕士', NULL, '19896221346  ', 'lyz9752@163.com', '边缘计算 博弈论', '河南省洛阳市西工区丹城路11号院', NULL, '	2015.09-2019.06在江西师范大学读本科\r\n2012.09-2015.06在洛阳市第一高级中学读高中\r\n');
INSERT INTO `lab_profile` VALUES ('images/yananming.png', 79, '1.	熟练使用python、sql语言；2.了解快消品，工业数据，互联网行业的数据，可以独立进行数据分析，BI开发；3.熟悉scikit-learn, numpy, pandas, keras, matplotlib，scrapy等工具；4.了解并实战过数据挖掘的算法；5.写自动化脚本处理excel。', '闫安民', 'anmin', '硕士', NULL, '15576624976', '：836147880@qq.com', '数据分析，数据挖掘', '湖南省长沙市湖南大学信科院', '2019.07-2019.10 腾讯公司 数据产品实习生\r\n1.负责 now 直播，腾讯直播的一些数据指标，数据分层表上报验收项目，跟进腾讯直播的数据治理体系建设，推进数据体 \r\n系的优化和完善； \r\n2.梳理商业化场景下的指标体系并设计日常数据报表体系，为产品商业化提供基础决策支持； \r\n3.通展开专题分析，提出业务/产品改进策略，并推进落地实施，观测效果反馈；协助导师规划业务分析点，并负责例行化 \r\n', '2013.09-2017.06 湖南师范大学 通信工程 学士\r\n2017.09-2020.6 湖南大学 计算机技术 硕士\r\n');
INSERT INTO `lab_profile` VALUES ('images/denghongqi.jpg', 80, '邓洪奇，男，中共党员。本科就读于湖南科技大学信息与计算科学专业，在本科期间，先后担任院学习部部长、院团委第一副书记、学生支部副书记等职务，工作认真负责。学习刻苦钻研，成绩优异，负责的数学建模科技创新团队荣获全国小平科技创新团队，全国大学生建模竞赛荣获省二等奖，获省优秀毕业生、校“十佳大学生”、校“十佳学生党员”等十多项校级及以上奖项，最后以专业第一成绩保研至湖南大学计算机技术专业，目前在超算中心匡安网络从事工控安全卫士研究。', '邓洪奇', NULL, '硕士', NULL, '15173263396 ', '1552479041@qq.com', '工业控制网络安全', '湖南省长沙市岳麓区湖南大学天马学生公寓三区十栋207', '2019.07-至今 在超算匡安网络中心实习', '2012.09-2015.06 就读于安徽省阜南县第一中学\r\n2015.09-2019.06 就读于湖南科技大学\r\n');
INSERT INTO `lab_profile` VALUES ('images/lijinna.png', 81, '我叫李金娜，来自湖南省岳阳市，本科就读于中南林业科技大学，目前所在的团队是工控安全团队，我目前的方向是利用snmp协议实现两个平台之间的通信。', '李金娜', NULL, '硕士', NULL, '15073136420', '15073136420@163.com', '工业控制网络安全', '	超算中心1号楼302', '目前在湖南省匡安网络技术有限公司研发部实习。', '2015~2019年就读于中南林业科技大学计算机与信息工程学院计算机科学与技术专业');
INSERT INTO `lab_profile` VALUES ('images/huqilin.png', 82, '本科就读于湖南大学软件工程专业，在校期间曾获得一等奖学金、单项奖学金，曾获得“三好学生”“优秀团干“等称号。2017年参与德州大学奥斯汀分校EASE访问交流项目。为人热情开朗，热爱生活。', '胡杞霖', NULL, '硕士', NULL, '15111272827', 'daisy1z@yeah.net', '工业控制网络安全', '国家超级计算长沙中心', NULL, '2015.9-2019.6 湖南大学软件工程专业\r\n2019.9-至今 湖南大学计算机技术专业\r\n');
INSERT INTO `lab_profile` VALUES ('images/jianzhen.jpg', 83, '不管是生活还是学习或者工作你对待的态度是最重要的，用积极的心态去面对，用心去思考。当困难和挑战出现时，你通过自己的思考解决了，你获得的是面对和解决困难的经验，当下次再遇见相似困难时就迎刃而解了，而且这会在心里极大的鼓励自己。\r\n思考和总结是工作和学习最有效的手段。会思考让你更快的解决问题，少走弯路。而总结能积累经验发现不足，解决问题才会越来越娴熟。\r\n', '简镇 ', NULL, '硕士', NULL, '18080462375', 'zhenjian@hnu.edu.cn', '神经网络与智能计算', '湖南省长沙市湖南大学天马学生公寓19栋', NULL, '西南民族大学     软件工程      工学学士    2014.9-2018.7');
INSERT INTO `lab_profile` VALUES ('images/liuyonggang.jpg', 84, '热爱生活，热爱科研，希望能成为一个纯粹的，有担当的人。', '刘勇刚  ', NULL, '博士', NULL, NULL, 'lyghnu@163.com', '并行图算法设计与社交网络应用。', '湖南大学信息科学与工程学院444室。', NULL, '2015年-至今，湖南大学，信息科学与工程学院，计算机科学与技术专业博士研究生');
INSERT INTO `lab_profile` VALUES ('images/wangteng.jpg', 85, '目前在智能超声团队研究深度学习对图像的处理', '王腾', NULL, '硕士', NULL, '13207610150', '237073700@qq.com', '应用深度学习对B超图像进行处理', '湖南省长沙市湖南大学超算中心1栋401', NULL, '2015-2019 河北工业大学本科\r\n2019-     湖南大学硕士研究生\r\n');
INSERT INTO `lab_profile` VALUES ('images/huwenpeng.jpg', 86, '本科阶段以数学系课程为主，主要包括：数学分析，高等代数、解析几何、概率统计、常微分方程、离散数学、实变函数、复变函数、运筹学、计算机组成原理、c++语言等课程；在研究生阶段主要学习计算机网络、现代计算机组出原理、智能算法优化以及算法设计。研究生期间在上海量华金融信息服务有限公司实习，工作主要内容为量化交易策略开发，在实习期间打下了良好的编程基础。', '胡文鹏 ', NULL, '博士', NULL, '15580056756	', '497142495@qq.com', '边缘计算、任务调度', '湖南省长沙市岳麓区湖南大学信息科学与工程学院', '2016.10 – 2018.1在上海量华金融信息服务有限公司实习，主要从事量化交易中的交易', '2011.9 - 2015.7 陕西科技大学 本科\r\n2015.9 - 2018.7 湖南大学  硕士研究生\r\n2018.9 – 至今  湖南大学  博士研究生\r\n');
INSERT INTO `lab_profile` VALUES ('images/luowensheng.jpg', 87, '2018级博士研究生', '罗文晟', 'Wensheng Luo', '博士', NULL, '18570078412', 'luowensheng@hnu.edu.cn', '数据管理，图计算，并行分布式计算', '湖南大学信息科学与工程学院', NULL, '本科就读于西北工业大学通信工程，硕士就读于西北工业大学电子与通信工程。');
INSERT INTO `lab_profile` VALUES ('images/liuzhao.jpg', 88, '无', '刘钊', 'Zhao Liu', '博士', NULL, '18874652015 ', 'lzhao@hnu.edu.cn', 'crowdsourcing', '湖南省长沙市岳麓区', NULL, NULL);
INSERT INTO `lab_profile` VALUES ('images/linpeiying.jpg', 89, '本硕就读于湖南大学信息科学学院通信工程专业，在此期间不断增强自己的编程能力、逻辑推理能力。掌握了C/C++11、matlab、Python、Verilog编程。\r\n博士期间主攻图计算。希望在图算法、图并行分布式算法、图系统方面都能有所贡献。\r\n', '林培英', 'Beli', '博士', NULL, '15243639724', 'peiying_lin@hnu.edu.cn', '图计算', '湖南大学信息科学与工程学院', '2017.07-2017.09: 浙江猛凌机电有限公司实习（FPGA开发）', '本科-2013.09-2017.06 湖南大学信息科学与工程学院通信工程专业 硕士-2017.09-2019.07 湖南大学信息科学与工程学院通信工程系 博士');
INSERT INTO `lab_profile` VALUES ('images/xiaotong.jpg', 90, '2012年于中北大学获工学学士学位，2018年于国防科技大学获工学硕士学位，目前在湖南大学信息科学与工程学院攻读博士学位。', '肖桐', NULL, '博士', NULL, '18807473747', 'xiaotong18@hnu.edu.cn', '超级计算机故障预测', '湖南大学信息科学与工程学院', '2012年7月至2015年7月 国防科技大学计算机学院计算机研究所 助理工程师', '2008年9月至2012年7月 中北大学     软件工程         本科\r\n2015年9月至2018年6月 国防科技大学 计算机科学与技术 硕士\r\n2018年9月至今          湖南大学     计算机科学与技术 博士\r\n');
INSERT INTO `lab_profile` VALUES ('images/guodawei.jpg', 91, '男 22岁 非全日制）硕士一年级', '郭大为', 'Guo Dawei ', '硕士', NULL, '18274182830', '610575364@qq.com', '机器学习', '岳麓区银杉路684号', NULL, '2015-2019年 湖南工业大学 软件工程专业');
INSERT INTO `lab_profile` VALUES ('images/chenweijie.png', 92, '为人诚恳踏实，思想积极向上，性格开朗，责任心强，执行力强、有较强的团队精神。 学习勤奋刻苦，专业知识扎实、学术素养 良好。 具有较强的学习能力，独立思维能力，办事沉稳。', '陈伟杰', 'WeijieChen', '硕士', NULL, '13510028027', '313372622@qq.com', '工控网络安全', '湖南大学天马学生公寓', '2017.07 ~ 2018.03    		腾讯  								用户研究\r\n2019.09 ~ 至今           湖南匡安网络技术有限公司			实习\r\n', '2013.07 ~ 2017.09 		沈阳理工大学 		信息与计算科学 		本科\r\n2018.07 ~ 至今  			湖南大学    		计算机技术   		硕士\r\n');
INSERT INTO `lab_profile` VALUES ('images/wengtongfeng.jpg', 93, '一个热爱祖国、热爱学习、积极乐观的人。', '翁同峰 ', 'Tony', '博士', NULL, '15827179569', 'wengtongfeng@hnu.enu.cn', '分布式图计算', '湖南大学南校区', '2015.7-2015.9 武汉盛帆电子股份有限公司\r\n2018.7-2019.4 深圳迈瑞生物医疗电子股份有限公司\r\n', '本科：2011.9-2015.6 武汉理工大学；\r\n硕士：2016.9-2018.6 武汉理工大学；\r\n博士：2019.9-至今 湖南大学\r\n');
INSERT INTO `lab_profile` VALUES ('images/hujunyan.jpg', 94, '胡俊艳，女，籍贯河南，2016级博士生，2014年毕业于湖南大学数学与计量经济学院理学学士。读博期间已发表两篇论文分别是IEEE TSC和NPC2019.', '胡俊艳', 'Junyan Hu', '博士', NULL, '15773150246', 'junyanhu@hnu.edu.cn', '云计算、边计算、博弈论', '湖南大学信息科学与工程学院', NULL, '2010-2014 湖南大学 数学与计量经济学院 \r\n2014-2016 湖南大学 数学与计量经济学院 数学与应用数学专业 \r\n2016-至今 湖南大学 信息科学与工程学院 计算机科学与技术 \r\n');
INSERT INTO `lab_profile` VALUES ('images/qinshujie.png', 95, '本科就读于湖南大学计算机科学与技术专业，编程基础扎实，掌握C、C++、Python、Java等编程语言，熟悉经典算法和数据结构，性格开朗，待人真诚，爱好广泛，喜欢接触新事物。', '覃舒婕 ', NULL, '硕士', NULL, '15717415441 ', 'qinshujie@hnu.edu.cn', '工业控制网络安全', '湖南省长沙市湖南大学天马学生公寓', '湖南匡安网络技术有限公司（实习）', '2011.09-2014.06  广西柳州高级中学\r\n2014.09-2018.06   湖南大学  计算机科学与技术  本科\r\n2018.09-至今     湖南大学  计算机科学与技术  学硕\r\n');
INSERT INTO `lab_profile` VALUES ('images/rentianyue.png', 96, '我叫任天悦，来自湖南岳阳，本科就读于东北林业大学。目前我在工控安全团队和图计算团队进行学习和研究，主要参与工控网络安全软件的开发，以及安全数据查询方向的研究。', '任天悦', 'Cisy ', '硕士', NULL, '15116201426', '576872451@qq.com', '安全数据查询', '湖南省长沙市岳麓区湖南大学天马学生公寓19栋511', NULL, '2012年-2015年 湖南广益实验中学\r\n2015年-2019年 东北林业大学信息与计算机工程学院\r\n2019年至今 湖南大学信息科学与工程学院\r\n');
INSERT INTO `lab_profile` VALUES ('images/liliping.jpg', 97, '编程语言：Java，Python\r\n	深度学习框架：Pytorch\r\n	英语：过四、六级\r\n本科主要方向Java开发、网站设计，获得一等奖学金、三好学生、优秀共青团员、国家励志奖学金、优秀毕业生荣誉。\r\n', '黎丽萍', 'Lavender	', '硕士', NULL, '15116089299', '2859747402@qq.com', '	数据挖掘、深度学习', '湖南大学信息科学与工程学院', '湘潭风云科技----安卓开发', '本科：湖南工业大学 2014-2018\r\n硕士：湖南大学     2018-2021\r\n');
INSERT INTO `lab_profile` VALUES ('images/zhongkai.jpg', 98, '湖南永州人', '钟凯', NULL, '硕士', NULL, '15200955035', 'startkz@163.com ', '工控安全', '湖南省长沙市岳麓区国家超级计算中心1号楼', NULL, '本科2014.9-2018.6 燕山大学；研究生 2018.9- 湖南大学');
INSERT INTO `lab_profile` VALUES ('images/chenbaoding.png', 99, '福建人', '陈宝鼎', NULL, '硕士', NULL, '13275631682', 'cbddehua@163.com', '工业控制系统与网络安全', '湖南省国家超级计算长沙中心1号楼', NULL, '本科 2014.9-2018.7 合肥工业大学，研究生 2018.9- 湖南大学 ');
INSERT INTO `lab_profile` VALUES ('images/pengjiwu.png', 100, ' 彭继武，湖南大学信息科学与工程学院在读博士。湖南郴州人。', '彭继武', 'JackPeng', '博士', NULL, '18874058341 ', 'pjw@hnu.edu.cn', '移动边/云计算，高性能计算，人工智能', '湖南省长沙市岳麓区湖南大学信息科学与工程学院312实验室', '1. 2019.03-2020.06 计科系博士生党支部副书记', '1.	2017/09-至今，湖南大学，信息科学与工程学院，硕博连读，导师：李肯立\r\n2.	2015/09-2017/09，湖南大学，信息科学与工程学院，硕士，导师：肖正\r\n3.	2011/09-2015/07，湖南师范大学，工学院，本科\r\n');
INSERT INTO `lab_profile` VALUES ('images/xieyuan.png', 101, '本科期间成绩排名专业前三，多次参加数学建模竞赛，荣获各类奖学金，校级三好学生等，获得保研资格，取得湖南大学直博的资格。', '谢缘', NULL, '博士', NULL, '15111278587', 'yxie@hnu.edu.cn', '众包技术、时空轨迹数据挖掘', '湖南大学老留学生公寓', NULL, '2015年-2019年就读于湖南农业大学\r\n2019年-至今就读于湖南大学\r\n');
INSERT INTO `lab_profile` VALUES ('images/dingyan.jpg', 102, '1992年出生，山西临汾人，中共党员，现任湖南大学信息科学与工程学院研究生会主席，计算机科学系博士生党支部书记。喜欢阅读，钟情自然。座右铭：千里之行，始于足下。目前发表SCI论文两篇，CCF C类会议一篇。', '丁岩', 'Yan Ding', '博士', NULL, '18570779200', 'ding@hnu.edu.cn', '边缘计算，机器学习，数据分析；', '湖南大学信息科学与工程学院；', NULL, '教育背景：\r\n2018.09 – 今 湖南大学，师从李克勤教授和李肯立教授，攻读工学博士学位；\r\n2015.09 – 2018.07 新疆大学，师从吾守尔·斯拉木院士和努尔布力教授，获工学硕士学位；\r\n2010.09 - 2014.07 中北大学，获工学学士学位；\r\n');
INSERT INTO `lab_profile` VALUES ('images/maziyi.jpg', 103, '本人硕士期间在长春理工大学吉林省网络与信息安全重点实验室参与了多个项目的研究工作，其中包括两个国际科技合作项目，“基于网络演算的混合高动态空间信息网络服务质量研究”和“基于软件定义网络的空间信息网络性能研究”，以及省教育厅项目“基于SDN的空间信息网络体系架构研究”。曾参加第十届WiSATS会议和第三届空间信息网络学术论坛做论文汇报。', '马子艺', NULL, '19级博士', NULL, '16680805377 ', 'maziyi@hnu.edu.cn', '图数据分析，图查询，数据挖掘', '老留学生公寓', NULL, '2019年至今就读于湖南大学信息科学与工程学院\r\n2016.9-2019.6 长春理工大学计算机科学技术学院\r\n2012.9-2016.6 沈阳农业大学信息与电气工程学院\r\n');
INSERT INTO `lab_profile` VALUES ('images/pubin.jpg', 104, '本人蒲斌现就读于湖南大学信息科学与工程学院，目前从事交通流量预测以及医学图像处理等工作，希望与各位同学和学者一起进步', '蒲斌', 'Bin Pu', '19级博士', NULL, '18469115494', 'pubin@hnu.edu.cn', '交通流量预测、深度学习', '湖南大学信息科学与工程学院', '2018年7月-2019年1月 自然资源部第一地理信息制图院', '2012年9月-2016年6月 西安科技大学\r\n2016年9月-2018年6月 云南大学\r\n2019年5月-至今 湖南大学\r\n');
INSERT INTO `lab_profile` VALUES ('images/wangjingjing.jpg', 105, '本人一直就读于计算机科学与技术专业，具有算法分析设计、软件设计、社交网络分析等方面的背景。从2015年开始在线社交网络信息传播的研究。攻读硕士期间，对社交网络相关的知识打下了基础。在博士期间，参与指导老师申请的省级，国家级项目，承担了一些数据分析和研究工作。在老师的指导下，发表一篇CCF C类会议文章，于2017年11月参加会议做论文报告。同时，积极参加社交网络相关的会议和培训课程，于2017年12月参加中国计算机学会举办的第87期社交网络与数据挖掘的前沿讲习班培训课程，对社交网络分析和数据挖掘的前沿和发展有一定的认识和了解。', '王晶晶', 'Jingjing Wang', '17级博士', NULL, '15675833233	', 'wangjingjing2019@hnu.edu.cn', '社交网络分析', '湖南大学信息科学与工程学院', NULL, '2011-2015 本科 平顶山学院\r\n2015-2017 硕士 湖南大学\r\n2017-至今 博士在读 湖南大学\r\n');
INSERT INTO `lab_profile` VALUES ('images/zhaiyuxuan.png', 106, '目前在智能超声团队', '翟宇轩', 'Yuxuan Zhai', '19级硕士', NULL, '17695785054', NULL, '计算机视觉', '湖南省岳麓区湖南大学天马学生公寓十栋', NULL, '2014.09-2018.06 河北工业大学 计算机科学与技术学士\r\n2011.09-2014.06 分宜中学 高中\r\n');
INSERT INTO `lab_profile` VALUES ('images/yusiyang.png', 107, '2009年进入湖南大学硕博连读，在校期间受导师项目安排一直兼职于深圳市证通电子股份有限公司。\r\n自2010年7月开始，我在该公司主要负责安全芯片的前端RTL代码设计和密码算法的安全研究。6年的项目经验，让我在芯片设计方面有了自己独特的经验积累和感悟。对于设计中出现的问题能够快速响应，并找到相应的解决方案。我也逐渐从一个我行我素的新人成长为一个有责任心，协助他人以及能辅导新人的热心员工。\r\n项目期间，我还负责密码算法的安全研究。结合自己的项目，对国内外的安全手段进行移植和改进，并通过相关安全认证。同时也锻炼了自己对相关领域的钻研和分析能力。\r\n工作期间由于工作突出，绩效多次被评为A级。\r\n', '余思洋', 'Nick', '博士', '博士后', ' 18673185225', 'nickysy@qq.com', '嵌入式系统开发，并行计算，非易失性存储加密', '湖南大学信息科学与工程学院', '起止时间：2017年5月—2018年3月\r\n工作单位：长沙市全度影像科技有限公司			职位： FPGA工程师；\r\n起止时间：2010年7月—2017年4月\r\n工作单位：深圳市证通电子股份有限公司			职位： IC设计工程师；\r\n', '2009/09—2017/06：  湖南大学		计算机科学与技术		硕博连读。\r\n2004/09—2008/06：  湖南农业大学	计算机科学与技术		学士学位。\r\n');
INSERT INTO `lab_profile` VALUES ('images/chencen.jpg', 108, '目前湖南大学在读博士后', '陈岑', 'Cen Chen', '博士', '博士后', '15973881597', 'chencen@hnu.edu.cn', 'Big data, Parallel and Distributed Computing, Machine Learning, Deep Learning', '湖南大学信息科学与工程学院', NULL, '2018/02 - 2019/03  Joint Phd. in University of Singapore, Department of Electrical and Computer Engineering\r\n2015/09 - 2019/03  Phd. in Hunan University, China, Computer science and technology\r\n2011/09 - 2014/06  M.S in Sun Yat-Sen University, China, MBA\r\n2003/09 - 2007/07  B.S. in Huazhong University of Science and Technology, China\r\n                 Computer science and technology and Industry design\r\n');
INSERT INTO `lab_profile` VALUES ('images/lichuang.jpg', 109, '2015级博士生，现为湖南工商大学老师', '李闯', 'Chuang Li', '博士', NULL, '15200918521 ', '15200918521@163.com', '并行计算，生物信息学', '湖南商学院', NULL, '2015.9-2019.6               博士研究生      导师：李肯立 教授\r\n湖南大学，信息科学与工程学院，国家超级计算长沙中心\r\n2017.9- 2018.9 	           联合培养           导师：Feng Lin教授\r\n新加坡，南洋理工大学，计算机科学与工程学院  \r\n2014.4 – 2015.6 	       项目合作          导师：朱云平 研究员\r\n军事医学科学院，北京蛋白质组研究中心              \r\n2013.9 - 2015.6 	            硕士研究生       导师：李肯立 教授\r\n湖南大学，信息科学与工程学院\r\n2009.9 – 2013.6 	         本科\r\n湖南人文科技学院，数学与应用数学\r\n');
INSERT INTO `lab_profile` VALUES ('images/zuoqiuyue.jpg', 110, '2013 - 2017年就读于苏州大学数学科学学院数学与应用数学（师范）专业；2017年9月至2019年6月就读于湖南大学数学学院，攻读硕士学位，研究方向是微分方程与动力系统；2019年9月2 + 3硕转博，到湖南大学信息科学与工程学院攻读博士学位，研究归零神经网络的时延鲁棒性。', '左秋月', 'Qiuyue Zuo', '博士', NULL, '18890058841', 'zuoqiuyue@hnu.edu.cn', '归零神经网络的时延鲁棒性', '软件大楼447', NULL, '2017年6月于苏州大学获理学学士学位；当前就读于湖南大学信息科学与工程学院，攻读博士学位。');
INSERT INTO `lab_profile` VALUES ('images/limincan.jpg', 111, '•	Individual Scholarship, Hunan University, China	2011\r\n“Freshmen Cup” Calligraphy Outstanding Awards, Hunan University, China 2012\r\n•	Individual Scholarship, Hunan University, China	2012\r\n•	The First Prize Scholarship, Hunan University, China	2013\r\n•	The Second Prize Scholarship, Hunan  University, China	2014\r\n•	The First Prize Graduate Scholarship, Hunan University, China	2015\r\n•	The Second Prize Graduate Scholarship, Hunan University, China	2016\r\n•	The First Prize Graduate Scholarship, Hunan University, China	2017\r\n', '李敏灿', 'Mincan Li', '博士', NULL, '+86 15084933566', ' limc@hnu.edu.cn', 'Distributed artificial intelligence: Multi-agent systems, agent dynamic cooperation, agent learning.\r\n\r\n•	Complex network: Dynamic MAS, complex cooperation and calculation.\r\n', 'Hunan University', NULL, '•	Ph.D.  Candidate	Hunan University, Hunan, China, Sept. 2015-now\r\nResearch Assistant	College of Computer Science and Electronic Engineering Total GPA: 80.65/100\r\n•	B.E.	Hunan University, Hunan, China, Sept. 2010-Jun. 2014\r\nCollege of Computer Science and Electronic Engineering Total GPA: 83.27/100\r\n');
INSERT INTO `lab_profile` VALUES ('images/linxuan.jpg', 112, 'Currently, I am a 3rd year PhD. student at College of Computer Science and Electrical Engineering of Hunan University, advised by Prof. Zhe Quan. Meanwhile, I am a visiting scholar at Department of Computer Science, University of Illinois at Chicago, advised by Prof. Philip S. Yu. My research interests lie in data mining, machine learning and bioinformatics. Specifically, my research focuses on graph neural representation, drug-target prediction and drug discovery.', '林轩', 'Jack Lin', '博士', NULL, '13007442289', 'jack_lin@hnu.edu.cn', 'Data Mining, Machine Learning, Deep Learning, Bioinformatics ', '软件大楼312实验室', '2014.4-2015.3  湖南沃森电气科技有限公司 实习硬件工程师\r\n2015.12-2017.8 湖南工程学院 招生与就业指导处系统管理员\r\n', '2019/10- now Joint PhD. in University of Illinois at Chicago, Computer Science; 2017/09 - now PhD. in Hunan University, China, Computer Science and Technology; 2013/09 - 2015/12 M.S in Hunan Institute of Engineering, China, Power Engineering; 2009/09 - 2013/06 B.S. in Hunan Institute of Engineering, China, Electrical Engineering. ');
INSERT INTO `lab_profile` VALUES ('images/zhouzhou.jpg', 113, '现为长沙学院老师，湖南大学在职博士后。', '周 舟', NULL, '博士', '博士后', '15274975378', 'zhouzhou03201@126.com', '云计算，边计算', '软件大楼312实验室', NULL, '留学经历：	2015．11-2016．11\r\n(国家公派\r\n留学1年)	留学形式：国家公派    留学国家：澳大利亚\r\n留学院校：Deakin University\r\n国外导师：Jemal H. Abawajy；博士	2012.9-至今	毕业院校：中南大学，专业：软件工程\r\n学历：博士研究生   学位：工学博士\r\n研究方向：云计算，高性能计算，虚拟资源管理\r\n硕士	2009.9-2012.6	毕业院校：湖南师范大学，专业：计算机软件与理论\r\n学历：硕士研究生   学位：工学硕士\r\n研究方向：大数据，知识集成，XML编码\r\n；本科	2003.9-2007.6	毕业院校：国防科技大学，专业：计算机信息管理\r\n本科学历\r\n\r\n\r\n');
INSERT INTO `lab_profile` VALUES ('images/hezhili.jpg', 114, '何臻力博士的主要研究领域为软件工程、机器学习、云计算。何臻力博士参与国家自然科学基金项目4项，各类省级科研项目10余项，参与申报发明专利4项，自行申报发明专利1项。何臻力博士在软件工程领域具有较强实践能力和技术创新能力。\r\n何臻力博士在软件开发项目方面表现突出，多次承担各类软件开发任务，具有较强的软件开发和组织领导能力，专业技能过硬，其主持或参与了多项各层次软件开发项目，经验丰富。\r\n', '何臻力', 'Zhenli He	', '博士', '博士后', '13888325256', 'hezl@ynu.edu.cn', '机器学习、软件工程、云计算', '软件大楼312实验室', '2015/07 – 2019/08   云南大学|软件学院|讲师\r\n2019/08 – 至今      湖南大学|信息科学与工程学院|博士后\r\n', '2012/09 –2015/07 \r\n云南大学 | 系统分析与集成 | 理学博士\r\n2010/09 --2012/07\r\n云南大学 | 软件工程 | 工程硕士\r\n2006/09 --2010/07\r\n云南大学 | 软件工程 | 工学学士\r\n');
INSERT INTO `lab_profile` VALUES ('images/liugang.jpg', 115, '现为湖南大学在读博士', '刘刚', 'Gang Liu', '博士', '', NULL, 'liug@hnu.edu.cn', '云计算，边计算，高性能计算', '软件大楼312实验室', NULL, NULL);
INSERT INTO `lab_profile` VALUES ('images/zengzihao.jpg', 116, '现为湖南大学19纪在读博士', '曾子豪', 'zihao zeng', '博士', NULL, '18874767560', 'zengzh@hun.edu.cn', '并行AI', '湖南大学信息科学与工程学院444', NULL, '2013.9 - 2017.6	吉林农业大学	本科\r\n2017.9 - 2019.6	湖南大学		硕士研究生\r\n2019.9 - 至今		湖南大学		博士研究生\r\n');
INSERT INTO `lab_profile` VALUES ('images/duliang.png', 117, '我叫杜亮，来自于河南省渑池县，本科就读于湖南大学，目前在超算工控中心从事工控\r\n管理平台的开发。', '杜亮', NULL, '硕士', NULL, '15608420042', '1186945606@qq.com', '大数据与云计算', '湖南大学天马学生公寓3区19栋', NULL, '2013.9–2017.6湖南大学');
INSERT INTO `lab_profile` VALUES ('images/wuyuxing.jpg', 118, '无', '吴宇星', NULL, '硕士', NULL, '15697335821', '1063303382@qq.com', '工控网络安全', '湖南省长沙市岳麓区山南路252号', '2019.3-至今  湖南匡安网络技术有限公司  软件开发实习生', '2014-2018   湖南工业大学   自动化专业');
INSERT INTO `lab_profile` VALUES ('images/huyouzuan.jpg', 119, '胡由钻，江西南昌市人，生于1995年10月17日。性格温和，对人真诚友善。', '胡由钻', NULL, '硕士', NULL, '13307083605', '1591960080@qq.com', '云计算与大数据', '湖南大学天马学生公寓1区3栋', '2019.2-至今  湖南匡安网络技术有限公司  软件开发实习生', '2013.9-2017.7 天津大学 船舶与海洋工程 本科    2018.9-至今 湖南大学 计算机技术 硕士');

SET FOREIGN_KEY_CHECKS = 1;
