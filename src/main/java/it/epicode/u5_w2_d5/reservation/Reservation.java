package it.epicode.u5_w2_d5.reservation;

import com.fasterxml.jackson.annotation.JsonBackReference;
import it.epicode.u5_w2_d5.trip.Trip;
import it.epicode.u5_w2_d5.employee.Employee;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    @JoinColumn(name = "trip_id")
    @JsonBackReference
    private Trip trip;
    //lo utilizza @jsonBackReference per scrivere solo l'id del viaggio
    public Long getTripId() {
        return trip != null ? trip.getId() : null;
    }


    @ManyToOne
    @JsonBackReference
    private Employee employee;
    public Long getEmployeeId() {
        return employee != null ? employee.getId() : null;
    }



    private LocalDate requestDate;

     private String notes;

}
