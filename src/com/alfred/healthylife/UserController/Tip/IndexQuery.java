package com.alfred.healthylife.UserController.Tip;

import com.alfred.healthylife.UserController.BaseServlet;
import com.alfred.healthylife.UserService.UTipService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "IndexQuery", urlPatterns = "/user/tip/indexQuery")
public class IndexQuery extends BaseServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    protected void dealWithSessionAlive(HttpServletRequest request, HttpServletResponse response, HttpSession session
            , PrintWriter out, long current_user, int current_user_type) throws IOException {
        super.dealWithSessionAlive(request, response, session, out, current_user, current_user_type);

        UTipService uTipService = new UTipService();
        out.append(uTipService.queryThreeTipsForIndex());
    }

    protected void dealWithSessionDead(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws IOException {
        UTipService uTipService = new UTipService();
        out.append(uTipService.queryThreeTipsForIndex());
    }


}
