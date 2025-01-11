package it.epicode.u5_w2_d5.employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmployeeCreateRequest {
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Firstname is required")
    private String firstname;
    @NotBlank(message = "Surname is required")
    private String surname;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
}
