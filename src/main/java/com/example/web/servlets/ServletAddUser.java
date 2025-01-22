package com.example.web.servlets;

import com.example.dao.UserDaoImplement;
import com.example.domain.Role;
import com.example.services.AdminServiceImplement;
import com.example.utilites.Validation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletAddUser", value = "/add.jhtml")
public class ServletAddUser extends HttpServlet {

    Validation validation = new Validation();
    String message;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/User_add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String birthdayStr = req.getParameter("birthday");
        String role = req.getParameter("role");

        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new Role(role));

        if (AdminServiceImplement.getInstance().userIsExist(login, pass)) {
            message = "User " + login + " is exist";
        } else if (!validation.isValidLogin(login)) {
            message = "The login cannot contain spaces or be equal to Null";
        } else if (!validation.isValidPassword(pass)) {
            message = "The password must not be less than 8 characters";
        } else if (age.equals("")) {
            message = "Insert your age";
        } else if (Integer.parseInt(age) < 18) {
            message = "Age doesn't be minor by 18";
        } else {
            UserDaoImplement.getInstance().addUser(login, pass, name, Integer.parseInt(age), birthdayStr, roles);
            message = "User has be added!";
        }
        req.setAttribute("message", message);
        req.getRequestDispatcher("WEB-INF/jsp/User_add.jsp").forward(req, resp);

    }

    @Override
    public void destroy() {
    }
}
