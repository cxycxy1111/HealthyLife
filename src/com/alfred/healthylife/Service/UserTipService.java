package com.alfred.healthylife.Service;

import com.alfred.healthylife.DAO.UserTipDAO;
import com.alfred.healthylife.DAO.UserTipLogDAO;
import com.alfred.healthylife.Util.Util;

import java.util.ArrayList;
import java.util.HashMap;

public class UserTipService extends Service{

    private UserTipLogDAO userTipLogDAO;
    private UserTipDAO userTipDAO;

    public UserTipService() {
        userTipDAO = new UserTipDAO();
        userTipLogDAO = new UserTipLogDAO();
    }

    /**
     * 收藏
     * @param user_id
     * @param tip_id
     * @param create_time
     * @return
     */
    public String addFavorite(long user_id,long tip_id,String create_time) {
        ArrayList<HashMap<String,Object>> list_rel = userTipDAO.queryByUserAndTip(user_id,tip_id,0);
        if (list_rel.size() == 0) {//新增
            if (userTipDAO.addFavorite(user_id,tip_id,0,create_time)) {
                userTipLogDAO.addFavorite(user_id,tip_id,create_time,user_id,1);
                return SUCCESS;
            }
            return FAIL;
        }
        if (Util.getBoolFromMapList(list_rel,"del")) {//处于删除状态时，进行恢复操作
            if (userTipDAO.recoverFavorite(user_id,tip_id,0)) {
                userTipLogDAO.addFavorite(user_id,tip_id,create_time,user_id,1);
                return SUCCESS;
            }
            return FAIL;
        }
        return SUCCESS;
    }

    /**
     * 取消收藏
     * @param user_id
     * @param tip_id
     * @param create_time
     * @return
     */
    public String removeFavorite(long user_id,long tip_id,String create_time) {
        ArrayList<HashMap<String,Object>> list_rel = userTipDAO.queryByUserAndTip(user_id,tip_id,0);
        if (list_rel.size() == 0) {//本来没有收藏的话，不需要取消收藏
            return SUCCESS;
        }
        if (!Util.getBoolFromMapList(list_rel,"del")) {//如果正处于收藏状态
            if (userTipDAO.removeFavorite(user_id,tip_id,0)) {
                userTipLogDAO.removeFavorite(user_id,tip_id,create_time,user_id,1);
                return SUCCESS;
            }
            return FAIL;
        }
        return SUCCESS;
    }

}
