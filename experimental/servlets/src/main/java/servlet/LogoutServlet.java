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

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

  @Override
  protected void doPost(final HttpServletRequest req,
      final HttpServletResponse resp)
      throws ServletException, IOException {
    final HttpSession httpSession = req.getSession(false);
    if (httpSession != null && httpSession.getAttribute("userName") != null) {
      httpSession.invalidate();
    }
  }
}