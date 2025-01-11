package it.epicode.u5_w2_d5.trip;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TripUpdateStatusRequest {
    @NotNull(message = "Status is required")
    private Status status;
}
