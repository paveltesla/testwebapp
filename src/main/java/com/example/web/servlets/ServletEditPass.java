package com.example.web.servlets;

import com.example.dao.UserDao;
import com.example.services.AdminService;
import com.example.utilites.Validation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@WebServlet(name = "ServletEditPass", value = "/editPass.jhtml")
public class ServletEditPass extends HttpServlet {

    @Autowired
    private UserDao userDao;
    @Autowired
    private AdminService adminService;

    Validation validation = new Validation();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/PassEdit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String pass = (String) session.getAttribute("pass");
        String oPass = req.getParameter("oPass");
        String nPass = req.getParameter("nPass");
        String nPassRep = req.getParameter("nPassRep");
        String login = (String) session.getAttribute("login");

        String message;
        if (pass.equals(oPass)) {
            if (oPass.equals(nPass)) {
                message = "Old password is equal New Password";
            } else {
                if (nPass.equals(nPassRep)) {
                    if (validation.isValidPassword(nPassRep)) {
                        adminService.editPass(
                                userDao.getUserByLogin(login), nPassRep);
                        message = "The Password has been changed";
                    } else {
                        message = "Password Can't be short by 8 chars and long by 20";
                    }
                } else {
                    message = "The New Password is not equals New Password repyt";
                }
            }
        } else {
            message = "Old Password is wrong";
        }
        req.setAttribute("message", message);
        req.getRequestDispatcher("WEB-INF/jsp/PassEdit.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
