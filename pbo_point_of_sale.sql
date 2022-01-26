-- Adminer 4.8.1 MySQL 8.0.27 dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `menu_id` int NOT NULL AUTO_INCREMENT,
  `menu_menu_id` int NOT NULL,
  `menu_name` varchar(50) NOT NULL,
  `menu_class` varchar(100) NOT NULL,
  `menu_function` varchar(100) NOT NULL,
  `menu_params` varchar(100) NOT NULL,
  `menu_index` int NOT NULL,
  `menu_description` text NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `menu` (`menu_id`, `menu_menu_id`, `menu_name`, `menu_class`, `menu_function`, `menu_params`, `menu_index`, `menu_description`, `created_at`, `updated_at`) VALUES
(5,	0,	'Login',	'auth',	'login',	'',	1,	'Login',	'2022-01-16 10:03:23',	'2022-01-16 10:04:04'),
(6,	0,	'Register',	'auth',	'register',	'',	2,	'Register',	'2022-01-16 10:03:56',	'2022-01-16 10:03:56'),
(7,	0,	'Main menu',	'home',	'home',	'',	1,	'Welcome page',	'2022-01-16 10:04:56',	'2022-01-16 10:05:43'),
(8,	7,	'Product',	'product',	'index',	'',	1,	'Product menu',	'2022-01-16 10:06:05',	'2022-01-16 10:06:05'),
(9,	8,	'Show Product',	'product',	'show_all',	'',	1,	'Show all product data',	'2022-01-16 10:06:51',	'2022-01-16 10:06:51'),
(10,	8,	'Create Product',	'product',	'create',	'',	2,	'Create new product',	'2022-01-16 10:07:13',	'2022-01-16 10:07:13'),
(11,	8,	'Update Product',	'product',	'update',	'',	3,	'Update Product',	'2022-01-16 10:07:42',	'2022-01-16 10:07:42'),
(12,	8,	'Delete Product',	'product',	'delete',	'',	4,	'Delete Product',	'2022-01-16 10:08:12',	'2022-01-16 10:08:12'),
(13,	8,	'Search Product',	'product',	'search',	'',	5,	'Search detail product',	'2022-01-16 10:08:44',	'2022-01-16 10:08:44'),
(14,	7,	'Purchase',	'purchase',	'index',	'',	2,	'Purchase menu',	'2022-01-26 06:56:54',	'2022-01-26 06:56:54'),
(15,	14,	'Show Pruchase',	'purchase',	'show_all',	'',	1,	'Show all purchase data',	'2022-01-26 06:57:27',	'2022-01-26 06:57:27'),
(16,	14,	'Create Purchase',	'purchase',	'create',	'',	2,	'Create new purchase',	'2022-01-26 06:58:03',	'2022-01-26 06:58:03'),
(17,	14,	'Update Purchase',	'purchase',	'update',	'',	3,	'Update purchase',	'2022-01-26 06:58:41',	'2022-01-26 06:58:41'),
(18,	14,	'Delete Purchase',	'purchase',	'delete',	'',	4,	'Delete purchase',	'2022-01-26 06:59:46',	'2022-01-26 06:59:46'),
(19,	14,	'Search Purchase',	'purchase',	'search',	'',	5,	'Search purchase',	'2022-01-26 07:00:11',	'2022-01-26 07:00:11'),
(20,	7,	'Sale',	'sale',	'index',	'',	3,	'',	'2022-01-26 16:31:55',	'2022-01-26 16:31:55'),
(21,	20,	'Show Sale',	'sale',	'show_all',	'',	1,	'Show all sale data',	'2022-01-26 16:32:25',	'2022-01-26 16:32:25'),
(22,	20,	'Create Sale',	'sale',	'create',	'',	2,	'Create new sale',	'2022-01-26 16:32:44',	'2022-01-26 16:32:44'),
(23,	20,	'Update Sale',	'sale',	'update',	'',	3,	'Update sale ',	'2022-01-26 16:33:02',	'2022-01-26 16:33:02'),
(24,	20,	'Delete Sale',	'sale',	'delete',	'',	4,	'Delete sale',	'2022-01-26 16:33:32',	'2022-01-26 16:33:32'),
(25,	20,	'Search Sale',	'sale',	'search',	'',	5,	'Search sale',	'2022-01-26 16:33:50',	'2022-01-26 16:33:50');

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `prod_id` int NOT NULL AUTO_INCREMENT,
  `prod_name` varchar(50) NOT NULL,
  `prod_price` varchar(20) NOT NULL,
  `prod_qty` int NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`prod_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `product` (`prod_id`, `prod_name`, `prod_price`, `prod_qty`, `created_at`, `updated_at`) VALUES
(1,	'produk',	'100000',	195,	'2022-01-25 15:10:34',	'2022-01-26 16:52:23'),
(2,	'test2',	'20000',	20,	'2022-01-25 15:10:44',	'2022-01-25 15:10:44'),
(3,	'test',	'10000',	10,	'2022-01-25 15:52:26',	'2022-01-25 15:52:26'),
(4,	'produk bagus',	'1000',	100,	'2022-01-25 15:53:09',	'2022-01-26 06:46:43'),
(6,	'Produk produk',	'1999',	200,	'2022-01-26 06:46:25',	'2022-01-26 06:46:25');

DROP TABLE IF EXISTS `purchase`;
CREATE TABLE `purchase` (
  `purch_id` int NOT NULL AUTO_INCREMENT,
  `pruch_user_id` int NOT NULL,
  `pruch_prod_id` int NOT NULL,
  `pruch_code` varchar(20) NOT NULL,
  `purch_date` varchar(50) NOT NULL,
  `purch_qty` int NOT NULL,
  `purch_price` int NOT NULL,
  `purch_total` int NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`purch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `purchase` (`purch_id`, `pruch_user_id`, `pruch_prod_id`, `pruch_code`, `purch_date`, `purch_qty`, `purch_price`, `purch_total`, `created_at`, `updated_at`) VALUES
