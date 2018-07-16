-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: lalamove_order
-- ------------------------------------------------------
-- Server version	8.0.11


--
-- Table structure for table `order_tb`
--

CREATE DATABASE IF NOT EXISTS lalamove_order;

USE lalamove_order;

DROP TABLE IF EXISTS `order_tb`;

CREATE TABLE `order_tb` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(45) NOT NULL,
  `distance` varchar(45) NOT NULL,
  `date_created` datetime DEFAULT NULL,
  `date_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ;

LOCK TABLES `order_tb` WRITE;

INSERT INTO `order_tb` VALUES (1,'ORDER_ALREADY_BEEN_TAKEN','123','2018-07-13 10:36:31','2018-07-13 15:31:42'),(2,'ORDER_ALREADY_BEEN_TAKEN','73.4 mi','2018-07-13 15:06:38','2018-07-13 15:31:48'),(3,'ORDER_ALREADY_BEEN_TAKEN','73.4 mi','2018-07-13 15:06:44','2018-07-13 15:31:52');

UNLOCK TABLES;


