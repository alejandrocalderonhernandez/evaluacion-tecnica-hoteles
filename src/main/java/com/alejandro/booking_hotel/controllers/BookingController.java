package com.alejandro.booking_hotel.controllers;

import com.alejandro.booking_hotel.models.request.BookingReq;
import com.alejandro.booking_hotel.models.response.BookingRes;
import com.alejandro.booking_hotel.services.abstracts.BookingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "booking")
@AllArgsConstructor
@Tag(name = "booking")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<String> post(@RequestBody BookingReq req) {
        return ResponseEntity.created(URI.create(this.bookingService.create(req).getId().toString())).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<BookingRes> get(@PathVariable Long id) {
        return ResponseEntity.ok(this.bookingService.read(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<BookingRes> put(@RequestBody BookingReq req, @PathVariable Long id) {
        return ResponseEntity.ok(this.bookingService.update(req, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.bookingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
