package com.alfred.healthylife.AdminController.Other;

import com.alfred.healthylife.AdminController.BaseServlet;
import com.alfred.healthylife.DAO.ImageDAO;
import com.alfred.healthylife.AdminService.ImageService;
import com.alfred.healthylife.Util.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "UploadImage", urlPatterns = "/image/upload")
public class UploadImage extends BaseServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    protected void dealWithSessionAlive(HttpServletRequest request, HttpServletResponse response, HttpSession session
            , PrintWriter out, long current_user, int current_user_type) throws IOException {
        super.dealWithSessionAlive(request, response, session, out, current_user, current_user_type);
        String imageString = request.getParameter("imageString");
        System.out.println("imageString:" + imageString);
        String[] imageStrings = imageString.split("，");

        StringBuilder stringBuilder = new StringBuilder();
        ImageDAO imageDAO = new ImageDAO();
        ImageService imageService = new ImageService();
        long timestamp = Util.getCurrentTimeStamp();

        System.out.println("imageStrings.length" + imageStrings.length);

        for (int i = 0; i < imageStrings.length; i++) {
            String series = "";
            if (i < 10) {
                series = "0000" + i;
            } else if (i < 100) {
                series = "000" + i;
            } else if (i < 1000) {
                series = "00" + i;
            } else if (i < 10000) {
                series = "0" + i;
            } else if (i < 100000) {
                series = String.valueOf(i);
            }
            String name = current_user_type + "_" + current_user + "_" + timestamp + "_" + series;

            imageDAO.createTipImage(name, imageStrings[i], "png");

            BufferedImage bufferedImage = ImageService.getBufferedImage(imageStrings[i]);

            stringBuilder.append(name).append(".png,");
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("address", stringBuilder.toString());
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        list.add(map);
        out.append(Util.transformFromCollection(list));
    }

    protected void dealWithSessionDead(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws IOException {
        super.dealWithSessionDead(request, response, out);
    }


}
