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


DROP TABLE IF EXISTS `resource_application`;
CREATE TABLE `resource_application` (
                                    `raid` int(11) NOT NULL AUTO_INCREMENT,
                                    `did` int(11) NOT NULL ,
                                    `create_date` datetime NOT NULL,
                                    `pass_date` datetime ,
                                    `hiid` int(11) NOT NULL,
                                    `uid` int(11) NOT NULL,
                                    `purpose` varchar(255) NOT NULL,
                                    `port` int(10) NOT NULL,
                                    `time` int(10) NOT NULL,
                                    `operating_system` varchar(255) NOT NULL,
                                    `progress` varchar(255) NOT NULL,
                                    PRIMARY KEY (`raid`),
                                    FOREIGN KEY (hiid) REFERENCES host_information(`hiid`),
                                    FOREIGN KEY (uid) REFERENCES user(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

alter table user add column  user_role int(11) NOT NULL ;
DROP TABLE IF EXISTS `faile`;
CREATE TABLE `faile`(
                        `raid` int(11) NOT NULL ,
                        `reason` varchar(255) NOT NULL,
                        PRIMARY KEY (`raid`),
                        FOREIGN KEY (raid) REFERENCES resource_application(`raid`)
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

alter table department_user add column  duid  int(11) auto_increment PRIMARY KEY ;

alter table resource_type add column  file VARCHAR(255)  NOT NULL ;

DROP TABLE IF EXISTS `host_queue`;
CREATE TABLE `host_queue`(
                        `hiid` int(11) NOT NULL ,
                        `rtid` int(11) NOT NULL ,
                        `queue_size` int(11) NOT NULL ,
                        `queue_element` longtext,
                        `total_time` int(11) NOT NULL ,
                        `total_user` int(11) NOT NULL ,
                        `head_time` int(11) NOT NULL ,
                        PRIMARY KEY (`hiid`),
                        FOREIGN KEY (`hiid`) REFERENCES host_information(`hiid`),
                        FOREIGN KEY (`rtid`) REFERENCES resource_type(`rtid`)
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `application_user`;
CREATE TABLE `application_user`(
                             `raid` int(11) NOT NULL ,
                             `username` varchar(255) NOT NULL,
                             `password` varchar(255) NOT NULL,
                             `state` varchar(255) NOT NULL,
                             PRIMARY KEY (`raid`),
                             FOREIGN KEY (`raid`) REFERENCES resource_application(`raid`)
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

