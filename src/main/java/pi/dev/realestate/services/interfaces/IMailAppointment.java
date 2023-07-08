package pi.dev.realestate.services.interfaces;

import pi.dev.realestate.entities.Appointment;

import javax.mail.MessagingException;

public interface IMailAppointment {

       public void sendAppointmentEmail(Appointment appointment, int userid ) throws MessagingException;

}
