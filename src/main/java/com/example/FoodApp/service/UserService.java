package com.example.FoodApp.service;

import com.example.FoodApp.model.UserLogin;
import com.example.FoodApp.model.Users;
import com.example.FoodApp.repository.UserReporsitory;
import com.example.FoodApp.util.LoginResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserReporsitory userRepo;

    BCryptPasswordEncoder encode =new BCryptPasswordEncoder(10);

    public UserService(UserReporsitory userRepo) {
        super();
        this.userRepo = userRepo;
    }

    public String addUser(Users user) {
        try {
            user.setPassword(encode.encode(user.getPassword()));
            userRepo.save(user);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return e.getMessage();
        }
        return "successfully added user ["+user.getId()+"] in the database..";
    }

    public List<Users> getAllUsers(){
        List<Users> users=userRepo.findAll();
        return users;
    }

    @Cacheable("users")
    public Users getUserById(Integer id){
        Optional<Users> user=userRepo.findById(id);
        System.out.println("service user--->"+user);
        return user.orElse(null);
    }

    @CacheEvict("users")
    public String deleteUserById(Integer id) {
        try {
            userRepo.deleteById(id);
        } catch (Exception e) {
            return "ERROR while deleting userId ["+id+"]";
        }

        return "successfully deleted userId ["+id+"]";
    }

    @CachePut("users")
    public String updateUserById(Integer id, String user) {

        Users existingUser;
        try {
            JSONObject json=new JSONObject(user);
            existingUser = userRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            if(json.has("username")) {
                existingUser.setUsername(json.getString("username"));
            }
            if(json.has("role")) {
                existingUser.setRole(json.getString("role"));
            }
            if(json.has("password")) {
                existingUser.setPassword(json.getString("password"));
            }
            if(json.has("email")) {
                existingUser.setEmail(json.getString("email"));
            }

            userRepo.save(existingUser);
        }catch(Throwable e) {
            e.printStackTrace();
            return "ERROR while updating userId ["+id+"]";
        }

        return userRepo.findById(id).get().toString();
    }

    public LoginResponse loginEmployee(UserLogin users) {
        Users user1 = userRepo.findByUsername(users.getUsername());
        if (user1 != null) {
            String password = users.getPassword();
            String encodedPassword = user1.getPassword();
            boolean isPwdRight = encode.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<Users> user = userRepo.findOneByUsernameAndPassword(users.getUsername(), encodedPassword);
                if (user.isPresent()) {
                    return new LoginResponse("Login Success", true, 100);
                } else {
                    return new LoginResponse("Login Failed", false, 104);
                }
            } else {
                return new LoginResponse("Password Not Match", false, 101);
            }
        }else {
            return new LoginResponse("Username not exits", false,102);
        }
    }
}
