package it.epicode.u5_w2_d5.reservation;

import it.epicode.u5_w2_d5.employee.Employee;
import it.epicode.u5_w2_d5.trip.Trip;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;


@Data
public class ReservationCreateRequest {
    @NotNull(message = "EmployeeId is required")
    private Long employeeId;
    @NotNull(message = "Date is required")
    @Future(message = "Date must be in the future")
    private LocalDate requestDate;
    @NotBlank(message = "Destination is required")
    private String destination;
    private String notes;
}
