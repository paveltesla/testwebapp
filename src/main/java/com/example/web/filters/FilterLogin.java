package com.example.web.filters;

import com.example.domain.User;

import com.example.DAO.UsersDao;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter(filterName ="FilterLogin", value = "/auth.jhtml")
public class FilterLogin implements Filter {
    String message;
    UsersDao dao = new UsersDao();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession();


        String login = req.getParameter("login");
        String pass = req.getParameter("pass");

        if(nonNull(session)&&nonNull(session.getAttribute("login"))&&nonNull(session.getAttribute("pass"))){
            final User.Role role = (User.Role) session.getAttribute("role");
            moveToMenu(req,resp,role);
        } else if (dao.userIsExist(login, pass)) {

            User.Role role = dao.getRoleLoginPass(login,pass);

            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("pass", pass);
            req.getSession().setAttribute("role",role);

            moveToMenu(req,resp,role);
        }else {
            moveToMenu(req, resp, User.Role.UNKNOWN);
        }
    }
    private void moveToMenu(HttpServletRequest req, HttpServletResponse resp, User.Role role) throws IOException, ServletException{
        String login = req.getParameter("login");

        if(role.equals(User.Role.ADMIN)){
            req.setAttribute("login",login);
            req.getRequestDispatcher("WEB-INF/jsp/HomePage.jsp").forward(req,resp);
        } else if (role.equals(User.Role.USER)) {
            req.setAttribute("login",login);
            req.getRequestDispatcher("WEB-INF/jsp/HomePage.jsp").forward(req,resp);
        }else {
            message = "Login or password is wrong!";
            req.setAttribute("message", message);
            req.getRequestDispatcher("WEB-INF/jsp/Login.jsp").forward(req,resp);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}