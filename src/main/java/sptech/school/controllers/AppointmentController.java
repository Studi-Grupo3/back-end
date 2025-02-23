package sptech.school.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.dtos.AppointmentDTO;
import sptech.school.entities.Appointment;
import sptech.school.services.AppointmentService;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody @Valid AppointmentDTO dto) {
        return ResponseEntity.ok(appointmentService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Integer id) {
        return ResponseEntity.ok(appointmentService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> listAppointments() {
        return ResponseEntity.ok(appointmentService.listAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointments(@RequestBody @Valid AppointmentDTO dto, @PathVariable Integer id) {
        return ResponseEntity.ok(appointmentService.update(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Integer id) {
        appointmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}