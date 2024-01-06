package com.alejandro.booking_hotel.beans;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Booking hotel API",
                version = "1.0",
                description = "Documentation for endpoints")
)
public class SwaggerBean {
}
