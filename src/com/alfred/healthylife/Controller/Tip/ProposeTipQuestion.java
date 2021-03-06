package com.alfred.healthylife.Controller.Tip;

import com.alfred.healthylife.Controller.BaseServlet;
import com.alfred.healthylife.Service.TipQuestionService;
import com.alfred.healthylife.Service.UserTipRelService;
import com.alfred.healthylife.Util.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ProposeTipQuestion", urlPatterns = "/user/tip/proposeTipQuestion")
public class ProposeTipQuestion extends BaseServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    protected void dealWithSessionAlive(HttpServletRequest request, HttpServletResponse response, HttpSession session
            , PrintWriter out, long current_user, int current_user_type) throws IOException {
        super.dealWithSessionAlive(request, response, session, out, current_user, current_user_type);
        long tip_id = Util.getLongFromRequest(request, "tip_id");
        String title = request.getParameter("title");
        TipQuestionService tipQuestionService = new TipQuestionService();
        out.append(tipQuestionService.create(tip_id, title, current_user, Util.getCurrentTime()));
    }

    protected void dealWithSessionDead(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws IOException {
        long tip_id = Util.getLongFromRequest(request, "tip_id");
        String title = request.getParameter("title");
        TipQuestionService tipQuestionService = new TipQuestionService();
        out.append(tipQuestionService.create(tip_id, title, 0, Util.getCurrentTime()));
    }


}
