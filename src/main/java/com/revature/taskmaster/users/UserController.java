package com.revature.taskmaster.users;

import com.revature.taskmaster.common.ResourceCreationResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

import static com.revature.taskmaster.common.SecurityUtils.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private static Logger logger = LogManager.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = "application/json")
    public List<UserResponse> getAllUsers(HttpServletRequest req) {
        logger.info("A GET request was received by /users at {}", LocalDateTime.now());
        HttpSession userSession = req.getSession(false);
        enforceAuthentication(userSession);
        enforcePermissions(userSession, "DIRECTOR");
        return userService.getAllUsers();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public UserResponse getUserById(@PathVariable String id, HttpSession userSession) {
        logger.info("A GET request was received by /users/{id} at {}", LocalDateTime.now());
        enforceAuthentication(userSession);
        enforcePermissions(userSession, "DIRECTOR");
        return userService.getUserById(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResourceCreationResponse registerNewUser(@RequestBody NewUserRequest requestBody) {
        logger.info("A POST request was received by /users at {}", LocalDateTime.now());
        return userService.register(requestBody);
    }

}
