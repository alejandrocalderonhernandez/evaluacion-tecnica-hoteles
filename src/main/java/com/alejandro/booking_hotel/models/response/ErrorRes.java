package com.alejandro.booking_hotel.models.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorRes  implements Serializable {

    private String status;
    private Integer code;
    private String error;
}
