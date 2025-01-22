package com.example.web.servlets;

import com.example.dao.UserDaoImplement;
import com.example.domain.Role;
import com.example.domain.User;
import com.example.services.ServiceDaoImplement;
import com.example.utilites.Validation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletEditUser", value = "/edit.jhtml")
public class ServletEditUser extends HttpServlet {

    String loginToEdit;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        loginToEdit = req.getParameter("login");
        for (User u : UserDaoImplement.getInstance().getAll()) {
            if (u.getLogin().equals(loginToEdit)) {
                req.setAttribute("user", u);
            }
        }
        req.getRequestDispatcher("WEB-INF/jsp/User_edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Validation validation = new Validation();

        String login = req.getParameter("login");
        String name = req.getParameter("name");
        String salary = req.getParameter("salary");
        String age = req.getParameter("age");
        String birthdayStr = req.getParameter("birthday");
        String role = req.getParameter("role");

        String message = null;
        ArrayList<Role> roles = new ArrayList<>();

        if (role.equals("ADMIN")) {
            roles.add(new Role(1, role));
        } else {
            roles.add(new Role(2, role));
        }

        if (validation.isValidLogin(login)) {
            try {
                ServiceDaoImplement.getInstance().editUser(login, name, Integer.parseInt(age), birthdayStr, Float.parseFloat(salary), roles);
                message = "user is edited";
            } catch (Exception e) {
                message = "Error input: ".concat(e.getMessage());
            }
        }
        req.setAttribute("message", message);
        req.getRequestDispatcher("WEB-INF/jsp/User_edit.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {

    }
}
