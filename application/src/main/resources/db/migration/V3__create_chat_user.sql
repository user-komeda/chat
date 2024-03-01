CREATE TABLE `chat_user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `roomId` int NOT NULL COMMENT 'roomId',
  `email` varchar(255) COMMENT 'email',
  `password` varchar(255) COMMENT 'password',
  `userName` varchar(255) COMMENT 'userName',
  `verificationCode` varchar(255)  COMMENT 'verificationCode',
  `isVerified` tinyint(1)  COMMENT 'isVerified',
  PRIMARY KEY (`id`,`roomId`)
) COMMENT='chat_user'