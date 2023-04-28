package com.example.web.filters;

import com.example.dao.RoleDaoSingleton;
import com.example.domain.User;
import com.example.services.ServiceDaoSingleton;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter(filterName ="FilterLogin", value = "/auth.jhtml")
public class FilterLogin implements Filter {
    String message;
    String login;
    String pass;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession();


        login = req.getParameter("login");
        pass = req.getParameter("pass");

        if(nonNull(session)&&nonNull(session.getAttribute("login"))&&nonNull(session.getAttribute("pass"))){
            final User.Role role = (User.Role) session.getAttribute("role");
            moveToMenu(req,resp,role);
        } else if (ServiceDaoSingleton.getInstance().getValue().userIsExist(login, pass)) {

            User.Role role = RoleDaoSingleton.getInstance().getValue().getRoleLoginPass(login,pass);

            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("pass", pass);
            req.getSession().setAttribute("role",role);

            moveToMenu(req,resp,role);
        }else {
            moveToMenu(req, resp, User.Role.UNKNOWN);
        }
    }
    private void moveToMenu(HttpServletRequest req, HttpServletResponse resp, User.Role role) throws IOException, ServletException{

        if(role.equals(User.Role.ADMIN)){
            req.setAttribute("login",login);
            req.getRequestDispatcher("WEB-INF/jsp/HomePage.jsp").forward(req,resp);
        } else if (role.equals(User.Role.USER)) {
            req.setAttribute("login",login);
            req.getRequestDispatcher("WEB-INF/jsp/HomePage.jsp").forward(req,resp);
        }else {
            req.getSession().invalidate();
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