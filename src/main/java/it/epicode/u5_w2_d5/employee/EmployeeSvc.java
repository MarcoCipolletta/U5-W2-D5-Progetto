package it.epicode.u5_w2_d5.employee;

import it.epicode.u5_w2_d5.cloudinary.CloudinarySvc;
import it.epicode.u5_w2_d5.email.EmailSvc;
import it.epicode.u5_w2_d5.exception.EmailAlreadyUsedException;
import it.epicode.u5_w2_d5.exception.AlreadyExistsException;
import it.epicode.u5_w2_d5.exception.IdException;
import it.epicode.u5_w2_d5.trip.Status;
import it.epicode.u5_w2_d5.trip.Trip;
import it.epicode.u5_w2_d5.trip.TripSvc;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Validated
public class EmployeeSvc {
    private final EmployeeRepository employeeRepo;
    private final CloudinarySvc cloudinarySvc;
    private final EmailSvc emailSvc;

    public Employee create(@Valid EmployeeCreateRequest request) {
        Employee e = new Employee();
        if (employeeRepo.existsByUsername(request.getUsername())) {
            throw new AlreadyExistsException("Username already exists");
        }
        if (employeeRepo.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyUsedException("Email already used");
        }
        BeanUtils.copyProperties(request, e);
        emailSvc.sendEmail(request.getEmail(), "Registration", "Welcome to our company, " + request.getUsername());
        return employeeRepo.save(e);
    }

    public Employee findById(Long id) {
        if (!employeeRepo.existsById(id)) {
            throw new EntityNotFoundException("Employee not found");
        }
        return employeeRepo.findById(id).get();
    }

    public String insertProfilePicture(Long id, MultipartFile file) {
        Employee e = findById(id);

        Map result = cloudinarySvc.uploader(file, "profilePictures");
        e.setProfilePicture((String) result.get("url"));
        employeeRepo.save(e);
        return (String) result.get("url");
    }

    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }

    public Employee update(Long id, EmployeeCreateRequest request) {
        Employee e = findById(id);

        BeanUtils.copyProperties(request, e);
        return employeeRepo.save(e);
    }

    public Employee update(Employee request) {
        return employeeRepo.save(request);
    }

    public String delete(Long id) {
        Employee e = findById(id);
        employeeRepo.delete(e);
        return "Employee with id " + id + " deleted";
    }

//    @Transactional
//    public String insertReservation(Long employeeId, Long tripId) {
//        Employee e = findById(employeeId);
//        Trip t = tripSvc.findById(tripId);
//        if(tripSvc.existsByEmployeeAndDataAndStatus(e, t.getDate(), Status.PLANNED)){
//            throw new AlreadyExistsException("Employee already has a trip on " + t.getDate());
//        }
//        e.getTrips().add(t);
//        t.setEmployee(e);
//        employeeRepo.save(e);
//        tripSvc.update(t);
//        return "Trip to " + t.getDestination() + " added to employee " + e.getUsername();
//    }
}