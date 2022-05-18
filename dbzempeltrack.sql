-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: dbzempeltrack
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `athletes`
--

DROP TABLE IF EXISTS `athletes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `athletes` (
  `id` int DEFAULT NULL,
  `firstName` varchar(20) NOT NULL,
  `lastName` varchar(20) NOT NULL,
  `school` varchar(20) NOT NULL,
  PRIMARY KEY (`firstName`,`lastName`,`school`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `athletes`
--

LOCK TABLES `athletes` WRITE;
/*!40000 ALTER TABLE `athletes` DISABLE KEYS */;
INSERT INTO `athletes` VALUES (0,'first1','last1','school1'),(111,'first11','last11','school1'),(222,'first2','last2','school2'),(333,'first21','last21','school2'),(444,'first3','last3','school3'),(555,'first31','last31','school3'),(666,'first4','last4','school4'),(777,'first41','last41','school4'),(888,'first5','last5','school5'),(999,'first51','last51','school5');
/*!40000 ALTER TABLE `athletes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `events` (
  `athleteID` int NOT NULL,
  `eventName` varchar(50) NOT NULL,
  `gender` char(1) NOT NULL,
  `place` int DEFAULT NULL,
  `score` int DEFAULT NULL,
  `dq` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`athleteID`,`eventName`),
  CONSTRAINT `events_ibfk_1` FOREIGN KEY (`athleteID`) REFERENCES `athletes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events`
--

LOCK TABLES `events` WRITE;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
INSERT INTO `events` VALUES (0,'jump','f',2,5,NULL),(0,'run','f',1,10,NULL),(111,'run','f',2,5,NULL),(111,'skip','f',1,10,NULL),(222,'jump','f',1,10,NULL),(222,'skip','f',NULL,0,1),(333,'jump','m',NULL,NULL,1),(333,'run','m',1,10,NULL),(444,'run','m',2,5,NULL),(444,'skip','m',1,10,NULL),(555,'jump','m',NULL,NULL,1),(555,'skip','m',1,10,NULL),(666,'leap','f',1,10,NULL),(666,'throw','f',2,5,NULL),(777,'leap','f',2,5,NULL),(777,'throw','f',1,10,NULL),(888,'leap','m',1,10,NULL),(888,'throw','m',1,10,NULL),(999,'leap','m',1,10,NULL),(999,'throw','m',NULL,NULL,1);
/*!40000 ALTER TABLE `events` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `toomanyevents` BEFORE INSERT ON `events` FOR EACH ROW begin
if count(new.athleteid) > 4 then
insert into school values ('fakeschool', 1, 'fakefirst', 'fakelast');
insert into athletes values(123, 'fakefirst', 'fakelast');
set new.athleteid = 123;
end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `school`
--

DROP TABLE IF EXISTS `school`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `school` (
  `schoolName` varchar(20) NOT NULL,
  `studentID` int NOT NULL,
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  UNIQUE KEY `studentID` (`studentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `school`
--

LOCK TABLES `school` WRITE;
/*!40000 ALTER TABLE `school` DISABLE KEYS */;
INSERT INTO `school` VALUES ('school1',1,'first1','last1'),('school2',2,'first2','last2'),('school3',3,'first3','last3'),('school4',4,'first4','last4'),('school5',5,'first5','last5'),('school1',11,'first11','last11'),('school2',21,'first21','last21'),('school3',31,'first31','last31'),('school4',41,'first41','last41'),('school5',51,'first51','last51');
/*!40000 ALTER TABLE `school` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'dbzempeltrack'
--

--
-- Dumping routines for database 'dbzempeltrack'
--
/*!50003 DROP PROCEDURE IF EXISTS `error` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `error`()
begin
declare exit handler for sqlexception
begin
signal sqlstate value '99999'
set message_text = 'entered in too many events';
end;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-10 16:57:51
