// package com.example.campus_connect.Security;


// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.stereotype.Service;

// import com.example.campus_connect.Modules.Users.UsersRepository;
// import com.example.campus_connect.Modules.Users.UsersEntity;

// import java.util.List;


// @Service
// public class CustomUserDetailsService implements UserDetailsService {

//     private final UsersRepository usersRepository;

//     public CustomUserDetailsService(UsersRepository usersRepository) {
//         this.usersRepository = usersRepository;
//     }

//     @Override
//     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//         UsersEntity user = usersRepository.findByEmail(email)
//                 .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

//         return new org.springframework.security.core.userdetails.User(
//                 user.getEmail(),
//                 user.getPassword(),
//                 List.of(new SimpleGrantedAuthority("ROLE_" + user.getUserType().toUpperCase())) // e.g. ROLE_STUDENT
//         );
//     }
// }

