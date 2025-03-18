package org.example.addressbook.controller;

import jakarta.validation.Valid;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.addressbook.dto.*;
import org.example.addressbook.interfaces.IAuthInterface;
import org.example.addressbook.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

    @RestController
    @Slf4j
    public class UserController {


        @Autowired
        EmailService emailService;

        IAuthInterface iAuthInterface;

        // --> For Registration of a user
        @PostMapping(path = "/register")
        public String register(@Valid@RequestBody AuthUserDTO user) {
            log.info("Employee tried to register with body: {}", getJSON(user));
            return iAuthInterface.register(user);
        }

        // --> For User Login
        @PostMapping(path ="/login")
        public String login(@Valid@RequestBody LoginDTO user) {
            log.info("Employee tried to login with body: {}", getJSON(user));
            return iAuthInterface.login(user);
        }

        // --> For sending mail to another person
        @PostMapping(path = "/sendMail")
        public String sendMail(@Valid@RequestBody MailDTO message)  {
            log.info("Employee tried to send email with body: {}", getJSON(message));
            emailService.sendEmail(message.getTo(), message.getSubject(), message.getBody());
            return "Mail sent";
        }


        // --> Added forgot password functionality
        @PutMapping("/forgotPassword/{email}")
        public AuthUserDTO forgotPassword(@Valid@RequestBody PassDTO pass, @Valid@PathVariable String email){
            log.info("Employee applied for forgot password with body: {}", getJSON(pass));
            return iAuthInterface.forgotPassword(pass, email);
        }

        // --> Added reset password functionality
        @PutMapping("/resetPassword/{email}")
        public String resetPassword(@Valid@PathVariable String email ,@Valid@RequestParam String currentPass,@Valid@RequestParam String newPass) {
            log.info("Employee applied for forgot password with email: {}", email);
            return iAuthInterface.resetPassword(email, currentPass, newPass);
        }

        @GetMapping("/clear")
        public String clear(){
            log.info("Database clear request made ");
            return iAuthInterface.clear();
        }
        public String getJSON(Object object){
            try {
                ObjectMapper obj = new ObjectMapper();
                return obj.writeValueAsString(object);
            }
            catch(JsonProcessingException e){
                log.error("Reason : {} Exception : {}", "Conversion error from Java Object to JSON");
            }
            return null;
        }

    }

