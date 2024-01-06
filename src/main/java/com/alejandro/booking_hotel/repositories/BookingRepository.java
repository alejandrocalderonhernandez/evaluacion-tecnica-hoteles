package com.alejandro.booking_hotel.repositories;

import com.alejandro.booking_hotel.models.entities.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
}
