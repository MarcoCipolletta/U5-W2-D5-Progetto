package it.epicode.u5_w2_d5.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeSvc employeeSvc;


    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody EmployeeCreateRequest request) {
        return new ResponseEntity<>(employeeSvc.create(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> findAll() {
        return new ResponseEntity<>(employeeSvc.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable Long id) {
        return new ResponseEntity<>(employeeSvc.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody EmployeeCreateRequest request) {
        return new ResponseEntity<>(employeeSvc.update(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return new ResponseEntity<>(employeeSvc.delete(id), HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}/profilePicture", consumes = "multipart/form-data")
    public ResponseEntity<String> insertProfilePicture(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(employeeSvc.insertProfilePicture(id, file), HttpStatus.OK);
    }
}
