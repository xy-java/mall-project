/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 04/07/2022 19:48:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address_info
-- ----------------------------
DROP TABLE IF EXISTS `address_info`;
CREATE TABLE `address_info`  (
  `address_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货地址id',
  `address_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货地址',
  `user_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户_id',
  `address_status` int NULL DEFAULT NULL COMMENT '是否启用(0 1 启用)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `address_level1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '第一级省',
  `address_level2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '第二级市',
  `address_level3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '第三级',
  `derive_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人',
  PRIMARY KEY (`address_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of address_info
-- ----------------------------
INSERT INTO `address_info` VALUES ('00fd6ddf5aff48eb', '西城绿魁花园', '25e0bb98c5a14d4b', 1, '2022-06-16 17:39:47', '110000', '110100', '110102', '李四');
INSERT INTO `address_info` VALUES ('32581b8f629540f0', '天津工业大学', 'baadcb013dc4473e', 1, '2022-06-29 13:34:26', '120000', '120100', '120111', '肖扬');
INSERT INTO `address_info` VALUES ('4163f7af671d49dc', '宾水西道388', 'cff8aa9316954ee4', 0, '2022-07-04 16:42:53', '120000', '120100', '120111', 'wjy');
INSERT INTO `address_info` VALUES ('5ee9ca5be3e24042', '富力又一城', '2e24c8b6abb743c0', 1, '2022-06-28 13:43:47', '120000', '120100', '120104', 'ss');
INSERT INTO `address_info` VALUES ('6046b79d642e40c3', '周边', '25e0bb98c5a14d4b', 1, '2022-06-28 12:15:03', '130000', '130300', '130306', '小明');
INSERT INTO `address_info` VALUES ('acdae728d3fb4427', '天津电子信息职业技术学院', 'b3f9dff4886347fb', 0, '2022-07-04 17:00:22', '120000', '120100', '120112', '刘德华');
INSERT INTO `address_info` VALUES ('cccc18909d974b0e', '我爱我家', '25e0bb98c5a14d4b', 0, '2022-06-20 14:52:42', '110000', '110100', '110106', '王五');
INSERT INTO `address_info` VALUES ('d280aa9d1d72469c', '东城绿魁花园', '25e0bb98c5a14d4b', 1, '2022-06-15 12:50:25', '110000', '110100', '110101', '张三');

-- ----------------------------
-- Table structure for cart_info
-- ----------------------------
DROP TABLE IF EXISTS `cart_info`;
CREATE TABLE `cart_info`  (
  `cart_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '购物车id（uuid）',
  `user_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `sku_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品id',
  `cart_num` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '数量',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `address_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户地址id',
  `sku_version` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机版本',
  `sku_color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机颜色',
  `sku_cp` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电脑配置',
  `sku_series` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手表配置',
  PRIMARY KEY (`cart_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cart_info
-- ----------------------------
INSERT INTO `cart_info` VALUES ('f21532b63aa6498f', 'b3f9dff4886347fb', '56fbc933a44b4392', 1, '2022-07-04 17:29:47', 'acdae728d3fb4427', '8GB+256GB', '时光蓝', '', '');

-- ----------------------------
-- Table structure for comment_info
-- ----------------------------
DROP TABLE IF EXISTS `comment_info`;
CREATE TABLE `comment_info`  (
  `comment_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评价id',
  `user_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `sku_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品id',
  `order_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单编号',
  `appraise` int NOT NULL COMMENT '评价（1好评 2中评 3差评）',
  `comment_txt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评价内容',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comment_info
-- ----------------------------

-- ----------------------------
-- Table structure for favor_info
-- ----------------------------
DROP TABLE IF EXISTS `favor_info`;
CREATE TABLE `favor_info`  (
  `favor_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收藏id(uuid)',
  `user_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `sku_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品id',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`favor_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of favor_info
-- ----------------------------

-- ----------------------------
-- Table structure for hive_analys
-- ----------------------------
DROP TABLE IF EXISTS `hive_analys`;
CREATE TABLE `hive_analys`  (
  `x_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `y_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hive_analys
-- ----------------------------
INSERT INTO `hive_analys` VALUES ('2022-06-28', '7', 'date');
INSERT INTO `hive_analys` VALUES ('2022-06-29', '4', 'date');
INSERT INTO `hive_analys` VALUES ('2022-06-30', '3', 'date');
INSERT INTO `hive_analys` VALUES ('2022-07-01', '5', 'date');
INSERT INTO `hive_analys` VALUES ('2022-07-02', '4', 'date');
INSERT INTO `hive_analys` VALUES ('2022-07-03', '3', 'date');
INSERT INTO `hive_analys` VALUES ('2022-07-04', '12', 'date');
INSERT INTO `hive_analys` VALUES ('手机', '18', 'num');
INSERT INTO `hive_analys` VALUES ('手表', '6', 'num');
INSERT INTO `hive_analys` VALUES ('电脑', '14', 'num');
INSERT INTO `hive_analys` VALUES ('手机', '32350.0', 'price');
INSERT INTO `hive_analys` VALUES ('手表', '3694.0', 'price');
INSERT INTO `hive_analys` VALUES ('电脑', '79086.0', 'price');

-- ----------------------------
-- Table structure for hive_count
-- ----------------------------
DROP TABLE IF EXISTS `hive_count`;
CREATE TABLE `hive_count`  (
  `count` int NULL DEFAULT NULL COMMENT '数量',
  `sumPrice` decimal(10, 2) NULL DEFAULT NULL COMMENT '总金额'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hive_count
-- ----------------------------
INSERT INTO `hive_count` VALUES (3, 115130.00);

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`  (
  `detail_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单明细id（uuid）',
  `order_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单编号',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `sku_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品id',
  `order_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '购买价格',
  `order_num` int NULL DEFAULT NULL COMMENT '购买数量',
  `sku_version` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sku_color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sku_cp` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sku_series` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`detail_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES ('1231cb07250a49e3', '32510b8a628c48ab', '2022-06-30 17:29:20', 'b745d704d9454968', 999.00, 1, '4GB+128GB', '暗影黑', '', '');
INSERT INTO `order_detail` VALUES ('12ead9ae9e524eb6', 'ff752b9e51cc4808', '2022-07-02 17:27:59', '21f89308273b4c37', 399.00, 1, '', '', '', '深空蓝');
INSERT INTO `order_detail` VALUES ('148981093c24419a', '32a0067f363e4c54', '2022-07-04 17:23:23', 'a7727c4d780d4e41', 5799.00, 1, '', '', 'i5-12450H/集显/16G/512G', '');
INSERT INTO `order_detail` VALUES ('175be8c8aaef442e', 'ae0d42f1766f4db9', '2022-07-01 13:34:49', 'a7727c4d780d4e41', 5799.00, 1, '', '', 'i5-12450H/集显/16G/512G', '');
INSERT INTO `order_detail` VALUES ('1ad13a9f9e9548c6', '340774ca5ed84bc5', '2022-07-01 13:32:56', '1e86413b50244cde', 5299.00, 1, '', '', 'i5-12450H/集显/16G/512G', '');
INSERT INTO `order_detail` VALUES ('2633cd1cef754e9c', 'a325ca96dd3e455f', '2022-06-29 17:29:54', '56fbc933a44b4392', 2199.00, 1, '6GB+128GB', '时光蓝', '', '');
INSERT INTO `order_detail` VALUES ('3370dbac74c04665', '6da78400b7054f97', '2022-06-28 17:33:55', '6b35aa76a7954205', 2191.00, 1, '8GB+128GB', '亮黑', 'sada', '');
INSERT INTO `order_detail` VALUES ('42841712a5214ef0', '6da78400b7054f97', '2022-06-28 17:33:55', '56fbc933a44b4392', 2199.00, 3, '8GB+256GB', '时光蓝', '', '');
INSERT INTO `order_detail` VALUES ('4e19cc84869442cb', 'b0d005d6493c4bbd', '2022-07-04 17:22:53', '1e86413b50244cde', 5299.00, 1, '', '', 'i5-12450H/集显/16G/512G', '');
INSERT INTO `order_detail` VALUES ('53ca9a2f179b48dc', '77655d9eca6f4e1d', '2022-07-04 17:20:31', '1e86413b50244cde', 5299.00, 1, '', '', 'i5-12450H/集显/16G/512G', '');
INSERT INTO `order_detail` VALUES ('58f984f0f77445e6', '38b5b790233e48cb', '2022-06-28 17:33:13', '56fbc933a44b4392', 2199.00, 1, '6GB+128GB', '时光蓝', '', '');
INSERT INTO `order_detail` VALUES ('5ce23e0fe0a544df', 'b0d005d6493c4bbd', '2022-07-04 17:22:53', '1e86413b50244cde', 5299.00, 1, '', '', 'i7-12650H/RTX 2050/16G/512G', '');
INSERT INTO `order_detail` VALUES ('6b7323cd4e164ec2', 'e9db34acfdf74286', '2022-07-04 17:24:30', '56fbc933a44b4392', 2199.00, 2, '6GB+128GB', '原子银', '', '');
INSERT INTO `order_detail` VALUES ('6c2d239e601c4bfe', '8c7889261cc04a1a', '2022-06-29 17:32:16', 'b745d704d9454968', 999.00, 3, '4GB+128GB', '暗影黑', '', '');
INSERT INTO `order_detail` VALUES ('742ffe31c2ef4b3e', '3acfe7e254e94d09', '2022-07-04 17:23:52', '21f89308273b4c37', 399.00, 1, '', '', '', '深空蓝');
INSERT INTO `order_detail` VALUES ('8851dcbbef0849a2', '77405b7dcc8e45b6', '2022-07-03 17:25:00', '56fbc933a44b4392', 2199.00, 1, '6GB+128GB', '时光蓝', '', '');
INSERT INTO `order_detail` VALUES ('895243265fc146a9', '25769ac8f4824419', '2022-07-03 17:25:31', 'b58d8138370a4c9a', 1049.00, 1, '', '', '', '曜石黑 黑色氟橡胶表带');
INSERT INTO `order_detail` VALUES ('8fe4b2017b224c49', '38e1a2bddffd46f3', '2022-07-04 17:21:13', '1e86413b50244cde', 5299.00, 1, '', '', 'i5-12450H/集显/16G/512G', '');
INSERT INTO `order_detail` VALUES ('926f3d5a6f4a4e2e', 'ff752b9e51cc4808', '2022-07-02 17:27:59', '21f89308273b4c37', 399.00, 1, '', '', '', '象牙白');
INSERT INTO `order_detail` VALUES ('976e4db974e34654', 'fe16c9e8161d4ecf', '2022-07-04 16:35:59', 'a7727c4d780d4e41', 5799.00, 1, '', '', 'i5-12450H/集显/16G/512G', '');
INSERT INTO `order_detail` VALUES ('99bd2c823c744868', 'b0d005d6493c4bbd', '2022-07-04 17:22:53', '56bcd2926c6d4e0c', 6999.00, 1, '', '', 'i5-12450H/集显/16G/512G', '');
INSERT INTO `order_detail` VALUES ('b7a2889b88114a71', 'fe2b43d85f1f421e', '2022-07-01 17:02:24', '64ab619905cf419e', 4299.00, 1, '', '', 'i5-12450H/集显/16G/512G', '');
INSERT INTO `order_detail` VALUES ('c1c5fba6c5ed4af6', 'fcbe090948a146d0', '2022-07-01 17:28:33', 'b58d8138370a4c9a', 1049.00, 1, '', '', '', '曜石黑 黑色氟橡胶表带');
INSERT INTO `order_detail` VALUES ('c40b9e07e9ba4618', '38b5b790233e48cb', '2022-06-28 17:33:13', '6b35aa76a7954205', 2191.00, 2, '8GB+128GB', '亮黑', 'sada', '');
INSERT INTO `order_detail` VALUES ('c866806fcae4455d', '04d967886de14551', '2022-07-04 17:18:54', 'a7727c4d780d4e41', 5799.00, 1, '', '', 'i5-12450H/集显/16G/512G', '');
INSERT INTO `order_detail` VALUES ('d019f60c20214801', '00d226b811a741a2', '2022-07-02 17:26:09', '6b35aa76a7954205', 2191.00, 1, '8GB+128GB', '亮黑', 'sada', '');
INSERT INTO `order_detail` VALUES ('d5b7c3e8a43c4dea', '340774ca5ed84bc5', '2022-07-01 13:32:56', '1e86413b50244cde', 5299.00, 1, '', '', 'i7-12650H/RTX 2050/16G/512G', '');
INSERT INTO `order_detail` VALUES ('d97aec9efcb24250', 'a49eee88be614971', '2022-07-04 17:11:06', 'a7727c4d780d4e41', 5799.00, 1, '', '', 'i5-12450H/集显/16G/512G', '');
INSERT INTO `order_detail` VALUES ('e27f8d917c7c469a', '32510b8a628c48ab', '2022-06-30 17:29:20', 'b745d704d9454968', 999.00, 2, '8GB+128GB', '暗影黑', '', '');
INSERT INTO `order_detail` VALUES ('e44e692fcb7e4061', '38e1a2bddffd46f3', '2022-07-03 17:21:13', '56bcd2926c6d4e0c', 6999.00, 1, '', '', 'i5-12450H/集显/16G/512G', '');
INSERT INTO `order_detail` VALUES ('f0559427d6ee4867', '340774ca5ed84bc5', '2022-07-02 13:32:56', '21f89308273b4c37', 399.00, 1, '', '', '', '深空蓝');

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `order_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单id(uuid)',
  `total_amount` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '总金额',
  `user_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `payment_way` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款方式',
  `order_status` int NOT NULL COMMENT '订单状态（1已支付，0未支付）',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `address_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址id',
  `isEnd` int NULL DEFAULT NULL COMMENT '是否结束（1 已完成， 0 未完成）',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES ('00d226b811a741a2', 2191.00, 'cff8aa9316954ee4', '在线支付', 1, '2022-07-02 17:26:09', '4163f7af671d49dc', 0);
INSERT INTO `order_info` VALUES ('04d967886de14551', 5799.00, 'b3f9dff4886347fb', '在线支付', 1, '2022-07-04 17:18:54', 'acdae728d3fb4427', 0);
INSERT INTO `order_info` VALUES ('25769ac8f4824419', 1049.00, 'cff8aa9316954ee4', '在线支付', 1, '2022-07-03 17:25:31', '4163f7af671d49dc', 0);
INSERT INTO `order_info` VALUES ('32510b8a628c48ab', 2997.00, 'b3f9dff4886347fb', '在线支付', 1, '2022-06-30 17:29:20', 'acdae728d3fb4427', 0);
INSERT INTO `order_info` VALUES ('32a0067f363e4c54', 5799.00, 'cff8aa9316954ee4', '在线支付', 1, '2022-07-04 17:23:23', '4163f7af671d49dc', 0);
INSERT INTO `order_info` VALUES ('340774ca5ed84bc5', 10997.00, '25e0bb98c5a14d4b', '在线支付', 1, '2022-07-01 13:32:56', 'cccc18909d974b0e', 1);
INSERT INTO `order_info` VALUES ('38b5b790233e48cb', 6581.00, '25e0bb98c5a14d4b', '在线支付', 1, '2022-06-28 17:33:13', 'cccc18909d974b0e', 0);
INSERT INTO `order_info` VALUES ('38e1a2bddffd46f3', 12298.00, 'b3f9dff4886347fb', '在线支付', 1, '2022-07-04 17:21:13', 'acdae728d3fb4427', 0);
INSERT INTO `order_info` VALUES ('3acfe7e254e94d09', 399.00, 'cff8aa9316954ee4', '在线支付', 1, '2022-07-04 17:23:52', '4163f7af671d49dc', 0);
INSERT INTO `order_info` VALUES ('6da78400b7054f97', 8788.00, '25e0bb98c5a14d4b', '在线支付', 1, '2022-06-28 17:33:55', 'cccc18909d974b0e', 0);
INSERT INTO `order_info` VALUES ('77405b7dcc8e45b6', 2199.00, 'cff8aa9316954ee4', '在线支付', 1, '2022-07-03 17:25:00', '4163f7af671d49dc', 0);
INSERT INTO `order_info` VALUES ('77655d9eca6f4e1d', 5299.00, 'b3f9dff4886347fb', '在线支付', 1, '2022-07-04 17:20:31', 'acdae728d3fb4427', 0);
INSERT INTO `order_info` VALUES ('8c7889261cc04a1a', 2997.00, '25e0bb98c5a14d4b', '在线支付', 1, '2022-06-29 17:32:16', 'cccc18909d974b0e', 0);
INSERT INTO `order_info` VALUES ('a325ca96dd3e455f', 2199.00, 'b3f9dff4886347fb', '在线支付', 1, '2022-06-29 17:29:54', 'acdae728d3fb4427', 0);
INSERT INTO `order_info` VALUES ('a49eee88be614971', 5799.00, '25e0bb98c5a14d4b', '在线支付', 1, '2022-07-04 17:11:06', '00fd6ddf5aff48eb', 1);
INSERT INTO `order_info` VALUES ('ae0d42f1766f4db9', 5799.00, '25e0bb98c5a14d4b', '在线支付', 1, '2022-07-01 13:34:49', 'cccc18909d974b0e', 1);
INSERT INTO `order_info` VALUES ('b0d005d6493c4bbd', 17597.00, 'cff8aa9316954ee4', '在线支付', 1, '2022-07-04 17:22:53', '4163f7af671d49dc', 0);
INSERT INTO `order_info` VALUES ('e9db34acfdf74286', 4398.00, 'cff8aa9316954ee4', '在线支付', 1, '2022-07-04 17:24:30', '4163f7af671d49dc', 0);
INSERT INTO `order_info` VALUES ('fcbe090948a146d0', 1049.00, 'b3f9dff4886347fb', '在线支付', 1, '2022-07-01 17:28:33', 'acdae728d3fb4427', 0);
INSERT INTO `order_info` VALUES ('fe16c9e8161d4ecf', 5799.00, '25e0bb98c5a14d4b', '在线支付', 1, '2022-07-04 16:35:59', 'cccc18909d974b0e', 1);
INSERT INTO `order_info` VALUES ('fe2b43d85f1f421e', 4299.00, '25e0bb98c5a14d4b', '在线支付', 1, '2022-07-01 17:02:24', 'cccc18909d974b0e', 1);
INSERT INTO `order_info` VALUES ('ff752b9e51cc4808', 798.00, 'b3f9dff4886347fb', '在线支付', 1, '2022-07-02 17:27:59', 'acdae728d3fb4427', 0);

-- ----------------------------
-- Table structure for paramter_info
-- ----------------------------
DROP TABLE IF EXISTS `paramter_info`;
CREATE TABLE `paramter_info`  (
  `parameter_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '参数页id',
  `create_time` datetime(0) NOT NULL COMMENT '时间',
  `parameter_versions` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机版本',
  `parameter_color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机颜色',
  `parameter_cp` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电脑配置',
  `parameter_series` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表系列',
  PRIMARY KEY (`parameter_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of paramter_info
-- ----------------------------
INSERT INTO `paramter_info` VALUES ('1c4f0997abfa4386', '2022-06-28 14:12:03', '', '', 'i5-12450H/集显/16G/512G,i5-12450H/MX550/16G/512G,i7-12650H/MX550/16G/512G', '');
INSERT INTO `paramter_info` VALUES ('3548b1020baf4244', '2022-06-28 14:17:27', '', '', '', '深空蓝,象牙白,典雅黑');
INSERT INTO `paramter_info` VALUES ('380481e30e6a4000', '2022-06-28 14:13:26', '', '', 'i5-12450H/集显/16G/512G,i5-12450H/MX550/16G/512G,i7-12650H/MX550/16G/512G', '');
INSERT INTO `paramter_info` VALUES ('8b5e5dbc2ede4e08', '2022-06-28 14:14:50', '6GB+128GB,8GB+128GB,8GB+256GB', '时光蓝,子夜黑,原子银', '', '');
INSERT INTO `paramter_info` VALUES ('ad7bbb7d9df944c9', '2022-06-28 14:16:27', '', '', '', '曜石黑 黑色氟橡胶表带,流光银 蓝色真皮表带,流光银 棕色真皮表带');
INSERT INTO `paramter_info` VALUES ('b335a2d6e5b34321', '2022-06-16 17:45:42', '', '', 'i5-12450H/集显/16G/512G,i7-12650H/RTX 2050/16G/512G,i5-12450H/RTX 2050/16G/512G', '');
INSERT INTO `paramter_info` VALUES ('b4ff02f06e834f5b', '2022-06-10 13:56:24', '8GB+128GB,8GB+256GB,8GB+520GB', '亮黑,雪白', 'sada', '');
INSERT INTO `paramter_info` VALUES ('c393cbeaf49544e8', '2022-06-28 14:10:55', '', '', 'i5-12450H/集显/16G/512G,i5-12450H/MX550/16G/512G,i7-12650H/MX550/16G/512G', '');
INSERT INTO `paramter_info` VALUES ('f5cdfd895b4443c5', '2022-06-28 14:15:39', '4GB+128GB,8GB+128GB', '暗影黑,深空蓝', '', '');

-- ----------------------------
-- Table structure for rotation_info
-- ----------------------------
DROP TABLE IF EXISTS `rotation_info`;
CREATE TABLE `rotation_info`  (
  `rotation_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '轮播图id',
  `rotation_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '轮播图地址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `rotation_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '轮播图类型（首页，...）',
  PRIMARY KEY (`rotation_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of rotation_info
-- ----------------------------
INSERT INTO `rotation_info` VALUES ('242dcdbe4c874211', '15CA48B3701D42E5.webp', '2022-06-06 09:48:30', '首页');
INSERT INTO `rotation_info` VALUES ('3e361f116855431a', 'EDBDBC7CF2284FB3.jpg', '2022-06-05 21:27:41', '首页');
INSERT INTO `rotation_info` VALUES ('5ecc9ed94f6043da', '43AD7FC394304A42.webp', '2022-06-06 09:48:36', '首页');
INSERT INTO `rotation_info` VALUES ('e5483c508d8344e2', '0916F7372F804A77.jpg', '2022-06-06 09:48:34', '首页');
INSERT INTO `rotation_info` VALUES ('f38ed2fe1adb4896', 'D2F641D43AE44F67.jpg', '2022-06-06 09:48:38', '首页');
INSERT INTO `rotation_info` VALUES ('ffe98b2a9be5449c', '8A5949B6EF8E44B6.jpg', '2022-06-06 09:48:32', '首页');

-- ----------------------------
-- Table structure for sku_info
-- ----------------------------
DROP TABLE IF EXISTS `sku_info`;
CREATE TABLE `sku_info`  (
  `sku_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品id(uuid)',
  `price` decimal(10, 2) UNSIGNED ZEROFILL NOT NULL DEFAULT 00000000.00 COMMENT '价格',
  `sku_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `sku_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品描述',
  `store` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '库存',
  `salcount` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '销售数量',
  `img` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `create_time` datetime(0) NOT NULL COMMENT '商品上架时间',
  `sku_summary` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品图片概述(用逗号分隔)',
  `parameter_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数页详情id',
  `sku_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品类型',
  `sku_status` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品上架状态（0已上架，1未上架）',
  PRIMARY KEY (`sku_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sku_info
-- ----------------------------
INSERT INTO `sku_info` VALUES ('1e86413b50244cde', 00005299.00, 'Redmi Book Pro 15 2022', '全新12代英特尔处理器 | Windows 11 家庭中文版 | 可选RTX 2050高性能独立显卡 | 3.2K 90Hz 原色超清屏', 194, 0, '00D8E2A9B74F471A.jpg', '2022-06-16 17:45:42', NULL, 'b335a2d6e5b34321', '电脑', 0);
INSERT INTO `sku_info` VALUES ('21f89308273b4c37', 00000399.00, 'Redmi 手表 2', '1.6\"AMOLED大屏 | 117种运动模式 | 24小时心率监测', 196, 0, 'B005C715CC7D4E51.jpg', '2022-06-28 14:17:27', NULL, '3548b1020baf4244', '手表', 0);
INSERT INTO `sku_info` VALUES ('56bcd2926c6d4e0c', 00006999.00, 'Redmi Book Pro 14 2022', '全新12代英特尔处理器 | Windows 11 家庭中文版 | 2.5K 120Hz高清屏 | 可选MX550独立显卡', 198, 0, '18D8508E7DE24DE3.jpg', '2022-06-28 14:12:03', NULL, '1c4f0997abfa4386', '电脑', 0);
INSERT INTO `sku_info` VALUES ('56fbc933a44b4392', 00002199.00, 'Redmi Note 11T Pro', '天玑8100｜144Hz高配LCD屏幕｜6400万像素｜VC液冷散热', 192, 0, '99C97EF67BA344CD.jpg', '2022-06-28 14:14:50', NULL, '8b5e5dbc2ede4e08', '手机', 0);
INSERT INTO `sku_info` VALUES ('64ab619905cf419e', 00004299.00, 'RedmiBook Pro 14 R5', '2.5K超视网膜全面屏 | 一体精雕工艺 | 轻薄本 | 智能互联', 197, 0, 'B39CC1E30D7249C8.jpg', '2022-06-28 14:13:26', NULL, '380481e30e6a4000', '电脑', 0);
INSERT INTO `sku_info` VALUES ('6b35aa76a7954205', 00002191.00, 'Redmi K40S', '骁龙870｜三星 E4直屏', 196, 0, 'EB1E6879DCBD4E4A.jpg', '2022-06-09 09:56:09', NULL, 'b4ff02f06e834f5b', '手机', 0);
INSERT INTO `sku_info` VALUES ('a7727c4d780d4e41', 00005799.00, 'Redmi G 2021锐龙版 3050Ti版本', 'RTX™ 3050Ti光追独显与硬核锐龙芯梦幻联合，迎来游戏性能释放新境界。视野再突破，天生为赢而战，全方位为热爱进阶，Redmi G 2021 锐龙版，实力超能打。', 194, 0, '2DAFAEE736D34483.png', '2022-06-28 14:10:55', NULL, 'c393cbeaf49544e8', '电脑', 0);
INSERT INTO `sku_info` VALUES ('b58d8138370a4c9a', 00001049.00, 'Xiaomi Watch S1', '蓝宝石玻璃镜面 | 不锈钢中框 | 1.43”AMOLED大屏幕', 198, 0, 'F312BAE93AF94C92.png', '2022-06-28 14:16:27', NULL, 'ad7bbb7d9df944c9', '手表', 0);
INSERT INTO `sku_info` VALUES ('b745d704d9454968', 00000999.00, 'Redmi Note 11SE', '天玑700｜18W快充｜5000mAh大电池｜6.5\"FHD+全高清屏｜双卡双5G｜90Hz 四挡变速高刷屏｜4096级 亮度调节｜全系128GB 大内存｜大功率扬声器｜4800万 高清双摄', 194, 0, 'BE8C48E937834103.jpg', '2022-06-28 14:15:39', NULL, 'f5cdfd895b4443c5', '手机', 0);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `user_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id（uuid）',
  `login_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `passwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `user_power` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户权限（管理员 ， 用户）',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('25e0bb98c5a14d4b', 'user', '123456', '17652525252', '2022-06-14 09:05:08', '普通用户');
INSERT INTO `user_info` VALUES ('945a27d2b1b24c25', 'admin', '123456', '17655552312', '2022-05-31 17:20:12', '管理员');
INSERT INTO `user_info` VALUES ('b3f9dff4886347fb', 'swk', '111111', '15037942040', '2022-07-04 16:54:31', '普通用户');
INSERT INTO `user_info` VALUES ('cff8aa9316954ee4', 'wjy', '123456', '19893445830', '2022-07-04 16:39:55', '普通用户');

SET FOREIGN_KEY_CHECKS = 1;
