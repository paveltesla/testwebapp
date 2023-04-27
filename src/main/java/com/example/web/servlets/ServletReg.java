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

@WebServlet(name = "ServletReg", value = "/reg.jhtml")
public class ServletReg extends HttpServlet {
    UsersDao dao = new UsersDao();
    Validation validation = new Validation();
    String login;
    String pass;
    String email;
    String role;
    String message;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/Sign_in.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        login = req.getParameter("login");
        pass = req.getParameter("pass");
        email = req.getParameter("email");
        role = req.getParameter("role");
        if(dao.userIsExist(login,pass)){
            message = "This login exist";
            req.setAttribute("message",message);
            req.getRequestDispatcher("WEB-INF/jsp/Sign_in.jsp").forward(req,resp);
        } else if (validation.isValidLogin(login)){
            message = "Login is invalid";
            req.setAttribute("message",message);
            req.getRequestDispatcher("WEB-INF/jsp/Sign_in.jsp").forward(req,resp);
        } else if (!validation.isValidPassword(pass)) {
            message = "Password is invalid";
            req.setAttribute("message",message);
            req.getRequestDispatcher("WEB-INF/jsp/Sign_in.jsp").forward(req,resp);
        } else if (!validation.isValidEmail(email)) {
            message ="email is invalid";
            req.setAttribute("message",message);
            req.getRequestDispatcher("WEB-INF/jsp/Sign_in.jsp").forward(req,resp);
        }else {
            dao.regUser(login,pass,email, User.Role.valueOf(role));
            resp.sendRedirect("/auth.jhtml");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
