package it.epicode.u5_w2_d5.trip;

import it.epicode.u5_w2_d5.employee.Employee;
import it.epicode.u5_w2_d5.exception.AlreadyExistsException;
import it.epicode.u5_w2_d5.exception.IdException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class TripSvc {
    private final TripRepository tripRepo;

    public Trip create(@Valid TripCreateRequest request) {
        Trip trip = new Trip();
        BeanUtils.copyProperties(request, trip);
        trip.setStatus(Status.PLANNED);
        return tripRepo.save(trip);
    }

    public Trip findById(Long id) {
        if(!tripRepo.existsById(id)){
            throw new EntityNotFoundException("Trip not found");
        }
        return tripRepo.findById(id).get();
    }

    public Trip updateStatus(Long id,Status newStatus){
        Trip trip = findById(id);
        trip.setStatus(newStatus);
        return tripRepo.save(trip);
    }

    public Trip update(Trip t) {
        return tripRepo.save(t);
    }

    public List<Trip> findAll() {
        return tripRepo.findAll();
    }

    public String delete(Long id) {
        Trip t = findById(id);
        tripRepo.delete(t);
        return "Trip with id " + id + " deleted";
    }
}
