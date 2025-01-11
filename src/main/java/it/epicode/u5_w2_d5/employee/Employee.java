package it.epicode.u5_w2_d5.employee;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import it.epicode.u5_w2_d5.reservation.Reservation;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String username;
    private String firstname;
    private String surname;
    private String email;

    @Column(name = "profile_picture")
    private String profilePicture;

    @OneToMany(mappedBy = "employee")
    @JsonManagedReference
    private List<Reservation> reservations = new ArrayList<>();
}
