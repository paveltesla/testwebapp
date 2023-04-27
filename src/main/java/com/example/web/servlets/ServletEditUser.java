package com.example.web.servlets;

import com.example.dao.UsersDao;
import com.example.domain.User;
import com.example.utilites.Validation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;

@WebServlet(name = "ServletEditUser", value = "/edit.jhtml")
public class ServletEditUser extends HttpServlet {
UsersDao dao = new UsersDao();
String loginToEdit;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        loginToEdit = req.getParameter("login");
        HttpSession session = req.getSession();
        for (User u:dao.getAll()){
            if (u.getLogin().equals(loginToEdit)){
                session.setAttribute("user",u);
            }
        }
        req.getRequestDispatcher("WEB-INF/jsp/User_edit.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Validation validation = new Validation();

        String login = req.getParameter("login");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String patronymic = req.getParameter("patronymic");
        String birthdayStr = req.getParameter("birthday");
        String role = req.getParameter("role");
        String email = req.getParameter("email");

        String message;


        if(/*validation.isValidLogin(login)*/ validation.isValidEmail(email) && validation.isValidRole(role) && validation.isValidDate(birthdayStr)){
            Date birthday = validation.date(birthdayStr);
            dao.editUser(dao.getLogin(login),name,surname,patronymic,birthday,role,email);
            message = "user is edited";
        }else {
            message ="login email or date is invalid";
        }
        req.setAttribute("message",message);
        req.getRequestDispatcher("WEB-INF/jsp/User_edit.jsp").forward(req,resp);
    }

    @Override
    public void destroy() {

    }
}
