package com.revature.taskmaster.common;

import com.revature.taskmaster.common.exceptions.AuthenticationException;
import com.revature.taskmaster.common.exceptions.AuthorizationException;
import com.revature.taskmaster.users.UserResponse;

import javax.servlet.http.HttpSession;

public class SecurityUtils {

    private SecurityUtils() {
        super();
    }

    public static void enforceAuthentication(HttpSession userSession) {
        if (userSession == null) {
            throw new AuthenticationException("Could not find HTTP session on request. Please log in to access this endpoint.");
        }
    }

    public static void enforcePermissions(HttpSession userSession, String expectedRole) {
        if (!((UserResponse) userSession.getAttribute("authUser")).getRole().equals(expectedRole)) {
            throw new AuthorizationException();
        }
    }

}
