CREATE DATABASE IF NOT EXISTS `employee_management_system` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `employee_management_system`;

DROP TABLE IF EXISTS `employees`;
CREATE TABLE `employees` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email_id` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (3,'Sagar123@gmail.com','Sagar','Kalthiya'),(4,'Sagar123@gmail.com','Sagar','Kalthiya'),(5,'Sagar123@gmail.com','Sagar','Kalthiya'),(6,'john.doe@example.com','John','Doe'),(8,'jane.smith@example.com','UpdatedFirstName','Smith'),(9,'alu@gmail.com','alu','alu'),(10,'fsdf','sdfdf','fdsdf'),(11,'alu@gmail.com','sdfdf','sdfdf'),(12,'Sagar123@gmail.com','Sagar','Kalthiya'),(13,'john.doe@example.com','John','Doe'),(15,'jane.smith@example.com','UpdatedFirstName','Smith'),(16,'asdsd','sdasd','sadsd'),(17,'Sagar123@gmail.com','Sagar','Kalthiya'),(18,'john.doe@example.com','John','Doe'),(20,'jane.smith@example.com','UpdatedFirstName','Smith'),(21,'Sagar123@gmail.com','Sagar','Kalthiya'),(22,'john.doe@example.com','John','Doe'),(24,'jane.smith@example.com','UpdatedFirstName','Smith'),(25,'Sagar123@gmail.com','Sagar','Kalthiya'),(26,'john.doe@example.com','John','Doe'),(28,'jane.smith@example.com','UpdatedFirstName','Smith');

