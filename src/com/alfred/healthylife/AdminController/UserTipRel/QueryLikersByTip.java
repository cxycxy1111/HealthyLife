package com.alfred.healthylife.AdminController.UserTipRel;

import com.alfred.healthylife.AdminController.BaseServlet;
import com.alfred.healthylife.AdminService.UserTipRelService;
import com.alfred.healthylife.Util.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "QueryLikersByTip", urlPatterns = "/usertip/query/liker")
public class QueryLikersByTip extends BaseServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    protected void dealWithSessionAlive(HttpServletRequest request, HttpServletResponse response, HttpSession session, PrintWriter out, long current_user, int current_user_type) throws IOException {
        super.dealWithSessionAlive(request, response, session, out, current_user, current_user_type);
        long tip_id = Util.getLongFromRequest(request, "tip_id");
        int page_no = Util.getIntFromRequest(request, "page_no");
        int num_lmt = Util.getIntFromRequest(request, "num_lmt");

        UserTipRelService userTipRelService = new UserTipRelService();
        out.append(userTipRelService.queryByTip(tip_id, 0, page_no, num_lmt));
    }

    protected void dealWithSessionDead(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws IOException {
        super.dealWithSessionDead(request, response, out);
    }


}