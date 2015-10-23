CREATE DATABASE  IF NOT EXISTS `bankdb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `bankdb`;
-- MySQL dump 10.13  Distrib 5.5.41, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: bankdb
-- ------------------------------------------------------
-- Server version	5.5.41-0ubuntu0.14.04.1

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `AccountNumber` varchar(45) NOT NULL,
  `Type` varchar(45) DEFAULT NULL,
  `Balance` double DEFAULT NULL,
  `DateOfVarification` date DEFAULT NULL,
  `Password` varchar(45) DEFAULT NULL,
  `UserID` int(11) DEFAULT NULL,
  `AdminID` int(11) DEFAULT NULL,
  PRIMARY KEY (`AccountNumber`),
  KEY `fk_account_1_idx` (`UserID`),
  KEY `fk_account_2_idx` (`AdminID`),
  CONSTRAINT `fk_account_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_account_2` FOREIGN KEY (`AdminID`) REFERENCES `admin` (`AdminID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('15001','Deposit',93020,'2015-04-10','12',1,15151),('15002','Deposit',NULL,NULL,NULL,2,NULL),('15003','Deposit',1000,'2015-04-10','n6sxucXM',3,15151),('15005','Deposit',NULL,NULL,NULL,5,NULL),('15006','Deposit',5000,'2015-04-12','RNmwmDqs',6,15151),('15007','Deposit',980,'2015-04-21','Kr5BznQd',7,15151),('15008','Deposit',0,'2015-04-21','3XkvZ+m9',8,15151);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `AdminID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(245) DEFAULT NULL,
  `Designation` varchar(45) DEFAULT NULL,
  `Password` varchar(45) DEFAULT NULL,
  `Photos` varchar(345) DEFAULT NULL,
  `Status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`AdminID`)
) ENGINE=InnoDB AUTO_INCREMENT=15153 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (15151,'Salim','Student','1','15151_mbuntu (13).jpg',1),(15152,'Ahmed','Student','123','15152_mbuntu (6).jpg',1);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction` (
  `TransID` int(11) NOT NULL AUTO_INCREMENT,
  `TFrom` varchar(45) DEFAULT NULL,
  `TTo` varchar(45) DEFAULT NULL,
  `Amount` double DEFAULT NULL,
  `DOT` date DEFAULT NULL,
  PRIMARY KEY (`TransID`),
  KEY `fk_transaction_1_idx` (`TFrom`),
  KEY `fk_transaction_2_idx` (`TTo`),
  CONSTRAINT `fk_transaction_1` FOREIGN KEY (`TFrom`) REFERENCES `account` (`AccountNumber`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_transaction_2` FOREIGN KEY (`TTo`) REFERENCES `account` (`AccountNumber`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (7,'15001','15006',1000,'2015-04-21'),(8,'15001','15006',1000,'2015-04-21'),(14,'15001','15003',1000,'2015-04-21'),(15,'15001','15006',1000,'2015-04-21'),(17,'15001','15006',1000,'2015-04-21'),(18,'15001','15007',1000,'2015-04-21'),(20,'15001','15006',1000,'2015-04-21'),(22,'15007','15001',10,'2015-04-21'),(23,'15007','15001',10,'2015-04-21'),(24,'15007','15001',10,'2015-04-21'),(25,'15001','15007',10,'2015-04-21');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `UserID` int(11) NOT NULL,
  `Name` varchar(245) DEFAULT NULL,
  `Gender` varchar(45) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `Occupation` varchar(45) DEFAULT NULL,
  `MaritalStatus` varchar(45) DEFAULT NULL,
  `Religion` varchar(45) DEFAULT NULL,
  `FatherName` varchar(245) DEFAULT NULL,
  `MotherName` varchar(245) DEFAULT NULL,
  `PresentAddress` varchar(400) DEFAULT NULL,
  `PermantAddress` varchar(400) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Phone` varchar(45) DEFAULT NULL,
  `NID` varchar(45) DEFAULT NULL,
  `Status` tinyint(4) DEFAULT NULL,
  `Photos` varchar(200) DEFAULT NULL,
  `DOA` date DEFAULT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'salim','Male','2015-03-02','student','Single','Islam','salam','jamila','sadfasdf','dsafsad','salim@yahoo.com','12122121','12222222222',1,'1_396491_3909589811426_47005832_n.jpg','2015-03-29'),(2,'salim','Male','2015-03-02','student','Single','Islam','salam','jamila','sadfasdf','dsafsad','salim@yahoo.com','12122121','12222222222',1,'2_396491_3909589811426_47005832_n.jpg','2015-03-29'),(3,'Tusher','Male','2015-03-09','Student','Single','Islam','saddddddddd','adssssss','sdaasssssss','sdaaaaaaaa','tusher@gmail.com','211111111111','12222222212',1,NULL,'2015-03-29'),(5,'Asif','Male','2015-03-12','Business','Single','Islam','sadf','sadf','asdfdsaf','sadf','asif@yahoo.com','211111111111','2134213',0,'5_mbuntu (6).jpg','2015-04-06'),(6,'aD','Male','2015-04-12','satudent','Single','Islam','ag','ag','dfgdf','dfg','ass@mm.com','12122121','134',1,NULL,'2015-04-11'),(7,'Asad','Male','2015-04-05','Teacher','Single','Islam','adf','asdf','asf','fafas','asa@sas.com','2111111','2111111',1,'7_205053.jpg','2015-04-21'),(8,'jaber','Male','2014-07-17','Teacher','Married','Islam','ag','ag','sag','afdga','jaber@yahoo.com','122222','314134',1,'8_205053.2.jpg','2015-04-21');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-23 14:03:33
