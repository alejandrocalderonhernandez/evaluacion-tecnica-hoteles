package com.alejandro.booking_hotel.beans;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource(value = "classpath:redis/cache.properties"),
})
public class PropertiesConfig {
}
