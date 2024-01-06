package com.alejandro.booking_hotel.models.response;

import com.alejandro.booking_hotel.models.entities.BookingEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerRes implements Serializable {

    private Long id;
    private String name;
    private String lastname;
    private String email;
    private String phone;
    private List<BookingRes> booking;
}
