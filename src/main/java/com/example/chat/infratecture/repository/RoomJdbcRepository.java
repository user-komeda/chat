package com.example.chat.infratecture.repository;

import com.example.chat.infratecture.entity.RoomEntity;
import org.springframework.data.repository.CrudRepository;

/** RoomJdbcRepository. */
public interface RoomJdbcRepository extends CrudRepository<RoomEntity, Long> {}
