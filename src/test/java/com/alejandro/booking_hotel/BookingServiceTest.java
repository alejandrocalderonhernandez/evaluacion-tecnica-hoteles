package com.alejandro.booking_hotel;

import com.alejandro.booking_hotel.models.entities.BookingEntity;
import com.alejandro.booking_hotel.models.entities.RoomEntity;
import com.alejandro.booking_hotel.models.enums.KindOf;
import com.alejandro.booking_hotel.models.request.BookingReq;
import com.alejandro.booking_hotel.models.response.BookingRes;
import com.alejandro.booking_hotel.repositories.BookingRepository;
import com.alejandro.booking_hotel.repositories.RoomRepository;
import com.alejandro.booking_hotel.services.abstracts.BookingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:redis/cache.properties")
public class BookingServiceTest {

    @MockBean
    private BookingRepository bookingRepository;

    @MockBean
    private RoomRepository roomRepository;

    @Autowired
    private BookingService target;

    @Test
    @DisplayName("create should work")
    public void create() {
        when(bookingRepository.save(any(BookingEntity.class)))
                .thenReturn(BookingEntity.builder().build());

        when(roomRepository.findById(anyLong()))
                .thenReturn(Optional.of(RoomEntity.builder().build()));

        var response = target.create(createRequest());

        verify(bookingRepository).save(any(BookingEntity.class));
        verify(roomRepository).findById(1L);

        assertNotNull(response);

    }

    @Test
    @DisplayName("read should work")
    public void read() {
        when(bookingRepository.findById(anyLong()))
                .thenReturn(Optional.of(createEntity()));

        when(roomRepository.findById(anyLong()))
                .thenReturn(Optional.of(createRoomEntity()));

        var response = target.read(1L);

        assertNotNull(response);

    }

    @Test
    @DisplayName("delete should work")
    public void delete() {
        when(bookingRepository.existsById(anyLong()))
                .thenReturn(true);

        target.delete(1L);

        verify(bookingRepository).deleteById(anyLong());



    }

    @Test
    @DisplayName("update should work")
    public void update() {
        when(bookingRepository.save(any(BookingEntity.class)))
                .thenReturn(BookingEntity.builder().build());

        when(bookingRepository.findById(anyLong()))
                .thenReturn(Optional.of(BookingEntity.builder().build()));

        when(roomRepository.findById(anyLong()))
                .thenReturn(Optional.of(RoomEntity.builder().build()));

        var response = target.update(createRequest(), 1L);

        verify(bookingRepository).save(any(BookingEntity.class));
        verify(bookingRepository).findById(1L);

        assertNotNull(response);

    }

    private BookingReq createRequest() {
        return BookingReq.builder()
                .roomId(1L)
                .dateStart(LocalDate.now())
                .dateEnd(LocalDate.MAX)
                .build();
    }

    private BookingEntity createEntity() {
        return BookingEntity.builder()
                .dateStart(LocalDate.now())
                .dateEnd(LocalDate.MAX)
                .room(createRoomEntity())
                .build();
    }

    private RoomEntity createRoomEntity() {
        return RoomEntity.builder()
                .id(1L)
                .price(BigDecimal.TEN)
                .kindOf(KindOf.COUPLE)
                .build();
    }
}
