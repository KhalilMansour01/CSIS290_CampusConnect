package com.example.campus_connect.Controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import com.example.campus_connect.DTOs.LoginForm;
// import com.example.campus_connect.JWT.CustomUserDetailsService;
// import com.example.campus_connect.JWT.JwtService;
// import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    // private final AuthService authService;
    // @Autowired
    // private JwtService jwtService;

    // @Autowired
    // private CustomUserDetailsService userDetailsService;

    // @Autowired
    // private AuthenticationManager authenticationManager;

    // @PostMapping("/login")
    // public String authenticateAndGetToken(@RequestBody LoginForm loginForm) {
    //     Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
    //             loginForm.email(), loginForm.password()));

    //     if (authentication.isAuthenticated()) {
    //         return jwtService.generateToken(userDetailsService.loadUserByUsername(loginForm.email()));
    //     } else {
    //         throw new UsernameNotFoundException("Invalid username or password");
    //     }
    // }

}