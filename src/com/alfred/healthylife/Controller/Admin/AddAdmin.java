package com.alfred.healthylife.Controller.Admin;

import com.alfred.healthylife.Service.AdminService;
import com.alfred.healthylife.Controller.BaseServlet;
import com.alfred.healthylife.Util.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddAdmin",urlPatterns = "/admin/add")
public class AddAdmin extends BaseServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request,response);
    }

    protected void dealWithSessionAlive(HttpServletRequest request, HttpServletResponse response, HttpSession session, PrintWriter out, long current_user) throws IOException {
        super.dealWithSessionAlive(request, response, session, out, current_user);
        String email = request.getParameter("email");
        String password = request.getParameter("passowrd");
        String confirm_password = request.getParameter("confirm_password");

        AdminService adminService = new AdminService();
        out.append(adminService.create(email,password,confirm_password,current_user, Util.getCurrentTime()));
    }

    protected void dealWithSessionDead(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws IOException {
        super.dealWithSessionDead(request, response, out);
    }


}
