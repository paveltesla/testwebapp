package com.example.web.servlets;

import com.example.dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;

@WebServlet(name = "ServletUserList", value = "/list.jhtml")
public class ServletUserList extends HttpServlet {

    @Autowired
    private UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.setAttribute("list", userDao.getAll());
        request.getRequestDispatcher("WEB-INF/jsp/UserList.jsp").forward(request, response);
    }
}