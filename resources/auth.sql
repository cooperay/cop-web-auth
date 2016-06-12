/*
Navicat MySQL Data Transfer

Source Server         : 192.168.99.202
Source Server Version : 50712
Source Host           : 192.168.99.202:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2016-06-12 17:06:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for jy_base_account
-- ----------------------------
DROP TABLE IF EXISTS `jy_base_account`;
CREATE TABLE `jy_base_account` (
  `id` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '用户Id',
  `loginName` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '用户登录名',
  `password` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '密码',
  `salt` varchar(32) NOT NULL,
  `roleId` varchar(32) NOT NULL DEFAULT '0' COMMENT '用户类型(对应jy_base_role的Id)',
  `picUrl` varchar(255) DEFAULT NULL,
  `name` varchar(64) CHARACTER SET utf8 DEFAULT '' COMMENT '用户名字',
  `email` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '电子邮箱',
  `skin` varchar(64) DEFAULT '',
  `isValid` int(2) NOT NULL DEFAULT '0' COMMENT '是否有效(1:有效，0:无效)',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL,
  `description` varchar(100) CHARACTER SET utf8 DEFAULT '' COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of jy_base_account
-- ----------------------------
INSERT INTO `jy_base_account` VALUES ('097756af9fb440d795ec9c300b3d56f1', 'test', '8eVbNrByWysQP6y+wXHdkg==', '38d61ac8eac545dde7c3736e86a3cec2', '3', null, '开发员工', '', 'skin-0', '1', '2015-12-13 23:33:45', '2015-12-21 21:16:38', '');
INSERT INTO `jy_base_account` VALUES ('1', 'sa', 'D5r6e+Ss9h/qEyRYjicXMA==', '139b27063725fab02cfaf3d3156af9df', '4', '/JYSystem/static/images/system/user/hpic1.jpg', 'Joker', 'xx@163.com', 'skin-3', '1', '2015-10-13 11:23:32', '2015-12-14 00:03:59', '');
INSERT INTO `jy_base_account` VALUES ('2', 'admin', 'M4t/qU4O3ICaaEOsjmDmTg==', '43270453df7ca64d3303ec4d835b15b8', '2', null, '京缘员工', 'xx@163.com', 'skin-0', '1', null, '2015-12-13 23:24:06', '');
INSERT INTO `jy_base_account` VALUES ('78ac1f03911e48b5abf9f8a9e10d4c34', 'liyang', 'AnRx/e95oMT+7W9DWqUCfQ==', '9c133e348dda4dff15009d7641b30171', '3', null, 'liyang', 'cooperay@live.cn', 'skin-0', '1', '2016-06-08 17:28:52', null, '7xK2ul');

-- ----------------------------
-- Table structure for jy_base_login_log
-- ----------------------------
DROP TABLE IF EXISTS `jy_base_login_log`;
CREATE TABLE `jy_base_login_log` (
  `id` varchar(32) DEFAULT NULL,
  `accountId` varchar(32) DEFAULT NULL,
  `loginTime` datetime DEFAULT NULL,
  `loginIP` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jy_base_login_log
-- ----------------------------
INSERT INTO `jy_base_login_log` VALUES ('c51361e6adbb4661ab8ef6e250ee8563', '097756af9fb440d795ec9c300b3d56f1', '2016-06-06 12:45:34', '0:0:0:0:0:0:0:1');
INSERT INTO `jy_base_login_log` VALUES ('5534e57f56f04652ae1a5ddef21a3b9b', '097756af9fb440d795ec9c300b3d56f1', '2016-06-06 13:00:02', '0:0:0:0:0:0:0:1');
INSERT INTO `jy_base_login_log` VALUES ('82eb085264d14d3db627618d3875830b', '097756af9fb440d795ec9c300b3d56f1', '2016-06-06 13:18:21', '0:0:0:0:0:0:0:1');
INSERT INTO `jy_base_login_log` VALUES ('5c00f6cb182b452e99b97ef0baa80634', '097756af9fb440d795ec9c300b3d56f1', '2016-06-06 13:23:09', '0:0:0:0:0:0:0:1');
INSERT INTO `jy_base_login_log` VALUES ('59f6018da1de42469cfb317ea407081d', '097756af9fb440d795ec9c300b3d56f1', '2016-06-06 13:30:50', '0:0:0:0:0:0:0:1');
INSERT INTO `jy_base_login_log` VALUES ('6ac889346f8e4b49aa954af8d2299b2e', '097756af9fb440d795ec9c300b3d56f1', '2016-06-06 13:53:23', '0:0:0:0:0:0:0:1');
INSERT INTO `jy_base_login_log` VALUES ('2db804559a3046708de22d1bd37870b0', '097756af9fb440d795ec9c300b3d56f1', '2016-06-06 13:53:23', '0:0:0:0:0:0:0:1');
INSERT INTO `jy_base_login_log` VALUES ('ce20f0fb6d854b348a1365b4db986f92', '097756af9fb440d795ec9c300b3d56f1', '2016-06-06 13:54:25', '0:0:0:0:0:0:0:1');
INSERT INTO `jy_base_login_log` VALUES ('bdaf43ae479f47d3af32befbb3be5a75', '097756af9fb440d795ec9c300b3d56f1', '2016-06-06 13:58:31', '0:0:0:0:0:0:0:1');
INSERT INTO `jy_base_login_log` VALUES ('2a66e8ad92b248f6b1e37a1072552283', '097756af9fb440d795ec9c300b3d56f1', '2016-06-06 14:00:47', '0:0:0:0:0:0:0:1');
INSERT INTO `jy_base_login_log` VALUES ('48bfa0d5dbac47d4a8ef26cbc4d1d29d', '097756af9fb440d795ec9c300b3d56f1', '2016-06-06 14:00:47', '0:0:0:0:0:0:0:1');
INSERT INTO `jy_base_login_log` VALUES ('e98408c9dff944f689ea612218092ea1', '097756af9fb440d795ec9c300b3d56f1', '2016-06-06 14:02:41', '0:0:0:0:0:0:0:1');
INSERT INTO `jy_base_login_log` VALUES ('5385b24c345d4d7f8bab2e9ac2c52c14', '097756af9fb440d795ec9c300b3d56f1', '2016-06-06 14:06:06', '0:0:0:0:0:0:0:1');
INSERT INTO `jy_base_login_log` VALUES ('1978e10098d4446eae58b3e9f1f6da3a', '097756af9fb440d795ec9c300b3d56f1', '2016-06-06 14:11:26', '0:0:0:0:0:0:0:1');
INSERT INTO `jy_base_login_log` VALUES ('8baec11f5fff41a99140af5810b0eec8', '097756af9fb440d795ec9c300b3d56f1', '2016-06-06 14:11:26', '0:0:0:0:0:0:0:1');
INSERT INTO `jy_base_login_log` VALUES ('a10cc0553c5d458aae52412a1ccdb58a', '097756af9fb440d795ec9c300b3d56f1', '2016-06-06 14:13:16', '0:0:0:0:0:0:0:1');
INSERT INTO `jy_base_login_log` VALUES ('ad090f877c584100b7887f0db0d5e82b', '097756af9fb440d795ec9c300b3d56f1', '2016-06-06 14:15:39', '0:0:0:0:0:0:0:1');
INSERT INTO `jy_base_login_log` VALUES ('9df8b1a8f61945ce90c836f8ab7c4962', '097756af9fb440d795ec9c300b3d56f1', '2016-06-06 14:17:04', '0:0:0:0:0:0:0:1');
INSERT INTO `jy_base_login_log` VALUES ('204c4d71359f40cf9b0b9af1205ed81a', '097756af9fb440d795ec9c300b3d56f1', '2016-06-06 14:19:48', '0:0:0:0:0:0:0:1');
INSERT INTO `jy_base_login_log` VALUES ('8bd71d7c554042a2b270af712842064e', '097756af9fb440d795ec9c300b3d56f1', '2016-06-06 14:19:48', '0:0:0:0:0:0:0:1');
INSERT INTO `jy_base_login_log` VALUES ('79370aebca594cb4811070180cbb628a', '097756af9fb440d795ec9c300b3d56f1', '2016-06-06 14:20:41', '0:0:0:0:0:0:0:1');
INSERT INTO `jy_base_login_log` VALUES ('223f32e0773d45139babd84adfaffc3d', '097756af9fb440d795ec9c300b3d56f1', '2016-06-06 14:20:41', '0:0:0:0:0:0:0:1');
INSERT INTO `jy_base_login_log` VALUES ('883221ceb9ae4361827fc570fd9f749d', '097756af9fb440d795ec9c300b3d56f1', '2016-06-06 14:22:32', '0:0:0:0:0:0:0:1');
INSERT INTO `jy_base_login_log` VALUES ('48f14696d014482cab59f130f317abf2', '097756af9fb440d795ec9c300b3d56f1', '2016-06-06 15:47:04', '0:0:0:0:0:0:0:1');
INSERT INTO `jy_base_login_log` VALUES ('4840cafa259640d08756e928d110f315', '097756af9fb440d795ec9c300b3d56f1', '2016-06-06 15:47:41', '0:0:0:0:0:0:0:1');
INSERT INTO `jy_base_login_log` VALUES ('adb4a6a03be34e718aeebe0158696dae', '097756af9fb440d795ec9c300b3d56f1', '2016-06-06 15:47:41', '0:0:0:0:0:0:0:1');
INSERT INTO `jy_base_login_log` VALUES ('e09bb67cb53b4e0bb8c12bfc2db5a614', '097756af9fb440d795ec9c300b3d56f1', '2016-06-06 15:55:48', '0:0:0:0:0:0:0:1');
INSERT INTO `jy_base_login_log` VALUES ('45bf051a6b0d4e56b8e4bfda3cbebc1a', '097756af9fb440d795ec9c300b3d56f1', '2016-06-06 16:16:29', '0:0:0:0:0:0:0:1');
INSERT INTO `jy_base_login_log` VALUES ('db27dea891d649d786714d58efa04d17', '097756af9fb440d795ec9c300b3d56f1', '2016-06-06 16:18:32', '0:0:0:0:0:0:0:1');
INSERT INTO `jy_base_login_log` VALUES ('89ff0f1fd7b844ea9d27ace746cb8f41', '097756af9fb440d795ec9c300b3d56f1', '2016-06-06 16:38:51', '0:0:0:0:0:0:0:1');
INSERT INTO `jy_base_login_log` VALUES ('bf83ef9c4b624cc1977f7c01e68a410b', '097756af9fb440d795ec9c300b3d56f1', '2016-06-06 16:38:51', '0:0:0:0:0:0:0:1');
INSERT INTO `jy_base_login_log` VALUES ('e9c4cbf208b04471a70243dd5a8073af', '097756af9fb440d795ec9c300b3d56f1', '2016-06-06 16:40:09', '0:0:0:0:0:0:0:1');
INSERT INTO `jy_base_login_log` VALUES ('e0e8864f6a9d4c75a1062cf8b4f96526', '097756af9fb440d795ec9c300b3d56f1', '2016-06-07 14:50:11', '0:0:0:0:0:0:0:1');
INSERT INTO `jy_base_login_log` VALUES ('18529f0b5709420ea57b4c0e7225b606', '097756af9fb440d795ec9c300b3d56f1', '2016-06-07 14:50:11', '0:0:0:0:0:0:0:1');
INSERT INTO `jy_base_login_log` VALUES ('229b903fd2384d96b572a3fbcf0ba346', '097756af9fb440d795ec9c300b3d56f1', '2016-06-07 14:56:08', '0:0:0:0:0:0:0:1');
INSERT INTO `jy_base_login_log` VALUES ('0900615987444175adbaf9fc60acaffe', '097756af9fb440d795ec9c300b3d56f1', '2016-06-07 14:56:08', '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for jy_base_org
-- ----------------------------
DROP TABLE IF EXISTS `jy_base_org`;
CREATE TABLE `jy_base_org` (
  `id` varchar(32) NOT NULL,
  `pId` varchar(32) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `isValid` int(2) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jy_base_org
-- ----------------------------
INSERT INTO `jy_base_org` VALUES ('1', '0', 'MA集团', '1', '', '2015-12-09 23:01:36', '2016-06-06 16:31:33');
INSERT INTO `jy_base_org` VALUES ('10', '0', '合作伙伴', '1', '', '2015-12-09 23:22:29', '2015-12-10 14:44:48');
INSERT INTO `jy_base_org` VALUES ('11', '10', 'E酷快递', '1', null, '2015-12-09 23:24:45', null);
INSERT INTO `jy_base_org` VALUES ('12', '10', '嗨淘商城', '1', null, '2015-12-09 23:25:29', null);
INSERT INTO `jy_base_org` VALUES ('13', '10', '第一金融', '1', null, '2015-12-09 23:27:41', null);
INSERT INTO `jy_base_org` VALUES ('2', '1', '设计部', '1', null, '2015-12-09 23:22:09', null);
INSERT INTO `jy_base_org` VALUES ('3', '1', '商务部', '1', null, '2015-12-09 23:26:36', null);
INSERT INTO `jy_base_org` VALUES ('4', '1', '开发部', '1', null, '2015-12-09 23:20:59', null);

-- ----------------------------
-- Table structure for jy_base_org_resources
-- ----------------------------
DROP TABLE IF EXISTS `jy_base_org_resources`;
CREATE TABLE `jy_base_org_resources` (
  `org_Id` varchar(32) DEFAULT NULL,
  `resources_id` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jy_base_org_resources
-- ----------------------------
INSERT INTO `jy_base_org_resources` VALUES ('10', '1');
INSERT INTO `jy_base_org_resources` VALUES ('10', '2');
INSERT INTO `jy_base_org_resources` VALUES ('10', 'ae8fea67b8434987b718c51fe83bb45d');
INSERT INTO `jy_base_org_resources` VALUES ('10', '1e2cd5e710224cf0ba09bbe5d4ee0009');
INSERT INTO `jy_base_org_resources` VALUES ('10', 'fa04b6fe03944f2a9c040dbc9071af9b');
INSERT INTO `jy_base_org_resources` VALUES ('10', '83984d22f9da4991833f506695307b6f');
INSERT INTO `jy_base_org_resources` VALUES ('10', '89ff6bdf54ca4501a264adba8eb0ee39');
INSERT INTO `jy_base_org_resources` VALUES ('10', '15a7d1cec70c47bdbfd41c7f9c694481');
INSERT INTO `jy_base_org_resources` VALUES ('10', '3');
INSERT INTO `jy_base_org_resources` VALUES ('10', '4c97ca25b7e940948b3d403c3b091c81');
INSERT INTO `jy_base_org_resources` VALUES ('10', 'd22f22f0bc9a4ad2a6464cca6497a7b4');
INSERT INTO `jy_base_org_resources` VALUES ('10', '51aa6b0b037340b9ae88d22d2a354acf');
INSERT INTO `jy_base_org_resources` VALUES ('10', '4ee50d1722a34edeb6c15bdbf29e556b');
INSERT INTO `jy_base_org_resources` VALUES ('10', '42a2938acb5e4a26b5ec2cc6089b7c2e');
INSERT INTO `jy_base_org_resources` VALUES ('10', '5ba75d14f5b74572bd5ff35d12c58060');
INSERT INTO `jy_base_org_resources` VALUES ('10', '4');
INSERT INTO `jy_base_org_resources` VALUES ('10', '41');
INSERT INTO `jy_base_org_resources` VALUES ('10', '161848b9e16e410e9d3fac6f9c91cad5');
INSERT INTO `jy_base_org_resources` VALUES ('10', '28d4a82889c341bdaceab323b9549245');
INSERT INTO `jy_base_org_resources` VALUES ('10', '3f184c74379c4b668030ba9e55c9caa3');
INSERT INTO `jy_base_org_resources` VALUES ('10', '6d44f1553c424e5c81bd3ed9a4e162f3');
INSERT INTO `jy_base_org_resources` VALUES ('11', '1');
INSERT INTO `jy_base_org_resources` VALUES ('11', '2');
INSERT INTO `jy_base_org_resources` VALUES ('11', 'ae8fea67b8434987b718c51fe83bb45d');
INSERT INTO `jy_base_org_resources` VALUES ('11', '1e2cd5e710224cf0ba09bbe5d4ee0009');
INSERT INTO `jy_base_org_resources` VALUES ('11', 'fa04b6fe03944f2a9c040dbc9071af9b');
INSERT INTO `jy_base_org_resources` VALUES ('11', '83984d22f9da4991833f506695307b6f');
INSERT INTO `jy_base_org_resources` VALUES ('11', '89ff6bdf54ca4501a264adba8eb0ee39');
INSERT INTO `jy_base_org_resources` VALUES ('11', '15a7d1cec70c47bdbfd41c7f9c694481');
INSERT INTO `jy_base_org_resources` VALUES ('11', '3');
INSERT INTO `jy_base_org_resources` VALUES ('11', '4c97ca25b7e940948b3d403c3b091c81');
INSERT INTO `jy_base_org_resources` VALUES ('11', 'd22f22f0bc9a4ad2a6464cca6497a7b4');
INSERT INTO `jy_base_org_resources` VALUES ('11', '51aa6b0b037340b9ae88d22d2a354acf');
INSERT INTO `jy_base_org_resources` VALUES ('11', '4ee50d1722a34edeb6c15bdbf29e556b');
INSERT INTO `jy_base_org_resources` VALUES ('11', '42a2938acb5e4a26b5ec2cc6089b7c2e');
INSERT INTO `jy_base_org_resources` VALUES ('11', '5ba75d14f5b74572bd5ff35d12c58060');
INSERT INTO `jy_base_org_resources` VALUES ('11', '4');
INSERT INTO `jy_base_org_resources` VALUES ('11', '41');
INSERT INTO `jy_base_org_resources` VALUES ('11', '161848b9e16e410e9d3fac6f9c91cad5');
INSERT INTO `jy_base_org_resources` VALUES ('11', '28d4a82889c341bdaceab323b9549245');
INSERT INTO `jy_base_org_resources` VALUES ('11', '3f184c74379c4b668030ba9e55c9caa3');
INSERT INTO `jy_base_org_resources` VALUES ('11', '6d44f1553c424e5c81bd3ed9a4e162f3');
INSERT INTO `jy_base_org_resources` VALUES ('2', 'f4507225045e4ce1a64183053bff4d86');
INSERT INTO `jy_base_org_resources` VALUES ('2', 'a7e37cbf113d4753944dc9ac56aa0fd9');
INSERT INTO `jy_base_org_resources` VALUES ('2', '1');
INSERT INTO `jy_base_org_resources` VALUES ('2', '2');
INSERT INTO `jy_base_org_resources` VALUES ('2', 'ae8fea67b8434987b718c51fe83bb45d');
INSERT INTO `jy_base_org_resources` VALUES ('2', '1e2cd5e710224cf0ba09bbe5d4ee0009');
INSERT INTO `jy_base_org_resources` VALUES ('2', 'fa04b6fe03944f2a9c040dbc9071af9b');
INSERT INTO `jy_base_org_resources` VALUES ('2', '83984d22f9da4991833f506695307b6f');
INSERT INTO `jy_base_org_resources` VALUES ('2', '89ff6bdf54ca4501a264adba8eb0ee39');
INSERT INTO `jy_base_org_resources` VALUES ('2', '15a7d1cec70c47bdbfd41c7f9c694481');
INSERT INTO `jy_base_org_resources` VALUES ('2', '3');
INSERT INTO `jy_base_org_resources` VALUES ('2', '4c97ca25b7e940948b3d403c3b091c81');
INSERT INTO `jy_base_org_resources` VALUES ('2', 'd22f22f0bc9a4ad2a6464cca6497a7b4');
INSERT INTO `jy_base_org_resources` VALUES ('2', '51aa6b0b037340b9ae88d22d2a354acf');
INSERT INTO `jy_base_org_resources` VALUES ('2', '4ee50d1722a34edeb6c15bdbf29e556b');
INSERT INTO `jy_base_org_resources` VALUES ('2', '42a2938acb5e4a26b5ec2cc6089b7c2e');
INSERT INTO `jy_base_org_resources` VALUES ('2', '5ba75d14f5b74572bd5ff35d12c58060');
INSERT INTO `jy_base_org_resources` VALUES ('2', '4');
INSERT INTO `jy_base_org_resources` VALUES ('2', '41');
INSERT INTO `jy_base_org_resources` VALUES ('2', '161848b9e16e410e9d3fac6f9c91cad5');
INSERT INTO `jy_base_org_resources` VALUES ('2', '28d4a82889c341bdaceab323b9549245');
INSERT INTO `jy_base_org_resources` VALUES ('2', '3f184c74379c4b668030ba9e55c9caa3');
INSERT INTO `jy_base_org_resources` VALUES ('2', '6d44f1553c424e5c81bd3ed9a4e162f3');
INSERT INTO `jy_base_org_resources` VALUES ('1', 'f4507225045e4ce1a64183053bff4d86');
INSERT INTO `jy_base_org_resources` VALUES ('1', 'a7e37cbf113d4753944dc9ac56aa0fd9');
INSERT INTO `jy_base_org_resources` VALUES ('1', '1');
INSERT INTO `jy_base_org_resources` VALUES ('1', '2');
INSERT INTO `jy_base_org_resources` VALUES ('1', 'ae8fea67b8434987b718c51fe83bb45d');
INSERT INTO `jy_base_org_resources` VALUES ('1', '1e2cd5e710224cf0ba09bbe5d4ee0009');
INSERT INTO `jy_base_org_resources` VALUES ('1', 'fa04b6fe03944f2a9c040dbc9071af9b');
INSERT INTO `jy_base_org_resources` VALUES ('1', '83984d22f9da4991833f506695307b6f');
INSERT INTO `jy_base_org_resources` VALUES ('1', '89ff6bdf54ca4501a264adba8eb0ee39');
INSERT INTO `jy_base_org_resources` VALUES ('1', '15a7d1cec70c47bdbfd41c7f9c694481');
INSERT INTO `jy_base_org_resources` VALUES ('1', '3');
INSERT INTO `jy_base_org_resources` VALUES ('1', '4c97ca25b7e940948b3d403c3b091c81');
INSERT INTO `jy_base_org_resources` VALUES ('1', 'd22f22f0bc9a4ad2a6464cca6497a7b4');
INSERT INTO `jy_base_org_resources` VALUES ('1', '51aa6b0b037340b9ae88d22d2a354acf');
INSERT INTO `jy_base_org_resources` VALUES ('1', '4ee50d1722a34edeb6c15bdbf29e556b');
INSERT INTO `jy_base_org_resources` VALUES ('1', '42a2938acb5e4a26b5ec2cc6089b7c2e');
INSERT INTO `jy_base_org_resources` VALUES ('1', '5ba75d14f5b74572bd5ff35d12c58060');
INSERT INTO `jy_base_org_resources` VALUES ('1', '4');
INSERT INTO `jy_base_org_resources` VALUES ('1', '41');
INSERT INTO `jy_base_org_resources` VALUES ('1', '161848b9e16e410e9d3fac6f9c91cad5');
INSERT INTO `jy_base_org_resources` VALUES ('1', '28d4a82889c341bdaceab323b9549245');
INSERT INTO `jy_base_org_resources` VALUES ('1', '3f184c74379c4b668030ba9e55c9caa3');
INSERT INTO `jy_base_org_resources` VALUES ('1', '6d44f1553c424e5c81bd3ed9a4e162f3');
INSERT INTO `jy_base_org_resources` VALUES ('4', 'f4507225045e4ce1a64183053bff4d86');
INSERT INTO `jy_base_org_resources` VALUES ('4', 'a7e37cbf113d4753944dc9ac56aa0fd9');
INSERT INTO `jy_base_org_resources` VALUES ('4', '1');
INSERT INTO `jy_base_org_resources` VALUES ('4', '2');
INSERT INTO `jy_base_org_resources` VALUES ('4', 'ae8fea67b8434987b718c51fe83bb45d');
INSERT INTO `jy_base_org_resources` VALUES ('4', '1e2cd5e710224cf0ba09bbe5d4ee0009');
INSERT INTO `jy_base_org_resources` VALUES ('4', 'fa04b6fe03944f2a9c040dbc9071af9b');
INSERT INTO `jy_base_org_resources` VALUES ('4', '83984d22f9da4991833f506695307b6f');
INSERT INTO `jy_base_org_resources` VALUES ('4', '89ff6bdf54ca4501a264adba8eb0ee39');
INSERT INTO `jy_base_org_resources` VALUES ('4', '15a7d1cec70c47bdbfd41c7f9c694481');
INSERT INTO `jy_base_org_resources` VALUES ('4', '3');
INSERT INTO `jy_base_org_resources` VALUES ('4', '4c97ca25b7e940948b3d403c3b091c81');
INSERT INTO `jy_base_org_resources` VALUES ('4', 'd22f22f0bc9a4ad2a6464cca6497a7b4');
INSERT INTO `jy_base_org_resources` VALUES ('4', '51aa6b0b037340b9ae88d22d2a354acf');
INSERT INTO `jy_base_org_resources` VALUES ('4', '4ee50d1722a34edeb6c15bdbf29e556b');
INSERT INTO `jy_base_org_resources` VALUES ('4', '42a2938acb5e4a26b5ec2cc6089b7c2e');
INSERT INTO `jy_base_org_resources` VALUES ('4', '5ba75d14f5b74572bd5ff35d12c58060');
INSERT INTO `jy_base_org_resources` VALUES ('4', '4');
INSERT INTO `jy_base_org_resources` VALUES ('4', '41');
INSERT INTO `jy_base_org_resources` VALUES ('4', '161848b9e16e410e9d3fac6f9c91cad5');
INSERT INTO `jy_base_org_resources` VALUES ('4', '28d4a82889c341bdaceab323b9549245');
INSERT INTO `jy_base_org_resources` VALUES ('4', '3f184c74379c4b668030ba9e55c9caa3');
INSERT INTO `jy_base_org_resources` VALUES ('4', '6d44f1553c424e5c81bd3ed9a4e162f3');

-- ----------------------------
-- Table structure for jy_base_resources
-- ----------------------------
DROP TABLE IF EXISTS `jy_base_resources`;
CREATE TABLE `jy_base_resources` (
  `id` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT 'Id',
  `name` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '菜单名字',
  `parentId` varchar(32) CHARACTER SET utf8 NOT NULL DEFAULT '0' COMMENT '父Id',
  `type` int(4) NOT NULL DEFAULT '1' COMMENT '资源类型(1:为菜单，2:功能，3:按钮)',
  `resUrl` varchar(512) CHARACTER SET utf8 DEFAULT NULL COMMENT '菜单链接',
  `btnId` varchar(32) DEFAULT NULL,
  `btnFun` varchar(64) DEFAULT NULL,
  `icon` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '菜单Icon',
  `sort` int(10) DEFAULT NULL COMMENT '菜单顺序(由小到大排列)',
  `isValid` int(2) DEFAULT NULL COMMENT '是否有效(1:有效,0:无效)',
  `description` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '描述',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  `systemUrl` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of jy_base_resources
-- ----------------------------
INSERT INTO `jy_base_resources` VALUES ('1', '基础管理', '0', '1', '', '', '', 'icon-qrcode', '2', '1', '基础管理', '2015-07-15 16:35:17', '2015-11-24 16:23:59', '');
INSERT INTO `jy_base_resources` VALUES ('15a7d1cec70c47bdbfd41c7f9c694481', '删除', '2', '3', '/backstage/account/del', '', 'del', 'icon-remove-sign color-red', '4', '1', '', '2015-11-16 10:29:46', '2015-11-24 14:53:34', '');
INSERT INTO `jy_base_resources` VALUES ('161848b9e16e410e9d3fac6f9c91cad5', '批量删除', '4', '2', '/backstage/resources/delBatch', 'delBatchBtn', '', 'icon-trash color-red', '2', '1', '', '2015-11-11 16:01:20', '2015-11-13 15:21:27', '');
INSERT INTO `jy_base_resources` VALUES ('1e2cd5e710224cf0ba09bbe5d4ee0009', '批量删除', '2', '2', '/backstage/account/delBatch', 'delBatchBtn', '', 'icon-trash color-red', '2', '1', '', '2015-11-16 09:36:13', '2015-11-24 14:53:48', '');
INSERT INTO `jy_base_resources` VALUES ('2', '用户管理', '1', '1', '/backstage/account/index', null, null, 'icon-group', '1', '1', '用户管理', '2015-07-15 16:35:19', null, '');
INSERT INTO `jy_base_resources` VALUES ('28d4a82889c341bdaceab323b9549245', '查看', '4', '3', '/backstage/resources/find', '', 'check', 'icon-zoom-in color-purple', '1', '1', '', '2015-11-12 15:33:53', '2015-11-12 16:19:30', '');
INSERT INTO `jy_base_resources` VALUES ('3', '组织管理', '1', '1', '/backstage/role/index', '', '', 'icon-github-alt', '2', '1', '组织管理', '2015-07-15 16:35:21', '2015-12-07 18:38:57', '');
INSERT INTO `jy_base_resources` VALUES ('3f184c74379c4b668030ba9e55c9caa3', '修改', '4', '3', '/backstage/resources/update', '', 'edit', 'icon-edit color-blue', '2', '1', '', '2015-11-11 16:50:47', '2015-11-12 16:19:24', '');
INSERT INTO `jy_base_resources` VALUES ('4', '菜单管理', '1', '1', '/backstage/resources/index', null, null, 'icon-th-large', '3', '1', '菜单管理', '2015-07-15 16:35:24', null, '');
INSERT INTO `jy_base_resources` VALUES ('41', '增加', '4', '2', '/backstage/resources/add', 'addBtn', '', 'icon-plus-sign color-green', '1', '1', '增加', '2015-11-06 11:21:29', '2015-11-12 17:32:36', '');
INSERT INTO `jy_base_resources` VALUES ('42a2938acb5e4a26b5ec2cc6089b7c2e', '修改角色', '3', '3', '/backstage/role/update', '', 'edit', 'icon-edit color-blue', '3', '1', '', '2015-11-13 17:13:52', '2015-12-10 09:58:46', '');
INSERT INTO `jy_base_resources` VALUES ('4c97ca25b7e940948b3d403c3b091c81', '增加角色', '3', '2', '/backstage/role/add', 'addBtn', '', 'icon-plus-sign color-green', '1', '1', '', '2015-11-13 10:12:20', '2015-12-10 09:57:51', '');
INSERT INTO `jy_base_resources` VALUES ('4ee50d1722a34edeb6c15bdbf29e556b', '查看角色', '3', '3', '/backstage/role/find', '', 'check', 'icon-zoom-in color-purple', '2', '1', '', '2015-11-13 16:27:36', '2015-12-10 09:58:15', '');
INSERT INTO `jy_base_resources` VALUES ('51aa6b0b037340b9ae88d22d2a354acf', '授权角色权限', '3', '3', '/backstage/role/listAuthorized', '', 'authorized', 'icon-key color-dark-green', '1', '1', '', '2015-11-13 17:41:57', '2015-12-10 09:58:29', '');
INSERT INTO `jy_base_resources` VALUES ('5ba75d14f5b74572bd5ff35d12c58060', '删除角色', '3', '3', '/backstage/role/del', '', 'del', 'icon-remove-sign color-red', '4', '1', '', '2015-11-13 17:21:08', '2015-12-10 09:58:38', '');
INSERT INTO `jy_base_resources` VALUES ('6d44f1553c424e5c81bd3ed9a4e162f3', '删除', '4', '3', '/backstage/resources/del', '', 'del', 'icon-remove-sign color-red', '3', '1', '', '2015-11-12 16:23:58', '2015-11-12 16:31:33', '');
INSERT INTO `jy_base_resources` VALUES ('83984d22f9da4991833f506695307b6f', '查看', '2', '3', '/backstage/account/find', '', 'check', 'icon-zoom-in color-purple', '2', '1', '', '2015-11-16 09:49:48', '2015-11-24 14:52:57', '');
INSERT INTO `jy_base_resources` VALUES ('89ff6bdf54ca4501a264adba8eb0ee39', '修改', '2', '3', '/backstage/account/update', '', 'edit', 'icon-edit color-blue', '3', '1', '', '2015-11-16 10:29:15', '2015-11-24 14:53:21', '');
INSERT INTO `jy_base_resources` VALUES ('a7e37cbf113d4753944dc9ac56aa0fd9', '入库管理', 'f4507225045e4ce1a64183053bff4d86', '1', '/governance/services', '', '', 'icon-file-text-alt ', '1', '1', '', '2016-06-06 13:35:20', '2016-06-06 16:39:54', null);
INSERT INTO `jy_base_resources` VALUES ('ae8fea67b8434987b718c51fe83bb45d', '增加', '2', '2', '/backstage/account/add', 'addBtn', '', 'icon-plus-sign color-green', '1', '1', '', '2015-11-15 17:34:33', '2015-11-24 14:52:44', '');
INSERT INTO `jy_base_resources` VALUES ('d22f22f0bc9a4ad2a6464cca6497a7b4', '批量删除角色', '3', '2', '/backstage/role/delBatch', 'delBatchBtn', '', 'icon-trash color-red', '2', '1', '', '2015-11-13 15:21:16', '2015-12-10 09:58:05', '');
INSERT INTO `jy_base_resources` VALUES ('f4507225045e4ce1a64183053bff4d86', '仓库管理系统', '0', '1', 'http://192.168.99.202:8081', '', '', 'icon-tasks ', '1', '1', '', '2016-06-06 13:34:32', '2016-06-06 16:38:27', null);
INSERT INTO `jy_base_resources` VALUES ('fa04b6fe03944f2a9c040dbc9071af9b', '密码重置', '2', '3', '/backstage/account/resetPwd', '', 'resetPwd', 'icon-unlock-alt color-pale-orange', '1', '1', '', '2015-11-16 10:49:17', '2015-11-26 17:16:54', '');

-- ----------------------------
-- Table structure for jy_base_role
-- ----------------------------
DROP TABLE IF EXISTS `jy_base_role`;
CREATE TABLE `jy_base_role` (
  `id` varchar(32) NOT NULL,
  `orgId` varchar(32) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `isValid` int(2) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jy_base_role
-- ----------------------------
INSERT INTO `jy_base_role` VALUES ('0f9600afc9274f74b16db37a784a84dc', '11', '业务员', '1', '', '2015-12-14 23:07:42', null);
INSERT INTO `jy_base_role` VALUES ('1', '2', '普通角色', '1', '普通角色', null, '2015-11-24 15:12:08');
INSERT INTO `jy_base_role` VALUES ('2', '2', '管理员', '1', '管理员', null, null);
INSERT INTO `jy_base_role` VALUES ('3', '4', '超级管理员', '1', '超级管理员', null, '2015-12-10 01:28:51');
INSERT INTO `jy_base_role` VALUES ('4', '4', '开发者', '1', '开发者', null, null);

-- ----------------------------
-- Table structure for jy_base_role_resources
-- ----------------------------
DROP TABLE IF EXISTS `jy_base_role_resources`;
CREATE TABLE `jy_base_role_resources` (
  `role_id` varchar(32) NOT NULL,
  `resources_id` varchar(32) NOT NULL,
  PRIMARY KEY (`role_id`,`resources_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jy_base_role_resources
-- ----------------------------
INSERT INTO `jy_base_role_resources` VALUES ('1', '1');
INSERT INTO `jy_base_role_resources` VALUES ('1', '2');
INSERT INTO `jy_base_role_resources` VALUES ('2', '1');
INSERT INTO `jy_base_role_resources` VALUES ('2', '15a7d1cec70c47bdbfd41c7f9c694481');
INSERT INTO `jy_base_role_resources` VALUES ('2', '161848b9e16e410e9d3fac6f9c91cad5');
INSERT INTO `jy_base_role_resources` VALUES ('2', '1e2cd5e710224cf0ba09bbe5d4ee0009');
INSERT INTO `jy_base_role_resources` VALUES ('2', '2');
INSERT INTO `jy_base_role_resources` VALUES ('2', '28d4a82889c341bdaceab323b9549245');
INSERT INTO `jy_base_role_resources` VALUES ('2', '3');
INSERT INTO `jy_base_role_resources` VALUES ('2', '3f184c74379c4b668030ba9e55c9caa3');
INSERT INTO `jy_base_role_resources` VALUES ('2', '4');
INSERT INTO `jy_base_role_resources` VALUES ('2', '41');
INSERT INTO `jy_base_role_resources` VALUES ('2', '42a2938acb5e4a26b5ec2cc6089b7c2e');
INSERT INTO `jy_base_role_resources` VALUES ('2', '4c97ca25b7e940948b3d403c3b091c81');
INSERT INTO `jy_base_role_resources` VALUES ('2', '4ee50d1722a34edeb6c15bdbf29e556b');
INSERT INTO `jy_base_role_resources` VALUES ('2', '51aa6b0b037340b9ae88d22d2a354acf');
INSERT INTO `jy_base_role_resources` VALUES ('2', '5ba75d14f5b74572bd5ff35d12c58060');
INSERT INTO `jy_base_role_resources` VALUES ('2', '6d44f1553c424e5c81bd3ed9a4e162f3');
INSERT INTO `jy_base_role_resources` VALUES ('2', '83984d22f9da4991833f506695307b6f');
INSERT INTO `jy_base_role_resources` VALUES ('2', '89ff6bdf54ca4501a264adba8eb0ee39');
INSERT INTO `jy_base_role_resources` VALUES ('2', 'ae8fea67b8434987b718c51fe83bb45d');
INSERT INTO `jy_base_role_resources` VALUES ('2', 'd22f22f0bc9a4ad2a6464cca6497a7b4');
INSERT INTO `jy_base_role_resources` VALUES ('2', 'fa04b6fe03944f2a9c040dbc9071af9b');
INSERT INTO `jy_base_role_resources` VALUES ('3', '1');
INSERT INTO `jy_base_role_resources` VALUES ('3', '15a7d1cec70c47bdbfd41c7f9c694481');
INSERT INTO `jy_base_role_resources` VALUES ('3', '161848b9e16e410e9d3fac6f9c91cad5');
INSERT INTO `jy_base_role_resources` VALUES ('3', '1e2cd5e710224cf0ba09bbe5d4ee0009');
INSERT INTO `jy_base_role_resources` VALUES ('3', '2');
INSERT INTO `jy_base_role_resources` VALUES ('3', '28d4a82889c341bdaceab323b9549245');
INSERT INTO `jy_base_role_resources` VALUES ('3', '3');
INSERT INTO `jy_base_role_resources` VALUES ('3', '3f184c74379c4b668030ba9e55c9caa3');
INSERT INTO `jy_base_role_resources` VALUES ('3', '4');
INSERT INTO `jy_base_role_resources` VALUES ('3', '41');
INSERT INTO `jy_base_role_resources` VALUES ('3', '42a2938acb5e4a26b5ec2cc6089b7c2e');
INSERT INTO `jy_base_role_resources` VALUES ('3', '4c97ca25b7e940948b3d403c3b091c81');
INSERT INTO `jy_base_role_resources` VALUES ('3', '4ee50d1722a34edeb6c15bdbf29e556b');
INSERT INTO `jy_base_role_resources` VALUES ('3', '51aa6b0b037340b9ae88d22d2a354acf');
INSERT INTO `jy_base_role_resources` VALUES ('3', '5ba75d14f5b74572bd5ff35d12c58060');
INSERT INTO `jy_base_role_resources` VALUES ('3', '6d44f1553c424e5c81bd3ed9a4e162f3');
INSERT INTO `jy_base_role_resources` VALUES ('3', '83984d22f9da4991833f506695307b6f');
INSERT INTO `jy_base_role_resources` VALUES ('3', '89ff6bdf54ca4501a264adba8eb0ee39');
INSERT INTO `jy_base_role_resources` VALUES ('3', 'a7e37cbf113d4753944dc9ac56aa0fd9');
INSERT INTO `jy_base_role_resources` VALUES ('3', 'ae8fea67b8434987b718c51fe83bb45d');
INSERT INTO `jy_base_role_resources` VALUES ('3', 'd22f22f0bc9a4ad2a6464cca6497a7b4');
INSERT INTO `jy_base_role_resources` VALUES ('3', 'f4507225045e4ce1a64183053bff4d86');
INSERT INTO `jy_base_role_resources` VALUES ('3', 'fa04b6fe03944f2a9c040dbc9071af9b');
INSERT INTO `jy_base_role_resources` VALUES ('4', '1');
INSERT INTO `jy_base_role_resources` VALUES ('4', '15a7d1cec70c47bdbfd41c7f9c694481');
INSERT INTO `jy_base_role_resources` VALUES ('4', '161848b9e16e410e9d3fac6f9c91cad5');
INSERT INTO `jy_base_role_resources` VALUES ('4', '1e2cd5e710224cf0ba09bbe5d4ee0009');
INSERT INTO `jy_base_role_resources` VALUES ('4', '2');
INSERT INTO `jy_base_role_resources` VALUES ('4', '28d4a82889c341bdaceab323b9549245');
INSERT INTO `jy_base_role_resources` VALUES ('4', '3');
INSERT INTO `jy_base_role_resources` VALUES ('4', '3f184c74379c4b668030ba9e55c9caa3');
INSERT INTO `jy_base_role_resources` VALUES ('4', '4');
INSERT INTO `jy_base_role_resources` VALUES ('4', '41');
INSERT INTO `jy_base_role_resources` VALUES ('4', '42a2938acb5e4a26b5ec2cc6089b7c2e');
INSERT INTO `jy_base_role_resources` VALUES ('4', '4c97ca25b7e940948b3d403c3b091c81');
INSERT INTO `jy_base_role_resources` VALUES ('4', '4ee50d1722a34edeb6c15bdbf29e556b');
INSERT INTO `jy_base_role_resources` VALUES ('4', '51aa6b0b037340b9ae88d22d2a354acf');
INSERT INTO `jy_base_role_resources` VALUES ('4', '5ba75d14f5b74572bd5ff35d12c58060');
INSERT INTO `jy_base_role_resources` VALUES ('4', '6d44f1553c424e5c81bd3ed9a4e162f3');
INSERT INTO `jy_base_role_resources` VALUES ('4', '83984d22f9da4991833f506695307b6f');
INSERT INTO `jy_base_role_resources` VALUES ('4', '89ff6bdf54ca4501a264adba8eb0ee39');
INSERT INTO `jy_base_role_resources` VALUES ('4', 'ae8fea67b8434987b718c51fe83bb45d');
INSERT INTO `jy_base_role_resources` VALUES ('4', 'd22f22f0bc9a4ad2a6464cca6497a7b4');
INSERT INTO `jy_base_role_resources` VALUES ('4', 'fa04b6fe03944f2a9c040dbc9071af9b');

-- ----------------------------
-- Table structure for jy_data_dict
-- ----------------------------
DROP TABLE IF EXISTS `jy_data_dict`;
CREATE TABLE `jy_data_dict` (
  `id` varchar(32) NOT NULL,
  `dataKey` varchar(32) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  `isValid` int(2) NOT NULL DEFAULT '0',
  `description` varchar(128) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jy_data_dict
-- ----------------------------
INSERT INTO `jy_data_dict` VALUES ('741cae2856aa43d3bb6cc60c8ed2d8a9', 'isTask', '任务状态', '1', '任务使用', '2015-12-24 13:01:11', '2015-12-30 20:12:35');
INSERT INTO `jy_data_dict` VALUES ('aac903e055014b68a3f09f8eb9ace6bc', 'logType', '日志类型', '1', '日志使用', '2015-12-24 20:39:15', '2015-12-24 20:39:25');
INSERT INTO `jy_data_dict` VALUES ('d6336e1e3ba64dfd885e930eb9d88e32', 'isValid', '状态', '1', '全局使用', '2015-11-20 15:56:20', '2015-12-09 20:56:38');
INSERT INTO `jy_data_dict` VALUES ('ebf0c2e03e0340048ee53e3e7c3f0805', 'wxSubscribe', '订阅情况', '1', '微信关注者使用', '2015-12-30 15:33:27', null);

-- ----------------------------
-- Table structure for jy_data_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `jy_data_dict_item`;
CREATE TABLE `jy_data_dict_item` (
  `dictId` varchar(32) NOT NULL,
  `value` varchar(64) NOT NULL,
  `name` varchar(32) NOT NULL,
  `sort` int(11) NOT NULL,
  PRIMARY KEY (`dictId`,`value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jy_data_dict_item
-- ----------------------------
INSERT INTO `jy_data_dict_item` VALUES ('741cae2856aa43d3bb6cc60c8ed2d8a9', '0', '停用', '2');
INSERT INTO `jy_data_dict_item` VALUES ('741cae2856aa43d3bb6cc60c8ed2d8a9', '1', '启用', '1');
INSERT INTO `jy_data_dict_item` VALUES ('aac903e055014b68a3f09f8eb9ace6bc', '1', '正常', '1');
INSERT INTO `jy_data_dict_item` VALUES ('aac903e055014b68a3f09f8eb9ace6bc', '2', '异常', '2');
INSERT INTO `jy_data_dict_item` VALUES ('d6336e1e3ba64dfd885e930eb9d88e32', '0', '无效', '2');
INSERT INTO `jy_data_dict_item` VALUES ('d6336e1e3ba64dfd885e930eb9d88e32', '1', '有效', '1');
INSERT INTO `jy_data_dict_item` VALUES ('ebf0c2e03e0340048ee53e3e7c3f0805', '0', '退订', '2');
INSERT INTO `jy_data_dict_item` VALUES ('ebf0c2e03e0340048ee53e3e7c3f0805', '1', '订阅', '1');
