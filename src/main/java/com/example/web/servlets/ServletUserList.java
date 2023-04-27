package com.example.web.servlets;

import com.example.dao.UsersDao;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;


@WebServlet(name = "ServletUserList", value = "/list.jhtml")
public class ServletUserList extends HttpServlet  {
    UsersDao usersDAO = new UsersDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.setAttribute("list", usersDAO.getAll());
        request.getRequestDispatcher("WEB-INF/jsp/UserList.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
