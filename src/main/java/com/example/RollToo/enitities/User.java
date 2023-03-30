package com.example.RollToo.enitities;

import javax.persistence.*;

@Entity
@Table (name = "users")
public class User {

    //below: variables for the data to be stored in the users table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "userId")
    private int id;

    @Column ( nullable = false, unique = true, length = 45)
    private String email;

    @Column (nullable = false, length = 45)
    private String password;

    @Column ( nullable = false, unique = true, length = 45)
    private String username;


    private boolean enabled;


    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    //ToString Method to print out data
    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
