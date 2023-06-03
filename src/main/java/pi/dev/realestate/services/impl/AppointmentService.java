package pi.dev.realestate.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pi.dev.realestate.entities.Appointment;
import pi.dev.realestate.repositories.AppointmentRepository;
import pi.dev.realestate.services.interfaces.IAppointmentService;
import java.util.List;


@Service
public class AppointmentService implements IAppointmentService {


        @Autowired
       AppointmentRepository appointmentRepository;

        @Override
        public Appointment addAppointment(Appointment appointment){
            appointmentRepository.save(appointment);
            return appointment;
        }

        public List<Appointment> getAllAppointments(){
            return appointmentRepository.findAll();
        }

        public Appointment getAppointment(long idAppointment){
            return appointmentRepository.findById(idAppointment).orElse(null);
        }

    }

