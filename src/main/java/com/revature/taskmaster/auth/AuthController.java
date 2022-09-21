package com.revature.taskmaster.auth;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.revature.taskmaster.common.ErrorResponse;
import com.revature.taskmaster.common.exceptions.AuthenticationException;
import com.revature.taskmaster.common.exceptions.DataSourceException;
import com.revature.taskmaster.common.exceptions.InvalidRequestException;
import com.revature.taskmaster.users.UserResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static Logger logger = LogManager.getLogger(AuthController.class);

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public UserResponse authenticate(@RequestBody Credentials credentials, HttpServletRequest req) {

        UserResponse authUser = authService.authenticate(credentials);

        logger.info("Establishing user session for user: {}", authUser.getUsername());
        HttpSession userSession = req.getSession();
        userSession.setAttribute("authUser", authUser);

        return authUser;

    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(HttpServletRequest req) {
        req.getSession().invalidate();
    }

    @ExceptionHandler({InvalidRequestException.class, JsonMappingException.class})
    public ErrorResponse handleBadRequests(Exception e) {
        logger.warn("A bad request was received at {}, details: {}", LocalDateTime.now(), e.getMessage());
        return new ErrorResponse(400, e.getMessage());
    }

    @ExceptionHandler
    public ErrorResponse handleAuthenticationExceptions(AuthenticationException e) {
        logger.warn("A failed authentication occurred at {}, details: {}", LocalDateTime.now(), e.getMessage());
        return new ErrorResponse(401, e.getMessage());
    }

    @ExceptionHandler
    public ErrorResponse handleDataSourceExceptions(DataSourceException e) {
        logger.error("A datasource exception was thrown at {}, details: {}", LocalDateTime.now(), e.getMessage());
        return new ErrorResponse(500, e.getMessage());
    }

    @ExceptionHandler
    public ErrorResponse handleDataSourceExceptions(Exception e) {
        logger.error("A unhandled exception was thrown at {}, details: {}", LocalDateTime.now(), e.getMessage());
        return new ErrorResponse(500, "An unexpected exception occurred. Devs, please check logs.");
    }

}
