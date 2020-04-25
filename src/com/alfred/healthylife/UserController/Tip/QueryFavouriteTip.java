package com.alfred.healthylife.UserController.Tip;

import com.alfred.healthylife.UserController.BaseServlet;
import com.alfred.healthylife.UserService.UUserTipRelService;
import com.alfred.healthylife.Util.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "QueryFavouriteTip", urlPatterns = "/user/tip/queryFavouriteTip")
public class QueryFavouriteTip extends BaseServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    protected void dealWithSessionAlive(HttpServletRequest request, HttpServletResponse response, HttpSession session
            , PrintWriter out, long current_user, int current_user_type) throws IOException {
        super.dealWithSessionAlive(request, response, session, out, current_user, current_user_type);
        int page_no = Util.getIntFromRequest(request, "page_no");
        UUserTipRelService uUserTipRelService = new UUserTipRelService();
        out.append(uUserTipRelService.queryByUser(current_user, 0, page_no, 20));
    }

    protected void dealWithSessionDead(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws IOException {
        super.dealWithSessionDead(request, response, out);
    }


}
