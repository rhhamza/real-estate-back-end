package pi.dev.realestate.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import pi.dev.realestate.entities.Appointment;
import pi.dev.realestate.entities.UserEntity;
import pi.dev.realestate.repositories.UserRepository;
import pi.dev.realestate.services.interfaces.IMailAppointment;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;



@Service
public class MailAppointment implements IMailAppointment {

    @Autowired
    UserRepository userRepository;
    private final JavaMailSender javaMailSender;

    @Autowired
    public MailAppointment(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendAppointmentEmail(Appointment appointment, int userid) {
        UserEntity use =userRepository.findByID( userid );
        String to = use.getEmail();
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject("Appointment Details");

            // Create the HTML content
            String template = "<html>\n" +
                    "<head>\n" +
                    "    <style>\n" +
                    "        /* Define your CSS styles here */\n" +
                    "        body {\n" +
                    "            font-family: Arial, sans-serif;\n" +
                    "            background-color: #f6f6f6;\n" +
                    "        }\n" +
                    "        .container {\n" +
                    "            max-width: 600px;\n" +
                    "            margin: 0 auto;\n" +
                    "            padding: 20px;\n" +
                    "            background-color: #ffffff;\n" +
                    "            border-radius: 4px;\n" +
                    "            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);\n" +
                    "        }\n" +
                    "        h2 {\n" +
                    "            color: #333333;\n" +
                    "        }\n" +
                    "        p {\n" +
                    "            margin-bottom: 10px;\n" +
                    "        }\n" +
                    "        strong {\n" +
                    "            font-weight: bold;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <div class=\"container\">\n" +
                    "        <h2>Your appointment details:</h2>\n" +
                    "        <p><strong>Title:</strong> " + appointment.getTitle() + "</p>\n" +
                    "        <p><strong>Date Start:</strong> " + appointment.getDateDebut() + "</p>\n" +
                    "        <p><strong>Date End:</strong> " + appointment.getDateFin() + "</p>\n" +
                    "        <p><strong>Meeting Link:</strong> " + appointment.getMeetingLink() + "</p>\n" +
                    "        <p><strong>Description:</strong> " + appointment.getDiscrition() + "</p>\n" +
                    "    </div>\n" +
                    "</body>\n" +
                    "</html>";
            helper.setText(template, true);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            // Handle the exception appropriately
            e.printStackTrace();
        }
    }
}




