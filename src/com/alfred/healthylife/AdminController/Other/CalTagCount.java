package com.alfred.healthylife.AdminController.Other;

import com.alfred.healthylife.AdminController.BaseServlet;
import com.alfred.healthylife.Util.CalTagCountTask;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Timer;

@WebServlet(name = "CalTagCount", urlPatterns = "/CalTagCount", loadOnStartup = 1)
public class CalTagCount extends HttpServlet {

    private Timer timer = null;

    /**
     * public void init(ServletConfig config) throws ServletException {
     * super.init(config);
     * timer = new Timer(true);
     * timer.schedule(new CalTagCountTask(), 20*60*1000, 1000*3600*24);
     * }
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void dealWithSessionAlive(HttpServletRequest request, HttpServletResponse response, HttpSession session
            , PrintWriter out, long current_user, int current_user_type) throws IOException {
    }

    protected void dealWithSessionDead(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws IOException {
    }


}
