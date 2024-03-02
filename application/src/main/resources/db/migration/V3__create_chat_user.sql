drop table if exists chat_user CASCADE;
CREATE TABLE `chat_user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `room_id` int  COMMENT 'room_id',
  `email` varchar(255) COMMENT 'email',
  `password` varchar(255) COMMENT 'password',
  `user_name` varchar(255) COMMENT 'user_name',
  `verification_code` varchar(255)  COMMENT 'verification_code',
  `is_verified` tinyint(1)  COMMENT 'is_verified',
  PRIMARY KEY (`id`), FOREIGN KEY (`room_id`) REFERENCES `chat_room` (`id`)
) COMMENT='chat_user'