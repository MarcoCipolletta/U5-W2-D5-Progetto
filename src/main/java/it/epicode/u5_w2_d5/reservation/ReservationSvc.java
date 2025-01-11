package it.epicode.u5_w2_d5.reservation;

import it.epicode.u5_w2_d5.employee.Employee;
import it.epicode.u5_w2_d5.employee.EmployeeSvc;
import it.epicode.u5_w2_d5.exception.AlreadyExistsException;
import it.epicode.u5_w2_d5.trip.Trip;
import it.epicode.u5_w2_d5.trip.TripCreateRequest;
import it.epicode.u5_w2_d5.trip.TripSvc;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class ReservationSvc {
    private final ReservationRepository reservationRepo;
    private final EmployeeSvc employeeSvc;
    private final TripSvc tripSvc;

    @Transactional
    public Reservation create(@Valid ReservationCreateRequest request) {
        Employee employee = employeeSvc.findById(request.getEmployeeId());
        if(reservationRepo.existsByEmployeeAndRequestDate(employee, request.getRequestDate())){
            throw new AlreadyExistsException("Employee already has a reservation for this date");
        }
        TripCreateRequest tripCreateRequest = new TripCreateRequest();
        tripCreateRequest.setDate(request.getRequestDate());
        tripCreateRequest.setDestination(request.getDestination());
        Trip trip = tripSvc.create(tripCreateRequest);
        Reservation reservation = new Reservation();
        reservation.setTrip(trip);
        reservation.setEmployee(employee);
        reservation.setRequestDate(request.getRequestDate());
        reservation.setNotes(request.getNotes());

        trip.setReservation(reservation);
        employee.getReservations().add(reservation);

        employeeSvc.update(employee);
        tripSvc.update(trip);
        return reservationRepo.save(reservation);
    }
    public Reservation findById(Long id) {
        if(!reservationRepo.existsById(id)){
            throw new EntityNotFoundException("Reservation not found");
        }
        return reservationRepo.findById(id).get();
    }

    public Reservation updateNotes(Long id, String notes) {
        Reservation reservation = findById(id);
        reservation.setNotes(notes);
        return reservationRepo.save(reservation);
    }

    public List<Reservation> findAll(){
        return reservationRepo.findAll();
    }

    public String delete(Long id) {
        Reservation reservation = findById(id);
        reservationRepo.delete(reservation);
        return "Reservation with id " + id + " deleted";
    }
}