(3,	1,	1,	'purch-23-24-02',	'2022-01-26',	20,	100000,	2000000,	'2022-01-26 16:23:14',	'2022-01-26 16:24:02');

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL,
  `role_description` text NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `rolm_id` int NOT NULL AUTO_INCREMENT,
  `rolm_role_id` int NOT NULL,
  `rolm_menu_id` int NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`rolm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `sale`;
CREATE TABLE `sale` (
  `sale_id` int NOT NULL AUTO_INCREMENT,
  `sale_user_id` int NOT NULL,
  `sale_prod_id` int NOT NULL,
  `sale_code` varchar(20) NOT NULL,
  `sale_date` varchar(50) NOT NULL,
  `sale_qty` int NOT NULL,
  `sale_price` int NOT NULL,
  `sale_total` int NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`sale_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `sale` (`sale_id`, `sale_user_id`, `sale_prod_id`, `sale_code`, `sale_date`, `sale_qty`, `sale_price`, `sale_total`, `created_at`, `updated_at`) VALUES
(1,	1,	1,	'sale-23-52-22',	'2022-01-26',	5,	100000,	500000,	'2022-01-26 16:51:55',	'2022-01-26 16:52:23');

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_role_id` int NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `user_password` varchar(100) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `user` (`user_id`, `user_role_id`, `user_name`, `user_password`, `created_at`, `updated_at`) VALUES
(1,	2,	'test',	'DEghU3KKrCOqYeVExTQCfBrTKQFd5pJDPpVA2g+suF0=',	'2022-01-24 17:47:17',	'2022-01-24 17:47:17'),
(2,	2,	'test2',	'SE4jXJBw6puqId3km3uO6G6MfHg4Eq/oDnM2wESouJw=',	'2022-01-24 17:47:25',	'2022-01-24 17:47:25');

-- 2022-01-26 16:53:30