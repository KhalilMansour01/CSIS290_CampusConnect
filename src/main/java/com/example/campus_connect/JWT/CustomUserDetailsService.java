package com.example.campus_connect.JWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.example.campus_connect.Entity.UsersEntity;
import com.example.campus_connect.Repository.UsersRepository;



import java.util.Optional;
// import java.util.Arrays;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UsersEntity> user = usersRepository.findByEmail(email);
        if (user.isPresent()) {
            var tempUser = user.get();
            String[] roles = getRoles(tempUser);
            
            return User.builder()
                    .username(tempUser.getEmail())
                    .password(tempUser.getPassword())
                    .roles(roles)
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }

    private String[] getRoles(UsersEntity user) {
        if (user.getUserType() == null) {
            return new String[] { "OSA_Admin" };
        } else {
            return user.getUserType().split(",");
        }
    }

    // private String[] getRoles(AdminEntity admin) {
    // if (admin.getRole() == null) {
    // return new String[] { "ROLE_ADMIN" };
    // } else {
    // // Add "ROLE_" prefix to each role
    // return Arrays.stream(admin.getRole().split(","))
    // .map(role -> "ROLE_" + role.trim())
    // .toArray(String[]::new);
    // }
    // }

}