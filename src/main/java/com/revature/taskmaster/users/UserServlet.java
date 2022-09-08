package com.revature.taskmaster.users;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.taskmaster.common.ResourceCreationResponse;
import com.revature.taskmaster.common.exceptions.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServlet extends HttpServlet {

    private final UserService userService;

    // TODO inject a shared reference to a configured ObjectMapper
    public UserServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper jsonMapper = new ObjectMapper();
        resp.setContentType("application/json");

        HttpSession userSession = req.getSession(false);

        // If userSession is null, this means that the requester is not authenticated with the server
        if (userSession == null) {

            // TODO encapsulate error response creation into its own utility method
            resp.setStatus(401);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("statusCode", 401);
            errorResponse.put("message", "Requester is not authenticated with the system, please log in.");
            errorResponse.put("timestamp", System.currentTimeMillis()); // TODO replace with LocalDateTime.now()
            resp.getWriter().write(jsonMapper.writeValueAsString(errorResponse));
            return;

        }

        String idToSearchFor = req.getParameter("id");

        UserResponse requester = (UserResponse) userSession.getAttribute("authUser");

        if (!requester.getRole().equals("DIRECTOR") && !requester.getId().equals(idToSearchFor)) {

            // TODO encapsulate error response creation into its own utility method
            resp.setStatus(403); // FORBIDDEN; the system recognizes the user, but they do not have permission to be here
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("statusCode", 403);
            errorResponse.put("message", "Requester is not permitted to communicate with this endpoint.");
            errorResponse.put("timestamp", System.currentTimeMillis()); // TODO replace with LocalDateTime.now()
            resp.getWriter().write(jsonMapper.writeValueAsString(errorResponse));
            return;

        }

        try {

            if (idToSearchFor == null) {
                List<UserResponse> allUsers = userService.getAllUsers();
                resp.addHeader("X-My-Custom-Header", "some-random-value");
                resp.getWriter().write(jsonMapper.writeValueAsString(allUsers));
            } else {
                UserResponse foundUser = userService.getUserById(idToSearchFor);
                resp.getWriter().write(jsonMapper.writeValueAsString(foundUser));
            }

        } catch (InvalidRequestException | JsonMappingException e) {

            // TODO encapsulate error response creation into its own utility method
            resp.setStatus(400); // BAD REQUEST;
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("statusCode", 400);
            errorResponse.put("message", e.getMessage());
            errorResponse.put("timestamp", System.currentTimeMillis()); // TODO replace with LocalDateTime.now()
            resp.getWriter().write(jsonMapper.writeValueAsString(errorResponse));

        } catch (ResourceNotFoundException e) {

            resp.setStatus(404); // NOT FOUND; the sought resource could not be located
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("statusCode", 404);
            errorResponse.put("message", e.getMessage());
            errorResponse.put("timestamp", System.currentTimeMillis()); // TODO replace with LocalDateTime.now()
            resp.getWriter().write(jsonMapper.writeValueAsString(errorResponse));

        } catch (DataSourceException e) {

            resp.setStatus(500); // INTERNAL SERVER ERROR; general error indicating a problem with the server
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("statusCode", 500);
            errorResponse.put("message", e.getMessage());
            errorResponse.put("timestamp", System.currentTimeMillis()); // TODO replace with LocalDateTime.now()
            resp.getWriter().write(jsonMapper.writeValueAsString(errorResponse));

        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper jsonMapper = new ObjectMapper();
        resp.setContentType("application/json");
        try {

            NewUserRequest requestBody = jsonMapper.readValue(req.getInputStream(), NewUserRequest.class);
            ResourceCreationResponse responseBody = userService.register(requestBody);
            resp.getWriter().write(jsonMapper.writeValueAsString(responseBody));

        } catch (InvalidRequestException | JsonMappingException e) {

            // TODO encapsulate error response creation into its own utility method
            resp.setStatus(400); // BAD REQUEST;
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("statusCode", 400);
            errorResponse.put("message", e.getMessage());
            errorResponse.put("timestamp", System.currentTimeMillis()); // TODO replace with LocalDateTime.now()
            resp.getWriter().write(jsonMapper.writeValueAsString(errorResponse));

        } catch (ResourcePersistenceException e) {

            resp.setStatus(409); // CONFLICT; indicates that the provided resource could not be saved without conflicting with other data
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("statusCode", 409);
            errorResponse.put("message", e.getMessage());
            errorResponse.put("timestamp", System.currentTimeMillis()); // TODO replace with LocalDateTime.now()
            resp.getWriter().write(jsonMapper.writeValueAsString(errorResponse));

        } catch (DataSourceException e) {

            resp.setStatus(500); // INTERNAL SERVER ERROR; general error indicating a problem with the server
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("statusCode", 500);
            errorResponse.put("message", e.getMessage());
            errorResponse.put("timestamp", System.currentTimeMillis()); // TODO replace with LocalDateTime.now()
            resp.getWriter().write(jsonMapper.writeValueAsString(errorResponse));

        }

    }
}
