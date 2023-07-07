package pi.dev.realestate.services.interfaces;

import pi.dev.realestate.entities.Appointment;
import pi.dev.realestate.entities.PropertyOffer;

import javax.mail.MessagingException;
import java.util.List;

public interface IAppointmentService {
    Appointment addAppointment (Appointment appointment) throws MessagingException;
    List<Appointment> getAllAppointments();
    Appointment getAppointment (long idAppointment);

    public String generatelink();
    Appointment updateAppointment(long idAppointment, Appointment appointment);
    void deleteAppiontment(long idAppointment);

    //mmvoid sendEmail(String to, String subject, String body) throws MessagingException;
}

