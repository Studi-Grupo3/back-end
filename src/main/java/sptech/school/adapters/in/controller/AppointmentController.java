package sptech.school.adapters.in.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.domain.dto.AppointmentDTO;
import sptech.school.domain.entity.Appointment;
import sptech.school.applitcation.mappers.AppointmentMapper;
import sptech.school.applitcation.service.AppointmentService;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private AppointmentMapper appointmentMapper;

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody @Valid AppointmentDTO dto) {
        return ResponseEntity.ok(appointmentService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Integer id) {
        return ResponseEntity.ok(appointmentService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> listAppointments() {
        List<AppointmentDTO> appointmentDTOS = appointmentService.listAll();
        return ResponseEntity.status(200).body(appointmentDTOS);
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