package com.example.FoodApp.repository;

import com.example.FoodApp.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserReporsitory extends MongoRepository<Users, Integer> {

    Users findByUsername(String username);
    Optional<Users> findOneByUsernameAndPassword(String email, String password);
}
