package com.alejandro.booking_hotel.controllers;

import com.alejandro.booking_hotel.models.request.CustomerReq;
import com.alejandro.booking_hotel.models.response.CustomerRes;
import com.alejandro.booking_hotel.services.abstracts.CustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "customer")
@AllArgsConstructor
@Tag(name = "customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> post(@RequestBody CustomerReq req) {
        return ResponseEntity.created(URI.create(this.customerService.create(req).getId().toString())).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerRes> get(@PathVariable Long id) {
        return ResponseEntity.ok(this.customerService.read(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<CustomerRes> put(@RequestBody CustomerReq req, @PathVariable Long id) {
        return ResponseEntity.ok(this.customerService.update(req, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{customerId}/{bookingId}")
    public ResponseEntity<CustomerRes> patch(@PathVariable Long customerId, @PathVariable Long bookingId) {
        return ResponseEntity.ok(this.customerService.addReservation(customerId, bookingId));
    }
}
