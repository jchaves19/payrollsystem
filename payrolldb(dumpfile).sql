-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: payrolldb
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `AdminId` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(25) NOT NULL,
  `Password` varchar(20) NOT NULL,
  PRIMARY KEY (`AdminId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'admin','admin');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deduction`
--

DROP TABLE IF EXISTS `deduction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `deduction` (
  `iddeduction` int(11) NOT NULL AUTO_INCREMENT,
  `EmployeeId` int(11) NOT NULL,
  `DeductionName` varchar(45) NOT NULL,
  `Month` varchar(45) NOT NULL,
  `Amount` double NOT NULL,
  PRIMARY KEY (`iddeduction`),
  KEY `deduction_ibfk_1` (`EmployeeId`),
  CONSTRAINT `deduction_ibfk_1` FOREIGN KEY (`EmployeeId`) REFERENCES `employee` (`EmployeeId`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deduction`
--

LOCK TABLES `deduction` WRITE;
/*!40000 ALTER TABLE `deduction` DISABLE KEYS */;
/*!40000 ALTER TABLE `deduction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `DeptId` int(11) NOT NULL AUTO_INCREMENT,
  `DeptName` varchar(25) NOT NULL,
  PRIMARY KEY (`DeptId`),
  UNIQUE KEY `DeptName_UNIQUE` (`DeptName`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (11,''),(13,'Compt Stud'),(12,'gwapo'),(7,'HR Section'),(4,'Mayors Office'),(6,'Payroll Section'),(8,'Security Office'),(5,'Utility Office'),(10,'Varsity');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `EmployeeId` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Address` varchar(45) NOT NULL,
  `ContactNumber` varchar(45) DEFAULT NULL,
  `EmergencyNum` varchar(45) DEFAULT NULL,
  `Sex` varchar(10) DEFAULT 'Undecided',
  `BirthDate` date NOT NULL,
  `Rate` varchar(45) NOT NULL,
  `DeptName` varchar(25) NOT NULL,
  PRIMARY KEY (`EmployeeId`),
  KEY `DeptName` (`DeptName`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`DeptName`) REFERENCES `department` (`DeptName`),
  CONSTRAINT `employee_ibfk_2` FOREIGN KEY (`DeptName`) REFERENCES `department` (`DeptName`),
  CONSTRAINT `employee_ibfk_3` FOREIGN KEY (`DeptName`) REFERENCES `department` (`DeptName`)
) ENGINE=InnoDB AUTO_INCREMENT=1010 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1002,'Joseph P. Chaves','Bugang, Sagay, Camiguin','09234566543','09123454342','Male','1999-04-19','50','Mayors Office'),(1003,'Rogelio P. Ibacarra','Cagayan de Oro City','0967899023','0973897328','Male','1999-10-14','25','Mayors Office'),(1004,'Benz  A. Tan','Cagayan de Oro City','0978904567','094567878','Male','1998-03-30','20','Mayors Office'),(1005,'Christian A. Mabao','Cagayan de Oro City','097867867','09456778878','Male','1999-11-20','10','Utility Office'),(1006,'Seigfred Q. Sevilla','Cagayan de Oro City','09786722867','09456718878','Male','1960-11-25','9','Utility Office'),(1007,'Lebron X. James','USA','000987666','456789032','Male','1978-12-25','14','Varsity'),(1008,'Joseph Chaves','qwertyu','1234567','23456767','female','1999-09-11','10','gwapo');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hourswork`
--

DROP TABLE IF EXISTS `hourswork`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hourswork` (
  `EmployeeId` int(11) NOT NULL,
  `Regular` int(11) NOT NULL DEFAULT '0',
  `NonRegular` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`EmployeeId`),
  CONSTRAINT `hourswork_ibfk_1` FOREIGN KEY (`EmployeeId`) REFERENCES `employee` (`EmployeeId`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hourswork`
--

LOCK TABLES `hourswork` WRITE;
/*!40000 ALTER TABLE `hourswork` DISABLE KEYS */;
INSERT INTO `hourswork` VALUES (1002,0,0),(1003,0,0),(1004,0,0),(1005,0,0),(1006,0,0),(1007,0,0),(1008,17,0);
/*!40000 ALTER TABLE `hourswork` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `password`
--

DROP TABLE IF EXISTS `password`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `password` (
  `EmployeeId` int(11) NOT NULL,
  `Password` varchar(45) NOT NULL DEFAULT '12345',
  PRIMARY KEY (`EmployeeId`),
  CONSTRAINT `password_ibfk_1` FOREIGN KEY (`EmployeeId`) REFERENCES `employee` (`EmployeeId`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `password`
--

LOCK TABLES `password` WRITE;
/*!40000 ALTER TABLE `password` DISABLE KEYS */;
INSERT INTO `password` VALUES (1002,'qwer'),(1003,'12345'),(1004,'qwer'),(1005,'qwer'),(1006,'qwert9969'),(1007,'qwer'),(1008,'qwer');
/*!40000 ALTER TABLE `password` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wage`
--

DROP TABLE IF EXISTS `wage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wage` (
  `EmployeeId` int(11) NOT NULL,
  `WageAmount` double NOT NULL DEFAULT '0',
  `Month` varchar(15) NOT NULL DEFAULT 'October',
  PRIMARY KEY (`EmployeeId`),
  CONSTRAINT `wage_ibfk_1` FOREIGN KEY (`EmployeeId`) REFERENCES `employee` (`EmployeeId`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wage`
--

LOCK TABLES `wage` WRITE;
/*!40000 ALTER TABLE `wage` DISABLE KEYS */;
INSERT INTO `wage` VALUES (1002,0,'October'),(1003,0,'October'),(1004,0,'October'),(1005,0,'October'),(1006,0,'October'),(1007,0,'October'),(1008,260,'October');
/*!40000 ALTER TABLE `wage` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-15 12:58:30
