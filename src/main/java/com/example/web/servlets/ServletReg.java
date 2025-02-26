package com.example.web.servlets;

import com.example.domain.Role;
import com.example.services.AdminService;
import com.example.utilites.Validation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletReg", value = "/reg.jhtml")
public class ServletReg extends HttpServlet {

    @Autowired
    private AdminService adminService;

    Validation validation = new Validation();
    String login;
    String pass;
    String name;
    String age;
    String role;
    String message;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/Sign_in.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        login = req.getParameter("login");
        pass = req.getParameter("pass");
        role = req.getParameter("role");
        age = req.getParameter("age");
        name = req.getParameter("name");

        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new Role(role));


        if (adminService.userIsExist(login, pass)) {
            message = "This login exist";
            req.setAttribute("message", message);
            req.getRequestDispatcher("WEB-INF/jsp/Sign_in.jsp").forward(req, resp);
        } else if (validation.isValidLogin(login)) {
            message = "Login is invalid";
            req.setAttribute("message", message);
            req.getRequestDispatcher("WEB-INF/jsp/Sign_in.jsp").forward(req, resp);
        } else if (!validation.isValidPassword(pass)) {
            message = "Password is invalid";
            req.setAttribute("message", message);
            req.getRequestDispatcher("WEB-INF/jsp/Sign_in.jsp").forward(req, resp);
        } else if (age.isEmpty() || Integer.parseInt(age) < 18 || Integer.parseInt(age) > 100) {
            message = "Age input error";
            req.setAttribute("message", message);
            req.getRequestDispatcher("WEB-INF/jsp/Sign_in.jsp").forward(req, resp);
        } else {
            adminService.regUser(login, name, Integer.parseInt(age), pass, roles);
            req.setAttribute("login", login);
            req.setAttribute("pass", pass);
            resp.sendRedirect("/auth.jhtml");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
