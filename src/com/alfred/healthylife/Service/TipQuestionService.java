package com.alfred.healthylife.Service;

import com.alfred.healthylife.DAO.TipQuestionDAO;

public class TipQuestionService extends Service {

    private TipQuestionDAO tipQuestionDAO;

    public TipQuestionService() {
        tipQuestionDAO = new TipQuestionDAO();
    }

    public String create(long tip_id, String title, long creator, String create_time) {
        if (title.length() > 256) {
            return TOO_LONG;
        }
        if (title.equals("")) {
            return TOO_SHORT;
        }
        if (tipQuestionDAO.create(tip_id, title, creator, create_time)) {
            return SUCCESS;
        }
        return FAIL;

    }

}
