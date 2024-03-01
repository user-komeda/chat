CREATE TABLE `refresh_token` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `refreshToken` varchar(255) NOT NULL COMMENT 'refreshToken',
  `userId` int COMMENT 'userId',
  `expirationDate` DATETIME COMMENT 'expirationDate',
  PRIMARY KEY (`id`)
) COMMENT='refresh_token'