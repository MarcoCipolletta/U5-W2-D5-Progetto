package it.epicode.u5_w2_d5.trip;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class TripCreateRequest {

    @NotBlank(message = "Destination is required")
    private String destination;
    @NotNull(message = "Date is required")
    @Future(message = "Date must be in the future")
    private LocalDate date;

}
