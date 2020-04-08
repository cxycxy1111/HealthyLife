package com.alfred.healthylife.Controller.User;

import com.alfred.healthylife.Controller.BaseServlet;
import com.alfred.healthylife.DAO.UserLogDAO;
import com.alfred.healthylife.Service.UserService;
import com.alfred.healthylife.Util.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "UserLogout", urlPatterns = "/user/logout")
public class UserLogout extends BaseServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    protected void dealWithSessionAlive(HttpServletRequest request, HttpServletResponse response, HttpSession session, PrintWriter out, long current_user) throws IOException {
        UserLogDAO userLogDAO = new UserLogDAO();
        userLogDAO.logout(current_user, Util.getCurrentTime(), current_user, 1);
        session.invalidate();
        out.append(SUCCESS);
    }

    protected void dealWithSessionDead(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws IOException {
        out.append(SUCCESS);
    }

}
