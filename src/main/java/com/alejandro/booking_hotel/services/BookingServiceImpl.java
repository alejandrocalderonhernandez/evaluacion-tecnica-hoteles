package com.alejandro.booking_hotel.services;

import com.alejandro.booking_hotel.beans.CacheConstants;
import com.alejandro.booking_hotel.exceptions.BookingException;
import com.alejandro.booking_hotel.models.entities.BookingEntity;
import com.alejandro.booking_hotel.models.request.BookingReq;
import com.alejandro.booking_hotel.models.response.BookingRes;
import com.alejandro.booking_hotel.models.response.RoomRes;
import com.alejandro.booking_hotel.repositories.BookingRepository;
import com.alejandro.booking_hotel.repositories.RoomRepository;
import com.alejandro.booking_hotel.services.abstracts.BookingService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@AllArgsConstructor
@Slf4j
@Transactional
@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;

    @Override
    public BookingRes create(BookingReq request) {

        this.validDates(request.getDateStart(), request.getDateEnd());
        var BookingToSave = this.requestToEntity(request);

        var room =  this.roomRepository.findById(request.getRoomId())
             .orElseThrow(() -> new BookingException("invalid room"));

        BookingToSave.setRoom(room);
        return this.entityToResponse(this.bookingRepository.save(BookingToSave));
    }

    @Override
    @Cacheable(value = CacheConstants.BOOKING_CACHE_NAME)
    public BookingRes read(Long id) {
        var responseDB = this.bookingRepository.findById(id)
                .orElseThrow(() -> new BookingException("booking not found"));

        var pricePerDay = responseDB.getRoom().getPrice();

        var start = responseDB.getDateStart();
        var end = responseDB.getDateEnd();
        var days = ChronoUnit.DAYS.between(start, end);

        var totalPrice = pricePerDay.multiply(BigDecimal.valueOf(days));

        responseDB.setTotalAmount(totalPrice);
        return this.entityToResponse(responseDB);
    }

    @Override
    @CacheEvict(cacheNames ={
            CacheConstants.BOOKING_CACHE_NAME
    }, allEntries = true)
    public BookingRes update(BookingReq request, Long id) {
        this.validDates(request.getDateStart(), request.getDateEnd());

        var responseDB = this.bookingRepository.findById(id)
                .orElseThrow(() -> new BookingException("Bocking not found"));

        responseDB.setStatus(request.getStatus());
        responseDB.setDateStart(request.getDateStart());
        responseDB.setDateEnd(request.getDateEnd());
        return this.entityToResponse(this.bookingRepository.save(responseDB));
    }

    @Override
    @CacheEvict(cacheNames ={
            CacheConstants.BOOKING_CACHE_NAME
    }, allEntries = true)
    public void delete(Long id) {
        if ((this.bookingRepository.existsById(id))) {
            this.bookingRepository.deleteById(id);
        } else {
            throw new BookingException("bocking not found");
        }
    }

    private BookingEntity requestToEntity(BookingReq request) {
        return BookingEntity.builder()
                .dateStart(request.getDateStart())
                .dateEnd(request.getDateEnd())
                .status(request.getStatus())
                .build();
    }

    private void validDates(LocalDate start, LocalDate end) {
        if(end.isBefore(start)) {
            throw new BookingException("start date couldn't be greater than end sate");
        }
    }

    private BookingRes entityToResponse(BookingEntity entity) {

        var roomRes = RoomRes.builder().build();
        if (Objects.nonNull(entity.getRoom())) {
            var roomEntity = entity.getRoom();
            roomRes.setDescription(roomEntity.getDescription());
            roomRes.setPrice(roomEntity.getPrice());
            roomRes.setKindOf(roomEntity.getKindOf());
            roomRes.setSize(roomEntity.getSize());
        }
        return BookingRes.builder()
                .id(entity.getId())
                .dateStart(entity.getDateStart())
                .dateEnd(entity.getDateEnd())
                .status(entity.getStatus())
                .room(roomRes)
                .totalAmount(entity.getTotalAmount()).build();
    }
}
