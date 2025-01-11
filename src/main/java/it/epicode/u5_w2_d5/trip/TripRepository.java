package it.epicode.u5_w2_d5.trip;

import it.epicode.u5_w2_d5.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;


public interface TripRepository extends JpaRepository<Trip, Long> {
    boolean existsByDestinationAndDate(String destination, LocalDate date);

}
