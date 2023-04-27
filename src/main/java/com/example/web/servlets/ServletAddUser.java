package com.example.web.servlets;

import com.example.dao.UsersDao;
import com.example.domain.User;
import com.example.utilites.Validation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;

@WebServlet(name = "ServletAddUser", value = "/add.jhtml")
public class ServletAddUser extends HttpServlet {
    UsersDao dao = new UsersDao();
    Validation validation = new Validation();
    String message;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/User_add.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String pass =req.getParameter("pass");
        String name = req.getParameter("name");
        String surname= req.getParameter("surname");
        String patronymic= req.getParameter("patronymic");
        String birthdayStr= req.getParameter("birthday");
        String email= req.getParameter("email");
        String role = req.getParameter("role");

        Date birthday = validation.date(birthdayStr);

        if(dao.userIsExist(login, pass)){
            message = "User "+login+" is exist";
        }else if (validation.isValidLogin(login)){
            message = "The login cannot contain spaces or be equal to Null";
        } else if (!validation.isValidPassword(pass)) {
            message = "The password must not be less than 8 characters";
        } else if (!validation.isValidEmail(email)) {
            message = "There is no exist mail";
        } else {
            dao.addUser(login,pass,name,surname,patronymic,birthday, User.Role.valueOf(role),email);
            message = "User has be added!";
        }
        req.setAttribute("message",message);
        req.getRequestDispatcher("WEB-INF/jsp/User_add.jsp").forward(req,resp);

    }
    @Override
    public void destroy() {
    }
}
