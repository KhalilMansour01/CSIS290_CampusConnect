package com.example.club_connect.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;

import com.example.club_connect.Entity.UsersEntity;
import com.example.club_connect.Repository.UsersRepository;

import java.util.*;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public List<UsersEntity> getAllUsers() {
        List<UsersEntity> usersList = usersRepository.findAll();

        if (usersList.size() > 0) {
            return usersList;
        } else {
            return new ArrayList<UsersEntity>();
        }
    }

    public ResponseEntity<UsersEntity> getUserById(String id) {
        UsersEntity user = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<UsersEntity> createUser(UsersEntity user) {
        // boolean isExist = usersRepository.existsById(user.getId());
        // if (isExist) {
        //     throw new RuntimeException("User already exists with id: " + user.getId());
        // } else {
            UsersEntity createdUser = usersRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        // }
    }

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

    public ResponseEntity<UsersEntity> deleteUser(String id) {
        UsersEntity user = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        usersRepository.delete(user);
        return ResponseEntity.ok().build();
    }
}
