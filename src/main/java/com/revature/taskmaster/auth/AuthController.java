package com.revature.taskmaster.auth;

import com.revature.taskmaster.users.UserResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
//we need this annotation to get requests from our angular front ends
@CrossOrigin(origins="http://localhost:4200", allowCredentials="true")
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


    //whole new mapping for the ngPokes demos---------------------------------
    @PostMapping(value="/pokeLogin", produces = "application/json", consumes = "application/json")
    public Credentials loginToPokedex(@RequestBody Credentials credentials){

        System.out.println(credentials.getUsername());
        System.out.println(credentials.getPassword());

        //SUPER dinky hardcoded login
        //like, very dinky
        if(credentials.getUsername().equals("user") && credentials.getPassword().equals("password")){
            return credentials;
        }

        return null; //if the if statement fails...

    }

}
