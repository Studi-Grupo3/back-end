package sptech.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sptech.school.dtos.AppointmentDTO;
import sptech.school.entities.Appointment;
import sptech.school.entities.User;
import sptech.school.repositories.AppointmentRepository;
import sptech.school.repositories.UserRepository;

import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private UserRepository userRepository;

    public Appointment create(AppointmentDTO dto) {
        User userStudent = userRepository.findById(dto.idStudent())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));

        User userTeacher = userRepository.findById(dto.idTeacher())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));

        if (appointmentRepository.existsByStudentIdAndTeacherIdAndDateTime(dto.idStudent(), dto.idTeacher(), dto.dateTime())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Users already have an appointment at this time.");
        }

        Appointment appointment = new Appointment(userStudent, userTeacher,dto.dateTime());
        return appointmentRepository.save(appointment);
    }

    public Appointment findById(Integer id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment not found."));
    }

    public List<Appointment> listAll() {
        return appointmentRepository.findAll();
    }

    public Appointment update(AppointmentDTO dto, Integer id) {
        Appointment appointment = findById(id);

        if (appointmentRepository.existsByStudentIdAndTeacherIdAndDateTime(dto.idStudent(), dto.idTeacher(), dto.dateTime())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Users already have an appointment at this time.");
        }

        if (dto.dateTime() != null) {
            appointment.setDateTime(dto.dateTime());
        }

        return appointmentRepository.save(appointment);
    }

    public void delete(Integer id) {
        if (!appointmentRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment not found.");
        }
        appointmentRepository.deleteById(id);
    }
}