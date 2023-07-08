package pi.dev.realestate.services.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import pi.dev.realestate.entities.Appointment;
import pi.dev.realestate.entities.PropertyOffer;
import pi.dev.realestate.entities.UserEntity;
import pi.dev.realestate.repositories.AppointmentRepository;
import pi.dev.realestate.repositories.PropertyOfferRepository;
import pi.dev.realestate.services.interfaces.IAppointmentService;
import pi.dev.realestate.services.interfaces.IMailAppointment;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class AppointmentService implements IAppointmentService {


        @Autowired
       AppointmentRepository appointmentRepository;
        @Autowired
        PropertyOfferRepository propertyOfferRepository;
        @Autowired
        IMailAppointment iMailAppointment;




    @Override
    public Appointment addAppointment(Appointment appointment) throws MessagingException {
        LocalDateTime dateDebut = appointment.getDateDebut();
        LocalDateTime dateFin = appointment.getDateFin();
        PropertyOffer propertyOffer = appointment.getPropertyOffer();
        UserEntity user = appointment.getUser();
        boolean online = appointment.isOnline();
        String meetingLink = online ? generatelink() : null;


        // Check if the property offer has an existing appointment in the same date range
        if (hasExistingAppointmentForProperty(propertyOffer, dateDebut, dateFin)) {
            throw new IllegalArgumentException("Property offer already has an appointment during the specified date range");
        }

        // Check if the user has an existing appointment in the same date range
        if (hasExistingAppointmentForUser(user, dateDebut, dateFin)) {
            throw new IllegalArgumentException("User already has an appointment during the specified date range");
        }


        appointment.setMeetingLink(meetingLink);
      // Set property offer ID

        // Set the createdAt timestamp
        Timestamp now = new Timestamp(System.currentTimeMillis());
        appointment.setCreatedAt(now);

        // Save the Appointment object
        Appointment savedAppointment = appointmentRepository.save(appointment);
        int userid=  savedAppointment.getUser().getID();
        iMailAppointment.sendAppointmentEmail(savedAppointment, userid);

        return savedAppointment;
    }



    private boolean hasExistingAppointmentForProperty(PropertyOffer propertyOffer, LocalDateTime dateDebut, LocalDateTime dateFin) {
        List<Appointment> existingAppointments = appointmentRepository.findByPropertyOfferAndDateRange(propertyOffer, dateDebut, dateFin);
        return !existingAppointments.isEmpty();
    }

    private boolean hasExistingAppointmentForUser(UserEntity userconnect, LocalDateTime dateDebut, LocalDateTime dateFin) {
        List<Appointment> existingAppointments = appointmentRepository.findByUserAndDateRange(userconnect, dateDebut, dateFin);
        return !existingAppointments.isEmpty();
    }

        public List<Appointment> getAllAppointments(){
            return appointmentRepository.findAll();
        }

        public Appointment getAppointment(long idAppointment){
            return appointmentRepository.findById(idAppointment).orElse(null);
        }

    @Override
   /* public Appointment updateAppointment(long idAppointment, Appointment appointment) {
        Appointment existingAppointment = appointmentRepository.findById(idAppointment).orElse(null);
        if (existingAppointment != null) {
            existingAppointment.setDateDebut(appointment.getDateDebut());
            existingAppointment.setDateFin(appointment.getDateFin());
            existingAppointment.setTitle(appointment.getTitle());
            existingAppointment.setDiscrition(appointment.getDiscrition());

            existingAppointment.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            appointmentRepository.save(existingAppointment);
        }
        return existingAppointment;
    }*/
    public Appointment updateAppointment(long idAppointment, Appointment appointment) {
        Appointment existingAppointment = appointmentRepository.findById(idAppointment).orElse(null);

        if (existingAppointment != null) {
            LocalDateTime originalDateDebut = existingAppointment.getDateDebut();
            LocalDateTime originalDateFin = existingAppointment.getDateFin();
            boolean originalOnlineStatus = existingAppointment.isOnline();

            // Update the appointment properties
            existingAppointment.setDateDebut(appointment.getDateDebut());
            existingAppointment.setDateFin(appointment.getDateFin());
            existingAppointment.setTitle(appointment.getTitle());
            existingAppointment.setDiscrition(appointment.getDiscrition());
            existingAppointment.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

            // Check if the online status has changed
            if (!originalOnlineStatus && appointment.isOnline()) {
                // Appointment is being updated from offline to online
                String meetingLink = generatelink();
                existingAppointment.setMeetingLink(meetingLink);
                existingAppointment.setOnline(true);
            } else if (originalOnlineStatus && !appointment.isOnline()) {
                // Appointment is being updated from online to offline
                existingAppointment.setMeetingLink(null);
                existingAppointment.setOnline(false);
            }

            appointmentRepository.save(existingAppointment);
        }

        return existingAppointment;
    }

    @Override
    public void deleteAppiontment(long idAppointment) {
            appointmentRepository.deleteById(idAppointment);

    }

    @Value("${jitsi.meet.url}")
    private String jitsiMeetUrl;
    @Override
    public String generatelink() {
        // Generate a unique meeting ID or room name
        String meetingId = generateUniqueMeetingId();

        // Construct the meeting link using the Jitsi Meet URL and the meeting ID
        String meetingLink = jitsiMeetUrl + "/" + meetingId;

        return meetingLink;
    }

    private String generateUniqueMeetingId() {


        return RandomStringUtils.randomAlphanumeric(10);
    }

}
