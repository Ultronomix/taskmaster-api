package com.revature.taskmaster.auth;

// This is an example of a request DTO (not required to be a Spring bean)
public class Credentials {

    private String username;
    private String password;

    // Jackson requires the DTOs have a no-arg constructor
    public Credentials() {
        super();
    }

    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
