-- MySQL dump 10.13  Distrib 5.7.9, for osx10.9 (x86_64)
--
-- Host: localhost    Database: code4good
-- ------------------------------------------------------
-- Server version	5.6.26

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
-- Table structure for table `quiz_participants`
--
USE code4good;

DROP TABLE IF EXISTS `quiz_participants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quiz_participants` (
  `id` bigint(128) NOT NULL AUTO_INCREMENT,
  `age` int(11) NOT NULL,
  `sex` varchar(45) NOT NULL,
  `ip` varchar(45) NOT NULL,
  `country` varchar(255) NOT NULL,
  `quiz_answers` text NOT NULL,
  `percent_correct_answers` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz_participants`
--

LOCK TABLES `quiz_participants` WRITE;
/*!40000 ALTER TABLE `quiz_participants` DISABLE KEYS */;
INSERT INTO `quiz_participants` VALUES (15,14,'O','0:0:0:0:0:0:0:1','UK','',83);
/*!40000 ALTER TABLE `quiz_participants` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quiz_questions`
--

DROP TABLE IF EXISTS `quiz_questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quiz_questions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question_text` mediumtext NOT NULL,
  `question_country` varchar(255) NOT NULL,
  `ans1` mediumtext NOT NULL,
  `ans2` mediumtext NOT NULL,
  `ans3` mediumtext NOT NULL,
  `ans4` mediumtext NOT NULL,
  `correct_answer` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz_questions`
--

LOCK TABLES `quiz_questions` WRITE;
/*!40000 ALTER TABLE `quiz_questions` DISABLE KEYS */;
INSERT INTO `quiz_questions` VALUES (11,'Of these countries, which has the highest portion of female to male ratio in parliament?','UK','United Kingdom','Germany','Senegal','Slovenia',3),(12,'What percentage of Sengalese women are literate?','UK','34%','15%','76%','4%',1),(13,'What age is a female child in many countries considered an adult?','UK','18','15','10','12',4),(14,'Marriage can happen early in Senegal. When do most women in Senegal get married?','UK','younger than 12','12-20','21-35','36+',2),(15,'How often do you think a women dies in childbirth?','UK','every 1 hour','every 25 minutes','every 10 minutes','every 2 minutes',4),(16,'What percentage of women in Senegal are affected by female mutilation?','UK','24.7%','3.4%','14.6%','44%',1);
/*!40000 ALTER TABLE `quiz_questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `salt` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','qQa9qF21CJZHPI/LVpypxsvKKhCNAOnTWCTIHcCFM6nhlLs7OIEaaLLUQwyqdjdtIscApNCmMqMrGspM6k/WkA==','27420E7C93A21B78F772EEE381EFBC84');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-05  4:19:03
