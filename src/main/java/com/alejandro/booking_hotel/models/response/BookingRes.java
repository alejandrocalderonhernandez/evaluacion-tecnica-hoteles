package com.alejandro.booking_hotel.models.response;

import com.alejandro.booking_hotel.models.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookingRes  implements Serializable {

    private Long id;
    private Status status;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private BigDecimal totalAmount;
    private RoomRes room;
}
