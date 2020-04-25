package com.alfred.healthylife.UserController.User;

import com.alfred.healthylife.UserController.BaseServlet;
import com.alfred.healthylife.UserService.UUserUService;
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

@WebServlet(name = "UserLogin", urlPatterns = "/user/login")
public class UserLogin extends BaseServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request,response);
    }

    protected void dealWithSessionAlive(HttpServletRequest request, HttpServletResponse response, HttpSession session
            , PrintWriter out, long current_user, int current_user_type) throws IOException {
        super.dealWithSessionAlive(request, response, session, out, current_user, current_user_type);
    }

    protected void dealWithSessionDead(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UUserUService userService = new UUserUService();
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap = userService.loginCheck(email,password,Util.getIPAdress(request),Util.getCurrentTime());
        if (hashMap == null) {
            out.append(NOT_MATCH);
        }else {
            HttpSession session_1 = request.getSession();
            session_1.setMaxInactiveInterval(7*24*60*60);
            session_1.setAttribute("id",Long.parseLong(String.valueOf(hashMap.get("id"))));
            session_1.setAttribute("type", 1);
            ArrayList<HashMap<String,Object>> list = new ArrayList<>();
            list.add(hashMap);
            out.append(Util.transformFromCollection(list));
        }
    }

}
