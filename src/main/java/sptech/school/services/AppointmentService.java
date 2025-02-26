package sptech.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sptech.school.dtos.AppointmentDTO;
import sptech.school.entities.Appointment;
import sptech.school.entities.Student;
import sptech.school.entities.Teacher;
import sptech.school.mappers.AppointmentMapper;
import sptech.school.repositories.AppointmentRepository;

import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    public Appointment create(AppointmentDTO dto) {
        Student student = studentService.findById(dto.idStudent());

        Teacher teacher = teacherService.findById(dto.idTeacher());

        if (appointmentRepository.existsByStudentIdAndTeacherIdAndDateTime(dto.idStudent(), dto.idTeacher(), dto.dateTime())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Users already have an appointment at this time.");
        }

        Appointment appointment = new Appointment(student, teacher,dto.dateTime());
        return appointmentRepository.save(appointment);
    }

    public Appointment findById(Integer id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment not found."));
    }

    public List<AppointmentDTO> listAll() {
        return appointmentRepository.findAll()
                .stream().map(appointmentMapper::toDto).toList();
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