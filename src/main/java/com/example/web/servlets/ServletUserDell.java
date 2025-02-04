package com.example.web.servlets;

import com.example.services.AdminService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@WebServlet(name = "ServletUserDell", value = "/dell.jhtml")
public class ServletUserDell extends HttpServlet {

    @Autowired
    private AdminService adminService;

    String login;
    String pass;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        login = req.getParameter("login");
        req.setAttribute("login", login);
        pass = req.getParameter("pass");
        req.setAttribute("pass", pass);
        req.getRequestDispatcher("/WEB-INF/jsp/User_dell.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (adminService.userIsExist(login, pass)) {
            adminService.delete(login);
            String message = "User has been deleted";
            req.setAttribute("message", message);
            req.getRequestDispatcher("WEB-INF/jsp/UserList.jsp").forward(req, resp);
        } else {
            String message = "User hasn't been deleted";
            req.setAttribute("message", message);
            req.getRequestDispatcher("WEB-INF/jsp/UserList.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
    }
}
