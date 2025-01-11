package it.epicode.u5_w2_d5.employee;

import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    public Boolean existsByUsername(String username);
    public Boolean existsByEmail(String email);

}
