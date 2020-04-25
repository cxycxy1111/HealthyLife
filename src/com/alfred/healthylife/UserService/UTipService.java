package com.alfred.healthylife.UserService;

import com.alfred.healthylife.AdminService.Service;
import com.alfred.healthylife.DAO.TipDAO;
import com.alfred.healthylife.DAO.TipTagRelDAO;
import com.alfred.healthylife.DAO.UserTipRelDAO;
import com.alfred.healthylife.Util.Util;

import java.util.*;

public class UTipService extends Service {

    private TipDAO tipDAO;
    private TipTagRelDAO tipTagRelDAO;
    private UserTipRelDAO userTipRelDAO;

    public UTipService() {
        tipDAO = new TipDAO();
        tipTagRelDAO = new TipTagRelDAO();
        userTipRelDAO = new UserTipRelDAO();
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

    public String queryTipsByTag(long tag_id, int page_no, int num_lmt) {
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        list = tipTagRelDAO.queryByTag(tag_id, page_no, num_lmt);
        Collections.shuffle(list);
        return Util.transformFromCollection(list);
    }

    /**
     * 查询贴士详情
     *
     * @param id
     * @return
     * @pa
     */
    public String query(long id, long user_id) {
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        list = tipDAO.query(id);
        HashMap<String, Object> map = new HashMap<>();
        map = list.get(0);

        if (list.size() > 0) {//读取内容
            list = tipDAO.query(id);
            map = list.get(0);
            String content = Util.readTipContent(id);
            map.put("content", content);
        }

        if (user_id != 0) {
            ArrayList<HashMap<String, Object>> list1 = new ArrayList<>();
            list1 = userTipRelDAO.queryByUserAndTip(user_id, id, 0);
            if (list1.size() > 0) {
                if (Util.getBoolFromMapList(list1, "del")) {
                    map.put("like", 0);
                } else {
                    map.put("like", 1);
                }
            } else {
                map.put("like", 0);
            }
        } else {
            map.put("like", 0);
        }
        return Util.transformFromCollection(list);
    }

}
