/*
 * Copyright (c) 2015 Edlink Inc. All rights reserved.
 *
 * Author: lahotipritesh@gmail.com
 */

package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDTO;
import dao.UserRepository;
import org.apache.cxf.common.util.StringUtils;
import utils.AppConstants;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserRepository userRepository = new UserRepository();

    @Override
    protected void doPost(final HttpServletRequest req,
        final HttpServletResponse resp)
        throws ServletException, IOException {
        final String username = req.getParameter("username");
        if (StringUtils.isEmpty(username)) {
            resp.sendRedirect(createRedirectUrl("Username cannot be empty"));
            return;
        }

        final String password = req.getParameter("password");
        if (StringUtils.isEmpty(password)) {
            resp.sendRedirect(createRedirectUrl("Password cannot be empty"));
            return;
        }

        if (userRepository.authenticate(new UserDTO(username, password))) {
            final HttpSession httpSession = req.getSession();
            httpSession.setAttribute("userName", username);
            resp.sendRedirect(AppConstants.WELCOME_URL);
        } else {
            resp.sendRedirect(createRedirectUrl("Invalid credentials"));
        }
    }

    private String createRedirectUrl(final String code) {
        return AppConstants.LOGIN_URL + "?result=" + code;
    }
}
