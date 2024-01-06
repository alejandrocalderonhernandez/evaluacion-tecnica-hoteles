package com.alejandro.booking_hotel.services.abstracts;

import com.alejandro.booking_hotel.models.request.CustomerReq;
import com.alejandro.booking_hotel.models.response.CustomerRes;

public interface CustomerService extends CrudService<CustomerReq, CustomerRes, Long> {

     CustomerRes addReservation(Long customerId, Long bookingId);
}
