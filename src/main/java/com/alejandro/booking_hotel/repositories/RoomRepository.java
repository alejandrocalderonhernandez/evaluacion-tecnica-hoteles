package com.alejandro.booking_hotel.repositories;

import com.alejandro.booking_hotel.models.entities.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
}
