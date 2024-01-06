package com.alejandro.booking_hotel.models.entities;

import com.alejandro.booking_hotel.models.enums.KindOf;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "room")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RoomEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "kind_of")
    private KindOf kindOf;
    private Integer size;
    private BigDecimal price;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            mappedBy = "room"
    )
    private List<BookingEntity> booking;
}
