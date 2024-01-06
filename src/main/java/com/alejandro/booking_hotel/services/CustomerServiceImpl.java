package com.alejandro.booking_hotel.services;

import com.alejandro.booking_hotel.beans.CacheConstants;
import com.alejandro.booking_hotel.exceptions.BookingException;
import com.alejandro.booking_hotel.models.entities.CustomerEntity;
import com.alejandro.booking_hotel.models.request.CustomerReq;
import com.alejandro.booking_hotel.models.response.BookingRes;
import com.alejandro.booking_hotel.models.response.CustomerRes;
import com.alejandro.booking_hotel.repositories.BookingRepository;
import com.alejandro.booking_hotel.repositories.CustomerRepository;
import com.alejandro.booking_hotel.services.abstracts.CustomerService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Slf4j
@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final BookingRepository bookingRepository;


    @Override
    public CustomerRes create(CustomerReq request) {
        var entity = this.requestToEntity(request);

        return this.entityToResponse(this.customerRepository.save(entity));
    }

    @Override
    @Cacheable(value = CacheConstants.CUSTOMER_CACHE_NAME)
    public CustomerRes read(Long id) {
        var responseDB = this.customerRepository.findById(id)
                .orElseThrow(() -> new BookingException("Customer not found"));

        return this.entityToResponse(responseDB);
    }

    @Override
    @CacheEvict(cacheNames ={
            CacheConstants.CUSTOMER_CACHE_NAME
    }, allEntries = true)
    public CustomerRes update(CustomerReq request, Long id) {
        var responseDB = this.customerRepository.findById(id)
                .orElseThrow(() -> new BookingException("Customer not found"));

        responseDB.setEmail(request.getEmail());
        responseDB.setName(request.getName());
        responseDB.setLastname(request.getLastname());
        responseDB.setPhone(request.getPhone());

        return this.entityToResponse(this.customerRepository.save(responseDB));
    }

    @Override
    @CacheEvict(cacheNames ={
            CacheConstants.CUSTOMER_CACHE_NAME
    }, allEntries = true)
    public void delete(Long id) {
        if ((this.customerRepository.existsById(id))) {
            this.customerRepository.deleteById(id);
        } else {
            throw new BookingException("Customer not found");
        }
    }

    @Override
    @CacheEvict(cacheNames ={
            CacheConstants.CUSTOMER_CACHE_NAME
    }, allEntries = true)
    public CustomerRes addReservation(Long customerId, Long bookingId) {
        var customerDB = this.customerRepository.findById(customerId)
                .orElseThrow(() -> new BookingException("Customer not found"));

        var bookingDB = this.bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingException("Booking not found"));
        bookingDB.setCustomer(customerDB);

        this.bookingRepository.save(bookingDB);

        return this.entityToResponse(this.customerRepository.findById(customerId).get());
    }

    private CustomerEntity requestToEntity(CustomerReq request) {
        return CustomerEntity.builder()
                .name(request.getName())
                .lastname(request.getLastname())
                .phone(request.getPhone())
                .email(request.getEmail())
                .dateRecord(LocalDate.now()).build();
    }

    private CustomerRes entityToResponse(CustomerEntity entity) {
        var response = CustomerRes.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .name(entity.getName())
                .lastname(entity.getLastname())
                .phone(entity.getPhone())
                .build();

        if (Objects.nonNull(entity.getBooking())) {
            var bookingRes = new ArrayList<BookingRes>();

            entity.getBooking().forEach(booking -> {
                bookingRes.add(
                        BookingRes.builder()
                                .id(booking.getId())
                                .dateStart(booking.getDateStart())
                                .dateEnd(booking.getDateEnd())
                                .totalAmount(booking.getTotalAmount())
                                .build()
                );
            });
            response.setBooking(bookingRes);
        }
        return response;
    }
}
