package com.alejandro.booking_hotel.exceptions;

import lombok.AllArgsConstructor;

public class BookingException extends RuntimeException {


    public BookingException(String msg) {
        super(msg);
    }
}
