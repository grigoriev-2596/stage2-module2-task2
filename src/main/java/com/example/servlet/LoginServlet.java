package com.example.servlet;

import com.example.Users;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            //response.sendRedirect("/login.jsp");
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/user/hello.jsp").forward(request, response);
//             response.sendRedirect("/user/hello.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        List<String> users = Users.getInstance().getUsers();
        if (users.contains(login) && !password.trim().isEmpty()) {
            request.getSession().setAttribute("user", login);

            getServletContext().getRequestDispatcher("/user/hello.jsp").forward(request, response);
//            response.sendRedirect("/user/hello.jsp");
        } else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);

//            response.sendRedirect("/login.jsp");
        }
    }
}
