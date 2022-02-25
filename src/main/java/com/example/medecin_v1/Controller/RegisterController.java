package com.example.medecin_v1.Controller;



import com.example.medecin_v1.entity.MedicalFile;
import com.example.medecin_v1.entity.User;
import com.example.medecin_v1.repository.MedicalFileRepository;
import com.example.medecin_v1.service.EmailService;
import com.example.medecin_v1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
public class RegisterController {

    private PasswordEncoder passwordEncoder;
    private UserService userService;
    private EmailService emailService;
    private MedicalFileRepository medicalFileRepository;

    @Autowired
    public RegisterController(
            UserService userService, EmailService emailService,MedicalFileRepository medicalFileRepository) {
        this.medicalFileRepository=medicalFileRepository;
        this.userService = userService;
        this.emailService = emailService;
    }

    //input data
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistrationForm(@Valid @RequestBody User user, HttpServletRequest request) {

        // Lookup user in database by e-mail
        //User user=new User();

       User userExists = userService.findByEmail(user.getEmail());
       System.out.println(userExists);

        if (userExists != null) {
            return "User Exist";

        }


        else { // new user so we create user and send confirmation e-mail

            // Disable user until they click on confirmation link in email

            user.setEnabled(false);
            String role=user.getRole();
            if(role.equals("doctor"))
                user.setRole("ROLE_DOCTOR");
            else
                user.setRole("ROLE_USER");


            // Generate random 36-character string token for confirmation link
            user.setConfirmationToken(UUID.randomUUID().toString());

            userService.saveUser(user);

            //	String appUrl = request.getScheme() + "://" + request.getServerName();

            String appUrl = "localhost:9001";


            SimpleMailMessage registrationEmail = new SimpleMailMessage();
            registrationEmail.setTo(user.getEmail());
            registrationEmail.setSubject("Registration Confirmation");
            registrationEmail.setText("To confirm your e-mail address, please click the link below:\n"
                    + appUrl + "/confirm?token=" + user.getConfirmationToken());
            registrationEmail.setFrom("spring.email.auth@gmail.com");

            emailService.sendEmail(registrationEmail);

            //Create a medical File
            MedicalFile med =new MedicalFile();
            med.setUser(user);
            medicalFileRepository.save(med);



        }

        return "A confirmation mail was sent to you ";
    }

}
