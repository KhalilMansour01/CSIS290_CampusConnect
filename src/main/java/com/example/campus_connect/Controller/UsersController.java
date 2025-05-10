package com.example.campus_connect.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.campus_connect.Entity.UsersEntity;
import com.example.campus_connect.Service.UsersService;

import java.util.*;

@Controller
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    // @GetMapping("/all")
    // public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
    // List<UserResponseDTO> users = usersService.getAllUsers();
    // return new ResponseEntity<>(users, HttpStatus.OK);
    // }

    @GetMapping("/all")
    public ResponseEntity<List<UsersEntity>> getAllUsers1() {
        List<UsersEntity> users = usersService.getAllUsers1();
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
    @PostMapping("/create")
    public ResponseEntity<UsersEntity> createUser(@RequestBody UsersEntity user) {
        return usersService.createUser(user);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UsersEntity> updateUser(@PathVariable String id, @RequestBody UsersEntity user) {
        return usersService.updateUser(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UsersEntity> deleteUser(@PathVariable String id) {
        return usersService.deleteUser(id);
    }

}
