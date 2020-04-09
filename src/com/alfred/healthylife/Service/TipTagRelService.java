package com.alfred.healthylife.Service;

import com.alfred.healthylife.DAO.TipTagRelDAO;

public class TipTagRelService extends Service {

    private TipTagRelDAO tipTagRelDAO;

    public TipTagRelService() {
        tipTagRelDAO = new TipTagRelDAO();
    }

    public String create(long tip_id, String tag_id) {
        return null;
    }

    public String delete(long tip_id, long tag_id) {
        if (tipTagRelDAO.query(tip_id, tag_id).size() == 0) {
            return SUCCESS;
        }
        if (tipTagRelDAO.delete(tip_id, tag_id)) {
            return SUCCESS;
        }
        return FAIL;
    }

}
