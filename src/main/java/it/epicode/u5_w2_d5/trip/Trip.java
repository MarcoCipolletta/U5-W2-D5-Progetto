package it.epicode.u5_w2_d5.trip;

import com.fasterxml.jackson.annotation.JsonBackReference;
import it.epicode.u5_w2_d5.reservation.Reservation;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String destination;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(mappedBy = "trip")
    private Reservation reservation;
}
