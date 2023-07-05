package pi.dev.realestate.entities.DTO;

import lombok.Data;
import pi.dev.realestate.entities.Company;
import pi.dev.realestate.entities.PropertyOffer;
import pi.dev.realestate.entities.UserEntity;

import java.time.LocalDateTime;
@Data
public class AppointmentDto {

        private Long idAppointment;
        private String title;
        private String discrition;
        private LocalDateTime dateDebut;
        private LocalDateTime dateFin;
        private String MeetingLink;
        private boolean online;
private UserEntity user;
private PropertyOffer propertyOffer;
private Company company;

}
