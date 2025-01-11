package it.epicode.u5_w2_d5.reservation;

import it.epicode.u5_w2_d5.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;


public interface ReservationRepository extends JpaRepository<Reservation, Long> {
Boolean existsByEmployeeAndRequestDate(Employee employee, LocalDate requestDate);
}
