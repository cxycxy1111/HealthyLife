package com.alfred.healthylife.UserService;

import com.alfred.healthylife.AdminService.Service;
import com.alfred.healthylife.DAO.TipDAO;
import com.alfred.healthylife.Util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class UTipService extends Service {

    private TipDAO tipDAO;

    public UTipService() {
        tipDAO = new TipDAO();
    }

    public static void makeCount(int bound) {


    }

    public String queryThreeTipsForIndex() {
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        list = tipDAO.query("0");
        int bound = list.size() - 1;
        Random random = new Random();
        int i = random.nextInt(bound);
        int j = random.nextInt(bound);
        int k = random.nextInt(bound);
        while (i == j) {
            j = random.nextInt(bound);
        }
        while (k == j || k == i) {
            k = random.nextInt(bound);
        }
        ArrayList<HashMap<String, Object>> list_result = new ArrayList<>();
        list_result.add(list.get(i));
        list_result.add(list.get(j));
        list_result.add(list.get(k));
        return Util.transformFromCollection(list_result);
    }

}
