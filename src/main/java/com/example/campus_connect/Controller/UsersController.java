package com.example.campus_connect.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.campus_connect.DTOs.LoginForm;
import com.example.campus_connect.DTOs.RegisterForm;
import com.example.campus_connect.Entity.UsersEntity;
import com.example.campus_connect.Service.EmailService;
import com.example.campus_connect.Service.UsersService;

import jakarta.validation.Valid;

import java.util.*;
import org.springframework.web.bind.annotation.RequestParam;

@Validated
@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/all")
    public ResponseEntity<List<UsersEntity>> getAllUsers1() {
        List<UsersEntity> users = usersService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersEntity> getUserById(@PathVariable String id) {
        return usersService.getUserById(id);
    }

    /*
     * {
     * "firstName": "",
     * "lastName": "",
     * "email": "",
     * "password": "",
     * "userType": "Student/Officer/OSA_Admin",
     * "dateOfBirth": "yyyy-mm-dd"
     * }
     */
    // @PostMapping("/create")
    // public ResponseEntity<UsersEntity> createUser(@RequestBody UsersEntity user) {
    //     return usersService.createUser(user);
    // }

    @PutMapping("/update/{id}")
    public ResponseEntity<UsersEntity> updateUser(@PathVariable String id, @RequestBody UsersEntity user) {
        return usersService.updateUser(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        return usersService.deleteUser(id);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginForm loginForm) {
        return usersService.loginUser(loginForm);
    }

    @PostMapping("/register")
    public ResponseEntity<UsersEntity> registerUser(@Valid @RequestBody RegisterForm registerForm) {
        return usersService.registerUser(registerForm);
    }
    

    @GetMapping("/verify-email")
    public ResponseEntity<String> verifyEmail(@RequestParam String token) {
        return usersService.verifyEmailAndEnableUser(token);
    }

    @GetMapping("/send-email")
    public String sendEmail(@RequestParam String to, @RequestParam String subject,
            @RequestParam String body) {
        emailService.sendEmail(to, subject, body);
        return "Email sent successfully!";
    }
    

}
