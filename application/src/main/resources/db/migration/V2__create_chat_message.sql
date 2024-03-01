CREATE TABLE `chat_message` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `roomId` int NOT NULL COMMENT 'roomId',
  `message` varchar(255) COMMENT 'message',
  `sender` varchar(255) COMMENT 'sender',
  `destinationUser` varchar(255) COMMENT 'destinationUser',
  `sendTime` DATETIME  COMMENT 'sendTime',
  `updateMessageFlag` tinyint(1)  COMMENT 'updateMessageFlag',
  PRIMARY KEY (`id`,`roomId`)
) COMMENT='chat_message'