package com.example.campus_connect.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import com.example.campus_connect.DTOs.LoginForm;
import com.example.campus_connect.DTOs.RegisterForm;
import com.example.campus_connect.Entity.UsersEntity;
import com.example.campus_connect.Entity.VerificationToken;
import com.example.campus_connect.JWT.CustomUserDetailsService;
import com.example.campus_connect.JWT.JwtService;
import com.example.campus_connect.Repository.UsersRepository;
import com.example.campus_connect.Repository.VerificationTokenRepository;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserIdGeneratorService userIdGenerator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Autowired
    private EmailService emailService;

    // Get all users
    public List<UsersEntity> getAllUsers() {
        return usersRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    // Get user by ID
    public ResponseEntity<UsersEntity> getUserById(String id) {
        UsersEntity user = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return ResponseEntity.ok(user);
    }

    // Create a new user 
    public ResponseEntity<UsersEntity> createUser(UsersEntity user) {
        boolean exists = usersRepository.findByEmail(user.getEmail()).isPresent();
        if (exists) {
            throw new RuntimeException("User already exists with email: " + user.getEmail());
        } else {
            user.setId(userIdGenerator.generateUserId());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            UsersEntity createdUser = usersRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        }
    }

    // Update user details
    public ResponseEntity<UsersEntity> updateUser(String id, UsersEntity user) {
        UsersEntity existingUser = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        if (user.getFirstName() != null) {
            existingUser.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            existingUser.setLastName(user.getLastName());
        }
        // if(user.getEmail() != null) {
        // existingUser.setEmail(user.getEmail());
        // }
        if (user.getDateOfBirth() != null) {
            existingUser.setDateOfBirth(user.getDateOfBirth());
        }

        final UsersEntity updatedUser = usersRepository.save(existingUser);
        return ResponseEntity.ok(updatedUser);
    }

    // Delete user
    public ResponseEntity<String> deleteUser(String id) {
        UsersEntity user = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        // String userId = user.getId();
        if (!user.getEnabled()) {
            VerificationToken verificationToken = tokenRepository.findByToken(user.getVerificationToken())
                    .orElseThrow(
                            () -> new RuntimeException("Verification token not found for user: " + user.getEmail()));
            tokenRepository.delete(verificationToken);
        }
        usersRepository.delete(user);
        userIdGenerator.decrementLastNumber(user.getId());
        return ResponseEntity.ok("User deleted successfully!");
    }

    // Login user and generate JWT token
    public ResponseEntity<String> loginUser(@RequestBody LoginForm loginForm) {

        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginForm.email(), loginForm.password()));

        // Get the user details 
        UsersEntity user = usersRepository.findByEmail(loginForm.email())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + loginForm.email()));

        if (authentication.isAuthenticated()) {

            // if (!user.getEnabled()) {

            //     // Check if the user has a verification token
            //     boolean isTokenPresent = tokenRepository.findByUserId(user.getId()).isPresent();
            //     // Check if the token is expired
            //     boolean isTokenExpired = tokenRepository.findByToken(user.getVerificationToken()).get().getExpiryDate()
            //             .isBefore(LocalDateTime.now());

            //     if (isTokenPresent && isTokenExpired) {

            //         // delete old token
            //         tokenRepository.delete(tokenRepository.findByUserId(user.getId()).get());

            //         // generate new token and save it to the user
            //         String token = UUID.randomUUID().toString();
            //         VerificationToken verificationToken = generateVerificationToken(user, token);
            //         user.setVerificationToken(verificationToken.getToken());
            //         usersRepository.save(user);

            //         // Send the verification email
            //         sendEmailVerification(loginForm.email(), token);

            //         throw new RuntimeException("User is not enabled or token has expired");
            //     }

            // }
            String jwtTokeString = jwtService.generateToken(userDetailsService.loadUserByUsername(loginForm.email()));
            return ResponseEntity.ok(jwtTokeString);
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }

    // Register a new user and send verification email
    public ResponseEntity<UsersEntity> registerUser(@RequestBody RegisterForm registerForm) {
        boolean exists = usersRepository.findByEmail(registerForm.getEmail()).isPresent();
        if (exists) {
            throw new RuntimeException("User already exists with email: " + registerForm.getEmail());
        } else {

            UsersEntity newUser = new UsersEntity();

            // Generate a unique user ID
            newUser.setId(userIdGenerator.generateUserId());

            // Set user details from registerForm
            newUser.setFirstName(registerForm.getFirstName());
            newUser.setLastName(registerForm.getLastName());
            newUser.setEmail(registerForm.getEmail());
            newUser.setUserType(registerForm.getUserType());
            newUser.setDateOfBirth(registerForm.getDateOfBirth());
            newUser.setPassword(passwordEncoder.encode(registerForm.getPassword()));

            // Set default values
            newUser.setEnabled(false);
            newUser.setCreatedAt(LocalDateTime.now());

            // Save the user to the database
            usersRepository.save(newUser);

            // Generate a verification token
            String token = UUID.randomUUID().toString();
            VerificationToken verificationToken = generateVerificationToken(newUser, token);

            // Set the verification token in the user entity
            newUser.setVerificationToken(verificationToken.getToken());
            usersRepository.save(newUser);

            // TODO Send the verification email
            // sendEmailVerification(registerForm.getEmail(), token);

            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        }
    }

    // Verify email and enable user
    public ResponseEntity<String> verifyEmailAndEnableUser(String token) {
        Optional<VerificationToken> optionalToken = tokenRepository.findByToken(token);

        if (optionalToken.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid verification token.");
        }

        VerificationToken verificationToken = optionalToken.get();

        if (verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token has expired.");
        }

        UsersEntity user = verificationToken.getUser();
        user.setEnabled(true);
        user.setVerificationToken(null);
        usersRepository.save(user);

        tokenRepository.delete(verificationToken); // clean up

        return ResponseEntity.ok("Email verified successfully!");
    }

    // Generate a verification token
    public VerificationToken generateVerificationToken(UsersEntity user, String token) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationToken.setExpiryDate(LocalDateTime.now().plusMinutes(30));
        tokenRepository.save(verificationToken);
        return verificationToken;
    }

    // Send email verification
    public void sendEmailVerification(String email, String token) {
        String verificationUrl = "http://localhost:8080/api/users/verify-email?token=" + token;
        emailService.sendEmail(email, "Verify your email",
                "Click the link to verify your account: " + verificationUrl);
    }


}
