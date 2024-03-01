CREATE TABLE `chat_room` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `room_name` varchar(255) DEFAULT NULL COMMENT 'room_name',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='chat_room'