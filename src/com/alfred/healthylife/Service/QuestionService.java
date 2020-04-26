package com.alfred.healthylife.Service;

import com.alfred.healthylife.DAO.QuestionDAO;

public class QuestionService extends Service {

    private QuestionDAO questionDAO;

    public QuestionService() {
        questionDAO = new QuestionDAO();
    }

    public String create(String title, String create_time) {
        title.replace("'", "''");
        if (title.contains("--")) {
            return ILLEGAL;
        }
        if (questionDAO.create(title, create_time) != 0) {
            return SUCCESS;
        }
        return FAIL;
    }

}
