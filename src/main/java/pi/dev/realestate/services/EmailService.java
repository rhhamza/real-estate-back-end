package pi.dev.realestate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pi.dev.realestate.entities.Company;
import pi.dev.realestate.entities.Order;
import pi.dev.realestate.services.interfaces.IEmailService;

@Service
public class EmailService implements IEmailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendOrderConfirmationEmail(Company company, Order order) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(company.getEmail());
        message.setSubject("Order Confirmation");
        message.setText("Dear " + company.getName() + ",\n\nYour order (ID: " + order.getId() + ") has been created.\n\nThank you for choosing our services.");

        javaMailSender.send(message);
    }


}
