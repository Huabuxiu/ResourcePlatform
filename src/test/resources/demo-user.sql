/*
Navicat MySQL Data Transfer

Source Server         : Localhost
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2017-06-23 14:25:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
show databases ;
create database resources;
use resources;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `username` varchar(255) NOT NULL,
                        `password` varchar(255) NOT NULL,
                        `name` varchar(255)  NOT NULL ,
                        `e_mail` varchar(255)  NOT NULL ,
                        `phone` varchar(255)  NOT NULL ,
                        `reg_time` datetime NOT NULL,
                        `token` varchar(255)  NOT NULL ,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
                        `did` int(11) NOT NULL AUTO_INCREMENT,
                        `name` varchar(255) NOT NULL,
                        `introduce` varchar(255) NOT NULL,
                        `reg_time` datetime NOT NULL,
                        PRIMARY KEY (`did`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `department_user`;
CREATE TABLE `department_user` (
                              `did` int(11) NOT NULL ,
                              `id` int(11) NOT NULL ,
                              FOREIGN KEY (did) REFERENCES department(did),
                              FOREIGN KEY (did) REFERENCES user(id)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
                              `nid` int(11) NOT NULL AUTO_INCREMENT,
                              `title` varchar(255) NOT NULL,
                              `html` longtext ,
                              `reg_time` datetime NOT NULL,
                              PRIMARY KEY (`nid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `description`;
CREATE TABLE `description` (
                        `deid` int(11) NOT NULL AUTO_INCREMENT,
                        `html` longtext ,
                        `reg_time` datetime NOT NULL,
                        PRIMARY KEY (`deid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `resource_type`;
CREATE TABLE `resource_type` (
                        `rtid` int(11) NOT NULL AUTO_INCREMENT,
                        `resource_name` varchar(255) NOT NULL,
                        `introduction` varchar(255) NOT NULL,
                        `image` varchar(255) NOT NULL,
                        `reg_time` datetime NOT NULL,
                        `deid` int(11) NOT NULL ,
                        PRIMARY KEY (`rtid`),
                        FOREIGN KEY (deid) REFERENCES description(`deid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `host_information`;
CREATE TABLE `host_information` (
                               `hiid` int(11) NOT NULL AUTO_INCREMENT,
                               `name` varchar(255) NOT NULL,
                               `reg_time` datetime NOT NULL,
                               `rtid` int(11) NOT NULL,
                               `address` varchar(255) NOT NULL,
                               `port` int(10) NOT NULL,
                               PRIMARY KEY (`hiid`),
                               FOREIGN KEY (rtid) REFERENCES resource_type(`rtid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

