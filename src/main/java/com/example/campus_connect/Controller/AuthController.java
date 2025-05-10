package com.example.campus_connect.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.campus_connect.DTOs.LoginForm;
import com.example.campus_connect.DTOs.RegisterForm;
import com.example.campus_connect.JWT.CustomUserDetailsService;
import com.example.campus_connect.JWT.JwtService;
import com.example.campus_connect.Service.UsersService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    // private final AuthService authService;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsersService usersService;

    @PostMapping("/login")
    public String authenticateAndGetToken(@RequestBody LoginForm loginForm) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginForm.email(), loginForm.password()));

        if(authentication.isAuthenticated()){
            return jwtService.generateToken(userDetailsService.loadUserByUsername(loginForm.email()));
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterForm registerRequest) {
        if (usersService.isUserExists(registerRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already registered");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        }
        // Implement your registration logic here
        // For example, save the user to the database and send a confirmation email
        
    }
    

    @GetMapping("/verify-email")
    public ResponseEntity<?> verifyEmail(@RequestParam String token) {
        // Implement your email verification logic here
        // For example, check if the token is valid and activate the user's account
        return ResponseEntity.ok("Email verified successfully");
    }

}