package it.epicode.u5_w2_d5.trip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trip")
public class TripController {
    @Autowired
    private TripSvc tripSvc;

    @GetMapping
    public ResponseEntity<List<Trip>> findAll() {
        return new ResponseEntity<>(tripSvc.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trip> findById(@PathVariable Long id) {
        return new ResponseEntity<>(tripSvc.findById(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Trip> updateStatus(@PathVariable Long id, @RequestBody Status newStatus) {
        return new ResponseEntity<>(tripSvc.updateStatus(id, newStatus), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return new ResponseEntity<>(tripSvc.delete(id), HttpStatus.OK);
    }
}
