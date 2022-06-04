-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: edms_rest_api
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `signature`
--

DROP TABLE IF EXISTS `signature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `signature` (
  `id` int NOT NULL AUTO_INCREMENT,
  `signature_text` varchar(60) DEFAULT NULL,
  `signed_document_id` int DEFAULT NULL,
  `owner_user_id` int DEFAULT NULL,
  `signed_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmrinsg66fumenl5w2s6ekuhbo` (`signed_document_id`),
  KEY `FKfio1wnumu7jp6llsvicicrq6x` (`owner_user_id`),
  CONSTRAINT `FKfio1wnumu7jp6llsvicicrq6x` FOREIGN KEY (`owner_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKmrinsg66fumenl5w2s6ekuhbo` FOREIGN KEY (`signed_document_id`) REFERENCES `document` (`document_id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `signature`
--

LOCK TABLES `signature` WRITE;
/*!40000 ALTER TABLE `signature` DISABLE KEYS */;
INSERT INTO `signature` VALUES (40,'Signed_238103747_EDU Taslak@03/06/2022-10:58:27',49,7,'2022-06-03 22:58:27.910000'),(41,'Signed_2146776376_EDU Taslak@03/06/2022-10:59:12',49,8,'2022-06-03 22:59:12.168000'),(42,'Signed_119851544_Yeni Proje 123@04/06/2022-12:44:12',53,8,'2022-06-04 00:44:12.076000'),(43,'Signed_289337594_Sanctuary@04/06/2022-12:48:42',54,8,'2022-06-04 00:48:42.799000'),(44,'Signed_383580310_My File@04/06/2022-01:00:00',55,8,'2022-06-04 01:00:00.466000'),(45,'Signed_703017280_My File@04/06/2022-01:00:38',55,10,'2022-06-04 01:00:38.682000'),(46,'Signed_1540573840_My File@04/06/2022-01:01:43',55,7,'2022-06-04 01:01:43.051000'),(47,'Signed_1642577104_Test Document@04/06/2022-01:14:05',56,7,'2022-06-04 01:14:05.663000'),(48,'Signed_556869045_Test Document@04/06/2022-01:14:54',56,9,'2022-06-04 01:14:54.860000');
/*!40000 ALTER TABLE `signature` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-04 10:48:24
