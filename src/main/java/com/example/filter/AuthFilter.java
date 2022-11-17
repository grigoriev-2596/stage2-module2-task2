package com.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/user/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResp = (HttpServletResponse) servletResponse;

        HttpSession session = httpReq.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            httpResp.sendRedirect("/login.jsp");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

}