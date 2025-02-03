package com.example.web.filters;


import com.example.domain.User;
import com.example.services.AdminService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Component
@Order(1)
@WebFilter(filterName = "FilterLogin", value = "/auth.jhtml")
public class FilterLogin implements Filter {

    @Autowired
    private AdminService adminService;

    public void init(FilterConfig fConfig) {
        ServletContext context = fConfig.getServletContext();
        context.log("RequestLoggingFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();

        String login = req.getParameter("login");
        String pass = req.getParameter("pass");

        if ((session.getAttribute("login") != null) && (session.getAttribute("pass") != null)) {
            req.setAttribute("login", session.getAttribute("login"));
            req.setAttribute("pass", session.getAttribute("pass"));
            req.setAttribute("role", session.getAttribute("role"));
            moveToMenu(req, resp);

        } else if (adminService.userIsExist(login, pass)) {
            User user = adminService.getUserByLogin(login);
            req.setAttribute("login", user.getLogin());
            req.setAttribute("pass", user.getPass());
            req.setAttribute("role", user.getRole());

            moveToMenu(req, resp);
        } else {
            req.getSession().invalidate();
            String message = "Login or password is wrong!";
            req.setAttribute("message", message);
            req.getRequestDispatcher("WEB-INF/jsp/Login.jsp").forward(req, resp);
        }
    }

    private void moveToMenu(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        session.setAttribute("login", req.getAttribute("login"));
        session.setAttribute("pass", req.getAttribute("pass"));
        session.setAttribute("role", req.getAttribute("role"));
        if (session.getAttribute("role").toString().contains("ADMIN")) {
            req.setAttribute("role", "ADMIN");
        }
        req.getRequestDispatcher("WEB-INF/jsp/HomePage.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}