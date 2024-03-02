drop table if exists chat_message CASCADE;
CREATE TABLE `chat_message` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `room_id` int  COMMENT 'room_id',
  `message` varchar(255) COMMENT 'message',
  `sender` varchar(255) COMMENT 'sender',
  `destination_user` varchar(255) COMMENT 'destination_user',
  `send_time` DATETIME  COMMENT 'send_time',
  `update_message_flag` tinyint(1)  COMMENT 'update_message_flag',
  PRIMARY KEY (`id`), FOREIGN KEY (`room_id`) REFERENCES `chat_room` (`id`)
) COMMENT='chat_message'