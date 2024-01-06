package com.alejandro.booking_hotel.controllers.advice_handler;

import com.alejandro.booking_hotel.exceptions.BookingException;
import com.alejandro.booking_hotel.models.response.ErrorRes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookingHandler {

    @ExceptionHandler({BookingException.class})
    public ErrorRes handleIdNotFound(RuntimeException exception) {
        return ErrorRes.builder()
                .error(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.name())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }
}
