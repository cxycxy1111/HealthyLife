package com.alfred.healthylife.Service;

import com.alfred.healthylife.DAO.TipDAO;
import com.alfred.healthylife.DAO.TipTagRelDAO;
import com.alfred.healthylife.DAO.UserTagRelDAO;
import com.alfred.healthylife.DAO.UserTipRelDAO;
import com.alfred.healthylife.Util.Util;

import java.util.*;

public class TipService extends Service {

    private TipDAO tipDAO;
    private TipTagRelDAO tipTagRelDAO;
    private UserTipRelDAO userTipRelDAO;
    private UserTagRelDAO userTagRelDAO;

    public TipService() {
        tipDAO = new TipDAO();
        tipTagRelDAO = new TipTagRelDAO();
        userTipRelDAO = new UserTipRelDAO();
        userTagRelDAO = new UserTagRelDAO();
    }

    public static void makeCount(int bound) {

    }

    //用户未登录的情况下查询
    public String queryThreeTipsForIndex() {
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        list = tipDAO.query("0");

        ArrayList<HashMap<String, Object>> list_temp = new ArrayList<>();

        list_temp.addAll(list);

        int bound = list_temp.size() - 1;
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
        list_result.add(list_temp.get(i));
        list_result.add(list_temp.get(j));
        list_result.add(list_temp.get(k));
        return Util.transformFromCollection(list_result);
    }

    //用户登录的情况下查询
    public String queryThreeTipsForIndex(long user_id) {

        return queryThreeTipsForIndex();
        /**

         ArrayList<HashMap<String,Object>> list_favourite_tags = new ArrayList<>();
         list_favourite_tags = userTagRelDAO.queryByUser(user_id,0);

         if (list_favourite_tags.size() == 0) {//用户未关注标签时
         return queryThreeTipsForIndex();
         }

         //找出喜欢的标签下的文章
         ArrayList<HashMap<String,Object>> list_favourite_tips = new ArrayList<>();
         list_favourite_tips = tipTagRelDAO.queryFavouriteTagsRelatedTipsByUserId(user_id);

         if (list_favourite_tips.size() <= 2) {//喜欢的标签下的文章小于2篇时
         return queryThreeTipsForIndex();
         }

         int bound = list_favourite_tips.size() - 1;
         Random random = new Random();
         int i,j,k;
         i = random.nextInt(bound);
         j = random.nextInt(bound);
         k = random.nextInt(bound);
         while (i == j) {
         j = random.nextInt(bound);
         }
         while (k == j || k == i) {
         k = random.nextInt(bound);
         }
         ArrayList<HashMap<String, Object>> list_result = new ArrayList<>();
         list_result.add(list_favourite_tips.get(i));
         list_result.add(list_favourite_tips.get(j));

         //找出不喜欢的文章
         ArrayList<HashMap<String,Object>> list_no_favourite_tips = new ArrayList<>();
         list_no_favourite_tips = tipTagRelDAO.queryNotFavouriteTagsRelatedTipsByUserId(user_id);

         if (list_no_favourite_tips.size() == 0) {//判断是否有
         list_result.add(list_favourite_tips.get(k));
         }else {
         k = random.nextInt(list_no_favourite_tips.size());
         list_result.add(list_no_favourite_tips.get(k));
         }

         Collections.shuffle(list_result);//打乱顺序

         return Util.transformFromCollection(list_result);
         **/
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

        if (list.size() == 0) {
            return NO_SUCH_RECORD;
        }

        tipDAO.increaseReadCount(id);//提升阅读数

        HashMap<String, Object> map = new HashMap<>();
        map = list.get(0);

        //从文件中读取内容
        list = tipDAO.query(id);
        map = list.get(0);
        String content = Util.readTipContent(id);
        map.put("content", content);

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
