package com.example.FoodApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Document(collection = "users")
@Data
public class Users {

    @Id
    private int id;
    private String username;
    private String role;
    private String password;
    private String email;

    public Users(){
        super();
    }

    public Users(int id, String username, String role, String password,String email) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.password = password;
        this.email=email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    @JsonIgnore
    public JSONObject getJSON() {
        JSONObject json=new JSONObject();

        json.put("userId", this.id);
        json.put("username", this.username);
        json.put("role", this.role);
        json.put("email", this.email);
//        json.put("password", this.password);

        return json;
    }
}
