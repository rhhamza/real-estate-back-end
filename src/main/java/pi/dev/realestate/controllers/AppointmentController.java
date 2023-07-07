package pi.dev.realestate.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pi.dev.realestate.entities.Appointment;
import pi.dev.realestate.entities.DTO.AppointmentDto;
import pi.dev.realestate.entities.PropertyOffer;
import pi.dev.realestate.entities.UserEntity;
import pi.dev.realestate.services.interfaces.IAppointmentService;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/Appointment")
public class AppointmentController {
    @Autowired
    IAppointmentService iAppointmentService;

   // @PostMapping("/add")
  //  public ResponseEntity<Object> addAppointment(@RequestBody Appointment appointment) {
       // Appointment newaddedAppointment = iAppointmentService.addAppointment(appointment);
      //  return new ResponseEntity<>(newaddedAppointment, HttpStatus.CREATED);
  //  }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllAppointments() {
        List<Appointment> appointment = iAppointmentService.getAllAppointments();

        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }

    @GetMapping("/{idAppointment}")
    public ResponseEntity<Object> getAppointment(@PathVariable long idAppointment) {
        Appointment appointment = iAppointmentService.getAppointment(idAppointment);
        if (appointment != null) {
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Appointment not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/{idAppointment}")
    public ResponseEntity<Object> deleteAppiontment(@PathVariable long idAppointment) {
        iAppointmentService.deleteAppiontment(idAppointment);
        return new ResponseEntity<>("Appointment is deleted", HttpStatus.OK);
    }

    @PutMapping("update/{idAppointment}")
    public ResponseEntity<Object> updateAppointment(@PathVariable long idAppointment, @RequestBody Appointment
            oldappointment) {
        Appointment appointment = iAppointmentService.updateAppointment(idAppointment, oldappointment);
        if (appointment != null) {
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Appointment not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody AppointmentDto appointmentDto) {
        // Extract data from the AppointmentDto
        String title = appointmentDto.getTitle();
        String discrition = appointmentDto.getDiscrition();
        LocalDateTime dateStart = appointmentDto.getDateDebut();
        LocalDateTime dateEnd = appointmentDto.getDateFin();
       UserEntity user = appointmentDto.getUser();
        PropertyOffer propertyOffer = appointmentDto.getPropertyOffer();
       // Company company = appointmentDto.getCompany();
        boolean online = appointmentDto.isOnline();

        // Generate MeetingLink if online is true
        String meetingLink = online ? iAppointmentService.generatelink() : null;

        // Create the Appointment object
        Appointment appointment = new Appointment();
        appointment.setTitle(title);
        appointment.setDiscrition(discrition);
        appointment.setDateDebut(dateStart);
        appointment.setDateFin(dateEnd);
        appointment.setOnline(online);
        appointment.setMeetingLink(meetingLink);
        appointment.setPropertyOffer(propertyOffer);
        appointment.setUser(user);

        // Set other properties and relationships if needed

        // Save the Appointment object
        //return iAppointmentService.addAppointment(appointment);

        try {
            Appointment savedAppointment = iAppointmentService.addAppointment(appointment);
            return ResponseEntity.ok(savedAppointment);
        } catch (MessagingException e) {
            // Gérez l'exception ou enregistrez une erreur
            // Par exemple, retournez une réponse d'erreur appropriée avec un message d'erreur
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //  @GetMapping("generatelin/{idAppointment}")
    // public ResponseEntity<Object> generatelink (@PathVariable long idAppointment){
    //  Appointment appointment = iAppointmentService.getAppointment(idAppointment);
    //    return new ResponseEntity<>(iAppointmentService.generatelink(appointment), HttpStatus.CREATED);
    //  }

   // @PostMapping("/send-mail")
    //public String sendMail(@RequestBody MailDetail mailDetail) {
     //   return sendMail(mailDetail);
   // }
}