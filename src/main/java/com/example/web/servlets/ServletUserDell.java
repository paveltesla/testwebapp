package com.example.web.servlets;

import com.example.dao.UserDao;
import com.example.services.ServiceDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@WebServlet(name = "ServletUserDell", value = "/dell.jhtml")
public class ServletUserDell extends HttpServlet {
    String login;
    String pass;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ServiceDao serviceDao;

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
        if (serviceDao.userIsExist(login, pass)) {
            serviceDao.delete(login);
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
