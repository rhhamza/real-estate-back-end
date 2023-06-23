package pi.dev.realestate.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pi.dev.realestate.entities.DTO.AuthResponseDto;
import pi.dev.realestate.entities.DTO.LoginDTO;

import pi.dev.realestate.entities.Roles;
import pi.dev.realestate.entities.StatusType;
import pi.dev.realestate.entities.UserEntity;
import pi.dev.realestate.repositories.RolesRepository;
import pi.dev.realestate.repositories.UserRepository;
import pi.dev.realestate.security.JWTGenerator;
import pi.dev.realestate.services.impl.UserService;

import javax.mail.PasswordAuthentication;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    JWTGenerator jwtGenerator;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RolesRepository rolesRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    ClientRegistrationRepository clientRegistrationRepository;

    @Autowired
    UserService userService;

    private static String authorizationRequestBaseUri
            = "oauth2/authorization";
    Map<String, String> oauth2AuthenticationUrls
            = new HashMap<>();




    @PostMapping("login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDTO loginDto) {
        Optional<UserEntity> user = userRepository.findByEmail(loginDto.getEmail());
        if (user == null || user.get().getStatus() != StatusType.ACTIVE) {
            // User not found or user's status is not active
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
    }

    @PostMapping("registerclient")
    public ResponseEntity<String> register(@RequestBody UserEntity userEntity) {
        if (userRepository.existsByEmail(userEntity.getEmail())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
        user.setFirstname(userEntity.getFirstname());
        user.setLastname(userEntity.getLastname());
        user.setEmail(userEntity.getEmail());
        user.setPassword(passwordEncoder.encode((userEntity.getPassword())));
        user.setPhone(userEntity.getPhone());
        //user.setStatus(StatusType.ACTIVE);
        user.setStatus(StatusType.INACTIVE);

        Roles roles = rolesRepository.findByName("client").get();
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);
        sendActivationEmail(user);


        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }
    @PostMapping("activeAccount")
    public UserEntity activeAccount (@RequestParam int id){
        return userService.activeUser(id);
    }
    private void sendActivationEmail(UserEntity user) {
        // Code to send the activation email
        // Use an email sending service or an email library to send the email
        // You will need to provide the necessary configuration (SMTP server details, email content, etc.)
        // Here's a simplified example using JavaMail API:

        String activationLink = "https://example.com/activate?userId=" + user.getID();
        String subject = "Activate your account";
        String body = "Dear " + user.getFirstname() + ",\n\n"
                + "Thank you for registering with our application! Please click the link below to activate your account:\n"
                + activationLink + "\n\n"
                + "Best regards,\n"
                + "Your Application Team";

        // Code to send the email using JavaMail API
        // You need to provide your SMTP server details and credentials
        // This is just a simplified example, make sure to handle exceptions and configure your email settings properly

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("hamza.melki.isetcom@gmail.com", "ahbklusopnzwbduy");
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("hamza.melki.isetcom@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }
    @GetMapping("/oauth_login")
    public String getLoginPage(Model model) {
        Iterable<ClientRegistration> clientRegistrations = null;
        ResolvableType type = ResolvableType.forInstance(clientRegistrationRepository)
                .as(Iterable.class);
        if (type != ResolvableType.NONE &&
                ClientRegistration.class.isAssignableFrom(type.resolveGenerics()[0])) {
            clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;
        }

        clientRegistrations.forEach(registration ->
                oauth2AuthenticationUrls.put(registration.getClientName(),
                        authorizationRequestBaseUri + "/" + registration.getRegistrationId()));
        model.addAttribute("urls", oauth2AuthenticationUrls);

        return "oauth_login";

            }

}
