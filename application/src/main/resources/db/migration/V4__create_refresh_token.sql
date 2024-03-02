drop table if exists refresh_token CASCADE;
CREATE TABLE `refresh_token` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `refresh_token` varchar(255) NOT NULL COMMENT 'refresh_token',
  `user_id` int COMMENT 'user_id',
  `expiration_date` DATETIME COMMENT 'expiration_date',
  PRIMARY KEY (`id`)
) COMMENT='refresh_token'