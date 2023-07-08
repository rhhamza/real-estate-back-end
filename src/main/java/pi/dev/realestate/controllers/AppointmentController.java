package pi.dev.realestate.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pi.dev.realestate.entities.Appointment;
import pi.dev.realestate.entities.DTO.AppointmentDto;
import pi.dev.realestate.entities.PropertyOffer;
import pi.dev.realestate.entities.UserEntity;
import pi.dev.realestate.repositories.AppointmentRepository;
import pi.dev.realestate.services.interfaces.IAppointmentService;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/Appointment")
public class AppointmentController {
    @Autowired
    IAppointmentService iAppointmentService;
    @Autowired
    AppointmentRepository appointmentRepository;

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
    @GetMapping("/listby/{userId}")
    public ResponseEntity<Object> getAllAppointmentsByUser(@PathVariable int userId) {
        List<Appointment> appointments= appointmentRepository.findByUser_ID(userId);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
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
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        try {
            Appointment savedAppointment = iAppointmentService.addAppointment(appointment);
            return ResponseEntity.ok(savedAppointment);
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
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
