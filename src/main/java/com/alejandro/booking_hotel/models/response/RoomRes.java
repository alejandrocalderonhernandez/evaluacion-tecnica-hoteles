package com.alejandro.booking_hotel.models.response;

import com.alejandro.booking_hotel.models.enums.KindOf;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoomRes  implements Serializable {

    private String description;
    private KindOf kindOf;
    private Integer size;
    private BigDecimal price;
}
