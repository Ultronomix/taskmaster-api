package com.revature.taskmaster.auth;

import com.revature.taskmaster.common.exceptions.AuthenticationException;
import com.revature.taskmaster.common.exceptions.InvalidRequestException;
import com.revature.taskmaster.users.UserDAO;
import com.revature.taskmaster.users.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserDAO userDAO;

    @Autowired
    public AuthService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserResponse authenticate(Credentials credentials) {

        if (credentials == null) {
            throw new InvalidRequestException("The provided credentials object was found to be null.");
        }

        if (credentials.getUsername().length() < 4) {
            throw new InvalidRequestException("The provided username was not the correct length (must be at least 4 characters).");
        }

        if (credentials.getPassword().length() < 8) {
            throw new InvalidRequestException("The provided password was not the correct length (must be at least 8 characters).");
        }

        return userDAO.findUserByUsernameAndPassword(credentials.getUsername(), credentials.getPassword())
                      .map(UserResponse::new)
                      .orElseThrow(AuthenticationException::new);

    }
}
