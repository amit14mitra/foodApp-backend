package com.example.FoodApp.controller;

import com.example.FoodApp.model.UserLogin;
import com.example.FoodApp.model.Users;
import com.example.FoodApp.service.UserService;
import com.example.FoodApp.util.LoginResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/welcomeUser")
    public String welcomeMessage() {
        return "welcome user";
    }

    @PostMapping("/addUser")
    public String addUsers(@RequestBody Users user) {
        logger.info("Inside Add User service");

        logger.debug("User data :: " + user);
        return userService.addUser(user);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id){
        Users user = userService.getUserById(id);
        if(user!=null)
            return ResponseEntity.ok(user);
        else
            return ResponseEntity.ok("No User found with this Id :: " + id);
    }

    @GetMapping("/getUsers")
    public ResponseEntity<String> getUsers() {
        logger.info("Inside Get User Service");
        List<Users> users = userService.getAllUsers();

        return ResponseEntity.ok(users.toString());
    }

    @PutMapping("/updateUser/{id}")
    public String updateUser(@PathVariable Integer id,@RequestBody String user) {
        return userService.updateUserById(id,user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Integer id) {
        return userService.deleteUserById(id);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginEmployee(@RequestBody UserLogin users)
    {
        LoginResponse loginResponse = userService.loginEmployee(users);
        return ResponseEntity.ok(loginResponse);
    }


}
