
-- MySQL dump 10.13  Distrib 5.7.19, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: Hotel
-- ------------------------------------------------------
-- Server version	5.7.19-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Coupon`
--

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `Coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Coupon` (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `description` varchar(255) DEFAULT NULL,
                          `hotelId` int(11) DEFAULT '-1',
                          `couponType` int(11) NOT NULL,
                          `couponName` varchar(255) NOT NULL,
                          `target_money` int(11) DEFAULT NULL,
                          `discount` double DEFAULT NULL,
                          `status` int(11) DEFAULT NULL,
                          `start_time` datetime DEFAULT NULL,
                          `end_time` datetime DEFAULT NULL,
                          `discount_money` int(11) DEFAULT NULL,
                          `bizReg` varchar(255) DEFAULT NULL,
                          `creditRequired` double DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Coupon`
--
BEGIN;
/*!40000 ALTER TABLE `Coupon` DISABLE KEYS */;
INSERT INTO `Coupon` VALUES (3,'VIP用户在新街口打8折',-1,6,'VIP优惠券',NULL,0.8,1,NULL,NULL,NULL,'XinJieKou',NULL),(2,'满500-100优惠',2,3,'满减优惠券',500,0,1,NULL,NULL,100,NULL,NULL);
/*!40000 ALTER TABLE `Coupon` ENABLE KEYS */;
COMMIT;

--
-- Table structure for table 'Comments'
--

DROP TABLE IF EXISTS `Comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Comments` (
                            `id` int (11) NOT NULL AUTO_INCREMENT,
                            `userId` int(11) NOT NULL ,
                            `contents` varchar(255) DEFAULT NULL,
                            `hotelId` int(11) NOT NULL ,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

BEGIN;
/*!40000 ALTER TABLE `Comments` DISABLE KEYS */;
INSERT INTO `Comments` VALUES (1,1, '卫生很好',1);
/*!40000 ALTER TABLE `Comments` ENABLE KEYS */;
COMMIT;


--
-- Table structure for table `Hotel`
--

DROP TABLE IF EXISTS `Hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Hotel` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `hotelName` varchar(255) NOT NULL,
                         `hotelDescription` varchar(255) DEFAULT NULL,
                         `address` varchar(255) DEFAULT NULL,
                         `bizRegion` varchar(255) DEFAULT NULL,
                         `hotelStar` varchar(255) DEFAULT NULL,
                         `phoneNum` varchar(255) DEFAULT NULL,
                         `rate` double DEFAULT NULL,
                         `manager_id` int(11) DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Hotel`
--

BEGIN;
/*!40000 ALTER TABLE `Hotel` DISABLE KEYS */;
INSERT INTO `Hotel` VALUES (1,'汉庭酒店','欢迎您入住',NULL,'XiDan','Four',18293738193,4.8,1),(2,'儒家酒店','欢迎您入住','南京市鼓楼区珠江路268号','XiDan','Four',18293738194,4.8,2),(3,'桂圆酒店','欢迎您入住','南京市栖霞区珠江路268号','XiDan','Four',18295537195,4.8,6);
/*!40000 ALTER TABLE `Hotel` ENABLE KEYS */;
COMMIT;

--
-- Table structure for table `OrderList`
--

DROP TABLE IF EXISTS `OrderList`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OrderList` (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `userId` int(11) DEFAULT NULL,
                             `hotelId` int(11) DEFAULT NULL,
                             `hotelName` varchar(255) DEFAULT NULL,
                             `checkInDate` varchar(255) DEFAULT NULL,
                             `checkOutDate` varchar(255) DEFAULT NULL,
                             `roomType` varchar(255) DEFAULT NULL,
                             `roomNum` int(255) DEFAULT NULL,
                             `peopleNum` int(255) DEFAULT NULL,
                             `haveChild` tinytext,
                             `createDate` varchar(255) DEFAULT NULL,
                             `price` decimal(65,0) DEFAULT NULL,
                             `clientName` varchar(255) DEFAULT NULL,
                             `phoneNumber` varchar(255) DEFAULT NULL,
                             `orderState` varchar(255) DEFAULT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OrderList`
--

BEGIN;
/*!40000 ALTER TABLE `OrderList` DISABLE KEYS */;
INSERT INTO `OrderList` VALUES (2,5,2,'如家酒店','2020-05-20','2020-05-21','Family',1,2,0,'2020-05-18',798,'Russell',18888888888,'异常'),(3,5,2,'如家酒店','2020-05-20','2020-05-21','Family',1,2,0,'2020-05-18',798,'Russell',18888888888,'已撤销');
/*!40000 ALTER TABLE `OrderList` ENABLE KEYS */;
COMMIT;

--
-- Table structure for table `Room`
--

DROP TABLE IF EXISTS `Room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Room` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `price` double DEFAULT NULL,
                        `curNum` int(11) DEFAULT NULL,
                        `total` int(11) DEFAULT NULL,
                        `hotel_id` int(11) DEFAULT NULL,
                        `roomType` varchar(50) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Room`
--

BEGIN;
/*!40000 ALTER TABLE `Room` DISABLE KEYS */;
INSERT INTO `Room` VALUES (2,199,20,20,1,'BigBed'),(3,299,30,30,1,'DoubleBed'),(4,399,10,10,1,'Family'),(5,122,7,10,1,'BigBed'),(6,399,10,10,2,'Family');
/*!40000 ALTER TABLE `Room` ENABLE KEYS */;
COMMIT;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `email` varchar(255) DEFAULT NULL,
                        `password` varchar(11) NOT NULL,
                        `username` varchar(255) DEFAULT NULL,
                        `phonenumber` varchar(255) DEFAULT NULL,
                        `credit` double(255,0) DEFAULT 0.0,
                        `usertype` varchar(255) DEFAULT NULL,
                        `portrait` varchar(674100) DEFAULT NULL,
                        `isVip` tinyint DEFAULT 0,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

BEGIN;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (8,'333@hz.ok','123456','Clement','12345',300,'Admin',NULL,0), (4,'1012681@qq.com','123456','Benjamin','12345678919',100,'Client',NULL,1 ),(5,'123@qq.com','123456','Harvey','12345678911',100,'Client',NULL,0 ),(6,'333@qq.com','123456','Andrea',NULL,0,'HotelManager',NULL,0),(7,'123@hz.ok','123456','Russell',NULL,0,'Marketer',NULL,1);
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
COMMIT;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-12 10:08:42
SET FOREIGN_KEY_CHECKS = 1;