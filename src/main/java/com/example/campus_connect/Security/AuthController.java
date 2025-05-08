package com.example.campus_connect.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import com.example.campus_connect.DTOs.Auth.LoginRequest;
import com.example.campus_connect.DTOs.Auth.RegisterRequest;

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

    // public AuthController(AuthService authService) {
    //     this.authService = authService;
    //     this.jwtService = new JwtService();
    //     this.userDetailsService = new CustomUserDetailsService();
    // }

    // @PostMapping("/register")
    // public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
    //     return authService.register(registerRequest);
    // }

    // @PostMapping("/login")
    // public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
    //     return authService.login(loginRequest);
    // }

    // @GetMapping("/verify-email")
    // public ResponseEntity<?> verifyEmail(@RequestParam String token) {
    //     return authService.verifyEmail(token);
    // }

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
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        // Implement your registration logic here
        // For example, save the user to the database and send a confirmation email
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }
    
    @GetMapping("/verify-email")
    public ResponseEntity<?> verifyEmail(@RequestParam String token) {
        // Implement your email verification logic here
        // For example, check if the token is valid and activate the user's account
        return ResponseEntity.ok("Email verified successfully");
    }

}