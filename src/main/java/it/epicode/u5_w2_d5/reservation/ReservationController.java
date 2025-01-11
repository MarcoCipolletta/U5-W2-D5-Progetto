package it.epicode.u5_w2_d5.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private ReservationSvc reservationSvc;

    @PostMapping
    public ResponseEntity<Reservation> create(@RequestBody ReservationCreateRequest request) {
        return new ResponseEntity<>(reservationSvc.create(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> findAll() {
        return new ResponseEntity<>(reservationSvc.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> findById(@PathVariable Long id) {
        return new ResponseEntity<>(reservationSvc.findById(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}/notes")
    public ResponseEntity<Reservation> update(@PathVariable Long id, @RequestBody String request) {
        return new ResponseEntity<>(reservationSvc.updateNotes(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return new ResponseEntity<>(reservationSvc.delete(id), HttpStatus.OK);
    }


}
