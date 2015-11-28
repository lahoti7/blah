/*
 * Copyright (c) 2015 Edlink Inc. All rights reserved.
 *
 * Author: lahotipritesh@gmail.com
 */

package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/welcome.jsp")
public class AppFilter implements Filter {

  public static final String ROOT_URL = "index.jsp";

  @Override
  public void init(final FilterConfig filterConfig) throws ServletException {}

  @Override
  public void doFilter(final ServletRequest servletRequest,
      final ServletResponse servletResponse, final FilterChain filterChain)
      throws IOException, ServletException {
    final HttpServletRequest request = (HttpServletRequest) servletRequest;
    final HttpServletResponse response = (HttpServletResponse) servletResponse;

    final HttpSession httpSession = request.getSession(false);
    if (httpSession == null || httpSession.getAttribute("userName") == null) {
      response.sendRedirect(ROOT_URL);
    } else {
      filterChain.doFilter(request, response);
    }
  }

  @Override
  public void destroy() {}
}
