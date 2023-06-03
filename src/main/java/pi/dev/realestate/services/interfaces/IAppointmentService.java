package pi.dev.realestate.services.interfaces;

import pi.dev.realestate.entities.Appointment;

import java.util.List;

public interface IAppointmentService {
    Appointment addAppointment (Appointment appointment);
    List<Appointment> getAllAppointments();
    Appointment getAppointment (long idAppointment);
}
