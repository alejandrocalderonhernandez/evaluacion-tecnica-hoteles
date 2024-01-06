package com.alejandro.booking_hotel.repositories;

import com.alejandro.booking_hotel.models.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
