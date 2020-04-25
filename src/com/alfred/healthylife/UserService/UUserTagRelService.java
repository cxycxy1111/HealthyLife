package com.alfred.healthylife.UserService;

import com.alfred.healthylife.DAO.*;
import com.alfred.healthylife.Util.Util;

import java.util.ArrayList;
import java.util.HashMap;

public class UUserTagRelService extends UService {

    private UserTagLogDAO userTagLogDAO;
    private UserTagRelDAO userTagRelDAO;
    private TagDAO tagDAO;

    public UUserTagRelService() {
        userTagRelDAO = new UserTagRelDAO();
        userTagLogDAO = new UserTagLogDAO();
        tagDAO = new TagDAO();
    }

    /**
     * 关注
     *
     * @param user_id
     * @param tag_id
     * @param create_time
     * @return
     */
    public String addFavorite(long user_id, long tag_id, String create_time) {
        ArrayList<HashMap<String, Object>> list_rel = userTagRelDAO.queryByUserAndTag(user_id, tag_id, 0);
        if (list_rel.size() == 0) {//新增
            if (userTagRelDAO.addFavorite(user_id, tag_id, 0, create_time)) {
                tagDAO.increaseFavouriteCount(tag_id);
                userTagLogDAO.addFavorite(user_id, tag_id, create_time, user_id, 1);
                return SUCCESS;
            }
            return FAIL;
        }
        if (Util.getBoolFromMapList(list_rel, "del")) {//处于删除状态时，进行恢复操作
            if (userTagRelDAO.recoverFavorite(user_id, tag_id, 0)) {
                tagDAO.increaseFavouriteCount(tag_id);
                userTagLogDAO.addFavorite(user_id, tag_id, create_time, user_id, 1);
                return SUCCESS;
            }
            return FAIL;
        }
        return SUCCESS;
    }

    /**
     * 取消关注
     *
     * @param user_id
     * @param tag_id
     * @param create_time
     * @return
     */
    public String removeFavorite(long user_id, long tag_id, String create_time) {
        ArrayList<HashMap<String, Object>> list_rel = userTagRelDAO.queryByUserAndTag(user_id, tag_id, 0);
        if (list_rel.size() == 0) {//本来没有收藏的话，不需要取消收藏
            return SUCCESS;
        }
        if (!Util.getBoolFromMapList(list_rel, "del")) {//如果正处于收藏状态
            if (userTagRelDAO.removeFavorite(user_id, tag_id, 0)) {
                tagDAO.decreaseFavouriteCount(tag_id);
                userTagLogDAO.removeFavorite(user_id, tag_id, create_time, user_id, 1);
                return SUCCESS;
            }
            return FAIL;
        }
        return SUCCESS;
    }

    /**
     * 查询用户关注了的贴士列表
     *
     * @param user_id
     * @param type
     * @param page_no
     * @param num_lmt
     * @return
     */
    public String queryByUser(long user_id, int type, int page_no, int num_lmt) {
        return Util.transformFromCollection(userTagRelDAO.queryByUser(user_id, type, page_no, num_lmt));
    }

    /**
     * 查询用户关注了的贴士列表
     *
     * @param tag_id
     * @param type
     * @param page_no
     * @param num_lmt
     * @return
     */
    public String queryByTag(long tag_id, int type, int page_no, int num_lmt) {
        return Util.transformFromCollection(userTagRelDAO.queryByTag(tag_id, type, page_no, num_lmt));
    }


}
