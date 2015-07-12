CREATE DATABASE  IF NOT EXISTS `filmcatalog` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `filmcatalog`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: filmcatalog
-- ------------------------------------------------------
-- Server version	5.6.23-log

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
-- Table structure for table `datastore`
--

DROP TABLE IF EXISTS `datastore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `datastore` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `place` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datastore`
--

LOCK TABLES `datastore` WRITE;
/*!40000 ALTER TABLE `datastore` DISABLE KEYS */;
INSERT INTO `datastore` VALUES (1,'Adventure 1','Hard Disk Drive','upper shelf'),(2,'Sci-Fi 1','Blueray','middle shelf'),(3,'Sci-Fi 2','Blueray','middle shelf');
/*!40000 ALTER TABLE `datastore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `film`
--

DROP TABLE IF EXISTS `film`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `film` (
  `id` int(11) NOT NULL,
  `title` varchar(90) DEFAULT NULL,
  `director` varchar(45) DEFAULT NULL,
  `time` varchar(45) DEFAULT NULL,
  `genre` varchar(45) DEFAULT NULL,
  `year` varchar(20) DEFAULT NULL,
  `datastore_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `datastore_id_idx` (`datastore_id`),
  CONSTRAINT `datastore_id` FOREIGN KEY (`datastore_id`) REFERENCES `datastore` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `film`
--

LOCK TABLES `film` WRITE;
/*!40000 ALTER TABLE `film` DISABLE KEYS */;
INSERT INTO `film` VALUES (1,'Pirates of the Caribbean: The Curse of the Black Pearl','Gore Verbinski','143 min','Action, Adventure, Fantasy','2003',1),(2,'Pirates of the Pirates of the Caribbean: Dead Man\'s Chest','Gore Verbinski','151 min','Action, Adventure, Fantasy','2006',1),(3,'Pirates of the Caribbean: At World\'s End ','Gore Verbinski','169 min','Action, Adventure, Fantasy','2007',1),(4,'Pirates of the Caribbean: On Stranger Tides','Rob Marshall','136 min','Action, Adventure, Fantasy','2011',1),(5,'Stargate','Roland Emmerich','121 min','Action, Adventure, Sci-Fi','1995',2),(6,'Stargate: Continuum','Martin Wood','98 min','Action, Adventure, Fantasy','2008',2),(7,'Stargate: The Ark of Truth','Robert C. Cooper','97 min','Adventure, Fantasy, Sci-Fi','2008',2);
/*!40000 ALTER TABLE `film` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-07-11 21:46:15
