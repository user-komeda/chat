package com.example.infratecture.repository;

import com.example.infratecture.entity.RoomEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 * RoomJdbcRepository.
 */
@Component
public interface RoomJdbcRepository extends CrudRepository<RoomEntity, Long> {

}
