package pi.dev.realestate.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pi.dev.realestate.entities.Company;
import pi.dev.realestate.entities.Order;

@Service
public class EmailService {




    @Autowired
    JavaMailSender javaMailSender;



    public void sendOrderConfirmationEmail(Company company, Order order) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(company.getEmail());
        message.setSubject("Order Confirmation");
        message.setText("Dear " + company.getName() + ",\n\nYour order (ID: " + order.getId() + ") has been created.\n\nThank you for choosing our services.");

        this.javaMailSender.send(message);
    }
    public void sendExpirationEmail(Company company) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(company.getEmail());
        message.setSubject("Order Expiration Notification");
        message.setText("Dear Company,\n\nYour order is going to expire soon.\n\nPlease take appropriate action.\n\nRegards,\nThe Real Estate Team");

        this.javaMailSender.send(message);
    }



    public void orderConfirmationByAdmin(Company company, Order order) {
        SimpleMailMessage message = new SimpleMailMessage();
        String activationLink = "https://example.com/activate?userId=" + order.getId();
        message.setTo("naoufel.ferjani@esprit.tn");
        message.setSubject("Order Request");
        message.setText("Dear Admin  ,\n\nThe Company "+company.getName()+" ask for order confimation:\n\n"+ activationLink +"\n\n"+"Best regards,\n\n" +
                        "Your Application Team;");

        this.javaMailSender.send(message);
    }



}

